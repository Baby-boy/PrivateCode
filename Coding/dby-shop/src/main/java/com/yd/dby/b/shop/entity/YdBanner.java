package com.yd.dby.b.shop.entity;

import java.util.HashMap;


public class YdBanner {
	private Integer banner_id;
	private String banner_type;
	private String banner_title;
	private String banner_cover;
	private String banner_url;
	private Integer banner_is_show;
	private Integer banner_sort;
	private Integer banner_open_type;
	private Integer banner_key_id;
	public Integer getBanner_key_id() {
		return banner_key_id;
	}
	public void setBanner_key_id(Integer banner_key_id) {
		this.banner_key_id = banner_key_id;
	}
	public Integer getBanner_open_type() {
		return banner_open_type;
	}
	public void setBanner_open_type(Integer banner_open_type) {
		this.banner_open_type = banner_open_type;
	}
	public Integer getBanner_id() {
		return banner_id;
	}
	public void setBanner_id(Integer banner_id) {
		this.banner_id = banner_id;
	}
	public String getBanner_type() {
		return banner_type;
	}
	public void setBanner_type(String banner_type) {
		this.banner_type = banner_type;
	}
	public String getBanner_title() {
		return banner_title;
	}
	public void setBanner_title(String banner_title) {
		this.banner_title = banner_title;
	}
	public String getBanner_cover() {
		return banner_cover;
	}
	public void setBanner_cover(String banner_cover) {
		this.banner_cover = banner_cover;
	}
	public String getBanner_url() {
		return banner_url;
	}
	public void setBanner_url(String banner_url) {
		this.banner_url = banner_url;
	}
	public Integer getBanner_is_show() {
		return banner_is_show;
	}
	public void setBanner_is_show(Integer banner_is_show) {
		this.banner_is_show = banner_is_show;
	}
	public Integer getBanner_sort() {
		return banner_sort;
	}
	public void setBanner_sort(Integer banner_sort) {
		this.banner_sort = banner_sort;
	}
	
	public HashMap<String, Object> getBanner_type_px() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("index", 		"宽度：720px | 高度470px");
		map.put("circulation", 	"宽度：1430px | 高度446px");
		map.put("replacement", 	"宽度：1430px | 高度446px");
		map.put("brandstore", 	"宽度：980px | 高度340px");
		map.put("maintenance", 	"宽度：1920px | 高度400px");
		map.put("c2c", 			"宽度：740px | 高度400px");
		map.put("auction", 		"拍卖...");
		map.put("groupon", 		"团购...");
		map.put("seckill", 		"宽度：1920px | 高度400px");
		map.put("activity", 	"活动...");
		map.put("supply", 		"宽度：740px | 高度400px");
		map.put("information", 	"信息商城...");
		return map;
	}
	
	public String getBanner_type_text() {
		String string = "";
		switch (banner_type) {
			case "index": 		string = "首页";  break;
			case "circulation": string = "流通";  break;
			case "replacement": string = "置换";  break;
			case "brandstore": 	string = "品牌店";  break;
			case "maintenance": string = "养护";  break;
			case "c2c": 		string = "懒鱼";  break;
			case "auction": 	string = "拍卖";  break;
			case "groupon": 	string = "团购";  break;
			case "seckill": 	string = "秒杀";  break;
			case "activity": 	string = "活动";  break;
			case "supply": 		string = "供货商城";  break;
			case "information": string = "信息商城";  break;

			default: 			string = "类型错误"; break;
		}
		return string;
	}
	
	public HashMap<String, Object> getBanner_type_list() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("index", "首页");
		map.put("circulation", "流通");
		map.put("replacement", "置换");
		map.put("brandstore", "品牌店");
		map.put("maintenance", "养护");
		map.put("c2c", "懒鱼");
		map.put("auction", "拍卖");
		map.put("groupon", "团购");
		map.put("seckill", "秒杀");
		map.put("activity", "活动");
		map.put("supply", "供货商城");
		map.put("information", "信息商城");
		return map;
	}
	
	
}
