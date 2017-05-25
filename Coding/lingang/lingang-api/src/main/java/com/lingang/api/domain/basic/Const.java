/**
 * 
 */package com.lingang.api.domain.basic;/** * @author   E-mail: * @date 创建时间：2016年2月4日 下午5:05:09  */
/**
 *@Description:全局变量
 *@Author:   联系方式：
 *@Since:2016年2月4日 下午5:05:09
 *@Version:1.0
 */
public class Const {

	/****************************************pingpp 管理平台****************************************************/
	/**
	 * pingpp 管理平台对应的 API key
	 */
//	public static final String apiKey = "sk_test_4KCqz5mbb1aPmrbHe94OivjD";//测试
	public static final String apiKey = "sk_live_zvXfnPmX10mHbT4SqDTO8yLK";//正式
	/**
	 * pingpp 管理平台对应的应用 ID
	 */
	public static final String appId = "app_uT84mLuPW5m1nLKa";
	/**
	 * 支付宝pc支付参数 success_url[string] 为支付成功的回调地址，required；
	 */
	public static final String success_url = "http://www.lingangsd.com/lingang-consumer/#/act/paySuccess";
	
	/**
	 * 支付宝pc支付参数 success_url[string] 为支付成功的回调地址，required；
	 */
	public static final String card_success_url = "http://www.lingangsd.com/lingang-consumer/#/bookPay/paySuccess";
	
	/****************************************pingpp 管理平台****************************************************/
	/**
	 * 成功状态码
	 */
	public static final String SUCCESS_CODE ="1000";
	
	/**
	 * 错误状态码
	 */
	public static final String ERROR_CODE ="1001";
	
	/**
	 * 参数错误状态码
	 */
	public static final String ERROR_PARAM_CODE ="1002";
	
	/**
	 * 无数据错误状态码
	 */
	public static final String ERROR_NODATA_CODE ="1003";
	
	/**
	 * 未知错误
	 */
	public static final String ERROR_UNKNOWN_CODE = "1004";
	
	/**
	 * 登录验证失败
	 */
	public static final String ERROR_LOGIN_CODE = "1005";
	
	/**
	 * 未登录
	 */
	public static final String NOT_LOGIN = "00";
	
	/**
	 * 用户被删除
	 */
	public static final String ERROR_USER_DELETED_CODE = "1006";
	
	/**
	 * 用户被拉入黑名单
	 */
	public static final String ERROR_USER_FORBIDDEN_CODE = "1007";
	
	/**
	 * 用户状态错误
	 */
	public static final String ERROR_USER_STATUS_CODE = "1008";
	
	/**
	 * 该记录已删除或不存在
	 */
	public static final String ERROR_RECORD_DELETED = "1009";
	
	/**
	 * 用户已经存在
	 */
	public static final String ERROR_USER_HAVE="1010";
	
	/**
	 * 用户不存在
	 */
	public static final String ERROR_USER_NULL ="1011";
	
	/**
	 * 账户名或密码错误
	 */
	public static final String ERROR_TEL_PWD ="1012";
	
	/**
	 * 密码错误
	 */
	public static final String ERROR_PWD ="10121";
	
	/**
	 * 短信验证码错误
	 */
	public static final String SMS_VERIFYCODE_NO = "1013";
	
	/**
	 * 借阅车已满
	 */
	public static final String READ_CAR_FULL = "1014";
	
	/**
	 * 已经加入借阅车
	 */
	public static final String READ_CAR_HAVE = "1015";
	
	/**
	 * 书籍库存不足
	 */
	public static final String BOOK_NULL = "1016";
	
	/**
	 * 书籍已下架
	 */
	public static final String BOOK_DOWN = "1017";
	
	/**
	 * 已有借阅订单
	 */
	public static final String BOOK_ORDER_HAVE="1018";
	
	/**
	 * 订单信息错误
	 */
	public static final String ERROR_BOOK_ORDER_MES="1019";
	
	/**
	 * 会员卡信息错误
	 */
	public static final String ERROR_VIP_CARD="1020";
	
	/**
	 * 短信验证码限制60秒
	 */
	public static final String SMS_LIMIT = "101";
	
	/**
	 * 没有会员卡
	 */
	public static final String ERROR_NOVIPCARD_CODE = "1021";
	
	/**
	 * 会员卡过期或已失效
	 */
	public static final String ERROR_FAIL_VIPCARD_CODE = "1022";
	
}
