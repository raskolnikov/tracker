package com.m2yazilim.tracker.logging;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Servlet Filter implementation class LogFilter
 */
public class LogFilter implements Filter {

	private String loggerName = null;
	private FilterConfig filterConfig = null;
	private static final String LOGGER_BEAN_NAME = "loggerName";

	/**
	 * Default constructor.
	 */
	public LogFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		loggerName = getLoggerName(getFilterConfig().getServletContext());
		if (request instanceof HttpServletRequest) {
			/*
			 * WsClientInfo.setLoggerName(getLoggerName(getFilterConfig().
			 * getServletContext())); Cookie[] cookies = ((HttpServletRequest)
			 * request).getCookies();
			 * 
			 * for (int i = 0; cookies != null && i < cookies.length; i++) { if
			 * (cookies[i] != null && cookies[i].getName() != null) { String
			 * cookieValue = cookies[i].getValue(); if (cookieValue != null) {
			 * cookieValue = cookieValue.replaceAll(",\\$Version=1", ""); } if
			 * (cookies[i].getName().equals("ip")) {
			 * Trace.getInstance(getLoggerName
			 * (getFilterConfig().getServletContext())).log(Level.INFO,
			 * "LogFilter IP VALUE FROM COOKIE : " + cookieValue);
			 * LogUserInfo.setIP(cookieValue); WsClientInfo.setIP(cookieValue);
			 * } else if (cookies[i].getName().equals("user")) {
			 * LogUserInfo.setUser(cookieValue);
			 * WsClientInfo.setUser(cookieValue); } else if
			 * (cookies[i].getName().equals("profile")) {
			 * LogUserInfo.setProfile(cookieValue); } else if
			 * (cookies[i].getName().equals("generatedID")) {
			 * LogUserInfo.setCurrentGeneratedID(cookieValue); } } }
			 */

			Trace.getInstance(getLoggerName(getFilterConfig().getServletContext())).log(Level.INFO,
					"LogFilter LogUserInfo IP VALUE  : " + LogUserInfo.getIP());

		}
		chain.doFilter(request, response);
	}

	private String getLoggerName(ServletContext servletContext) {
		if (loggerName == null) {
			synchronized (LOGGER_BEAN_NAME) {
				WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
				loggerName = (String) wac.getBean(LOGGER_BEAN_NAME);
				loggerName = Trace.LOGGER_APP_PRE + loggerName;
			}
		}
		return loggerName;
	}

	public void init(FilterConfig fConfig) throws ServletException {
		this.filterConfig = fConfig;
	}

	public FilterConfig getFilterConfig() {
		return filterConfig;
	}

	public void setFilterConfig(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

}
