package com.yoon.standard.core.handler;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Aspect
public class AopHandler {
	
	@Around("execution(* com.yoon.standard.controller..*Controller..*(..) )")
	public Object logging(ProceedingJoinPoint joinPoint) throws Throwable{
		
		long startTime = System.nanoTime();
		log.debug("=> "+joinPoint.getSignature().getDeclaringTypeName() + "/" + joinPoint.getSignature().getName());
		Object result = joinPoint.proceed();
		long endTime = System.nanoTime();
		
		double resultTime = (endTime-startTime)/1000000000.0;
		
		log.debug("<= " + joinPoint.getSignature().getDeclaringTypeName() + "/" + joinPoint.getSignature().getName() + "/" + resultTime + "초");
		return result;
	}

}
