package com.yd.dby.utils.jwt;

public enum YdJwtType {

	// 注册
	SMS_REGISTER("SMS_REGISTER"),
	// 忘记密码
	SMS_FORGETASSWORD("SMS_FORGETASSWORD"),
	// 重置登录密码
	SMS_RESETLOGINPASSWORD("SMS_RESETLOGINPASSWORD"),
	// 重置支付密码
	SMS_RESETPAYPASSWORD("SMS_RESETPAYPASSWORD"),
	
	// 修改手机号码
	SMS_RESETMOBILE("SMS_RESETMOBILE"),
	
	// 普通
	LOGIN("LOGIN"),
	// 注册-用户名
	REGISTER_NAME("REGISTER_NAME"),
	// 注册-手机号
	REGISTER_MOBILE("REGISTER_MOBILE"),
	// 忘记密码
	FORGETASSWORD("FORGETASSWORD"),
	// 重置登录密码
	RESETLOGINPASSWORD("RESETLOGINPASSWORD"),
	// 重置支付密码
	RESETPAYPASSWORD("RESETPAYPASSWORD");

	private String type;

	private YdJwtType(String type) {
		this.type = type;
	}

	public static YdJwtType getType(String state) {
		for (YdJwtType deviceType : YdJwtType.values()) {
			if (deviceType.type.equalsIgnoreCase(state)) {
				return deviceType;
			}
		}
		throw new RuntimeException(state + " is not a valid Type!");
	}

	public String getStrType() {
		return type;
	}

}