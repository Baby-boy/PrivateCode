package com.yd.dby.c.member.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yd.dby.b.shop.entity.YdCtcOrder;

public interface YdMemberMapperCtcOrder {

	List<Map<String, Object>> index(HashMap<String, Object> request);

	Integer total(HashMap<String, Object> request);

	// 查询单个懒鱼订单信息
	YdCtcOrder queryOneCtcOrderDetailById(@Param("ctc_order_id") Integer ctc_order_id);

	// 更改订单状态
	Integer update(HashMap<String, Object> request);

}