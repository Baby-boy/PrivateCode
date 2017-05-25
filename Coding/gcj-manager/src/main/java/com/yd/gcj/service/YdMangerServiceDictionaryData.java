package com.yd.gcj.service;

import java.util.List;
import java.util.Map;

import com.yd.gcj.entity.YdMangerDictionaryData;

public interface YdMangerServiceDictionaryData {
	/***
	 * 获取指定组值
	 * @param value
	 * @return
	 */
	List<YdMangerDictionaryData> $queryAllByValue(Integer value);
	/**
	 * 查询记录数量
	 * @param value
	 * @return
	 */

	Integer $queryCountByValue(Integer value);
	
	/***
	 * 获取指定组的子分组值
	 * @param value
	 * @param isfixed
	 * @return
	 */
	List<YdMangerDictionaryData> $queryAllByValueAndIsfixed(Integer value,Integer isfixed);
	
	/***
	 * 查询记录数量
	 * @param value
	 * @param isfixed
	 * @return
	 */
	Integer $queryCountByValueAndIsfixed(Integer value,Integer isfixed);
	
	/***
	 * 获取指定id的值
	 * @param id
	 * @return
	 */
	YdMangerDictionaryData $queryById(Integer id);
	
	/***
	 * 查询指定id数据是否存下
	 * @param id
	 * @return
	 */
	Integer $isExsitById(Integer id);
	
	/***
	 * 获取任务规模
	 * @return
	 */
	List<YdMangerDictionaryData> $queryPScale();
	
	/***
	 * 获取合同默认值
	 * @return
	 */
	Map<String, Object> $queryDefContractValue();
}
