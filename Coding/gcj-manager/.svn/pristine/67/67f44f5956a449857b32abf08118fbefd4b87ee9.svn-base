package com.yd.gcj.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.yd.gcj.entity.YdMangerTaskType;

public interface YdMangerMapperTaskType {
	
	/**
	 * 查询任务类型全部信息
	 * @return
	 */
	List<YdMangerTaskType> $queryAll();
	
	/**
	 * 根据类型id查询单个类型
	 * @param taskt_id
	 * @return
	 */
	YdMangerTaskType $qeuryById(@Param("taskt_id") Integer taskt_id);
	
	/**
	 * 添加类型
	 * @param task_type
	 * @return
	 */
	Integer $insert(YdMangerTaskType task_type);
	
	/**
	 * 更新任务类型信息
	 * @param task_type
	 * @return
	 */
	Integer $update(YdMangerTaskType task_type);
	
	/**
	 * 删除指定的任务类型
	 * @param taskt_id
	 * @return
	 */
	Integer $delete(@Param("taskt_id") Integer taskt_id);
	
}
