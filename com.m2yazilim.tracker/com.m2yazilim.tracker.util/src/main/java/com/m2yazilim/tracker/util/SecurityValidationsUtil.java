package com.m2yazilim.tracker.util;

public class SecurityValidationsUtil {

	
	public static boolean validateGSM(String gsm) {

		if (gsm == null || !gsm.matches("^5[0-9]{9}")) {
			return false;
		}

		return true;
	}
	
	
	public static boolean validateGeneric(String str) {

		if (str == null) {
			return false;
		}

		String exp= "^[0-9A-Za-zğüşıöçĞÜŞİÖÇ&,. _+|-]+$";
		if (str.matches(exp)) {
			return true;
		}
		return false;
	}
	
	
	
	
}
