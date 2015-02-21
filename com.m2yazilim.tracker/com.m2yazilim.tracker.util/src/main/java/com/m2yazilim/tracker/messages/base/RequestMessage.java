package com.m2yazilim.tracker.messages.base;



public class RequestMessage extends TransactionMessage implements IRequestMessage {
	private static final long serialVersionUID = 1717976345607889939L;

	private MessageRequestHeader header;

	public RequestMessage() {
		this.header = new MessageRequestHeader();

	}

	public MessageRequestHeader getHeader() {
		return header;
	}

	public void setHeader(MessageRequestHeader header) {
		this.header = header;
	}

	public String toXml() {
		//return XstreamSerialization.getInstance().toXml(this);
		return "";
	}

	public String toText() {
		//return XstreamTextSerialization.getInstance().toText(this);
		return "";
	}

}
