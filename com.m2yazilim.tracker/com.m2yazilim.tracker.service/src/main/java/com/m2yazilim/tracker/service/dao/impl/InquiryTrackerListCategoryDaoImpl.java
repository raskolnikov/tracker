package com.m2yazilim.tracker.service.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

import com.m2yazilim.tracker.dao.AbstractSyncDAO;
import com.m2yazilim.tracker.dataaccess.factories.tracker.HibernateTrackerDaoFactory;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerListCategoryDao;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerListCategory;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerUsers;
import com.m2yazilim.tracker.dto.TrackerListCategoryDto;

@Repository
public class InquiryTrackerListCategoryDaoImpl extends AbstractSyncDAO {

	private static final long serialVersionUID = 281363029151032499L;

	public static void execute(Criterion inRowIdCn, TrackerUsers trackerUser, HashMap<String, List<?>> returnHashMap)
			throws Exception {

		validate(inRowIdCn, trackerUser, returnHashMap);

		TrackerListCategoryDao trackerListCategoryDao = HibernateTrackerDaoFactory.getTrackerListCategoryDao();
		List<TrackerListCategory> trackerListCategoryList = trackerListCategoryDao.findByCriteria(inRowIdCn);
		List<TrackerListCategoryDto> trackerListCategoryDtoList = new ArrayList<TrackerListCategoryDto>();
		copyTrackerDbObjectToDto(trackerListCategoryList, trackerListCategoryDtoList);
		returnHashMap.put(TrackerListCategoryDto.class.getSimpleName(), trackerListCategoryDtoList);

	}

	public static void validate(Criterion inRowIdCn, TrackerUsers trackerUser, HashMap<String, List<?>> returnHashMap)
			throws Exception {

	}

	private static void copyTrackerDbObjectToDto(List<TrackerListCategory> trackerListCategoryList,
			List<TrackerListCategoryDto> trackerListCategoryDtoList) throws Exception {
		for (TrackerListCategory trackerListCategory : trackerListCategoryList) {
			TrackerListCategoryDto trackerListCategoryDto = new TrackerListCategoryDto();
			BeanUtilsBean.getInstance().copyProperties(trackerListCategoryDto, trackerListCategory);
			trackerListCategoryDto.setProjectId(trackerListCategory.getProject().getId());
			trackerListCategoryDto.setCategoryId(trackerListCategory.getId());
			trackerListCategoryDtoList.add(trackerListCategoryDto);

		}

	}

}
