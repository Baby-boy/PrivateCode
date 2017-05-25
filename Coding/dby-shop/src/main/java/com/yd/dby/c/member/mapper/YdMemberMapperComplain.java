package com.yd.dby.c.member.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.dby.c.member.entity.YdComplain;
import com.yd.dby.c.member.entity.YdOrderRefund;
import com.yd.dby.c.member.entity.vo.YdMemberRefundOrderVo;


public interface YdMemberMapperComplain {
	
	/**
	 * 订单信息
	 * @param user_id
	 * @param order_id
	 * @return
	 */
	YdMemberRefundOrderVo info(@Param("userId") Integer user_id, @Param("refundId") Integer refund_id);

	
	/*投诉列表*/
	List<YdOrderRefund> lists(HashMap<String, Object> request);
	
	/*投诉总数*/
	Integer total(HashMap<String, Object> request);

	
	/**
	 * 发起投诉
	 * @param ydComplain
	 * @return
	 */
	Integer store(YdComplain ydComplain);


	/**
	 * 取消
	 * @param complainId
	 * @param userId
	 * @return
	 */
	Integer delete(@Param("complainId")Integer complainId, @Param("userId")Integer userId);
}