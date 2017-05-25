package com.yd.dby.app.util.pingxx;

import java.util.HashMap;
import java.util.Map;

import com.pingplusplus.Pingpp;
import com.pingplusplus.exception.APIConnectionException;
import com.pingplusplus.exception.APIException;
import com.pingplusplus.exception.AuthenticationException;
import com.pingplusplus.exception.ChannelException;
import com.pingplusplus.exception.InvalidRequestException;
import com.pingplusplus.exception.RateLimitException;
import com.pingplusplus.model.Charge;
import com.yd.dby.app.entity.vo.ChargeVo;

public class ChargeCreate {

//	 public static void main(String[] args) {
//	 ChargeVo chargeVo = new ChargeVo();
//	 chargeVo.setAmount(new BigDecimal(1));
//	 chargeVo.setSubject("测试封装");
//	 chargeVo.setBody("这只是一个简单的测试");
//	 chargeVo.setChannel("alipay");
//	 chargeVo.setClientIp("127.0.0.1");
//	 
//	 System.out.println("--" + createCharge(chargeVo));
//	 }

	public static Charge createCharge(ChargeVo chargeVo) {
		Pingpp.apiKey = BasePing.apiKey;
		// 设置私钥路径，用于请求签名
		// Pingpp.privateKeyPath = BasePing.privateKeyFilePath;
		Pingpp.privateKey = "-----BEGIN RSA PRIVATE KEY-----\n"
				+ "MIICWwIBAAKBgQDD1OhCbNbIqPD3FrKfs8yoTmmJtpCaFsAL6j53X0TNLyvcPIVc\n"
				+ "9GT2KSiDXOHCvxq5js4kb6+0PcNd/u9m0sqx/7F+Rs7Tyzyzzi33ylVX+R8rg8YN\n"
				+ "pXii6/iDPInJInCedkpUGvQBgM2SuUvzo0JcX0sn72oa00FYTWB7VfJWrwIDAQAB\n"
				+ "AoGASajs0Ad2ZrW9JYPBHncX9PrBkc485+jExfaPFoGcNH2Yxtdw5nz6+OiUV4z5\n"
				+ "UjJl0AQaH/Rup6JPuSxqSkfhe5cynMnVGyWjY/8HjPxNbeH7OT63VOlbi+pfwOwE\n"
				+ "D5VZ0UwBEn4N8Sz3IomojHVGJVCfkBm2umLGDRHmB/4ap5kCQQD4c4+kheYinGEP\n"
				+ "68caANZ8taHQJsBieqxVRiOpnjwprFGZXNhiNIZOs4yOefpizhTJ3VPvaRLRYnDl\n"
				+ "LRVlIHnlAkEAycgSv40DMHy/gtR+WWH+dFo3u9IGoFZ7Xyv3yfNFV6Eentz8nmBC\n"
				+ "TsrIETho6bEee30WJkDweunXR5nuILq1AwJAbhdG3zA1+cAxgkyHvQ4B/OC/Au0o\n"
				+ "8npJQsABrXdgUVb6EWsCtwFvQhBth/Sk7oNC0vAv8TYuEHn+bI69nEH3wQJAS+UO\n"
				+ "+DFHw9MIDWNzapcrKARO9jwW9fPRT4iwGKuBp2YYqFQiSEeYaR5oheGbxFo0RupT\n"
				+ "jGvch76wlGPXZ0QSlQJAE9iJMStmqvsVI2lKKEJj06MVierV8/NCkP7q+OJ3OQ1O\n"
				+ "dNOinMmaNDHyP2pLXznsnR1QP5h3H+yKPDi5zdfhzw==\n" + "-----END RSA PRIVATE KEY-----";
		Charge charge = null;
		Map<String, Object> chargeMap = new HashMap<String, Object>();
		chargeMap.put("amount", chargeVo.getAmount());// 订单总金额, 人民币单位：分（如订单总金额为
														// 1 元，此处请填 100）
		chargeMap.put("currency", "cny");
		chargeMap.put("subject", chargeVo.getSubject());
		chargeMap.put("body", chargeVo.getBody());
		// String orderNo = new Date().getTime() + Main.randomString(7);//
		// 推荐使用8-20位，要求数字或字母，不允许其他字符
		chargeMap.put("order_no", chargeVo.getOrderNo());
		chargeMap.put("channel", chargeVo.getChannel());// 支付使用的第三方支付渠道取值，请参考：https://www.pingxx.com/api#api-c-new
		chargeMap.put("client_ip", chargeVo.getClientIp()); // 发起支付请求客户端的 IP
															// 地址，格式为 IPV4，如:
															// 127.0.0.1
		Map<String, String> app = new HashMap<String, String>();
		app.put("id", BasePing.appId);
		chargeMap.put("app", app);

		Map<String, Object> extra = new HashMap<String, Object>();
		// extra.put("open_id", "USER_OPENID");
		chargeMap.put("extra", extra);

		// 元数据
		Map<String, Object> metadataMap = new HashMap<String, Object>();
		metadataMap.put("payType", chargeVo.getMetadataPayType());
		chargeMap.put("metadata", metadataMap);

		try {
			// 发起交易请求
			charge = Charge.create(chargeMap);
			// 传到客户端请先转成字符串 .toString(), 调该方法，会自动转成正确的 JSON 字符串
			// String chargeString = charge.toString();
			// System.out.println(chargeString);
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (ChannelException e) {
			e.printStackTrace();
		} catch (RateLimitException e) {
			e.printStackTrace();
		} catch (AuthenticationException e) {
			e.printStackTrace();
		} catch (APIException e) {
			e.printStackTrace();
		} catch (InvalidRequestException e) {
			e.printStackTrace();
		}
		return charge;
	}
}
