package com.yd.dby.app.entity.vo;

import java.util.Date;

/**
 * 说明: 个人中心信息
 * 
 * @Author: fqh(fqh@zwzyd.com)
 * @date:2017年2月13日 下午2:40:03
 * @Version:1.0
 */
public class UserCenterVo {

	private String userAvatar;// 用户头像

	private String userUsername;// 用户名称

	private String userNickname;// 用户昵称

	private Integer userSex;// 用户性别

	private Date userBirthday;// 用户生日

	private String userProvinceValue;// 用户所在省名称

	private Integer userIntegration;// 用户积分

	private Integer unPaidCount;// 未支付总数

	private Integer nonReceiptCount;// 待收货总数

	private Integer noCommentCount;// 未回复总数

	private Integer favoriteGoodsCount;// 收藏商品总数

	private Integer favoriteStoreCount;// 收藏店铺总数

	private Integer addressCount;// 收货地址总数

	public String getUserAvatar() {
		return userAvatar;
	}

	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}

	public String getUserUsername() {
		return userUsername;
	}

	public void setUserUsername(String userUsername) {
		this.userUsername = userUsername;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public Integer getUserSex() {
		return userSex;
	}

	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}

	public Date getUserBirthday() {
		return userBirthday;
	}

	public void setUserBirthday(Date userBirthday) {
		this.userBirthday = userBirthday;
	}

	public String getUserProvinceValue() {
		return userProvinceValue;
	}

	public void setUserProvinceValue(String userProvinceValue) {
		this.userProvinceValue = userProvinceValue;
	}

	public Integer getUserIntegration() {
		return userIntegration;
	}

	public void setUserIntegration(Integer userIntegration) {
		this.userIntegration = userIntegration;
	}

	public Integer getUnPaidCount() {
		return unPaidCount;
	}

	public void setUnPaidCount(Integer unPaidCount) {
		this.unPaidCount = unPaidCount;
	}

	public Integer getNonReceiptCount() {
		return nonReceiptCount;
	}

	public void setNonReceiptCount(Integer nonReceiptCount) {
		this.nonReceiptCount = nonReceiptCount;
	}

	public Integer getNoCommentCount() {
		return noCommentCount;
	}

	public void setNoCommentCount(Integer noCommentCount) {
		this.noCommentCount = noCommentCount;
	}

	public Integer getFavoriteGoodsCount() {
		return favoriteGoodsCount;
	}

	public void setFavoriteGoodsCount(Integer favoriteGoodsCount) {
		this.favoriteGoodsCount = favoriteGoodsCount;
	}

	public Integer getFavoriteStoreCount() {
		return favoriteStoreCount;
	}

	public void setFavoriteStoreCount(Integer favoriteStoreCount) {
		this.favoriteStoreCount = favoriteStoreCount;
	}

	public Integer getAddressCount() {
		return addressCount;
	}

	public void setAddressCount(Integer addressCount) {
		this.addressCount = addressCount;
	}

}
