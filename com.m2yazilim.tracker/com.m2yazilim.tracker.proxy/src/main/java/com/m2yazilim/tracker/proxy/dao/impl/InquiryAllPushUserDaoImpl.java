package com.m2yazilim.tracker.proxy.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.m2yazilim.tracker.dao.AbstractSyncDAO;
import com.m2yazilim.tracker.dataaccess.factories.tracker.HibernateTrackerDaoFactory;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerUsersDao;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerUsers;

@Repository
public class InquiryAllPushUserDaoImpl extends AbstractSyncDAO {

	private static final long serialVersionUID = 281363029151032499L;

	public static void execute(int rowId, ArrayList<Integer> affectedUserList) throws Exception {
		validate(rowId, affectedUserList);

		Criterion criterion = Restrictions.eq("accountEnabled", 1);
		TrackerUsersDao trackerUsersDao = HibernateTrackerDaoFactory.getTrackerUsersDao();

		List<TrackerUsers> trackerUserList = trackerUsersDao.findByCriteria(criterion);
		if (trackerUserList != null && trackerUserList.size() > 0) {

			for (TrackerUsers trackerUser : trackerUserList) {
				affectedUserList.add(trackerUser.getId());
			}
		}

	}

	public static void validate(int rowId, ArrayList<Integer> affectedUserList) throws Exception {

	}

}
