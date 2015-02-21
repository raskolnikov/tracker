package com.m2yazilim.tracker.service.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

import com.m2yazilim.tracker.dao.AbstractSyncDAO;
import com.m2yazilim.tracker.dataaccess.factories.tracker.HibernateTrackerDaoFactory;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerLocationGetTypeDao;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerLocationGetType;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerUsers;
import com.m2yazilim.tracker.dto.TrackerLocationGetTypeDto;

@Repository
public class InquiryTrackerLocationGetTypeDaoImpl extends AbstractSyncDAO {

	private static final long serialVersionUID = 281363029151032499L;

	public static void execute(Criterion inRowIdCn, TrackerUsers trackerUser, HashMap<String, List<?>> returnHashMap)
			throws Exception {

		validate(inRowIdCn, trackerUser, returnHashMap);

		TrackerLocationGetTypeDao trackerLocationGetTypeDao = HibernateTrackerDaoFactory.getTrackerLocationGetTypeDao();
		List<TrackerLocationGetType> trackerLocationGetTypeList = trackerLocationGetTypeDao.findByCriteria(inRowIdCn);
		List<TrackerLocationGetTypeDto> trackerLocationGetTypeDtoList = new ArrayList<TrackerLocationGetTypeDto>();
		copyTrackerDbObjectToDto(trackerLocationGetTypeList, trackerLocationGetTypeDtoList);
		returnHashMap.put(TrackerLocationGetTypeDto.class.getSimpleName(), trackerLocationGetTypeDtoList);

	}

	public static void validate(Criterion inRowIdCn, TrackerUsers trackerUser, HashMap<String, List<?>> returnHashMap)
			throws Exception {

	}

	private static void copyTrackerDbObjectToDto(List<TrackerLocationGetType> trackerLocationGetTypeList,
			List<TrackerLocationGetTypeDto> trackerLocationGetTypeDtoList) throws Exception {

		for (TrackerLocationGetType trackerLocationGetType : trackerLocationGetTypeList) {
			TrackerLocationGetTypeDto trackerLocationGetTypeDto = new TrackerLocationGetTypeDto();
			BeanUtilsBean.getInstance().copyProperties(trackerLocationGetTypeDto, trackerLocationGetType);
			trackerLocationGetTypeDtoList.add(trackerLocationGetTypeDto);
		}
	}

}
