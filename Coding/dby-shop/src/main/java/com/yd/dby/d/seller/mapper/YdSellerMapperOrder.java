package com.yd.dby.d.seller.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.dby.b.shop.entity.YdOrderGoods;


public interface YdSellerMapperOrder {
	
	List<Object> index(HashMap<String, Object> request);
	
	Integer total(HashMap<String, Object> request);
	
	Object info(@Param("store_id") Integer store_id, @Param("order_id") Integer order_id);
	
	Object infos(@Param("store_id") Integer store_id, @Param("order_id") Integer order_id);
	
	List<Object> orderGoods(@Param("order_id") Integer order_id);
	
	
	/**
	 * 获取所有订单退款-退货商品
	 * @param orderId
	 * @return
	 */
	List<YdOrderGoods> orderGoodsRefund(Integer orderId);
	
	
	/**
	 * 更新收货地址
	 */
	Integer updateTake(@Param("order_id") Integer order_id, @Param("text") String text, @Param("store_id") Integer store_id);

	
	/**
	 * 发货
	 */
	Integer deliver( @Param("order_id") Integer order_id, @Param("shipping_express") String shipping_express,
			 @Param("logis_code") String logis_code,
			 @Param("shipping_code") String shipping_code, @Param("deliver_explain") String deliver_explain,
			 @Param("shipping_address") String shipping_address, @Param("shipping_id") Integer shipping_id,
			 @Param("store_id") Integer store_id);
}