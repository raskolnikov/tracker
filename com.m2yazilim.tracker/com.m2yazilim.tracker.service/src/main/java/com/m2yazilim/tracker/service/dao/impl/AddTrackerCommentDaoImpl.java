package com.m2yazilim.tracker.service.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.m2yazilim.tracker.dao.AbstractDAO;
import com.m2yazilim.tracker.dataaccess.factories.tracker.HibernateTrackerDaoFactory;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerCommentsDao;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerTasksDao;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerUsersDao;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerComments;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerTasks;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerUsers;
import com.m2yazilim.tracker.service.messages.AddTrackerCommentRequest;
import com.m2yazilim.tracker.service.messages.AddTrackerCommentResponse;

@Repository
public class AddTrackerCommentDaoImpl extends AbstractDAO<AddTrackerCommentRequest, AddTrackerCommentResponse> {

	@Override
	@Transactional
	public void execute(AddTrackerCommentRequest request, AddTrackerCommentResponse response) throws Exception {

		if (!validate(request, response)) {
			return;
		}

		TrackerUsersDao trackerUsersDao = HibernateTrackerDaoFactory.getTrackerUsersDao();
		TrackerUsers trackerUser = trackerUsersDao.get(request.getUserId());

		TrackerTasksDao trackerTasksDao = HibernateTrackerDaoFactory.getTrackerTasksDao();
		TrackerTasks trackerTask = trackerTasksDao.get(request.getTaskId());

		int dateAdded = (int) (System.currentTimeMillis() / 1000L);

		TrackerCommentsDao trackerCommentsDao = HibernateTrackerDaoFactory.getTrackerCommentsDao();
		TrackerComments trackerComment = new TrackerComments();

		trackerComment.setCommentText(request.getCommentText());
		trackerComment.setTask(trackerTask);
		trackerComment.setUser(trackerUser);
		trackerComment.setDateAdded(dateAdded);
		trackerComment.setLastEditedTime(dateAdded);
		trackerComment.setVersion(0);

		trackerCommentsDao.save(trackerComment);
		response.setCommentId(trackerComment.getId());

	}

	@Override
	public boolean validate(AddTrackerCommentRequest request, AddTrackerCommentResponse response) {

		return true;

	}

}
