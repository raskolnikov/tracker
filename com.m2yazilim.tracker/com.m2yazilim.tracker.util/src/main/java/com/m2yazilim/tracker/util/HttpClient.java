package com.m2yazilim.tracker.util;

import java.io.IOException;

import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpState;

public class HttpClient extends org.apache.commons.httpclient.HttpClient {

	@Override
	public int executeMethod(HostConfiguration hostconfig, HttpMethod method, HttpState state) throws IOException, HttpException {
		return super.executeMethod(hostconfig, method, state);
	}
	
	@Override
	public int executeMethod(HostConfiguration hostConfiguration, HttpMethod method) throws IOException, HttpException {
		return super.executeMethod(hostConfiguration, method);
	}
	
	@Override
	public int executeMethod(HttpMethod method) throws IOException, HttpException {
		return super.executeMethod(method);
	} 
}
