package com.yd.gcj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.gcj.entity.YdMangerDictionaryData;

public interface YdMangerMapperDictionaryData {
	
	/***
	 * 获取指定组值
	 * @param value
	 * @return
	 */
	List<YdMangerDictionaryData> $queryAllByValue(@Param("value") Integer value);
	
	Integer $queryCountByValue(@Param("value") Integer value);
	
	/***
	 * 获取指定组的子分组值
	 * @param value
	 * @param isfixed
	 * @return
	 */
	List<YdMangerDictionaryData> $queryAllByValueAndIsfixed(@Param("value") Integer value,@Param("isfixed") Integer isfixed);
	
	Integer $queryCountByValueAndIsfixed(@Param("value") Integer value,@Param("isfixed") Integer isfixed);
	
	/***
	 * 获取指定id的值
	 * @param id
	 * @return
	 */
	YdMangerDictionaryData $queryById(@Param("id") Integer id);
	
	Integer $isExsitById(@Param("id") Integer id);
	
}
