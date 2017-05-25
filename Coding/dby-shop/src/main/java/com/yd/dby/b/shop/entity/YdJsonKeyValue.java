package com.yd.dby.b.shop.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class YdJsonKeyValue {

	// 字典键
	private String key;
	// 字典值
	private String value;
	

	@JsonProperty(value = "key")
	public String getKey() {
		return key;
	}

	@JsonProperty(value = "value")
	public String getValue() {
		return value;
	}


	public void setKey(String key) {
		this.key = key;
	}

	public void setValue(String value) {
		this.value = value;
	}

}