package com.m2yazilim.tracker.dto;

// Generated Nov 21, 2012 12:58:59 PM by Hibernate Tools 4.0.0

/**
 * TrackerCityDto generated by hbm2java
 */
public class TrackerCityDto implements java.io.Serializable {

	private Integer id;
	private int version;
	private String cityName;

	public TrackerCityDto() {
	}

	public TrackerCityDto(String cityName) {
		this.cityName = cityName;
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

	public String getCityName() {
		return this.cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

}
