package com.m2yazilim.tracker.proxy.dao.impl;

import java.util.List;
import java.util.UUID;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.m2yazilim.tracker.constants.ErrorCodes;
import com.m2yazilim.tracker.dao.AbstractDAO;
import com.m2yazilim.tracker.dataaccess.factories.tracker.HibernateTrackerDaoFactory;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerUserSessionDao;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerUsersDao;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerUserSession;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerUsers;
import com.m2yazilim.tracker.model.UserLoginOutput;
import com.m2yazilim.tracker.proxy.helper.TrackerLoginHelper;
import com.m2yazilim.tracker.proxy.messages.TrackerUserLoginControlRequest;
import com.m2yazilim.tracker.proxy.messages.TrackerUserLoginControlResponse;
import com.m2yazilim.tracker.util.TrackerDateHelper;
import com.m2yazilim.tracker.util.TrackerException;

@Repository
public class TrackerUserLoginControlDaoImpl extends
		AbstractDAO<TrackerUserLoginControlRequest, TrackerUserLoginControlResponse> {

	@Override
	@Transactional
	public void execute(TrackerUserLoginControlRequest request, TrackerUserLoginControlResponse response)
			throws Exception {

		if (!validate(request, response)) {
			return;
		}

		UserLoginOutput userLoginOutput = null;
		TrackerUsersDao trackerUsersDao = HibernateTrackerDaoFactory.getTrackerUsersDao();
		TrackerUserSessionDao trackerUserSessionDao;
		Criterion userNameCn = Restrictions.eq("userName", request.getUserLoginInfo().getUserName());
		String userPassMd5 = TrackerLoginHelper.stringToMd5Hash(request.getUserLoginInfo().getUserPass());
		Criterion userPassCn = Restrictions.eq("userPass", userPassMd5);

		List<TrackerUsers> trackerUserList = trackerUsersDao.findByCriteria(userNameCn, userPassCn);
		// User found and matched!
		if (trackerUserList != null && trackerUserList.size() > 0) {
			TrackerUsers trackerUser = trackerUserList.get(0);
			trackerUserSessionDao = HibernateTrackerDaoFactory.getTrackerUserSessionDao();
			UUID uniqueKey = UUID.randomUUID();
			String token = uniqueKey.toString();
			long currentTimeMillis = (System.currentTimeMillis());// (new
			// Date()).getTime();
			int createDate = (int) (currentTimeMillis / 1000L);

			int expireDate = (int) (TrackerDateHelper.calculateExpireDate(currentTimeMillis,
					request.getTokenLiveTimeInSecond()) / 1000L);

			TrackerUserSession trackerUserSession = new TrackerUserSession();
			trackerUserSession.setCreateDate(createDate);
			trackerUserSession.setDeviceRegisterKey(request.getUserLoginInfo().getDeviceRegisterKey());
			trackerUserSession.setToken(token);
			trackerUserSession.setExpireDate(expireDate);
			trackerUserSession.setUser(trackerUser);
			trackerUserSessionDao.save(trackerUserSession);

			userLoginOutput = new UserLoginOutput();
			userLoginOutput.setToken(token);
			userLoginOutput.setUserId(trackerUser.getId());
			response.setUserLoginOutput(userLoginOutput);

		} else {
			// User not found
			throw new TrackerException(ErrorCodes.ERROR_INVALID_USER_OR_PASSWORD);

		}

	}

	@Override
	public boolean validate(TrackerUserLoginControlRequest request, TrackerUserLoginControlResponse response) {

		return true;

	}

}
