package com.m2yazilim.tracker.model;

import java.io.Serializable;

public class UserLoginOutput implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9127665754286899912L;
	private String token;
	private int userId;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
