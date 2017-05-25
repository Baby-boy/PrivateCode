package com.yd.dby.d.seller.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pingplusplus.exception.*;
import com.pingplusplus.model.Charge;
import com.pingplusplus.model.ChargeRefundCollection;
import com.pingplusplus.model.Refund;

import com.yd.dby.c.member.entity.vo.YdMemberRefundOrderVo;
import com.yd.dby.d.seller.mapper.YdSellerMapperOrder;
import com.yd.dby.d.seller.mapper.YdSellerMapperOrderRefund;
import com.yd.dby.d.seller.service.YdSellerServiceOrderRefund;
import com.yd.dby.utils.YdUtilResponse;
import com.yd.dby.utils.date.YdUtilDate;

@SuppressWarnings("all")
@Service("_ydSellerMapperOrderRefund")
public class YdSellerServiceImplOrderRefund implements YdSellerServiceOrderRefund {

	@Autowired
	private HttpSession session;
	
	private Charge charge;

	@Autowired
	private YdSellerMapperOrderRefund ydSellerMapperOrderRefund;
	
	@Autowired
	private YdSellerMapperOrder ydSellerMapperOrder;

	/**
	 * 售后服务
	 */
	@Override
	public Map<String, Object> index(HashMap<String, Object> request) {
		Integer perPage = 10;

		HashMap<String, Object> map = new HashMap<String, Object>();

		request.put("to", (Integer.valueOf(request.get("p").toString()) - 1) * perPage);
		request.put("perPage", perPage);
		request.put("store_id", session.getAttribute("store_id"));
		map.put("data", ydSellerMapperOrderRefund.saleService(request));
				
		Integer total = ydSellerMapperOrderRefund.totalService(request);

		map.put("p", request.get("p"));

		if (total % perPage == 0) {
			map.put("totalPage", Math.ceil(total / perPage));
		} else {
			map.put("totalPage", Math.ceil(total / perPage) + 1);
		}

		return map;
	}

	
	/**
	 * 退款-退货-处理页面
	 */
	@Override
	public Object handle(HashMap<String, Object> request) {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		Integer store_id = Integer.parseInt(session.getAttribute("store_id").toString());
		
		YdMemberRefundOrderVo ydMemberRefundOrderVo = ydSellerMapperOrderRefund.info(store_id, Integer.parseInt( request.get("refundId").toString() ));
		returnMap.put("data", ydMemberRefundOrderVo);
		returnMap.put("dataGoods", ydSellerMapperOrder.orderGoodsRefund(ydMemberRefundOrderVo.getOrder_id()));
		returnMap.put("dataOrder", ydSellerMapperOrder.info(store_id, ydMemberRefundOrderVo.getOrder_id()) );
		return returnMap;
	}
	
	
	/**
	 * 退款-退货- 详情
	 */
	@Override
	public Object detail(HashMap<String, Object> request) {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		Integer store_id = Integer.parseInt(session.getAttribute("store_id").toString());
		
		YdMemberRefundOrderVo ydMemberRefundOrderVo = ydSellerMapperOrderRefund.info(store_id, Integer.parseInt( request.get("refundId").toString() ));
		returnMap.put("data", ydMemberRefundOrderVo);
		returnMap.put("dataGoods", ydSellerMapperOrder.orderGoodsRefund(ydMemberRefundOrderVo.getOrder_id()));
		returnMap.put("dataOrder", ydSellerMapperOrder.info(store_id, ydMemberRefundOrderVo.getOrder_id()) );
		return returnMap;
	}


	/**
	 * 商家处理退款 - 更新
	 */
	@Override
	public Object update(Integer refundId, String sellerMessage,
			Integer sellerState) {
		try {
			Integer storeId = Integer.parseInt(session.getAttribute("store_id").toString());
			
			if (sellerState == null) {
				return YdUtilResponse.fail("请选择是否同意！");
			}
			
			if (sellerMessage == "") {
				return YdUtilResponse.fail("备注信息不能为空！");
			}
			
			if ( !sellerState.equals(2) && !sellerState.equals(3) ) {
				return YdUtilResponse.fail("参数错误！");
			}
			
			Integer refundState = 2;
			if ( sellerState.equals(3) ) {
				refundState = 3;
			}
			
			String sellerTime = YdUtilDate.currentMillisString();
			
			ydSellerMapperOrderRefund.update(refundId, sellerMessage, sellerState, storeId, refundState, sellerTime);
			return YdUtilResponse.success(null, "操作成功");
		} catch (Exception e) {
			return YdUtilResponse.fail("操作失败");
		}
	}


	/**
	 * 发起退款
	 */
	@Override
	public Object playMoney(Integer refundId) {
		System.out.println(refundId);
		
		try {
			Refund refund = null;
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("description", "苹果被咬了一口。");
			params.put("amount", 1);// 退款的金额, 单位为对应币种的最小货币单位，例如：人民币为分（如退款金额为 1 元，此处请填 100）。必须小于等于可退款金额，默认为全额退款

			refund = Refund.create("2", params);
			
			
			return YdUtilResponse.success(null, "操作成功");
//			return YdUtilResponse.fail("操作失败");
		} catch (AuthenticationException e) {
			e.printStackTrace();
		} catch (InvalidRequestException e) {
			e.printStackTrace();
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIException e) {
			e.printStackTrace();
		} catch (ChannelException e) {
			e.printStackTrace();
		} catch (RateLimitException e) {
			e.printStackTrace();
		}
		return YdUtilResponse.fail("操作失败");
		
	}


	/**
	 * 退款成功
	 */
	@Override
	public Object success(HttpServletRequest request,
			HttpServletResponse response) {
		
		return null;
	}

}