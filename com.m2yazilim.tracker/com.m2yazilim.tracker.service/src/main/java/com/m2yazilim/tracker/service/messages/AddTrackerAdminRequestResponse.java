package com.m2yazilim.tracker.service.messages;

import com.m2yazilim.tracker.messages.base.ResponseMessage;

public class AddTrackerAdminRequestResponse extends ResponseMessage {

	private static final long serialVersionUID = 1L;
	private int adminRequestId;

	public int getAdminRequestId() {
		return adminRequestId;
	}

	public void setAdminRequestId(int adminRequestId) {
		this.adminRequestId = adminRequestId;
	}

}
