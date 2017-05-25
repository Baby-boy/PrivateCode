package com.yd.dby.utils.pingpp;

public class YdUtilPingppExtraWxWap  extends YdUtilPingppExtra  {

	/* 支付完成的回调地址 */
	private String result_url;

	/* 商品标记, 代金券或立减优惠功能的参数 */
	private String goods_tag;

	/* 支付完成后额外返回付款用户的微信 open_id */
	private String open_id;

	/* 支付完成后额外返回付款用户的付款银行类型 bank_type */
	private String bank_type;

	public String getResult_url() {
		return result_url;
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

	public void setResult_url(String result_url) {
		this.result_url = result_url;
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
