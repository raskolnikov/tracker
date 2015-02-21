package com.m2yazilim.tracker.util;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.text.StrTokenizer;

public class IpUtils {

	public static final String _255 = "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";

	public static final Pattern pattern = Pattern.compile("^(?:" + _255 + "\\.){3}" + _255 + "$");

	public static String longToIpV4(long longIp) {

		int octet3 = (int) ((longIp >> 24) % 256);

		int octet2 = (int) ((longIp >> 16) % 256);

		int octet1 = (int) ((longIp >> 8) % 256);

		int octet0 = (int) ((longIp) % 256);

		return octet3 + "." + octet2 + "." + octet1 + "." + octet0;

	}

	public static long ipV4ToLong(String ip) {

		String[] octets = ip.split("\\.");

		return (Long.parseLong(octets[0]) << 24) + (Integer.parseInt(octets[1]) << 16) +

		(Integer.parseInt(octets[2]) << 8) + Integer.parseInt(octets[3]);

	}

	public static boolean isIPv4Valid(String ip) {

		return pattern.matcher(ip).matches();

	}

	public static String getIpFromRequest(HttpServletRequest request) {

		String ip;

		boolean found = false;

		if (request.getHeader("CLIENT-IP") != null) {
			ip = request.getHeader("CLIENT-IP");
		} else if (request.getHeader("x-forwarded-for") != null) {
			ip = request.getHeader("x-forwarded-for");
		}

		else if (request.getHeader("HTTP-X-FORWARDED_FOR") != null) {
			ip = request.getHeader("HTTP-X-FORWARDED_FOR");
		}

		else if (request.getHeader("HTTP-CLIENT-IP") != null) {
			ip = request.getHeader("HTTP-CLIENT-IP");
		}

		else {
			ip = request.getRemoteAddr();
		}

		if (ip != null) {

			StrTokenizer tokenizer = new StrTokenizer(ip, ",");

			while (tokenizer.hasNext()) {

				ip = tokenizer.nextToken().trim();

				if (isIPv4Valid(ip)) {

					found = true;

					break;

				}

			}
		}
		if (!found) {

			ip = request.getRemoteAddr();

		}

		return ip;

	}

}