package com.m2yazilim.tracker.service.filter;

import java.io.IOException;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.m2yazilim.tracker.base.constants.BaseConstants;
import com.m2yazilim.tracker.constants.ErrorCodes;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerUsers;
import com.m2yazilim.tracker.logging.LogUserInfo;
import com.m2yazilim.tracker.logging.Trace;
import com.m2yazilim.tracker.logging.WsClientInfo;
import com.m2yazilim.tracker.model.SsoRole;
import com.m2yazilim.tracker.service.dao.impl.TrackerUserTokenCheckDaoImpl;
import com.m2yazilim.tracker.service.messages.TrackerUserTokenCheckRequest;
import com.m2yazilim.tracker.service.messages.TrackerUserTokenCheckResponse;
import com.m2yazilim.tracker.util.IpUtils;

/**
 * Servlet Filter implementation class SsoFilter
 */
public class SsoFilter implements Filter {

	private String loggerName = null;
	private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource = null;
	private static final String RESOURCE_BUNDLE_NAME = "reloadableResourceBundleMessageSource";
	private static final String LOGGER_BEAN_NAME = "loggerName";
	private FilterConfig filterConfig = null;
	private String randomUUid = null;

	private final static String TOKEN_REGEX = "[0-9a-zA-Z-]{0,70}";

	/**
	 * Default constructor.
	 */
	public SsoFilter() {
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
		reloadableResourceBundleMessageSource = getReloadableResourceBundle(getFilterConfig().getServletContext());
		if (request instanceof HttpServletRequest) {
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			final HttpServletResponse httpServletResponse = (HttpServletResponse) response;
			HttpSession session = httpServletRequest.getSession(true);
			String requestUrl = httpServletRequest.getRequestURI();

			if (requestUrl.matches(BaseConstants.SSO_FILTER_REGEX)) {
				chain.doFilter(request, response);
				return;
			}

			String token = "";
			String userId = "";
			String deviceRegisterKey = "";
			String status = "false";

			String user_ip = IpUtils.getIpFromRequest(httpServletRequest);
			if (user_ip != null && user_ip.length() > 0) {
				LogUserInfo.setIP(user_ip);
				WsClientInfo.setIP(user_ip);
			} else {
				LogUserInfo.setIP(httpServletRequest.getRemoteAddr());
				WsClientInfo.setIP(httpServletRequest.getRemoteAddr());
			}
			Trace.getInstance(loggerName).log(Level.INFO,
					LogUserInfo.asLoggableString() + "Sso Filter LogUserInfo IP VALUE: " + LogUserInfo.getIP());

			randomUUid = UUID.randomUUID().toString();
			LogUserInfo.setCurrentGeneratedID(randomUUid);

			SsoRole role = (SsoRole) session.getAttribute("ssoRole");
			if (role != null) {

				if (role.getUserId() == null || role.getUserId().trim().length() == 0) {
					LogUserInfo.setUser("N/A");
					WsClientInfo.setUser("N/A");
				} else {
					LogUserInfo.setUser(role.getTrackerUser().getId().toString());
					LogUserInfo.setDeviceRegisterKey(role.getDeviceRegisterKey());
					LogUserInfo.setToken(role.getToken());
					WsClientInfo.setUser(role.getTrackerUser().getId().toString());
				}
			} else {
				LogUserInfo.setProfile("N/A");
				LogUserInfo.setUser("N/A");
				LogUserInfo.setDeviceRegisterKey("N/A");
				LogUserInfo.setToken("N/A");
			}

			final String ssoHack = reloadableResourceBundleMessageSource.getMessage("onlinePortalSsoHack",
					new Object[0], Locale.getDefault());
			if ("true".equals(ssoHack)) {
				String userIdHack = "";
				if (role == null) {
					role = new SsoRole();

					session.setAttribute("userId", 1);
					session.setAttribute("ssoRole", role);
					session.setAttribute("ssoStatus", "true");

				}

				if (request.getParameter("userId") != null) {
					userIdHack = request.getParameter("userId");
				} else if (httpServletRequest.getHeader("referer") != null) {
					String referer = httpServletRequest.getHeader("referer");
					if (referer.indexOf("userId=") >= 0) {
						int firstIndex = referer.indexOf("userId=") + 7;
						String refererLastPart = referer.substring(firstIndex);
						if (refererLastPart.contains("&")) {
							int lastIndex = refererLastPart.indexOf("&");
							userIdHack = refererLastPart.substring(0, lastIndex);
						} else {
							userIdHack = refererLastPart;
						}
					}
				}
				if (userIdHack != null && userIdHack.trim().length() > 0) {
					int diezIndex = userIdHack.indexOf("#");
					if (diezIndex != -1) {
						userIdHack = userIdHack.substring(0, diezIndex);
					}
					session.setAttribute("ssoHackUserId", userIdHack);
				}
				userIdHack = String.valueOf(session.getAttribute("userId"));
				role.setUserId(userIdHack);
				TrackerUsers trackerUser = new TrackerUsers();
				trackerUser.setAccountEnabled(1);
				trackerUser.setId(Integer.parseInt(userIdHack));
				trackerUser.setEmailAddress("mehmet2aktas@gmail.com");
				role.setTrackerUser(trackerUser);
				session.setAttribute("ssoRole", role);

				chain.doFilter(request, response);
				return;
			}
			// TODO: Simulation
			token =  httpServletRequest.getHeader("token"); //"12343abc";
			userId =  httpServletRequest.getHeader("userId"); // "1";
			deviceRegisterKey = httpServletRequest.getHeader("deviceRegisterKey"); //"4567";//

			LogUserInfo.setUser(userId);
			LogUserInfo.setDeviceRegisterKey(deviceRegisterKey);
			LogUserInfo.setToken(token);

			Trace.getInstance(loggerName).log(Level.INFO,
					LogUserInfo.asLoggableString() + "*** Entering SSO filter " + request);
			Trace.getInstance(loggerName).log(
					Level.INFO,
					LogUserInfo.asLoggableString() + "Sso Filter Start Time For Uuid: " + randomUUid
							+ " CurrentTimeMill: " + System.currentTimeMillis());

			session = isValidRequest(httpServletRequest, session, token);

			Trace.getInstance(loggerName).log(
					Level.INFO,
					LogUserInfo.asLoggableString() + "*** SSO filter getFromParameter: Token:" + token + " UserId: "
							+ userId + " DeviceRegisterKey: " + deviceRegisterKey);

			try {
				Trace.getInstance(loggerName).log(Level.INFO,
						LogUserInfo.asLoggableString() + "*** SSO Request - IP Address: " + user_ip);

				boolean checkSsoNeeded = cacheSSO(session);
				if (checkSsoNeeded) {
					TrackerUserTokenCheckResponse trackerUserTokenCheckResponse = userTokenCheckInquiry(token,
							deviceRegisterKey, userId, session);

					if (trackerUserTokenCheckResponse != null && trackerUserTokenCheckResponse.isTokenValid()) {
						SsoRole ssoRole = new SsoRole();
						ssoRole.setDeviceRegisterKey(deviceRegisterKey);
						ssoRole.setToken(token);
						ssoRole.setTrackerUser(trackerUserTokenCheckResponse.getTrackerUser());
						ssoRole.setUserId(userId);
						session.setAttribute("ssoRole", ssoRole);
						status = "true";

					}
				} else {
					String sessionSSoStatus = (String) session.getAttribute("ssoStatus");
					status = sessionSSoStatus != null && sessionSSoStatus.trim().length() > 0 ? sessionSSoStatus
							: "false";
				}

			} catch (Exception exception) {
				Trace.getInstance(loggerName).log(Level.INFO,
						LogUserInfo.asLoggableString() + "*** SSO Response EXCEPTION");
				Trace.getInstance(loggerName).error(Level.ERROR, exception);
				redirectPage(session, httpServletRequest, httpServletResponse);
				return;
			} finally {
				Trace.getInstance(loggerName).log(Level.INFO,
						LogUserInfo.asLoggableString() + "****filter sso status " + status);
				session.setAttribute("ssoStatus", status);
				session.setAttribute("tokenId", token);
			}

			role = (SsoRole) session.getAttribute("ssoRole");
			if ("false".equals(status) || role == null || token == null || token.trim().length() == 0) {
				redirectPage(session, httpServletRequest, httpServletResponse);
				return;
			}

			Trace.getInstance(loggerName).log(
					Level.INFO,
					LogUserInfo.asLoggableString() + "Sso Filter Finish Time For Uuid: " + randomUUid
							+ " CurrentTimeMill: " + System.currentTimeMillis());
			Trace.getInstance(loggerName).log(Level.INFO, LogUserInfo.asLoggableString() + "*** EXITING SSO filter");

		}
		chain.doFilter(request, response);
	}

	private void redirectPage(HttpSession session, final HttpServletRequest httpServletRequest,
			final HttpServletResponse httpServletResponse) throws IOException {
		if (session != null) {
			session.invalidate();
			session = httpServletRequest.getSession(true);
		}
		String redirectUrl = reloadableResourceBundleMessageSource.getMessage("unauthorizedPageUrl", new Object[0],
				Locale.getDefault());
		redirectUrl += ErrorCodes.ERROR_INVALID_TOKEN;

		httpServletResponse.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
		httpServletResponse.setContentType("text/html; charset=UTF-8");
		httpServletResponse.setHeader("Content-Type", "text/html; charset=UTF-8");
		httpServletResponse.setHeader("Pragma", "no-cache");
		httpServletResponse.setHeader("Cache-Control", "no-cache");
		httpServletResponse.setHeader("Location", redirectUrl);

		httpServletResponse.sendRedirect(redirectUrl);
	}

	private HttpSession isValidRequest(HttpServletRequest request, HttpSession session, String token) {
		String sessionTokenId = (String) session.getAttribute("tokenId");

		boolean isSessionNewOne = false;
		if (sessionTokenId != null && token != null) {
			if (!token.equals(sessionTokenId)) {
				session.removeAttribute("tokenId");
				session.removeAttribute("ssoRole");
				session.removeAttribute("ssoStatus");
				session = request.getSession(true);
				isSessionNewOne = true;
			}
		}

		if (!isSessionNewOne) {
			if ((request.getRequestedSessionId() != null) && !request.isRequestedSessionIdValid()) {
				session = request.getSession(true);
			}
		}

		return session;
	}

	private TrackerUserTokenCheckResponse userTokenCheckInquiry(String token, String deviceRegisterKey, String userId,
			HttpSession session) throws Exception {
		TrackerUserTokenCheckResponse response = new TrackerUserTokenCheckResponse();
		TrackerUserTokenCheckRequest request = new TrackerUserTokenCheckRequest();

		final String tokenLiveTimeInSecond = reloadableResourceBundleMessageSource.getMessage(
				"sessionTokenLiveTimeInSecond", new Object[0], Locale.getDefault());

		request.setDeviceRegisterKey(deviceRegisterKey);
		request.setToken(token);
		request.setUserId(userId);
		request.setTokenLiveTimeInSecond(tokenLiveTimeInSecond);

		TrackerUserTokenCheckDaoImpl trackerUserTokenCheckDaoImpl = new TrackerUserTokenCheckDaoImpl();
		trackerUserTokenCheckDaoImpl.execute(request, response);
		if (response.getHeader().hasError()) {
			return null;
		}

		return response;

	}

	/*
	 * private CheckSelfcareSessionR callSSO(String token, HttpSession session)
	 * {
	 * 
	 * WsClientInfo.setLoggerName(getLoggerName(getFilterConfig().getServletContext
	 * ())); ServiceFactory<WsSelfcareService, WsSelfcare> sf = new
	 * ServiceFactory<WsSelfcareService, WsSelfcare>(); String endpoint =
	 * reloadableResourceBundleMessageSource.getMessage("SSOWSNew.endpoint", new
	 * Object[0], Locale.getDefault()); String username =
	 * reloadableResourceBundleMessageSource.getMessage("SSOWSNew.user", new
	 * Object[0], Locale.getDefault()); String password =
	 * reloadableResourceBundleMessageSource.getMessage("SSOWSNew.pass", new
	 * Object[0], Locale.getDefault()); String applicationId =
	 * reloadableResourceBundleMessageSource
	 * .getMessage("SSOWSNew.applicationId", new Object[0],
	 * Locale.getDefault());
	 * 
	 * WsSelfcare ssows = sf.getPort(WsSelfcareService.class, WsSelfcare.class,
	 * endpoint);
	 * 
	 * ((BindingProvider)
	 * ssows).getRequestContext().put(BindingProvider.USERNAME_PROPERTY,
	 * username); ((BindingProvider)
	 * ssows).getRequestContext().put(BindingProvider.PASSWORD_PROPERTY,
	 * password);
	 * 
	 * ((BindingProvider)
	 * ssows).getRequestContext().put("com.sun.xml.internal.ws.request.timeout",
	 * new Integer(60000)); ((BindingProvider)
	 * ssows).getRequestContext().put("com.sun.xml.internal.ws.connect.timeout",
	 * new Integer(60000));
	 * 
	 * List<SsoExtensionParam> eprms = new ArrayList<SsoExtensionParam>();
	 * 
	 * Trace.getInstance(loggerName).log( Level.INFO,
	 * LogUserInfo.asLoggableString() +
	 * "SsoFilter Sso Call CheckSelfcareSession  Start Time For Uuid: " +
	 * randomUUid + " CurrentTimeMill: " + System.currentTimeMillis());
	 * CheckSelfcareSessionR responseSso = ssows.checkSelfcareSession(token,
	 * applicationId, eprms); Trace.getInstance(loggerName).log( Level.INFO,
	 * LogUserInfo.asLoggableString() +
	 * "SsoFilter Sso Call CheckSelfcareSession  Finish Time For Uuid: " +
	 * randomUUid + " CurrentTimeMill: " + System.currentTimeMillis());
	 * 
	 * Trace.getInstance(loggerName).log( Level.INFO,
	 * LogUserInfo.asLoggableString() +
	 * "*** SSO Response checkSelfcareSession(): isInSession: " +
	 * (responseSso.isInSession() ? "TRUE" : "FALSE") + " / Code:" +
	 * responseSso.getResultCode() + " / Description:" +
	 * responseSso.getResultDescription());
	 * 
	 * SsoRole role = (SsoRole) session.getAttribute("ssoRole");
	 * 
	 * if (role == null) { if (responseSso != null && responseSso.isInSession())
	 * { GetSelfcareInfoR responseSsoR = ssows.getSelfcareInfo(token,
	 * reloadableResourceBundleMessageSource
	 * .getMessage("SSOWSNew.applicationId", new Object[0],
	 * Locale.getDefault()), new ArrayList<SsoExtensionParam>()); if
	 * (responseSsoR != null) {
	 * 
	 * IccbCustomer iccbCustomer = responseSsoR.getIccbCustomer(); if
	 * (iccbCustomer != null) { role = new SsoRole();
	 * role.setCustomerId(iccbCustomer.getCustomerId());
	 * role.setCustomerType(iccbCustomer.getCustomerType());
	 * role.setPaymentType(iccbCustomer.getPaymentType());
	 * role.setProfile(iccbCustomer.getProfile());
	 * role.setMsisdn(iccbCustomer.getMsisdn()); role.setSessionStatus("true");
	 * role.setStartDate(iccbCustomer.getStartDate());
	 * role.setPackageName(iccbCustomer.getPackageName());
	 * role.setEmail(iccbCustomer.getEmail()); if (responseSsoR.getExt() != null
	 * && responseSsoR.getExt().size() > 0) { for (SsoExtensionParam
	 * ssoExtensionParam : responseSsoR.getExt()) { if (ssoExtensionParam !=
	 * null) {
	 * 
	 * if ("customerStatus".equals(ssoExtensionParam.getLabel())) {
	 * role.setCustomerStatus(ssoExtensionParam.getValue()); } // TODO: Bu deger
	 * paymentType alanindan // farkli bir degiskende tasinmalidir. // Oncelikle
	 * RestCall larin duzeltilmesi // gerekmektedir. // HYBRID & HYBRID_WITH_CC
	 * abonelerinin bot // log ATYPE kayitlarinin duzeltilmesi icin //
	 * yapilmistir if ("virtualbrand".equals(ssoExtensionParam.getLabel())) {
	 * role.setPaymentType(ssoExtensionParam.getValue()); }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * session.setAttribute("ssoRole", role);
	 * 
	 * }
	 * 
	 * } } }
	 * 
	 * return responseSso; }
	 */

	// ayni metoddan CustomDataBacking.java sinifinda da var!
	private String validateToken(String token) {
		if (token.matches(TOKEN_REGEX)) {
			return token;
		} else {
			return "invalid-token";
		}
	}

	private String prepareToken(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String token = "";

		if (request.getParameter("tokenId") != null) {
			token = request.getParameter("tokenId");
			Trace.getInstance(loggerName).log(Level.INFO,
					LogUserInfo.asLoggableString() + "PREPARE TOKEN FROM REQUESTGETPARAM:" + token);
		} else if (request.getHeader("referer") != null) {
			String referer = request.getHeader("referer");
			if (referer.indexOf("tokenId=") >= 0) {
				int firstIndex = referer.indexOf("tokenId=") + 8;
				String refererLastPart = referer.substring(firstIndex);
				if (refererLastPart.contains("&")) {
					int lastIndex = refererLastPart.indexOf("&");
					token = refererLastPart.substring(0, lastIndex);
				} else {
					token = refererLastPart;
				}
			}
			Trace.getInstance(loggerName).log(Level.INFO,
					LogUserInfo.asLoggableString() + "PREPARE TOKEN FROM REQUESTHEADERREFERRER:" + token);
		} else if (session.getAttribute("tokenId") != null) {
			token = (String) session.getAttribute("tokenId");
			Trace.getInstance(loggerName).log(Level.INFO,
					LogUserInfo.asLoggableString() + "PREPARE TOKEN FROM SESSION:" + token);
		}
		// token sonundaki #pageid bilgisi silinir
		if (token != null && token.contains("#")) {
			Trace.getInstance(loggerName).log(Level.INFO,
					LogUserInfo.asLoggableString() + "CLEARING #page0 FROM TOKEN:" + token);
			int lastIndex = token.indexOf("#");
			token = token.substring(0, lastIndex);
		}

		token = validateToken(token);
		Trace.getInstance(loggerName).log(Level.INFO, LogUserInfo.asLoggableString() + "RETURNING TOKEN:" + token);

		return token;
	}

	private boolean cacheSSO(HttpSession session) {
		boolean checkSSO = true;
		if (session.getAttribute("TIMER") != null
				&& !((String) session.getAttribute("TIMER")).trim().equalsIgnoreCase("")) {
			long startTime = 0;
			try {
				startTime = Long.parseLong((String) session.getAttribute("TIMER"));
			} catch (Exception e) {
				Trace.getInstance(loggerName).log(Level.WARN,
						LogUserInfo.asLoggableString() + " *** Error getting SSO Timer, each request will call sso");
			}
			long stopTime = System.currentTimeMillis();
			if (stopTime - startTime < 5000) {
				checkSSO = false;
				Trace.getInstance(loggerName).log(Level.INFO, LogUserInfo.asLoggableString() + " *** sso not checking");
			} else {
				checkSSO = true;
				session.setAttribute("TIMER", stopTime + "");
				Trace.getInstance(loggerName).log(Level.INFO, LogUserInfo.asLoggableString() + " *** sso checking");
			}
		} else {
			long stopTime = System.currentTimeMillis();
			session.setAttribute("TIMER", stopTime + "");
			Trace.getInstance(loggerName).log(Level.INFO, LogUserInfo.asLoggableString() + " *** Caching for sso");
		}
		return checkSSO;
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

	private ReloadableResourceBundleMessageSource getReloadableResourceBundle(ServletContext servletContext) {
		if (reloadableResourceBundleMessageSource == null) {
			synchronized (RESOURCE_BUNDLE_NAME) {
				WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
				reloadableResourceBundleMessageSource = (ReloadableResourceBundleMessageSource) wac.getBean(RESOURCE_BUNDLE_NAME);
			}
		}
		return reloadableResourceBundleMessageSource;
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
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
