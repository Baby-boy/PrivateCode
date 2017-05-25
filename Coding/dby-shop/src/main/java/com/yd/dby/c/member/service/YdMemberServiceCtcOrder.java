package com.yd.dby.c.member.service;

import java.util.HashMap;
import java.util.Map;

import com.yd.dby.b.shop.entity.YdCtcOrder;

public interface YdMemberServiceCtcOrder {
	
	/*c2c订单*/
	Map<String, Object> index(HashMap<String, Object> request);

	// 查询单个ctc订单信息
	YdCtcOrder queryOneCtcOrderDetailById(Integer ctc_order_id);
	
	
}
