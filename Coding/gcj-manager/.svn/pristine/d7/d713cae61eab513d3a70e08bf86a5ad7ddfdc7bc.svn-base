package com.yd.gcj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.YdMangerAwards;
import com.yd.gcj.mapper.YdMangerMapperAwards;
import com.yd.gcj.service.YdMangerServiceAwards;
@Service("YdMangerServiceAwards")
public class YdMangerServiceImplAwards implements YdMangerServiceAwards{
	
	@Autowired
	private YdMangerMapperAwards ydMangerMapperAwards;
	
	@Override
	public List<YdMangerAwards> $queryAll(Integer awards_caseid) {
		return ydMangerMapperAwards.$queryAll(awards_caseid);
	}

	@Override
	public YdMangerAwards $queryById(Integer awards_id) {
		return ydMangerMapperAwards.$queryById(awards_id);
	}

	@Override
	public Integer $queryCountNum(Integer awards_caseid) {
		return ydMangerMapperAwards.$queryCountNum(awards_caseid);
	}

	@Override
	public Integer $insert(YdMangerAwards awards) {
		return ydMangerMapperAwards.$insert(awards);
	}

	@Override
	public Integer $update(YdMangerAwards awards) {
		return ydMangerMapperAwards.$update(awards);
	}

	@Override
	public Integer $delete(Integer awards_id) {
		return ydMangerMapperAwards.$delete(awards_id);
	}
	
}
