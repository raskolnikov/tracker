package com.m2yazilim.tracker.util;

import java.util.Calendar;

public class TrackerDateHelper {

	public static long calculateExpireDate(long ts, long tokenLiveTimeInSecond) {

		Calendar cal = Calendar.getInstance(); // creates calendar
		cal.setTimeInMillis(ts);
		cal.add(Calendar.SECOND, (int) tokenLiveTimeInSecond); // adds second
		cal.getTime(); // returns new date object, one hour in the future
		return cal.getTimeInMillis();
	}

}
