package com.yd.dby.c.member.service;

import java.util.HashMap;
import java.util.Map;

import com.yd.dby.c.member.entity.vo.YdMemberRefundOrderVo;

public interface YdMemberServiceOrderRefund {
	
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

	Object store(Integer reasonId, String reasonContent, Integer reasonNum,
			String buyerMessage, String picInfo, Integer goodsId, Integer type,
			Integer orderId, Integer orderGoodsId);
	
	/**
	 * 退款-退货 - 详情
	 * @param request
	 * @return
	 */
	Object detail(HashMap<String, Object> request);
}