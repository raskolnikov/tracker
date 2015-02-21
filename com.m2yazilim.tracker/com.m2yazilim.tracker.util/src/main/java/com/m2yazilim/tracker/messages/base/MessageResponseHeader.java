package com.m2yazilim.tracker.messages.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public class MessageResponseHeader implements Serializable {
	private static final long serialVersionUID = -1558909157261920081L;
	private List<Notification> notificationList;
	private boolean transactionSuccess = false;


	public List<Notification> getNotificationList() {
		return notificationList;
	}

	public void setNotificationList(List<Notification> notificationList) {
		this.notificationList = notificationList;
	}

	public MessageResponseHeader() {
		this.notificationList = new ArrayList<Notification>();
	}

	public boolean hasError() {
		for (Notification notification : this.notificationList) {
			if (notification.getType().equals(Notification.NotificationType.ERROR))
				return true;
		}
		return false;
	}

	public void cleanNotifications() {
		List<Notification> notificationList = getNotificationList();
		if (notificationList != null && notificationList.size() > 0) {
			setNotificationList(new ArrayList<Notification>());
		}
	}

	public boolean isTransactionSuccess() {
		return transactionSuccess;
	}

	public void setTransactionSuccess(boolean transactionSuccess) {
		this.transactionSuccess = transactionSuccess;
	}


}
