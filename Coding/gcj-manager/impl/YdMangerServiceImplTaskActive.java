package com.yd.gcj.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.YdMangerTaskActive;
import com.yd.gcj.mapper.YdMangerMapperTaskActive;
import com.yd.gcj.service.YdMangerServiceTaskActive;
import com.yd.gcj.tool.MapFactory;
import com.yd.gcj.tool.MapInitFactory;
import com.yd.gcj.tool.Values;

@Service("YdMangerServiceTaskActive")
public class YdMangerServiceImplTaskActive implements YdMangerServiceTaskActive{
	
	@Autowired
	private YdMangerMapperTaskActive ydMangerMapperTaskActive;
	
	@Override
	public Object $queryByTid(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			Integer taska_tid = (Integer) map.get("taska_tid");
			mapInitFactory.init().setData(ydMangerMapperTaskActive.$queryByTid(taska_tid));
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object $insert(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			YdMangerTaskActive active = (YdMangerTaskActive) MapFactory.toObject(map, YdMangerTaskActive.class);
			Integer success = ydMangerMapperTaskActive.$insert(active);
			if(success!=null&&success>0){
				mapInitFactory.setMsg(Values.INITSUCCESSCODE, "回复成功");
			}else{
				mapInitFactory.setMsg("501", "回复失败");
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
			Integer taska_id = (Integer) map.get("taska_id");
			Integer success = ydMangerMapperTaskActive.$delete(taska_id);
			if(success>0){
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
