package com.yd.gcj.entity.vo;

import com.yd.gcj.entity.YdMangerRefund;

public class YdMangerRefundVo extends YdMangerRefund {
	
	//退款状态
	private String refundState;

	public String getRefundState() {
			switch (super.getRefund_state()) {
			case 0:
				setRefundState("提交审核中");
				break;
			case 1:
				setRefundState("退给雇主");
				break;
			case 2:
				setRefundState("退给服务商");
				break;
			case 3:
				setRefundState("拒绝申请");
				break;
			default:
				setRefundState("未设置");
				break;
			}
		
		return refundState;
	}

	public void setRefundState(String refundState) {
		this.refundState = refundState;
	}
}
