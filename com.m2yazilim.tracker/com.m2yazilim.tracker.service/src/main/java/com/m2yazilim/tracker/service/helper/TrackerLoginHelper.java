package com.m2yazilim.tracker.service.helper;

import java.security.MessageDigest;

import com.m2yazilim.tracker.model.UserLoginInfo;
import com.m2yazilim.tracker.model.UserLoginOutput;
import com.m2yazilim.tracker.service.dao.impl.TrackerUserLoginControlDaoImpl;
import com.m2yazilim.tracker.service.messages.TrackerUserLoginControlRequest;
import com.m2yazilim.tracker.service.messages.TrackerUserLoginControlResponse;
import com.m2yazilim.tracker.util.TrackerException;

public class TrackerLoginHelper {

	public static UserLoginOutput userLoginControl(UserLoginInfo userLoginInfo, long tokenLiveTimeInSecond)
			throws Exception, TrackerException {
		TrackerUserLoginControlResponse response = new TrackerUserLoginControlResponse();
		TrackerUserLoginControlRequest request = new TrackerUserLoginControlRequest();
		request.setTokenLiveTimeInSecond(tokenLiveTimeInSecond);
		request.setUserLoginInfo(userLoginInfo);
		TrackerUserLoginControlDaoImpl trackerUserLoginControlDao = new TrackerUserLoginControlDaoImpl();
		trackerUserLoginControlDao.execute(request, response);
		if (response.getHeader().hasError()) {
			throw new TrackerException(response.getHeader().getNotificationList().get(0).getMessageCode());
		}
		return response.getUserLoginOutput();
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
