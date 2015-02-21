package com.m2yazilim.tracker.service.dao.impl;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.m2yazilim.tracker.constants.ErrorCodes;
import com.m2yazilim.tracker.dao.AbstractDAO;
import com.m2yazilim.tracker.dataaccess.factories.tracker.HibernateTrackerDaoFactory;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerAdminRequestsDao;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerTasksDao;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerUsersDao;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerAdminRequests;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerTasks;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerUsers;
import com.m2yazilim.tracker.service.messages.AddTrackerAdminRequestRequest;
import com.m2yazilim.tracker.service.messages.AddTrackerAdminRequestResponse;
import com.m2yazilim.tracker.util.TrackerException;

@Repository
public class AddTrackerAdminRequestDaoImpl extends
		AbstractDAO<AddTrackerAdminRequestRequest, AddTrackerAdminRequestResponse> {

	@Override
	@Transactional
	public void execute(AddTrackerAdminRequestRequest request, AddTrackerAdminRequestResponse response)
			throws Exception {

		if (!validate(request, response)) {
			return;
		}

		TrackerUsersDao trackerUsersDao = HibernateTrackerDaoFactory.getTrackerUsersDao();
		TrackerUsers trackerUser = trackerUsersDao.get(request.getSubmittedBy());

		TrackerTasksDao trackerTasksDao = HibernateTrackerDaoFactory.getTrackerTasksDao();
		TrackerTasks trackerTask = trackerTasksDao.get(request.getTaskId());

		int dateAdded = (int) (System.currentTimeMillis() / 1000L);

		TrackerAdminRequestsDao trackerAdminRequestsDao = HibernateTrackerDaoFactory.getTrackerAdminRequestsDao();
		TrackerAdminRequests trackerAdminRequest = new TrackerAdminRequests();

		trackerAdminRequest.setProject(trackerTask.getProject());
		trackerAdminRequest.setReasonGiven(request.getReasonGiven());
		trackerAdminRequest.setRequestType(request.getRequestType());
		trackerAdminRequest.setSubmittedBy(trackerUser);
		trackerAdminRequest.setResolvedBy(trackerUser);
		trackerAdminRequest.setTask(trackerTask);
		trackerAdminRequest.setTimeSubmitted(dateAdded);
		trackerAdminRequest.setTimeResolved(dateAdded);
		trackerAdminRequest.setVersion(0);

		trackerAdminRequestsDao.save(trackerAdminRequest);
		response.setAdminRequestId(trackerAdminRequest.getId());

	}

	@Override
	public boolean validate(AddTrackerAdminRequestRequest request, AddTrackerAdminRequestResponse response)
			throws TrackerException {

		// TODO: input and role validations

		TrackerTasksDao trackerTasksDao = HibernateTrackerDaoFactory.getTrackerTasksDao();
		TrackerTasks trackerTask = trackerTasksDao.get(request.getTaskId());
		Criterion crTask = Restrictions.eq("task", trackerTask);
		Criterion crTime = Restrictions.eq("timeResolved", 0);
		Criterion crType = Restrictions.eq("requestType", request.getRequestType());

		TrackerAdminRequestsDao trackerAdminRequestsDao = HibernateTrackerDaoFactory.getTrackerAdminRequestsDao();

		List<TrackerAdminRequests> trackerAdminRequestList = trackerAdminRequestsDao.findByCriteria(crTask, crTime,
				crType);
		if (trackerAdminRequestList != null && trackerAdminRequestList.size() > 0) {
			throw new TrackerException(ErrorCodes.ERROR_CLOSE_REQUEST_ALREADY_SENT);
		}

		return true;

	}
}
