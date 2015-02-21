package com.m2yazilim.tracker.dto;

// Generated Nov 21, 2012 12:58:59 PM by Hibernate Tools 4.0.0

/**
 * TrackerProjectsDto generated by hbm2java
 */
public class TrackerProjectsDto implements java.io.Serializable {

	private Integer projectId;
	private int version;
	private String projectTitle;
	private String themeStyle;
	private int defaultCatOwner;
	private String introMessage;
	private int projectIsActive;
	private String visibleColumns;
	private int othersView;
	private int anonOpen;
	private String notifyEmail;
	private String notifyJabber;
	private String notifyReply;
	private String notifyTypes;
	private String feedImgUrl;
	private String feedDescription;
	private String notifySubject;
	private String langCode;
	private int commentClosed;
	private int autoAssign;
	private int lastUpdated;
	private String defaultTask;
	private String defaultEntry;

	public TrackerProjectsDto() {
	}

	public TrackerProjectsDto(String projectTitle, String themeStyle,
			int defaultCatOwner, int projectIsActive, String visibleColumns,
			int othersView, int anonOpen, String notifyTypes,
			String notifySubject, String langCode, int commentClosed,
			int autoAssign, int lastUpdated, String defaultEntry) {
		this.projectTitle = projectTitle;
		this.themeStyle = themeStyle;
		this.defaultCatOwner = defaultCatOwner;
		this.projectIsActive = projectIsActive;
		this.visibleColumns = visibleColumns;
		this.othersView = othersView;
		this.anonOpen = anonOpen;
		this.notifyTypes = notifyTypes;
		this.notifySubject = notifySubject;
		this.langCode = langCode;
		this.commentClosed = commentClosed;
		this.autoAssign = autoAssign;
		this.lastUpdated = lastUpdated;
		this.defaultEntry = defaultEntry;
	}

	public TrackerProjectsDto(String projectTitle, String themeStyle,
			int defaultCatOwner, String introMessage, int projectIsActive,
			String visibleColumns, int othersView, int anonOpen,
			String notifyEmail, String notifyJabber, String notifyReply,
			String notifyTypes, String feedImgUrl, String feedDescription,
			String notifySubject, String langCode, int commentClosed,
			int autoAssign, int lastUpdated, String defaultTask,
			String defaultEntry) {
		this.projectTitle = projectTitle;
		this.themeStyle = themeStyle;
		this.defaultCatOwner = defaultCatOwner;
		this.introMessage = introMessage;
		this.projectIsActive = projectIsActive;
		this.visibleColumns = visibleColumns;
		this.othersView = othersView;
		this.anonOpen = anonOpen;
		this.notifyEmail = notifyEmail;
		this.notifyJabber = notifyJabber;
		this.notifyReply = notifyReply;
		this.notifyTypes = notifyTypes;
		this.feedImgUrl = feedImgUrl;
		this.feedDescription = feedDescription;
		this.notifySubject = notifySubject;
		this.langCode = langCode;
		this.commentClosed = commentClosed;
		this.autoAssign = autoAssign;
		this.lastUpdated = lastUpdated;
		this.defaultTask = defaultTask;
		this.defaultEntry = defaultEntry;
	}

	public Integer getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getProjectTitle() {
		return this.projectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}

	public String getThemeStyle() {
		return this.themeStyle;
	}

	public void setThemeStyle(String themeStyle) {
		this.themeStyle = themeStyle;
	}

	public int getDefaultCatOwner() {
		return this.defaultCatOwner;
	}

	public void setDefaultCatOwner(int defaultCatOwner) {
		this.defaultCatOwner = defaultCatOwner;
	}

	public String getIntroMessage() {
		return this.introMessage;
	}

	public void setIntroMessage(String introMessage) {
		this.introMessage = introMessage;
	}

	public int getProjectIsActive() {
		return this.projectIsActive;
	}

	public void setProjectIsActive(int projectIsActive) {
		this.projectIsActive = projectIsActive;
	}

	public String getVisibleColumns() {
		return this.visibleColumns;
	}

	public void setVisibleColumns(String visibleColumns) {
		this.visibleColumns = visibleColumns;
	}

	public int getOthersView() {
		return this.othersView;
	}

	public void setOthersView(int othersView) {
		this.othersView = othersView;
	}

	public int getAnonOpen() {
		return this.anonOpen;
	}

	public void setAnonOpen(int anonOpen) {
		this.anonOpen = anonOpen;
	}

	public String getNotifyEmail() {
		return this.notifyEmail;
	}

	public void setNotifyEmail(String notifyEmail) {
		this.notifyEmail = notifyEmail;
	}

	public String getNotifyJabber() {
		return this.notifyJabber;
	}

	public void setNotifyJabber(String notifyJabber) {
		this.notifyJabber = notifyJabber;
	}

	public String getNotifyReply() {
		return this.notifyReply;
	}

	public void setNotifyReply(String notifyReply) {
		this.notifyReply = notifyReply;
	}

	public String getNotifyTypes() {
		return this.notifyTypes;
	}

	public void setNotifyTypes(String notifyTypes) {
		this.notifyTypes = notifyTypes;
	}

	public String getFeedImgUrl() {
		return this.feedImgUrl;
	}

	public void setFeedImgUrl(String feedImgUrl) {
		this.feedImgUrl = feedImgUrl;
	}

	public String getFeedDescription() {
		return this.feedDescription;
	}

	public void setFeedDescription(String feedDescription) {
		this.feedDescription = feedDescription;
	}

	public String getNotifySubject() {
		return this.notifySubject;
	}

	public void setNotifySubject(String notifySubject) {
		this.notifySubject = notifySubject;
	}

	public String getLangCode() {
		return this.langCode;
	}

	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}

	public int getCommentClosed() {
		return this.commentClosed;
	}

	public void setCommentClosed(int commentClosed) {
		this.commentClosed = commentClosed;
	}

	public int getAutoAssign() {
		return this.autoAssign;
	}

	public void setAutoAssign(int autoAssign) {
		this.autoAssign = autoAssign;
	}

	public int getLastUpdated() {
		return this.lastUpdated;
	}

	public void setLastUpdated(int lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getDefaultTask() {
		return this.defaultTask;
	}

	public void setDefaultTask(String defaultTask) {
		this.defaultTask = defaultTask;
	}

	public String getDefaultEntry() {
		return this.defaultEntry;
	}

	public void setDefaultEntry(String defaultEntry) {
		this.defaultEntry = defaultEntry;
	}

}
