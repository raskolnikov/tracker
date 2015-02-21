package com.m2yazilim.tracker.dto;

// Generated Nov 21, 2012 12:58:59 PM by Hibernate Tools 4.0.0

/**
 * TrackerSearchesDto generated by hbm2java
 */
public class TrackerSearchesDto implements java.io.Serializable {

	private Integer id;
	private int version;
	private int userId;
	private String name;
	private String searchString;
	private int time;

	public TrackerSearchesDto() {
	}

	public TrackerSearchesDto(int userId, String name, int time) {
		this.userId = userId;
		this.name = name;
		this.time = time;
	}

	public TrackerSearchesDto(int userId, String name, String searchString,
			int time) {
		this.userId = userId;
		this.name = name;
		this.searchString = searchString;
		this.time = time;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSearchString() {
		return this.searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	public int getTime() {
		return this.time;
	}

	public void setTime(int time) {
		this.time = time;
	}

}