package com.yd.gcj.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.YdMangerDictionaryData;
import com.yd.gcj.system.mapper.YdMangerMapperSystemTaskSize;
import com.yd.gcj.system.service.YdMangerServiceSystemTaskSize;

@Service("ydMangerServiceSystemTaskSize")
public class YdMangerServiceImplSystemTaskSize implements YdMangerServiceSystemTaskSize{

	@Autowired
	private YdMangerMapperSystemTaskSize ydMangerMapperSystemTaskSize;
	
	//查询所有的任务规模
	@Override
	public List<YdMangerDictionaryData> queryAllTaskSize(String dictdata_value) {
		List<YdMangerDictionaryData> dictionaryDataList = ydMangerMapperSystemTaskSize.queryAllTaskSize(dictdata_value);
		return dictionaryDataList;
	}

	//删除任务规模
	@Override
	public Integer deleteTaskSzie(Integer dict_id) {
		Integer delNum = ydMangerMapperSystemTaskSize.deleteTaskSzie(dict_id);
		return delNum;
	}

	//根据dict_id查询当前任务规模信息
	@Override
	public YdMangerDictionaryData queryTaskSizeByDictId(Integer dict_id) {
		YdMangerDictionaryData ydMangerDictionaryData = ydMangerMapperSystemTaskSize.queryTaskSizeByDictId(dict_id);
		return ydMangerDictionaryData;
	}

	//修改任务规模
	@Override
	public Integer updateTaskSize(YdMangerDictionaryData ydMangerDictionaryData) {
		Integer updateNum = ydMangerMapperSystemTaskSize.updateTaskSize(ydMangerDictionaryData);
		return updateNum;
	}
	
	//添加搜索条件任务规模
	@Override
	public Integer addTaskSize(YdMangerDictionaryData ydMangerDictionaryData) {
		Integer addNum = ydMangerMapperSystemTaskSize.addTaskSize(ydMangerDictionaryData);
		return addNum;
	}

	
}
