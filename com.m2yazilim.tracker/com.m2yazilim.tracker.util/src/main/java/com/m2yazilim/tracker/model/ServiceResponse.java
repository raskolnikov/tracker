package com.m2yazilim.tracker.model;

import java.io.Serializable;

public class ServiceResponse <T> implements Serializable{
	private static final long serialVersionUID = 1L;
	private T result;
	private Boolean statusSuccess;
	private String errorCode;
	private String errorDescription;
	
	public ServiceResponse() {
		// TODO Auto-generated constructor stub
	}
	public ServiceResponse(T result) {
		this.result=result;
	}
	public T getResult() {
		return result;
	}
	public void setResult(T result) {
		this.result = result;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorDescription() {
		return errorDescription;
	}
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	public Boolean isStatusSuccess() {
		return statusSuccess;
	}
	public void setStatusSuccess(Boolean statusSuccess) {
		this.statusSuccess = statusSuccess;
	}
}
