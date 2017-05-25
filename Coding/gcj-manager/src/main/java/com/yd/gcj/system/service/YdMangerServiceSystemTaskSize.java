package com.yd.gcj.system.service;

import java.util.List;

import com.yd.gcj.entity.YdMangerDictionaryData;

public interface YdMangerServiceSystemTaskSize {

	/**
	 * description(查询所有的任务规模)
	 * @param
	 * @return
	 */
	List<YdMangerDictionaryData> queryAllTaskSize(String dictdata_value);

	/**
	 * description(根据dict_id删除与任务规模)
	 * @param
	 * @param dict_id
	 * @return
	 */
	Integer deleteTaskSzie(Integer dict_id);

	/**
	 * description(修改之前先根据dict_id查询当前任务规模信息)
	 * @param
	 * @param dict_id
	 * @return
	 */
	YdMangerDictionaryData queryTaskSizeByDictId(Integer dict_id);

	/**
	 * description(修改任务规模)
	 * @param
	 * @param ydMangerDictionaryData
	 * @return
	 */
	Integer updateTaskSize(YdMangerDictionaryData ydMangerDictionaryData);

	/**
	 * description(添加搜索条件任务规模)
	 * @param
	 * @param ydMangerDictionaryData
	 * @return
	 */
	Integer addTaskSize(YdMangerDictionaryData ydMangerDictionaryData);

}
