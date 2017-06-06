package com.tadpole.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.tadpole.common.Constant;

public abstract class MD5Util {

	/** md5 */
	public static final String MD5 = "md5";

	/**
	 * MD5加密.
	 *
	 * @param plainText
	 * @return
	 * @author FUQIHAO
	 * @throws NoSuchAlgorithmException
	 * @dateTime 2017年6月6日 下午3:28:02
	 */
	public static String md5(String plainText) {
		byte[] plainTextBytes = null;
		try {
			plainTextBytes = MessageDigest.getInstance(MD5).digest(plainText.getBytes());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("没有md5");
		}

		String md5code = new BigInteger(1, plainTextBytes).toString(16);

		for (int i = 0; i < 32 - md5code.length(); i++) {
			md5code = Constant.ZERO_STR + md5code;
		}

		return md5code;
	}

	public static void main(String[] args) {
		System.out.println(md5("admin"));
	}
}
