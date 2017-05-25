package com.yd.dby.utils;

import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.yd.dby.utils.uuid.YdUtilUUID;

public class YdUtilTokenOnlyExpire {
	private static final String CHARSET = "utf-8";
	private static final String ALGORITHM = "HmacSHA256";
	private static final String SECRET = "YUNdong*#&%";
	private static final Long EXPIRE = 1000L * 60 * 5 * 10000;

	private static byte[] encrypt(String input, String secret) throws Exception {
		Mac mac = Mac.getInstance(ALGORITHM);
		SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(CHARSET),
				ALGORITHM);
		mac.init(secret_key);
		return mac.doFinal(input.getBytes(CHARSET));
	}

	public static void verification(String token, String salt) throws Exception {
		if (token == null) {
			throw new Exception("验证失败");
		}
		String[] tmp = token.split("\\.");
		if (tmp.length != 3) {
			throw new Exception("验证失败");
		}
		if (System.currentTimeMillis() > Long.valueOf(tmp[0])) {
			throw new Exception("验证失败");
		}

		if (!tmp[2].equals(Base64.getEncoder().encodeToString(encrypt(tmp[0] + tmp[1], SECRET + salt)))) {
			throw new Exception("验证失败");
		}
	}

	public static String generate(String input) throws Exception {
		String expire = String.valueOf(System.currentTimeMillis() + EXPIRE);
//		String uuid = YdUtilUUID.generate().toString();
		String uuid = YdUtilUUID.randomUUID().toString();
		String secret = Base64.getEncoder().encodeToString(encrypt(expire + uuid, SECRET + input));
		return expire + "." + uuid + "." + secret;
	}
}
