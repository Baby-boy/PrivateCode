package com.yd.gcj.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.YdMangerBanner;
import com.yd.gcj.mapper.YdMangerMapperBanner;
import com.yd.gcj.service.YdMangerServiceBanner;
import com.yd.gcj.tool.MapFactory;
import com.yd.gcj.tool.MapInitFactory;

@Service("YdMangerServiceBanner")
public class YdMangerServiceImplBanner implements YdMangerServiceBanner {
	
	
	@Autowired
	private YdMangerMapperBanner ydMangerMapperBanner;
	
	@Override
	public Map<String, Object> $queryAll() {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			mapInitFactory.init().setData(ydMangerMapperBanner.$queryAll());
		} catch (Exception e) {
			e.getStackTrace();
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

	
	@Override
	public Map<String, Object> $queryAllByPage(HashMap<String, Object> obj) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			Integer startPageNum = (Integer) obj.get("startPageNum");
			Integer queryPageNum = (Integer) obj.get("queryPageNum");
			mapInitFactory.init().setData(ydMangerMapperBanner.$queryAllByPage(startPageNum, queryPageNum));
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

	
	@Override
	public Map<String, Object> $queryByIds(HashMap<String, Object> map) {
		
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			
			@SuppressWarnings("unchecked")
			List<Integer> ids = (List<Integer>)map.get("banner_ids");
			
			mapInitFactory.init().setData(ydMangerMapperBanner.$queryByIds(ids));
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

	
	@Override
	public Map<String, Object> $queryById(HashMap<String, Object> map) {
		Integer id = (Integer) map.get("banner_id");
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			mapInitFactory.init().setData(ydMangerMapperBanner.$queryById(id));
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

	
	@Override
	public Map<String, Object> $insert(HashMap<String, Object> map) {
		
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			YdMangerBanner banner = (YdMangerBanner)MapFactory.toObject(map, YdMangerBanner.class);
			mapInitFactory.init().setData(ydMangerMapperBanner.$insert(banner));
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

	
	@Override
	public Map<String, Object> $update(HashMap<String, Object> map) {
		
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			map = MapFactory.removeByKeys(map, "banner_update_time","banner_create_time");
			YdMangerBanner banner = (YdMangerBanner)MapFactory.toObject(map, YdMangerBanner.class);
			banner.setBanner_update_time(new Date());
			mapInitFactory.init().setData(ydMangerMapperBanner.$update(banner));
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		
		return mapInitFactory.getMap();
	}

	
	@Override
	public Map<String, Object> $delete(HashMap<String, Object> map) {
		
		Integer banner_id = (Integer) map.get("banner_id");
		
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			mapInitFactory.init().setData(ydMangerMapperBanner.$delete(banner_id));
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		
		return mapInitFactory.getMap();
	}
	
	@Override
	public Integer $test() {
		Integer testv = ydMangerMapperBanner.$test();
		System.out.println(testv);
		return testv;
	}
	
}
