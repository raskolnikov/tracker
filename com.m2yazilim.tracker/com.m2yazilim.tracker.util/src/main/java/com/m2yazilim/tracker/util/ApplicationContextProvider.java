package com.m2yazilim.tracker.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextProvider implements ApplicationContextAware {

	private static ApplicationContext applicationContext=null;
	
	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
       applicationContext = context;		

	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	

}
