package com.yd.dby.b.shop.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.dby.b.shop.entity.YdOrder;

public interface YdShopMapperOrder {

	List<HashMap<String, Object>> SelectGoods(@Param("depot_id") Integer depot_id);
	List<HashMap<String, Object>> SelectCoupon(@Param("user_id") Integer user_id, @Param("store_ids") List<Integer> store_ids);

	
	/**
	 * 插入订单
	 */
	Integer store(YdOrder ydOrder);
	
	
	/**
	 * 支付成功修改订单状态
	 * @param order_sn
	 * @param string 
	 * @return
	 */
	Integer paySuccess(@Param("order_sn") String order_sn, @Param("pay_sn") String pay_sn, @Param("payment_time") String payment_time);
}