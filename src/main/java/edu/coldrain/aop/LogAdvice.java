package edu.coldrain.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Log4j
@Component
@Aspect //해당 클래스의 객체가 Aspect를 구현한 것임을 나타내기 위해서 사용한다.
public class LogAdvice {

	//간단한 로그를 찍는다.
	@Before("execution(* edu.coldrain.service.SampleService*.*(..))")
	public void logBefore() {
		log.info("==========================");
	}
	
	//파라미터를 기록한다.
	@Before("execution(* edu.coldrain.service.SampleService*.doAdd(String, String)) && args(str1, str2)")
	public void logBeforeWithParam(String str1, String str2) {
		log.info("str1: " + str1);
		log.info("str2: " + str2);
	}
	
	
	//예외를 기록한다.
	@AfterThrowing(pointcut = "execution(* edu.coldrain.service.SampleService*.*(..))", throwing="exception")
	public void logException(Exception exception) {
		log.info("Exception....!!!");
		log.info("exception: " + exception);
	}
	
	@Around("execution(* edu.coldrain.service.SampleService*.*(..))")
	public Object logTime(ProceedingJoinPoint pjp) {
		long start = System.currentTimeMillis();
		log.info("Target: " + pjp.getTarget());
		log.info("Param: " + Arrays.toString(pjp.getArgs()));
		
		//invoke method
		Object result = null;
		
		try {
			result = pjp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		
		log.info("TIME: " + (end - start));
		
		return result;
	}
}
