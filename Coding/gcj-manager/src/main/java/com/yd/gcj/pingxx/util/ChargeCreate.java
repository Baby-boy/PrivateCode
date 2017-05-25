package com.yd.gcj.pingxx.util;

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
import com.yd.gcj.entity.vo.ChargeVo;

public class ChargeCreate {

	// public static void main(String[] args) {
	// ChargeVo chargeVo = new ChargeVo();
	// chargeVo.setAmount(new BigDecimal(1));
	// chargeVo.setSubject("测试封装");
	// chargeVo.setBody("这只是一个简单的测试");
	// chargeVo.setChannel("alipay_wap");
	// chargeVo.setClientIp("127.0.0.1");
	// chargeVo.setOrderNo("1234567890");
	//
	// System.out.println("--" + createCharge(chargeVo));
	// }

	public static Charge createCharge(ChargeVo chargeVo) {
		Pingpp.apiKey = BasePing.apiKey;
		// 设置私钥路径，用于请求签名
		Pingpp.privateKeyPath = BasePing.privateKeyFilePath;
		// Pingpp.privateKey = "-----BEGIN RSA PRIVATE KEY-----\n"
		// +"MIICXQIBAAKBgQCpUz7HzCB1GJpptd4eZrYc1ki1WEHEHqxRP9TettXxQsLMuBiD"
		// +"o3zRFku/PXFu4Jc/NRXCwgAywqbSbrW54A+qWf137u8ApYmiqiSgbVPKGa21HSiI"
		// +"RrT/NnFlQIC3DjTinhmrbvyLMKKyGk8sTK3g+KpfRufu/kArzPziH0YnswIDAQAB"
		// +"AoGBAJ38Setpji8zUX+obSK4etrVJOnu+w3eALGeeSOEPIHq5I7Whijs9ZywzOQW"
		// +"lCv76aLUCQW6tSynabeWDIDtii7HVylwqKedOp5zU/oZNvM19FJGqUrXT63jn9xu"
		// +"ZYnyhiYnz5w0rNOw5HANR92LHLbsu+WAppxUz5y1BT3y0SXhAkEA0hLY3pnsbJlY"
		// +"qsIwZI28tplEPWMI2+OzVk7xoVhI2XY6EFjJLDt2fHGUjp2clqmZer2n5Hmoi2sQ"
		// +"z2iCDVrRbwJBAM5X1k95RDnd+FFlw6BoFletd5xAnCJySjQtPGqmcdKtpDEFpNWU"
		// +"wINkigmXoFgk/i/+Fu+zolIpJgYP0PTNI/0CQQCwexboQyWEwnrj0Ym4H/7G5BGZ"
		// +"BKb/m9YgjuedpT5mOlVjQEOZPkc3GnA0M6LGS6rdm5D9d1sp7QNNSz3enEpPAkAz"
		// +"IbEJoJM3g7m08fK4HRLsRyAMqDYIkymwJEWEaBpsinrVawCdrG7IIHHUTq59KS2T"
		// +"1vL4Ext2IJw7hE/TWcJBAkBI9CdrOslLFY1SmufZU08Lfw9NmqB+1RXUJANrk9+9"
		// +"Jh5MRY1s+NezsOZ1BTvrZakTtgWw/9s53VhyGcV0Se7B==\n" + "-----END RSA PRIVATE KEY-----";

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
		extra.put("success_url", BasePing.alipayWapSuccessUrl);// 成功回调地址
		// extra.put("cancel_url", BasePing.alipayWapSuccessUrl);// 取消支付回调地址
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
