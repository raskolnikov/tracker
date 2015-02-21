package com.m2yazilim.tracker.model;

import java.io.Serializable;

public class TaskInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String userId;
	private String taskId;
	private String projectId;
	private String projectTitle;
	private String itemStatus;
	private String statusName;
	private String resolutionReason;
	private String dateOpened;
	private String dueDate;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getProjectTitle() {
		return projectTitle;
	}
	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}
	public String getItemStatus() {
		return itemStatus;
	}
	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getResolutionReason() {
		return resolutionReason;
	}
	public void setResolutionReason(String resolutionReason) {
		this.resolutionReason = resolutionReason;
	}
	public String getDateOpened() {
		return dateOpened;
	}
	public void setDateOpened(String dateOpened) {
		this.dateOpened = dateOpened;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}



}

