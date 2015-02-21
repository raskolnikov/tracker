package com.m2yazilim.tracker.util;

import java.security.MessageDigest;
import java.util.List;

public class StringFunctions {

	public static String listToString(List<String> list, String delimiter) {
		if (list == null) {
			return "";
		}
		String strList = new String();

		int len = list.size();
		if (len > 0) {
			strList += list.get(0);
		}
		for (int i = 1; i < len; i++) {
			strList = strList + ";" + list.get(i);
		}
		return strList;
	}

	public static String stringToMd5Hash(String originalString) throws Exception {

		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(originalString.getBytes());

		byte byteData[] = md.digest();

		// convert the byte to hex format method 1
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}

		System.out.println("Digest(in hex format):: " + sb.toString());

		// convert the byte to hex format method 2
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			String hex = Integer.toHexString(0xff & byteData[i]);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}

}
