package com.yd.dby.b.shop.entity;

public class YdShopMessage {
	
	private Integer message_id;//短消息索引id
	private Integer message_state;//消息状态
	private Integer message_type;//消息类型
	private Integer message_obj_id;//消息对应类型的id
	private String message_content;//消息内容
	private String create_date;//创建时间(毫秒)
	private Integer message_author_id;//消息发起人的id
	private String message_author_name;//消息发起人的姓名
	private String message_author_img;//消息发起人的头像
	private Integer message_receive_id;//接收人id
	private String message_receive_name;//接收人姓名
	private String message_receive_img;//接收人头像
	private Integer message_farther_id;//消息的父类id
	public Integer getMessage_id() {
		return message_id;
	}
	public void setMessage_id(Integer message_id) {
		this.message_id = message_id;
	}
	public Integer getMessage_state() {
		return message_state;
	}
	public void setMessage_state(Integer message_state) {
		this.message_state = message_state;
	}
	public Integer getMessage_type() {
		return message_type;
	}
	public void setMessage_type(Integer message_type) {
		this.message_type = message_type;
	}
	public Integer getMessage_obj_id() {
		return message_obj_id;
	}
	public void setMessage_obj_id(Integer message_obj_id) {
		this.message_obj_id = message_obj_id;
	}
	
	public String getMessage_content() {
		return message_content;
	}
	public void setMessage_content(String message_content) {
		this.message_content = message_content;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public Integer getMessage_author_id() {
		return message_author_id;
	}
	public void setMessage_author_id(Integer message_author_id) {
		this.message_author_id = message_author_id;
	}
	public String getMessage_author_name() {
		return message_author_name;
	}
	public void setMessage_author_name(String message_author_name) {
		this.message_author_name = message_author_name;
	}
	public String getMessage_author_img() {
		return message_author_img;
	}
	public void setMessage_author_img(String message_author_img) {
		this.message_author_img = message_author_img;
	}
	public Integer getMessage_receive_id() {
		return message_receive_id;
	}
	public void setMessage_receive_id(Integer message_receive_id) {
		this.message_receive_id = message_receive_id;
	}
	public String getMessage_receive_name() {
		return message_receive_name;
	}
	public void setMessage_receive_name(String message_receive_name) {
		this.message_receive_name = message_receive_name;
	}
	public String getMessage_receive_img() {
		return message_receive_img;
	}
	public void setMessage_receive_img(String message_receive_img) {
		this.message_receive_img = message_receive_img;
	}
	public Integer getMessage_farther_id() {
		return message_farther_id;
	}
	public void setMessage_farther_id(Integer message_farther_id) {
		this.message_farther_id = message_farther_id;
	}
}
