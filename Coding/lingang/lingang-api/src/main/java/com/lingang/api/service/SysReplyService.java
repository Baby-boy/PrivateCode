package com.lingang.api.service;

import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.entity.SysReply;

public interface SysReplyService {
	/**
	* @Description: (添加回复)
	* @param reply
	* @return    
	* @author gsh(15136390655@163.com)
	* @date: 2016年12月9日 上午2:49:33
	 */
	ServiceResult<Integer> addSysReplyService(SysReply reply);	

}
