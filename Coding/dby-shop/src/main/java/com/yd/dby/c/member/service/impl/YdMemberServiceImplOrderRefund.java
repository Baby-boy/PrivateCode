package com.yd.dby.c.member.service.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.b.shop.entity.YdOrder;
import com.yd.dby.b.shop.entity.YdOrderGoods;
import com.yd.dby.c.member.entity.YdOrderRefund;
import com.yd.dby.c.member.entity.vo.YdMemberRefundOrderVo;
import com.yd.dby.c.member.mapper.YdMemberMapperOrder;
import com.yd.dby.c.member.mapper.YdMemberMapperOrderRefund;
import com.yd.dby.c.member.service.YdMemberServiceOrderRefund;
import com.yd.dby.utils.YdUtilResponse;
import com.yd.dby.utils.date.YdUtilDate;
import com.yd.dby.utils.ordersn.YdRefundSn;

@SuppressWarnings("all")
@Service("_YdWebServiceMemberOrderRefund")
public class YdMemberServiceImplOrderRefund implements YdMemberServiceOrderRefund {

	@Autowired
	private HttpSession session;

	@Autowired
	private YdMemberMapperOrderRefund ydMemberMapperOrderRefund;

	@Autowired
	private YdMemberMapperOrder ydMemberMapperOrder;

	/**
	 * 售后服务
	 */
	@Override
	public Map<String, Object> index(HashMap<String, Object> request) {
		Integer perPage = 10;

		HashMap<String, Object> map = new HashMap<String, Object>();

		request.put("to", (Integer.valueOf(request.get("p").toString()) - 1) * perPage);
		request.put("perPage", perPage);
		request.put("user_id", session.getAttribute("user_id"));
		request.put("state_service", request.get("state"));
		
		List<YdOrderRefund> saleService = ydMemberMapperOrderRefund.saleService(request);

		map.put("data", saleService);

		Integer total = ydMemberMapperOrderRefund.totalService(request);

		map.put("p", request.get("p"));

		if (total % perPage == 0) {
			map.put("totalPage", Math.ceil(total / perPage));
		} else {
			map.put("totalPage", Math.ceil(total / perPage) + 1);
		}

		return map;
	}

	/**
	 * 订单详情
	 */
	@Override
	public YdMemberRefundOrderVo info(Integer id) {
		Integer user_id = Integer.parseInt(session.getAttribute("user_id").toString());
		YdMemberRefundOrderVo ydMemberRefundOrderVo = ydMemberMapperOrderRefund.info(user_id, id);
		String add_time = ydMemberRefundOrderVo.getAdd_time();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long parseLong = Long.parseLong(add_time);
		ydMemberRefundOrderVo.setFormat_time(sdf.format(parseLong));
		;

		Integer goods_num = ydMemberRefundOrderVo.getGoods_num();
		Double goods_price = ydMemberRefundOrderVo.getGoods_price();
		Double goods_pay_price = goods_num * goods_price;
		ydMemberRefundOrderVo.setGoods_pay_price(goods_pay_price);

		return ydMemberRefundOrderVo;
	}

	
	/**
	 * 发起退款
	 */
	@Override
	public Object store(Integer reasonId, String reasonContent,
			Integer reasonNum, String buyerMessage, String picInfo,
			Integer goodsId, Integer type, Integer orderId, Integer orderGoodsId) {

		Integer userId = (Integer) session.getAttribute("user_id");
		Integer ogId = orderGoodsId;		// 订单商品id
		Integer ogState = 3;				// 订单商品状态   1:正常 2:退货退款 3:仅退款 4:换货 5:维修
		String goodsName = "订单商品全部退款";	// 商品名称
		String goodsCover = "";				// 商品图片
		float refundAmount = 0;				// 退款金额
		YdOrder ydOrder = new YdOrder();
		
		HashMap<String, Object> orderMap = ydMemberMapperOrder.info(userId, orderId);
		
		// 获取订单下所有的正常商品
		if ( type.equals(1) ) {
			List<YdOrderGoods> ydOrderGoodsList = ydMemberMapperOrder.orderGoodsNormal(orderId);
			if ( ydOrderGoodsList.size() == 1 ) {
				if ( ydOrderGoodsList.get(0).getGoods_num().equals(reasonNum) ) {
					type = 2;
				}
			}
		}
		

		// 物流状态:1为待发货,2为待收货,3为未收到,4为已收货,默认为1
		// 订单状态 1:待付款 2:已付款 3：已发货 4:已收货(待评论) 5:退换货 6:已完成 9(已取消)
		Integer goodsState = 1;	// 物流状态
		Integer returnType = 1;	// 退货类型:1为不用退货,2为需要退货,默认为1
		switch ( orderMap.get("state").toString() ) {
			case "3": 	// 为待收货
				goodsState = 2;
				returnType = 2;
				ogState = 2;
				break;
			case "4":	// 已收货
				goodsState = 4;
				returnType = 2;
				ogState = 2;
				break;
	
			default: goodsState = 1;  break;
		}
		
		
		// 退款类型    1：单个商品     2：全部商品
		if ( type.equals(1) ) {
			YdOrderGoods ydOrderGoods = ydMemberMapperOrder.findByOrderGoods(ogId);
			goodsName = ydOrderGoods.getGoods_name();
			goodsCover = ydOrderGoods.getGoods_cover();
			
			// 退款数量大于购买数量
			if ( reasonNum > ydOrderGoods.getGoods_num() ) {
				return YdUtilResponse.fail("参数错误");
			}
			
			// 修改订单商品状态（如果退款数量等于购买数量，修改订单商品状态；否则只修改订单商品退款数量）
			if ( ydOrderGoods.getGoods_num().equals(reasonNum) ) {
				
				// 退款总为商品总价
				refundAmount = ydOrderGoods.getGoods_pay_price();
				ydMemberMapperOrder.updateOrderGoodsState(ogId, ogState, reasonNum);
			} else {
				
				// 退款总价为商品总价除以商品数量乘以退款数量
				refundAmount = (float) (Math.round( ydOrderGoods.getGoods_pay_price() / ydOrderGoods.getGoods_num()  * 100) / 100) * reasonNum;
				ydMemberMapperOrder.updateOrderGoodsState(ogId, null, reasonNum);
			}
		} else {
			
			goodsId = 0;
			orderGoodsId = 0;
			ydOrder.setOrder_id(orderId);
			ydOrder.setState(5);
			
			// 修改订单状态
			ydMemberMapperOrder.update(ydOrder);
			
			// 修改订单商品状态
			ydMemberMapperOrder.updateOrderGoodsStateAll(orderId, ogState);
			
			// 订单总金额
			float totalPrice = Float.parseFloat( orderMap.get("total_price").toString() );
			
			// 计算退款金额（如果物流状态为待发货，退款金额为订单总金额；如果为已发货状态，退款金额为订单总额减运费）
			if ( goodsState.equals(1) ) {
				refundAmount = totalPrice;
			} else {
				float transportPrice = Float.parseFloat( orderMap.get("transport_price").toString() );	// 运费
				refundAmount = totalPrice - transportPrice;
			}
		}
		

		YdOrderRefund ydOrderRefund = new YdOrderRefund();
		ydOrderRefund.setOrder_id( orderId );
		ydOrderRefund.setOrder_sn( orderMap.get("order_sn").toString() );
		ydOrderRefund.setRefund_sn( YdRefundSn.get(userId) );
		ydOrderRefund.setStore_id( Integer.parseInt( orderMap.get("store_id").toString() ) );
		ydOrderRefund.setStore_name( orderMap.get("store_name").toString() );
		ydOrderRefund.setBuyer_id( Integer.parseInt( orderMap.get("buyer_id").toString() ) );
		ydOrderRefund.setBuyer_name( orderMap.get("buyer_name").toString() );
		ydOrderRefund.setGoods_id( goodsId );					// 商品ID,全部退款是0
		ydOrderRefund.setOrder_id(orderId);
		ydOrderRefund.setOrder_sn(orderMap.get("order_sn").toString());
		ydOrderRefund.setRefund_sn(YdRefundSn.get(userId));
		ydOrderRefund.setStore_id(Integer.parseInt(orderMap.get("store_id").toString()));
		ydOrderRefund.setStore_name(orderMap.get("store_name").toString());
		ydOrderRefund.setBuyer_id(Integer.parseInt(orderMap.get("buyer_id").toString()));
		ydOrderRefund.setBuyer_name(orderMap.get("buyer_name").toString());
		ydOrderRefund.setOrder_goods_id(orderGoodsId);			// 订单商品ID,全部退款是0
		ydOrderRefund.setGoods_name( goodsName );
		ydOrderRefund.setGoods_num(reasonNum);
		ydOrderRefund.setRefund_amount( refundAmount );			// 退款金额
		ydOrderRefund.setGoods_image( goodsCover );
		ydOrderRefund.setOrder_goods_type(1);
		ydOrderRefund.setRefund_type(1); // 申请类型:1为退款,2为退货,默认为1
		ydOrderRefund.setSeller_state(1); // 卖家处理状态:1为待审核,2为同意,3为不同意,默认为1
		ydOrderRefund.setRefund_state(1); // 申请状态:1为处理中,2为待管理员处理,3为已完成,默认为1
		ydOrderRefund.setReturn_type( returnType ); // 退货类型:1为不用退货,2为需要退货,默认为1
		ydOrderRefund.setOrder_lock(1); // 订单锁定类型:1为不用锁定,2为需要锁定,默认为1
		ydOrderRefund.setGoods_state( goodsState ); // 物流状态:1为待发货,2为待收货,3为未收到,4为已收货,默认为1
		ydOrderRefund.setAdd_time(YdUtilDate.currentMillis());
		ydOrderRefund.setSeller_time(0);
		ydOrderRefund.setAdmin_time(0);
		ydOrderRefund.setReason_id(reasonId);
		ydOrderRefund.setReason_info(reasonContent);
		ydOrderRefund.setPic_info(picInfo);
		ydOrderRefund.setBuyer_message(buyerMessage);
		ydOrderRefund.setSeller_message("");
		ydOrderRefund.setAdmin_message("");
		ydOrderRefund.setExpress_id(0);
		ydOrderRefund.setInvoice_no(orderMap.get("invoice_no").toString());
		ydOrderRefund.setShip_time(Long.parseLong(orderMap.get("shipping_time").toString())); // 发货时间,默认为0
		ydOrderRefund.setDelay_time(0);
		ydOrderRefund.setReceive_time(Long.parseLong(orderMap.get("shipping_time").toString()));
		ydOrderRefund.setReceive_message(""); // 收货备注
		ydOrderRefund.setCommis_rate(0); // 佣金比例
		try {
			ydMemberMapperOrderRefund.store(ydOrderRefund);
			return YdUtilResponse.success(null, "提交成功");
		} catch (Exception e) {
			return YdUtilResponse.fail("提交失败");
		}
	}

	
	/**
	 * 退款-退货- 详情
	 */
	@Override
	public Object detail(HashMap<String, Object> request) {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		Integer userId = Integer.parseInt(session.getAttribute("user_id").toString());
		
		YdMemberRefundOrderVo ydMemberRefundOrderVo = ydMemberMapperOrderRefund.info(userId, Integer.parseInt( request.get("refundId").toString() ));
		returnMap.put("data", ydMemberRefundOrderVo);
		
		System.out.println( ydMemberRefundOrderVo.getOrder_id() );
		
		returnMap.put("dataGoods", ydMemberMapperOrder.orderGoodsRefund(ydMemberRefundOrderVo.getOrder_id()));
		
		System.out.println( returnMap.get("dataGoods") );
		
		returnMap.put("dataOrder", ydMemberMapperOrder.info(userId, ydMemberRefundOrderVo.getOrder_id()) );
		return returnMap;
	}
}