package com.m2yazilim.tracker.dto;

// Generated Jan 12, 2013 9:43:39 AM by Hibernate Tools 4.0.0

/**
 * TrackerUserMobileSettingDto generated by hbm2java
 */
public class TrackerUserMobileSettingDto implements java.io.Serializable {

	private Integer id;
	private Integer version;
	private int userId;
	private int locationGetTypeId;
	private int coordinatPostInterval;
	private int isSentLocation;
	private String remoteAttachmentFolder;

	public TrackerUserMobileSettingDto() {
	}

	public TrackerUserMobileSettingDto(int userId, int locationGetTypeId, int coordinatPostInterval,
			int isSentLocation, String remoteAttachmentFolder) {
		this.userId = userId;
		this.locationGetTypeId = locationGetTypeId;
		this.coordinatPostInterval = coordinatPostInterval;
		this.isSentLocation = isSentLocation;
		this.remoteAttachmentFolder = remoteAttachmentFolder;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getLocationGetTypeId() {
		return this.locationGetTypeId;
	}

	public void setLocationGetTypeId(int locationGetTypeId) {
		this.locationGetTypeId = locationGetTypeId;
	}

	public int getCoordinatPostInterval() {
		return this.coordinatPostInterval;
	}

	public void setCoordinatPostInterval(int coordinatPostInterval) {
		this.coordinatPostInterval = coordinatPostInterval;
	}

	public int getIsSentLocation() {
		return this.isSentLocation;
	}

	public void setIsSentLocation(int isSentLocation) {
		this.isSentLocation = isSentLocation;
	}

	public String getRemoteAttachmentFolder() {
		return remoteAttachmentFolder;
	}

	public void setRemoteAttachmentFolder(String remoteAttachmentFolder) {
		this.remoteAttachmentFolder = remoteAttachmentFolder;
	}

}
