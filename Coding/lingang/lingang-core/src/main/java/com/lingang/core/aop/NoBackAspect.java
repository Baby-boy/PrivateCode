package com.lingang.core.aop;

import org.apache.log4j.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import com.lingang.api.domain.basic.ServiceResult;



public class NoBackAspect {
	
	private static Logger logger = Logger.getLogger(NoBackAspect.class);
	
	public void doAfter(JoinPoint jp) {
 
    }  
  
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {  
        long time = System.currentTimeMillis();  
        Object retVal = null;
        try{
        	 retVal = pjp.proceed(); 
        }catch(Exception e){
        	logger.error("get------------------------",e);
        	if(retVal == null){
            	ServiceResult<Object> result  = new ServiceResult<Object>();
            	result.setStateCode(e.getMessage());
            	result.setRemark("运行异常!");
            	retVal = result;
            } 
        }
         
        time = System.currentTimeMillis() - time;  
        if(retVal == null){
        	retVal = new ServiceResult<Object>();
        }
        return retVal;  
    }  
  
    public void doBefore(JoinPoint jp) {  
       
    }  
  
    public void doThrowing(JoinPoint jp, Throwable ex) {  
       
    }  
}
