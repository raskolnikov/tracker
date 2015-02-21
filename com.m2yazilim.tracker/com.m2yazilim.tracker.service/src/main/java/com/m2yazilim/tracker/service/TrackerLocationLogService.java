package com.m2yazilim.tracker.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Level;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m2yazilim.tracker.constants.ErrorCodes;
import com.m2yazilim.tracker.logging.Trace;
import com.m2yazilim.tracker.model.ServiceResponse;
import com.m2yazilim.tracker.model.SsoRole;
import com.m2yazilim.tracker.service.dao.impl.TrackerUserLocationInsertDaoImpl;
import com.m2yazilim.tracker.service.messages.TrackerUserLocationInsertRequest;
import com.m2yazilim.tracker.service.messages.TrackerUserLocationInsertResponse;
import com.m2yazilim.tracker.shell.ServiceResponseShell;
import com.m2yazilim.tracker.util.TrackerException;
import com.m2yazilim.tracker.util.TrackerSessionUtil;

@Service
@JsonAutoDetect
@Path("/TrackerLocationLogService")
public class TrackerLocationLogService {
	private String loggerName;

	@GET
	@Path("/locationLog/{longitude}/{latitude}/{locationGetType}/{deviationShare}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public ServiceResponseShell locationLog(@PathParam("longitude") String longitude,
			@PathParam("latitude") String latitude, @PathParam("locationGetType") int locationGetType,
			@PathParam("deviationShare") String deviationShare) {

		ServiceResponseShell serviceResponseShell = new ServiceResponseShell();
		ServiceResponse<Boolean> serviceResponse = new ServiceResponse<Boolean>();

		try {
			SsoRole role = TrackerSessionUtil.getSsoRole();
			TrackerUserLocationInsertDaoImpl trackerUserLocationLogDao = new TrackerUserLocationInsertDaoImpl();
			TrackerUserLocationInsertRequest request = new TrackerUserLocationInsertRequest();

			TrackerUserLocationInsertResponse response = new TrackerUserLocationInsertResponse();

			request.setDeviationShare(deviationShare);
			request.setLatitude(latitude);
			request.setLocationGetType(locationGetType);
			request.setLongitude(longitude);
			request.setUserId(role.getTrackerUser().getId());

			trackerUserLocationLogDao.execute(request, response);

			if (response.getHeader().hasError()) {
				throw new TrackerException(response.getHeader().getNotificationList().get(0).getMessageCode());
			}

			serviceResponse.setResult(true);
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
}
