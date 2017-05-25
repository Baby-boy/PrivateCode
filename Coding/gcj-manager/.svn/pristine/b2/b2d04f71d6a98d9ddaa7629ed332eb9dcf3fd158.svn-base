package com.yd.gcj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.gcj.entity.YdMangerFilesTask;

public interface YdMangerMapperFilesTask {
	
	
	List<YdMangerFilesTask> $queryFiles(@Param("taskId") Integer taskId);
	
	/***
	 * 添加任务文件关联信息
	 * @param filesTask
	 * @return
	 */
	Integer $insert(YdMangerFilesTask filesTask);
	
	/***
	 * 删除任务文件关联关系
	 * @param taskId
	 * @param filesId
	 * @return
	 */
	Integer $del(@Param("taskId") Integer taskId,@Param("fileId") Integer fileId);
	
}
