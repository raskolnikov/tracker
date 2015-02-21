package com.m2yazilim.tracker.service.messages;

import com.m2yazilim.tracker.messages.base.RequestMessage;

public class TrackerUserLocationInsertRequest extends RequestMessage {

	private static final long serialVersionUID = -2415013547947151808L;

	private String longitude;
	private String latitude;
	private int locationGetType;
	private String deviationShare;
	private int userId;

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public int getLocationGetType() {
		return locationGetType;
	}

	public void setLocationGetType(int locationGetType) {
		this.locationGetType = locationGetType;
	}

	public String getDeviationShare() {
		return deviationShare;
	}

	public void setDeviationShare(String deviationShare) {
		this.deviationShare = deviationShare;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
