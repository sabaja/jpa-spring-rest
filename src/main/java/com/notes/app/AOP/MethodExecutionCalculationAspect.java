package com.notes.app.AOP;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
@Slf4j
public class MethodExecutionCalculationAspect {


	@Around("@annotation(com.notes.a.annotations.TrackTime)")
	public void around(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();

		joinPoint.proceed();

		long timeTaken = System.currentTimeMillis() - startTime;
		log.info("Time Taken by {} is {}", joinPoint, timeTaken);
	}
}
