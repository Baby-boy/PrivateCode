package com.connect.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.connect.util.GsonUtil;
import com.connect.util.UUIDUtil;

@Controller
@RequestMapping("user")
public class WXLoginController {

	private static final String APP_ID = "wxa517d463f51813d1";// APP_ID // TODO APP_ID需要修改
	private static final String APP_SECRET = "93af3cd349dccf7057e525326a4a1974";// APP_Key // TODO APP_Key需要修改

	/** 回调地址: 需要和该项目的第三方登录请求地址相同 */
	private static final String REDIRECT_URL = "http://ydgcj.tunnel.2bdata.com/user/wxLogin";// TODO 回调地址需要修改

	/** 获取code地址(十分钟有效期) */
	private static final String CODE_URL = "https://open.weixin.qq.com/connect/qrconnect?response_type=code&scope=snsapi_login&appid=";
	/** 获取Access Token地址 */
	private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?grant_type=authorization_code&appid=";
	/** 获取用户基本信息 */
	private static final String USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=";

	@SuppressWarnings("all")
	@RequestMapping(value = "wxLogin")
	public String jump(String code, String state, HttpServletRequest request) {
		if (null == code) {
			// 验证状态码(防止非法盗链,可以随机生成)
			state = UUIDUtil.generate();
			request.getSession().setMaxInactiveInterval(5 * 60);// 五分钟有效
			request.getSession().setAttribute("wx_state", state);
			return "redirect:" + CODE_URL + APP_ID + "&redirect_uri=" + REDIRECT_URL + "&state=" + state + "#wechat_redirect";
		} else {
			if (!state.equals(request.getSession().getAttribute("wx_state"))) {
				return "redirect:http://ydgcj.tunnel.2bdata.com";// TODO 这里的跳转需要修改
			}

			// 根据code码获取token
			String tokenUrl = ACCESS_TOKEN_URL + APP_ID + "&secret=" + APP_SECRET + "&code=" + code;
			// 创建Httpclient对象
			CloseableHttpClient httpclient = HttpClients.createDefault();
			// 创建http GET请求
			HttpGet tokenHttpGet = new HttpGet(tokenUrl);
			CloseableHttpResponse response = null;
			try {
				// 执行请求
				response = httpclient.execute(tokenHttpGet);
				// 判断返回状态是否为200
				if (response.getStatusLine().getStatusCode() == 200) {
					String content = EntityUtils.toString(response.getEntity(), "UTF-8");
					System.out.println("内容: " + content);
					if (StringUtils.isNotEmpty(content)) {
						Map<String, String> fromJson = GsonUtil.fromJson(content, Map.class);
						String accessToken = fromJson.get("access_token");
						String refreshToken = fromJson.get("refresh_token");
						String openid = fromJson.get("openid");
						System.out.println("token: " + accessToken + "\nrefreshToken: " + refreshToken + "\nopenid: " + openid);

						// 获取用户基本信息
						String userInfoUrl = USER_INFO_URL + accessToken + "&openid=" + openid;
						HttpGet userInfoHttpGet = new HttpGet(userInfoUrl);
						CloseableHttpResponse userInfoResponse = httpclient.execute(userInfoHttpGet);
						if (userInfoResponse.getStatusLine().getStatusCode() == 200) {
							String userInfoContent = EntityUtils.toString(userInfoResponse.getEntity(), "UTF-8");
							System.out.println("内容: " + userInfoContent);
							Map<String, Object> userInfo = GsonUtil.fromJson(userInfoContent, Map.class);
							String nickname = userInfo.get("nickname").toString();
							String headimgurl = userInfo.get("headimgurl").toString();
							Double sex = (Double) userInfo.get("sex");// 1为男,2为女性
							System.out.println("昵称: " + nickname + "\n性别: " + sex + "\n头像地址: " + headimgurl);
						}
					}
				}
			} catch (Exception e) {

			} finally {
				if (response != null) {
					try {
						response.close();
					} catch (IOException e) {
					}
				}
				try {
					httpclient.close();
				} catch (IOException e) {
				}
			}
			return "redirect:http://ydgcj.tunnel.2bdata.com";// TODO 这里的跳转需要修改
		}

	}

	/**
	 * 说明: 刷新token
	 * 
	 * @param refreshToken
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年3月13日 下午1:06:14
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "refreshToken")
	public String refreshToken(@RequestParam("refreshToken") String refreshToken) {
		String refreshTokenUrl = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=" + APP_ID + "&grant_type=refresh_token&refresh_token=" + refreshToken;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet refreshTokenHttpGet = new HttpGet(refreshTokenUrl);
		CloseableHttpResponse response = null;
		try {
			// 执行请求
			response = httpclient.execute(refreshTokenHttpGet);
			// 判断返回状态是否为200
			if (response.getStatusLine().getStatusCode() == 200) {
				String content = EntityUtils.toString(response.getEntity(), "UTF-8");
				System.out.println("内容: " + content);
				if (StringUtils.isNotEmpty(content)) {
					Map<String, String> fromJson = GsonUtil.fromJson(content, Map.class);
					String accessToken = fromJson.get("access_token");
					refreshToken = fromJson.get("refresh_token");
					String openid = fromJson.get("openid");
					System.out.println("token: " + accessToken + "\nrefreshToken: " + refreshToken + "\nopenid: " + openid);
				}
			}
		} catch (Exception e) {

		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
				}
			}
			try {
				httpclient.close();
			} catch (IOException e) {
			}
		}
		return "redirect:http://ydgcj.tunnel.2bdata.com";// TODO 这里的跳转需要修改
	}
}
