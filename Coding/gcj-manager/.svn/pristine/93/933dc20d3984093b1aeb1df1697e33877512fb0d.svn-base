package com.yd.gcj.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.gcj.entity.YdMangerTaskModel;

public interface YdMangerMapperTaskModel {
	
	/***
	 * 查询任务所有节点模块
	 * @param taskmd_tid
	 * @return
	 */
	List<YdMangerTaskModel> $queryByTid(@Param("taskmd_tid") Integer taskmd_tid);
	
	/***
	 * 查询指定任务模块信息
	 * @param taskmd_id
	 * @return
	 */
	YdMangerTaskModel $queryById(@Param("taskmd_id") Integer taskmd_id);
	
	/***
	 * 修改模块
	 * @param taskmd
	 * @return
	 */
	Integer $update(YdMangerTaskModel taskmd);
	
	/***
	 * 修改模块状态，主要为检查确认是使用
	 * @param taskmd_tid 任务id
	 * @param taskmd_state 需要修改的状态
	 * @param taskmd_update_time 更新时间
	 * @return
	 */
	Integer $updateState(@Param("taskmd_tid") Integer taskmd_tid,@Param("taskmd_state") Integer taskmd_state,@Param("taskmd_update_time") Date taskmd_update_time);
	
	/***
	 * 添加任务新模块节点计划
	 * @param taskmd
	 * @return
	 */
	Integer $insert(YdMangerTaskModel taskmd);
	
	/***
	 * 删除指定模块
	 * @param taskmd_id
	 * @return
	 */
	Integer $delete(@Param("taskmd_id") Integer taskmd_id);
	
	
}
