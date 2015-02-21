package com.m2yazilim.tracker.proxy.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

import com.m2yazilim.tracker.dao.AbstractSyncDAO;
import com.m2yazilim.tracker.dataaccess.factories.tracker.HibernateTrackerDaoFactory;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerListTasktypeDao;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerListTasktype;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerUsers;
import com.m2yazilim.tracker.dto.TrackerListTasktypeDto;

@Repository
public class InquiryTrackerListTasktypeDaoImpl extends AbstractSyncDAO {

	private static final long serialVersionUID = 281363029151032499L;

	public static void execute(Criterion inRowIdCn, TrackerUsers trackerUser, HashMap<String, List<?>> returnHashMap)
			throws Exception {

		validate(inRowIdCn, trackerUser, returnHashMap);

		TrackerListTasktypeDao trackerListTasktypeDao = HibernateTrackerDaoFactory.getTrackerListTasktypeDao();
		List<TrackerListTasktype> trackerListTasktypeList = trackerListTasktypeDao.findByCriteria(inRowIdCn);
		List<TrackerListTasktypeDto> trackerListTasktypeDtoList = new ArrayList<TrackerListTasktypeDto>();
		copyTrackerDbObjectToDto(trackerListTasktypeList, trackerListTasktypeDtoList);
		returnHashMap.put(TrackerListTasktypeDto.class.getSimpleName(), trackerListTasktypeDtoList);

	}

	public static void validate(Criterion inRowIdCn, TrackerUsers trackerUser, HashMap<String, List<?>> returnHashMap)
			throws Exception {

	}

	private static void copyTrackerDbObjectToDto(List<TrackerListTasktype> trackerListTasktypeList,
			List<TrackerListTasktypeDto> trackerListTasktypeDtoList) throws Exception {
		for (TrackerListTasktype trackerListTasktype : trackerListTasktypeList) {
			TrackerListTasktypeDto trackerListTasktypeDto = new TrackerListTasktypeDto();
			BeanUtilsBean.getInstance().copyProperties(trackerListTasktypeDto, trackerListTasktype);
			trackerListTasktypeDto.setProjectId(trackerListTasktype.getProject().getId());
			trackerListTasktypeDto.setTasktypeId(trackerListTasktype.getId());
			trackerListTasktypeDtoList.add(trackerListTasktypeDto);
		}

	}

}
