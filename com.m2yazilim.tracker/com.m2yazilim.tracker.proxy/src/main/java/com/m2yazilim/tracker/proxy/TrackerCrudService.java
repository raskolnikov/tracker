package com.m2yazilim.tracker.proxy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Level;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.m2yazilim.tracker.constants.ErrorCodes;
import com.m2yazilim.tracker.constants.TrackerConstants;
import com.m2yazilim.tracker.dao.AbstractDAO;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerAdminRequests;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerComments;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerTasks;
import com.m2yazilim.tracker.logging.Trace;
import com.m2yazilim.tracker.model.Crud;
import com.m2yazilim.tracker.model.CrudOutputModel;
import com.m2yazilim.tracker.model.ServiceResponse;
import com.m2yazilim.tracker.model.SsoRole;
import com.m2yazilim.tracker.proxy.dao.impl.AddTrackerAdminRequestDaoImpl;
import com.m2yazilim.tracker.proxy.messages.AddTrackerAdminRequestRequest;
import com.m2yazilim.tracker.proxy.messages.AddTrackerAdminRequestResponse;
import com.m2yazilim.tracker.proxy.messages.AddTrackerCommentRequest;
import com.m2yazilim.tracker.proxy.messages.AddTrackerCommentResponse;
import com.m2yazilim.tracker.proxy.messages.UpdateTrackerTaskRequest;
import com.m2yazilim.tracker.proxy.messages.UpdateTrackerTaskResponse;
import com.m2yazilim.tracker.shell.ServiceResponseShell;
import com.m2yazilim.tracker.util.TrackerException;
import com.m2yazilim.tracker.util.TrackerSessionUtil;

@Service
@Transactional
@JsonAutoDetect
@Path("/TrackerCrudService")
public class TrackerCrudService {
	private AbstractDAO<AddTrackerCommentRequest, AddTrackerCommentResponse> addTrackerCommentDao;
	private AbstractDAO<UpdateTrackerTaskRequest, UpdateTrackerTaskResponse> updateTrackerTaskDao;

	private String loggerName;

	@POST
	@Path("/handleCrud")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Transactional
	public ServiceResponseShell handleCrud(ArrayList<Crud> crudList) throws Exception {

		ServiceResponseShell serviceResponseShell = new ServiceResponseShell();
		ServiceResponse<List<CrudOutputModel>> serviceResponse = new ServiceResponse<List<CrudOutputModel>>();
		List<CrudOutputModel> returnList = new ArrayList<CrudOutputModel>();

		AddTrackerCommentRequest addTrackerCommentRequest;
		AddTrackerCommentResponse addTrackerCommentResponse;

		UpdateTrackerTaskResponse updateTrackerTaskResponse;
		UpdateTrackerTaskRequest updateTrackerTaskRequest;

		AddTrackerAdminRequestDaoImpl addTrackerAdminRequestDaoImpl;
		AddTrackerAdminRequestRequest addTrackerAdminRequestRequest;
		AddTrackerAdminRequestResponse addTrackerAdminRequestResponse;

		try {
			SsoRole role = TrackerSessionUtil.getSsoRole();
			// Simulation
			/*
			 * ArrayList<Crud> crudList = new ArrayList<Crud>(); Crud crudTmp =
			 * new Crud(); crudTmp.setId(20);
			 * crudTmp.setTableName("tracker_comments");
			 * crudTmp.setUserID(Integer.parseInt(userId)); HashMap<String,
			 * String> keyValue = new HashMap<String, String>();
			 * keyValue.put("task_id", "1"); keyValue.put("comment_text",
			 * "Deneme"); crudTmp.setKeyValue(keyValue);
			 * crudTmp.setType(Crud.TYPE_INSERT); crudList.add(crudTmp);
			 */
			if (crudList != null && crudList.size() > 0) {
				for (Crud crud : crudList) {
					CrudOutputModel crudOutputModel = new CrudOutputModel();
					try {
						if (crud.getTableName().equals(TrackerComments.class.getSimpleName())
								&& crud.getType() == Crud.TYPE_INSERT) {
							addTrackerCommentRequest = new AddTrackerCommentRequest();
							addTrackerCommentResponse = new AddTrackerCommentResponse();
							Iterator<?> it = crud.getKeyValue().entrySet().iterator();
							while (it.hasNext()) {
								Map.Entry pairs = (Map.Entry) it.next();
								addTrackerCommentRequest.setUserId(role.getTrackerUser().getId());
								if (pairs.getKey().equals("task_id")) {
									addTrackerCommentRequest.setTaskId(Integer.parseInt((String) pairs.getValue()));
								} else if (pairs.getKey().equals("comment_text")) {
									addTrackerCommentRequest.setCommentText((String) pairs.getValue());
								}
							}
							this.addTrackerCommentDao.execute(addTrackerCommentRequest, addTrackerCommentResponse);
							crudOutputModel.setAffectedId(addTrackerCommentResponse.getCommentId());
							crudOutputModel.setReturnCode(TrackerConstants.OPERATION_SUCCEED);
							crudOutputModel.setCrudId(crud.getId());

						} else if (crud.getTableName().equals(TrackerTasks.class.getSimpleName())
								&& crud.getType() == Crud.TYPE_UPDATE) {

							updateTrackerTaskRequest = new UpdateTrackerTaskRequest();
							updateTrackerTaskResponse = new UpdateTrackerTaskResponse();

							Iterator<?> it = crud.getKeyValue().entrySet().iterator();
							while (it.hasNext()) {
								Map.Entry pairs = (Map.Entry) it.next();
								updateTrackerTaskRequest.setUserId(role.getTrackerUser().getId());
								updateTrackerTaskRequest.setTaskId(crud.getTableID());
								if (pairs.getKey().equals("resolution_reason")) {
									updateTrackerTaskRequest.setResolutionReason(Integer.parseInt((String) pairs.getValue()));
								} else if (pairs.getKey().equals("percent_complete")) {
									updateTrackerTaskRequest.setPercentComplete(Integer.parseInt((String) pairs.getValue()));
								} else if (pairs.getKey().equals("item_status")) {
									updateTrackerTaskRequest.setItemStatus(Integer.parseInt((String) pairs.getValue()));
								} else if (pairs.getKey().equals("closedby_version")) {
									updateTrackerTaskRequest.setClosedbyVersion(Integer.parseInt((String) pairs.getValue()));
								}
							}
							this.updateTrackerTaskDao.execute(updateTrackerTaskRequest, updateTrackerTaskResponse);

							crudOutputModel.setAffectedId(crud.getTableID());
							crudOutputModel.setReturnCode(TrackerConstants.OPERATION_SUCCEED);
							crudOutputModel.setCrudId(crud.getId());
							// Task kapatma istegi
						} else if (crud.getTableName().equals(TrackerAdminRequests.class.getSimpleName())
								&& crud.getType() == Crud.TYPE_INSERT) {
							addTrackerAdminRequestDaoImpl = new AddTrackerAdminRequestDaoImpl();
							addTrackerAdminRequestRequest = new AddTrackerAdminRequestRequest();
							addTrackerAdminRequestResponse = new AddTrackerAdminRequestResponse();
							Iterator<?> it = crud.getKeyValue().entrySet().iterator();
							while (it.hasNext()) {
								Map.Entry pairs = (Map.Entry) it.next();
								addTrackerAdminRequestRequest.setSubmittedBy(role.getTrackerUser().getId());
								if (pairs.getKey().equals("task_id")) {
									addTrackerAdminRequestRequest.setTaskId(Integer.parseInt((String) pairs.getValue()));
								} else if (pairs.getKey().equals("reason_given")) {
									addTrackerAdminRequestRequest.setReasonGiven((String) pairs.getValue());
								}
							}
							// Bu islem client tarafindan sadece kapatma icin
							// cagrilabilir
							addTrackerAdminRequestRequest.setRequestType(TrackerConstants.REQUEST_TYPE_CLOSE);
							addTrackerAdminRequestDaoImpl.execute(addTrackerAdminRequestRequest,
									addTrackerAdminRequestResponse);
							crudOutputModel.setAffectedId(addTrackerAdminRequestResponse.getAdminRequestId());
							crudOutputModel.setReturnCode(TrackerConstants.OPERATION_SUCCEED);
							crudOutputModel.setCrudId(crud.getId());

						}
					} catch (TrackerException e) {
						crudOutputModel.setReturnCode(e.getMessage());
						Trace.getInstance(getLoggerAppName()).error(Level.ERROR, e);

					} catch (Exception e) {
						crudOutputModel.setReturnCode(ErrorCodes.ERROR_GENERAL);
						Trace.getInstance(getLoggerAppName()).error(Level.ERROR, e);

					}

					returnList.add(crudOutputModel);

				}
			}

			serviceResponse.setResult(returnList);
			serviceResponse.setStatusSuccess(true);

		} catch (Exception e) {
			serviceResponse.setStatusSuccess(false);
			serviceResponse.setErrorCode(ErrorCodes.ERROR_GENERAL);
			Trace.getInstance(getLoggerAppName()).error(Level.ERROR, e);

		}

		serviceResponseShell.setServiceResponse(serviceResponse);
		return serviceResponseShell;
	}

	@Autowired
	public void setAddTrackerCommentDao(AbstractDAO addTrackerCommentDao) {
		this.addTrackerCommentDao = addTrackerCommentDao;
	}

	@Autowired
	public void setUpdateTrackerTaskDao(AbstractDAO updateTrackerTaskDao) {
		this.updateTrackerTaskDao = updateTrackerTaskDao;
	}

	@Autowired
	public void setLoggerName(String loggerName) {
		this.loggerName = loggerName;
	}

	public String getLoggerAppName() {
		return Trace.LOGGER_APP_PRE + this.loggerName;
	}

}
