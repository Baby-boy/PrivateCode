package com.yd.dby.utils.pingpp;

/* alipay 支付宝 APP */
public class YdUtilPingppExtraAlipay extends YdUtilPingppExtra {

	/*
	 * 开放平台返回的包含账户信息的 token（授权令牌，商户在一定时间内对支付宝某些服务的访问权限）。 通过授权登录后获取的
	 * alipay_open_id ，作为该参数的 value ，登录授权账户即会为支付账户，32 位字符串。
	 */
	private String extern_token;

	/*
	 * 是否发起实名校验，T 代表发起实名校验；F 代表不发起实名校验。
	 */
	private String rn_check;

	/*
	 * 支付完成将额外返回付款用户的支付宝账号。
	 */
	private String buyer_account;

	public String getExtern_token() {
		return extern_token;
	}

	public String getRn_check() {
		return rn_check;
	}

	public String getBuyer_account() {
		return buyer_account;
	}

	public void setExtern_token(String extern_token) {
		this.extern_token = extern_token;
	}

	public void setRn_check(String rn_check) {
		this.rn_check = rn_check;
	}

	public void setBuyer_account(String buyer_account) {
		this.buyer_account = buyer_account;
	}

}
