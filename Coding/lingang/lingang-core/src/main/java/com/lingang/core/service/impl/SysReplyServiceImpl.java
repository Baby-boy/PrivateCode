package com.lingang.core.service.impl;

import org.springframework.stereotype.Service;

import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.basic.StateCodeConstant;
import com.lingang.api.domain.entity.SysReply;
import com.lingang.api.service.SysReplyService;
@Service("sysReplyService")
public class SysReplyServiceImpl implements SysReplyService{

	/**
	 * 添加回复
	 */
	@Override
	public ServiceResult<Integer> addSysReplyService(SysReply reply) {
		ServiceResult<Integer> result=new ServiceResult<Integer>();
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		return result;
	}
}
