package com.m2yazilim.tracker.model;

public class CrudOutputModel {

	private int crudId;
	private int affectedId;
	private String returnCode;

	public int getCrudId() {
		return crudId;
	}

	public void setCrudId(int crudId) {
		this.crudId = crudId;
	}

	public int getAffectedId() {
		return affectedId;
	}

	public void setAffectedId(int affectedId) {
		this.affectedId = affectedId;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

}
