package com.yd.dby.utils.pingpp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.springframework.core.io.ClassPathResource;

import com.mysql.fabric.xmlrpc.base.Array;
import com.pingplusplus.model.Charge;
import com.yd.dby.utils.json.YdUtilJson;

@SuppressWarnings("all")
public class YdUtilPingpp {


	/* 商户订单号，适配每个渠道对此参数的要求，必须在商户系统内唯一 */
	/* order_no */

	/* Ping++ 应用 ID */
	/* app_id */

	/* 支付使用的第三方支付渠道 */
	/* channel */

	/* 订单总金额(必须大于0) 如订单总金额为 1元, amount为 100 */
	/* amount */

	/* 发起支付请求客户端的 IP地址，格式为 IPv4 整型, 如 127.0.0.1 */
	/* client_ip */

	/* 3 位 ISO货币代码, 人民币为 cny, 目前仅支持人民币 */
	/* currency */

	/* 商品标题, 该参数最长为 32 个 Unicode 字符, 银联全渠道(upacp / upacp_wap)限制在 32 个字节. */
	/* subject */

	/* 商品描述信息，该参数最长为 128 个 Unicode字符, yeepay_wap 对于该参数长度限制为 100 个 Unicode字符. */
	/* body */

	private static YdUtilPingppResult YdCharge(YdUtilPingppChannel channel, String client_ip, String order_no, float amount, String currency, String subject, String body, YdUtilPingppApp app, YdUtilPingppExtra extra, Integer type) throws Exception {

		Map<String, Object> chargeMap = new HashMap<String, Object>();

		chargeMap.put("channel", channel.getStrType());
		chargeMap.put("client_ip", client_ip);
		chargeMap.put("order_no", order_no);
		chargeMap.put("amount", amount);
		chargeMap.put("currency", currency);

		HashMap<String, Object> metaDataMap = new HashMap<String, Object>();
		metaDataMap.put("payType", type.toString());		// 1普通商品支付成功    2 ctc商品支付成功
		
		chargeMap.put("metadata", metaDataMap);
		if ( subject.length() > 32 ) {
			subject = subject.substring(0, 32);
		}
		chargeMap.put("subject", subject);
		chargeMap.put("body", body);
		chargeMap.put("app", app.toMap());

		if (extra != null) {
			chargeMap.put("extra", extra.toMap());
		}
		
		return new YdUtilPingppResult(order_no, Charge.create(chargeMap));

	}

	/* 支付使用的第三方支付渠道 */
	/* channel */

	/* 订单总金额(必须大于0) 如订单总金额为 1元, amount为 100 */
	/* amount */

	/* 商品标题, 该参数最长为 32 个 Unicode 字符, 银联全渠道(upacp / upacp_wap)限制在 32 个字节. */
	/* subject */

	/* 商品描述信息，该参数最长为 128 个 Unicode字符, yeepay_wap 对于该参数长度限制为 100 个 Unicode字符. */
	/* body */
	public static YdUtilPingppResult YdCharge(YdUtilPingppChannel channel, Integer amount, String subject, String body, Integer type) throws Exception {
		return YdCharge(channel, "127.0.0.1", channel.getOrderNo(), amount, "cny", subject, body, channel.getApp(), channel.getExtra(), type);
	}

	/* 支付使用的第三方支付渠道 */
	/* channel */

	/* 订单总金额(必须大于0) 如订单总金额为 1元, amount为 100 */
	/* amount */

	/* 商品标题, 该参数最长为 32 个 Unicode 字符, 银联全渠道(upacp / upacp_wap)限制在 32 个字节. */
	/* subject */

	/* 商品描述信息，该参数最长为 128 个 Unicode字符, yeepay_wap 对于该参数长度限制为 100 个 Unicode字符. */
	/* body */
	public static YdUtilPingppResult YdCharge(YdUtilPingppChannel channel, String order_no, float amount, String goodsName, String goodsBody, Integer type) throws Exception {
		return YdCharge(channel, "127.0.0.1", order_no, amount, "cny", goodsName, goodsBody, channel.getApp(), channel.getExtra(), type);
	}

	/* 支付使用的第三方支付渠道 */
	/* channel */

	/* 订单总金额(必须大于0) 如订单总金额为 1元, amount为 100 */
	/* amount */

	public static YdUtilPingppResult YdCharge(YdUtilPingppChannel channel, Integer amount) throws Exception {
		return YdCharge(channel, "127.0.0.1", channel.getOrderNo(), amount, "cny", "多宝鱼测试", "多宝鱼测试", channel.getApp(), channel.getExtra(), 1);
	}

	@SuppressWarnings("unchecked")
	public static String YdCallBackChargeSucceeded(HashMap<String, Object> input, String signatureString) throws Exception {
		if (!input.get("type").equals("charge.succeeded")) {
			throw new Exception("callback type must charge.succeeded");
		}

		if (!verifyData(YdUtilJson.toString(input), signatureString, getPubKey())) {
			throw new Exception("verify failure");
		}
		HashMap<String, Object> data = (HashMap<String, Object>) input.get("data");
		HashMap<String, Object> data_object = (HashMap<String, Object>) data.get("object");
		return data_object.get("order_no").toString();
	}

	public static String getStringFromFile(String filePath) throws Exception {
		ClassPathResource resource = new ClassPathResource(filePath);
		InputStreamReader inReader = new InputStreamReader(resource.getInputStream());
		BufferedReader bf = new BufferedReader(inReader);
		StringBuilder sb = new StringBuilder();
		String line;
		do {
			line = bf.readLine();
			if (line != null) {
				if (sb.length() != 0) {
					sb.append("\n");
				}
				sb.append(line);
			}
		} while (line != null);

		return sb.toString();
	}

	private static PublicKey getPubKey() throws Exception {
		String pubKeyString = getStringFromFile(YdUtilPingppEnv.PINGPP_PUBLIC_KEY_PATH);
		pubKeyString = pubKeyString.replaceAll("(-+BEGIN PUBLIC KEY-+\\r?\\n|-+END PUBLIC KEY-+\\r?\\n?)", "");
		byte[] keyBytes = Base64.decodeBase64(pubKeyString);

		// generate public key
		X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyFactory.generatePublic(spec);
		return publicKey;
	}

	/**
	 * 验证签名
	 * 
	 * @param dataString
	 * @param signatureString
	 * @param publicKey
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws SignatureException
	 */
	private static boolean verifyData(String dataString, String signatureString, PublicKey publicKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, UnsupportedEncodingException {
		byte[] signatureBytes = Base64.decodeBase64(signatureString);
		Signature signature = Signature.getInstance("SHA256withRSA");
		signature.initVerify(publicKey);
		signature.update(dataString.getBytes("UTF-8"));
		return signature.verify(signatureBytes);
	}

}
