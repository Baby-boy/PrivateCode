package com.yd.gcj.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.YdMangerArticle;
import com.yd.gcj.mapper.YdMangerMapperArticle;
import com.yd.gcj.service.YdMangerServiceArticle;
import com.yd.gcj.tool.MapInitFactory;

@Service("YdMangerServiceArticle")
public class YdMangerServiceImplArticle implements YdMangerServiceArticle{
	
	@Autowired
	private YdMangerMapperArticle mapperArticle;
	
	@Override
	public Object $insert(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		mapInitFactory.setSystemError();
		return mapInitFactory.getMap();//ydMangerServiceArticle.$insert(article);
	}

	@Override
	public Object $delete(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();// ydMangerServiceArticle.$delete(article_id);
	}

	@Override
	public Object $update(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();// ydMangerServiceArticle.$update(article);
	}

	@Override
	public List<YdMangerArticle> $queryAll(Integer type,Integer startPageNum,Integer queryPageNum) {
		return mapperArticle.$queryAll(type,startPageNum, queryPageNum);
	}

	@Override
	public Object $queryById(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();// ydMangerServiceArticle.$queryById(article_id);
	}

	@Override
	public Object $queryCountNum() {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();// ydMangerServiceArticle.$queryCountNum();
	}

	@Override
	public Integer queryCountAll(Integer type) {
		return mapperArticle.$queryCountAll(type);
	}
	
}
