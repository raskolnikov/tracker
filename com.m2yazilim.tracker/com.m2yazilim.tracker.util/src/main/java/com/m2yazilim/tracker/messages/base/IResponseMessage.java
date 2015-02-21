package com.m2yazilim.tracker.messages.base;

public interface IResponseMessage {

	public MessageResponseHeader getHeader();

	public void setHeader(MessageResponseHeader header); 

	public String toXml();

	public String toText();
}
