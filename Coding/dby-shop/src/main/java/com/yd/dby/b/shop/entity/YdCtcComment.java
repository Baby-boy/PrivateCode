package com.yd.dby.b.shop.entity;

import java.util.Date;

import com.yd.dby.c.member.entity.User;
import com.yd.dby.c.member.entity.YdMemberCtc;

public class YdCtcComment {

	// 用户编号
	private Integer user_id;
	// 卖家用户编号
	private Integer seller_id;
	// 订单编号
	private Integer ctc_order_id;
	// 商品编号
	private Integer ctc_id;
	// 评论编号
	private Integer ctc_comment_id;
	// 评论评分(1:好,2:中,3:差)
	private Integer ctc_comment_grade;
	// 评论评分(1-5)
	private Integer ctc_comment_score;
	// 评论内容
	private String ctc_comment_content;
	// 评论图片(JSON字符串)
	private String ctc_comment_pics;
	// 评论是否匿名
	private Integer ctc_comment_is_name;
	// 评论是否带图片(1带,0不带)
	private Integer ctc_comment_is_pic;
	// 评论点赞数量
	private Integer ctc_comment_total_like;
	// 评论回复数量
	private Integer ctc_comment_total_reply;
	// 评论时间(毫秒时间戳)
	private String ctc_comment_created_time;
	// 购买时间
	private String ctc_comment_buy_time;
	// 卖家回复
	private String seller_replay_content;
	// 格式化后的时间 数据库表没有字段
	private String time;

	// 和商品表一对一关系
	private YdMemberCtc ydMemberCtc;

	// 和user表一对一关系
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public YdMemberCtc getYdMemberCtc() {
		return ydMemberCtc;
	}

	public void setYdMemberCtc(YdMemberCtc ydMemberCtc) {
		this.ydMemberCtc = ydMemberCtc;
	}

	public String getSeller_replay_content() {
		return seller_replay_content;
	}

	public void setSeller_replay_content(String seller_replay_content) {
		this.seller_replay_content = seller_replay_content;
	}

	public Integer getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(Integer seller_id) {
		this.seller_id = seller_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getCtc_order_id() {
		return ctc_order_id;
	}

	public void setCtc_order_id(Integer ctc_order_id) {
		this.ctc_order_id = ctc_order_id;
	}

	public Integer getCtc_id() {
		return ctc_id;
	}

	public void setCtc_id(Integer ctc_id) {
		this.ctc_id = ctc_id;
	}

	public Integer getCtc_comment_id() {
		return ctc_comment_id;
	}

	public void setCtc_comment_id(Integer ctc_comment_id) {
		this.ctc_comment_id = ctc_comment_id;
	}

	public Integer getCtc_comment_grade() {
		return ctc_comment_grade;
	}

	public void setCtc_comment_grade(Integer ctc_comment_grade) {
		this.ctc_comment_grade = ctc_comment_grade;
	}

	public Integer getCtc_comment_score() {
		return ctc_comment_score;
	}

	public void setCtc_comment_score(Integer ctc_comment_score) {
		this.ctc_comment_score = ctc_comment_score;
	}

	public String getCtc_comment_content() {
		return ctc_comment_content;
	}

	public void setCtc_comment_content(String ctc_comment_content) {
		this.ctc_comment_content = ctc_comment_content;
	}

	public String getCtc_comment_pics() {
		return ctc_comment_pics;
	}

	public void setCtc_comment_pics(String ctc_comment_pics) {
		this.ctc_comment_pics = ctc_comment_pics;
	}

	public Integer getCtc_comment_is_name() {
		return ctc_comment_is_name;
	}

	public void setCtc_comment_is_name(Integer ctc_comment_is_name) {
		this.ctc_comment_is_name = ctc_comment_is_name;
	}

	public Integer getCtc_comment_is_pic() {
		return ctc_comment_is_pic;
	}

	public void setCtc_comment_is_pic(Integer ctc_comment_is_pic) {
		this.ctc_comment_is_pic = ctc_comment_is_pic;
	}

	public Integer getCtc_comment_total_like() {
		return ctc_comment_total_like;
	}

	public void setCtc_comment_total_like(Integer ctc_comment_total_like) {
		this.ctc_comment_total_like = ctc_comment_total_like;
	}

	public Integer getCtc_comment_total_reply() {
		return ctc_comment_total_reply;
	}

	public void setCtc_comment_total_reply(Integer ctc_comment_total_reply) {
		this.ctc_comment_total_reply = ctc_comment_total_reply;
	}

	public String getCtc_comment_created_time() {
		return ctc_comment_created_time;
	}

	public void setCtc_comment_created_time(String ctc_comment_created_time) {
		this.ctc_comment_created_time = ctc_comment_created_time;
	}

	public String getCtc_comment_buy_time() {
		return ctc_comment_buy_time;
	}

	public void setCtc_comment_buy_time(String ctc_comment_buy_time) {
		this.ctc_comment_buy_time = ctc_comment_buy_time;
	}

}