package com.yd.dby.c.member.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.b.shop.entity.YdOrder;
import com.yd.dby.b.shop.entity.YdOrderGoods;
import com.yd.dby.b.shop.mapper.YdShopMapperGoods;
import com.yd.dby.c.member.entity.YdComment;
import com.yd.dby.c.member.mapper.YdMemberMapperComplainSubject;
import com.yd.dby.c.member.mapper.YdMemberMapperOrder;
import com.yd.dby.c.member.mapper.YdMemberMapperRefundReason;
import com.yd.dby.c.member.service.YdMemberServiceOrder;
import com.yd.dby.utils.YdUtilResponse;
import com.yd.dby.utils.date.YdUtilDate;

@SuppressWarnings("all")
@Service("_YdWebServiceMemberOrder")
public class YdMemberServiceImplOrder implements YdMemberServiceOrder {

	@Autowired
	private HttpSession session;

	@Autowired
	private YdMemberMapperOrder ydWebMapperMemberOrder;
	
	@Autowired
	private YdMemberMapperRefundReason ydMemberMapperRefundReason;
	
	@Autowired
	private YdMemberMapperComplainSubject ydMemberMapperComplainSubject;
	
	@Autowired
	private YdShopMapperGoods ydShopMapperGoods;

	/**
	 * 订单列表
	 */
	@Override
	public Map<String, Object> index(HashMap<String, Object> request) {
		Integer perPage = 10;
		HashMap<String, Object> map = new HashMap<String, Object>();
		request.put("to", (Integer.valueOf(request.get("p").toString()) - 1) * perPage);
		request.put("perPage", perPage);
		request.put("user_id", session.getAttribute("user_id"));

		map.put("data", ydWebMapperMemberOrder.index(request));
		Integer total = ydWebMapperMemberOrder.total(request);
		map.put("p", request.get("p"));

		if (total % perPage == 0) {
			map.put("totalPage", Math.ceil(total / perPage));
		} else {
			map.put("totalPage", Math.ceil(total / perPage) + 1);
		}

		return map;
	}

	/**
	 * 取消订单
	 */
	@Override
	public Object cancel(Integer id, String text) {
		try {
			Integer userId = Integer.parseInt( session.getAttribute("user_id").toString() );
			HashMap<String, Object> dataOrderMap = ydWebMapperMemberOrder.info(userId, id);
			
			List<YdOrderGoods> ydOrderGoods = ydWebMapperMemberOrder.orderGoods(id);
			for (YdOrderGoods ydOrderGoodsItem : ydOrderGoods) {
				ydShopMapperGoods.updateStockAdd(ydOrderGoodsItem.getGoods_id(), ydOrderGoodsItem.getGoods_num());
			}
			
			String typeString = dataOrderMap.get("type").toString();
			if ( typeString.equals("5") ) {
				for (YdOrderGoods ydOrderGoodsItem : ydOrderGoods) {
					ydShopMapperGoods.updatePreStockAdd(ydOrderGoodsItem.getGoods_id(), ydOrderGoodsItem.getGoods_num());
				}
			}
			
			ydWebMapperMemberOrder.editOrderState(id, text);
			return YdUtilResponse.success(null, "取消订单成功");
		} catch (Exception e) {
			return YdUtilResponse.fail("取消订单失败");
		}
	}

	/**
	 * 确认收货
	 */
	@Override
	public Object receipt(Integer id) {
		Date date = new Date();
		long time = date.getTime();
		try {
			ydWebMapperMemberOrder.editOrderStateByOrderId(id,time);
			return YdUtilResponse.success(null, "确认收货成功");
		} catch (Exception e) {
			e.printStackTrace();
			return YdUtilResponse.fail("确认收货失败");
		}
	}


	/**
	 * 订单详情
	 */
	@Override
	public HashMap<String, Object> info(Integer id) {
		Integer user_id = Integer.parseInt(session.getAttribute("user_id").toString());
		HashMap<String, Object> map = ydWebMapperMemberOrder.info(user_id, id);
		map.put("payment_time_text", YdUtilDate.stampToDateDay(map.get("payment_time").toString()));
		return map;
	}

	/**
	 * 订单商品
	 */
	@Override
	public Object orderGoods(Integer id) {
		return ydWebMapperMemberOrder.orderGoods(id);
	}

	
	/**
	 * 添加已完成订单之前先获取订单所需要的信息
	 */
	@Override
	public List<YdOrder> queryOrderInfomationByOrderSn(long num) {
		List<YdOrder> ydOrderList = ydWebMapperMemberOrder.queryOrderInfomationByOrderSn(num);
		return ydOrderList;
	}
	

	/**
	 * 根据订单ID查询所对应的商品信息
	 */
	@Override
	public List<YdOrderGoods> queryOrderGoodsByOrderId(Integer order_id) {
		List<YdOrderGoods> ydOrderGoodsList = ydWebMapperMemberOrder.queryOrderGoodsByOrderId(order_id);
		return ydOrderGoodsList;
	}

	
	/**
	 * 订单完成之后添加评论
	 */
	@Override
	public Object addComment(String data, Integer order_id,Integer single,String receiving_time) {
		YdComment ydComment = new YdComment();
		//设置收货时间
		ydComment.setComment_buy_time(receiving_time);
		
		//是否匿名
		ydComment.setComment_is_name(single);
		
		// 获取当前登录用户id
		Integer user_id = (Integer) session.getAttribute("user_id");
		ydComment.setUser_id(user_id);
		
		// 订单id
		ydComment.setOrder_id(order_id);
		
		//获取当前时间  
		Date date = new Date();
		long time = date.getTime();
		ydComment.setComment_created_time(time);
		
		Integer adNum = null;
		JSONObject jsonObject = JSONObject.fromObject(data);
		Iterator iterator = jsonObject.keys();
		String goodsId = null;
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			JSONObject value = (JSONObject) jsonObject.get(key);
			String contentString = value.getString("content");
			String imgsString = value.getString("imgs");
			String score = value.getString("score");
			goodsId = value.getString("goods_id");
			int parseIntScore = Integer.parseInt(score);
			// 设置评论分的等级
			if (parseIntScore > 3) {
				//好
				ydComment.setComment_grade(1);
			}else if(parseIntScore==3){
				//中
				ydComment.setComment_grade(2);
			}else {
				//差
				ydComment.setComment_grade(3);
			}
			// 设置评论内容
			ydComment.setComment_content(contentString);

			// 设置商品id
			ydComment.setGoods_id(Integer.parseInt(goodsId));

			// 设置评分
			ydComment.setComment_score(Integer.parseInt(score));

			// 判断图片
			if (imgsString.contains(".")) {
				ydComment.setComment_is_pic(1);
			} else {
				ydComment.setComment_is_pic(0);
			}
			// 设置图片
			ydComment.setComment_pics(imgsString);
			adNum = ydWebMapperMemberOrder.addComment(ydComment);
			
		}
		
		//修改订单状态根据订单id
		ydWebMapperMemberOrder.updateOrderState(order_id,time);
		
		//根据仓库id找到当前商品
		HashMap<String,Object> map = ydWebMapperMemberOrder.queryGoodsIdBuyDepotId(Integer.parseInt(goodsId));
		Integer goods_id = (Integer) map.get("goods_id");
		
		//查询当前商品的评价总数
		HashMap<String,Object> com =  ydWebMapperMemberOrder.queryGoodsTotalComment(goods_id);
		Integer goods_total_comment = (Integer) com.get("goods_total_comment")+1;
		
		//评价成功之后修改商品的评价总数
		ydWebMapperMemberOrder.updateGoodsAssessNum(goods_id,goods_total_comment);
		
		if (adNum > 0) {
			
			return YdUtilResponse.success(null, "评价成功");
			
		} else {
			return YdUtilResponse.fail("评价失败");
		}
	}

	
	/**
	 * 查询订单的收货时间
	 */
	@Override
	public YdOrder queryReceivingTimeByOrderId(Integer orderId) {
		YdOrder ydOrder = ydWebMapperMemberOrder.queryReceivingTimeByOrderId(orderId);
		return ydOrder;
	}

	
	/**
	 * 退款页面
	 */
	@Override
	public Object refund(HashMap<String, Object> request) {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		
		Integer orderId = Integer.parseInt( request.get("orderId").toString() );
		Integer orderGoodsId = Integer.parseInt( request.get("orderGoodsId").toString() );
		String orderSn = request.get("orderSn").toString();
		Integer user_id = Integer.parseInt(session.getAttribute("user_id").toString());
		
		returnMap.put("dataGoods", ydWebMapperMemberOrder.findByOrderGoods( orderGoodsId ));
		
		HashMap<String, Object> orderMap = ydWebMapperMemberOrder.info(user_id, orderId );
		
		if ( orderMap != null ) {
			returnMap.put("dataOrder", orderMap);
		}
		returnMap.put("dataOrderGoods", ydWebMapperMemberOrder.orderGoods(orderId));
		returnMap.put("dataReason", ydMemberMapperRefundReason.lists());
		
		return returnMap;
	}
	
	
	/**
	 * 交易投诉页面
	 */
	@Override
	public Object complain(HashMap<String, Object> request) {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		
		Integer orderId = Integer.parseInt( request.get("orderId").toString() );
		Integer orderGoodsId = 0;
		String orderSn = request.get("orderSn").toString();
		Integer user_id = Integer.parseInt(session.getAttribute("user_id").toString());
		
		if ( request.get("orderGoodsId") != null ) {
			orderGoodsId = Integer.parseInt( request.get("orderGoodsId").toString() );
			returnMap.put("dataGoods", ydWebMapperMemberOrder.findByOrderGoods( orderGoodsId ));
		}
		
		HashMap<String, Object> orderMap = ydWebMapperMemberOrder.info(user_id, orderId );
		
		if ( orderMap != null ) {
			returnMap.put("dataOrder", orderMap);
		}
		returnMap.put("dataOrderGoods", ydWebMapperMemberOrder.orderGoods(orderId));
		returnMap.put("dataSubject", ydMemberMapperComplainSubject.lists());
		
		return returnMap;
	}
	


	/**
	 * 删除订单
	 * @param id
	 * @return
     */
	@Override
	public Object delete(Integer id) {
		try {
			ydWebMapperMemberOrder.delete(id);
			return YdUtilResponse.success(null, "删除订单成功");
		} catch (Exception e) {
			return YdUtilResponse.fail("删除订单失败");
		}
	}
}