package com.m2yazilim.tracker.messages.base;



public class ResponseMessage extends TransactionMessage implements IResponseMessage {
	private static final long serialVersionUID = 8465222137017408281L;

	private MessageResponseHeader header;

	public ResponseMessage() {
		this.header = new MessageResponseHeader();
	}

	public MessageResponseHeader getHeader() {
		return header;
	}

	public void setHeader(MessageResponseHeader header) {
		this.header = header;
	}

	@Override
	public String toXml() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toText() {
		// TODO Auto-generated method stub
		return null;
	}

}
