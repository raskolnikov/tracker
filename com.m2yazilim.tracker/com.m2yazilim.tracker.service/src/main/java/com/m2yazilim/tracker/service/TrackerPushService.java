package com.m2yazilim.tracker.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Level;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.m2yazilim.tracker.constants.ErrorCodes;
import com.m2yazilim.tracker.constants.TrackerConstants;
import com.m2yazilim.tracker.dataaccess.factories.tracker.HibernateTrackerDaoFactory;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerUserSessionDao;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerUserSession;
import com.m2yazilim.tracker.logging.Trace;
import com.m2yazilim.tracker.model.ServiceResponse;
import com.m2yazilim.tracker.model.TrackerSyncTableModel;
import com.m2yazilim.tracker.service.helper.TrackerPushHelper;
import com.m2yazilim.tracker.service.helper.TrackerSyncTableListHelper;
import com.m2yazilim.tracker.shell.ServiceResponseShell;
import com.m2yazilim.tracker.util.TrackerException;

@Service
@Transactional
@JsonAutoDetect
@Path("/TrackerPushService")
public class TrackerPushService {
	private String loggerName;

	private Sender sender;

	@GET
	@Path("/pushData/{tableName}/{rowId}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Transactional
	public ServiceResponseShell getTableRowVersionMap(@PathParam("tableName") String tableName,
			@PathParam("rowId") int rowId) {

		ServiceResponseShell serviceResponseShell = new ServiceResponseShell();
		ServiceResponse<ArrayList<Integer>> serviceResponse = new ServiceResponse<ArrayList<Integer>>();

		try {
			// Geneli etkileyen bir push, sync gerektirir
			String pushType = TrackerConstants.PUSH_TYPE_SYNC;
			String tableObjectName = "-";
			for (TrackerSyncTableModel trackerSyncTableModel : TrackerSyncTableListHelper.getTrackerPushTableModelList()) {
				if (trackerSyncTableModel.getTableDbName().equals(tableName)) {
					pushType = TrackerConstants.PUSH_TYPE_GET_ROW;
					tableObjectName = trackerSyncTableModel.getTableObjectName();
				}
			}

			ArrayList<Integer> affectedUserList = TrackerPushHelper.getAffectedUserList(tableName, rowId);

			if (affectedUserList != null && affectedUserList.size() > 0) {

				sender = new Sender(TrackerPushHelper.getPushServiceKey());

				// Aktif kulanicinicilarin bulunmasi
				TrackerUserSessionDao trackerUserSessionDao = HibernateTrackerDaoFactory.getTrackerUserSessionDao();
				long currentTimeMillis = (System.currentTimeMillis());

				int expireDate = (int) (currentTimeMillis / 1000L);

				Criterion userCn = Restrictions.in("user.id", affectedUserList);
				Criterion expireTimeCn = Restrictions.ge("expireDate", expireDate);
				List<TrackerUserSession> trackerUserSessionList = trackerUserSessionDao.findByCriteria(userCn,
						expireTimeCn);

				if (trackerUserSessionList != null && trackerUserSessionList.size() > 0) {

					List<String> deviceList = new ArrayList<String>();

					for (TrackerUserSession trackerUserSession : trackerUserSessionList) {

						String registryId = trackerUserSession.getDeviceRegisterKey();

						deviceList.add(registryId);

					}

					String notificationMessage = tableObjectName + "|" + rowId + "|" + pushType;
					Trace.getInstance(getLoggerAppName()).log(Level.INFO,
							"notificationMessage  : " + notificationMessage);

					Message message = new Message.Builder().addData("message", notificationMessage).build();

					Trace.getInstance(getLoggerAppName()).log(Level.INFO, "Google push is sending ...");

					MulticastResult multicastResult = sender.send(message, deviceList, 1);

					for (Result result : multicastResult.getResults()) {

						Trace.getInstance(getLoggerAppName()).log(
								Level.INFO,
								"Google Push Result : CanonicalRegistrationId : " + result.getCanonicalRegistrationId()
										+ " MessageId : " + result.getMessageId() + " ErrorCodeName : "
										+ result.getErrorCodeName());
					}

				}
			}

			serviceResponse.setResult(affectedUserList);
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
