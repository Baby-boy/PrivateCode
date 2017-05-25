package com.yd.gcj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.gcj.entity.YdMangerTaskMsg;

public interface YdMangerMapperTaskMsg {
	
	/***
	 * 根据任务id查询该任务所发布的公告
	 * @param taskmsg_tid
	 * @return
	 */
	List<YdMangerTaskMsg> $queryByTid(@Param("taskmsg_tid") Integer taskmsg_tid);
	
	/***
	 * 根据公告id查询该公告信息
	 * @param taskmsg_id
	 * @return
	 */
	YdMangerTaskMsg $queryById(@Param("taskmsg_id") Integer taskmsg_id);
	
	/***
	 * 添加新的公告信息
	 * @param taskmsg
	 * @return
	 */
	Integer $insert(YdMangerTaskMsg taskmsg);
	
	/***
	 * 修改公告信息
	 * @param taskmsg
	 * @return
	 */
	Integer $update(YdMangerTaskMsg taskmsg);
	
	/***
	 * 删除指定id公告
	 * @param taskmsg_id
	 * @return
	 */
	Integer $delete(@Param("taskmsg_id") Integer taskmsg_id);
	
}
