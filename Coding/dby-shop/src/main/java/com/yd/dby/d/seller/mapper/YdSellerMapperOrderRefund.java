package com.yd.dby.d.seller.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.dby.c.member.entity.YdOrderRefund;
import com.yd.dby.c.member.entity.vo.YdMemberRefundOrderVo;


public interface YdSellerMapperOrderRefund {
	
	/**
	 * 退款-退货订单信息
	 * @param user_id
	 * @param order_id
	 * @return
	 */
	YdMemberRefundOrderVo info(@Param("storeId") Integer storeId, @Param("refundId") Integer refundId);

	
	/*退款-退货订单*/
	List<YdOrderRefund> saleService(HashMap<String, Object> request);
	
	
	/*售后服务总数*/
	Integer totalService(HashMap<String, Object> request);


	/**
	 * 商家处理退款 - 提交
	 * @param refundId
	 * @param sellerMessage
	 * @param sellerState
	 * @param storeId
	 * @param sellerTime 
	 * @return
	 */
	Integer update(
			@Param("refundId") Integer refundId, @Param("sellerMessage") String sellerMessage,
			@Param("sellerState") Integer sellerState, @Param("storeId") Integer storeId,
			@Param("refundState") Integer refundState, @Param("sellerTime") String sellerTime);
}