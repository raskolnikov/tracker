package com.m2yazilim.tracker.messages.base;

import java.io.Serializable;


public class Notification implements Serializable {
	private static final long serialVersionUID = -4908142024263809253L;
	private final String HOST_ERROR_CODE = "ERROR.HOST";
	private NotificationType type;
	private String messageCode;
	private Object[] parameters;
	private int hostErrorCode;

	// son sayfada warning olarak göstermek için...
	private boolean showMessage = true;

	public static Notification createHostNotification(String errorMessage) {

		Notification not = new Notification(NotificationType.ERROR, "ERROR.HOST.21", new String[] { errorMessage });
		not.setDetail(errorMessage);
		return not;

	}

	private boolean logout = false;
	private String detail;
	private String preMessageCode;
	private Object[] preMessageParameters;

	public enum NotificationType {
		INFO, WARNING, ERROR, FATAL
	}




	public Notification(NotificationType type, String messageCode) {
		this.type = type;
		this.messageCode = messageCode;
	}

	public Notification(NotificationType type, String messageCode, boolean showMessage) {
		this.type = type;
		this.messageCode = messageCode;
		this.showMessage = showMessage;
	}


	public Notification(NotificationType type, String messageCode, Object[] parameters) {
		this.type = type;
		this.parameters = parameters;
		this.messageCode = messageCode;
	}

	public Notification(NotificationType type, int hostErrorCode, Object[] parameters) {
		this.type = type;
		this.parameters = parameters;
		this.messageCode = HOST_ERROR_CODE + "." + hostErrorCode;
	}



	public NotificationType getType() {
		return type;
	}

	public void setType(NotificationType type) {
		this.type = type;
	}

	public String getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	public Object[] getParameters() {
		return parameters;
	}

	public void setParameters(Object[] parameters) {
		this.parameters = parameters;
	}

	public int getHostErrorCode() {
		return hostErrorCode;
	}

	public void setHostErrorCode(int hostErrorCode) {
		this.hostErrorCode = hostErrorCode;
	}

	public boolean isLogout() {
		return logout;
	}

	public void setLogout(boolean logout) {
		this.logout = logout;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}



	public String getPreMessageCode() {
		return preMessageCode;
	}

	public void setPreMessageCode(String preMessageCode) {
		this.preMessageCode = preMessageCode;
	}

	public Object[] getPreMessageParameters() {
		return preMessageParameters;
	}

	public void setPreMessageParameters(Object[] preMessageParameters) {
		this.preMessageParameters = preMessageParameters;
	}

	public boolean isShowMessage() {
		return showMessage;
	}

	public void setShowMessage(boolean showMessage) {
		this.showMessage = showMessage;
	}

}
