package com.m2yazilim.tracker.scheduler.messages;

import java.util.List;

import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerTableChangeLog;
import com.m2yazilim.tracker.messages.base.ResponseMessage;

public class UpdateUserPushTableResponse extends ResponseMessage {

	private static final long serialVersionUID = 1L;
	private List<TrackerTableChangeLog> trackerTableChangeLogList;

	public List<TrackerTableChangeLog> getTrackerTableChangeLogList() {
		return trackerTableChangeLogList;
	}

	public void setTrackerTableChangeLogList(List<TrackerTableChangeLog> trackerTableChangeLogList) {
		this.trackerTableChangeLogList = trackerTableChangeLogList;
	}

}
