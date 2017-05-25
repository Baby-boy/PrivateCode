package com.yd.dby.c.member.service;

import java.util.HashMap;
import java.util.Map;

import com.yd.dby.c.member.entity.YdComplain;
import com.yd.dby.c.member.entity.vo.YdMemberRefundOrderVo;

public interface YdMemberServiceComplain {
	
	/**
	 * 售后列表
	 * @param request
	 * @return
	 */
	Map<String, Object> index(HashMap<String, Object> request);
	
	/**
	 * 订单详情
	 */
	YdMemberRefundOrderVo info(Integer id);

	/**
	 * 退款-退货 - 详情
	 * @param request
	 * @return
	 */
	Object detail(HashMap<String, Object> request);

	
	/**
	 * 发起投诉
	 * @param ydComplain
	 * @return
	 */
	Object store(YdComplain ydComplain);

	
	/**
	 * 取消
	 * @param complainId
	 * @return
	 */
	Object delete(Integer complainId);
}