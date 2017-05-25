package com.yd.gcj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.gcj.entity.YdMangerCases;

public interface YdMangerMapperCases {
	
	/***
	 * 查询指定用户案例
	 * @param case_uid
	 * @return
	 */
	List<YdMangerCases> $queryAllByUserId(@Param("userId") Integer userId);
	
	/***
	 * 根据多条件查询案例
	 * @param case_uid
	 * @param startPageNum
	 * @param queryPageNum
	 * @return
	 */
	List<YdMangerCases> $queryByPageNum(@Param("case_uid") Integer case_uid,@Param("startPageNum") Integer startPageNum,@Param("queryPageNum") Integer queryPageNum);
	
	/***
	 * 查询指定案例信息
	 * @param case_id
	 * @return
	 */
	YdMangerMapperCases $queryById(@Param("case_id") Integer case_id);
	
	/***
	 * 查询指定用户案例信息数量
	 * @param case_uid
	 * @return
	 */
	Integer $queryCountNum(@Param("case_uid") Integer case_uid);
	
	/***
	 * 
	 * 添加用户案例信息
	 * @param cases
	 * @return
	 */
	Integer $insert(YdMangerCases cases);
	
	/***
	 * 修改用户案例信息
	 * @param cases
	 * @return
	 */
	Integer $update(YdMangerCases cases);
	
	/***
	 * 删除指定案例信息
	 * @param case_id
	 * @return
	 */
	Integer $delete(@Param("case_id") Integer case_id);
	
}
