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
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerPrefsDao;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerUserMobileSettingDao;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerPrefs;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerUserMobileSetting;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerUsers;
import com.m2yazilim.tracker.dto.TrackerUserMobileSettingDto;

@Repository
public class InquiryTrackerUserMobileSettingDaoImpl extends AbstractSyncDAO {

	private static final long serialVersionUID = 281363029151032499L;

	public static void execute(Criterion inRowIdCn, TrackerUsers trackerUser, HashMap<String, List<?>> returnHashMap)
			throws Exception {

		validate(inRowIdCn, trackerUser, returnHashMap);

		// Get TrackerUserMobileSettings
		TrackerUserMobileSettingDao trackerUserMobileSettingDao = HibernateTrackerDaoFactory.getTrackerUserMobileSettingDao();
		List<TrackerUserMobileSetting> trackerUserMobileSettingList = trackerUserMobileSettingDao.findByCriteria(inRowIdCn);
		List<TrackerUserMobileSettingDto> trackerUserMobileSettingDtoList = new ArrayList<TrackerUserMobileSettingDto>();

		// Get remote image path
		TrackerPrefsDao trackerPrefsDao = HibernateTrackerDaoFactory.getTrackerPrefsDao();
		Criterion prefCn = Restrictions.eq("prefName", "attachment_folder");
		TrackerPrefs trackerPref = trackerPrefsDao.findByCriteria(prefCn).get(0);

		copyTrackerDbObjectToDto(trackerUserMobileSettingList, trackerUserMobileSettingDtoList, trackerPref);
		returnHashMap.put(TrackerUserMobileSettingDto.class.getSimpleName(), trackerUserMobileSettingDtoList);

	}

	public static void validate(Criterion inRowIdCn, TrackerUsers trackerUser, HashMap<String, List<?>> returnHashMap)
			throws Exception {

	}

	private static void copyTrackerDbObjectToDto(List<TrackerUserMobileSetting> trackerUserMobileSettingList,
			List<TrackerUserMobileSettingDto> trackerUserMobileSettingDtoList, TrackerPrefs trackerPref)
			throws Exception {

		for (TrackerUserMobileSetting trackerUserMobileSetting : trackerUserMobileSettingList) {
			TrackerUserMobileSettingDto trackerUserMobileSettingDto = new TrackerUserMobileSettingDto();
			BeanUtilsBean.getInstance().copyProperties(trackerUserMobileSettingDto, trackerUserMobileSetting);
			trackerUserMobileSettingDto.setRemoteAttachmentFolder(trackerPref.getPrefValue());
			trackerUserMobileSettingDto.setUserId(trackerUserMobileSetting.getUser().getId());
			trackerUserMobileSettingDto.setLocationGetTypeId(trackerUserMobileSetting.getLocationGetType().getId());
			trackerUserMobileSettingDtoList.add(trackerUserMobileSettingDto);
		}
	}
}
