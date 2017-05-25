package com.yd.gcj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.YdMangerTaskTemp;
import com.yd.gcj.mapper.YdMangerMapperTaskTemp;
import com.yd.gcj.service.YdMangerServiceTaskTemp;

@Service("YdMangerServiceTaskTemp")
public class YdMangerServiceImplTaskTemp implements YdMangerServiceTaskTemp{
	
	@Autowired
	private YdMangerMapperTaskTemp ydMangerMapperTaskTemp;
	
	@Override
	public List<YdMangerTaskTemp> $queryAll() {
		return ydMangerMapperTaskTemp.$queryAll();
	}

	@Override
	public YdMangerTaskTemp $queryById(Integer tasktemp_id) {
		return ydMangerMapperTaskTemp.$queryById(tasktemp_id);
	}

	@Override
	public Integer $queryCountNum() {
		return ydMangerMapperTaskTemp.$queryCountNum();
	}

	@Override
	public Integer $insert(YdMangerTaskTemp tasktemp) {
		return ydMangerMapperTaskTemp.$insert(tasktemp);
	}

	@Override
	public Integer $update(YdMangerTaskTemp tasktemp) {
		return ydMangerMapperTaskTemp.$update(tasktemp);
	}

	@Override
	public Integer $delete(Integer tasktemp_id) {
		return ydMangerMapperTaskTemp.$delete(tasktemp_id);
	}

}
