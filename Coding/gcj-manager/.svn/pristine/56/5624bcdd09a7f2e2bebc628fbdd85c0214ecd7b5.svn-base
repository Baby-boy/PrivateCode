package com.yd.gcj.service.impl;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.YdMangerMessage;
import com.yd.gcj.mapper.YdMangerMapperMessage;
import com.yd.gcj.service.YdMangerServiceMessage;
import com.yd.gcj.tool.MapFactory;
import com.yd.gcj.tool.MapInitFactory;
import com.yd.gcj.tool.Values;

@Service("YdMangerServiceMessage")
public class YdMangerServiceImplMessage implements YdMangerServiceMessage{
	
	@Autowired
	private YdMangerMapperMessage ydMangerMapperMessage;
	
	@Override
	public Object $queryByUserIdAndByPageNum(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			Integer user_id = (Integer) map.get("user_id");
			Integer startPageNum = (Integer) map.get("startPageNum");
			Integer queryPageNum = (Integer) map.get("queryPageNum");
			Integer msg_isdel = (Integer) map.get("msg_isdel");
			Integer msg_type = (Integer) map.get("msg_type");
			mapInitFactory.init().setData(ydMangerMapperMessage.$queryByUserIdAndByPageNum(user_id, startPageNum, queryPageNum, msg_isdel, msg_type));
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object $queryCountNumByUserId(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			Integer user_id = (Integer) map.get("user_id");
			Integer startPageNum = (Integer) map.get("startPageNum");
			Integer queryPageNum = (Integer) map.get("queryPageNum");
			Integer msg_isdel = (Integer) map.get("msg_isdel");
			Integer msg_type = (Integer) map.get("msg_type");
			mapInitFactory.init().setData(ydMangerMapperMessage.$queryCountNumByUserId(user_id, startPageNum, queryPageNum, msg_isdel, msg_type));
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object $insert(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			YdMangerMessage message = (YdMangerMessage) MapFactory.toObject(map, YdMangerMessage.class);
			message.setMsg_create_time(new Date());
			Integer success = ydMangerMapperMessage.$insert(message);
			if(success!=null&&success>0){
				mapInitFactory.setMsg(Values.INITSUCCESSCODE, "发送成功");
			}else{
				mapInitFactory.setMsg("501", "发送失败");
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
			Integer msg_id = (Integer) map.get("msg_id");
			Integer success = ydMangerMapperMessage.$delete(msg_id);
			if(success!=null&&success > 0){
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
