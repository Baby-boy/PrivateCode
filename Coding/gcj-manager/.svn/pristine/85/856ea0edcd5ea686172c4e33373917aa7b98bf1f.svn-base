package com.yd.gcj.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.yd.gcj.entity.YdMangerTaskLabel;

public interface YdMangerServiceTaskLabel {

	/***
	 * 查询任务类型数据
	 * @param taskl_tid
	 * @return
	 */
	List<YdMangerTaskLabel> $queryByTid(@Param("taskl_tid") Integer taskl_tid);
	
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
	/*Integer $insert(YdMangerTaskLabel taskl);*/
	
	/***
	 * 删除类型数据
	 * @param taskl_tid
	 * @param taskl_tlid
	 * @return
	 */
	@Transactional
	Integer $delete(Integer taskId);
	
}
