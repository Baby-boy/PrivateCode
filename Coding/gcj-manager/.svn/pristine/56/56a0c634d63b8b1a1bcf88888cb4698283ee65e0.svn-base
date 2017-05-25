package com.yd.gcj.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.YdMangerContract;
import com.yd.gcj.system.mapper.YdMangerMapperSystemContract;
import com.yd.gcj.system.service.YdMangerServiceSystemContract;

@Service("ydMangerServiceSystemContract")
public class YdMangerServiceImplSystemContract implements YdMangerServiceSystemContract{

	@Autowired
	private YdMangerMapperSystemContract ydMangerMapperSystemContract;
	
	//查询项目任务所对应的合同信息
	@Override
	public YdMangerContract queryContractByTaskId(Integer task_id) {
		YdMangerContract ydMangerContract = ydMangerMapperSystemContract.queryContractByTaskId(task_id);
		return ydMangerContract;
	}

	
}
