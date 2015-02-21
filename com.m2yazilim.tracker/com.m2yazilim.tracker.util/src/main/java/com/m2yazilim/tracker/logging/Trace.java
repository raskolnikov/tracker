package com.m2yazilim.tracker.logging;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.context.WebApplicationContext;

import com.m2yazilim.tracker.util.ApplicationContextProvider;


public class Trace {
	
	private static final String LOGGER_PRE = "com.vodafone.online.";
	public static final String LOGGER_SEC_PRE = LOGGER_PRE + "security.";
	public static final String LOGGER_APP_PRE = LOGGER_PRE + "app.";
	public static final String LOGGER_BOTLOG = LOGGER_PRE + "botlog.";
	
	public static final String LOGGER_BASE = "base";

	private static Map<String, Object> instanceMap = new HashMap<String, Object>();
		
	public static String LOG_STATUS_SUCCESS = "SUCCESS";
	public static String LOG_STATUS_FAILED = "FAILED";
	
	private static final String BEAN_NAME = "reloadableResourceBundleMessageSource";
	private static ReloadableResourceBundleMessageSource RESOURCE_BUNDLE = null;
	
	private Logger logger = null;
	
    private Trace(Logger logger) {
    	super();
    	this.logger = logger;
    }
    
	public static synchronized Trace getInstance(String loggerName) {
		
		String standartLoggerName = "com.vodafone.online.app.onlineportal";
		if(loggerName.contains(".security.")){
			standartLoggerName = "com.vodafone.online.security.onlineportal";
		}else if(loggerName.contains(".botlog.")){
			standartLoggerName = "com.vodafone.online.botlog";
		}
			
		if(instanceMap.containsKey(standartLoggerName)) {	
			return (Trace) instanceMap.get(standartLoggerName);
		}else {
			int webAppNameIndex = loggerName.lastIndexOf(".");
			
			String webAppName = "";
			if(webAppNameIndex != -1 && loggerName.length() > webAppNameIndex + 1){
				webAppName = loggerName.substring(webAppNameIndex+1);
			}
			
			//java.net.URL url=Thread.currentThread().getContextClassLoader().getResource("log4j.xml");			
			DOMConfigurator.configure(getResourceByName("onlinePortalLog4jPath"));			
			Trace logUtil = new Trace(Logger.getLogger(standartLoggerName));
			instanceMap.put(standartLoggerName, logUtil);    
			instanceMap.put("webAppName", "applicationName:" + webAppName + " ");    	
			return logUtil;
		}
	}
	
	public static String getResourceByName(String resourceName){
		ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource = getResourceBundle();			
		return reloadableResourceBundleMessageSource.getMessage(resourceName, new Object[0], Locale.getDefault());				
	}
	
	private static ReloadableResourceBundleMessageSource getResourceBundle(){
		if(RESOURCE_BUNDLE == null){
			synchronized (BEAN_NAME) {		
				if (ApplicationContextProvider.getApplicationContext() instanceof WebApplicationContext) {
					WebApplicationContext wac = (WebApplicationContext) ApplicationContextProvider.getApplicationContext();		
					RESOURCE_BUNDLE =  (ReloadableResourceBundleMessageSource)wac.getBean(BEAN_NAME);	
				} else if (ApplicationContextProvider.getApplicationContext() instanceof GenericApplicationContext) {
					GenericApplicationContext wac = (GenericApplicationContext) ApplicationContextProvider.getApplicationContext();		
					RESOURCE_BUNDLE =  (ReloadableResourceBundleMessageSource)wac.getBean(BEAN_NAME);	
				}
			}
		}
		return RESOURCE_BUNDLE;
	}
	
	
	private String getWebAppName(){
		String webAppName = (String) instanceMap.get("webAppName");
		webAppName = webAppName != null ? webAppName : "";
		return webAppName;
	}
	public Logger getCurrentLogger(){
		return this.logger;
	}
	
	public void log(Level level, String msg)
	{		
		logger.log(level, getWebAppName() + msg );
	}
	
	public void log(Level level, String msg, Throwable t)
	{
		logger.log(level,  getWebAppName() + msg, t);
	}

    public void info(Object message) {
    	logger.info( getWebAppName() + message);
    }
    
    public void info(Object message, Throwable throwable) {
    	logger.info(getWebAppName() + message, throwable);
    }
    
    public void warn(Object message) {
    	logger.warn(getWebAppName() + message);
    }
    
    public void warn(Object message, Throwable throwable) {
    	logger.warn(getWebAppName() + message, throwable);
    }
    
    public void debug(Object message) {
    	logger.debug(getWebAppName() + message);
    }
    
    public void debug(Object message, Throwable throwable) {
    	logger.debug(getWebAppName() + message, throwable);
    }
    
    public void error(Object message) {
    	logger.error(getWebAppName() + message);
    }
    
    public void error(Object message, Throwable throwable) {
    	logger.error(getWebAppName() + message, throwable);
    }
    
    public void fatal(Object message) {
    	logger.fatal(getWebAppName() + message);
    }
    
    public void fatal(Object message, Throwable throwable) {
    	logger.fatal(getWebAppName() + message, throwable);
    }
}
