package com.m2yazilim.tracker.util;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.m2yazilim.tracker.model.SsoRole;

public class TrackerSessionUtil {

	public static long calculateExpireDate(long ts, long tokenLiveTimeInSecond) {

		Calendar cal = Calendar.getInstance(); // creates calendar
		cal.setTimeInMillis(ts);
		cal.add(Calendar.SECOND, (int) tokenLiveTimeInSecond); // adds second
		cal.getTime(); // returns new date object, one hour in the future
		return cal.getTimeInMillis();
	}

	public static SsoRole getSsoRole() {

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder

		.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		SsoRole role = (SsoRole) session.getAttribute("ssoRole");
		return role;
	}

}
