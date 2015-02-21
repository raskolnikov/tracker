package com.m2yazilim.tracker.service.messages;

import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerUsers;
import com.m2yazilim.tracker.messages.base.ResponseMessage;

public class TrackerUserTokenCheckResponse extends ResponseMessage {

	private static final long serialVersionUID = -5942671969569027217L;
	private boolean tokenValid;

	private TrackerUsers trackerUser;

	public boolean isTokenValid() {
		return tokenValid;
	}

	public void setTokenValid(boolean tokenValid) {
		this.tokenValid = tokenValid;
	}

	public TrackerUsers getTrackerUser() {
		return trackerUser;
	}

	public void setTrackerUser(TrackerUsers trackerUser) {
		this.trackerUser = trackerUser;
	}

}
