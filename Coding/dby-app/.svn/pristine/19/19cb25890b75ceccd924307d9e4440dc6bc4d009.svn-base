package com.yd.dby.app.util.sms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.yd.dby.app.util.YdUtilBase64;
import com.yd.dby.app.util.YdUtilHttpclient;


public class YdMontnets {

	private static final String IP = "175.25.18.221";
	private static final String PORT = "8087";
	private static final String USERNAME = "J71238";
	private static final String PASSWORD = "292205";

	private final static HashMap<String, String> MSG = new HashMap<String, String>();
	static {
		MSG.put("-1", "参数为空。信息、电话号码等有空指针，登陆失败");
		MSG.put("-12", "有异常电话号码");
		MSG.put("-14", "实际号码个数超过1000");
		MSG.put("-999", "服务器内部错误");
		MSG.put("-10001", "用户登陆不成功(帐号不存在/停用/密码错误)");
		MSG.put("-10003", "用户余额不足");
		MSG.put("-10011", "信息内容超长");
		MSG.put("-10029", "此用户没有权限从此通道发送信息(用户没有绑定该性质的通道，比如：用户发了小灵通的号码)");
		MSG.put("-10030", "不能发送移动号码");
		MSG.put("-10031", "手机号码(段)非法");
		MSG.put("-10057", "IP受限");
		MSG.put("-10056", "连接数超限");
	}

	private static String encodeSendSmsUrl() {
		return "http://" + IP + ":" + PORT + "/MWGate/wmgw.asmx/MongateMULTIXSend";
	}

	private static String encodeQueryBalanceUrl() {
		return "http://" + IP + ":" + PORT + "/MWGate/wmgw.asmx/MongateQueryBalance";
	}

	private static List<NameValuePair> encodeSendSmsparameters(List<YdZzzMontnetsExt> MultixMt) throws Exception {

		StringBuilder extstr = new StringBuilder();

		for (YdZzzMontnetsExt ext : MultixMt) {
			// 设置流水号
			extstr.append("0").append("|");
			// 设置通道号
			extstr.append("*").append("|");
			// 设置手机号码
			extstr.append(ext.getMobile()).append("|");
			// 设置短信内容
			extstr.append(YdUtilBase64.encode(ext.getMsg(), "GBK", "UTF-8"));

			// 每条短信消息逗号分隔
			extstr.append(",");
		}
		extstr.setLength(extstr.length() - 1);

		List<NameValuePair> parameters = new ArrayList<NameValuePair>(3);
		parameters.add(new BasicNameValuePair("userId", USERNAME));
		parameters.add(new BasicNameValuePair("password", PASSWORD));
		parameters.add(new BasicNameValuePair("multixmt", extstr.toString()));

		return parameters;
	}

	private static List<NameValuePair> encodeSendSmsparameters(String mobile, String msg) throws Exception {

		StringBuilder extstr = new StringBuilder();

		extstr.append("0").append("|");
		// 设置通道号
		extstr.append("*").append("|");
		// 设置手机号码
		extstr.append(mobile).append("|");
		// 设置短信内容
		extstr.append(YdUtilBase64.encode(msg, "GBK", "UTF-8"));

		List<NameValuePair> parameters = new ArrayList<NameValuePair>(3);
		parameters.add(new BasicNameValuePair("userId", USERNAME));
		parameters.add(new BasicNameValuePair("password", PASSWORD));
		parameters.add(new BasicNameValuePair("multixmt", extstr.toString()));

		return parameters;
	}

	private static List<NameValuePair> encodeQueryBalanceparameters() {
		List<NameValuePair> parameters = new ArrayList<NameValuePair>(2);
		parameters.add(new BasicNameValuePair("userId", USERNAME));
		parameters.add(new BasicNameValuePair("password", PASSWORD));

		return parameters;
	}

	public static void SendMultixSms(List<YdZzzMontnetsExt> MultixMt) throws Exception {
		String Url = encodeSendSmsUrl();
		List<NameValuePair> parameters = encodeSendSmsparameters(MultixMt);
		String Message = YdUtilHttpclient.post(Url, parameters, "UTF-8");
		if (Message == null || Message.isEmpty()) {
			throw new Exception("没有返回值");
		}
		Document doc = DocumentHelper.parseText(Message);
		Element el = doc.getRootElement();
		String result = el.getText();
		System.out.println(result);
		if (MSG.containsKey(result)) {
			throw new Exception(MSG.get(result));
		}
	}

	public static String SendMultixSms(String mobile, String msg) throws Exception {
		String resultStr="OK";
		String Url = encodeSendSmsUrl();
		List<NameValuePair> parameters = encodeSendSmsparameters(mobile, msg);
		String Message = YdUtilHttpclient.post(Url, parameters, "UTF-8");
		if (Message == null || Message.isEmpty()) {
			resultStr="没有返回值";
//			throw new Exception("没有返回值");
		}
		Document doc = DocumentHelper.parseText(Message);
		Element el = doc.getRootElement();
		String result = el.getText();
		if (MSG.containsKey(result)) {
			resultStr=MSG.get(result);
//			throw new Exception(MSG.get(result));
		}
		return resultStr;
	}

	public static Integer QueryBalance() throws Exception {
		String Url = encodeQueryBalanceUrl();
		List<NameValuePair> parameters = encodeQueryBalanceparameters();
		String Message = YdUtilHttpclient.post(Url, parameters, "UTF-8");
		if (Message == null || Message.isEmpty()) {
			throw new Exception("没有返回值");
		}
		Document doc = DocumentHelper.parseText(Message);
		Element el = doc.getRootElement();
		Integer result = Integer.parseInt(el.getText());
		if (result == null || result <= 0) {
			System.out.println(result);
			throw new Exception("查询余额失败");
		}
		return result;
	}
	
	
//	 public static void main(String[] args) {
//	 try {
//		 System.out.println(YdMontnets.SendMultixSms("15221583664", "您正在注册账号, 验证码是:" + 1234));
//	 } catch (Exception e) {
//	 // TODO Auto-generated catch block
//	 e.printStackTrace();
//	 }
//	 }

}
