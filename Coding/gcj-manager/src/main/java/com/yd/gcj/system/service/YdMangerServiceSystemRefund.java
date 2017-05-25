package com.yd.gcj.system.service;

import java.util.List;

import com.yd.gcj.entity.YdMangerRefund;
import com.yd.gcj.entity.vo.YdMangerRefundVo;

public interface YdMangerServiceSystemRefund {

	/**
	 * description(查询所有大的退款信息)
	 * @param
	 * @return
	 */
	List<YdMangerRefundVo> queryAllRefund(String refund_fphone,String refund_gphone,Integer refund_state);

	/**
	 * description(根据refund_id查询当前退款的详细信息)
	 * @param
	 * @param refund_id
	 * @return
	 */
	YdMangerRefundVo queryRefundByRefundId(Integer refund_id);

	/**
	 * description(审核退款信息)
	 * @param
	 * @param ydMangerRefund
	 * @return
	 */
	Integer updateRefundStateByRefundId(YdMangerRefund ydMangerRefund);

}
