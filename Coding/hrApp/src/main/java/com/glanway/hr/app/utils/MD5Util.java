package com.glanway.hr.app.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密.
 * 
 * @author fuqihao
 * @version 1.0.0
 * @dateTime 2017年6月6日 下午3:52:56
 */
public class MD5Util {

	public static final String MD5 = "md5";

	public static String getMd5Encoding(String plainText) {
		byte[] md5CodeBytes = null;
		try {
			md5CodeBytes = MessageDigest.getInstance(MD5).digest(plainText.getBytes());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("不存在md5");
		}

		String md5Code = new BigInteger(1, md5CodeBytes).toString(16);// 转换成十六进制

		// 不足32位进行补位
		for (int i = 0; i < 32 - md5Code.length(); i++) {
			md5Code = "0" + md5Code;
		}

		return md5Code;
	}
}
