package com.yd.dby.utils.pingpp;

import com.pingplusplus.model.Charge;

public class YdUtilPingppResult {

	private String order_no;
	private Charge charge;

	public YdUtilPingppResult(String order_no, Charge charge) {
		this.order_no = order_no;
		this.charge = charge;
	}

	public String getOrder_no() {
		return order_no;
	}

	public Charge getCharge() {
		return charge;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public void setCharge(Charge charge) {
		this.charge = charge;
	}

}
