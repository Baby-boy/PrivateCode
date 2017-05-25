package com.yd.gcj.system.service.impl;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.YdMangerTaskType;
import com.yd.gcj.system.mapper.YdMangerMapperSystemTaskType;
import com.yd.gcj.system.service.YdMangerServiceSystemTaskType;

@Service("ydMangerMappertSystemTaskType")
public class YdMangerServiceImplSystemTaskType implements YdMangerServiceSystemTaskType{
	
	@Autowired
	private YdMangerMapperSystemTaskType ydMangerMapperSystemTaskType;
	
	@Autowired
	private ServletContext context;
	
	//查询所有的任务类型
	@Override
	public List<YdMangerTaskType> queryAllTaskType() {
		List<YdMangerTaskType> taskTypeList = ydMangerMapperSystemTaskType.queryAllTaskType();	
		return taskTypeList;
	}

	//删除任务类型
	@Override
	public Integer deleteTaskTypeByTasktId(Integer taskt_id) {
		Integer delNum = ydMangerMapperSystemTaskType.deleteTaskTypeByTasktId(taskt_id);
		context.setAttribute("taskTypes", null);
		return delNum;
	}

	//添加任务类型
	@Override
	public Integer addTaskType(YdMangerTaskType ydMangerTaskType) {
		Integer addNum = ydMangerMapperSystemTaskType.addTaskType(ydMangerTaskType);
		context.setAttribute("taskTypes", null);
		return addNum;
	}
	
	//修改任务类型信息
	@Override
	public YdMangerTaskType updateTaskTypeByTaskTypeId(Integer taskt_id) {
		YdMangerTaskType ydMangerTaskType = ydMangerMapperSystemTaskType.updateTaskTypeByTaskTypeId(taskt_id);
		return ydMangerTaskType;
	}

	//修改任务类型信息
	@Override
	public Integer updateTaskType(YdMangerTaskType ydMangerTaskType) {
		Integer updateNum =ydMangerMapperSystemTaskType.updateTaskType(ydMangerTaskType);
		return updateNum;
	}

	
}
