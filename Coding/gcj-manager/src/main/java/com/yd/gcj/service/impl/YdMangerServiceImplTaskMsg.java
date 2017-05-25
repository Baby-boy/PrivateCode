package com.yd.gcj.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.YdMangerMessage;
import com.yd.gcj.entity.YdMangerTaskMsg;
import com.yd.gcj.mapper.YdMangerMapperMessage;
import com.yd.gcj.mapper.YdMangerMapperTaskMsg;
import com.yd.gcj.service.YdMangerServiceTaskMsg;
import com.yd.gcj.tool.MapFactory;
import com.yd.gcj.tool.MapInitFactory;
import com.yd.gcj.tool.Values;

@Service("YdMangerServiceTaskMsg")
public class YdMangerServiceImplTaskMsg implements YdMangerServiceTaskMsg {

	@Autowired
	private YdMangerMapperTaskMsg ydMangerMapperTaskMsg;

	@Autowired
	private YdMangerMapperMessage mapperMessage;

	@Override
	public List<YdMangerTaskMsg> $queryByTid(Integer taskId) {
		return ydMangerMapperTaskMsg.$queryByTid(taskId);
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
	public Object $insert(YdMangerTaskMsg taskMsg) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		Date date = new Date();
		taskMsg.setTaskmsg_create_time(date);
		Integer success = ydMangerMapperTaskMsg.$insert(taskMsg);
		if (success > 0) {
			YdMangerMessage message = new YdMangerMessage();
			message.setMsg_said(taskMsg.getTaskmsg_uid());
			message.setMsg_sbid(0);
			message.setMsg_isdel(0);
			message.setMsg_contents(taskMsg.getTaskmsg_contents());
			message.setMsg_tid(taskMsg.getTaskmsg_tid());
			message.setMsg_type(3);
			message.setMsg_create_time(date);
			Integer msgSuccess = mapperMessage.$insert(message);
			if (msgSuccess > 0) {
				mapInitFactory.setMsg(Values.INITSUCCESSCODE, "公告发布成功！");
			} else {
				mapInitFactory.setMsg("502", "发布失败，请稍后再试！");
			}
		} else {
			mapInitFactory.setMsg("501", "发布失败，请稍后再试");
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object $update(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();

		try {
			YdMangerTaskMsg taskmsg = (YdMangerTaskMsg) MapFactory.toObject(map, YdMangerTaskMsg.class);
			Integer success = ydMangerMapperTaskMsg.$update(taskmsg);
			if (success > 0) {
				mapInitFactory.setMsg(Values.INITSUCCESSCODE, "更新成功");
			} else {
				mapInitFactory.setMsg("501", "更新失败");
			}
		} catch (Exception e) {
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object $delete(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();

		try {
			Integer taskmsg_id = (Integer) map.get("taskmsg_id");
			Integer success = ydMangerMapperTaskMsg.$delete(taskmsg_id);
			if (success > 0) {
				mapInitFactory.setMsg(Values.INITSUCCESSCODE, "删除成功");
			} else {
				mapInitFactory.setMsg("501", "删除失败");
			}
		} catch (Exception e) {

		}

		return mapInitFactory.getMap();
	}

}
