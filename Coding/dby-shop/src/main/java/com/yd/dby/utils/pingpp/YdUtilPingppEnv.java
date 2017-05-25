package com.yd.dby.utils.pingpp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pingplusplus.Pingpp;

@Service
public class YdUtilPingppEnv {

	// Ping++的公钥
	public static String PINGPP_PUBLIC_KEY_PATH; // "res/pingpp_public_key.pem"

	// 我的公钥
	public static String YOU_PUBLIC_KEY_PATH; // "res/your_rsa_public_key.pem"

	// 我的私钥
	public static String YOU_PRIVATE_KEY_PATH; // "res/your_rsa_private_key.pem"

	// 密钥
	public static String SECRET_KEY; // "sk_test_mLSi9GbvPWP8iLCOOSPCu1K8"

	// 渠道-支付宝-APP
	public static String CHANNEL_ALIPAY_APP; // "app_OW1en59SebzPav1O"

	public static String CHANNEL_ALIPAY_PC_DIRECT; // "app_OW1en59SebzPav1O"

	public static String ALIPAY_PC_DIRECT_SUCCESS_URL;

	@Value("${yd.pay.Pingpp.PINGPP_PUBLIC_KEY_PATH}")
	public void setPINGPP_PUBLIC_KEY_PATH(String val) {
		YdUtilPingppEnv.PINGPP_PUBLIC_KEY_PATH = val;
	}

	@Value("${yd.pay.Pingpp.YOU_PUBLIC_KEY_PATH}")
	public void setYOU_PUBLIC_KEY_PATH(String val) {
		YdUtilPingppEnv.YOU_PUBLIC_KEY_PATH = val;
	}

	@Value("${yd.pay.Pingpp.YOU_PRIVATE_KEY_PATH}")
	public void setYOU_PRIVATE_KEY_PATH(String val) {
		YdUtilPingppEnv.YOU_PRIVATE_KEY_PATH = val;
		Pingpp.privateKeyPath = val;
	}

	@Value("${yd.pay.Pingpp.SECRET_KEY}")
	public void setSECRET_KEY(String val) {
		YdUtilPingppEnv.SECRET_KEY = val;
		Pingpp.apiKey = val;
	}

	@Value("${yd.pay.Pingpp.CHANNEL_ALIPAY_APP}")
	public void setCHANNEL_ALIPAY_APP(String val) {
		YdUtilPingppEnv.CHANNEL_ALIPAY_APP = val;
		YdUtilPingppChannel.APP_ALIPAY_APP = new YdUtilPingppApp(val);
		YdUtilPingppChannel.EXTRA_ALIPAY = null;
	}

	@Value("${yd.pay.Pingpp.CHANNEL_ALIPAY_PC_DIRECT}")
	public void setCHANNEL_ALIPAY_PC_DIRECT(String val) {
		YdUtilPingppEnv.CHANNEL_ALIPAY_PC_DIRECT = val;
		YdUtilPingppChannel.APP_ALIPAY_PC_DIRECT = new YdUtilPingppApp(val);
		YdUtilPingppChannel.EXTRA_ALIPAY_PC_DIRECT = new YdUtilPingppExtraAlipayPcDirect(val);
	}

	@Value("${yd.pay.Pingpp.ALIPAY_PC_DIRECT_SUCCESS_URL}")
	public void setALIPAY_PC_DIRECT_SUCCESS_URL(String val) {
		YdUtilPingppEnv.ALIPAY_PC_DIRECT_SUCCESS_URL = val;
		YdUtilPingppChannel.EXTRA_ALIPAY_PC_DIRECT = new YdUtilPingppExtraAlipayPcDirect(val);
	}

}
