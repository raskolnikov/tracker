package com.m2yazilim.tracker.model;

public class TrackerSyncTableModel {

	private String tableObjectName;
	private String inquiryDaoClassName;
	private String tableDbName;
	private String tableDtoName;
	private String tableDaoName;
	public String DTO_PACKAGE_NAME = "com.m2yazilim.tracker.dto";
	public String SERVICE_DAO_PACKAGE_NAME = "com.m2yazilim.tracker.service.dao.impl";

	public TrackerSyncTableModel(String tableObjectName, String inquiryDaoClassName) {
		this.tableObjectName = tableObjectName;
		this.inquiryDaoClassName = inquiryDaoClassName;
	}

	public String getTableObjectName() {
		return tableObjectName;
	}

	public void setTableObjectName(String tableObjectName) {
		this.tableObjectName = tableObjectName;
	}

	public String getTableDbName() {
		tableDbName = "";
		String[] part = getTableObjectName().split("(?=\\p{Upper})");

		for (int i = 1; i < part.length; i++) {

			if (i == 1) {
				tableDbName += part[i].toLowerCase();
			} else {

				tableDbName += "_" + part[i].toLowerCase();
			}

		}
		return tableDbName;
	}

	public String getTableDtoName() {
		return getTableObjectName() + "Dto";
	}

	public String getTableDaoName() {
		return getTableObjectName() + "Dao";
	}

	public String getInquiryDaoClassName() {
		return inquiryDaoClassName;
	}

	public void setInquiryDaoClassName(String inquiryDaoClassName) {
		this.inquiryDaoClassName = inquiryDaoClassName;
	}

}
