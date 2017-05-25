package com.yd.gcj.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.vo.YdMangerTenderVo;
import com.yd.gcj.system.mapper.YdMangerMapperSystemTender;
import com.yd.gcj.system.service.YdMangerServiceSystemTender;

@Service("ydMangerServiceSystemTender")
public class YdMangerServiceImplSystemTender implements YdMangerServiceSystemTender{

	@Autowired
	private YdMangerMapperSystemTender ydMangerMapperSystemTender;
	
	//查询任务所对应的所有投标信息
	@Override
	public List<YdMangerTenderVo> queryTenderByTaskId(Integer task_id,String tender_pname,String tender_uphone) {
		List<YdMangerTenderVo> ydMangerTender = ydMangerMapperSystemTender.queryTenderByTaskId(task_id,tender_pname,tender_uphone);
		return ydMangerTender;
	}

	//查询当前投标的详细信息
	@Override
	public YdMangerTenderVo queryTenderByTenderId(Integer tender_id) {
		YdMangerTenderVo ydMangerTenderVo = ydMangerMapperSystemTender.queryTenderByTenderId(tender_id);
		return ydMangerTenderVo;
	}

	//条件查询
	@Override
	public List<YdMangerTenderVo> queryTenderByCondition(Integer task_id,String tender_uname,String tender_uphone) {
		List<YdMangerTenderVo> ydMangerTenderVo= ydMangerMapperSystemTender.queryTenderByCondition(task_id,tender_uname,tender_uphone);
		return ydMangerTenderVo;
	}

	
}
