package com.m2yazilim.tracker.service.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

import com.m2yazilim.tracker.dao.AbstractSyncDAO;
import com.m2yazilim.tracker.dataaccess.factories.tracker.HibernateTrackerDaoFactory;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerHistoryDao;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerHistory;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerUsers;
import com.m2yazilim.tracker.dto.TrackerHistoryDto;

@Repository
public class InquiryTrackerHistoryDaoImpl extends AbstractSyncDAO {

	private static final long serialVersionUID = 281363029151032499L;

	public static void execute(Criterion inRowIdCn, TrackerUsers trackerUser, HashMap<String, List<?>> returnHashMap)
			throws Exception {

		validate(inRowIdCn, trackerUser, returnHashMap);

		TrackerHistoryDao trackerHistoryDao = HibernateTrackerDaoFactory.getTrackerHistoryDao();
		List<TrackerHistory> trackerHistoryList = trackerHistoryDao.findByCriteria(inRowIdCn);
		List<TrackerHistoryDto> trackerHistoryDtoList = new ArrayList<TrackerHistoryDto>();
		copyTrackerDbObjectToDto(trackerHistoryList, trackerHistoryDtoList);
		returnHashMap.put(TrackerHistoryDto.class.getSimpleName(), trackerHistoryDtoList);

	}

	public static void validate(Criterion inRowIdCn, TrackerUsers trackerUser, HashMap<String, List<?>> returnHashMap)
			throws Exception {

	}

	private static void copyTrackerDbObjectToDto(List<TrackerHistory> trackerHistoryList,
			List<TrackerHistoryDto> trackerHistoryDtoList) throws Exception {

		for (TrackerHistory trackerHistory : trackerHistoryList) {
			TrackerHistoryDto trackerHistoryDto = new TrackerHistoryDto();
			BeanUtilsBean.getInstance().copyProperties(trackerHistoryDto, trackerHistory);
			trackerHistoryDto.setTaskId(trackerHistory.getTask().getId());
			trackerHistoryDto.setUserId(trackerHistory.getUser().getId());
			trackerHistoryDto.setHistoryId(trackerHistory.getId());
			trackerHistoryDtoList.add(trackerHistoryDto);
		}
	}

}
