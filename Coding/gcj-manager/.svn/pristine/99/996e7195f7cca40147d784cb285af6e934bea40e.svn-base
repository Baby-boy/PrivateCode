package com.yd.gcj.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.YdMangerTaskMsg;
import com.yd.gcj.mapper.YdMangerMapperTaskMsg;
import com.yd.gcj.service.YdMangerServiceTaskMsg;
import com.yd.gcj.tool.MapFactory;
import com.yd.gcj.tool.MapInitFactory;
import com.yd.gcj.tool.Values;

@Service("YdMangerServiceTaskMsg")
public class YdMangerServiceImplTaskMsg implements YdMangerServiceTaskMsg{
	
	@Autowired
	private YdMangerMapperTaskMsg ydMangerMapperTaskMsg;
	
	@Override
	public Object $queryByTid(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		Integer taskmsg_tid = (Integer) map.get("taskmsg_tid");
		mapInitFactory.init().setData(ydMangerMapperTaskMsg.$queryByTid(taskmsg_tid));
		return mapInitFactory.getMap();
	}

	@Override
	public Object $queryById(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			Integer taskmsg_id = (Integer) map.get("taskmsg_id");
			mapInitFactory.init().setData(ydMangerMapperTaskMsg.$queryById(taskmsg_id));
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object $insert(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			YdMangerTaskMsg taskmsg = (YdMangerTaskMsg) MapFactory.toObject(map, YdMangerTaskMsg.class);
			Integer success = ydMangerMapperTaskMsg.$insert(taskmsg);
			if(success > 0){
				mapInitFactory.setMsg(Values.INITSUCCESSCODE, "发布成功");
			}else{
				mapInitFactory.setMsg("501", "发布失败，请稍后再试");
			}
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object $update(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		
		try {
			YdMangerTaskMsg taskmsg = (YdMangerTaskMsg) MapFactory.toObject(map, YdMangerTaskMsg.class);
			Integer success = ydMangerMapperTaskMsg.$update(taskmsg);
			if(success > 0){
				mapInitFactory.setMsg(Values.INITSUCCESSCODE, "更新成功");
			}else{
				mapInitFactory.setMsg("501", "更新失败");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object $delete(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		
		try {
			Integer taskmsg_id = (Integer) map.get("taskmsg_id");
			Integer success = ydMangerMapperTaskMsg.$delete(taskmsg_id);
			if(success > 0){
				mapInitFactory.setMsg(Values.INITSUCCESSCODE,"删除成功");
			}else{
				mapInitFactory.setMsg("501", "删除失败");
			}
		} catch (Exception e) {
			
		}
		
		return mapInitFactory.getMap();
	}
	
}
