package com.yd.gcj.entity.vo;

import java.util.List;

import com.yd.gcj.entity.YdMangerLabel;

public class YdMangerLabelVo extends YdMangerLabel{

	private List<YdMangerLabel> labels;

	/**
	 * 获取 labels
	 * @return labels
	 */
	public List<YdMangerLabel> getLabels() {
		return labels;
	}

	/**
	 * 设置 labels
	 * @param labels
	 */
	public void setLabels(List<YdMangerLabel> labels) {
		this.labels = labels;
	}
	
}
