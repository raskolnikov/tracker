package com.m2yazilim.tracker.proxy.dao.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.fileupload.FileItem;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.m2yazilim.tracker.constants.TrackerConstants;
import com.m2yazilim.tracker.dao.AbstractDAO;
import com.m2yazilim.tracker.dataaccess.factories.tracker.HibernateTrackerDaoFactory;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerAttachmentsDao;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerCommentsDao;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerTasksDao;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerUsersDao;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerAttachments;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerComments;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerTasks;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerUsers;
import com.m2yazilim.tracker.model.AddAttachmentModel;
import com.m2yazilim.tracker.model.Crud;
import com.m2yazilim.tracker.model.CrudOutputModel;
import com.m2yazilim.tracker.proxy.messages.AddTrackerAttachmentRequest;
import com.m2yazilim.tracker.proxy.messages.AddTrackerAttachmentResponse;

@Repository
public class AddTrackerAttachmentDaoImpl extends AbstractDAO<AddTrackerAttachmentRequest, AddTrackerAttachmentResponse> {

	@Override
	@Transactional
	public void execute(AddTrackerAttachmentRequest request, AddTrackerAttachmentResponse response) throws Exception {

		List<AddAttachmentModel> addAttachmentModelList;

		if (!validate(request, response)) {
			return;
		}
		List<CrudOutputModel> crudOutputModelList = new ArrayList<CrudOutputModel>();
		addAttachmentModelList = request.getAddAttachmentModelList();
		TrackerTasksDao trackerTasksDao = HibernateTrackerDaoFactory.getTrackerTasksDao();
		TrackerUsersDao trackerUsersDao = HibernateTrackerDaoFactory.getTrackerUsersDao();
		TrackerUsers trackerUser = trackerUsersDao.get(request.getUserId());
		TrackerTasks trackerTask = null;
		Integer taskId = null;
		String commentText = null;

		TrackerCommentsDao trackerCommentsDao = HibernateTrackerDaoFactory.getTrackerCommentsDao();
		TrackerComments trackerComment = null;

		if (request.getCommentCrud() != null) {
			Iterator<?> it = request.getCommentCrud().getKeyValue().entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pairs = (Map.Entry) it.next();

				if (pairs.getKey().equals("task_id")) {
					taskId = Integer.parseInt((String) pairs.getValue());
				} else if (pairs.getKey().equals("comment_text")) {
					commentText = (String) pairs.getValue();
				}
			}

			trackerTask = trackerTasksDao.get(taskId);

			Integer dateAdded = (int) System.currentTimeMillis();
			trackerComment = new TrackerComments();
			trackerComment.setCommentText(commentText);
			trackerComment.setTask(trackerTask);
			trackerComment.setUser(trackerUser);
			trackerComment.setDateAdded(dateAdded);
			trackerComment.setLastEditedTime(dateAdded);
			trackerComment.setVersion(0);

			trackerCommentsDao.save(trackerComment);

			CrudOutputModel crudOutputModel = new CrudOutputModel();
			crudOutputModel.setAffectedId(trackerComment.getId());
			crudOutputModel.setCrudId(request.getCommentCrud().getId());
			crudOutputModel.setReturnCode(TrackerConstants.OPERATION_SUCCEED);
			crudOutputModelList.add(crudOutputModel);

		} else {
			trackerComment = trackerCommentsDao.get(0);
		}

		for (AddAttachmentModel addAttachmentModel : addAttachmentModelList) {
			Crud attachmentCrud = addAttachmentModel.getAttachmentCrud();
			String origName = null;
			Iterator<?> it = attachmentCrud.getKeyValue().entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pairs = (Map.Entry) it.next();
				if (pairs.getKey().equals("orig_name")) {
					origName = (String) pairs.getValue();
				} else if (pairs.getKey().equals("task_id")) {
					taskId = Integer.parseInt((String) pairs.getValue());
				}

			}

			if (request.getCommentCrud() == null) {
				trackerTask = trackerTasksDao.get(taskId);
			}

			FileItem fileItem = addAttachmentModel.getFileItem();
			int fileSize = (int) fileItem.getSize();

			UUID uniqueKey = UUID.randomUUID();
			String uniqueFileName = uniqueKey.toString();

			int dateAdded = (int) (System.currentTimeMillis() / 1000L);
			TrackerAttachmentsDao trackerAttachmentsDao = HibernateTrackerDaoFactory.getTrackerAttachmentsDao();
			TrackerAttachments trackerAttachment = new TrackerAttachments();
			trackerAttachment.setAddedBy(trackerUser);
			trackerAttachment.setComment(trackerComment);
			trackerAttachment.setDateAdded(dateAdded);
			trackerAttachment.setFileName(uniqueFileName);
			trackerAttachment.setFileSize(fileSize);
			trackerAttachment.setFileType(fileItem.getContentType());
			trackerAttachment.setOrigName(origName);
			trackerAttachment.setTask(trackerTask);
			trackerAttachment.setVersion(0);
			trackerAttachmentsDao.save(trackerAttachment);

			// write file to disk

			File someFile = new File(request.getFileUploadLocation() + "/" + uniqueFileName);
			if (!someFile.exists()) {
				someFile.createNewFile();
			}
			OutputStream out = new FileOutputStream(someFile);

			int read = 0;
			byte[] bytes = new byte[1024];
			InputStream inputStream = fileItem.getInputStream();
			while ((read = inputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();

			CrudOutputModel crudOutputModel = new CrudOutputModel();
			crudOutputModel.setAffectedId(trackerAttachment.getId());
			crudOutputModel.setCrudId(attachmentCrud.getId());
			crudOutputModel.setReturnCode(TrackerConstants.OPERATION_SUCCEED);
			crudOutputModelList.add(crudOutputModel);

		}

		response.setCrudOutputModelList(crudOutputModelList);

	}

	@Override
	public boolean validate(AddTrackerAttachmentRequest request, AddTrackerAttachmentResponse response) {

		return true;

	}

}
