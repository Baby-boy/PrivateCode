package com.yd.dby.c.member.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.dby.c.member.entity.YdOrderRefund;
import com.yd.dby.c.member.entity.vo.YdMemberRefundOrderVo;


public interface YdMemberMapperOrderRefund {
	
	/**
	 * 订单信息
	 * @param user_id
	 * @param order_id
	 * @return
	 */
	YdMemberRefundOrderVo info(@Param("userId") Integer user_id, @Param("refundId") Integer refund_id);

	
	/*售后服务*/
	List<YdOrderRefund> saleService(HashMap<String, Object> request);
	
	/*售后服务总数*/
	Integer totalService(HashMap<String, Object> request);


	Integer store(YdOrderRefund ydOrderRefund);
}