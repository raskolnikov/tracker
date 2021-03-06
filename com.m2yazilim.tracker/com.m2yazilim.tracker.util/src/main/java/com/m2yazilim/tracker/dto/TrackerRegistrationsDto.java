package com.m2yazilim.tracker.dto;

// Generated Nov 21, 2012 12:58:59 PM by Hibernate Tools 4.0.0

/**
 * TrackerRegistrationsDto generated by hbm2java
 */
public class TrackerRegistrationsDto implements java.io.Serializable {

	private Integer regId;
	private int version;
	private int regTime;
	private String confirmCode;
	private String userName;
	private String realName;
	private String emailAddress;
	private String jabberId;
	private int notifyType;
	private String magicUrl;
	private int timeZone;

	public TrackerRegistrationsDto() {
	}

	public TrackerRegistrationsDto(int regTime, String confirmCode,
			String userName, String realName, String emailAddress,
			String jabberId, int notifyType, String magicUrl, int timeZone) {
		this.regTime = regTime;
		this.confirmCode = confirmCode;
		this.userName = userName;
		this.realName = realName;
		this.emailAddress = emailAddress;
		this.jabberId = jabberId;
		this.notifyType = notifyType;
		this.magicUrl = magicUrl;
		this.timeZone = timeZone;
	}

	public Integer getRegId() {
		return this.regId;
	}

	public void setRegId(Integer regId) {
		this.regId = regId;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getRegTime() {
		return this.regTime;
	}

	public void setRegTime(int regTime) {
		this.regTime = regTime;
	}

	public String getConfirmCode() {
		return this.confirmCode;
	}

	public void setConfirmCode(String confirmCode) {
		this.confirmCode = confirmCode;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getJabberId() {
		return this.jabberId;
	}

	public void setJabberId(String jabberId) {
		this.jabberId = jabberId;
	}

	public int getNotifyType() {
		return this.notifyType;
	}

	public void setNotifyType(int notifyType) {
		this.notifyType = notifyType;
	}

	public String getMagicUrl() {
		return this.magicUrl;
	}

	public void setMagicUrl(String magicUrl) {
		this.magicUrl = magicUrl;
	}

	public int getTimeZone() {
		return this.timeZone;
	}

	public void setTimeZone(int timeZone) {
		this.timeZone = timeZone;
	}

}
