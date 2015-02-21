package com.m2yazilim.tracker.model;

import java.io.Serializable;

public class WSStatusListModel implements Serializable{
	private static final long serialVersionUID = 1L;
	private boolean successEmail;
	private boolean successMail;
	private boolean successMOF;
	
	public boolean allSuccess() {
		return (successEmail && successMail && successMOF);
	}
	
	public void setSuccessEmail(boolean successEmail) {
		this.successEmail = successEmail;
	}
	public boolean isSuccessEmail() {
		return successEmail;
	}
	public void setSuccessMail(boolean successMail) {
		this.successMail = successMail;
	}
	public boolean isSuccessMail() {
		return successMail;
	}
	public void setSuccessMOF(boolean successMOF) {
		this.successMOF = successMOF;
	}
	public boolean isSuccessMOF() {
		return successMOF;
	}
	
	//private String msg = new String();

}
