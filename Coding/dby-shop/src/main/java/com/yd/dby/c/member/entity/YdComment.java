package com.yd.dby.c.member.entity;


public class YdComment {
    private Integer user_id;//用户id

    private Integer order_id;//订单id

    private Integer goods_id;//商品id

    private Integer comment_id;//评论id

    private Integer comment_grade;//评论评分(1:好 2:中 3:差)
   
    private Integer comment_score;//评论评分(1-5)

    private String comment_content;//评论内容

    private String comment_pics;//评论图片
    
    private Integer comment_is_pic;//评论是否带图片
    
    private Integer comment_is_name;//是否匿名

    private Integer comment_total_like;//评论点赞数量
   
    private Integer comment_total_reply;//评论回复数量

    private long comment_created_time;//评论时间

    private String comment_buy_time;//购买时间

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	public Integer getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
	}

	public Integer getComment_id() {
		return comment_id;
	}

	public void setComment_id(Integer comment_id) {
		this.comment_id = comment_id;
	}

	public Integer getComment_grade() {
		return comment_grade;
	}

	public void setComment_grade(Integer comment_grade) {
		this.comment_grade = comment_grade;
	}

	public Integer getComment_score() {
		return comment_score;
	}

	public void setComment_score(Integer comment_score) {
		this.comment_score = comment_score;
	}

	public Integer getComment_total_like() {
		return comment_total_like;
	}

	public void setComment_total_like(Integer comment_total_like) {
		this.comment_total_like = comment_total_like;
	}

	public Integer getComment_total_reply() {
		return comment_total_reply;
	}

	public void setComment_total_reply(Integer comment_total_reply) {
		this.comment_total_reply = comment_total_reply;
	}

	public String getComment_content() {
		return comment_content;
	}

	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}

	public String getComment_pics() {
		return comment_pics;
	}

	public void setComment_pics(String comment_pics) {
		this.comment_pics = comment_pics;
	}

	public Integer getComment_is_pic() {
		return comment_is_pic;
	}

	public void setComment_is_pic(Integer comment_is_pic) {
		this.comment_is_pic = comment_is_pic;
	}

	public long getComment_created_time() {
		return comment_created_time;
	}

	public void setComment_created_time(long comment_created_time) {
		this.comment_created_time = comment_created_time;
	}

	public Integer getComment_is_name() {
		return comment_is_name;
	}

	public void setComment_is_name(Integer comment_is_name) {
		this.comment_is_name = comment_is_name;
	}

	public String getComment_buy_time() {
		return comment_buy_time;
	}

	public void setComment_buy_time(String comment_buy_time) {
		this.comment_buy_time = comment_buy_time;
	}

    
}