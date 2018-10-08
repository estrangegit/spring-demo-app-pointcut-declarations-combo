package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {
	
	@Pointcut("execution(* com.luv2code.aopdemo.dao.*.*(..))")
	private void forDaoPackage() {}
	
	// create pointcut for getter methods
	@Pointcut("execution(* com.luv2code.aopdemo.dao.*.get*(..))")
	private void forDaoPackageGetters() {}
	
	// create pointcut for setter methods
	@Pointcut("execution(* com.luv2code.aopdemo.dao.*.set*(..))")
	private void forDaoPackageSetters() {}	
	
	// create point: include package ... exclude getter/setter
	@Pointcut("forDaoPackage() && !(forDaoPackageGetters() || forDaoPackageSetters())")
	private void forDaoPackageNoGetterSetter() {}
	
	@Before("forDaoPackageNoGetterSetter()")
	public void beforeAnyMethodInDaoPackage() {
		System.out.println("\n=====>>> Executing @Before any method of any class in com.luv2code.aopdemo.dao");		
	}
	
	@Before("forDaoPackageNoGetterSetter()")	
	public void performApiAnalytics() {
		System.out.println("\n=====>>> Performing API Analytics");		
	}	
}
