package com.m2yazilim.tracker.dto;

// Generated Nov 21, 2012 12:58:59 PM by Hibernate Tools 4.0.0

/**
 * TrackerListTasktypeDto generated by hbm2java
 */
public class TrackerListTasktypeDto implements java.io.Serializable {

	private Integer tasktypeId;
	private int version;
	private String tasktypeName;
	private int listPosition;
	private int showInList;
	private int projectId;

	public TrackerListTasktypeDto() {
	}

	public TrackerListTasktypeDto(String tasktypeName, int listPosition,
			int showInList, int projectId) {
		this.tasktypeName = tasktypeName;
		this.listPosition = listPosition;
		this.showInList = showInList;
		this.projectId = projectId;
	}

	public Integer getTasktypeId() {
		return this.tasktypeId;
	}

	public void setTasktypeId(Integer tasktypeId) {
		this.tasktypeId = tasktypeId;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getTasktypeName() {
		return this.tasktypeName;
	}

	public void setTasktypeName(String tasktypeName) {
		this.tasktypeName = tasktypeName;
	}

	public int getListPosition() {
		return this.listPosition;
	}

	public void setListPosition(int listPosition) {
		this.listPosition = listPosition;
	}

	public int getShowInList() {
		return this.showInList;
	}

	public void setShowInList(int showInList) {
		this.showInList = showInList;
	}

	public int getProjectId() {
		return this.projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

}
