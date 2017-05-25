package com.yd.gcj.service.impl;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.YdMangerAttention;
import com.yd.gcj.entity.YdMangerUser;
import com.yd.gcj.mapper.YdMangerMapperAttention;
import com.yd.gcj.mapper.YdMangerMapperUser;
import com.yd.gcj.service.YdMangerServiceAttention;
import com.yd.gcj.tool.MapInitFactory;
import com.yd.gcj.tool.Values;

@Service("YdMangerServiceAttention")
public class YdMangerServiceImplAttention implements YdMangerServiceAttention {
	
	@Autowired
	private YdMangerMapperAttention mapperAttention;
	@Autowired
	private YdMangerMapperUser mapperUser;
	
	@Override
	public Object $queryAll(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		return mapInitFactory.getMap();
	}

	@Override
	public Object $queryACountNum(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		return mapInitFactory.getMap();
	}

	@Override
	public Object $queryBCountNum(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		
		return mapInitFactory.getMap();
	}

	@Override
	public Object $addAtten(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			Integer att_aid = (Integer) map.get("att_aid");
			Integer att_bid = (Integer) map.get("att_bid");
			
			YdMangerUser user = mapperUser.$queryById(att_bid);
			
			YdMangerAttention attention = new YdMangerAttention();
			
			attention.setAtt_aid(att_aid);
			attention.setAtt_bid(att_bid);
			attention.setAtt_uname(user.getUser_name());
			attention.setAtt_pname(user.getUser_ename());
			attention.setAtt_create_time(new Date());
			Integer isExist = mapperAttention.$isExist(att_aid, att_bid);
			if(isExist!=null){
				mapInitFactory.setMsg("502", "已经关注过了");
			}else{
				Integer success = mapperAttention.$insert(attention);
				if(success > 0){
					mapInitFactory.setMsg(Values.INITSUCCESSCODE, "关注成功");
				}else{
					mapInitFactory.setMsg("501", "关注失败");
				}
			}
			
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object $delAtten(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			Integer att_aid = (Integer) map.get("att_aid");
			Integer att_bid = (Integer) map.get("att_bid");
			Integer att_id = mapperAttention.$isExist(att_aid, att_bid);
			if(att_id!=null){
				Integer success = mapperAttention.$delete(att_id);
				if(success>0){
					mapInitFactory.setMsg(Values.INITSUCCESSCODE, "取消成功");
				}else{
					mapInitFactory.setMsg("501", "取消失败");
				}
			}else{
				mapInitFactory.setMsg("501", "参数有误");
			}
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

}
