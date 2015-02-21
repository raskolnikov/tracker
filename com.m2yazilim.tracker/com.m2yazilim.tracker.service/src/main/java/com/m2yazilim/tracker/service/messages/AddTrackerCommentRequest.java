package com.m2yazilim.tracker.service.messages;

import com.m2yazilim.tracker.messages.base.RequestMessage;

public class AddTrackerCommentRequest extends RequestMessage {

	private static final long serialVersionUID = 1L;
	private int userId;
	private int taskId;
	private int commentId;
	private String commentText ;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	

}
