package com.yd.gcj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.YdMangerTenderCase;
import com.yd.gcj.mapper.YdMangerMapperTenderCase;
import com.yd.gcj.service.YdMangerServiceTenderCase;

@Service("YdMangerServiceTenderCase")
public class YdMangerServiceImplTenderCase implements YdMangerServiceTenderCase{
	
	@Autowired
	private YdMangerMapperTenderCase ydMangerMapperTenderCase;
	
	@Override
	public List<YdMangerTenderCase> $queryByTenderId(Integer tc_tenderid) {
		return ydMangerMapperTenderCase.$queryByTenderId(tc_tenderid);
	}

	@Override
	public Integer $queryCountNumByTenderId(Integer tc_tenderid) {
		return ydMangerMapperTenderCase.$queryCountNumByTenderId(tc_tenderid);
	}

	@Override
	public Integer $insert(YdMangerTenderCase tc) {
		return ydMangerMapperTenderCase.$insert(tc);
	}

	@Override
	public Integer $delete(Integer tc_tenderid, Integer tc_caseid) {
		return ydMangerMapperTenderCase.$delete(tc_tenderid, tc_caseid);
	}
	
}
