package com.m2yazilim.tracker.model;

import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerUsers;

public class SsoRole implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String userId;
	private String token;
	private String deviceRegisterKey;
	private String sessionStatus;
	private String userStatus;
	private TrackerUsers trackerUser;

	public String getSessionStatus() {
		return sessionStatus;
	}

	public void setSessionStatus(String sessionStatus) {
		this.sessionStatus = sessionStatus;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getDeviceRegisterKey() {
		return deviceRegisterKey;
	}

	public void setDeviceRegisterKey(String deviceRegisterKey) {
		this.deviceRegisterKey = deviceRegisterKey;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public TrackerUsers getTrackerUser() {
		return trackerUser;
	}

	public void setTrackerUser(TrackerUsers trackerUser) {
		this.trackerUser = trackerUser;
	}

}
