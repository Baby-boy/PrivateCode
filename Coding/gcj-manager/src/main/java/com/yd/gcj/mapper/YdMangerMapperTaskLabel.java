package com.yd.gcj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.gcj.entity.YdMangerTaskLabel;

public interface YdMangerMapperTaskLabel {
	
	/***
	 * 查询任务类型数据
	 * @param taskl_tid
	 * @return
	 */
	List<YdMangerTaskLabel> $queryByTid(@Param("taskId") Integer taskl_tid);
	
	/***
	 * 根据任务查询指定的任务标签id集合
	 * @param taskId
	 * @return
	 */
	List<Integer> $queryIdByTid(@Param("taskId") Integer taskId);
	
	/**
	 * 
	 * @Description: 根据多个任务id查询相应标签
	 * @param tids
	 * @return    
	 * @date: 2016年12月21日 下午9:46:14
	 */
	List<YdMangerTaskLabel> $queryByTids(@Param("tids") List<Integer> tids);
	
	/***
	 * 查询单个任务类型信息
	 * @param taskl_id
	 * @return
	 */
	YdMangerTaskLabel $queryById(@Param("taskl_id") Integer taskl_id);
	
	/***
	 * 添加新类型
	 * @param taskl
	 * @return
	 */
	Integer $insert(@Param("taskId") Integer taskId,@Param("labelId") Integer labelId);
	
	/***
	 * 删除类型数据
	 * @param taskl_tid
	 * @param taskl_tlid
	 * @return
	 */
	Integer $deleteByTaskId(@Param("taskId") Integer taskId);
	
}
