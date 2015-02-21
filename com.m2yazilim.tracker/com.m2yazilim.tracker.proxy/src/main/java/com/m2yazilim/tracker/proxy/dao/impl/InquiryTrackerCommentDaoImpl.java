package com.m2yazilim.tracker.proxy.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

import com.m2yazilim.tracker.dao.AbstractSyncDAO;
import com.m2yazilim.tracker.dataaccess.factories.tracker.HibernateTrackerDaoFactory;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerCommentsDao;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerComments;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerUsers;
import com.m2yazilim.tracker.dto.TrackerCommentsDto;

@Repository
public class InquiryTrackerCommentDaoImpl extends AbstractSyncDAO {

	private static final long serialVersionUID = 281363029151032499L;

	public static void execute(Criterion inRowIdCn, TrackerUsers trackerUser, HashMap<String, List<?>> returnHashMap)
			throws Exception {

		validate(inRowIdCn, trackerUser, returnHashMap);

		TrackerCommentsDao trackerCommentsDao = HibernateTrackerDaoFactory.getTrackerCommentsDao();
		List<TrackerComments> trackerCommentList = trackerCommentsDao.findByCriteria(inRowIdCn);
		List<TrackerCommentsDto> trackerCommentDtoList = new ArrayList<TrackerCommentsDto>();
		copyTrackerDbObjectToDto(trackerCommentList, trackerCommentDtoList);
		returnHashMap.put(TrackerCommentsDto.class.getSimpleName(), trackerCommentDtoList);

	}

	public static void validate(Criterion inRowIdCn, TrackerUsers trackerUser, HashMap<String, List<?>> returnHashMap)
			throws Exception {

	}

	private static void copyTrackerDbObjectToDto(List<TrackerComments> trackerCommentList,
			List<TrackerCommentsDto> trackerCommentDtoList) throws Exception {

		for (TrackerComments trackerComment : trackerCommentList) {
			TrackerCommentsDto trackerCommentsDto = new TrackerCommentsDto();
			BeanUtilsBean.getInstance().copyProperties(trackerCommentsDto, trackerComment);
			trackerCommentsDto.setUserId(trackerComment.getUser().getId());
			trackerCommentsDto.setTaskId(trackerComment.getTask().getId());
			trackerCommentsDto.setCommentId(trackerComment.getId());
			trackerCommentDtoList.add(trackerCommentsDto);
		}

	}

}
