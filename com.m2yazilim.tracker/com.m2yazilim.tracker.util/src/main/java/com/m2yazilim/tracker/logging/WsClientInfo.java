package com.m2yazilim.tracker.logging;

public class WsClientInfo {

	private static ThreadLocal<String> currentLoggerName = new ThreadLocal<String>();
	private static ThreadLocal<String> currentUser = new ThreadLocal<String>();
	private static ThreadLocal<String> currentIP = new ThreadLocal<String>();

	public static void setLoggerName(String loggerName) {
		currentLoggerName.set(loggerName);
	}

	public static String getLoggerName() {
		if (currentLoggerName.get() != null) {
			return currentLoggerName.get();
		}
		return Trace.LOGGER_APP_PRE;
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

	public static String asLoggableString() {
		String u = getUser();
		if (u == null)
			u = "N/A";
		String ip = getIP();
		if (ip == null)
			ip = "N/A";

		// return "[user:"+u + " client-ip:" + ip+"]";
		return " " + ip + " " + u + " ";
	}
}
