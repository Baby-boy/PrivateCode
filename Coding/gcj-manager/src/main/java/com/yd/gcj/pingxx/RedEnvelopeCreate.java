package com.yd.gcj.pingxx;

import java.util.HashMap;
import java.util.Map;

import com.pingplusplus.Pingpp;
import com.pingplusplus.exception.APIConnectionException;
import com.pingplusplus.exception.APIException;
import com.pingplusplus.exception.AuthenticationException;
import com.pingplusplus.exception.ChannelException;
import com.pingplusplus.exception.InvalidRequestException;
import com.pingplusplus.exception.RateLimitException;
import com.pingplusplus.model.RedEnvelope;
import com.yd.gcj.entity.vo.RedEnvelopeVo;

public class RedEnvelopeCreate {

//	public static void main(String[] args) {
//		RedEnvelopeVo redEnvelopeVo = new RedEnvelopeVo();
//		redEnvelopeVo.setAmount(new BigDecimal(100));
//		redEnvelopeVo.setSubject("测试红包");
//		redEnvelopeVo.setBody("测试封装");
//		redEnvelopeVo.setChannel("wx_pub");
//		redEnvelopeVo.setOrderNo("1234567891");
//		redEnvelopeVo.setDescription("这只是一个简单的测试");
//		redEnvelopeVo.setSendName("上海鼎固建筑科技有限公司");
//		redEnvelopeVo.setOpenId("oGRqG0qX3SOg9rTHCqUJsHSyV8-g");
//
//		System.out.println("--" + redEnvelopeCreateCharge(redEnvelopeVo));
//	}

	public static RedEnvelope redEnvelopeCreateCharge(RedEnvelopeVo redEnvelopeVo) {
		Pingpp.apiKey = BasePing.apiKey;
		// 设置私钥路径，用于请求签名
		Pingpp.privateKeyPath = BasePing.privateKeyFilePath;
//		Pingpp.privateKey = "-----BEGIN RSA PRIVATE KEY-----\n"
//				+"MIICXQIBAAKBgQCpUz7HzCB1GJpptd4eZrYc1ki1WEHEHqxRP9TettXxQsLMuBiD"
//				+"o3zRFku/PXFu4Jc/NRXCwgAywqbSbrW54A+qWf137u8ApYmiqiSgbVPKGa21HSiI"
//				+"RrT/NnFlQIC3DjTinhmrbvyLMKKyGk8sTK3g+KpfRufu/kArzPziH0YnswIDAQAB"
//				+"AoGBAJ38Setpji8zUX+obSK4etrVJOnu+w3eALGeeSOEPIHq5I7Whijs9ZywzOQW"
//				+"lCv76aLUCQW6tSynabeWDIDtii7HVylwqKedOp5zU/oZNvM19FJGqUrXT63jn9xu"
//				+"ZYnyhiYnz5w0rNOw5HANR92LHLbsu+WAppxUz5y1BT3y0SXhAkEA0hLY3pnsbJlY"
//				+"qsIwZI28tplEPWMI2+OzVk7xoVhI2XY6EFjJLDt2fHGUjp2clqmZer2n5Hmoi2sQ"
//				+"z2iCDVrRbwJBAM5X1k95RDnd+FFlw6BoFletd5xAnCJySjQtPGqmcdKtpDEFpNWU"
//				+"wINkigmXoFgk/i/+Fu+zolIpJgYP0PTNI/0CQQCwexboQyWEwnrj0Ym4H/7G5BGZ"
//				+"BKb/m9YgjuedpT5mOlVjQEOZPkc3GnA0M6LGS6rdm5D9d1sp7QNNSz3enEpPAkAz"
//				+"IbEJoJM3g7m08fK4HRLsRyAMqDYIkymwJEWEaBpsinrVawCdrG7IIHHUTq59KS2T"
//				+"1vL4Ext2IJw7hE/TWcJBAkBI9CdrOslLFY1SmufZU08Lfw9NmqB+1RXUJANrk9+9"
//				+"Jh5MRY1s+NezsOZ1BTvrZakTtgWw/9s53VhyGcV0Se7B==\n" + "-----END RSA PRIVATE KEY-----";
		
		Map<String, Object> redenvelope = new HashMap<String, Object>();
		redenvelope.put("amount", redEnvelopeVo.getAmount());// 订单总金额, 人民币单位：分（如订单总金额为 1 元，此处请填 100，金额限制在 100 ~ 20000 之间，即 1 ~ 200 元）
		redenvelope.put("currency", "cny");
		redenvelope.put("subject", redEnvelopeVo.getSubject());
		redenvelope.put("body", redEnvelopeVo.getBody());
		redenvelope.put("order_no", redEnvelopeVo.getOrderNo());// 红包使用的商户订单号。wx(新渠道)、wx_pub 规定为 1 ~ 28 位不能重复的数字
		redenvelope.put("channel", redEnvelopeVo.getChannel());// 目前支持 wx(新渠道)、 wx_pub
		redenvelope.put("recipient", redEnvelopeVo.getOpenId());// 接收者 id， 为用户在 wx(新渠道)、wx_pub 下的 open_id
		redenvelope.put("description", redEnvelopeVo.getDescription());

		Map<String, String> app = new HashMap<String, String>();
		app.put("id", BasePing.appId);
		redenvelope.put("app", app);

		Map<String, String> extra = new HashMap<String, String>();
		extra.put("send_name", redEnvelopeVo.getSendName());// 商户名称，最多 32 个字节
		redenvelope.put("extra", extra);
		RedEnvelope red = null;
		try {
			red = RedEnvelope.create(redenvelope);
			System.out.println(red);
		} catch (AuthenticationException e) {
			e.printStackTrace();
		} catch (InvalidRequestException e) {
			e.printStackTrace();
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIException e) {
			e.printStackTrace();
		} catch (ChannelException e) {
			e.printStackTrace();
		} catch (RateLimitException e) {
			e.printStackTrace();
		}
		return red;
	}
}
