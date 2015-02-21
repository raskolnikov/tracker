package com.m2yazilim.tracker.aspect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.apache.log4j.Level;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import com.m2yazilim.tracker.constants.ErrorCodes;
import com.m2yazilim.tracker.logging.LogUserInfo;
import com.m2yazilim.tracker.logging.Trace;
import com.m2yazilim.tracker.model.ServiceResponse;
import com.m2yazilim.tracker.shell.ServiceResponseShell;

@Aspect
public class LoggerAspect {

	private String loggerName;

	public void setLoggerName(String loggerName) {
		this.loggerName = loggerName;
	}

	public String getLoggerAppName() {
		return Trace.LOGGER_APP_PRE + this.loggerName;
	}

	public String getLoggerSecName() {
		return Trace.LOGGER_SEC_PRE + this.loggerName;
	}

	@Pointcut("execution(com.m2yazilim.tracker.model.ServiceResponse com.m2yazilim.tracker..*.*(..)) && !execution(com.m2yazilim.tracker.model.ServiceResponse com.m2yazilim.tracker.shell..*.*(..)) ")
	void withinAllServiceMethods() {
	}

	@Pointcut("execution(com.m2yazilim.tracker.shell.ServiceResponseShell+ com.m2yazilim.tracker..*.*(..)) && !execution(* com.m2yazilim.tracker.util.RestServiceUtil.*(..))")
	void withinAllRestServiceCalls() {
	}

	/*
	 * This method writes logs(also parameter info) before a service method is
	 * called.
	 */
	@Before("withinAllServiceMethods()")
	public void beforeServiceMethod(JoinPoint joinPoint) throws Throwable {
		Method interfaceMethod = ((MethodSignature) joinPoint.getSignature()).getMethod();
		Annotation[][] annotationParameters = interfaceMethod.getParameterAnnotations();

		Signature sig = joinPoint.getSignature();
		String transactionName = sig.getDeclaringTypeName() + " " + sig.getName() + "_Request";

		Object[] arguments = joinPoint.getArgs();
		StringBuffer paramBuffer = new StringBuffer("[Args:(");

		for (int length = arguments.length, i = 0; i < length; ++i) {
			boolean restricted = false;
			String value = "XXXHiddenValue";
			Annotation[] annotations = annotationParameters[i];
			for (Annotation annotation : annotations) {
				if (annotation.annotationType().equals(RestrictedLogParam.class)) {
					restricted = true;
					value = ((RestrictedLogParam) annotation).value();
					break;
				}

				else if (annotation.annotationType().equals(CreditCardLogParam.class)) {
					restricted = true;
					String tempValue = (String) arguments[i];
					StringBuilder maskedValueBuilder = new StringBuilder(tempValue);
					for (int valLength = 0; valLength < tempValue.length(); valLength++) {
						if (valLength > 3 && valLength < tempValue.length() - 4) {
							maskedValueBuilder.setCharAt(valLength, 'X');
						}
					}
					value = maskedValueBuilder.toString();
					break;
				}

			}
			Object argument = restricted ? value : arguments[i];
			paramBuffer.append(argument);
			if (i != length - 1) {
				paramBuffer.append(',');
			}
		}
		paramBuffer.append(")]");

		String msg = " Entering[" + sig.getName() + "]" + paramBuffer.toString();

		Trace.getInstance(getLoggerAppName()).log(
				Level.INFO,
				LogUserInfo.asLoggableString() + sig.getName() + " Start Time For Uuid: "
						+ LogUserInfo.getCurrentGeneratedID() + " CurrentTimeMill: " + System.currentTimeMillis());
		Trace.getInstance(getLoggerAppName()).log(Level.INFO,
				LogUserInfo.asLoggableString(transactionName, LogUserInfo.getUser(), null, msg));
		Trace.getInstance(getLoggerSecName()).log(Level.INFO,
				LogUserInfo.asLoggableString(transactionName, LogUserInfo.getUser(), null, msg));
	}

	@Before("withinAllRestServiceCalls()")
	public void beforeRestServiceMethod(JoinPoint joinPoint) throws Throwable {
		Method interfaceMethod = ((MethodSignature) joinPoint.getSignature()).getMethod();
		Annotation[][] annotationParameters = interfaceMethod.getParameterAnnotations();

		Signature sig = joinPoint.getSignature();
		String transactionName = sig.getDeclaringTypeName() + " " + sig.getName() + "_Request";

		Object[] arguments = joinPoint.getArgs();
		StringBuffer paramBuffer = new StringBuffer("[Args:(");

		for (int length = arguments.length, i = 0; i < length; ++i) {
			boolean restricted = false;
			String value = "XXXHiddenValue";
			Annotation[] annotations = annotationParameters[i];
			for (Annotation annotation : annotations) {
				if (annotation.annotationType().equals(RestrictedLogParam.class)) {
					restricted = true;
					value = ((RestrictedLogParam) annotation).value();
					break;
				}

				else if (annotation.annotationType().equals(CreditCardLogParam.class)) {
					restricted = true;
					String tempValue = (String) arguments[i];
					StringBuilder maskedValueBuilder = new StringBuilder(tempValue);
					for (int valLength = 0; valLength < tempValue.length(); valLength++) {
						if (valLength > 3 && valLength < tempValue.length() - 4) {
							maskedValueBuilder.setCharAt(valLength, 'X');
						}
					}
					value = maskedValueBuilder.toString();
					break;
				}
			}
			Object argument = restricted ? value : arguments[i];
			paramBuffer.append(argument);
			if (i != length - 1) {
				paramBuffer.append(',');
			}
		}
		paramBuffer.append(")]");

		String msg = " Entering[" + sig.getName() + "]" + paramBuffer.toString();

		Trace.getInstance(getLoggerAppName()).log(
				Level.INFO,
				LogUserInfo.asLoggableString() + sig.getName() + " Start Time For Uuid: "
						+ LogUserInfo.getCurrentGeneratedID() + " CurrentTimeMill: " + System.currentTimeMillis());
		Trace.getInstance(getLoggerAppName()).log(Level.INFO,
				LogUserInfo.asLoggableString(transactionName, LogUserInfo.getUser(), null, msg));
		Trace.getInstance(getLoggerSecName()).log(Level.INFO,
				LogUserInfo.asLoggableString(transactionName, LogUserInfo.getUser(), null, msg));
	}

	/*
	 * This method writes logs after service methods calls, even if has an
	 * exception or doesn't have an exception.
	 */
	@AfterReturning(pointcut = "withinAllServiceMethods()", returning = "retVal")
	public void afterServiceMethod(JoinPoint joinPoint, ServiceResponse<?> retVal) throws Throwable {
		Signature sig = joinPoint.getSignature();
		String transactionName = sig.getDeclaringTypeName() + " " + sig.getName() + "_Response";

		if (retVal == null) {
			String msg = ("ErrorCode: " + ErrorCodes.ERROR_UNCOVERED + " - ErrorDescription: " + " Uncovered Service Side Exception: No ServiceResponse returned.");
			Trace.getInstance(getLoggerAppName()).log(Level.ERROR,
					LogUserInfo.asLoggableString(transactionName, LogUserInfo.getUser(), Trace.LOG_STATUS_FAILED, msg));
			Trace.getInstance(getLoggerSecName()).log(Level.ERROR,
					LogUserInfo.asLoggableString(transactionName, LogUserInfo.getUser(), Trace.LOG_STATUS_FAILED, msg));
			return;
		}

		final String responseData = " Response Data[" + sig.getName() + "]"
				+ new OnlineReflectionToStringBuilder(retVal, OnlineStyle.getInstance()).toString();
		if (!retVal.isStatusSuccess()) {
			String msg = (" ErrorCode: " + retVal.getErrorCode() + " - ErrorDescription: " + retVal.getErrorDescription());
			Trace.getInstance(getLoggerAppName()).log(
					Level.ERROR,
					LogUserInfo.asLoggableString(transactionName, LogUserInfo.getUser(), Trace.LOG_STATUS_FAILED, msg
							+ responseData));
			Trace.getInstance(getLoggerSecName()).log(Level.ERROR,
					LogUserInfo.asLoggableString(transactionName, LogUserInfo.getUser(), Trace.LOG_STATUS_FAILED, msg));

			try {
				if (retVal.getResult() == null) {
					Trace.getInstance(getLoggerSecName()).log(
							Level.ERROR,
							LogUserInfo.asLoggableString(transactionName, LogUserInfo.getUser(),
									Trace.LOG_STATUS_FAILED, "result:" + null));
				} else if (retVal.getResult() instanceof String) {
					Trace.getInstance(getLoggerSecName()).log(
							Level.ERROR,
							LogUserInfo.asLoggableString(transactionName, LogUserInfo.getUser(),
									Trace.LOG_STATUS_FAILED, "result:" + retVal.getResult()));
				} else if (retVal.getResult() instanceof Integer) {
					Trace.getInstance(getLoggerSecName()).log(
							Level.ERROR,
							LogUserInfo.asLoggableString(transactionName, LogUserInfo.getUser(),
									Trace.LOG_STATUS_FAILED, "result:" + Integer.toString((Integer) retVal.getResult())));
				} else if (retVal.getResult() instanceof Long) {
					Trace.getInstance(getLoggerSecName()).log(
							Level.ERROR,
							LogUserInfo.asLoggableString(transactionName, LogUserInfo.getUser(),
									Trace.LOG_STATUS_FAILED, "result:" + Long.toString((Long) retVal.getResult())));
				} else if (retVal.getResult() instanceof String[]) {
					String resultOfArray = "";
					String[] result = (String[]) retVal.getResult();

					for (int i = 0; i < result.length; i++) {
						resultOfArray = resultOfArray + "," + result[i];
					}

					Trace.getInstance(getLoggerSecName()).log(
							Level.ERROR,
							LogUserInfo.asLoggableString(transactionName, LogUserInfo.getUser(),
									Trace.LOG_STATUS_FAILED, "result:" + resultOfArray));
				} else if (retVal.getResult() instanceof Integer[]) {
					String resultOfArray = "";
					Integer[] result = (Integer[]) retVal.getResult();

					for (int i = 0; i < result.length; i++) {
						resultOfArray = resultOfArray + "," + result[i].intValue();
					}

					Trace.getInstance(getLoggerSecName()).log(
							Level.ERROR,
							LogUserInfo.asLoggableString(transactionName, LogUserInfo.getUser(),
									Trace.LOG_STATUS_FAILED, "result:" + resultOfArray));
				} else if (retVal.getResult() instanceof Long[]) {
					String resultOfArray = "";
					Long[] result = (Long[]) retVal.getResult();

					for (int i = 0; i < result.length; i++) {
						resultOfArray = resultOfArray + "," + result[i].longValue();
					}

					Trace.getInstance(getLoggerSecName()).log(
							Level.ERROR,
							LogUserInfo.asLoggableString(transactionName, LogUserInfo.getUser(),
									Trace.LOG_STATUS_FAILED, "result:" + resultOfArray));
				}
			} catch (Exception e) {
				Trace.getInstance(getLoggerSecName()).log(
						Level.ERROR,
						LogUserInfo.asLoggableString(transactionName, LogUserInfo.getUser(), Trace.LOG_STATUS_FAILED,
								"Error occured during logging: " + e.getLocalizedMessage()));
			}

		} else {
			Trace.getInstance(getLoggerAppName()).log(
					Level.INFO,
					LogUserInfo.asLoggableString(transactionName, LogUserInfo.getUser(), Trace.LOG_STATUS_SUCCESS,
							responseData));
			Trace.getInstance(getLoggerSecName()).log(Level.INFO,
					LogUserInfo.asLoggableString(transactionName, LogUserInfo.getUser(), Trace.LOG_STATUS_SUCCESS, ""));

			try {
				if (retVal.getResult() == null) {
					Trace.getInstance(getLoggerSecName()).log(
							Level.INFO,
							LogUserInfo.asLoggableString(transactionName, LogUserInfo.getUser(),
									Trace.LOG_STATUS_SUCCESS, "result:" + null));
				} else if (retVal.getResult() instanceof String) {
					Trace.getInstance(getLoggerSecName()).log(
							Level.INFO,
							LogUserInfo.asLoggableString(transactionName, LogUserInfo.getUser(),
									Trace.LOG_STATUS_SUCCESS, "result:" + retVal.getResult()));
				} else if (retVal.getResult() instanceof Integer) {
					Trace.getInstance(getLoggerSecName()).log(
							Level.INFO,
							LogUserInfo.asLoggableString(transactionName, LogUserInfo.getUser(),
									Trace.LOG_STATUS_SUCCESS,
									"result:" + Integer.toString((Integer) retVal.getResult())));
				} else if (retVal.getResult() instanceof Long) {
					Trace.getInstance(getLoggerSecName()).log(
							Level.INFO,
							LogUserInfo.asLoggableString(transactionName, LogUserInfo.getUser(),
									Trace.LOG_STATUS_SUCCESS, "result:" + Long.toString((Long) retVal.getResult())));
				} else if (retVal.getResult() instanceof String[]) {
					String resultOfArray = "";
					String[] result = (String[]) retVal.getResult();

					for (int i = 0; i < result.length; i++) {
						resultOfArray = resultOfArray + "," + result[i];
					}

					Trace.getInstance(getLoggerSecName()).log(
							Level.INFO,
							LogUserInfo.asLoggableString(transactionName, LogUserInfo.getUser(),
									Trace.LOG_STATUS_SUCCESS, "result:" + resultOfArray));
				} else if (retVal.getResult() instanceof Integer[]) {
					String resultOfArray = "";
					Integer[] result = (Integer[]) retVal.getResult();

					for (int i = 0; i < result.length; i++) {
						resultOfArray = resultOfArray + "," + result[i].intValue();
					}

					Trace.getInstance(getLoggerSecName()).log(
							Level.INFO,
							LogUserInfo.asLoggableString(transactionName, LogUserInfo.getUser(),
									Trace.LOG_STATUS_SUCCESS, "result:" + resultOfArray));
				} else if (retVal.getResult() instanceof Long[]) {
					String resultOfArray = "";
					Long[] result = (Long[]) retVal.getResult();

					for (int i = 0; i < result.length; i++) {
						resultOfArray = resultOfArray + "," + result[i].longValue();
					}

					Trace.getInstance(getLoggerSecName()).log(
							Level.INFO,
							LogUserInfo.asLoggableString(transactionName, LogUserInfo.getUser(),
									Trace.LOG_STATUS_SUCCESS, "result:" + resultOfArray));
				}
			} catch (Exception e) {
				Trace.getInstance(getLoggerSecName()).log(
						Level.ERROR,
						LogUserInfo.asLoggableString(transactionName, LogUserInfo.getUser(), Trace.LOG_STATUS_FAILED,
								"Error occured during logging: " + e.getLocalizedMessage()));
			}
		}

		Trace.getInstance(getLoggerAppName()).log(
				Level.INFO,
				LogUserInfo.asLoggableString() + sig.getName() + " Finish Time For Uuid: "
						+ LogUserInfo.getCurrentGeneratedID() + " CurrentTimeMill: " + System.currentTimeMillis());
	}

	/*
	 * This method writes logs after service methods calls, even if has an
	 * exception or doesn't have an exception.
	 */
	@AfterReturning(pointcut = "withinAllRestServiceCalls()", returning = "retVal")
	public void afterRestServiceCall(JoinPoint joinPoint, ServiceResponseShell retVal) throws Throwable {
		Signature sig = joinPoint.getSignature();
		String transactionName = sig.getDeclaringTypeName() + " " + sig.getName() + "_Response";

		if (retVal == null || retVal.getServiceResponse() == null) {
			String msg = ("ErrorCode: " + ErrorCodes.ERROR_UNCOVERED + " - ErrorDescription: " + " Uncovered Service Side Exception: No ServiceResponseShell or ServiceResponse returned.");
			Trace.getInstance(getLoggerAppName()).log(Level.ERROR,
					LogUserInfo.asLoggableString(transactionName, LogUserInfo.getUser(), Trace.LOG_STATUS_FAILED, msg));
			Trace.getInstance(getLoggerSecName()).log(Level.ERROR,
					LogUserInfo.asLoggableString(transactionName, LogUserInfo.getUser(), Trace.LOG_STATUS_FAILED, msg));
			return;
		}

		final String responseData = " Response Data[" + sig.getName() + "]"
				+ new OnlineReflectionToStringBuilder(retVal, OnlineStyle.getInstance()).toString();
		if (!retVal.getServiceResponse().isStatusSuccess()) {
			String msg = ("ErrorCode: " + retVal.getServiceResponse().getErrorCode() + " - ErrorDescription: " + retVal.getServiceResponse().getErrorDescription());
			Trace.getInstance(getLoggerAppName()).log(
					Level.ERROR,
					LogUserInfo.asLoggableString(transactionName, LogUserInfo.getUser(), Trace.LOG_STATUS_FAILED, msg
							+ responseData));
			Trace.getInstance(getLoggerSecName()).log(Level.ERROR,
					LogUserInfo.asLoggableString(transactionName, LogUserInfo.getUser(), Trace.LOG_STATUS_FAILED, msg));

		} else {
			Trace.getInstance(getLoggerAppName()).log(
					Level.INFO,
					LogUserInfo.asLoggableString(transactionName, LogUserInfo.getUser(), Trace.LOG_STATUS_SUCCESS,
							responseData));
			Trace.getInstance(getLoggerSecName()).log(Level.INFO,
					LogUserInfo.asLoggableString(transactionName, LogUserInfo.getUser(), Trace.LOG_STATUS_SUCCESS, ""));
		}
		Trace.getInstance(getLoggerAppName()).log(
				Level.INFO,
				LogUserInfo.asLoggableString() + sig.getName() + " Finish Time For Uuid: "
						+ LogUserInfo.getCurrentGeneratedID() + " CurrentTimeMill: " + System.currentTimeMillis());
	}

	/*
	 * This aspect method covers all service methods exceptions which has
	 * uncovered try - catch block.
	 */
	@AfterThrowing(pointcut = "withinAllServiceMethods()", throwing = "e")
	public void afterServiceThrowingMethod(JoinPoint joinPoint, Throwable e) throws Throwable {
		final Signature reqsig = joinPoint.getSignature();
		final String reqtransactionName = reqsig.getDeclaringTypeName() + " " + reqsig.getName() + "_Request";

		final Object[] arguments = joinPoint.getArgs();
		final StringBuffer paramBuffer = new StringBuffer("[Args:(");
		Object[] loggableArguments = null;
		final MethodSignature metSig = ((MethodSignature) joinPoint.getSignature());
		final String[] parNames = metSig.getParameterNames();
		loggableArguments = new Object[parNames.length];
		for (int i = 0; i < parNames.length; i++) {
			if (OnlineReflectionToStringBuilder.getRestrictedParNames().contains(parNames[i])) {
				loggableArguments[i] = parNames[i];
			} else {
				loggableArguments[i] = arguments[i];
			}
		}

		paramBuffer.append(new OnlineReflectionToStringBuilder(loggableArguments, OnlineStyle.getInstance()).toString());
		paramBuffer.append(")]");
		final String reqmsg = " Method Params[" + reqsig.getName() + "]" + paramBuffer.toString();

		Signature sig = joinPoint.getSignature();
		String transactionName = sig.getDeclaringTypeName() + " " + sig.getName() + "_Response";
		String msg = ("ErrorCode: " + ErrorCodes.ERROR_UNCOVERED + " - ErrorDescription: "
				+ " Uncovered Service Side Exception: No try - catch covered defined." + e.getMessage());

		Trace.getInstance(getLoggerAppName()).log(
				Level.ERROR,
				LogUserInfo.asLoggableString(reqtransactionName, LogUserInfo.getUser(), Trace.LOG_STATUS_FAILED, reqmsg));
		Trace.getInstance(getLoggerSecName()).log(
				Level.ERROR,
				LogUserInfo.asLoggableString(reqtransactionName, LogUserInfo.getUser(), Trace.LOG_STATUS_FAILED, reqmsg));

		Trace.getInstance(getLoggerAppName()).log(Level.ERROR,
				LogUserInfo.asLoggableString(transactionName, LogUserInfo.getUser(), Trace.LOG_STATUS_FAILED, msg));
		Trace.getInstance(getLoggerSecName()).log(Level.ERROR,
				LogUserInfo.asLoggableString(transactionName, LogUserInfo.getUser(), Trace.LOG_STATUS_FAILED, msg));

		Trace.getInstance(getLoggerAppName()).log(Level.ERROR, AspectUtil.getMethodFailMessage(joinPoint));
		Trace.getInstance(getLoggerAppName()).log(Level.ERROR, AspectUtil.getMethodFailMessageFromException(e), e);
	}

	/*
	 * This aspect method covers all service methods exceptions which has
	 * uncovered try - catch block.
	 */
	@AfterThrowing(pointcut = "withinAllRestServiceCalls()", throwing = "e")
	public void afterRestCallThrowingMethod(JoinPoint joinPoint, Throwable e) throws Throwable {
		final Signature reqsig = joinPoint.getSignature();
		final String reqtransactionName = reqsig.getDeclaringTypeName() + " " + reqsig.getName() + "_Request";

		final Object[] arguments = joinPoint.getArgs();
		final StringBuffer paramBuffer = new StringBuffer("[Args:(");
		Object[] loggableArguments = null;
		final MethodSignature metSig = ((MethodSignature) joinPoint.getSignature());
		final String[] parNames = metSig.getParameterNames();
		loggableArguments = new Object[parNames.length];
		for (int i = 0; i < parNames.length; i++) {
			if (OnlineReflectionToStringBuilder.getRestrictedParNames().contains(parNames[i])) {
				loggableArguments[i] = parNames[i];
			} else {
				loggableArguments[i] = arguments[i];
			}
		}

		paramBuffer.append(new OnlineReflectionToStringBuilder(loggableArguments, OnlineStyle.getInstance()).toString());
		paramBuffer.append(")]");
		final String reqmsg = " Method Params[" + reqsig.getName() + "]" + paramBuffer.toString();

		Signature sig = joinPoint.getSignature();
		String transactionName = sig.getDeclaringTypeName() + " " + sig.getName() + "_Response";
		String msg = ("ErrorCode: " + ErrorCodes.ERROR_UNCOVERED + " - ErrorDescription: "
				+ " Uncovered Service Side Exception: No try - catch covered defined." + e.getMessage());
		Trace.getInstance(getLoggerAppName()).log(
				Level.ERROR,
				LogUserInfo.asLoggableString(reqtransactionName, LogUserInfo.getUser(), Trace.LOG_STATUS_FAILED, reqmsg));
		Trace.getInstance(getLoggerSecName()).log(
				Level.ERROR,
				LogUserInfo.asLoggableString(reqtransactionName, LogUserInfo.getUser(), Trace.LOG_STATUS_FAILED, reqmsg));
		Trace.getInstance(getLoggerAppName()).log(Level.ERROR,
				LogUserInfo.asLoggableString(transactionName, LogUserInfo.getUser(), Trace.LOG_STATUS_FAILED, msg));
		Trace.getInstance(getLoggerSecName()).log(Level.ERROR,
				LogUserInfo.asLoggableString(transactionName, LogUserInfo.getUser(), Trace.LOG_STATUS_FAILED, msg));

		Trace.getInstance(getLoggerAppName()).log(Level.ERROR, AspectUtil.getMethodFailMessage(joinPoint));
		Trace.getInstance(getLoggerAppName()).log(Level.ERROR, AspectUtil.getMethodFailMessageFromException(e), e);

	}

}
