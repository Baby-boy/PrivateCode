package com.yd.gcj.pay.zfb;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：1.0
 *日期：2016-06-06
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	//合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://openhome.alipay.com/platform/keyManage.htm?keyType=partner
	public static String partner = "2017010504858297";

	//商户的私钥,需要PKCS8格式，RSA公私钥生成：https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.nBDxfy&treeId=58&articleId=103242&docType=1
	public static String private_key = "";
	
	//支付宝的公钥，查看地址：https://openhome.alipay.com/platform/keyManage.htm?keyType=partner
	/*public static String alipay_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDL8m78havCu10y6MYM6UX44ayjZciMwy1Z3h335wx5z82Progm67ZD21HJHFD0zHnShj/dBImO6bqImFCE3Jp9skFe+kXoSUvRgho7guNqdOaoHMfL7qOJoO1eMQR8LuEg9u5okPAbuM4Tf3N44e5RN6fNYVc6de+RomRcz1dhxwIDAQAB";*/
	public static String alipay_public_key  = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAruhNLM7nxsfz9/HF+qT7dSElU0tlK9Sg9dxHJpk6TzjO1aLaXRrFEQRE/sl/DbjsOnypcrB2D9qivyi5xM5UmdfHTX0FZ0hS8Gs7td2g1R2S/eZqbMmGxfBx9u3vEcCwqwVqzCChZC+nK9OinWcLeSDkOIRPML7D9NxX0CoucspwQO4R+mHB5A1d+Pl2i8qmpARxDNjt+59Bo+u/ngP51AGfHdCJD/nua4xcolqD+9iONXfs8UYjIzrcHZh952q196SCceLl8iyIocn4mXYUIBMw4OsSq/MGDtBadywx1Palh5h80/+VNXR6rvl9276N7MEy0LbLpvzsKVPlt5tWKQIDAQAB";
	// 签名方式
	public static String sign_type = "RSA";
	
	// 调试用，创建TXT日志文件夹路径，见AlipayCore.java类中的logResult(String sWord)打印方法。
	public static String log_path ="E://";
		
	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
	
	//支付宝账号
	public static String seller_id = "3150694581@qq.com";
	
	// 接收通知的接口名
	public static String service = "mobile.securitypay.pay";
	
	//回调地址
	public static String notify_url = "http://www.buildingap.com/admin/cad_order_pay";
	
	//调用类型
	public static String payment_type = "1";
	
	
	/**
	 * 支付宝签名字符串拼接
	 * @param array
	 * @return
	 */
	public static String signAllString(String [] array){
		StringBuffer sb = new StringBuffer("");
		   for (int i = 0; i < array.length; i++) {
		      if(i>0)
		         sb.append("&");
		      sb.append(array[i]);
		   }
	   return sb.toString();
	}
}

