package com.m2yazilim.tracker.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


public class Filter implements Serializable{
	private static final long serialVersionUID = 1L;
	private Date startDate;
	private Date endDate;
	private String[] types;
	private ArrayList<String> params = new ArrayList<String>();
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String[] getTypes() {
		return types;
	}
	public void setTypes(String[] types) {
		this.types = types;
	}
	public ArrayList<String> getParams() {
		return params;
	}
	public void setParams(ArrayList<String> params) {
		this.params = params;
	}
}
