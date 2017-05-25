package com.yd.dby.app.entity.para;

public class FavoritePara {

	private Integer favType;// 收藏类型 (1:店铺 2:商品 3:拍卖 4:c2c)

	private String favIds;// 多个收藏ID

	public Integer getFavType() {
		return favType;
	}

	public void setFavType(Integer favType) {
		this.favType = favType;
	}

	public String getFavIds() {
		return favIds;
	}

	public void setFavIds(String favIds) {
		this.favIds = favIds;
	}

}
