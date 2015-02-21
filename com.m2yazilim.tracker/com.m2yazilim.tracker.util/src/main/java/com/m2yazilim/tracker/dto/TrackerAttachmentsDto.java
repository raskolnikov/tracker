package com.m2yazilim.tracker.dto;

// Generated Nov 21, 2012 12:58:59 PM by Hibernate Tools 4.0.0

/**
 * TrackerAttachmentsDto generated by hbm2java
 */
public class TrackerAttachmentsDto implements java.io.Serializable {

	private Integer attachmentId;
	private int version;
	private int taskId;
	private int commentId;
	private String origName;
	private String fileName;
	private String fileType;
	private int fileSize;
	private int addedBy;
	private int dateAdded;

	public TrackerAttachmentsDto() {
	}

	public TrackerAttachmentsDto(int taskId, int commentId, String origName,
			String fileName, String fileType, int fileSize, int addedBy,
			int dateAdded) {
		this.taskId = taskId;
		this.commentId = commentId;
		this.origName = origName;
		this.fileName = fileName;
		this.fileType = fileType;
		this.fileSize = fileSize;
		this.addedBy = addedBy;
		this.dateAdded = dateAdded;
	}

	public Integer getAttachmentId() {
		return this.attachmentId;
	}

	public void setAttachmentId(Integer attachmentId) {
		this.attachmentId = attachmentId;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getTaskId() {
		return this.taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public int getCommentId() {
		return this.commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getOrigName() {
		return this.origName;
	}

	public void setOrigName(String origName) {
		this.origName = origName;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return this.fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public int getFileSize() {
		return this.fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	public int getAddedBy() {
		return this.addedBy;
	}

	public void setAddedBy(int addedBy) {
		this.addedBy = addedBy;
	}

	public int getDateAdded() {
		return this.dateAdded;
	}

	public void setDateAdded(int dateAdded) {
		this.dateAdded = dateAdded;
	}

}