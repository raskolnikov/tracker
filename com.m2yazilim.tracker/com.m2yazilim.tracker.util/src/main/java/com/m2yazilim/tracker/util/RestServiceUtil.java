package com.m2yazilim.tracker.util;

import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import com.m2yazilim.tracker.logging.LogUserInfo;
import com.m2yazilim.tracker.shell.ServiceResponseShell;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class RestServiceUtil{
	
	public static <T extends ServiceResponseShell> T  restCall(MultivaluedMap<String, String> params, String restUrl, String restMethod, Class<T> classT ) {
		Client c = null; 
		
		try {
			c =	Client.create();
			c.setFollowRedirects(true);
			
			WebResource r = c.resource(restUrl);		
			T shell =  r.path(restMethod)
				.queryParams(params)
				.cookie(new Cookie("ip", LogUserInfo.getIP()))
				.cookie(new Cookie("profile", LogUserInfo.getProfile()))
				.cookie(new Cookie("user", LogUserInfo.getUser()))
				.cookie(new Cookie("generatedID", LogUserInfo.getCurrentGeneratedID()))
				.get(classT);

			return shell;
		} finally {
			if (c != null) {
				c.destroy();
			}
		}
	}

	
	public static <T extends ServiceResponseShell> T  restPostCall(MultivaluedMap<String, String> params, String restUrl, String restMethod, Class<T> classT ) {
		Client c = null; 
		
		try {
			c =	Client.create();
			c.setFollowRedirects(true);
			
			WebResource r = c.resource(restUrl);		
			T shell =  r.path(restMethod).type(MediaType.APPLICATION_FORM_URLENCODED)
				.cookie(new Cookie("ip", LogUserInfo.getIP()))
				.cookie(new Cookie("profile", LogUserInfo.getProfile()))
				.cookie(new Cookie("user", LogUserInfo.getUser()))
				.cookie(new Cookie("generatedID", LogUserInfo.getCurrentGeneratedID()))
				.post(classT,params);

			return shell;
		} finally {
			if (c != null) {
				c.destroy();
			}
		}
	}
	
}
