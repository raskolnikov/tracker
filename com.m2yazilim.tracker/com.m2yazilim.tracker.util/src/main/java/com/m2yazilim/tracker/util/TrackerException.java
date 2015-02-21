package com.m2yazilim.tracker.util;

public class TrackerException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5954371595321158085L;

	public TrackerException(String message) {
		super(message);
	}

	public TrackerException(String message, Throwable throwable) {
		super(message, throwable);
	}

}