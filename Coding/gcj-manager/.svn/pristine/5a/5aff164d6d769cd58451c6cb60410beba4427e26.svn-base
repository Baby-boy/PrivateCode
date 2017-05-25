package com.yd.gcj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.YdMangerUserLabel;
import com.yd.gcj.mapper.YdMangerMapperUserLabel;
import com.yd.gcj.service.YdMangerServiceUserLabel;

@Service("YdMangerServiceUserLabel")
public class YdMangerServiceImplUserLabel implements YdMangerServiceUserLabel{
	
	@Autowired
	private YdMangerMapperUserLabel ydMangerMapperUserLabel;
	
	@Override
	public List<YdMangerUserLabel> $queryAll(Integer userl_uid) {
		return ydMangerMapperUserLabel.$queryAll(userl_uid);
	}

	@Override
	public YdMangerUserLabel $queryById(Integer userl_id) {
		return ydMangerMapperUserLabel.$queryById(userl_id);
	}

	@Override
	public Integer $delete(Integer userl_id) {
		return ydMangerMapperUserLabel.$delete(userl_id);
	}

	@Override
	public Integer $insert(YdMangerUserLabel userLabel) {
		return ydMangerMapperUserLabel.$insert(userLabel);
	}

	@Override
	public Integer $queryCountNumById(Integer userl_id) {
		return ydMangerMapperUserLabel.$queryCountNumById(userl_id);
	}

}
