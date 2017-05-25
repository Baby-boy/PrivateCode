package com.yd.gcj.system.service;

import java.util.List;

import com.yd.gcj.entity.vo.YdMangerFilesTaskVo;

public interface YdMangerserviceSystemFilesTask {

	/**
	 * description(查询当前任务所对应的所有文件)
	 * @param
	 * @param task_id
	 * @return
	 */
	List<YdMangerFilesTaskVo> queryFilesTaskByTaskId(Integer task_id);

}
