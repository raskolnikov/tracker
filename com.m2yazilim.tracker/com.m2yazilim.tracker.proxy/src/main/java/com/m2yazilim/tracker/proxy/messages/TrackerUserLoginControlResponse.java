package com.m2yazilim.tracker.proxy.messages;

import com.m2yazilim.tracker.messages.base.ResponseMessage;
import com.m2yazilim.tracker.model.UserLoginOutput;

public class TrackerUserLoginControlResponse extends ResponseMessage {

	private static final long serialVersionUID = 8369013947082808015L;
	private UserLoginOutput userLoginOutput;

	public UserLoginOutput getUserLoginOutput() {
		return userLoginOutput;
	}

	public void setUserLoginOutput(UserLoginOutput userLoginOutput) {
		this.userLoginOutput = userLoginOutput;
	}

}
