package com.yd.dby.app.entity.vo;

/**
 * 说明: 商品详情(评论信息)
 * 
 * @Author: lgl(lgl@zwzyd.com)
 * @date:2017年2月8日 下午2:38:29
 * @Version:1.0
 */
public class GoodsDetailsCommentVo {

	private String userNickname;

	private String userAvatar;

	private Float commentScore;

	private String commentCreatedTime;

	private String commentContent;

	private String commentPics;

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public String getUserAvatar() {
		return userAvatar;
	}

	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}

	public Float getCommentScore() {
		return commentScore;
	}

	public void setCommentScore(Float commentScore) {
		this.commentScore = commentScore;
	}

	public String getCommentCreatedTime() {
		return commentCreatedTime;
	}

	public void setCommentCreatedTime(String commentCreatedTime) {
		this.commentCreatedTime = commentCreatedTime;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public String getCommentPics() {
		return commentPics;
	}

	public void setCommentPics(String commentPics) {
		this.commentPics = commentPics;
	}
}
