package com.yd.dby.b.shop.controller;

import java.util.List;

public class YdOrderCoreRequestPayReturn {
	private Integer og_id;
	private Float score;
	private String content;
	private List<String> pics;

	public Integer getOg_id() {
		return og_id;
	}

	public Float getScore() {
		return score;
	}

	public String getContent() {
		return content;
	}

	public List<String> getPics() {
		return pics;
	}

	public void setOg_id(Integer og_id) {
		this.og_id = og_id;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setPics(List<String> pics) {
		this.pics = pics;
	}

}
