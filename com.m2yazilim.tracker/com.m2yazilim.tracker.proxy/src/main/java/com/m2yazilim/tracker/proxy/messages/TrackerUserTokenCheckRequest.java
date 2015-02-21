package com.m2yazilim.tracker.proxy.messages;

import com.m2yazilim.tracker.messages.base.RequestMessage;

public class TrackerUserTokenCheckRequest extends RequestMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1435732571062853280L;
	private String userId;
	private String token;
	private String deviceRegisterKey;
	private String tokenLiveTimeInSecond;

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

	public String getTokenLiveTimeInSecond() {
		return tokenLiveTimeInSecond;
	}

	public void setTokenLiveTimeInSecond(String tokenLiveTimeInSecond) {
		this.tokenLiveTimeInSecond = tokenLiveTimeInSecond;
	}

}
