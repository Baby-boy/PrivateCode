package com.yd.dby.b.shop.entity;

import java.math.BigDecimal;

public class YdCtcMessage {

	private Integer messae_ctc_id;//ctc商品留言表
	private Integer ctc_id;//ctc商品id
	private String ctc_img;//ctc商品图片
	private String ctc_name;//ctc商品名称
	private BigDecimal ctc_price;//ctc商品价格
	private String ctc_summary;//ctc商品简介
	public Integer getMessae_ctc_id() {
		return messae_ctc_id;
	}
	public void setMessae_ctc_id(Integer messae_ctc_id) {
		this.messae_ctc_id = messae_ctc_id;
	}
	public Integer getCtc_id() {
		return ctc_id;
	}
	public void setCtc_id(Integer ctc_id) {
		this.ctc_id = ctc_id;
	}
	public String getCtc_img() {
		return ctc_img;
	}
	public void setCtc_img(String ctc_img) {
		this.ctc_img = ctc_img;
	}
	public String getCtc_name() {
		return ctc_name;
	}
	public void setCtc_name(String ctc_name) {
		this.ctc_name = ctc_name;
	}
	public BigDecimal getCtc_price() {
		return ctc_price;
	}
	public void setCtc_price(BigDecimal ctc_price) {
		this.ctc_price = ctc_price;
	}
	public String getCtc_summary() {
		return ctc_summary;
	}
	public void setCtc_summary(String ctc_summary) {
		this.ctc_summary = ctc_summary;
	}
}
