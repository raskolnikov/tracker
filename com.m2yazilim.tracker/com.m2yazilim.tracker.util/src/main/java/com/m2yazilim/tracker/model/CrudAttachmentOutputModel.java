package com.m2yazilim.tracker.model;

import java.util.List;

public class CrudAttachmentOutputModel {

	private int commentId;
	private List<Integer> attachmentIdList;
	private int crudId;
	private String returnCode;

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public int getCrudId() {
		return crudId;
	}

	public void setCrudId(int crudId) {
		this.crudId = crudId;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public List<Integer> getAttachmentIdList() {
		return attachmentIdList;
	}

	public void setAttachmentIdList(List<Integer> attachmentIdList) {
		this.attachmentIdList = attachmentIdList;
	}

}
