package com.capgemini.jstk.capmates.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestAspect {
	private static final Logger LOGGER = LoggerFactory.getLogger(TestAspect.class);
	private long startTime;

	@Before("execution(* com.capgemini.jstk.capmates.repository.dao.*.*(..))")
	public void beforeSearchAllBooks() {
		startTime = System.currentTimeMillis();
		LOGGER.info("start time " + startTime);
	}

	@After("execution(* com.capgemini.jstk.capmates.repository.dao.*.*(..))")
	public void afterSearchAllBooks() {
		long endTime = System.currentTimeMillis();
		long time = endTime - startTime;
		LOGGER.info("start time= " + startTime + "| end time= " + endTime + "| execution time= " + time);
	}
}