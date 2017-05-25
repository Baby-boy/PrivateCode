package com.yd.gcj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.YdMangerSkill;
import com.yd.gcj.mapper.YdMangerMapperSkill;
import com.yd.gcj.service.YdMangerServiceSkill;

@Service("YdMangerServiceSkill")
public class YdMangerServiceImplSkill implements YdMangerServiceSkill{
	
	@Autowired
	private YdMangerMapperSkill ydMangerMapperSkill;
	
	@Override
	public List<YdMangerSkill> $queryAll() {
		return ydMangerMapperSkill.$queryAll();
	}

	@Override
	public YdMangerSkill $queryById(Integer skill_id) {
		return ydMangerMapperSkill.$queryById(skill_id);
	}

	@Override
	public Integer $update(YdMangerSkill skill) {
		return ydMangerMapperSkill.$update(skill);
	}

	@Override
	public Integer $delete(Integer skill_id) {
		return ydMangerMapperSkill.$delete(skill_id);
	}

	@Override
	public Integer $insert(YdMangerSkill skill) {
		return ydMangerMapperSkill.$insert(skill);
	}
	
}
