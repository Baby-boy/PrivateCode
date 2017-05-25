package com.yd.gcj.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.YdMangerTaskTemp;
import com.yd.gcj.system.mapper.YdMangerMapperSystemWorkHall;
import com.yd.gcj.system.service.YdMangerServiceSystemWorkHall;

@Service("ydMangerServiceSystemWorkHall")
public class YdMangerServiceImplSystemWorkHall implements YdMangerServiceSystemWorkHall {

	@Autowired
	private YdMangerMapperSystemWorkHall ydMangerMapperSystemWorkHall;
	
	//查询所有工作大厅数据
	@Override
	public List<YdMangerTaskTemp> queryAllWorkHall(String tasktemp_num) {
		List<YdMangerTaskTemp> taskTempList = ydMangerMapperSystemWorkHall.queryAllWorkHall(tasktemp_num);
		return taskTempList;
	}

	//添加工作大厅数据
	@Override
	public Integer addWorkHall(YdMangerTaskTemp ydMangerTaskTemp) {
		Integer addNum = ydMangerMapperSystemWorkHall.addWorkHall(ydMangerTaskTemp);
		return addNum;
	}

	//删除工作大厅数据
	@Override
	public Integer deleteWorkHallByTaskTempId(Integer tasktemp_id) {

		Integer delNum = ydMangerMapperSystemWorkHall.deleteWorkHallByTaskTempId(tasktemp_id);
		return delNum;
	}

	//修改之前先查到工作大厅的数据
	@Override
	public YdMangerTaskTemp queryWorkHallByTaskTempId(Integer tasktemp_id) {
		YdMangerTaskTemp ydMangerTaskTemp = ydMangerMapperSystemWorkHall.queryWorkHallByTaskTempId(tasktemp_id);
		return ydMangerTaskTemp;
	}

	//根据指定的tasktemp_id修改工作大厅数据
	@Override
	public Integer updateWorkHallByTaskTempId(YdMangerTaskTemp ydMangerTaskTemp) {
		Integer updateNum = ydMangerMapperSystemWorkHall.updateWorkHallTaskTempId(ydMangerTaskTemp);
		return updateNum;
	}
	
}
