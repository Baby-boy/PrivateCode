package com.yd.dby.utils.pingpp;

import java.util.Date;

import com.yd.dby.utils.random.YdUtilRandom;

public enum YdUtilPingppChannel {

	// 支付宝 APP 支付
	ALIPAY("alipay"),
	// 支付宝手机网页支付
	ALIPAY_WAP("alipay_wap"),
	// 支付宝即时到账支付，即支付宝 PC 网页支付
	ALIPAY_PC_DIRECT("alipay_pc_direct"),
	// 支付宝当面付，即支付宝扫码支付
	ALIPAY_QR("alipay_qr"),
	// 百度钱包移动快捷支付，即百度钱包 APP 支付
	BFB("bfb"),
	// 百度钱包手机网页支付
	BFB_WAP("bfb_wap"),
	// 应用内快捷支付(内卡)
	CNP_U("cnp_u"),
	// 应用内快捷支付(外卡)
	CNP_F("cnp_f"),
	// 银联企业网银支付, 即 B2B 银联 PC 网页支付
	CP_B2B("cp_b2b"),
	// 银联支付，即银联 APP 支付(2015 年 1 月 1 日后的银联新商户使用。若有疑问，请与 Ping++ 或者相关的收单行联系)
	UPACP("upacp"),
	// 银联手机网页支付(2015 年 1 月 1 日后的银联新商户使用。若有疑问，请与 Ping++ 或者相关的收单行联系)
	UPACP_WAP("upacp_wap"),
	// 银联网关支付，即银联 PC 网页支付
	UPACP_PC("upacp_pc"),
	// 微信 APP 支付
	WX("wx"),
	// 微信公众号支付
	WX_PUB("wx_pub"),
	// 微信公众号扫码支付
	WX_PUB_QR("wx_pub_qr"),
	// 微信 WAP支付(此渠道仅针对特定客户开放)
	WX_WAP("wx_wap"),
	// 易宝手机网页支付
	YEEPAY_WAP("yeepay_wap"),
	// 京东手机网页支付
	JDPAY_WAP("jdpay_wap"),
	// 分期乐支付
	FQLPAY_WAP("fqlpay_wap"),
	// 量化派支付
	QGBC_WAP("qgbc_wap"),
	// 招行一网通
	CMB_WALLET("cmb_wallet"),
	// Apple Pay
	APPLEPAY_UPACP("applepay_upacp"),
	// 么么贷
	MMDPAY_WAP("mmdpay_wap"),
	// QQ 钱包支付
	QPAY("qpay");

	public static YdUtilPingppApp APP_ALIPAY_APP = null;
	public static YdUtilPingppExtra EXTRA_ALIPAY = null;

	
	public static YdUtilPingppApp APP_ALIPAY_PC_DIRECT = null;
	public static YdUtilPingppExtra EXTRA_ALIPAY_PC_DIRECT = null;
	
	private String type;

	private YdUtilPingppChannel(String type) {
		this.type = type;
	}

	public static YdUtilPingppChannel getType(String state) {
		for (YdUtilPingppChannel deviceType : YdUtilPingppChannel.values()) {
			if (deviceType.type.equalsIgnoreCase(state)) {
				return deviceType;
			}
		}
		throw new RuntimeException(state + " is not a valid Host Type!");
	}

	public String getStrType() {
		return type;
	}

	public YdUtilPingppApp getApp() throws Exception {
		switch (this) {
		case ALIPAY:
			return APP_ALIPAY_APP;
		case ALIPAY_PC_DIRECT:
			return APP_ALIPAY_PC_DIRECT;
		default:
			throw new Exception("暂时不支持该渠道");
		}
	}

	public YdUtilPingppExtra getExtra() throws Exception {
		switch (this) {
		case ALIPAY:
			return EXTRA_ALIPAY;
		case ALIPAY_PC_DIRECT:
			return EXTRA_ALIPAY_PC_DIRECT;
		default:
			throw new Exception("暂时不支持该渠道");
		}
	}

	public String getOrderNo() throws Exception {
		return new Date().getTime() + YdUtilRandom.number(7);
	}
}
