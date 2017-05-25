package com.yd.gcj.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.YdMangerTask;
import com.yd.gcj.entity.vo.YdMangerTaskVo;
import com.yd.gcj.system.mapper.YdMangerMapperSystemTask;
import com.yd.gcj.system.service.YdMangerServiceSystemTask;

@Service("ydMangerMappertSystemTask")
public class YdMangerServiceImplSystemTask implements YdMangerServiceSystemTask{

	@Autowired
	private YdMangerMapperSystemTask ydMangerMappertSystemTask;

	//查询所有的任务
	@Override
	public List<YdMangerTaskVo> queryAllTask(String task_num,Integer task_state) {
		List<YdMangerTaskVo> taskList =  ydMangerMappertSystemTask.queryAllTask(task_num,task_state);
		return taskList;
	}

	//删除任务
	@Override
	public Integer deleteTaskByTaskId(Integer task_id) {
		
		Integer delNum = ydMangerMappertSystemTask.deleteTaskByTaskId(task_id);
		return delNum;
	}

	//查询任务时查询任务id
	@Override
	public YdMangerTask queryTaskByTaskNum(String task_num) {
		YdMangerTask ydMangerTask = ydMangerMappertSystemTask.queryTaskByTaskNum(task_num);
		return ydMangerTask;
	}

	//查询任务详细信息
	@Override
	public YdMangerTaskVo queryTaskByTaskId(Integer task_id) {
		YdMangerTaskVo ydMangerTaskVo = ydMangerMappertSystemTask.queryTaskByTaskId(task_id);
		return ydMangerTaskVo;
	}

	//查询所有 任务的状态
	@Override
	public List<YdMangerTaskVo> queryTaskAllState() {
		List<YdMangerTaskVo> taskStateList = ydMangerMappertSystemTask.queryTaskAllState();
		return taskStateList;
	}

	//修改任务状态
	@Override
	public Integer updateTaskStateByTaskId(YdMangerTaskVo ydMangerTaskVo) {
		Integer updateNum = ydMangerMappertSystemTask.updateTaskStateByTaskId(ydMangerTaskVo);
		return updateNum;
	}

	
}
