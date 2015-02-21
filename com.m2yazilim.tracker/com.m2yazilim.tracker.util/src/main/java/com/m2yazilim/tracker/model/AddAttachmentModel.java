package com.m2yazilim.tracker.model;

import org.apache.commons.fileupload.FileItem;


public class AddAttachmentModel {

	private Crud attachmentCrud;
	private Crud commentCrud;
	private FileItem fileItem;

	public Crud getAttachmentCrud() {
		return attachmentCrud;
	}

	public void setAttachmentCrud(Crud attachmentCrud) {
		this.attachmentCrud = attachmentCrud;
	}

	public FileItem getFileItem() {
		return fileItem;
	}

	public void setFileItem(FileItem fileItem) {
		this.fileItem = fileItem;
	}

	public Crud getCommentCrud() {
		return commentCrud;
	}

	public void setCommentCrud(Crud commentCrud) {
		this.commentCrud = commentCrud;
	}
}
