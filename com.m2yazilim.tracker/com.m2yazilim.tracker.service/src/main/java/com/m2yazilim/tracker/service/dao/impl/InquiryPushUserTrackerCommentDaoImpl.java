package com.m2yazilim.tracker.service.dao.impl;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.m2yazilim.tracker.dao.AbstractSyncDAO;
import com.m2yazilim.tracker.dataaccess.factories.tracker.HibernateTrackerDaoFactory;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerCommentsDao;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerComments;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerTasks;
import com.m2yazilim.tracker.service.helper.TrackerPushHelper;

@Repository
public class InquiryPushUserTrackerCommentDaoImpl extends AbstractSyncDAO {

	private static final long serialVersionUID = 281363029151032499L;

	public static void execute(int rowId, ArrayList<Integer> affectedUserList) throws Exception {

		validate(rowId, affectedUserList);

		TrackerCommentsDao trackerCommentsDao = HibernateTrackerDaoFactory.getTrackerCommentsDao();
		TrackerComments trackerComment = trackerCommentsDao.get(rowId);
		TrackerTasks trackerTask = trackerComment.getTask();
		TrackerPushHelper.findAssignedUserList(affectedUserList, trackerTask);

	}

	public static void validate(int rowId, ArrayList<Integer> affectedUserList) throws Exception {

	}

}
