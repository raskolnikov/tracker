package com.m2yazilim.tracker.messages.base;

import java.io.Serializable;
import java.util.Date;

import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerUsers;

public class MessageRequestHeader implements Serializable {
	private static final long serialVersionUID = -4628966121773720014L;

	private Date date;
	private String transactionName;
	private String clientIpAddress;
	private String clientBrowserAgent;
	private String jsfSessionId;

	private Object[] messageParams;
	private Long transactionLogId;
	private boolean logHistory;
	private boolean mainTransaction;
	private Date loginDate;
	private String serverIp;
	// private VpInvoker invoker;
	private String callId;
	private String sessionId;
	private TrackerUsers trackerUser;

	public MessageRequestHeader() {

	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTransactionName() {
		return transactionName;
	}

	public void setTransactionName(String transactionName) {
		this.transactionName = transactionName;
	}

	public String getClientIpAddress() {
		return clientIpAddress;
	}

	public void setClientIpAddress(String clientIpAddress) {
		this.clientIpAddress = clientIpAddress;
	}

	public String getJsfSessionId() {
		return jsfSessionId;
	}

	public void setJsfSessionId(String jsfSessionId) {
		this.jsfSessionId = jsfSessionId;
	}

	public String getClientBrowserAgent() {
		return clientBrowserAgent;
	}

	public void setClientBrowserAgent(String clientBrowserAgent) {
		this.clientBrowserAgent = clientBrowserAgent;
	}

	public Object[] getMessageParams() {
		return messageParams;
	}

	public void setMessageParams(Object[] messageParams) {
		this.messageParams = messageParams;
	}

	public Long getTransactionLogId() {
		return transactionLogId;
	}

	public void setTransactionLogId(Long transactionLogId) {
		this.transactionLogId = transactionLogId;
	}

	public boolean isLogHistory() {
		return logHistory;
	}

	public void setLogHistory(boolean logHistory) {
		this.logHistory = logHistory;
	}

	public boolean isMainTransaction() {
		return mainTransaction;
	}

	public void setMainTransaction(boolean mainTransaction) {
		this.mainTransaction = mainTransaction;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public String getCallId() {
		return callId;
	}

	public void setCallId(String callId) {
		this.callId = callId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

}
