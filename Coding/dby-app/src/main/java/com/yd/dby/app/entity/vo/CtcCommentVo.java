package com.yd.dby.app.entity.vo;

/**
 * 说明: 懒鱼评论(订单评价)
 * 
 * @Author: fqh(fqh@zwzyd.com)
 * @date:2017年2月16日 下午9:21:49
 * @Version:1.0
 */
public class CtcCommentVo {

	private Integer avgScore;// 懒鱼用户平均分

	private Integer totalComment;// 懒鱼用户总评价数

	private Integer userId;// 用户ID

	private String userAvatar;// 用户头像

	private String userNickname;// 用户昵称

	private String ctcCommentCreatedTime;// 评论创建时间

	private String ctcCommentContent;// 评论内容

	public Integer getAvgScore() {
		return avgScore;
	}

	public void setAvgScore(Integer avgScore) {
		this.avgScore = avgScore;
	}

	public Integer getTotalComment() {
		return totalComment;
	}

	public void setTotalComment(Integer totalComment) {
		this.totalComment = totalComment;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserAvatar() {
		return userAvatar;
	}

	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public String getCtcCommentCreatedTime() {
		return ctcCommentCreatedTime;
	}

	public void setCtcCommentCreatedTime(String ctcCommentCreatedTime) {
		this.ctcCommentCreatedTime = ctcCommentCreatedTime;
	}

	public String getCtcCommentContent() {
		return ctcCommentContent;
	}

	public void setCtcCommentContent(String ctcCommentContent) {
		this.ctcCommentContent = ctcCommentContent;
	}

}
