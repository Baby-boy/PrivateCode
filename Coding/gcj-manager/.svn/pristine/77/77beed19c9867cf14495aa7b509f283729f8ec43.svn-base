package com.yd.gcj.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.YdMangerTaskModel;
import com.yd.gcj.mapper.YdMangerMapperTaskModel;
import com.yd.gcj.service.YdMangerServiceTaskModel;
import com.yd.gcj.tool.MapFactory;
import com.yd.gcj.tool.MapInitFactory;
import com.yd.gcj.tool.Values;
@Service("YdMangerServiceTaskModel")
public class YdMangerServiceImplTaskModel implements YdMangerServiceTaskModel{
	
	@Autowired
	private YdMangerMapperTaskModel ydMangerMapperTaskModel;
	
	@Override
	public List<YdMangerTaskModel> $queryByTid(Integer taskId) {
		List<YdMangerTaskModel> taskModels = ydMangerMapperTaskModel.$queryByTid(taskId);
		return taskModels;
	}

	@Override
	public Object $queryById(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			Integer taskmd_id = (Integer) map.get("taskmd_id");
			mapInitFactory.init().setData(ydMangerMapperTaskModel.$queryById(taskmd_id));
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object $update(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		YdMangerTaskModel tm = (YdMangerTaskModel) MapFactory.toObject(map, YdMangerTaskModel.class);
		Integer success = ydMangerMapperTaskModel.$update(tm);
		if(success > 0){
			mapInitFactory.setMsg(Values.INITSUCCESSCODE, "更新成功");
		}else{
			mapInitFactory.setMsg("501", "更新失败，请检查后再试");
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object $updateState(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			Integer taskmd_tid = (Integer) map.get("taskmd_tid");
			Integer taskmd_state = (Integer) map.get("taskmd_state");
			Date taskmd_update_time = new Date();
			Integer success = ydMangerMapperTaskModel.$updateState(taskmd_tid, taskmd_state, taskmd_update_time);
			if(success > 0){
				mapInitFactory.setMsg(Values.INITSUCCESSCODE, "更新成功");
			}else{
				mapInitFactory.setMsg("501", "更新失败，请检查后再试");
			}
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object $insert(YdMangerTaskModel taskModel) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			Date date = new Date();
			taskModel.setTaskmd_create_time(date);
			taskModel.setTaskmd_update_time(date);
			Integer success = ydMangerMapperTaskModel.$insert(taskModel);
			if(success > 0){
				mapInitFactory.setMsg(Values.INITSUCCESSCODE, "提交成功，等待检查审核");
			}else{
				mapInitFactory.setMsg("501", "添加失败，请检查后再试");
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
			Integer taskmd_id = (Integer) map.get("taskmd_id");
			Integer success = ydMangerMapperTaskModel.$delete(taskmd_id);
			if(success > 0){
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
