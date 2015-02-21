package com.m2yazilim.tracker.service.dao.impl;

import org.springframework.transaction.annotation.Transactional;

import com.m2yazilim.tracker.dao.AbstractDAO;
import com.m2yazilim.tracker.dataaccess.factories.tracker.HibernateTrackerDaoFactory;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerListResolutionDao;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerListStatusDao;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerTasksDao;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerListResolution;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerListStatus;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerTasks;
import com.m2yazilim.tracker.service.messages.UpdateTrackerTaskRequest;
import com.m2yazilim.tracker.service.messages.UpdateTrackerTaskResponse;

public class UpdateTrackerTaskDaoImpl extends AbstractDAO<UpdateTrackerTaskRequest, UpdateTrackerTaskResponse> {

	@Override
	@Transactional
	public void execute(UpdateTrackerTaskRequest request, UpdateTrackerTaskResponse response) {

		if (!validate(request, response)) {
			return;
		}

		TrackerTasksDao trackerTasksDao = HibernateTrackerDaoFactory.getTrackerTasksDao();
		TrackerListStatusDao trackerListStatusDao = HibernateTrackerDaoFactory.getTrackerListStatusDao();
		TrackerListResolutionDao trackerListResolutionDao = HibernateTrackerDaoFactory.getTrackerListResolutionDao();

		TrackerTasks trackerTask = trackerTasksDao.get(request.getTaskId());
		TrackerListStatus trackerListStatus = trackerListStatusDao.get(request.getItemStatus());
		TrackerListResolution resolutionReason = trackerListResolutionDao.get(request.getResolutionReason());

		trackerTask.setItemStatus(trackerListStatus);
		trackerTask.setPercentComplete(request.getPercentComplete());
		trackerTask.setResolutionReason(resolutionReason);

		trackerTasksDao.saveOrUpdate(trackerTask);

	}

	@Override
	public boolean validate(UpdateTrackerTaskRequest request, UpdateTrackerTaskResponse response) {

		return true;

	}
}
