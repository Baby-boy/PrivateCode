package com.yd.gcj.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.gcj.entity.YdMangerRefund;
import com.yd.gcj.entity.vo.YdMangerRefundVo;

public interface YdMangerMapperSystemRefund {

	/**
	 * description(查询所有的退款信息)
	 * @param
	 * @return
	 */
	List<YdMangerRefundVo> queryAllRefund(@Param("refund_fphone")String refund_fphone,@Param("refund_gphone")String refund_gphone,@Param("refund_state")Integer refund_state);

	/**
	 * description(根据refund_id查询当前退款的详细信息)
	 * @param
	 * @param refund_id
	 * @return
	 */
	YdMangerRefundVo queryRefundByRefundId(Integer refund_id);

	/**
	 * description(审核退款)
	 * @param
	 * @param ydMangerRefund
	 * @return
	 */
	Integer updateRefundStateByRefundId(YdMangerRefund ydMangerRefund);
	
	
	
}
