package com.m2yazilim.tracker.model;

import java.io.Serializable;
import java.util.HashMap;

public class Crud implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4110873701409358485L;
	public final static int TYPE_UPDATE = 0;
	public final static int TYPE_INSERT = 1;
	public final static int TYPE_DELETE = 2;

	private int id;
	private int type;
	private String tableName;
	private int tableID;
	private String filter;
	private int userID;
	private HashMap<String, String> keyValue;

	public HashMap<String, String> getKeyValue() {
		return keyValue;
	}

	public void setKeyValue(HashMap<String, String> keyValue) {
		this.keyValue = keyValue;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getTableID() {
		return tableID;
	}

	public void setTableID(int tableID) {
		this.tableID = tableID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

}
