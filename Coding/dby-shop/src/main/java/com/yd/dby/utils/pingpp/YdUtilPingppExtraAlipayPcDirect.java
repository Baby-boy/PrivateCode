package com.yd.dby.utils.pingpp;

/* alipay_pc_direct 支付宝 PC网页支付  */
public class YdUtilPingppExtraAlipayPcDirect extends YdUtilPingppExtra {

	/* 支付成功的回调地址 */
	private String success_url;

	/* 是否开启防钓鱼网站的验证参数(如果已申请开通防钓鱼时间戳验证, 则此字段必填) */
	private Boolean enable_anti_phishing_key;

	/* 客户端 IP, 用户在创建交易时, 该用户当前所使用机器的IP */
	private String exter_invoke_ip;


	public YdUtilPingppExtraAlipayPcDirect(String success_url) {
		this.success_url = success_url;
	}
	
	public String getSuccess_url() {
		return success_url;
	}

	public Boolean getEnable_anti_phishing_key() {
		return enable_anti_phishing_key;
	}

	public String getExter_invoke_ip() {
		return exter_invoke_ip;
	}

	public void setSuccess_url(String success_url) {
		this.success_url = success_url;
	}

	public void setEnable_anti_phishing_key(Boolean enable_anti_phishing_key) {
		this.enable_anti_phishing_key = enable_anti_phishing_key;
	}

	public void setExter_invoke_ip(String exter_invoke_ip) {
		this.exter_invoke_ip = exter_invoke_ip;
	}


}
