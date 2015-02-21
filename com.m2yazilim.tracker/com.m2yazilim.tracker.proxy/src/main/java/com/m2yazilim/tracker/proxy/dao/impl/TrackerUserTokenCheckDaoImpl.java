package com.m2yazilim.tracker.proxy.dao.impl;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.m2yazilim.tracker.dao.AbstractDAO;
import com.m2yazilim.tracker.dataaccess.factories.tracker.HibernateTrackerDaoFactory;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerUserSessionDao;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerUsersDao;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerUserSession;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerUsers;
import com.m2yazilim.tracker.proxy.messages.TrackerUserTokenCheckRequest;
import com.m2yazilim.tracker.proxy.messages.TrackerUserTokenCheckResponse;
import com.m2yazilim.tracker.util.TrackerDateHelper;

@Repository
public class TrackerUserTokenCheckDaoImpl extends
		AbstractDAO<TrackerUserTokenCheckRequest, TrackerUserTokenCheckResponse> {

	@Override
	@Transactional
	public void execute(TrackerUserTokenCheckRequest request, TrackerUserTokenCheckResponse response) throws Exception {

		if (!validate(request, response)) {
			return;
		}

		int userId = Integer.parseInt(request.getUserId());
		TrackerUserSessionDao trackerUserSessionDao = HibernateTrackerDaoFactory.getTrackerUserSessionDao();
		TrackerUsersDao trackerUsersDao = HibernateTrackerDaoFactory.getTrackerUsersDao();
		TrackerUsers trackerUser = trackerUsersDao.get(userId);
		if (trackerUser != null && trackerUser.getAccountEnabled().equals(1)) {
			Criterion userCn = Restrictions.eq("user", trackerUser);
			Criterion tokenCn = Restrictions.eq("token", request.getToken());
			Criterion registerKeyCn = Restrictions.eq("deviceRegisterKey", request.getDeviceRegisterKey());

			int currentTimeMillis = (int) (System.currentTimeMillis() / 1000L);

			Criterion expireDateCn = Restrictions.ge("expireDate", currentTimeMillis);
			List<TrackerUserSession> trackerUserSessionList = trackerUserSessionDao.findByCriteria(userCn, tokenCn,
					registerKeyCn, expireDateCn);
			if (trackerUserSessionList != null && trackerUserSessionList.size() > 0) {
				response.setTrackerUser(trackerUser);
				response.setTokenValid(true);

				// make token expire date later
				TrackerUserSession trackerUserSession = trackerUserSessionList.get(0);
				int nextExprireDate = (int) (TrackerDateHelper.calculateExpireDate(System.currentTimeMillis(),
						Long.parseLong(request.getTokenLiveTimeInSecond())) / 1000L);

				trackerUserSession.setExpireDate(nextExprireDate);
				trackerUserSession.setUpdateDate(currentTimeMillis);
				trackerUserSessionDao.saveOrUpdate(trackerUserSession);

			}
		}

	}

	@Override
	public boolean validate(TrackerUserTokenCheckRequest request, TrackerUserTokenCheckResponse response) {

		return true;

	}

}
