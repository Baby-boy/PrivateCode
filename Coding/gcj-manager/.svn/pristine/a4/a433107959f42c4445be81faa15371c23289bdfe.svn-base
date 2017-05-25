package com.yd.gcj.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.yd.gcj.entity.YdMangerTaskTemp;

public interface YdMangerServiceTaskTemp {

	/***
	 * 查询全部临时任务数据
	 * @return
	 */
	List<YdMangerTaskTemp> $queryAll();
	
	/***
	 * 根据id查询指定临时任务数据
	 * @param tasktemp_id
	 * @return
	 */
	YdMangerTaskTemp $queryById(@Param("tasktemp_id") Integer tasktemp_id);
	
	/***
	 * 查询临时任务数量
	 * @return
	 */
	Integer $queryCountNum();
	
	/***
	 * 添加临时任务信息
	 * @param tasktemp
	 * @return
	 */
	@Transactional
	Integer $insert(YdMangerTaskTemp tasktemp);
	
	/***
	 * 修改临时任务信息
	 * @param tasktemp
	 * @return
	 */
	@Transactional
	Integer $update(YdMangerTaskTemp tasktemp);
	
	/***
	 * 删除指定临时任务信息
	 * @param tasktemp_id
	 * @return
	 */
	@Transactional
	Integer $delete(@Param("tasktemp_id") Integer tasktemp_id);
	
}
