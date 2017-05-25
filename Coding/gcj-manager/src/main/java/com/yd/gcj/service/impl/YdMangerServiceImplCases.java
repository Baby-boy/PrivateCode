package com.yd.gcj.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.YdMangerCases;
import com.yd.gcj.mapper.YdMangerMapperCases;
import com.yd.gcj.service.YdMangerServiceCases;
import com.yd.gcj.tool.MapFactory;
import com.yd.gcj.tool.MapInitFactory;
import com.yd.gcj.tool.Values;
@Service("YdMangerServiceCases")
public class YdMangerServiceImplCases implements YdMangerServiceCases{
	
	@Autowired
	private YdMangerMapperCases ydMangerMapperCases;
	
	@Override
	public Object $queryAll(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			Integer case_uid = (Integer) map.get("case_uid");
			mapInitFactory.init().setData(ydMangerMapperCases.$queryAllByUserId(case_uid));
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object $queryByPageNum(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			
			Integer case_uid = (Integer) map.get("case_uid");
			Integer startPageNum = (Integer) map.get("startPageNum");
			Integer queryPageNum = (Integer) map.get("queryPageNum");
			
			mapInitFactory
			.init()
			.setData(ydMangerMapperCases.$queryByPageNum(case_uid, startPageNum, queryPageNum));
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object $queryById(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			Integer case_id = (Integer) map.get("case_id");
			mapInitFactory.init().setData(ydMangerMapperCases.$queryById(case_id));
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object $queryCountNum(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			Integer case_uid = (Integer) map.get("case_uid");
			Integer countNum = ydMangerMapperCases.$queryCountNum(case_uid);
			if(countNum!=null){
				mapInitFactory.init().setData(countNum);
			}else{
				mapInitFactory.setMsg("501", "参数异常");
			}
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object $insert(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		mapInitFactory.setSystemError();
		YdMangerCases cases = (YdMangerCases) MapFactory.toObject(map, YdMangerCases.class);
		Integer success = ydMangerMapperCases.$insert(cases);
		if(success != null && success > 0){
			mapInitFactory.setMsg(Values.INITSUCCESSCODE, "添加成功");
		}else{
			mapInitFactory.setMsg("501", "参数异常");
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object $update(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		mapInitFactory.setSystemError();
		YdMangerCases cases = (YdMangerCases) MapFactory.toObject(map, YdMangerCases.class);
		Integer success = ydMangerMapperCases.$update(cases);
		if(success != null && success > 0){
			mapInitFactory.setMsg(Values.INITSUCCESSCODE, "更新成功");
		}else{
			mapInitFactory.setMsg("501", "参数异常");
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object $delete(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		mapInitFactory.setSystemError();
		Integer case_id = (Integer) map.get("case_id");
		Integer success = ydMangerMapperCases.$delete(case_id);
		if(success != null && success > 0){
			mapInitFactory.setMsg(Values.INITSUCCESSCODE, "删除成功");
		}else{
			mapInitFactory.setMsg("501", "参数异常");
		}
		return mapInitFactory.getMap();
	}
	
}
