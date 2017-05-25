package com.yd.gcj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.YdMangerFeedBack;
import com.yd.gcj.mapper.YdMangerMapperFeedBack;
import com.yd.gcj.service.YdMangerServiceFeedBack;

@Service("YdMangerServiceFeedBack")
public class YdMangerServiceImplFeedBack implements YdMangerServiceFeedBack{
	
	@Autowired
	private YdMangerMapperFeedBack ydMangerMapperFeedBack;
	
	@Override
	public List<YdMangerFeedBack> $queryAllByPageNum(Integer startPageNum, Integer queryPageNum, Integer fb_isdel) {
		return ydMangerMapperFeedBack.$queryAllByPageNum(startPageNum, queryPageNum, fb_isdel);
	}

	@Override
	public Integer $insert(YdMangerFeedBack fb) {
		return ydMangerMapperFeedBack.$insert(fb);
	}

	@Override
	public Integer $updateIsRead(Integer fb_id) {
		return ydMangerMapperFeedBack.$updateIsRead(fb_id);
	}

	@Override
	public Integer $updateRemarks(Integer fb_id, String fb_remarks) {
		return ydMangerMapperFeedBack.$updateRemarks(fb_id, fb_remarks);
	}

	@Override
	public Integer $delete(Integer fb_id) {
		return ydMangerMapperFeedBack.$delete(fb_id);
	}

}
