package com.m2yazilim.tracker.dto;

// Generated Nov 21, 2012 12:58:59 PM by Hibernate Tools 4.0.0

/**
 * TrackerUsersInGroupsDto generated by hbm2java
 */
public class TrackerUsersInGroupsDto implements java.io.Serializable {

	private Integer recordId;
	private int version;
	private int userId;
	private int groupId;

	public TrackerUsersInGroupsDto() {
	}

	public TrackerUsersInGroupsDto(int userId, int groupId) {
		this.userId = userId;
		this.groupId = groupId;
	}

	public Integer getRecordId() {
		return this.recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getGroupId() {
		return this.groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

}
