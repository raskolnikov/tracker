package com.m2yazilim.tracker.messages.base;

public interface IRequestMessage {

	public MessageRequestHeader getHeader();

	public void setHeader(MessageRequestHeader header);

	public String toXml();

	public String toText();
}
