package com.m2yazilim.tracker.proxy.messages;

import com.m2yazilim.tracker.messages.base.RequestMessage;
import com.m2yazilim.tracker.model.UserLoginInfo;

public class TrackerUserLoginControlRequest extends RequestMessage {

	private static final long serialVersionUID = -2697034897249665199L;
	private UserLoginInfo userLoginInfo;
	private long tokenLiveTimeInSecond;

	public UserLoginInfo getUserLoginInfo() {
		return userLoginInfo;
	}

	public void setUserLoginInfo(UserLoginInfo userLoginInfo) {
		this.userLoginInfo = userLoginInfo;
	}

	public long getTokenLiveTimeInSecond() {
		return tokenLiveTimeInSecond;
	}

	public void setTokenLiveTimeInSecond(long tokenLiveTimeInSecond) {
		this.tokenLiveTimeInSecond = tokenLiveTimeInSecond;
	}

}
