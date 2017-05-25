package com.yd.gcj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface YdMangerMapperArticleClass {
	
	/***
	 * 查询文章所有类型信息
	 * @return
	 */
	List<YdMangerMapperArticleClass> $queryAll();
	
	/***
	 * 根据id查询类型信息
	 * @param ac_id
	 * @return
	 */
	YdMangerMapperArticleClass $queryById(@Param("ac_id") Integer ac_id);
	
	/***
	 * 查询类型信息数量
	 * @return
	 */
	Integer $queryCountNum();
	
	/***
	 * 修改类型信息
	 * @param ac
	 * @return
	 */
	Integer $update(YdMangerMapperArticleClass ac);
	
	/***
	 * 删除指定文章类型数据
	 * @param ac_id
	 * @return
	 */
	Integer $delete(@Param("ac_id")Integer ac_id);
	
	/***
	 * 添加新的文章类型信息
	 * @param ac
	 * @return
	 */
	Integer $insert(YdMangerMapperArticleClass ac);
	
}	
