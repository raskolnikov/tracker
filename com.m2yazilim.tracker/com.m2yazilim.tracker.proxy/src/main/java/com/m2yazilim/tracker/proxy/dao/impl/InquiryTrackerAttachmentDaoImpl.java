package com.m2yazilim.tracker.proxy.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

import com.m2yazilim.tracker.dao.AbstractSyncDAO;
import com.m2yazilim.tracker.dataaccess.factories.tracker.HibernateTrackerDaoFactory;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerAttachmentsDao;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerAttachments;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerUsers;
import com.m2yazilim.tracker.dto.TrackerAttachmentsDto;

@Repository
public class InquiryTrackerAttachmentDaoImpl extends AbstractSyncDAO {

	private static final long serialVersionUID = 281363029151032499L;

	public static void execute(Criterion inRowIdCn, TrackerUsers trackerUser, HashMap<String, List<?>> returnHashMap)
			throws Exception {

		validate(inRowIdCn, trackerUser, returnHashMap);

		TrackerAttachmentsDao trackerAttachmentsDao = HibernateTrackerDaoFactory.getTrackerAttachmentsDao();
		List<TrackerAttachments> trackerAttachmentList = trackerAttachmentsDao.findByCriteria(inRowIdCn);
		List<TrackerAttachmentsDto> trackerAttachmentDtoList = new ArrayList<TrackerAttachmentsDto>();
		copyTrackerDbObjectToDto(trackerAttachmentList, trackerAttachmentDtoList);
		returnHashMap.put(TrackerAttachmentsDto.class.getSimpleName(), trackerAttachmentDtoList);

	}

	public static void validate(Criterion inRowIdCn, TrackerUsers trackerUser, HashMap<String, List<?>> returnHashMap)
			throws Exception {

	}

	private static void copyTrackerDbObjectToDto(List<TrackerAttachments> trackerAttachmentList,
			List<TrackerAttachmentsDto> trackerAttachmentDtoList) throws Exception {

		for (TrackerAttachments trackerAttachment : trackerAttachmentList) {
			TrackerAttachmentsDto trackerAttachmentDto = new TrackerAttachmentsDto();
			BeanUtilsBean.getInstance().copyProperties(trackerAttachmentDto, trackerAttachment);
			trackerAttachmentDto.setAddedBy(trackerAttachment.getAddedBy().getId());
			trackerAttachmentDto.setCommentId(trackerAttachment.getComment().getId());
			trackerAttachmentDto.setTaskId(trackerAttachment.getTask().getId());
			trackerAttachmentDto.setAttachmentId(trackerAttachment.getId());
			trackerAttachmentDtoList.add(trackerAttachmentDto);
		}

	}

}
