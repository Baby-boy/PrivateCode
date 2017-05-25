package com.lingang.common.util;


/**
 * 加密工具
 * @author tongc
 *
 */
public class SecurityUtils {
	
	/**
	 * 加密DB密码字段和登录密码
	 * @param rdmStr 如果没有传入随机数，自动生成
	 * @param password 明文密码
	 * @return
	 */
	public static final String encodeDBPassword(String rdmStr, String password){
		SecurityEncoderUtils securityEncoderUtils = SecurityEncoderUtils.getInstance("MD5");

		// 1,统一：md5
		String step1 = "md5";
		
		//2,第二段是12位随机数
		String step2 = rdmStr;
		
		// 未传入随机数，或者传入值不是12位时，随机生成
		if(StringUtils.isStrNull(rdmStr) || rdmStr.length() != 12){
			
			step2 = StringUtils.randomString(12);
		}

		//3,第三段是第二段+输入的密码md5
		String step3 = securityEncoderUtils.encodePassword(step2 + password);

		//4,最后将3段拼接插入数据库password,字段第一段$第二段$第三段
		String encodedPasswd = step1+"$"+step2+"$"+step3;
		
		return encodedPasswd;
	}
	
}
