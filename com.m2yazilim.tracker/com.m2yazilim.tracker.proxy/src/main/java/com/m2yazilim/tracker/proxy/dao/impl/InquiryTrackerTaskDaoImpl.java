package com.m2yazilim.tracker.proxy.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.m2yazilim.tracker.constants.TrackerConstants;
import com.m2yazilim.tracker.dao.AbstractSyncDAO;
import com.m2yazilim.tracker.dataaccess.factories.tracker.HibernateTrackerDaoFactory;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerAdminRequestsDao;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerTasksDao;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerAdminRequests;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerTasks;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerUsers;
import com.m2yazilim.tracker.dto.TrackerTasksDto;

@Repository
public class InquiryTrackerTaskDaoImpl extends AbstractSyncDAO {

	private static final long serialVersionUID = 281363029151032499L;

	public static void execute(Criterion inRowIdCn, TrackerUsers trackerUser, HashMap<String, List<?>> returnHashMap)
			throws Exception {

		validate(inRowIdCn, trackerUser, returnHashMap);

		TrackerTasksDao trackerTasksDao = HibernateTrackerDaoFactory.getTrackerTasksDao();
		List<TrackerTasks> trackerTasksList = trackerTasksDao.findByCriteria(inRowIdCn);
		List<TrackerTasksDto> trackerTasksDtoList = new ArrayList<TrackerTasksDto>();

		copyTrackerDbObjectToDto(trackerTasksList, trackerTasksDtoList);

		// Kapatma isteginin yollanip yollanmadigini kontrol eder
		Criterion taskCn = Restrictions.in("task", trackerTasksList);
		Criterion requestTypeCn = Restrictions.eq("requestType", TrackerConstants.REQUEST_TYPE_CLOSE);
		Criterion resolvedByCn = Restrictions.eq("resolvedBy.id", TrackerConstants.DEFAULT_TRACKER_DB_GENERIC_ID);

		TrackerAdminRequestsDao trackerAdminRequestsDao = HibernateTrackerDaoFactory.getTrackerAdminRequestsDao();
		List<TrackerAdminRequests> trackerAdminRequestList = trackerAdminRequestsDao.findByCriteria(taskCn,
				requestTypeCn, resolvedByCn);

		for (TrackerAdminRequests trackerAdminRequest : trackerAdminRequestList) {
			for (TrackerTasksDto trackerTasksDto : trackerTasksDtoList) {
				if (trackerAdminRequest.getTask().getId().equals(trackerTasksDto.getTaskId())) {
					trackerTasksDto.setIsCloseRequestSent(TrackerConstants.CLOSE_REQUEST_SENT);
				}

			}
		}

		returnHashMap.put(TrackerTasksDto.class.getSimpleName(), trackerTasksDtoList);

	}

	public static void validate(Criterion inRowIdCn, TrackerUsers trackerUser, HashMap<String, List<?>> returnHashMap)
			throws Exception {

	}

	private static void copyTrackerDbObjectToDto(List<TrackerTasks> trackerTasksList,
			List<TrackerTasksDto> trackerTasksDtoList) throws Exception {

		for (TrackerTasks trackerTask : trackerTasksList) {

			TrackerTasksDto trackerTaskDto = new TrackerTasksDto();
			BeanUtilsBean.getInstance().copyProperties(trackerTaskDto, trackerTask);
			trackerTaskDto.setClosedBy(trackerTask.getClosedBy().getId());
			trackerTaskDto.setItemStatus(trackerTask.getItemStatus().getId());
			trackerTaskDto.setLastEditedBy(trackerTask.getLastEditedBy().getId());
			trackerTaskDto.setOpenedBy(trackerTask.getOpenedBy().getId());
			trackerTaskDto.setOperatingSystem(trackerTask.getOperatingSystem().getId());
			trackerTaskDto.setProductCategory(trackerTask.getProductCategory().getId());
			trackerTaskDto.setProductVersion(trackerTask.getProductVersion().getId());
			trackerTaskDto.setProjectId(trackerTask.getProject().getId());
			trackerTaskDto.setResolutionReason(trackerTask.getResolutionReason().getId());
			trackerTaskDto.setTaskType(trackerTask.getTaskType().getId());
			trackerTaskDto.setTaskId(trackerTask.getId());
			trackerTasksDtoList.add(trackerTaskDto);

		}

	}

}
