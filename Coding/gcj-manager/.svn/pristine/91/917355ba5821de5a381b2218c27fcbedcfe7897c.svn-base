package com.yd.gcj.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.vo.YdMangerFeedbackVo;
import com.yd.gcj.system.mapper.YdMangerMapperSystemFeedback;
import com.yd.gcj.system.service.YdMangerServiceSystemFeedback;

@Service("ydMangerServiceSystemFeedback")
public class YdMangerServiceImplSystemFeedback implements YdMangerServiceSystemFeedback {

	@Autowired
	private YdMangerMapperSystemFeedback ydMangerMapperSystemFeedback;
	
	//查询所有用户反馈的信息
	@Override
	public List<YdMangerFeedbackVo> queryAllFeedback(String user_name,Integer fb_isread) {
		List<YdMangerFeedbackVo> feedbackList = ydMangerMapperSystemFeedback.queryAllFeedback(user_name,fb_isread);
		return feedbackList;
	}

	//删除用户反馈信息
	@Override
	public Integer delFeedback(Integer fb_id) {
		Integer delNum = ydMangerMapperSystemFeedback.delFeedback(fb_id);
		return delNum;
	}

	//备注客户反馈内容
	@Override
	public YdMangerFeedbackVo queryFeedBackByFbId(Integer fb_id) {
		YdMangerFeedbackVo ydMangerFeedbackVo = ydMangerMapperSystemFeedback.queryFeedBackByFbId(fb_id);
		return ydMangerFeedbackVo;
	}

	//添加备注
	@Override
	public Integer updateRemarkByFbId(YdMangerFeedbackVo ydMangerFeedbackVo) {
		
		Integer addNum = ydMangerMapperSystemFeedback.updateRemarkByFbId(ydMangerFeedbackVo);
		return addNum;
	}
	

}
