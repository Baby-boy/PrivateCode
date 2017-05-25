package com.yd.gcj.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.yd.gcj.entity.YdMangerTaskActive;

public interface YdMangerMapperTaskActive {
	
	/**
	 * 根据任务id查询该任务的评论及回复信息
	 * @param task_tid
	 * @return
	 */
	List<YdMangerTaskActive> $queryByTid(@Param("taskId")  Integer taskId);
	
	/**
	 * 添加任务新评论信息
	 * @param taska
	 * @return
	 */
	Integer $insert(YdMangerTaskActive taska);
	
	/**
	 * 删除评论
	 * @param taska_id
	 * @return
	 */
	Integer $delete(@Param("taska_id") Integer taska_id);
	
	
}
