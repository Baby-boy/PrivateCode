package com.yd.gcj.service.impl;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.YdMangerTender;
import com.yd.gcj.mapper.YdMangerMapperTender;
import com.yd.gcj.service.YdMangerServiceTender;
import com.yd.gcj.tool.MapFactory;
import com.yd.gcj.tool.MapInitFactory;
import com.yd.gcj.tool.Values;

@Service("YdMangerServiceTender")
public class YdMangerServiceImplTender implements YdMangerServiceTender{
	
	@Autowired
	private YdMangerMapperTender mapperTender;
	
	@Override
	public Object $queryByTid(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			Integer tender_tid = (Integer) map.get("tender_tid");
			Integer startPageNum = (Integer) map.get("startPageNum");
			Integer queryPageNum = (Integer) map.get("queryPageNum"); 
			mapInitFactory.init().setData(mapperTender.$queryByTid(tender_tid,startPageNum,queryPageNum));
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object $queryByType(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			Integer tender_tid = (Integer) map.get("tender_tid");
			Integer tender_type = (Integer) map.get("tender_type");
			if(tender_tid!=null&&tender_type!=null&&tender_tid!=0&&tender_type!=0){
				mapInitFactory.init().setData(mapperTender.$queryByType(tender_tid, tender_type));
			}else{
				mapInitFactory.setMsg("501", "参数有误");
			}
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object $queryBySid(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			Integer tender_uid = (Integer) map.get("tender_uid");
			Integer startPageNum = (Integer) map.get("startPageNum");
			Integer queryPageNum = (Integer) map.get("queryPageNum");
			if(tender_uid!=null&&startPageNum!=null&&queryPageNum!=null){
				if(tender_uid>0&&startPageNum>=0&&queryPageNum<15){
					mapInitFactory.init().setData(mapperTender.$queryBySid(tender_uid, startPageNum, queryPageNum));
				}else{
					mapInitFactory.setMsg("502", "参数值异常");
				}
			}else{
				mapInitFactory.setMsg("501", "参数有误");
			}
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object $queryCountNumBySid(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		Integer tender_uid = (Integer) map.get("tender_uid");
		if(tender_uid!=null){
			if(tender_uid>0){
				mapInitFactory.init().setData(mapperTender.$queryCountNumBySid(tender_uid));
			}else{
				mapInitFactory.setMsg("502", "参数值异常");
			}
		}else{
			mapInitFactory.setMsg("501", "参数有误");
		}
		return mapInitFactory.getMap();
	}
	
	@Override
	public Object $queryCountNumByTid(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			Integer tender_tid = (Integer) map.get("tender_tid");
			mapInitFactory.init().setData(mapperTender.$queryCountNumByTid(tender_tid));
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}
	
	@Override
	public Object $isExist(HashMap<String, Object> map){
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			Integer tender_tid = (Integer) map.get("tender_tid");
			Integer tender_uid = (Integer) map.get("tender_uid");
			Integer isExist = mapperTender.$isExist(tender_tid, tender_uid);
			if(isExist!=null){
				mapInitFactory.init().setData(isExist);
			}else{
				mapInitFactory.init().setData(0);
			}
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}
	
	@Override
	public Object $insert(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			Integer tender_tid = (Integer) map.get("tender_tid");
			Integer tender_uid = (Integer) map.get("tender_uid");
			Integer isExist = mapperTender.$isExist(tender_tid, tender_uid);
			if(isExist<1){
				YdMangerTender tender = (YdMangerTender) MapFactory.toObject(map, YdMangerTender.class);
				tender.setTender_create_time(new Date());
				tender.setTender_update_time(new Date());
				Integer success = mapperTender.$insert(tender);
				if(success>0){
					mapInitFactory.setMsg(Values.INITSUCCESSCODE, "投标成功");
				}else{
					mapInitFactory.setMsg("502", "投标失败");
				}
			}else{
				mapInitFactory.setMsg("501", "您该任务已经进行过投标了");
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
			Integer tender_id = (Integer) map.get("tender_id");
			if(tender_id!=null&&tender_id>0){
				Integer tender_state = mapperTender.$queryState(tender_id);
				if(tender_state==0){
					YdMangerTender tender = (YdMangerTender) MapFactory.toObject(map, YdMangerTender.class);
					tender.setTender_update_time(new Date());
					Integer success = mapperTender.$update(tender);
					if(success>0){
						mapInitFactory.setMsg(Values.INITSUCCESSCODE, "修改成功");
					}else{
						mapInitFactory.setMsg("501", "修改失败，请稍后再试");
					}
				}
			}
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object $updateState(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			Integer tender_id = (Integer) map.get("tender_id");
			Integer tender_state = (Integer) map.get("tender_state");
			Integer success = mapperTender.$updateState(tender_id, tender_state, new Date());
			if(success>0){
				mapInitFactory.setMsg(Values.INITSUCCESSCODE, "操作成功");
			}else{
				mapInitFactory.setMsg("501", "操作失败");
			}
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}
	
	@Override
	public Object $selection(HashMap<String, Object> map){
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			Integer tender_id = (Integer) map.get("tender_id");
			Integer tender_type = (Integer) map.get("tender_type");
			
			Integer tender_state = mapperTender.$queryState(tender_id);
			tender_state = tender_state!=null?tender_state:0;
			switch (tender_state) {
				case 1:
					Integer groupCode = 2;
					groupCode = tender_type==1||tender_id==2?tender_type:2;
					Integer success = mapperTender.$selection(tender_id, groupCode);
					if(success!=null&&success>0){
						mapInitFactory.setMsg(Values.INITSUCCESSCODE, "操作成功");
					}else{
						mapInitFactory.setMsg("502", "操作失败，请稍后再试");
					}
					break;
				default:
					mapInitFactory.setMsg("501", "参数有误");
					break;
			}
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}
	/*
	@Override
	public Object $leader(HashMap<String, Object> map){
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			Integer tender_id = (Integer) map.get("tender_id");
			Integer tender_state = mapperTender.$queryState(tender_id);
			tender_state = tender_state!=null?tender_state:0;
			switch (tender_state) {
			case 1:
				Integer success = mapperTender.$selection(tender_id, 1);
				if(success!=null&&success>0){
					mapInitFactory.setMsg(Values.INITSUCCESSCODE, "操作成功");
				}else{
					mapInitFactory.setMsg("502", "操作失败，请稍后再试");
				}
				break;
			default:
				mapInitFactory.setMsg("501", "参数有误");
				break;
			}
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}*/
	
	@Override
	public Object $delete(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			Integer tender_id = (Integer) map.get("tender_id");
			Integer success = mapperTender.$delete(tender_id);
			if(success!=null&&success>0){
				mapInitFactory.setMsg(Values.INITSUCCESSCODE, "操作成功");
			}else{
				mapInitFactory.setMsg("501", "操作失败，请稍后再试");
			}
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

}
