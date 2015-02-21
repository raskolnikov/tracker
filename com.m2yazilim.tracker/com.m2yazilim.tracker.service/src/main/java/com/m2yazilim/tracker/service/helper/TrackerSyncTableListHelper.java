package com.m2yazilim.tracker.service.helper;

import java.util.ArrayList;
import java.util.List;

import com.m2yazilim.tracker.model.TrackerSyncTableModel;

public class TrackerSyncTableListHelper {
	private static List<TrackerSyncTableModel> trackerSyncTableModelList;
	private static List<TrackerSyncTableModel> trackerPushTableModelList;

	private static List<String> trackerSyncTableList;

	static {
		trackerSyncTableModelList = new ArrayList<TrackerSyncTableModel>();

		trackerSyncTableModelList.add(new TrackerSyncTableModel("TrackerComments", "InquiryTrackerCommentDaoImpl"));
		trackerSyncTableModelList.add(new TrackerSyncTableModel("TrackerAssigned", "InquiryTrackerAssignedDaoImpl"));
		trackerSyncTableModelList.add(new TrackerSyncTableModel("TrackerTasks", "InquiryTrackerTaskDaoImpl"));
		trackerSyncTableModelList.add(new TrackerSyncTableModel("TrackerAttachments", "InquiryTrackerAttachmentDaoImpl"));
		trackerSyncTableModelList.add(new TrackerSyncTableModel("TrackerHistory", "InquiryTrackerHistoryDaoImpl"));
		trackerSyncTableModelList.add(new TrackerSyncTableModel("TrackerListCategory",
				"InquiryTrackerListCategoryDaoImpl"));
		trackerSyncTableModelList.add(new TrackerSyncTableModel("TrackerListResolution",
				"InquiryTrackerListResolutionDaoImpl"));
		trackerSyncTableModelList.add(new TrackerSyncTableModel("TrackerListStatus", "InquiryTrackerListStatusDaoImpl"));
		trackerSyncTableModelList.add(new TrackerSyncTableModel("TrackerListTasktype",
				"InquiryTrackerListTasktypeDaoImpl"));
		trackerSyncTableModelList.add(new TrackerSyncTableModel("TrackerProjects", "InquiryTrackerProjectDaoImpl"));
		trackerSyncTableModelList.add(new TrackerSyncTableModel("TrackerVotes", "InquiryTrackerVoteDaoImpl"));
		trackerSyncTableModelList.add(new TrackerSyncTableModel("TrackerLocationGetType",
				"InquiryTrackerLocationGetTypeDaoImpl"));
		trackerSyncTableModelList.add(new TrackerSyncTableModel("TrackerUserMobileSetting",
				"InquiryTrackerUserMobileSettingDaoImpl"));

		/**
		 * TrackerComments,TrackerAssigned,TrackerTasks,TrackerAttachments,
		 * TrackerVotes bu tablolar disindaki tum degisiklikler tum kullaicilari
		 * etkileyecegi icin ozel bir sorgu yapilmasina gerek yoktur
		 * 
		 * InquiryAllPushUserDaoImpl sorgusu diger tum push lar icin
		 * kullanilacaktir
		 * 
		 */
		trackerPushTableModelList = new ArrayList<TrackerSyncTableModel>();

		trackerPushTableModelList.add(new TrackerSyncTableModel("TrackerComments",
				"InquiryPushUserTrackerCommentDaoImpl"));
		trackerPushTableModelList.add(new TrackerSyncTableModel("TrackerAssigned",
				"InquiryPushUserTrackerAssignedDaoImpl"));
		trackerPushTableModelList.add(new TrackerSyncTableModel("TrackerTasks", "InquiryPushUserTrackerTaskDaoImpl"));
		trackerPushTableModelList.add(new TrackerSyncTableModel("TrackerAttachments",
				"InquiryPushUserTrackerAttachmentDaoImpl"));
		trackerPushTableModelList.add(new TrackerSyncTableModel("TrackerVotes", "InquiryPushUserTrackerVoteDaoImpl"));

	}

	public static List<TrackerSyncTableModel> getTrackerSyncTableModelList() {
		return trackerSyncTableModelList;
	}

	public static List<String> getTrackerSyncTableList() {
		return trackerSyncTableList;
	}

	public static List<TrackerSyncTableModel> getTrackerPushTableModelList() {
		return trackerPushTableModelList;
	}

}
