package com.yd.dby.utils.jwt;

import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.yd.dby.a.sys.entity.YdSysUserJwt;
import com.yd.dby.utils.base64.YdUtilBase64;
import com.yd.dby.utils.json.YdUtilJson;
import com.yd.dby.utils.uuid.YdUtilUUID;

public class YdJwt {
	private static final String CHARSET = "utf-8";
	private static final String ALGORITHM = "HmacSHA256";
	private static final String SECRET = "YUNdong*#&%";
	private static final Long EXPIRE = 1000L * 60 * 5 * 10000;

	private static byte[] encrypt(String input, String secret) throws Exception {
		Mac mac = Mac.getInstance(ALGORITHM);
		SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(CHARSET), ALGORITHM);
		mac.init(secret_key);
		return mac.doFinal(input.getBytes(CHARSET));
	}

	public static String verify(String token, String salt) throws Exception {
		if (token == null) {
			throw new Exception("token is null");
		}
		if (token.isEmpty()) {
			throw new Exception("token is empty");
		}

		String[] t = token.split("\\.");

		if (t.length != 3) {
			throw new Exception("wrong format");
		}

		if (System.currentTimeMillis() > Long.valueOf(t[0])) {
			throw new Exception("expired");
		}

		if (!t[2].equals(Base64.getEncoder().encodeToString(encrypt(t[0] + t[1], SECRET + salt)))) {
			throw new Exception("verify fail");
		}

		return YdUtilBase64.decode(t[1]);
	}

	public static YdSysUserJwt verifyUser(String token) throws Exception {
		return YdUtilJson.toObject(verify(token, ""), YdSysUserJwt.class);
	}

	public static YdSysUserJwt verifyUser(String token, String salt) throws Exception {
		return YdUtilJson.toObject(verify(token, salt), YdSysUserJwt.class);
	}

	public static YdSysUserJwt verifyUser(YdJwtType type, String token, String salt) throws Exception {
		return YdUtilJson.toObject(verify(token, salt + type.getStrType()), YdSysUserJwt.class);
	}

	public static YdSysUserJwt verifyUser(YdJwtType type, String token) throws Exception {
		return YdUtilJson.toObject(verify(token, type.getStrType()), YdSysUserJwt.class);
	}

	public static String verify(YdJwtType type, String token, String salt) throws Exception {
		return verify(token, salt + type.getStrType());
	}

	public static String generate(String body, String salt) throws Exception {
		String expire = String.valueOf(System.currentTimeMillis() + EXPIRE);
		String payload = YdUtilBase64.encode(body);
		String secret = YdUtilBase64.encode(encrypt(expire + payload, SECRET + salt));
		return expire + "." + payload + "." + secret;
	}

	public static String generate(String salt) throws Exception {
		return generate(YdUtilUUID.generate(), salt);
	}

	public static String generate(YdJwtType type, String salt) throws Exception {
		// return generate(salt + type.getStrType());
		return generate(salt);
	}

	public static String generate(YdSysUserJwt payload, String salt) throws Exception {
		return generate(YdUtilJson.toString(payload), salt);
	}

	public static String generate(YdSysUserJwt payload) throws Exception {
		return generate(YdUtilJson.toString(payload));
	}

	public static String generate(YdJwtType type, String payload, String salt) throws Exception {
		return generate(payload, salt + type.getStrType());
	}

	public static String generate(YdJwtType type, YdSysUserJwt payload, String salt) throws Exception {
		return generate(payload, salt + type.getStrType());
	}

	public static String generate(YdJwtType type, YdSysUserJwt user) throws Exception {
		return generate(YdUtilJson.toString(user), type.getStrType());
	}

}