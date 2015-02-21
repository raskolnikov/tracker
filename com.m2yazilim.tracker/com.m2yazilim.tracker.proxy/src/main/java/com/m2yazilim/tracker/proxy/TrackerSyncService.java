package com.m2yazilim.tracker.proxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Level;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.m2yazilim.tracker.constants.ErrorCodes;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerComments;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerTasks;
import com.m2yazilim.tracker.logging.Trace;
import com.m2yazilim.tracker.model.ServiceResponse;
import com.m2yazilim.tracker.model.SsoRole;
import com.m2yazilim.tracker.proxy.helper.TrackerSyncHelper;
import com.m2yazilim.tracker.shell.ServiceResponseShell;
import com.m2yazilim.tracker.util.TrackerException;
import com.m2yazilim.tracker.util.TrackerSessionUtil;

@Service
@Transactional
@JsonAutoDetect
@Path("/TrackerSyncService")
public class TrackerSyncService {
	private String loggerName;

	@GET
	@Path("/getTableRowVersionMap")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Transactional
	public ServiceResponseShell getTableRowVersionMap() {

		ServiceResponseShell serviceResponseShell = new ServiceResponseShell();
		ServiceResponse<HashMap<String, ArrayList<String>>> serviceResponse = new ServiceResponse<HashMap<String, ArrayList<String>>>();
		try {

			SsoRole role = TrackerSessionUtil.getSsoRole();

			HashMap<String, ArrayList<String>> lightTableVersionList = TrackerSyncHelper.getLightTableVersionList(role.getTrackerUser().getId().toString());

			serviceResponse.setResult(lightTableVersionList);
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

	@POST
	@Path("/getTableRowData")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Transactional
	public ServiceResponseShell getTableRowData(HashMap<String, ArrayList<String>> tableRowMap) {

		// simulate
		// HashMap<String, ArrayList<String>> tableRowMap = new HashMap<String,
		// ArrayList<String>>();
		// ArrayList<String> idList = new ArrayList<String>();
		// idList.add("1");
		// idList.add("2");
		// tableRowMap.put(TrackerTasks.class.getSimpleName(), idList);
		// tableRowMap.put(TrackerComments.class.getSimpleName(), idList);

		ServiceResponseShell serviceResponseShell = new ServiceResponseShell();
		ServiceResponse<HashMap<String, List<?>>> serviceResponse = new ServiceResponse<HashMap<String, List<?>>>();
		try {
			SsoRole role = TrackerSessionUtil.getSsoRole();
			HashMap<String, List<?>> userDataByTable = TrackerSyncHelper.getUserDataByTable(tableRowMap,
					role.getTrackerUser().getId().toString());

			serviceResponse.setResult(userDataByTable);
			serviceResponse.setStatusSuccess(true);

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
