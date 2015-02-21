package com.m2yazilim.tracker.service.messages;

import java.util.List;

import org.apache.commons.fileupload.FileItem;

import com.m2yazilim.tracker.messages.base.RequestMessage;
import com.m2yazilim.tracker.model.AddAttachmentModel;
import com.m2yazilim.tracker.model.Crud;

public class AddTrackerAttachmentRequest extends RequestMessage {

	private static final long serialVersionUID = 1L;
	private int userId;
	private Crud commentCrud;
	private List<AddAttachmentModel> addAttachmentModelList;
	private String fileUploadLocation;

	private List<FileItem> fileItemList;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<FileItem> getFileItemList() {
		return fileItemList;
	}

	public void setFileItemList(List<FileItem> fileItemList) {
		this.fileItemList = fileItemList;
	}

	public List<AddAttachmentModel> getAddAttachmentModelList() {
		return addAttachmentModelList;
	}

	public void setAddAttachmentModelList(List<AddAttachmentModel> addAttachmentModelList) {
		this.addAttachmentModelList = addAttachmentModelList;
	}

	public Crud getCommentCrud() {
		return commentCrud;
	}

	public void setCommentCrud(Crud commentCrud) {
		this.commentCrud = commentCrud;
	}

	public String getFileUploadLocation() {
		return fileUploadLocation;
	}

	public void setFileUploadLocation(String fileUploadLocation) {
		this.fileUploadLocation = fileUploadLocation;
	}

}
