package com.yd.dby.c.member.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yd.dby.b.shop.entity.YdOrder;
import com.yd.dby.b.shop.entity.YdOrderGoods;

public interface YdMemberServiceOrder {
	
	/**
	 * 用户订单列表
	 * @param request
	 * @return
	 */
	Map<String, Object> index(HashMap<String, Object> request);
	
	
	/**
	 * 取消订单
	 */
	Object cancel(Integer id, String text);
	
	
	/**
	 * 确认收货
	 */
	Object receipt(Integer id);


	/**
	 * 订单详情
	 */
	HashMap<String, Object> info(Integer id);


	/**
	 * 订单商品
	 */
	Object orderGoods(Integer id);


	/**
	 * @param num
	 * @return
	 * 方法作用(添加已完成订单评论之前先获取页评价所需要的订单信息和商品信息)
	 */
	List<YdOrder> queryOrderInfomationByOrderSn(long num);


	/**
	 * @param order_id
	 * @return
	 * 方法作用(根据订单id查询所对应的商品信息)
	 */
	List<YdOrderGoods> queryOrderGoodsByOrderId(Integer order_id);


	/**
	 * @param data
	 * @return
	 * 方法作用(订单完成之后添加评论)
	 */
	Object addComment(String data, Integer order_id,Integer single,String receiving_time);


	/**
	 * @param orderId
	 * @return
	 * 方法作用(查询收货时间)
	 */
	YdOrder queryReceivingTimeByOrderId(Integer orderId);
	
	
	/**
	 * 退款页面
	 */
	Object refund(HashMap<String, Object> request);
	
	
	/**
	 * 交易投诉页面
	 */
	Object complain(HashMap<String, Object> request);


	/**
	 * 删除订单
	 * @param id
	 * @return
     */
	Object delete(Integer id);
}