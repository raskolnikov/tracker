package com.m2yazilim.tracker.service.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.m2yazilim.tracker.dao.AbstractSyncDAO;
import com.m2yazilim.tracker.dataaccess.factories.tracker.HibernateTrackerDaoFactory;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerAssignedDao;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerCommentsDao;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerAssigned;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerComments;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerTasks;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerUsers;
import com.m2yazilim.tracker.dto.TrackerAssignedDto;
import com.m2yazilim.tracker.service.helper.TrackerSyncHelper;

@Repository
public class InquiryTrackerAssignedDaoImpl extends AbstractSyncDAO {

	private static final long serialVersionUID = 281363029151032499L;

	public static void execute(Criterion inRowIdCn, TrackerUsers trackerUser, HashMap<String, List<?>> returnHashMap)
			throws Exception {

		validate(inRowIdCn, trackerUser, returnHashMap);

		Criterion eqUserCn = Restrictions.eq("user", trackerUser);
		TrackerAssignedDao trackerAssignedDao = HibernateTrackerDaoFactory.getTrackerAssignedDao();
		List<TrackerAssigned> trackerAssignedList = trackerAssignedDao.findByCriteria(inRowIdCn, eqUserCn);
		List<TrackerAssignedDto> trackerAssignedDtoList = new ArrayList<TrackerAssignedDto>();
		copyTrackerDbObjectToDto(trackerAssignedList, trackerAssignedDtoList);
		returnHashMap.put(TrackerAssignedDto.class.getSimpleName(), trackerAssignedDtoList);

	}

	public static void fetch(Criterion inRowIdCn, TrackerUsers trackerUser, List<TrackerTasks> trackerTasksList,
			HashMap<String, ArrayList<String>> lightTableVersionList) throws Exception {

		validate(inRowIdCn, trackerUser, trackerTasksList, lightTableVersionList);

		TrackerCommentsDao trackerCommentsDao = HibernateTrackerDaoFactory.getTrackerCommentsDao();
		// Get Comment
		Criterion inTaskCn = Restrictions.in("task", trackerTasksList);
		List<TrackerComments> trackerCommentList = trackerCommentsDao.findByCriteria(inTaskCn);
		TrackerSyncHelper.generateTableRowVersion(TrackerComments.class.getSimpleName(), lightTableVersionList,
				(ArrayList<?>) trackerCommentList);

	}

	private static void validate(Criterion inRowIdCn, TrackerUsers trackerUser, List<TrackerTasks> trackerTasksList,
			HashMap<String, ArrayList<String>> lightTableVersionList) {
		// TODO Auto-generated method stub

	}

	public static void validate(Criterion inRowIdCn, TrackerUsers trackerUser, HashMap<String, List<?>> returnHashMap)
			throws Exception {

	}

	private static void copyTrackerDbObjectToDto(List<TrackerAssigned> trackerAssignedList,
			List<TrackerAssignedDto> trackerAssignedDtoList) throws Exception {
		for (TrackerAssigned trackerAssigned : trackerAssignedList) {

			TrackerAssignedDto trackerAssignedDto = new TrackerAssignedDto();
			BeanUtilsBean.getInstance().copyProperties(trackerAssignedDto, trackerAssigned);
			trackerAssignedDto.setTaskId(trackerAssigned.getTask().getId());
			trackerAssignedDto.setUserId(trackerAssigned.getUser().getId());
			trackerAssignedDto.setAssignedId(trackerAssigned.getId());
			trackerAssignedDtoList.add(trackerAssignedDto);

		}

	}

}
