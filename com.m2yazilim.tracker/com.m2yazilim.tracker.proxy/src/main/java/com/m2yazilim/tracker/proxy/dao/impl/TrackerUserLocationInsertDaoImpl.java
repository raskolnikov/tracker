package com.m2yazilim.tracker.proxy.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.m2yazilim.tracker.dao.AbstractDAO;
import com.m2yazilim.tracker.dataaccess.factories.tracker.HibernateTrackerDaoFactory;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerLocationGetTypeDao;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerUserLocationLogDao;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerUsersDao;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerLocationGetType;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerUserLocationLog;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerUsers;
import com.m2yazilim.tracker.proxy.messages.TrackerUserLocationInsertRequest;
import com.m2yazilim.tracker.proxy.messages.TrackerUserLocationInsertResponse;

@Repository
public class TrackerUserLocationInsertDaoImpl extends
		AbstractDAO<TrackerUserLocationInsertRequest, TrackerUserLocationInsertResponse> {

	@Override
	@Transactional
	public void execute(TrackerUserLocationInsertRequest request, TrackerUserLocationInsertResponse response)
			throws Exception {

		if (!validate(request, response)) {
			return;
		}

		TrackerLocationGetTypeDao trackerLocationGetTypeDao = HibernateTrackerDaoFactory.getTrackerLocationGetTypeDao();
		TrackerLocationGetType trackerLocationGetType = trackerLocationGetTypeDao.get(request.getLocationGetType());
		TrackerUserLocationLogDao trackerUserLocationLogDao = HibernateTrackerDaoFactory.getTrackerUserLocationLogDao();
		TrackerUserLocationLog trackerUserLocationLog = new TrackerUserLocationLog();
		TrackerUsersDao trackerUsersDao = HibernateTrackerDaoFactory.getTrackerUsersDao();
		TrackerUsers trackerUser = trackerUsersDao.get(request.getUserId());

		int createDate = (int) (System.currentTimeMillis() / 1000L);

		trackerUserLocationLog.setCreateDate(createDate);
		trackerUserLocationLog.setDeviationShare(request.getDeviationShare());
		trackerUserLocationLog.setLatitude(request.getLatitude());
		trackerUserLocationLog.setLocationGetType(trackerLocationGetType);
		trackerUserLocationLog.setLongitude(request.getLongitude());
		trackerUserLocationLog.setUser(trackerUser);

		trackerUserLocationLogDao.save(trackerUserLocationLog);

	}

	@Override
	public boolean validate(TrackerUserLocationInsertRequest request, TrackerUserLocationInsertResponse response) {

		return true;

	}

}
