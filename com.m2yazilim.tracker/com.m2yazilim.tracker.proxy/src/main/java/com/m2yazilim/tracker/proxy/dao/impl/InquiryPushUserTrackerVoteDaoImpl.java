package com.m2yazilim.tracker.proxy.dao.impl;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.m2yazilim.tracker.dao.AbstractSyncDAO;
import com.m2yazilim.tracker.dataaccess.factories.tracker.HibernateTrackerDaoFactory;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerVotesDao;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerTasks;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerVotes;
import com.m2yazilim.tracker.proxy.helper.TrackerPushHelper;

@Repository
public class InquiryPushUserTrackerVoteDaoImpl extends AbstractSyncDAO {

	private static final long serialVersionUID = 281363029151032499L;

	public static void execute(int rowId, ArrayList<Integer> affectedUserList) throws Exception {

		validate(rowId, affectedUserList);

		TrackerVotesDao trackerVotesDao = HibernateTrackerDaoFactory.getTrackerVotesDao();
		TrackerVotes trackerVote = trackerVotesDao.get(rowId);
		TrackerTasks trackerTask = trackerVote.getTask();

		TrackerPushHelper.findAssignedUserList(affectedUserList, trackerTask);

	}

	public static void validate(int rowId, ArrayList<Integer> affectedUserList) throws Exception {

	}

}
