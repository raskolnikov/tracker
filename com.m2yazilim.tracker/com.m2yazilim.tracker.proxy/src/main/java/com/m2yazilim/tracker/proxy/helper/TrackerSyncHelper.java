package com.m2yazilim.tracker.proxy.helper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.m2yazilim.tracker.dataaccess.factories.tracker.HibernateTrackerDaoFactory;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerAssignedDao;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerAttachmentsDao;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerCommentsDao;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerHistoryDao;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerListCategoryDao;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerListResolutionDao;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerListStatusDao;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerListTasktypeDao;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerLocationGetTypeDao;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerProjectsDao;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerUserMobileSettingDao;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerVotesDao;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerAssigned;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerAttachments;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerComments;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerHistory;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerListCategory;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerListResolution;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerListStatus;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerListTasktype;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerLocationGetType;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerProjects;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerTasks;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerUserMobileSetting;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerUsers;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerVotes;
import com.m2yazilim.tracker.dataaccess.services.data.DataLayerTrackerImpl;
import com.m2yazilim.tracker.dto.TrackerAssignedDto;
import com.m2yazilim.tracker.dto.TrackerAttachmentsDto;
import com.m2yazilim.tracker.dto.TrackerCommentsDto;
import com.m2yazilim.tracker.dto.TrackerHistoryDto;
import com.m2yazilim.tracker.dto.TrackerListCategoryDto;
import com.m2yazilim.tracker.dto.TrackerListResolutionDto;
import com.m2yazilim.tracker.dto.TrackerListStatusDto;
import com.m2yazilim.tracker.dto.TrackerListTasktypeDto;
import com.m2yazilim.tracker.dto.TrackerProjectsDto;
import com.m2yazilim.tracker.dto.TrackerTasksDto;
import com.m2yazilim.tracker.dto.TrackerVotesDto;
import com.m2yazilim.tracker.model.TrackerSyncTableModel;

public class TrackerSyncHelper {

	public static HashMap<String, List<?>> getUserAllDataByUserId(String userId) throws IllegalAccessException,
			InvocationTargetException {
		TrackerAssignedDao trackerAssignedDao = HibernateTrackerDaoFactory.getTrackerAssignedDao();
		TrackerCommentsDao trackerCommentsDao = HibernateTrackerDaoFactory.getTrackerCommentsDao();
		TrackerAttachmentsDao trackerAttachmentDao = HibernateTrackerDaoFactory.getTrackerAttachmentsDao();
		TrackerHistoryDao trackerHistoryDao = HibernateTrackerDaoFactory.getTrackerHistoryDao();
		TrackerListCategoryDao trackerListCategoryDao = HibernateTrackerDaoFactory.getTrackerListCategoryDao();
		TrackerListResolutionDao trackerListResolutionDao = HibernateTrackerDaoFactory.getTrackerListResolutionDao();

		TrackerUsers user = DataLayerTrackerImpl.getInstance().getTrackerUsers(Integer.parseInt(userId));

		Criterion eqUserCn = Restrictions.eq("user", user);
		List<TrackerAssigned> trackerAssignedList = trackerAssignedDao.findByCriteria(eqUserCn);
		// Query query =
		// trackerAssignedDao.getQuery("from TrackerAssigned A left join A.task T where A.user=:user and T.isClosed=:isClosed");
		// query.setParameter("user", user) ;
		// query.setParameter("isClosed", 1) ;

		// List resultObjectList = query.list() ;

		List<TrackerAssignedDto> trackerAssignedDtoList = new ArrayList<TrackerAssignedDto>();
		List<TrackerCommentsDto> trackerCommentDtoList = new ArrayList<TrackerCommentsDto>();
		List<TrackerAttachmentsDto> trackerAttachmentDtoList = new ArrayList<TrackerAttachmentsDto>();
		List<TrackerTasksDto> trackerTasksDtoList = new ArrayList<TrackerTasksDto>();
		List<TrackerHistoryDto> trackerHistoryDtoList = new ArrayList<TrackerHistoryDto>();

		List<TrackerTasks> trackerTasksList = new ArrayList<TrackerTasks>();

		for (TrackerAssigned trackerAssigned : trackerAssignedList) {

			TrackerAssignedDto trackerAssignedDto = new TrackerAssignedDto();

			trackerAssignedDto.setTaskId(trackerAssigned.getTask().getId());
			trackerAssignedDto.setUserId(trackerAssigned.getUser().getId());
			trackerAssignedDto.setAssignedId(trackerAssigned.getId());
			trackerAssignedDtoList.add(trackerAssignedDto);

			TrackerTasks trackerTask = trackerAssigned.getTask();
			trackerTasksList.add(trackerTask);

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

		// Get Comment
		TrackerComments trackerComments = new TrackerComments();
		Criterion inTaskCn = Restrictions.in("task", trackerTasksList);
		List<TrackerComments> trackerCommentList = trackerCommentsDao.findByCriteria(inTaskCn);

		for (TrackerComments trackerComment : trackerCommentList) {
			TrackerCommentsDto trackerCommentsDto = new TrackerCommentsDto();
			BeanUtilsBean.getInstance().copyProperties(trackerCommentsDto, trackerComment);
			trackerCommentsDto.setUserId(trackerComment.getUser().getId());
			trackerCommentsDto.setTaskId(trackerComment.getTask().getId());
			trackerCommentsDto.setCommentId(trackerComment.getId());
			trackerCommentDtoList.add(trackerCommentsDto);
		}

		// Get attachment
		List<TrackerAttachments> trackerAttachmentList = trackerAttachmentDao.findByCriteria(inTaskCn);
		for (TrackerAttachments trackerAttachment : trackerAttachmentList) {
			TrackerAttachmentsDto trackerAttachmentDto = new TrackerAttachmentsDto();
			BeanUtilsBean.getInstance().copyProperties(trackerAttachmentDto, trackerAttachment);
			trackerAttachmentDto.setAddedBy(trackerAttachment.getAddedBy().getId());
			trackerAttachmentDto.setCommentId(trackerAttachment.getComment().getId());
			trackerAttachmentDto.setTaskId(trackerAttachment.getTask().getId());
			trackerAttachmentDto.setAttachmentId(trackerAttachment.getId());
			trackerAttachmentDtoList.add(trackerAttachmentDto);
		}

		// Get task history
		List<TrackerHistory> trackerHistoryList = trackerHistoryDao.findByCriteria(inTaskCn);

		for (TrackerHistory trackerHistory : trackerHistoryList) {
			TrackerHistoryDto trackerHistoryDto = new TrackerHistoryDto();
			BeanUtilsBean.getInstance().copyProperties(trackerHistoryDto, trackerHistory);
			trackerHistoryDto.setTaskId(trackerHistory.getTask().getId());
			trackerHistoryDto.setUserId(trackerHistory.getUser().getId());
			trackerHistoryDto.setHistoryId(trackerHistory.getId());
			trackerHistoryDtoList.add(trackerHistoryDto);
		}

		// Get TrackerListCategory
		List<TrackerListCategoryDto> trackerListCategoryDtoList = new ArrayList<TrackerListCategoryDto>();

		Criterion trListCn = Restrictions.eq("showInList", 1);
		List<TrackerListCategory> trackerListCategoryList = trackerListCategoryDao.findByCriteria(trListCn);
		for (TrackerListCategory trackerListCategory : trackerListCategoryList) {
			TrackerListCategoryDto trackerListCategoryDto = new TrackerListCategoryDto();
			BeanUtilsBean.getInstance().copyProperties(trackerListCategoryDto, trackerListCategory);
			trackerListCategoryDto.setProjectId(trackerListCategory.getProject().getId());
			trackerListCategoryDto.setCategoryId(trackerListCategory.getId());
			trackerListCategoryDtoList.add(trackerListCategoryDto);

		}

		// Get TrackerListResolution
		List<TrackerListResolutionDto> trackerListResolutionDtoList = new ArrayList<TrackerListResolutionDto>();

		Criterion resolutionListCn = Restrictions.eq("showInList", 1);
		List<TrackerListResolution> trackerListResolutionList = trackerListResolutionDao.findByCriteria(resolutionListCn);
		for (TrackerListResolution trackerListResolution : trackerListResolutionList) {
			TrackerListResolutionDto trackerListResolutionDto = new TrackerListResolutionDto();
			BeanUtilsBean.getInstance().copyProperties(trackerListResolutionDto, trackerListResolution);
			trackerListResolutionDto.setProjectId(trackerListResolution.getProject().getId());
			trackerListResolutionDto.setResolutionId(trackerListResolution.getId());

			trackerListResolutionDtoList.add(trackerListResolutionDto);
		}

		// Get TrackerListStatus
		TrackerListStatusDao trackerListStatusDao = HibernateTrackerDaoFactory.getTrackerListStatusDao();
		List<TrackerListStatusDto> trackerListStatusDtoList = new ArrayList<TrackerListStatusDto>();
		List<TrackerListStatus> trackerListStatusList = trackerListStatusDao.findByCriteria(resolutionListCn);
		for (TrackerListStatus trackerListStatus : trackerListStatusList) {
			TrackerListStatusDto trackerListStatusDto = new TrackerListStatusDto();
			BeanUtilsBean.getInstance().copyProperties(trackerListStatusDto, trackerListStatus);
			trackerListStatusDto.setProjectId(trackerListStatus.getProject().getId());
			trackerListStatusDto.setStatusId(trackerListStatus.getId());
			trackerListStatusDtoList.add(trackerListStatusDto);

		}

		// Get TrackerListTasktype
		TrackerListTasktypeDao trackerListTasktypeDao = HibernateTrackerDaoFactory.getTrackerListTasktypeDao();
		List<TrackerListTasktypeDto> trackerListTasktypeDtoList = new ArrayList<TrackerListTasktypeDto>();
		List<TrackerListTasktype> trackerListTasktypeList = trackerListTasktypeDao.findByCriteria(resolutionListCn);
		for (TrackerListTasktype trackerListTasktype : trackerListTasktypeList) {
			TrackerListTasktypeDto trackerListTasktypeDto = new TrackerListTasktypeDto();
			BeanUtilsBean.getInstance().copyProperties(trackerListTasktypeDto, trackerListTasktype);
			trackerListTasktypeDto.setProjectId(trackerListTasktype.getProject().getId());
			trackerListTasktypeDto.setTasktypeId(trackerListTasktype.getId());
			trackerListTasktypeDtoList.add(trackerListTasktypeDto);
		}

		// Get TrackerProjects
		TrackerProjectsDao trackerProjectsDao = HibernateTrackerDaoFactory.getTrackerProjectsDao();
		List<TrackerProjectsDto> trackerProjectsDtoList = new ArrayList<TrackerProjectsDto>();
		List<TrackerProjects> trackerProjectsList = trackerProjectsDao.findByCriteria();
		for (TrackerProjects trackerProject : trackerProjectsList) {
			TrackerProjectsDto trackerProjectsDto = new TrackerProjectsDto();
			BeanUtilsBean.getInstance().copyProperties(trackerProjectsDto, trackerProject);
			trackerProjectsDto.setProjectId(trackerProject.getId());
			trackerProjectsDtoList.add(trackerProjectsDto);
		}

		// Get TrackerVotes
		TrackerVotesDao trackerVotesDao = HibernateTrackerDaoFactory.getTrackerVotesDao();
		List<TrackerVotesDto> trackerVotesDtoList = new ArrayList<TrackerVotesDto>();
		List<TrackerVotes> trackerVotesList = trackerVotesDao.findByCriteria(inTaskCn, eqUserCn);
		for (TrackerVotes trackerVotes : trackerVotesList) {
			TrackerVotesDto trackerVotesDto = new TrackerVotesDto();
			BeanUtilsBean.getInstance().copyProperties(trackerVotesDto, trackerVotes);
			trackerVotesDto.setTaskId(trackerVotes.getTask().getId());
			trackerVotesDto.setUserId(trackerVotes.getUser().getId());
			trackerVotesDtoList.add(trackerVotesDto);
		}

		// Sync service output map

		HashMap<String, List<?>> returnHashMap = new HashMap<String, List<?>>();

		returnHashMap.put(TrackerCommentsDto.class.getSimpleName(), trackerCommentDtoList);
		returnHashMap.put(TrackerAssignedDto.class.getSimpleName(), trackerAssignedDtoList);
		returnHashMap.put(TrackerTasksDto.class.getSimpleName(), trackerTasksDtoList);
		returnHashMap.put(TrackerAttachmentsDto.class.getSimpleName(), trackerAttachmentDtoList);
		returnHashMap.put(TrackerHistoryDto.class.getSimpleName(), trackerHistoryDtoList);
		returnHashMap.put(TrackerListCategoryDto.class.getSimpleName(), trackerListCategoryDtoList);
		returnHashMap.put(TrackerListResolutionDto.class.getSimpleName(), trackerListResolutionDtoList);
		returnHashMap.put(TrackerListStatusDto.class.getSimpleName(), trackerListStatusDtoList);
		returnHashMap.put(TrackerListTasktypeDto.class.getSimpleName(), trackerListTasktypeDtoList);
		returnHashMap.put(TrackerProjectsDto.class.getSimpleName(), trackerProjectsDtoList);
		returnHashMap.put(TrackerVotesDto.class.getSimpleName(), trackerVotesDtoList);
		return returnHashMap;
	}

	public static HashMap<String, ArrayList<String>> getLightTableVersionList(String userId) throws Exception {

		HashMap<String, ArrayList<String>> lightTableVersionList = new HashMap<String, ArrayList<String>>();

		TrackerAssignedDao trackerAssignedDao = HibernateTrackerDaoFactory.getTrackerAssignedDao();
		TrackerCommentsDao trackerCommentsDao = HibernateTrackerDaoFactory.getTrackerCommentsDao();
		TrackerAttachmentsDao trackerAttachmentDao = HibernateTrackerDaoFactory.getTrackerAttachmentsDao();
		TrackerHistoryDao trackerHistoryDao = HibernateTrackerDaoFactory.getTrackerHistoryDao();
		TrackerListCategoryDao trackerListCategoryDao = HibernateTrackerDaoFactory.getTrackerListCategoryDao();
		TrackerListResolutionDao trackerListResolutionDao = HibernateTrackerDaoFactory.getTrackerListResolutionDao();

		TrackerUsers user = DataLayerTrackerImpl.getInstance().getTrackerUsers(Integer.parseInt(userId));

		Criterion eqUserCn = Restrictions.eq("user", user);
		List<TrackerAssigned> trackerAssignedList = trackerAssignedDao.findByCriteria(eqUserCn);

		TrackerSyncHelper.generateTableRowVersion(TrackerAssigned.class.getSimpleName(), lightTableVersionList,
				(ArrayList<?>) trackerAssignedList);

		List<TrackerTasks> trackerTasksList = new ArrayList<TrackerTasks>();
		for (TrackerAssigned trackerAssigned : trackerAssignedList) {
			trackerTasksList.add(trackerAssigned.getTask());
		}
		
		//Get Task
		TrackerSyncHelper.generateTableRowVersion(TrackerTasks.class.getSimpleName(), lightTableVersionList,
				(ArrayList<?>) trackerTasksList);

		// Get Comment
		Criterion inTaskCn = Restrictions.in("task", trackerTasksList);
		List<TrackerComments> trackerCommentList = trackerCommentsDao.findByCriteria(inTaskCn);
		TrackerSyncHelper.generateTableRowVersion(TrackerComments.class.getSimpleName(), lightTableVersionList,
				(ArrayList<?>) trackerCommentList);

		// Get attachment
		List<TrackerAttachments> trackerAttachmentList = trackerAttachmentDao.findByCriteria(inTaskCn);
		TrackerSyncHelper.generateTableRowVersion(TrackerAttachments.class.getSimpleName(), lightTableVersionList,
				(ArrayList<?>) trackerAttachmentList);

		// Get task history
		List<TrackerHistory> trackerHistoryList = trackerHistoryDao.findByCriteria(inTaskCn);
		TrackerSyncHelper.generateTableRowVersion(TrackerHistory.class.getSimpleName(), lightTableVersionList,
				(ArrayList<?>) trackerHistoryList);

		// Get TrackerListCategory
		Criterion resolutionListCn = Restrictions.eq("showInList", 1);
		List<TrackerListCategory> trackerListCategoryList = trackerListCategoryDao.findByCriteria(resolutionListCn);
		TrackerSyncHelper.generateTableRowVersion(TrackerListCategory.class.getSimpleName(), lightTableVersionList,
				(ArrayList<?>) trackerListCategoryList);

		// Get TrackerListResolution
		List<TrackerListResolution> trackerListResolutionList = trackerListResolutionDao.findByCriteria(resolutionListCn);
		TrackerSyncHelper.generateTableRowVersion(TrackerListResolution.class.getSimpleName(), lightTableVersionList,
				(ArrayList<?>) trackerListResolutionList);

		// Get TrackerListStatus
		TrackerListStatusDao trackerListStatusDao = HibernateTrackerDaoFactory.getTrackerListStatusDao();
		List<TrackerListStatus> trackerListStatusList = trackerListStatusDao.findByCriteria(resolutionListCn);
		TrackerSyncHelper.generateTableRowVersion(TrackerListStatus.class.getSimpleName(), lightTableVersionList,
				(ArrayList<?>) trackerListStatusList);

		// Get TrackerListTasktype
		TrackerListTasktypeDao trackerListTasktypeDao = HibernateTrackerDaoFactory.getTrackerListTasktypeDao();
		List<TrackerListTasktype> trackerListTasktypeList = trackerListTasktypeDao.findByCriteria(resolutionListCn);
		TrackerSyncHelper.generateTableRowVersion(TrackerListTasktype.class.getSimpleName(), lightTableVersionList,
				(ArrayList<?>) trackerListTasktypeList);

		// Get TrackerProjects
		TrackerProjectsDao trackerProjectsDao = HibernateTrackerDaoFactory.getTrackerProjectsDao();
		List<TrackerProjects> trackerProjectsList = trackerProjectsDao.findByCriteria();
		TrackerSyncHelper.generateTableRowVersion(TrackerProjects.class.getSimpleName(), lightTableVersionList,
				(ArrayList<?>) trackerProjectsList);

		// Get TrackerVotes
		TrackerVotesDao trackerVotesDao = HibernateTrackerDaoFactory.getTrackerVotesDao();
		List<TrackerVotes> trackerVotesList = trackerVotesDao.findByCriteria(inTaskCn, eqUserCn);
		TrackerSyncHelper.generateTableRowVersion(TrackerVotes.class.getSimpleName(), lightTableVersionList,
				(ArrayList<?>) trackerVotesList);

		// Get TrackerUserMobileSettings
		TrackerUserMobileSettingDao trackerUserMobileSettingDao = HibernateTrackerDaoFactory.getTrackerUserMobileSettingDao();
		List<TrackerUserMobileSetting> trackerUserMobileSettingList = trackerUserMobileSettingDao.findByCriteria(eqUserCn);
		TrackerSyncHelper.generateTableRowVersion(TrackerUserMobileSetting.class.getSimpleName(),
				lightTableVersionList, (ArrayList<?>) trackerUserMobileSettingList);

		// Get TrackerLocationGetType
		TrackerLocationGetTypeDao trackerLocationGetTypeDao = HibernateTrackerDaoFactory.getTrackerLocationGetTypeDao();
		List<TrackerLocationGetType> trackerLocationGetTypeList = trackerLocationGetTypeDao.findAll();
		TrackerSyncHelper.generateTableRowVersion(TrackerLocationGetType.class.getSimpleName(), lightTableVersionList,
				(ArrayList<?>) trackerLocationGetTypeList);

		return lightTableVersionList;

	}

	public static void generateTableRowVersion(String tableName,
			HashMap<String, ArrayList<String>> lightTableVersionList, ArrayList<?> ObjectList) throws Exception {
		ArrayList<String> tableRowIdVersionList;
		tableRowIdVersionList = new ArrayList<String>();
		for (Object trackerDbObject : ObjectList) {

			Method method = trackerDbObject.getClass().getDeclaredMethod("getId");
			int rowId = (Integer) method.invoke(trackerDbObject);

			Method method2 = trackerDbObject.getClass().getDeclaredMethod("getVersion");
			int version = (Integer) method2.invoke(trackerDbObject);

			tableRowIdVersionList.add(String.valueOf(rowId) + ":" + String.valueOf(version));
			lightTableVersionList.put(tableName, tableRowIdVersionList);
		}
	}

	public static HashMap<String, List<?>> getUserDataByTable(HashMap<String, ArrayList<String>> tableRowMap,
			String userId) throws Exception {
		HashMap<String, List<?>> returnHashMap = new HashMap<String, List<?>>();
		TrackerUsers user = DataLayerTrackerImpl.getInstance().getTrackerUsers(Integer.parseInt(userId));

		// Method parameters
		Class<?>[] executeMethodParams = new Class[3];

		executeMethodParams[0] = Criterion.class;
		executeMethodParams[1] = TrackerUsers.class;
		executeMethodParams[2] = HashMap.class;

		for (TrackerSyncTableModel trackerSyncTableModel : TrackerSyncTableListHelper.getTrackerSyncTableModelList()) {
			if (tableRowMap.containsKey(trackerSyncTableModel.getTableObjectName())) {

				List<Integer> tableRowIdList = stringListToInteger(tableRowMap,
						trackerSyncTableModel.getTableObjectName());
				Criterion inRowIdCn = Restrictions.in("id", tableRowIdList);

				Class<?> cls = Class.forName(trackerSyncTableModel.SERVICE_DAO_PACKAGE_NAME + "."
						+ trackerSyncTableModel.getInquiryDaoClassName());
				Object obj = cls.newInstance();

				// call the execute method
				Method method = cls.getDeclaredMethod("execute", executeMethodParams);
				method.invoke(obj, inRowIdCn, user, returnHashMap);

			}
		}
		return returnHashMap;
	}

	private static List<Integer> stringListToInteger(HashMap<String, ArrayList<String>> tableRowMap, String tableName) {
		List<String> tableRowIdListTmp = tableRowMap.get(tableName);
		List<Integer> tableRowIdList = new ArrayList<Integer>();
		for (String tableRowId : tableRowIdListTmp) {
			tableRowIdList.add(Integer.parseInt(tableRowId));
		}
		return tableRowIdList;
	}
}
