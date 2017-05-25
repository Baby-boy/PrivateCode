package com.yd.gcj.service.impl;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.mapper.YdMangerMapperBankTR;
import com.yd.gcj.service.YdMangerServiceBankTR;
import com.yd.gcj.tool.MapInitFactory;

@Service("YdMangerServiceBankTR")
public class YdMangerServiceImplBankTR implements YdMangerServiceBankTR{
	
	@Autowired
	private YdMangerMapperBankTR ydMangerMapperBankTR;
	
	@Override
	public Object $queryAll(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			Integer btr_uid = (Integer) map.get("btr_uid");
			mapInitFactory.init().setData(ydMangerMapperBankTR.$queryAll(btr_uid));
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object $queryByDate(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			long dateStr = (long) map.get("btr_date");
			Date btr_date = new Date(dateStr);
			mapInitFactory.init().setData(ydMangerMapperBankTR.$queryByDate(btr_date));
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object $queryBySql(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();// ydMangerMapperBankTR.$queryBySql(sql);
	}

	@Override
	public Object $queryById(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();// ydMangerMapperBankTR.$queryById(btr_id);
	}

	@Override
	public Object $queryCountNum(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();// ydMangerMapperBankTR.$queryCountNum(btr_uid);
	}

	@Override
	public Object $queryCountNum() {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();// ydMangerMapperBankTR.$queryCountNum();
	}

	@Override
	public Object $insert(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();// ydMangerMapperBankTR.$insert(banktr);
	}
	
}
