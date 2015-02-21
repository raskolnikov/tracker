package com.m2yazilim.tracker.model;

import java.io.Serializable;

public class Notification implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String message;
	private String messageCode;
	private String notificationType;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	public String getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

}

