package com.m2yazilim.tracker.service;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Level;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.m2yazilim.tracker.constants.ErrorCodes;
import com.m2yazilim.tracker.logging.Trace;
import com.m2yazilim.tracker.model.ServiceResponse;
import com.m2yazilim.tracker.model.SsoRole;
import com.m2yazilim.tracker.service.helper.TrackerSyncHelper;
import com.m2yazilim.tracker.shell.ServiceResponseShell;
import com.m2yazilim.tracker.util.TrackerSessionUtil;

@Service
@Transactional
@JsonAutoDetect
@Path("/teamBranchService")
public class TeamBranchService {
	private String loggerName;
	private JdbcTemplate jdbcTemplateIssueTrackerDataSource;
	private SessionFactory sessionFactory;

	@GET
	@Path("/userAllData")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Transactional
	public ServiceResponseShell getUserAllData() throws Exception {

		ServiceResponseShell serviceResponseShell = new ServiceResponseShell();
		ServiceResponse<HashMap<String, List<?>>> serviceResponse = new ServiceResponse<HashMap<String, List<?>>>();
		try {
			SsoRole role = TrackerSessionUtil.getSsoRole();
			HashMap<String, List<?>> returnHashMap = TrackerSyncHelper.getUserAllDataByUserId(role.getTrackerUser().getId().toString());

			serviceResponse.setResult(returnHashMap);
			serviceResponse.setStatusSuccess(true);

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

	public JdbcTemplate getJdbcTemplateIssueTrackerDataSource() {
		return jdbcTemplateIssueTrackerDataSource;
	}

	/*
	 * @Autowired public void setIssueTrackerDao(IIssueTrackerDao
	 * issueTrackerDao) { this.issueTrackerDao=issueTrackerDao; }
	 */

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
