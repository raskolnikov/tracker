package com.m2yazilim.tracker.proxy;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.m2yazilim.tracker.logging.Trace;
import com.m2yazilim.tracker.model.ServiceResponse;
import com.m2yazilim.tracker.shell.ServiceResponseShell;

@Service
@Transactional
@JsonAutoDetect
@Path("/UnAuthorizedService")
public class UnAuthorizedService {
	private String loggerName;
	private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;

	@GET
	@Path("/noPermission/{errorCode}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Transactional
	public ServiceResponseShell noPermission(@PathParam("errorCode") String errorCode) {

		ServiceResponseShell serviceResponseShell = new ServiceResponseShell();
		ServiceResponse<String> serviceResponse = new ServiceResponse<String>();
		serviceResponse.setErrorCode(errorCode);
		serviceResponse.setResult("invalid request");
		serviceResponse.setStatusSuccess(false);
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
