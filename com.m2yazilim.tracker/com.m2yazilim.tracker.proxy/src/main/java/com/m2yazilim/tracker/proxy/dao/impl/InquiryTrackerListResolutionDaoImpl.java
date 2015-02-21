package com.m2yazilim.tracker.proxy.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

import com.m2yazilim.tracker.dao.AbstractSyncDAO;
import com.m2yazilim.tracker.dataaccess.factories.tracker.HibernateTrackerDaoFactory;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerListResolutionDao;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerListResolution;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerUsers;
import com.m2yazilim.tracker.dto.TrackerListResolutionDto;

@Repository
public class InquiryTrackerListResolutionDaoImpl extends AbstractSyncDAO {

	private static final long serialVersionUID = 281363029151032499L;

	public static void execute(Criterion inRowIdCn, TrackerUsers trackerUser, HashMap<String, List<?>> returnHashMap)
			throws Exception {

		validate(inRowIdCn, trackerUser, returnHashMap);

		TrackerListResolutionDao trackerListResolutionDao = HibernateTrackerDaoFactory.getTrackerListResolutionDao();
		List<TrackerListResolution> trackerListResolutionList = trackerListResolutionDao.findByCriteria(inRowIdCn);
		List<TrackerListResolutionDto> trackerListResolutionDtoList = new ArrayList<TrackerListResolutionDto>();
		copyTrackerDbObjectToDto(trackerListResolutionList, trackerListResolutionDtoList);
		returnHashMap.put(TrackerListResolutionDto.class.getSimpleName(), trackerListResolutionDtoList);

	}

	public static void validate(Criterion inRowIdCn, TrackerUsers trackerUser, HashMap<String, List<?>> returnHashMap)
			throws Exception {

	}

	private static void copyTrackerDbObjectToDto(List<TrackerListResolution> trackerListResolutionList,
			List<TrackerListResolutionDto> trackerListResolutionDtoList) throws Exception {

		for (TrackerListResolution trackerListResolution : trackerListResolutionList) {
			TrackerListResolutionDto trackerListResolutionDto = new TrackerListResolutionDto();
			BeanUtilsBean.getInstance().copyProperties(trackerListResolutionDto, trackerListResolution);
			trackerListResolutionDto.setProjectId(trackerListResolution.getProject().getId());
			trackerListResolutionDto.setResolutionId(trackerListResolution.getId());

			trackerListResolutionDtoList.add(trackerListResolutionDto);
		}

	}

}
