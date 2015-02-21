package com.m2yazilim.tracker.model;

import java.io.Serializable;

public class WSStatusModel implements Serializable{
	private static final long serialVersionUID = 1L;
	private boolean success;
	
	private String msg = new String();

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

}
