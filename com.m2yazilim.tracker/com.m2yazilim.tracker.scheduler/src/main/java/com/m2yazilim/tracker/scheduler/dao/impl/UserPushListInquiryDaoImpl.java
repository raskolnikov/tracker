package com.m2yazilim.tracker.scheduler.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.m2yazilim.tracker.dao.AbstractDAO;
import com.m2yazilim.tracker.dataaccess.factories.tracker.HibernateTrackerDaoFactory;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerTableChangeLogDao;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerTableChangeLog;
import com.m2yazilim.tracker.scheduler.messages.UserPushListInquiryRequest;
import com.m2yazilim.tracker.scheduler.messages.UserPushListInquiryResponse;

@Transactional
@Repository
public class UserPushListInquiryDaoImpl extends AbstractDAO<UserPushListInquiryRequest, UserPushListInquiryResponse> {

	@Override
	@Transactional
	public void execute(UserPushListInquiryRequest request, UserPushListInquiryResponse response) throws Exception {

		if (!validate(request, response)) {
			return;
		}
		TrackerTableChangeLogDao trackerTableChangeLogDao = HibernateTrackerDaoFactory.getTrackerTableChangeLogDao();
		Criterion isSentCn = Restrictions.eq("isSent", 0);
		List<TrackerTableChangeLog> trackerTableChangeLogList = trackerTableChangeLogDao.findByCriteria(isSentCn);
		if (trackerTableChangeLogList == null)
			trackerTableChangeLogList = new ArrayList<TrackerTableChangeLog>();
		response.setTrackerTableChangeLogList(trackerTableChangeLogList);
	}

	@Override
	public boolean validate(UserPushListInquiryRequest request, UserPushListInquiryResponse response) {

		return true;

	}

}
