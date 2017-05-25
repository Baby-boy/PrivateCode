package com.yd.gcj.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.vo.YdMangerUserLabelVo;
import com.yd.gcj.system.mapper.YdMangerMapperSystemUserLabel;
import com.yd.gcj.system.service.YdMangerServiceSystemUserLabel;
@Service("ydMangerServiceSystemUserLabel")
public class YdMangerServiceImplSystemUserLabel implements YdMangerServiceSystemUserLabel {

	@Autowired
	private YdMangerMapperSystemUserLabel ydMangerMapperSystemUserLabel;
	
	@Override
	public List<YdMangerUserLabelVo> queryAllServerSkillState(Integer user_id) {

		List<YdMangerUserLabelVo> labelList = ydMangerMapperSystemUserLabel.queryAllServerSkillState(user_id);
		return labelList;
	}


}
