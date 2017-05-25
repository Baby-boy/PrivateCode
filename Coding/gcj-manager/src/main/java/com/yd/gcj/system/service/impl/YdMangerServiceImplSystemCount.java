package com.yd.gcj.system.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.YdMangerCount;
import com.yd.gcj.system.mapper.YdMangerMapperSystemCount;
import com.yd.gcj.system.service.YdMangerServiceSystemCount;

@Service("ydMangerServiceSystemCount")
public class YdMangerServiceImplSystemCount implements YdMangerServiceSystemCount{

	@Autowired
	private YdMangerMapperSystemCount ydMangerMapperSystemCount;
	
	//查询当前月的数据
	@Override
	public List<YdMangerCount> queryAllInfomation(Date monthFirstDay,Date monthLastDay) {
		List<YdMangerCount> countList = ydMangerMapperSystemCount.queryAllInfomation(monthFirstDay,monthLastDay);
		
		return countList;
	}

	//根据当前日期的int类型查询统计数据
	@Override
	public List<YdMangerCount> queryAllInfomationByCountNum(int parseFirstTime,int parseLastTime) {
		List<YdMangerCount> countList = ydMangerMapperSystemCount.queryAllInfomationByCountNum(parseFirstTime,parseLastTime);
		return countList;
	}


	
}
