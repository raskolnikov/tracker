package com.m2yazilim.tracker.constants;

public class TrackerConstants {

	public static final String OPERATION_SUCCEED = "0";
	public static final String SERVICE_CALL_SOURCE_NAME_WEB = "WEB";
	public static final String NON_ALPHAANDSPACE_REGEX = "[^A-Z^a-z^şŞıİçÇöÖüÜĞğ\\s]";
	public static final String ALPHAANDSPACE_REGEX = "^[A-Za-zşŞıİçÇöÖüÜĞğ\\s]+$";
	public static final String PERSON_NAME_REGEX = "^[A-Za-zşŞıİçÇöÖüÜĞğ\\s.]+$";
	public static final String ALPHAANDDIGIT_REGEX = "^[A-Za-zşŞıİçÇöÖüÜĞğ0-9]+$";
	public static final String NON_DIGIT_REGEX = "[^\\d]";
	public static final String DIGIT_REGEX = "^[\\d]+$";
	public static final String MANY_SPACE_IN_MIDDLE = "( )+";
	public static final String SPACE_IN_THE_BEGINNING = "^ +";
	public static final String LONG_SMS_MESSAGE_REGEX = "^[A-Za-z0-9ğüşıöçĞÜŞİÖÇ?,;.:\\s]+$";
	public static final String ID_GIF = "Gif";
	public static final String STYLE_CLASS_ERROR_VALIDATED = "error validated";
	public static final String EMAIL_VALIDATION_REGEX = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*(\\.[_A-Za-z0-9-]+)";
	public static final String ALPHAANDDIGIT = "[A-Za-zşŞıİçÇöÖüÜĞğ0-9]+";
	public static final String NAME_VALIDATION_REGEX = "[A-Za-zşŞıİçÇöÖüÜĞğ .]+";
	public static final String PUSH_TYPE_SYNC = "1";
	public static final String PUSH_TYPE_GET_ROW = "2";
	public static final int REQUEST_TYPE_CLOSE = 1;
	public static final int DEFAULT_TRACKER_DB_GENERIC_ID = 0;
	public static final int CLOSE_REQUEST_SENT = 1;

}
