package com.yd.dby.utils.oss;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.yd.dby.utils.base64.YdUtilBase64;
import com.yd.dby.utils.binary.YdBinary;
import com.yd.dby.utils.httpclient.YdUtilHttpclient;
import com.yd.dby.utils.json.YdUtilJson;
import com.yd.dby.utils.string.YdString;

public class YdOssUtil {

	public static Map<String, Object> generateToken() throws Exception {
		OSSClient client = new OSSClient(YdOssEnv.ENDPOINT, YdOssEnv.ACCESS_ID, YdOssEnv.ACCESS_KEY);

		long expireTime = 30;
		long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
		Date expiration = new Date(expireEndTime);
		PolicyConditions policyConds = new PolicyConditions();
		policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
		policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, YdOssEnv.DIR);

		String postPolicy = client.generatePostPolicy(expiration, policyConds);
		String encodedPolicy = YdString.toBase64String(postPolicy);
		
		System.out.println(encodedPolicy);
		
		String postSignature = client.calculatePostSignature(postPolicy);
		
		Map<String, Object> callback = new LinkedHashMap<String, Object>();
		callback.put("callbackUrl", YdOssEnv.CALLBACK_RequestURL);
		callback.put("callbackBody", "filename=");
		callback.put("callbackBodyType", "application/x-www-form-urlencoded");

		Map<String, Object> respMap = new LinkedHashMap<String, Object>();
		respMap.put("accessid", YdOssEnv.ACCESS_ID);
		respMap.put("policy", encodedPolicy);
		respMap.put("signature", postSignature);
		respMap.put("dir", YdOssEnv.DIR);
		respMap.put("host", YdOssEnv.HOST);
		respMap.put("callback", YdUtilBase64.encode(YdUtilJson.toString(callback)));
		respMap.put("expire", String.valueOf(expireEndTime / 1000));
		return respMap;
	}

	public static final void verifyCallback(String authorization, String x_oss_pub_key_url, String requestURI, String paramsString) throws Exception {

		String pubKeyAddr = YdString.fromBase64String(x_oss_pub_key_url);
		if (!pubKeyAddr.startsWith("http://gosspublic.alicdn.com/") && !pubKeyAddr.startsWith("https://gosspublic.alicdn.com/")) {
			throw new Exception("pub key addr must be oss addrss");
		}

		String publicKey = YdUtilHttpclient.get(pubKeyAddr);
		publicKey = publicKey.replace("-----BEGIN PUBLIC KEY-----", "");
		publicKey = publicKey.replace("-----END PUBLIC KEY-----", "");

		doCheck(requestURI + "\n" + paramsString, YdBinary.fromBase64String(authorization), publicKey);

	}

	/**
	 * 检查公钥和数字签名是否正确
	 *
	 * @param content
	 *            需要检查的内容
	 * @param sign
	 *            数字签名
	 * @param publicKey
	 *            公钥
	 * @return
	 */
	private static final void doCheck(String content, byte[] sign, String publicKey) throws Exception {
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		byte[] encodedKey = BinaryUtil.fromBase64String(publicKey);
		PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
		java.security.Signature signature = java.security.Signature.getInstance("MD5withRSA");
		signature.initVerify(pubKey);
		signature.update(content.getBytes());
		if (!signature.verify(sign)) {
			throw new Exception("verify failure");
		}
	}
}
