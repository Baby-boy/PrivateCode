package com.yd.dby.app.entity.para;

/**
 * 说明: 购物车请求参数
 * 
 * @Author: fqh(fqh@zwzyd.com)
 * @date:2017年2月10日 下午3:25:13
 * @Version:1.0
 */
public class CartPara {

	private Integer goodsId;// 商品ID

	private Integer cartNum;// 购买数量

	private String cartIds;// 多个购物车ID

	private Integer cartId;// 购物车ID

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getCartNum() {
		return cartNum;
	}

	public void setCartNum(Integer cartNum) {
		this.cartNum = cartNum;
	}

	public String getCartIds() {
		return cartIds;
	}

	public void setCartIds(String cartIds) {
		this.cartIds = cartIds;
	}

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

}
