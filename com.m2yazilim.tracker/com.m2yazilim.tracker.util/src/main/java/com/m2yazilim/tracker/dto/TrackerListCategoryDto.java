package com.m2yazilim.tracker.dto;

// Generated Nov 21, 2012 12:58:59 PM by Hibernate Tools 4.0.0

/**
 * TrackerListCategoryDto generated by hbm2java
 */
public class TrackerListCategoryDto implements java.io.Serializable {

	private Integer categoryId;
	private int version;
	private int projectId;
	private String categoryName;
	private int showInList;
	private int categoryOwner;
	private int lft;
	private int rgt;

	public TrackerListCategoryDto() {
	}

	public TrackerListCategoryDto(int projectId, String categoryName,
			int showInList, int categoryOwner, int lft, int rgt) {
		this.projectId = projectId;
		this.categoryName = categoryName;
		this.showInList = showInList;
		this.categoryOwner = categoryOwner;
		this.lft = lft;
		this.rgt = rgt;
	}

	public Integer getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getProjectId() {
		return this.projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getShowInList() {
		return this.showInList;
	}

	public void setShowInList(int showInList) {
		this.showInList = showInList;
	}

	public int getCategoryOwner() {
		return this.categoryOwner;
	}

	public void setCategoryOwner(int categoryOwner) {
		this.categoryOwner = categoryOwner;
	}

	public int getLft() {
		return this.lft;
	}

	public void setLft(int lft) {
		this.lft = lft;
	}

	public int getRgt() {
		return this.rgt;
	}

	public void setRgt(int rgt) {
		this.rgt = rgt;
	}

}