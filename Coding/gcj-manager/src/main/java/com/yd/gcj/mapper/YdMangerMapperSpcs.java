package com.yd.gcj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.gcj.entity.YdMangerSpcs;

public interface YdMangerMapperSpcs {
	
	/***
	 * 查询指定用户的评价信息
	 * @param spcs_sid
	 * @return
	 */
	List<YdMangerSpcs> $queryAll(@Param("userId") Integer userId);
	
	/***
	 * 查询用户评价信息
	 * @param userId
	 * @return
	 */
	List<YdMangerSpcs> $queryByUserId(@Param("userId") Integer userId);
	
	/***
	 * 查询指定用户根据 分页 的评价信息
	 * @param spcs_sid
	 * @param startPageNum
	 * @param queryPageNum
	 * @return
	 */
	List<YdMangerSpcs> $queryAllByPageNum(@Param("spcs_sid") Integer spcs_sid,@Param("startPageNum") Integer startPageNum,@Param("queryPageNum") Integer queryPageNum);
	
	/***
	 * 查询指定评价信息
	 * @param spcs_id
	 * @return
	 */
	YdMangerSpcs $queryById(@Param("spcs_id") Integer spcs_id);
	
	/***
	 * 新增用户评价信息
	 * @param spcs
	 * @return
	 */
	Integer $insert(YdMangerSpcs spcs);
	
	/***
	 * 更新用户评价信息
	 * @param spcs
	 * @return
	 */
	Integer $update(YdMangerSpcs spcs);
	
	/***
	 * 删除指定评价信息
	 * @param spcs_id
	 * @return
	 */
	Integer $delete(@Param("spcs_id") Integer spcs_id);
	
}
