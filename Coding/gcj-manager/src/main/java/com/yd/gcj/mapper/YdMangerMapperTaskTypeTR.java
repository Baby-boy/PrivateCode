package com.yd.gcj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.gcj.entity.YdMangerTaskType;
import com.yd.gcj.entity.YdMangerTaskTypeTR;

public interface YdMangerMapperTaskTypeTR {
	
	/**
	 * 查询指定任务id查询该任务的所有类型
	 * @param task_id
	 * @return
	 */
	List<YdMangerTaskTypeTR> $queryAllByTaskId(@Param("taskId") Integer taskId);
	
	/***
	 * 查询所有类型并标识已选类型
	 * @param taskId
	 * @return
	 */
	List<YdMangerTaskType> $queryTypeByTaskId(@Param("taskId") Integer taskId);
	
	/**
	 * 根据指定类型查询该类型的所有任务id
	 * @param taskt_id
	 * @return
	 */
	List<YdMangerTaskTypeTR> $queryAllTaskIdByTypeId(@Param("taskttr_ttid") Integer taskttr_ttid);
	
	/**
	 * 为任务添加类型
	 * @param task_typertr
	 * @return
	 */
	Integer $insert(@Param("taskId") Integer taskId,@Param("typeId") Integer typeId);
	
	/**
	 * 更新任务类型信息
	 * @param task_typetr
	 * @return
	 */
	Integer $update(YdMangerTaskTypeTR task_typetr);
	
	/**
	 * 删除指定任务类型
	 * @param taskttr_id
	 * @return
	 */
	Integer $delete(@Param("taskId") Integer taskId);
}
