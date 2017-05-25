package com.yd.gcj.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.gcj.entity.vo.YdMangerFeedbackVo;


public interface YdMangerMapperSystemFeedback {

	/**
	 * description(查询用户反馈的所有信息)
	 * @param
	 * @return
	 */
	List<YdMangerFeedbackVo> queryAllFeedback(@Param("user_name")String user_name,@Param("fb_isread")Integer fb_isread);

	/**
	 * description(根据指定的fb_id删除用户反馈信息)
	 * @param
	 * @param fb_id
	 * @return
	 */
	Integer delFeedback(Integer fb_id);

	/**
	 * description(备注之前先查到当前客户反馈的内容)
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
