package com.m2yazilim.tracker.service.messages;

import com.m2yazilim.tracker.messages.base.RequestMessage;

public class AddTrackerAdminRequestRequest extends RequestMessage {

	private static final long serialVersionUID = 3609331658466707972L;
	private int taskId;
	private int submittedBy;
	private int requestType;
	private String reasonGiven;

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public int getSubmittedBy() {
		return submittedBy;
	}

	public void setSubmittedBy(int submittedBy) {
		this.submittedBy = submittedBy;
	}

	public int getRequestType() {
		return requestType;
	}

	public void setRequestType(int requestType) {
		this.requestType = requestType;
	}

	public String getReasonGiven() {
		return reasonGiven;
	}

	public void setReasonGiven(String reasonGiven) {
		this.reasonGiven = reasonGiven;
	}

}
