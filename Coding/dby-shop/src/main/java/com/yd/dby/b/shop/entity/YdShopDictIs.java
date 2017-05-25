package com.yd.dby.b.shop.entity;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class YdShopDictIs {

	/*-----------表结构---------------*/
	// 字典编号
	private Integer dict_id;
	// 字典类型
	private String dict_type;
	// 字典键
	private Integer dict_key;
	// 字典值
	private String dict_value;
	// 字典创建时间
	private Timestamp dict_created_time;

	@JsonProperty(value = "id")
	public Integer getDict_id() {
		return dict_id;
	}

	@JsonProperty(value = "type")
	public String getDict_type() {
		return dict_type;
	}

	@JsonProperty(value = "key")
	public Integer getDict_key() {
		return dict_key;
	}

	@JsonProperty(value = "value")
	public String getDict_value() {
		return dict_value;
	}

	@JsonProperty(value = "created_time")
	public Timestamp getDict_created_time() {
		return dict_created_time;
	}

	public void setDict_id(Integer dict_id) {
		this.dict_id = dict_id;
	}

	public void setDict_type(String dict_type) {
		this.dict_type = dict_type;
	}

	public void setDict_key(Integer dict_key) {
		this.dict_key = dict_key;
	}

	public void setDict_value(String dict_value) {
		this.dict_value = dict_value;
	}

	public void setDict_created_time(Timestamp dict_created_time) {
		this.dict_created_time = dict_created_time;
	}

}