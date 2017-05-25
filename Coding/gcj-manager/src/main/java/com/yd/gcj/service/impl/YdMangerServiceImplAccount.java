package com.yd.gcj.service.impl;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.YdMangerBankTR;
import com.yd.gcj.entity.YdMangerMessage;
import com.yd.gcj.entity.vo.YdMangerUserVo;
import com.yd.gcj.mapper.YdMangerMapperAccount;
import com.yd.gcj.mapper.YdMangerMapperBankTR;
import com.yd.gcj.mapper.YdMangerMapperMessage;
import com.yd.gcj.mapper.YdMangerMapperUser;
import com.yd.gcj.pay.zfb.AlipayConfig;
import com.yd.gcj.pay.zfb.RSA;
import com.yd.gcj.service.YdMangerServiceAccount;
import com.yd.gcj.tool.MapInitFactory;

@Service("YdMangerServiceAccount")
public class YdMangerServiceImplAccount implements YdMangerServiceAccount {

	@Autowired
	private HttpSession session;// TODO 此session存在线程安全问题,后期进行修改

	@Autowired
	private YdMangerMapperAccount mapperAccount;

	@Autowired
	private YdMangerMapperUser mapperUser;

	@Autowired
	private YdMangerMapperMessage mapperMessage;

	@Autowired
	private YdMangerMapperBankTR mapperBankTR;

	@Override
	public Object $zfbSign(Integer userId, float price) {

		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			String orderNum = "";
			String body = "支付标题内容";
			String detail = "订单详情，交易订单：";

			String[] tsigns = { "_input_charset=\"" + AlipayConfig.input_charset + "\"", // 参数编码，
																							// 固定值
					"body=\"" + body + "\"", // 商品详情
					"it_b_pay=\"90m\"", // 设置未付款交易的超时时间
					"notify_url=\"" + AlipayConfig.notify_url + "\"", // 服务器异步通知页面路径
					"out_trade_no=\"" + orderNum + "\"", // 商户网站唯一订单号
					"partner=\"" + AlipayConfig.partner + "\"", // 签约合作者身份ID
					"payment_type=\"" + AlipayConfig.payment_type + "\"", // 支付类型，
																			// 固定值
					"seller_id=\"" + AlipayConfig.seller_id + "\"", // 签约卖家支付宝账号
					"service=\"" + AlipayConfig.service + "\"", // 服务接口名称， 固定值
					"subject=\"" + detail + "\"", // 商品名称
					"total_fee=\"" + String.valueOf(price) + "\""// 商品金额
			};

			String tsign = AlipayConfig.signAllString(tsigns);

			String sign = RSA.sign(tsign, AlipayConfig.private_key, "UTF-8");

			/*
			 * System.out.println("字符拼接signstr:"+tsign+"   生成的签名sign:"+sign,
			 * "生成签名");
			 */

			/*
			 * YdMangerPay pay = new YdMangerPay(); pay.setSignStr(tsign);
			 * pay.setSgin(sign);
			 */

			mapInitFactory.setData(sign);
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}

		return mapInitFactory.getMap();
	}

	@Override
	public Object $zfbNotive(Integer userId, float price, Integer payType) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			YdMangerUserVo user = mapperUser.$queryById(userId);
			if (user != null) {
				float reprice = price;
				float userPrice = user.getUser_cprice();
				price = userPrice + price;
				if (price >= 0) {
					System.out.println(userId + "\n" + price);
					Integer success = mapperAccount.$updateUserPrice(userId, price);
					if (success > 0) {
						updateUserPrice(price);
						Date date = new Date();
						YdMangerBankTR banktr = new YdMangerBankTR();
						banktr.setBtr_uid(userId);
						banktr.setBtr_rtime(date);
						banktr.setBtr_create_time(date);
						banktr.setBtr_type(0);
						banktr.setBtr_account("123456456");
						banktr.setBtr_tdesc("支付宝");
						banktr.setBtr_aos(null);
						banktr.setBtr_price(reprice);
						banktr.setBtr_osid(0);
						mapperBankTR.$insert(banktr);
						YdMangerMessage msg = new YdMangerMessage();
						msg.setMsg_isdel(0);
						msg.setMsg_contents("您好，您于2017-01-10 20:11:20 充值的" + reprice + "￥,充值成功！");
						msg.setMsg_said(0);
						msg.setMsg_sbid(userId);
						msg.setMsg_tid(0);
						msg.setMsg_type(0);
						msg.setMsg_create_time(date);
						mapperMessage.$insert(msg);
						mapInitFactory.setMsg("200", "交易成功！");
					} else {
						mapInitFactory.setMsg("502", "交易失败！");
					}
				} else {
					mapInitFactory.setMsg("503", "交易被取消！");
				}
			} else {
				mapInitFactory.setMsg("503", "参数异常！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object $wxSign(Integer userId, float price) {

		return null;
	}

	@Override
	public Object $wxNotive(Integer userId, float price, Integer payType) {
		return null;
	}

	/*
	 * 
	 * public void print(String data,String fileName){ try { String name =
	 * "/home//"+DateTo.getNowDT()+fileName+".txt"; FileOutputStream fos = new
	 * FileOutputStream(new File(name)); fos.write(new String(data).getBytes());
	 * fos.flush(); fos.close(); } catch (Exception e) { } }
	 */

	private void updateUserPrice(float price) {
		try {
			YdMangerUserVo userVo = (YdMangerUserVo) session.getAttribute("user");
			userVo.setUser_cprice(price);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
