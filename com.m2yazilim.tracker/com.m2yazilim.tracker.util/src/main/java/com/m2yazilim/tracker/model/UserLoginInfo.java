package com.m2yazilim.tracker.model;

import java.io.Serializable;

public class UserLoginInfo implements Serializable {

	private static final long serialVersionUID = 8240866173894108969L;
	private String userName;
	private String userPass;
	private String deviceRegisterKey;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public String getDeviceRegisterKey() {
		return deviceRegisterKey;
	}

	public void setDeviceRegisterKey(String deviceRegisterKey) {
		this.deviceRegisterKey = deviceRegisterKey;
	}

}
