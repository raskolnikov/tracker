package com.m2yazilim.tracker.logging;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogUserInfo {
	private static ThreadLocal<String> currentUser = new ThreadLocal<String>();
	private static ThreadLocal<String> currentIP = new ThreadLocal<String>();
	private static ThreadLocal<String> currentProfile = new ThreadLocal<String>();
	private static ThreadLocal<String> currentGeneratedID = new ThreadLocal<String>();
	private static ThreadLocal<String> currentToken = new ThreadLocal<String>();
	private static ThreadLocal<String> currentDeviceRegisterKey = new ThreadLocal<String>();

	public static void setToken(String token) {
		currentToken.set(token);
	}

	public static String getToken() {
		return currentToken.get();
	}

	public static void setDeviceRegisterKey(String deviceRegisterKey) {
		currentDeviceRegisterKey.set(deviceRegisterKey);
	}

	public static String getDeviceRegisterKey() {
		return currentDeviceRegisterKey.get();
	}

	public static void setUser(String user) {
		currentUser.set(user);
	}

	public static String getUser() {
		return currentUser.get();
	}

	public static void setIP(String ip) {
		currentIP.set(ip);
	}

	public static String getIP() {
		if (currentIP.get() == null) {
			return "";
		}
		return currentIP.get();
	}

	public static void setProfile(String profile) {
		currentProfile.set(profile);
	}

	public static String getProfile() {
		return currentProfile.get();
	}

	public static void setCurrentGeneratedID(String generatedId) {
		currentGeneratedID.set(generatedId);
	}

	public static String getCurrentGeneratedID() {
		return currentGeneratedID.get();
	}

	public static String asLoggableString(String transactionName, String msisdn, String status, String otherParameters) {
		String userId = getUser();
		if (userId == null)
			userId = "N/A";
		String ip = getIP();
		if (ip == null)
			ip = "";
		String profile = getProfile();
		if (profile == null)
			profile = "N/A";

		if (msisdn == null) {
			msisdn = userId;
		}
		if (status == null) {
			status = "SUCCESS";
		}

		// return "[user:"+u + " client-ip:" + ip+"]";
		return ip + " " + userId + " " + transactionName + " " + profile + " " + msisdn + " " + status + " - "
				+ otherParameters;
	}

	public static String asBotLoggableString(String eventName, String msisdn, String subscriberType, String desttel,
			String customerType) {
		String userId = getUser();

		if (userId == null)
			userId = "N/A";
		String ip = getIP();
		if (ip == null)
			ip = "";
		String profile = getProfile();
		if (profile == null)
			profile = "N/A";

		if (msisdn == null) {
			msisdn = userId;
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
		Date now = new Date();
		String eventDate = dateFormat.format(now);
		// return "[user:"+u + " client-ip:" + ip+"]";
		return eventDate + "|" + eventName + "|" + msisdn + "|" + (subscriberType == null ? "" : subscriberType) + "|"
				+ ip + "|" + desttel + "|" + (customerType == null ? "" : customerType);
	}

	public static String asLoggableString() {
		String u = getUser();
		if (u == null)
			u = "N/A";
		String ip = getIP();
		if (ip == null)
			ip = "N/A";
		String token = getToken();
		if (token == null)
			token = "N/A";

		String deviceRegisterKey = getDeviceRegisterKey();
		if (deviceRegisterKey == null)
			deviceRegisterKey = "N/A";

		// return "[user:"+u + " client-ip:" + ip+"]";
		return " ip: " + ip + " user: " + u + " token: " + token + " " + " deviceRegisterKey: " + deviceRegisterKey;
	}

}
