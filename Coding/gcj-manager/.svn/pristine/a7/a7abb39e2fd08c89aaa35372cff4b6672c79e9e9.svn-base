package com.yd.gcj.system.service;

import java.util.List;

import com.yd.gcj.entity.YdMangerArticleClass;

public interface YdMangerServiceSystemArticleType {

	/**
	 * description(查询文章的所有类型)
	 * @param
	 * @return
	 */
	List<YdMangerArticleClass> queryAllArticleType();

	/**
	 * description(查询文章类型的所有信息)
	 * @param
	 * @return
	 */
	List<YdMangerArticleClass> queryAllArticleTypeInfo(String ac_name);

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
	 * description(修改之前先根据ac_id查询到当前文章类型)
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
