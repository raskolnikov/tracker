package com.m2yazilim.tracker.scheduler.push;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerTableChangeLog;
import com.m2yazilim.tracker.logging.Trace;
import com.m2yazilim.tracker.model.ServiceResponse;
import com.m2yazilim.tracker.scheduler.WorkQueue;
import com.m2yazilim.tracker.scheduler.dao.impl.UpdateUserPushTableDaoImpl;
import com.m2yazilim.tracker.scheduler.dao.impl.UserPushListInquiryDaoImpl;
import com.m2yazilim.tracker.scheduler.messages.UpdateUserPushTableRequest;
import com.m2yazilim.tracker.scheduler.messages.UpdateUserPushTableResponse;
import com.m2yazilim.tracker.scheduler.messages.UserPushListInquiryRequest;
import com.m2yazilim.tracker.scheduler.messages.UserPushListInquiryResponse;
import com.m2yazilim.tracker.shell.ServiceResponseShell;
import com.m2yazilim.tracker.util.TrackerException;

@Service
public class SchedulerPushJob {
	private String loggerName;
	public static final String CMN_OK = "00";
	public static final String THREAD_NAME = "WORKER-";
	// Thread Sayisi
	private int threadNumber;

	@Transactional
	public void printMe() {

		WorkQueue queue = new WorkQueue();

		UserPushListInquiryDaoImpl userPushListInquiryDaoImpl = new UserPushListInquiryDaoImpl();
		UserPushListInquiryRequest request = new UserPushListInquiryRequest();
		UserPushListInquiryResponse response = new UserPushListInquiryResponse();

		try {
			userPushListInquiryDaoImpl.execute(request, response);
			if (!response.getHeader().hasError()) {
				List<TrackerTableChangeLog> trackerTableChangeLogList = response.getTrackerTableChangeLogList();
				// if (true) {
				// List<TrackerTableChangeLog> trackerTableChangeLogList = new
				// ArrayList<TrackerTableChangeLog>();
				// for (int i = 0; i < 10; i++) {
				// TrackerTableChangeLog trackerTableChangeLog = new
				// TrackerTableChangeLog();
				//
				// trackerTableChangeLog.setTableName("tracker_comments");
				// trackerTableChangeLog.setTableRowId(13);
				// trackerTableChangeLogList.add(trackerTableChangeLog);
				//
				// }

				int totalPushSize = trackerTableChangeLogList.size();
				Trace.getInstance(getLoggerAppName()).log(Level.INFO,
						"Gonderilecek toplam push sayisi : " + totalPushSize);
				if (totalPushSize > 0) {

					threadNumber = Integer.parseInt(Trace.getResourceByName("schedulerPushThreadCount"));

					Worker workerThread[] = new Worker[threadNumber];
					for (int i = 0; i < threadNumber; ++i) {
						workerThread[i] = new Worker(THREAD_NAME + String.valueOf(i), queue, getLoggerAppName());
						workerThread[i].start();
					}

					// Atilacak SMS'lerin queue'ya yuklenmesi
					for (TrackerTableChangeLog trackerTableChangeLog : trackerTableChangeLogList) {
						queue.addWork(trackerTableChangeLog);
					}

					// Add special end-of-stream markers to terminate the
					// workers
					for (int i = 0; i < workerThread.length; i++) {
						queue.addWork(Worker.NO_MORE_WORK);
					}
					for (int j = 0; j < workerThread.length; j++)
						workerThread[j].join();
					Trace.getInstance(getLoggerAppName()).log(Level.INFO, "All thread completed");

				}

			}
		} catch (Exception e) {
			Trace.getInstance(getLoggerAppName()).error(Level.ERROR, e);
		}
	}

	static class Worker extends Thread {

		// Special end-of-stream marker. If a worker retrieves
		// an Integer that equals this marker, the worker will terminate.
		static final TrackerTableChangeLog NO_MORE_WORK = new TrackerTableChangeLog();

		private WorkQueue q;
		private String loggerAppNameThread;

		public Worker(String threadName, WorkQueue q, String loggerAppNameThread) {
			super(threadName);
			this.q = q;
			this.loggerAppNameThread = loggerAppNameThread;
		}

		public void run() {

			long worketStartTime;

			while (true) {

				try {

					// Retrieve some work; block if the queue is empty
					TrackerTableChangeLog currentPushLog = null;
					currentPushLog = (TrackerTableChangeLog) q.getWork();
					// Terminate if the end-of-stream marker was retrieved
					if (currentPushLog == NO_MORE_WORK) {
						break;
					}
					Trace.getInstance(loggerAppNameThread).log(Level.INFO, " Push gonderiliyor");
					worketStartTime = System.currentTimeMillis();
					String trackerPushServiceEndPoint = Trace.getResourceByName("trackerPushServiceEndPoint");

					trackerPushServiceEndPoint += "/" + currentPushLog.getTableName() + "/"
							+ currentPushLog.getTableRowId();

					final HttpParams httpParams = new BasicHttpParams();
					HttpConnectionParams.setConnectionTimeout(httpParams, 30000);

					DefaultHttpClient httpClient = new DefaultHttpClient(httpParams);
					HttpGet getRequest = new HttpGet(trackerPushServiceEndPoint);
					getRequest.addHeader("accept", "application/json");

					HttpResponse response = httpClient.execute(getRequest);

					if (response.getStatusLine().getStatusCode() != 200) {
						throw new RuntimeException("Failed : HTTP error code : "
								+ response.getStatusLine().getStatusCode());
					}

					BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

					StringBuilder builder = new StringBuilder();
					String output;
					Trace.getInstance(loggerAppNameThread).log(Level.INFO, "Output from Server .... \n");
					System.out.println("Output from Server .... \n");
					while ((output = br.readLine()) != null) {
						System.out.println(output);
						builder.append(output);

					}

					ServiceResponseShell serviceResponseShell = new ServiceResponseShell();
					ServiceResponse<ArrayList<Integer>> serviceResponse = new ServiceResponse<ArrayList<Integer>>();
					Gson gson = new Gson();
					// convert the json string back to object
					serviceResponseShell = gson.fromJson(builder.toString(), ServiceResponseShell.class);
					serviceResponse = serviceResponseShell.getServiceResponse();
					if (serviceResponse.isStatusSuccess()) {
						Trace.getInstance(loggerAppNameThread).log(Level.INFO, " Push rest service gonderildi");
						UpdateUserPushTableDaoImpl updateUserPushTableDaoImpl = new UpdateUserPushTableDaoImpl();
						UpdateUserPushTableRequest updateUserPushTableRequest = new UpdateUserPushTableRequest();
						UpdateUserPushTableResponse updateUserPushTableResponse = new UpdateUserPushTableResponse();

						updateUserPushTableRequest.setCurrentPushLog(currentPushLog);
						updateUserPushTableDaoImpl.execute(updateUserPushTableRequest, updateUserPushTableResponse);
						Trace.getInstance(loggerAppNameThread).log(Level.INFO, " Push db guncellendi");

					} else {
						Trace.getInstance(loggerAppNameThread).log(Level.INFO,
								" Push rest service tarafinda yapilamadi");
						throw new TrackerException(serviceResponse.getErrorCode()
								+ serviceResponse.getErrorDescription());

					}

					httpClient.getConnectionManager().shutdown();

					long processtime = System.currentTimeMillis() - worketStartTime;
					Trace.getInstance(loggerAppNameThread).log(Level.INFO,
							" Push isleme suresi : " + processtime + " milliseconds");

				} catch (Exception exc) {
					Trace.getInstance(loggerAppNameThread).error(Level.ERROR, exc);
				}
			}
			Trace.getInstance(loggerAppNameThread).log(Level.DEBUG, " DONE!" + getName());
		}
	}

	@Autowired
	public void setLoggerName(String loggerName) {
		this.loggerName = loggerName;
	}

	public String getLoggerAppName() {
		return Trace.LOGGER_APP_PRE + this.loggerName;
	}

}
