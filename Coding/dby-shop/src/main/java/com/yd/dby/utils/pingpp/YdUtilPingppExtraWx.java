package com.yd.dby.utils.pingpp;

public class YdUtilPingppExtraWx   extends YdUtilPingppExtra {

	/* 指定支付方式，指定不能使用信用卡支付可设置为 no_credit */
	private String limit_pay;

	/* 商品标记, 代金券或立减优惠功能的参数 */
	private String goods_tag;

	/* 支付完成后额外返回付款用户的微信 open_id */
	private String open_id;

	/* 支付完成后额外返回付款用户的付款银行类型 bank_type */
	private String bank_type;

	public String getLimit_pay() {
		return limit_pay;
	}

	public String getGoods_tag() {
		return goods_tag;
	}

	public String getOpen_id() {
		return open_id;
	}

	public String getBank_type() {
		return bank_type;
	}

	public void setLimit_pay(String limit_pay) {
		this.limit_pay = limit_pay;
	}

	public void setGoods_tag(String goods_tag) {
		this.goods_tag = goods_tag;
	}

	public void setOpen_id(String open_id) {
		this.open_id = open_id;
	}

	public void setBank_type(String bank_type) {
		this.bank_type = bank_type;
	}

}
