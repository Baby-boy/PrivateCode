package com.yd.dby.app.entity.para;

import java.util.ArrayList;
import java.util.List;

public class OrderPara extends BasePara {

	private Integer orderState=1;

	private Integer userId;

	private List<Integer> states = new ArrayList<>();

	private void updateStates() {

		switch (getOrderState()) {
		case 1:
			this.states = null;
			break;
		case 2:
			states.clear();
			this.states.add(1);
			break;
		case 3:
			states.clear();
			this.states.add(2);
			this.states.add(3);
			break;
		case 4:
			states.clear();
			this.states.add(4);
			break;
		case 5:
			states.clear();
			this.states.add(5);
			break;

		default:
			this.states = null;
			break;
		}
	}

	public Integer getOrderState() {
		return orderState;
	}

	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public List<Integer> getStates() {
		updateStates();
		return states;
	}

	public void setStates(List<Integer> states) {
		this.states = states;
	}
}
