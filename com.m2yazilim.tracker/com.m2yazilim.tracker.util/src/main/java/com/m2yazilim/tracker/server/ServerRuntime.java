package com.m2yazilim.tracker.server;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.naming.InitialContext;

public class ServerRuntime {

	private static String IP;
	private static String PORT;

	public static String getServerIp() {
		if (IP == null) {
			initialize();
		}
    
		return IP;

	}

	public static String getServerPort() {
		if (PORT == null) {
			initialize();
		}
		return PORT;
	}

	private synchronized static void initialize() {

		try {
			ObjectName service = new ObjectName("com.bea:Name=RuntimeService,Type=weblogic.management.mbeanservers.runtime.RuntimeServiceMBean");
			InitialContext ctx = new InitialContext();
			MBeanServer connection = (MBeanServer) ctx
					.lookup("java:comp/env/jmx/runtime");
			ObjectName serverRT = (ObjectName) connection.getAttribute(service,
					"ServerRuntime");
			IP = (String) connection.getAttribute(serverRT, "ListenAddress");
			if(IP != null && IP.contains("/")){
				IP=IP.substring(0,IP.indexOf("/"));
			}
			PORT = (Integer) connection.getAttribute(serverRT, "ListenPort")
					+ "";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
