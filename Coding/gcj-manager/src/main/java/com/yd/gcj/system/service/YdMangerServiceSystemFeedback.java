package com.yd.gcj.system.service;

import java.util.List;

import com.yd.gcj.entity.vo.YdMangerFeedbackVo;

public interface YdMangerServiceSystemFeedback {

	/**
	 * description(查询所有用户反馈的信息)
	 * @param
	 * @return
	 */
	List<YdMangerFeedbackVo> queryAllFeedback(String user_name,Integer fb_isread);

	/**
	 * description(根据指定的fb_id删除用户反馈信息)
	 * @param
	 * @param fb_id
	 * @return
	 */
	Integer delFeedback(Integer fb_id);

	/**
	 * description(备注之前先查到当前客户反馈的信息)
	 * @param
	 * @param fb_id
	 * @return
	 */
	YdMangerFeedbackVo queryFeedBackByFbId(Integer fb_id);

	/**
	 * description(添加备注)
	 * @param
	 * @param ydMangerFeedbackVo
	 * @return
	 */
	Integer updateRemarkByFbId(YdMangerFeedbackVo ydMangerFeedbackVo);

}
