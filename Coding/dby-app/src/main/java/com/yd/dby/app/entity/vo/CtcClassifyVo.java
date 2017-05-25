package com.yd.dby.app.entity.vo;

import java.util.ArrayList;
import java.util.List;

public class CtcClassifyVo {

	private String ccNameOne;

	private List<CtcClassifyTwoVo> twoClassify = new ArrayList<>();

	public String getCcNameOne() {
		return ccNameOne;
	}

	public void setCcNameOne(String ccNameOne) {
		this.ccNameOne = ccNameOne;
	}

	public List<CtcClassifyTwoVo> getTwoClassify() {
		return twoClassify;
	}

	public void setTwoClassify(List<CtcClassifyTwoVo> twoClassify) {
		this.twoClassify = twoClassify;
	}

}
