package com.yd.gcj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.gcj.entity.YdMangerEpcs;

public interface YdMangerMapperEpcs {
	
	/***
	 * 查询指定任务的评价
	 * @param epcs_tid
	 * @return
	 */
	List<YdMangerEpcs> $queryAllByTid(@Param("epcs_tid") Integer epcs_tid);
	
	/***
	 * 查询用户点评信息
	 * @param userId
	 * @return
	 */
	List<YdMangerEpcs> $queryAllByUserId(@Param("userId") Integer userId);
	
	/***
	 * 使用sql进行多条件情况查询
	 * @param sql
	 * @return
	 */
	List<YdMangerEpcs> $queryBySql(@Param("sql") String sql);
	
	/***
	 * 查询指定任务评价数量
	 * @param epcs_tid
	 * @return
	 */
	Integer $queryCountNum(@Param("taskId") Integer taskId);
	
	/***
	 * 使用sql查询多条件下评价数量
	 * @param sql
	 * @return
	 */
	Integer $queryCountNumBySql(@Param("sql") String sql);
	
	/***
	 * 添加新评价信息
	 * @param epcs
	 * @return
	 */
	Integer $insert(YdMangerEpcs epcs);
	
	/***
	 * 修改评价信息
	 * @param epcs
	 * @return
	 */
	Integer $update(YdMangerEpcs epcs);
	
	/***
	 * 删除指定评价信息
	 * @param epcs_id
	 * @return
	 */
	Integer $delete(@Param("epcs_id") Integer epcs_id);
	
}
