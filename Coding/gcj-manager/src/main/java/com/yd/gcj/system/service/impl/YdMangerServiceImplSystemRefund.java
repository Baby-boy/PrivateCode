package com.yd.gcj.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.YdMangerRefund;
import com.yd.gcj.entity.vo.YdMangerRefundVo;
import com.yd.gcj.system.mapper.YdMangerMapperSystemRefund;
import com.yd.gcj.system.service.YdMangerServiceSystemRefund;

@Service("ydMangerServiceSystemRefund")
public class YdMangerServiceImplSystemRefund implements YdMangerServiceSystemRefund{

	@Autowired
	private YdMangerMapperSystemRefund ydMangerMapperSystemRefund;
	
	//查询所有的退款信息
	@Override
	public List<YdMangerRefundVo> queryAllRefund(String refund_fphone,String refund_gphone,Integer refund_state) {
		List<YdMangerRefundVo> refundList = ydMangerMapperSystemRefund.queryAllRefund(refund_fphone,refund_gphone,refund_state);
		return refundList;
	}

	//查询当前退款的详细信息
	@Override
	public YdMangerRefundVo queryRefundByRefundId(Integer refund_id) {
		YdMangerRefundVo ydMangerRefund = ydMangerMapperSystemRefund.queryRefundByRefundId(refund_id);
		return ydMangerRefund;
	}

	//审核退款信息
	@Override
	public Integer updateRefundStateByRefundId(YdMangerRefund ydMangerRefund) {
		Integer updateNum = ydMangerMapperSystemRefund.updateRefundStateByRefundId(ydMangerRefund);
		return updateNum;
	}


	
}
