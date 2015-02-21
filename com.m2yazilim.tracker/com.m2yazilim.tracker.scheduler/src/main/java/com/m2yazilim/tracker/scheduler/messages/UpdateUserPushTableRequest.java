package com.m2yazilim.tracker.scheduler.messages;

import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerTableChangeLog;
import com.m2yazilim.tracker.messages.base.RequestMessage;

public class UpdateUserPushTableRequest extends RequestMessage {

	private static final long serialVersionUID = -8266015395073919196L;
	private TrackerTableChangeLog currentPushLog;

	public TrackerTableChangeLog getCurrentPushLog() {
		return currentPushLog;
	}

	public void setCurrentPushLog(TrackerTableChangeLog currentPushLog) {
		this.currentPushLog = currentPushLog;
	}

}
