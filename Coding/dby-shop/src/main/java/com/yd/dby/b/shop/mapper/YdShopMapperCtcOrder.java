package com.yd.dby.b.shop.mapper;

import org.apache.ibatis.annotations.Param;

import com.yd.dby.b.shop.entity.YdCtcOrder;

public interface YdShopMapperCtcOrder {

	Integer store(YdCtcOrder ydCtcOrder);

	YdCtcOrder info(@Param("order_sn") String order_sn);

	// 查询单个订单的信息
	YdCtcOrder queryOneDetailById(@Param("ctc_order_id") Integer ctc_order_id);

	// 更新订单
	Integer update(YdCtcOrder ydCtcOrder);

	
	/**
	 * 支付成功后，修改订单状态
	 * @param ctcId 
	 * @param orderNo
	 * @param string 
	 * @param string
	 * @return
	 */
	Integer paySuccess(@Param("orderSn") String orderSn, @Param("paySn") String paySn, @Param("paymentTime") String paymentTime, @Param("ctcId") Integer ctcId);

}
