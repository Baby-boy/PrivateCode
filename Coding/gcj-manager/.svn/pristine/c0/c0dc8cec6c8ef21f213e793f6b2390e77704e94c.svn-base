package com.yd.gcj.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.YdMangerArticleClass;
import com.yd.gcj.system.mapper.YdMangerMapperSystemArticleType;
import com.yd.gcj.system.service.YdMangerServiceSystemArticleType;


@Service("ydMangerServiceSystemArticleType")
public class YdMangerServiceImplSystemArticleType implements YdMangerServiceSystemArticleType {

	@Autowired
	private YdMangerMapperSystemArticleType ydMangerMapperSystemArticleType;
	
	//查询文章的所有类型
	@Override
	public List<YdMangerArticleClass> queryAllArticleType() {
		List<YdMangerArticleClass> articleClassList = ydMangerMapperSystemArticleType.queryAllArticleType();
		return articleClassList;
	}

	//查询文章类型的所有信息
	@Override
	public List<YdMangerArticleClass> queryAllArticleTypeInfo(String ac_name) {
		List<YdMangerArticleClass> articleTypeList = ydMangerMapperSystemArticleType.queryAllArticleTypeInfo(ac_name);
		return articleTypeList;
	}

	//添加文章类型
	@Override
	public Integer addArticleClass(YdMangerArticleClass ydMangerArticleClass) {
		Integer addNum = ydMangerMapperSystemArticleType.addArticleClass(ydMangerArticleClass);
		return addNum;
	}

	//根据指定的ac_id删除文章类型
	@Override
	public Integer deleteArticleClassByAcId(Integer ac_id) {
		Integer delNum = ydMangerMapperSystemArticleType.deleteArticleClassByAcId(ac_id);
		return delNum;
	}

	//查询当前文章类型
	@Override
	public YdMangerArticleClass queryArticleTypeByAcId(Integer ac_id) {
		YdMangerArticleClass ydMangerArticleClass = ydMangerMapperSystemArticleType.queryArticleTypeByAcId(ac_id);
		return ydMangerArticleClass;
	}

	//修改文章类型
	@Override
	public Integer updateArticleClassByAcId(YdMangerArticleClass ydMangerArticleClass) {
		Integer updateNum = ydMangerMapperSystemArticleType.updateArticleClassByAcId(ydMangerArticleClass);
		return updateNum;
	}

	
}
