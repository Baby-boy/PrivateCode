package com.yd.gcj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.gcj.entity.YdMangerCollection;

public interface YdMangerMapperCollection {
	
	/***
	 * 查询该用户所有收藏任务信息
	 * @param colle_uid
	 * @return
	 */
	List<YdMangerCollection> $queryAll(@Param("colle_uid") Integer colle_uid);
	
	/***
	 * 查询该用户根据分页的收藏任务信息
	 * @param colle_uid
	 * @param startPageNum
	 * @param queryPageNum
	 * @return
	 */
	List<YdMangerCollection> $queryAllByPageNum(@Param("colle_uid") Integer colle_uid,
			@Param("startPageNum") Integer startPageNum,@Param("queryPageNum") Integer queryPageNum);
	
	/***
	 * 查询该用户收藏任务信息数量
	 * @param colle_uid
	 * @return
	 */
	Integer $queryCountNum(@Param("colle_uid") Integer colle_uid);
	
	/***
	 * 添加收藏
	 * @param colle
	 * @return
	 */
	Integer $insert(@Param("userId") Integer userId,@Param("taskId") Integer taskId);
	
	/***
	 * 删除收藏任务信息（取消收藏）
	 * @param colle_id
	 * @return
	 */
	Integer $delete(@Param("userId") Integer userId,@Param("taskId") Integer taskId);
	
	Integer $isExist(@Param("userId") Integer userId, @Param("taskId") Integer taskId);
}
