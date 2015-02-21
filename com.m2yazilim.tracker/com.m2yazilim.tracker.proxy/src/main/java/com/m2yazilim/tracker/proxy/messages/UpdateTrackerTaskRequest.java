package com.m2yazilim.tracker.proxy.messages;

import com.m2yazilim.tracker.messages.base.RequestMessage;

public class UpdateTrackerTaskRequest extends RequestMessage {

	private static final long serialVersionUID = 1L;
	private int taskId;
	private int itemStatus;
	private int resolutionReason;
	private int percentComplete;
	private int closedbyVersion;
	private int  userId;
	
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getItemStatus() {
		return itemStatus;
	}
	public void setItemStatus(int itemStatus) {
		this.itemStatus = itemStatus;
	}
	public int getResolutionReason() {
		return resolutionReason;
	}
	public void setResolutionReason(int resolutionReason) {
		this.resolutionReason = resolutionReason;
	}
	public int getPercentComplete() {
		return percentComplete;
	}
	public void setPercentComplete(int percentComplete) {
		this.percentComplete = percentComplete;
	}
	public int getClosedbyVersion() {
		return closedbyVersion;
	}
	public void setClosedbyVersion(int closedbyVersion) {
		this.closedbyVersion = closedbyVersion;
	}
	
	

}
