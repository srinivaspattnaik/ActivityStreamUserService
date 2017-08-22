package com.stackroute.activitystream.userutility;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerUtility 
{
	 private static final Logger LOGGER = Logger.getLogger(LoggerUtility.class);

	 @Before("execution(public * com.stackroute.activitystream.userutility.UserController.*(..))")
	 public void beforeEveryMethod(JoinPoint joinPoint) 
	 {
		 	MethodSignature methodSignature=(MethodSignature)joinPoint.getSignature();
		 	
		 	String methodName=methodSignature.getName();
		 	
		 	LOGGER.info("--- Method Name:"+methodName+"---------");
		 	
		 	String methodParameterNames[]=methodSignature.getParameterNames();
	       
	        if(methodParameterNames!=null)
	        {
		        LOGGER.info("Parameters:");
		        for(int i=0;i<methodParameterNames.length;i++)
		        {
		        LOGGER.info(methodParameterNames[i]);
		        }
	        }
	        
	        
	 }
}
