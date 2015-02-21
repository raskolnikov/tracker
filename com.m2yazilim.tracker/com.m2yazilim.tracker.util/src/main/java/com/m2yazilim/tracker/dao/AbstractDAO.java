package com.m2yazilim.tracker.dao;
import com.m2yazilim.tracker.messages.base.IRequestMessage;
import com.m2yazilim.tracker.messages.base.IResponseMessage;

public abstract class AbstractDAO<REQ extends IRequestMessage, RES extends IResponseMessage> {


	public boolean validate(REQ request, RES response) throws Exception {
		throw new Exception("MUSTIMPLEMENTMETHOD");
	}

	public void execute(REQ request, RES response) throws Exception {
		throw new Exception("MUSTIMPLEMENTMETHOD");
	}

	public void confirm(REQ request, RES response) throws Exception {
		throw new Exception("MUSTIMPLEMENTMETHOD");
	}

	public void fetch(REQ request, RES response) throws Exception {
		throw new Exception("MUSTIMPLEMENTMETHOD");
	}




}
