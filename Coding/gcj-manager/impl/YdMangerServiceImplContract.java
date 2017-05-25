package com.yd.gcj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.YdMangerContract;
import com.yd.gcj.mapper.YdMangerMapperContract;
import com.yd.gcj.service.YdMangerServiceContract;

@Service("YdMangerServiceContract")
public class YdMangerServiceImplContract implements YdMangerServiceContract{
	
	@Autowired
	private YdMangerMapperContract ydMangerMapperContract;
	
	@Override
	public YdMangerContract $queryById(Integer contract_id) {
		return ydMangerMapperContract.$queryById(contract_id);
	}

	@Override
	public YdMangerContract $queryBySql(String sql) {
		return ydMangerMapperContract.$queryBySql(sql);
	}

	@Override
	public Integer $insert(YdMangerContract contract) {
		return ydMangerMapperContract.$insert(contract);
	}

	@Override
	public Integer $update(YdMangerContract contract) {
		return ydMangerMapperContract.$update(contract);
	}

	@Override
	public Integer $delete(Integer contract_id) {
		return ydMangerMapperContract.$delete(contract_id);
	}

}
