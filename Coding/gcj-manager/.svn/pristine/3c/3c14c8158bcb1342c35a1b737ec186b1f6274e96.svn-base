package com.yd.gcj.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.YdMangerSpcs;
import com.yd.gcj.mapper.YdMangerMapperSpcs;
import com.yd.gcj.service.YdMangerServiceSpcs;
import com.yd.gcj.tool.MapFactory;
import com.yd.gcj.tool.MapInitFactory;
import com.yd.gcj.tool.Values;
@Service("YdMangerServiceSpcs")
public class YdMangerServiceImplSpcs implements YdMangerServiceSpcs{
	
	@Autowired
	private YdMangerMapperSpcs ydMangerMapperSpcs;
	
	@Override
	public Object $queryAll(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			Integer spcs_sid = (Integer) map.get("spcs_sid");
			mapInitFactory.init().setData(ydMangerMapperSpcs.$queryAll(spcs_sid));
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}
	
	@Override
	public List<YdMangerSpcs> $queryByUserId(Integer userId) {
		return ydMangerMapperSpcs.$queryByUserId(userId);
	}
	
	@Override
	public Object $queryAllByPageNum(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			Integer spcs_sid = (Integer) map.get("spcs_sid");
			Integer startPageNum = (Integer) map.get("startPageNum");
			Integer queryPageNum = (Integer) map.get("queryPageNum");
			mapInitFactory.init().setData(ydMangerMapperSpcs.$queryAllByPageNum(spcs_sid, startPageNum, queryPageNum));
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object $queryById(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			Integer spcs_id = (Integer) map.get("spcs_id");
			mapInitFactory.init().setData(ydMangerMapperSpcs.$queryById(spcs_id));
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object $insert(YdMangerSpcs spcs) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		Date date = new Date();
		spcs.setSpcs_update_time(date);
		spcs.setSpcs_create_time(date);
		Integer success = ydMangerMapperSpcs.$insert(spcs);
		if(success > 0){
			mapInitFactory.setMsg(Values.INITSUCCESSCODE, "评价成功");
		}else{
			mapInitFactory.setMsg("501", "参数异常");
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object $update(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		YdMangerSpcs spcs = (YdMangerSpcs) MapFactory.toObject(map, YdMangerSpcs.class);			
		Integer success = ydMangerMapperSpcs.$update(spcs);
		if(success != null && success > 0){
			mapInitFactory.setMsg(Values.INITSUCCESSCODE, "");
		}else{
			mapInitFactory.setMsg("501", "参数异常");
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object $delete(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		Integer spcs_id = (Integer) map.get("spcs_id");
		Integer success = ydMangerMapperSpcs.$delete(spcs_id);
		if(success != null && success > 0){
			mapInitFactory.setMsg(Values.INITSUCCESSCODE, "删除成功");
		}else{
			mapInitFactory.setMsg("501", "删除失败");
		}
		return mapInitFactory.getMap();
	}

}
