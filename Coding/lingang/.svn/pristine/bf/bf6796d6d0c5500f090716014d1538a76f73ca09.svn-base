package com.lingang.core.aop;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.lingang.api.domain.basic.ServiceResult;


public class RollBackAspect {
	
	private static Logger logger = Logger.getLogger(RollBackAspect.class);
	
	public void doAfter(JoinPoint jp) {
		logger.info("log Ending method: "  
                + jp.getTarget().getClass().getName() + "."  
                + jp.getSignature().getName());  
    }  
	
	@Resource
	private DataSourceTransactionManager transactionManager;
    
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {  
        long time = System.currentTimeMillis();  
        Object retVal = null;

        //获取事务
        DefaultTransactionDefinition def =new DefaultTransactionDefinition();
        TransactionStatus status=transactionManager.getTransaction(def);
        try{
        	 retVal = pjp.proceed(); 
        	 
        	 //事务执行
        	 transactionManager.commit(status);
        }catch(RuntimeException e){
            if(retVal == null){
            	ServiceResult<Object> result  = new ServiceResult<Object>();
            	result.setStateCode(e.getMessage());
            	retVal = result;
            }       	
        	//事务回滚
        	transactionManager.rollback(status);
        	logger.error("RuntimeException----------------------",e);
        }catch(Exception e){
        	//事务回滚
        	transactionManager.rollback(status);
        	logger.error("update----------------------",e);
        }
         
        time = System.currentTimeMillis() - time;  
        
        if(retVal == null){
        	retVal = new ServiceResult<Object>();
        }
        return retVal;  
    }  
  
    public void doBefore(JoinPoint jp) {  
        logger.info("log Begining method: "  
                + jp.getTarget().getClass().getName() + "."  
                + jp.getSignature().getName());  
    }  
  
    public void doThrowing(JoinPoint jp, Throwable ex) {  
    	 logger.info("method " + jp.getTarget().getClass().getName()  
                + "." + jp.getSignature().getName() + " throw exception");  
    	 logger.info(ex.getMessage());  
    }  
}
