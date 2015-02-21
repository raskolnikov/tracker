package com.m2yazilim.tracker.service.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.m2yazilim.tracker.dao.AbstractSyncDAO;
import com.m2yazilim.tracker.dataaccess.factories.tracker.HibernateTrackerDaoFactory;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerAssignedDao;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerAssignedOldDao;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerAssigned;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerAssignedOld;

@Repository
public class InquiryPushUserTrackerAssignedDaoImpl extends AbstractSyncDAO {

	private static final long serialVersionUID = 281363029151032499L;

	public static void execute(int rowId, ArrayList<Integer> affectedUserList) throws Exception {

		validate(rowId, affectedUserList);
		TrackerAssignedDao trackerAssignedDao = HibernateTrackerDaoFactory.getTrackerAssignedDao();
		TrackerAssigned trackerAssigned = trackerAssignedDao.get(rowId);

		affectedUserList.add(trackerAssigned.getUser().getId());

		// Taskin eski sahibinin de bilgilendirilmesi gerektiginden bu islem
		// yapiliyor
		TrackerAssignedOldDao trackerAssignedOldDao = HibernateTrackerDaoFactory.getTrackerAssignedOldDao();
		Criterion assignedCn = Restrictions.eq("assigned", trackerAssigned);

		List<TrackerAssignedOld> trackerAssignedOldList = trackerAssignedOldDao.findByCriteria(assignedCn);
		if (trackerAssignedOldList != null) {
			for (TrackerAssignedOld trackerAssignedOld : trackerAssignedOldList) {
				affectedUserList.add(trackerAssignedOld.getOldUser().getId());
			}
		}

	}

	public static void validate(int rowId, ArrayList<Integer> affectedUserList) throws Exception {

	}

}
