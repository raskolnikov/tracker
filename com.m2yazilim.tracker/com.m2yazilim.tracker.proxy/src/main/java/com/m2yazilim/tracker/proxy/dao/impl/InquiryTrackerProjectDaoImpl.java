package com.m2yazilim.tracker.proxy.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

import com.m2yazilim.tracker.dao.AbstractSyncDAO;
import com.m2yazilim.tracker.dataaccess.factories.tracker.HibernateTrackerDaoFactory;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerProjectsDao;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerProjects;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerUsers;
import com.m2yazilim.tracker.dto.TrackerProjectsDto;

@Repository
public class InquiryTrackerProjectDaoImpl extends AbstractSyncDAO {

	private static final long serialVersionUID = 281363029151032499L;

	public static void execute(Criterion inRowIdCn, TrackerUsers trackerUser, HashMap<String, List<?>> returnHashMap)
			throws Exception {

		validate(inRowIdCn, trackerUser, returnHashMap);

		TrackerProjectsDao trackerProjectsDao = HibernateTrackerDaoFactory.getTrackerProjectsDao();
		List<TrackerProjects> trackerProjectsList = trackerProjectsDao.findByCriteria(inRowIdCn);
		List<TrackerProjectsDto> trackerProjectsDtoList = new ArrayList<TrackerProjectsDto>();
		copyTrackerDbObjectToDto(trackerProjectsList, trackerProjectsDtoList);
		returnHashMap.put(TrackerProjectsDto.class.getSimpleName(), trackerProjectsDtoList);

	}

	public static void validate(Criterion inRowIdCn, TrackerUsers trackerUser, HashMap<String, List<?>> returnHashMap)
			throws Exception {

	}

	private static void copyTrackerDbObjectToDto(List<TrackerProjects> trackerProjectsList,
			List<TrackerProjectsDto> trackerProjectsDtoList) throws Exception {

		for (TrackerProjects trackerProject : trackerProjectsList) {
			TrackerProjectsDto trackerProjectsDto = new TrackerProjectsDto();
			BeanUtilsBean.getInstance().copyProperties(trackerProjectsDto, trackerProject);
			trackerProjectsDto.setProjectId(trackerProject.getId());
			trackerProjectsDtoList.add(trackerProjectsDto);
		}

	}

}
