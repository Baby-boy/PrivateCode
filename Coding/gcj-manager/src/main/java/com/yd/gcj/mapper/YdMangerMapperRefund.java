package com.yd.gcj.mapper;

import com.yd.gcj.entity.YdMangerRefund;

public interface YdMangerMapperRefund {
	
	/***
	 * 提交退款申请
	 * @param refund
	 * @return
	 */
	Integer $insert(YdMangerRefund refund);
	
}
