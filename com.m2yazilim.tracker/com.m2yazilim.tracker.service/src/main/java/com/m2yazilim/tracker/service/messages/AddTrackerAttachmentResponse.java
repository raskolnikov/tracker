package com.m2yazilim.tracker.service.messages;

import java.util.List;

import com.m2yazilim.tracker.messages.base.ResponseMessage;
import com.m2yazilim.tracker.model.CrudOutputModel;

public class AddTrackerAttachmentResponse extends ResponseMessage {

	private static final long serialVersionUID = 1L;
	private List<CrudOutputModel> crudOutputModelList;

	public List<CrudOutputModel> getCrudOutputModelList() {
		return crudOutputModelList;
	}

	public void setCrudOutputModelList(List<CrudOutputModel> crudOutputModelList) {
		this.crudOutputModelList = crudOutputModelList;
	}

}
