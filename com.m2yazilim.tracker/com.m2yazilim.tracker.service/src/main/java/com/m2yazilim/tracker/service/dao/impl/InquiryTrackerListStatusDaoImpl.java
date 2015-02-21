package com.m2yazilim.tracker.service.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

import com.m2yazilim.tracker.dao.AbstractSyncDAO;
import com.m2yazilim.tracker.dataaccess.factories.tracker.HibernateTrackerDaoFactory;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerListStatusDao;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerListStatus;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerUsers;
import com.m2yazilim.tracker.dto.TrackerListStatusDto;

@Repository
public class InquiryTrackerListStatusDaoImpl extends AbstractSyncDAO {

	private static final long serialVersionUID = 281363029151032499L;

	public static void execute(Criterion inRowIdCn, TrackerUsers trackerUser, HashMap<String, List<?>> returnHashMap)
			throws Exception {

		validate(inRowIdCn, trackerUser, returnHashMap);

		TrackerListStatusDao trackerListStatusDao = HibernateTrackerDaoFactory.getTrackerListStatusDao();
		List<TrackerListStatus> trackerListStatusList = trackerListStatusDao.findByCriteria(inRowIdCn);
		List<TrackerListStatusDto> trackerListStatusDtoList = new ArrayList<TrackerListStatusDto>();
		copyTrackerDbObjectToDto(trackerListStatusList, trackerListStatusDtoList);
		returnHashMap.put(TrackerListStatusDto.class.getSimpleName(), trackerListStatusDtoList);

	}

	public static void validate(Criterion inRowIdCn, TrackerUsers trackerUser, HashMap<String, List<?>> returnHashMap)
			throws Exception {

	}

	private static void copyTrackerDbObjectToDto(List<TrackerListStatus> trackerListStatusList,
			List<TrackerListStatusDto> trackerListStatusDtoList) throws Exception {
		for (TrackerListStatus trackerListStatus : trackerListStatusList) {
			TrackerListStatusDto trackerListStatusDto = new TrackerListStatusDto();
			BeanUtilsBean.getInstance().copyProperties(trackerListStatusDto, trackerListStatus);
			trackerListStatusDto.setProjectId(trackerListStatus.getProject().getId());
			trackerListStatusDto.setStatusId(trackerListStatus.getId());
			trackerListStatusDtoList.add(trackerListStatusDto);

		}

	}

}
