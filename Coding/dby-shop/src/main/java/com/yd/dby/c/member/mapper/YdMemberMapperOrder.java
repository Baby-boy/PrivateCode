package com.yd.dby.c.member.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.dby.b.shop.entity.YdOrder;
import com.yd.dby.b.shop.entity.YdOrderGoods;
import com.yd.dby.c.member.entity.YdComment;

public interface YdMemberMapperOrder {

	/* 普通订单 */
	List<Object> index(HashMap<String, Object> request);

	/* 普通订单总数 */
	Integer total(HashMap<String, Object> request);

	/* 拍卖订单列表 */
	List<Object> orderSale(HashMap<String, Object> request);

	/* 拍卖订单总数 */
	Integer totalSale(HashMap<String, Object> request);

	/**
	 * 订单信息
	 * 
	 * @param user_id
	 * @param order_id
	 * @return
	 */
	HashMap<String, Object> info(@Param("user_id") Integer user_id, @Param("order_id") Integer order_id);

	/**
	 * 订单商品
	 * 
	 * @param order_id
	 * @return
	 */
	List<YdOrderGoods> orderGoods(@Param("order_id") Integer order_id);

	/**
	 * 订单商品
	 * @param order_id
	 * @return
	 */
	List<HashMap<String, Object>> orderGoodsMap(@Param("order_id") Integer order_id);
	
	
	/**
	 * 获取所有订单正常商品
	 * 
	 * @param orderId
	 * @return
	 */
	List<YdOrderGoods> orderGoodsNormal(Integer orderId);

	/**
	 * 获取所有订单退款-退货商品
	 * 
	 * @param orderId
	 * @return
	 */
	List<YdOrderGoods> orderGoodsRefund(@Param("orderId") Integer orderId);

	/**
	 * 修改订单状态
	 */
	Integer editOrderState(@Param("order_id") Integer order_id, @Param("cancel_reason") String text);

	/**
	 * @param num
	 * @return 方法作用(添加订单评价之前先根据订单编号获取评价订单所需要的信息)
	 */
	List<YdOrder> queryOrderInfomationByOrderSn(@Param("orderSn") long num);

	/**
	 * @param order_id
	 * @return 方法作用(根据订单Id查询所对应的商品信息)
	 */
	List<YdOrderGoods> queryOrderGoodsByOrderId(Integer order_id);

	/**
	 * @param ydComment
	 * @return 方法作用(订单完成之后添加评论)
	 */
	Integer addComment(YdComment ydComment);

	/**
	 * @param order_id
	 *            方法作用(修改订单状态根据订单id)
	 */
	Integer updateOrderState(@Param("order_id") Integer order_id, @Param("evaluation_time") long time);

	/**
	 * @param id
	 * @param time
	 */
	Integer editOrderStateByOrderId(@Param("order_id") Integer id, @Param("receiving_time") long time);

	/**
	 * @param orderId
	 * @return 方法作用(查询订单的收货时间)
	 */
	YdOrder queryReceivingTimeByOrderId(@Param("order_id") Integer orderId);

	/**
	 * 查询单条订单商品
	 * 
	 * @param orderGoodsId
	 * @return
	 */
	YdOrderGoods findByOrderGoods(@Param("orderGoodsId") Integer orderGoodsId);

	/**
	 * 修改订单
	 * 
	 * @param ydOrder
	 * @return
	 */
	Integer update(YdOrder ydOrder);

	/**
	 * 退货-退款 -（修改全部订单商品状态）
	 * 
	 * @param orderId
	 * @param ogState
	 * @return
	 */
	Integer updateOrderGoodsStateAll(@Param("orderId") Integer orderId, @Param("ogState") Integer ogState);

	/**
	 * 退货-退款 -（修改单个订单商品状态）
	 * 
	 * @param ogId
	 * @param ogState
	 * @param reasonNum
	 */
	Integer updateOrderGoodsState(@Param("ogId") Integer ogId, @Param("ogState") Integer ogState, @Param("reasonNum") Integer reasonNum);

	/**
	 * 删除订单
	 * 
	 * @param id
	 * @return
	 */
	Integer delete(@Param("orderId") Integer orderId);

	/**
	 * @param parseInt
	 *            方法作用(评价成功之后修改当前商品的评价总数)
	 * @param goods_total_comment
	 */
	void updateGoodsAssessNum(@Param("goods_id")Integer goods_id,@Param("goods_total_comment") Integer goods_total_comment);

	/**
	 * @param parseInt
	 * @return 方法作用(根据仓库id查到当前商品)
	 */
	HashMap<String, Object> queryGoodsIdBuyDepotId(@Param("depot_id") Integer depot_id);

	/**
	 * @param goods_id
	 * @return 方法作用(查询当前商品的评价总数)
	 */
	HashMap<String, Object> queryGoodsTotalComment(@Param("goods_id") Integer goods_id);
}