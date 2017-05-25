package com.yd.gcj.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.gcj.entity.YdMangerArticleClass;

public interface YdMangerMapperSystemArticleType {

	/**
	 * description(查询文章的所有 类型)
	 * @param
	 * @return
	 */
	List<YdMangerArticleClass> queryAllArticleType();

	/**
	 * description(查询文章类型的所有信息)
	 * @param
	 * @return
	 */
	List<YdMangerArticleClass> queryAllArticleTypeInfo(@Param("ac_name")String ac_name);

	/**
	 * description(添加文章类型)
	 * @param
	 * @param ydMangerArticleClass
	 * @return
	 */
	Integer addArticleClass(YdMangerArticleClass ydMangerArticleClass);

	/**
	 * description(根据指定的ac_id删除文章类型)
	 * @param
	 * @param ac_id
	 * @return
	 */
	Integer deleteArticleClassByAcId(Integer ac_id);

	/**
	 * description(修改之前先根据ac_id查到当前文章类型)
	 * @param
	 * @param ac_id
	 * @return
	 */
	YdMangerArticleClass queryArticleTypeByAcId(Integer ac_id);

	/**
	 * description(根据指定的ac_id修改文章类型)
	 * @param
	 * @param ydMangerArticleClass
	 * @return
	 */
	Integer updateArticleClassByAcId(YdMangerArticleClass ydMangerArticleClass);

}
