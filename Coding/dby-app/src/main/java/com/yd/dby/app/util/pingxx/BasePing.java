package com.yd.dby.app.util.pingxx;

import org.springframework.beans.factory.annotation.Value;

public class BasePing {

	/**
	 * Pingpp 管理平台对应的 API Key，api_key
	 * 
	 * 获取方式：登录 [Dashboard](https://dashboard.pingxx.com)->点击管理平台右上角公司名称->开发信息-> Secret Key
	 */
	final static String apiKey = "sk_live_SyHaf9nzv1805u9SKGKCeP4G";

	/**
	 * Pingpp 管理平台对应的应用 ID，app_id
	 * 
	 * 获取方式：登录 [Dashboard](https://dashboard.pingxx.com)->点击你创建的应用->应用首页->应用 ID(App ID)
	 */
	final static String appId = "app_subnvnzzHKarfXLX";

	/**
	 * 设置请求签名密钥，密钥对需要你自己用 openssl 工具生成，
	 * 
	 * 如何生成可以参考帮助中心：https://help.pingxx.com/article/123161；
	 * 
	 * 生成密钥后，需要在代码中设置请求签名的私钥(rsa_private_key.pem)；
	 * 
	 * 然后登录 [Dashboard](https://dashboard.pingxx.com)->点击右上角公司名称->开发信息->商户公钥（用于商户身份验证） 将你的公钥复制粘贴进去并且保存->先启用 Test 模式进行测试->测试通过后启用 Live 模式
	 */
	// 你生成的私钥路径
	static String privateKeyFilePath;

	@Value("${privateKeyFilePath}")
	public void setPrivateKeyFilePath(String privateKeyFilePath) {
		BasePing.privateKeyFilePath = privateKeyFilePath;
	}

}
