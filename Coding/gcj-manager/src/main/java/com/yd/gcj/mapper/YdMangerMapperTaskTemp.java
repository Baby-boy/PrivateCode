package com.yd.gcj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.gcj.entity.YdMangerTaskTemp;

public interface YdMangerMapperTaskTemp {
	
	/***
	 * 查询全部临时任务数据
	 * @return
	 */
	List<YdMangerTaskTemp> $queryAll(@Param("startPageNum") Integer startPageNum,@Param("queryPageNum") Integer queryPageNum);
	
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
	Integer $insert(YdMangerTaskTemp tasktemp);
	
	/***
	 * 修改临时任务信息
	 * @param tasktemp
	 * @return
	 */
	Integer $update(YdMangerTaskTemp tasktemp);
	
	/***
	 * 删除指定临时任务信息
	 * @param tasktemp_id
	 * @return
	 */
	Integer $delete(@Param("tasktemp_id") Integer tasktemp_id);
	
}
