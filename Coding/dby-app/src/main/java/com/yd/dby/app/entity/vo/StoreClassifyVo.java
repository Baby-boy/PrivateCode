package com.yd.dby.app.entity.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明: 店铺分类(筛选)
 * 
 * @Author: lgl(lgl@zwzyd.com)
 * @date:2017年2月8日 下午7:47:16
 * @Version:1.0
 */
public class StoreClassifyVo {

	private Integer scIdOne;

	private String titleOne;

	private Integer pidOne;

	private List<StoreClassifyTwoVo> twoClassify = new ArrayList<>();

	public Integer getScIdOne() {
		return scIdOne;
	}

	public void setScIdOne(Integer scIdOne) {
		this.scIdOne = scIdOne;
	}

	public String getTitleOne() {
		return titleOne;
	}

	public void setTitleOne(String titleOne) {
		this.titleOne = titleOne;
	}

	public Integer getPidOne() {
		return pidOne;
	}

	public void setPidOne(Integer pidOne) {
		this.pidOne = pidOne;
	}

	public List<StoreClassifyTwoVo> getTwoClassify() {
		return twoClassify;
	}

	public void setTwoClassify(List<StoreClassifyTwoVo> twoClassify) {
		this.twoClassify = twoClassify;
	}

}
