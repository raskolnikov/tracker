package com.m2yazilim.tracker.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

public class AspectUtil {

	public static String getMethodStartMessage(JoinPoint joinPoint){
		StringBuilder buffer = new StringBuilder();
		
		buffer.append("Starting ").append(joinPoint.getSignature().toString());
		
		String[] parameterNames = ((MethodSignature)joinPoint.getStaticPart().getSignature()).getParameterNames();
		Object[] parameterValues = joinPoint.getArgs();
		if(parameterNames.length > 0){
			buffer.append(" with arguments (");
			
			for(int i=0; i<parameterNames.length; i++) {
				buffer.append(parameterNames[i]);
				buffer.append("=");
				buffer.append(parameterValues[i] != null ? parameterValues[i].toString() : "null");
				buffer.append(",");
			}

			buffer.deleteCharAt(buffer.length()-1);
			buffer.append(")");
		}
		
		return buffer.toString();
	}

	public static String getMethodEndMessage(JoinPoint joinPoint){
		StringBuilder buffer = new StringBuilder();
		buffer.append(joinPoint.getSignature().toString()).append(" completed succesfully");
		return buffer.toString();
	}

	public static String getMethodFailMessage(JoinPoint joinPoint){
		StringBuilder buffer = new StringBuilder();
		buffer.append(joinPoint.getSignature()).append(" failed!");
		return buffer.toString();
	}

	public static String getMethodFailMessageFromException(Throwable e){
		StringBuilder buffer = new StringBuilder();
		buffer.append("EXCEPTION: ");
		buffer.append(e.getStackTrace()[0].toString());
		buffer.append("\nEXPLANATION: ");
		buffer.append(e.toString());
		return buffer.toString();
	}

}
