package com.yd.dby.wx.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * @author 作者 E-mail:
 * @version 创建时间：2017年2月17日 下午12:08:04
 * 
 */
@SuppressWarnings("all")
public class YdWxParamUtils {

	public static final String AppId = "wxa517d463f51813d1";
	public static final String AppSecret = "93af3cd349dccf7057e525326a4a1974".trim();

public static JSONObject doGetJson(String url) throws ClientProtocolException, IOException {
	JSONObject jsonObject = null;
	HttpClient client = new DefaultHttpClient();
	HttpGet httpGet = new HttpGet(url);
	HttpResponse response = client.execute(httpGet);
	HttpEntity entity = response.getEntity();
	if (entity != null) {
		System.err.println(entity);
		String result = EntityUtils.toString(entity);
		jsonObject = JSONObject.fromObject(result);
	}
	httpGet.releaseConnection();
	return jsonObject;
}

	public static String doGetAccessToken(String url) throws ClientProtocolException, IOException {
		String accessToken = null;
		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		HttpResponse response = client.execute(httpGet);
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			String result = EntityUtils.toString(entity, "UTF-8");
			String[] split = result.split("&");
			String[] split2 = split[0].split("=");
			accessToken = split2[1].toString();
		}
		httpGet.releaseConnection();
		return accessToken;
	}

	// 获得随机值
	public static final String generateRandomString(int length) {
		String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(allChar.charAt(random.nextInt(allChar.length())));
		}
		return sb.toString();
	}

	/**
	 * 通过code向微信开放平台请求access_token
	 *
	 * @param code
	 *
	 */
	public static Map<String, Object> get_access_token(String code) {
		// 拼接请求access_token的链接
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token";

		String params = "appid=" + AppId + "&secret=" + AppSecret + "&code=" + code + "&grant_type=authorization_code";

		String resultJson = sendGet(url, params);
		Map<String, Object> map = parseData(resultJson);// 将返回的json数据转换为Map结构存储
		/*
		 * 示例:{ "access_token":"ACCESS_TOKEN", "expires_in":7200,
		 * "refresh_token":"REFRESH_TOKEN", "openid":"OPENID", "scope":"SCOPE",
		 * "unionid": "o6_bmasdasdsad6_2sgVt7hMZOPfL" }
		 */
		return map;
	}

	/**
	 * 函数名称: refresh_access_token
	 *
	 * 函数描述: access_token超时,使用refresh_token重新获得一个access_token
	 *
	 * @param refresh_token
	 * @return Map<String, String>
	 */
	public static Map<String, Object> refresh_access_token(String refresh_token) {
		// access_token超时,此时需要重新获得一个access_token。
		String url_access = "https://api.weixin.qq.com/sns/oauth2/refresh_token";

		StringBuffer params_access = new StringBuffer().append("appid=").append(AppId).append("&grant_type=refresh_token").append("&refresh_token=").append(refresh_token);
		String resultJson = sendGet(url_access, params_access.toString());
		Map<String, Object> map = parseData(resultJson);// 将返回的json数据转换为Map结构存储
		/*
		 * { "access_token":"ACCESS_TOKEN", "expires_in":7200,
		 * "refresh_token":"REFRESH_TOKEN", "openid":"OPENID", "scope":"SCOPE" }
		 */
		return map;
	}

	/**
	 * 函数名称: get_userinfo
	 *
	 * 函数描述: 通过access_token去获取用户的信息
	 *
	 * @param access_token
	 * @return Map<String, String>
	 */
	public static Map<String, Object> get_userinfo(String access_token, String openid) {
		// access_token超时,此时需要重新获得一个access_token。
		String url_userinfo = "https://api.weixin.qq.com/sns/userinfo";
		StringBuffer params_userinfo = new StringBuffer().append("access_token=").append(access_token).append("&openid=").append(openid);
		String resultJson = sendGet(url_userinfo, params_userinfo.toString());
		Map<String, Object> map = parseData(resultJson);// 将返回的json数据转换为Map结构存储
		if (map.size() > 3) {
			// 已经正确获取到用户信息
			/*
			 * { "openid":"OPENID", "nickname":"NICKNAME", "sex":1,
			 * "province":"PROVINCE", "city":"CITY", "country":"COUNTRY",
			 * "headimgurl":
			 * "http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0"
			 * , "privilege":[ "PRIVILEGE1", "PRIVILEGE2" ], "unionid":
			 * " o6_bmasdasdsad6_2sgVt7hMZOPfL" }
			 */
			return map;
		} else {
			if (map.get("errcode").equals("42001")) {
				// access_token超时,需要重新获得access_token超时，再请求用户信息
				Map<String, Object> map1 = refresh_access_token(access_token);
				String access_token2 = map1.get("access_token").toString();
				String openid2 = map1.get("openid").toString();
				// 刷新以后重新获取用户的信息
				get_userinfo(access_token2, openid2);
			}
		}
		return map;
	}

	/**
	 * 函数名称: parseData 函数描述: 将json字符串转换为Map<String, String>结构
	 *
	 * @param data
	 * @return Map<String, String>
	 */
	private static Map<String, Object> parseData(String data) {
		GsonBuilder gb = new GsonBuilder();
		Gson g = gb.create();
		Map<String, Object> map = g.fromJson(data, new TypeToken<Map<String, Object>>() {
		}.getType());
		return map;
	}

	/**
	 * 向指定URL发送GET方法的请求
	 *
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();

			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
}
