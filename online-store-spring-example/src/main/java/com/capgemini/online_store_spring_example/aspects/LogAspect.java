package com.capgemini.online_store_spring_example.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import com.capgemini.online_store_spring_example.commons.DataRelatedException;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Slf4j
public class LogAspect {
	
	@After("execution(public * toViewModel(..))")
	public void entityToViewModelTransformation(JoinPoint jp) {
		String entityType = ((MethodSignature) jp.getSignature()).getReturnType().toString(); 
		if(log.isInfoEnabled()) {
			log.info(entityType + " has been transformed into ViewModel");
		}
	}
	
	@After("execution(public * toEntity(..))")
	public void viewModeltoEntityTransformation(JoinPoint jp) {
		String vmType = ((MethodSignature) jp.getSignature()).getReturnType().toString();
		if(log.isInfoEnabled()) {
			log.info(vmType + " has been transformed into Entity"); 
		}
	}
		
	/*
	 * Logging per le operazioni di save a livello service
	 */
	@Around("execution(* com.capgemini.online_store_spring_example.services.impl.*.save(..))")
	public Object savingEntityLog(ProceedingJoinPoint pjp) throws Throwable {
		String entityType = ((MethodSignature) pjp.getSignature()).getReturnType().toString(); 
		
		if(log.isDebugEnabled()) {
			log.debug("Saving entity of type [{0}]", entityType);
		}
		
		try {
			return pjp.proceed();
		}catch(DataRelatedException dre){
			if(log.isErrorEnabled()) {
				log.error(entityType +"-- Error while saving ! MESSAGE: \n"+ dre.getMessage());
			}
			throw dre;
		}
	}
	
	
}
