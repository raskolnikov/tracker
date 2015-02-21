package com.m2yazilim.tracker.aspect;

import java.util.ArrayList;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class OnlineReflectionToStringBuilder extends ReflectionToStringBuilder {

    private static ArrayList<String> restrictedParNameList;
	private static String[]          restrictedParNameArray;
	 
    public OnlineReflectionToStringBuilder(final Object object, final ToStringStyle style) {
        super(object, style);
        this.setExcludeFieldNames(getRestrictedParNameArray());
    }
    

    public static ArrayList<String> getRestrictedParNames() {
        if (restrictedParNameList == null) {
            restrictedParNameList = new ArrayList<String>();
            restrictedParNameList.add("ccNumber");
            restrictedParNameList.add("ccExpireDate");
            restrictedParNameList.add("ccCvv2");
            restrictedParNameList.add("cvv2");
            restrictedParNameList.add("kkNo");
            restrictedParNameList.add("ccExpDate");
            restrictedParNameList.add("enteredSecurityCode");
            restrictedParNameList.add("cardNumber");
            restrictedParNameList.add("expireDateMonth");
            restrictedParNameList.add("expireDateYear");            
            restrictedParNameList.add("securityCode");
            restrictedParNameList.add("passwordAgain");
            restrictedParNameList.add("kkTarih");
            restrictedParNameList.add("kkGuvKod");
            restrictedParNameList.add("password");
            restrictedParNameList.add("currentPass");
            restrictedParNameList.add("oldPassword");
            restrictedParNameList.add("newPassword");
            restrictedParNameList.add("currentPassword");
            restrictedParNameList.add("hiddenSmsMsg");
            restrictedParNameList.add("topupMessagesImpl");

        }

        return restrictedParNameList;
    }

    public static String[] getRestrictedParNameArray() {
        if (restrictedParNameArray == null) {
            restrictedParNameArray = getRestrictedParNames().toArray(new String[0]);
        }
        return restrictedParNameArray;
    }

}
