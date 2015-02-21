package com.m2yazilim.tracker.proxy.messages;

import com.m2yazilim.tracker.messages.base.ResponseMessage;

public class AddTrackerCommentResponse extends ResponseMessage {

	private static final long serialVersionUID = 1L;
	private int commentId;

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

}
