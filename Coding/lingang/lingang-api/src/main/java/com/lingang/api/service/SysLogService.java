package com.lingang.api.service;

import java.util.Date;

import com.lingang.api.domain.basic.Page;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.entity.SysLog;
import com.lingang.api.domain.para.SysLogPara;

public interface SysLogService {

	/**
	* @Description: (查看日志)
	* @param para
	* @return    
	* @author lgl(lgl1012dream@foxmail.com)
	* @date: 2017年1月19日 下午1:58:38
	 */
	ServiceResult<Page<SysLog>> selectSysLogPageList(SysLogPara para);
	
	/**
	* @Description: (添加日志)
	* @param managerAccount
	* @param logIp
	* @param logContent
	* @param createTime
	* @return    
	* @author lgl(lgl1012dream@foxmail.com)
	* @date: 2017年1月19日 下午1:58:51
	 */
	ServiceResult<Object> insertSysLog(String managerAccount,String logIp,Date createTime,String logContent); 
}
