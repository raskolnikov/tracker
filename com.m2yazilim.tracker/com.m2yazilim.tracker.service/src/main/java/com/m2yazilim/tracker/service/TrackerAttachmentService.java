package com.m2yazilim.tracker.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Level;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.m2yazilim.tracker.constants.ErrorCodes;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerAttachments;
import com.m2yazilim.tracker.logging.Trace;
import com.m2yazilim.tracker.model.AddAttachmentModel;
import com.m2yazilim.tracker.model.Crud;
import com.m2yazilim.tracker.model.CrudOutputModel;
import com.m2yazilim.tracker.model.ServiceResponse;
import com.m2yazilim.tracker.model.SsoRole;
import com.m2yazilim.tracker.service.dao.impl.AddTrackerAttachmentDaoImpl;
import com.m2yazilim.tracker.service.messages.AddTrackerAttachmentRequest;
import com.m2yazilim.tracker.service.messages.AddTrackerAttachmentResponse;
import com.m2yazilim.tracker.shell.ServiceResponseShell;
import com.m2yazilim.tracker.util.TrackerSessionUtil;

@Service
@JsonAutoDetect
@Path("/TrackerAttachmentService")
public class TrackerAttachmentService {
	private String loggerName;

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Path("/multipleFiles")
	public ServiceResponseShell registerWebService(@Context HttpServletRequest request) {

		ServiceResponseShell serviceResponseShell = new ServiceResponseShell();
		ServiceResponse<List<CrudOutputModel>> serviceResponse = new ServiceResponse<List<CrudOutputModel>>();
		List<CrudOutputModel> returnList = new ArrayList<CrudOutputModel>();

		try {
			SsoRole role = TrackerSessionUtil.getSsoRole();
			String fileUploadLocation = Trace.getResourceByName("fileUploadLocation");

			Gson gson = new Gson();
			HashMap<Integer, Crud> fileAttachmentMap = new HashMap<Integer, Crud>();
			HashMap<Integer, Crud> fileCommentMap = new HashMap<Integer, Crud>();
			ArrayList<Crud> crudList;
			JsonParser parser = new JsonParser();

			// checks whether there is a file upload request or not
			if (ServletFileUpload.isMultipartContent(request)) {
				final FileItemFactory factory = new DiskFileItemFactory();
				final ServletFileUpload fileUpload = new ServletFileUpload(factory);

				final List<FileItem> items = fileUpload.parseRequest(request);

				if (items != null) {

					// last item must be crud list
					FileItem crudJsonString = (FileItem) items.get(items.size() - 1);
					JsonElement element = parser.parse(crudJsonString.getString());
					JsonArray crudArray = element.getAsJsonArray();

					Crud crud;
					for (int i = 0; i < crudArray.size(); i++) {

						crud = gson.fromJson(crudArray.get(i).toString(), Crud.class);
						if (crud.getTableName().equals(TrackerAttachments.class.getSimpleName())) {
							fileAttachmentMap.put(crud.getId(), crud);
						} else {// means it is comment
							fileCommentMap.put(crud.getId(), crud);
						}
					}

					List<AddAttachmentModel> addAttachmentModelList = new ArrayList<AddAttachmentModel>();
					for (int i = 0; i < items.size() - 1; i++) {
						AddAttachmentModel addAttachmentModel = new AddAttachmentModel();

						FileItem item = (FileItem) items.get(i);
						String fileName = item.getName();
						String fieldName = item.getFieldName();
						// retrieve attachment and comment id
						String[] tmp = fieldName.split(",");
						Crud attachmentCrud = null, commentCrud = null;
						attachmentCrud = fileAttachmentMap.get(Integer.parseInt(tmp[0]));

						addAttachmentModel.setAttachmentCrud(attachmentCrud);
						addAttachmentModel.setFileItem(item);

						if (tmp.length == 2) {

							commentCrud = fileCommentMap.get(Integer.parseInt(tmp[1]));
							addAttachmentModel.setCommentCrud(commentCrud);
						}
						addAttachmentModelList.add(addAttachmentModel);
					}

					// Ayni comment crud ina sahip objelerin gruplandirilmasi
					HashMap<Crud, List<AddAttachmentModel>> attachmentModelMap = new HashMap<Crud, List<AddAttachmentModel>>();
					for (AddAttachmentModel addAttachmentModel : addAttachmentModelList) {
						if (addAttachmentModel.getCommentCrud() != null) {
							if (attachmentModelMap.containsKey(addAttachmentModel.getCommentCrud())) {
								attachmentModelMap.get(addAttachmentModel.getCommentCrud()).add(addAttachmentModel);
							} else {
								List<AddAttachmentModel> addAttachmentModelTmpList = new ArrayList<AddAttachmentModel>();
								addAttachmentModelTmpList.add(addAttachmentModel);
								attachmentModelMap.put(addAttachmentModel.getCommentCrud(), addAttachmentModelTmpList);
							}
						} else {

							try {

								// insert attachment into db
								AddTrackerAttachmentDaoImpl addTrackerAttachmentDao = new AddTrackerAttachmentDaoImpl();
								AddTrackerAttachmentResponse addTrackerAttachmentResponse = new AddTrackerAttachmentResponse();
								AddTrackerAttachmentRequest addTrackerAttachmentRequest = new AddTrackerAttachmentRequest();

								addTrackerAttachmentRequest.setCommentCrud(null);
								addTrackerAttachmentRequest.setFileUploadLocation(fileUploadLocation);
								List<AddAttachmentModel> addAttachmentModelTmpList = new ArrayList<AddAttachmentModel>();
								addAttachmentModelTmpList.add(addAttachmentModel);
								addTrackerAttachmentRequest.setAddAttachmentModelList(addAttachmentModelTmpList);
								addTrackerAttachmentRequest.setUserId(role.getTrackerUser().getId());
								addTrackerAttachmentDao.execute(addTrackerAttachmentRequest,
										addTrackerAttachmentResponse);

								returnList.addAll(addTrackerAttachmentResponse.getCrudOutputModelList());

							} catch (Exception e) {
								Trace.getInstance(getLoggerAppName()).error(Level.ERROR, e);

							}

						}

					}

					// insert comment + attachmentList map into db
					if (attachmentModelMap.size() > 0) {

						List<Crud> crudKeyList = new ArrayList<Crud>(attachmentModelMap.keySet());
						for (Crud commentCrudTmp : crudKeyList) {
							try {

								AddTrackerAttachmentDaoImpl addTrackerAttachmentDao = new AddTrackerAttachmentDaoImpl();
								AddTrackerAttachmentResponse addTrackerAttachmentResponse = new AddTrackerAttachmentResponse();
								AddTrackerAttachmentRequest addTrackerAttachmentRequest = new AddTrackerAttachmentRequest();
								addTrackerAttachmentRequest.setFileUploadLocation(fileUploadLocation);
								addTrackerAttachmentRequest.setCommentCrud(commentCrudTmp);
								addTrackerAttachmentRequest.setAddAttachmentModelList(attachmentModelMap.get(commentCrudTmp));
								addTrackerAttachmentRequest.setUserId(role.getTrackerUser().getId());

								addTrackerAttachmentDao.execute(addTrackerAttachmentRequest,
										addTrackerAttachmentResponse);

								returnList.addAll(addTrackerAttachmentResponse.getCrudOutputModelList());

							} catch (Exception e) {
								Trace.getInstance(getLoggerAppName()).error(Level.ERROR, e);
							}

						}
					}
				}

			}
			serviceResponse.setStatusSuccess(true);
			serviceResponse.setResult(returnList);

		} /*
		 * catch (TrackerException e) { serviceResponse.setStatusSuccess(false);
		 * serviceResponse.setErrorCode(e.getMessage());
		 * Trace.getInstance(getLoggerAppName()).error(Level.ERROR, e);
		 * 
		 * }
		 */catch (Exception e) {
			serviceResponse.setStatusSuccess(false);
			serviceResponse.setErrorCode(ErrorCodes.ERROR_GENERAL);
			Trace.getInstance(getLoggerAppName()).error(Level.ERROR, e);
		}

		serviceResponseShell.setServiceResponse(serviceResponse);

		return serviceResponseShell;
	}

	@Autowired
	public void setLoggerName(String loggerName) {
		this.loggerName = loggerName;
	}

	public String getLoggerAppName() {
		return Trace.LOGGER_APP_PRE + this.loggerName;
	}
}
