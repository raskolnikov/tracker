package com.m2yazilim.tracker.proxy;

import java.util.Locale;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Level;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.m2yazilim.tracker.constants.ErrorCodes;
import com.m2yazilim.tracker.logging.Trace;
import com.m2yazilim.tracker.model.ServiceResponse;
import com.m2yazilim.tracker.model.UserLoginInfo;
import com.m2yazilim.tracker.model.UserLoginOutput;
import com.m2yazilim.tracker.proxy.helper.TrackerLoginHelper;
import com.m2yazilim.tracker.shell.ServiceResponseShell;
import com.m2yazilim.tracker.util.TrackerException;

@Service
@Transactional
@JsonAutoDetect
@Path("/TrackerLoginService")
public class TrackerLoginService {
	private String loggerName;
	private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Transactional
	public ServiceResponseShell login(UserLoginInfo userLoginInfo) {

		ServiceResponseShell serviceResponseShell = new ServiceResponseShell();
		ServiceResponse<UserLoginOutput> serviceResponse = new ServiceResponse<UserLoginOutput>();

		try {
			// token yasam suresi saniye cinsinden
			long tokenLiveTimeInSecond = Long.valueOf(reloadableResourceBundleMessageSource.getMessage(
					"sessionTokenLiveTimeInSecond", new Object[0], Locale.getDefault()));
			UserLoginOutput userLoginOutput = TrackerLoginHelper.userLoginControl(userLoginInfo, tokenLiveTimeInSecond);

			serviceResponse.setResult(userLoginOutput);
			serviceResponse.setStatusSuccess(true);
			serviceResponseShell.setServiceResponse(serviceResponse);

		} catch (TrackerException e) {
			serviceResponse.setStatusSuccess(false);
			serviceResponse.setErrorCode(e.getMessage());
			Trace.getInstance(getLoggerAppName()).error(Level.ERROR, e);

		} catch (Exception e) {
			serviceResponse.setStatusSuccess(false);
			serviceResponse.setErrorCode(ErrorCodes.ERROR_GENERAL);
			Trace.getInstance(getLoggerAppName()).error(Level.ERROR, e);
		}
		serviceResponseShell.setServiceResponse(serviceResponse);
		return serviceResponseShell;

	}

	@Autowired
	public void setLoggerName(String loggerName) {
		this.loggerName = loggerName;
	}

	public String getLoggerAppName() {
		return Trace.LOGGER_APP_PRE + this.loggerName;
	}

	public ReloadableResourceBundleMessageSource getReloadableResourceBundleMessageSource() {
		return reloadableResourceBundleMessageSource;
	}

	@Autowired
	public void setReloadableResourceBundleMessageSource(
			ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource) {
		this.reloadableResourceBundleMessageSource = reloadableResourceBundleMessageSource;
	}
}
