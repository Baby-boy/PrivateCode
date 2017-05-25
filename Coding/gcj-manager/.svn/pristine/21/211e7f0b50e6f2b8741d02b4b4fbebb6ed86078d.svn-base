package com.yd.gcj.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.YdMangerCollection;
import com.yd.gcj.mapper.YdMangerMapperCollection;
import com.yd.gcj.service.YdMangerServiceCollection;
import com.yd.gcj.tool.MapFactory;
import com.yd.gcj.tool.MapInitFactory;
import com.yd.gcj.tool.Values;

@Service("YdMangerServiceCollection")
public class YdMangerServiceImplCollection implements YdMangerServiceCollection {
	
	@Autowired
	private YdMangerMapperCollection ydMangerMapperCollection;
	
	@Override
	public Object $queryAll(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			Integer colle_uid = (Integer) map.get("colle_uid");
			mapInitFactory.init().setData(ydMangerMapperCollection.$queryAll(colle_uid));
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object $queryAllByPageNum(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			Integer colle_uid = (Integer) map.get("colle_uid");
			Integer startPageNum = (Integer) map.get("startPageNum");
			Integer queryPageNum = (Integer) map.get("queryPageNum");
			mapInitFactory.init().setData(ydMangerMapperCollection.$queryAllByPageNum(colle_uid, startPageNum, queryPageNum));
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object $queryCountNum(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			Integer colle_uid = (Integer) map.get("colle_uid");
			mapInitFactory.init().setData(ydMangerMapperCollection.$queryCountNum(colle_uid));
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object $insert(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			YdMangerCollection colle = (YdMangerCollection) MapFactory.toObject(map, YdMangerCollection.class);
			Integer success = ydMangerMapperCollection.$insert(colle);
			if(success!=null&&success>0){
				mapInitFactory.setMsg(Values.INITSUCCESSCODE, "收藏成功");
			}else{
				mapInitFactory.setMsg("501", "收藏失败");
			}
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object $delete(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			Integer colle_id = (Integer) map.get("colle_id");
			Integer success = ydMangerMapperCollection.$delete(colle_id);
			if(success!=null&&success>0){
				mapInitFactory.setMsg(Values.INITSUCCESSCODE, "删除成功");
			}else{
				mapInitFactory.setMsg("501", "删除失败");
			}
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

}
