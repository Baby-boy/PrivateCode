package com.yd.gcj.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.vo.YdMangerFilesTaskVo;
import com.yd.gcj.system.mapper.YdMangerMapperSystemFilesTask;
import com.yd.gcj.system.service.YdMangerserviceSystemFilesTask;

@Service("ydMangerserviceSystemFilesTask")
public class YdMangerServiceImplSystemFilesTask implements YdMangerserviceSystemFilesTask{

	@Autowired
	private YdMangerMapperSystemFilesTask ydMangerMapperSystemFilesTask;
	
	//查询当前任务所对应的所有文件
	@Override
	public List<YdMangerFilesTaskVo> queryFilesTaskByTaskId(Integer task_id) {
		List<YdMangerFilesTaskVo> filesTaskList = ydMangerMapperSystemFilesTask.queryFilesTaskByTaskId(task_id);
		return filesTaskList;
	}


	
}
