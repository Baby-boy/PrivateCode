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

import com.connect.util.GsonUtil;
import com.connect.util.UUIDUtil;

@Controller
@RequestMapping("user")
public class QQLoginController {

	private static final String CLIENT_ID = "101384210";// APP_ID // TODO APP_ID需要修改
	private static final String CLIENT_SECRET = "209458c5aec0bfa9fefa9c848ae270ed";// APP_Key // TODO APP_Key需要修改

	/** 回调地址: 需要和该项目的第三方登录请求地址相同 */
	private static final String REDIRECT_URL = "http://ydgcj.tunnel.2bdata.com/user/userThirdLogin";// TODO 回调地址需要修改

	/** 获取code地址(十分钟有效期) */
	private static final String CODE_URL = "https://graph.qq.com/oauth2.0/authorize?response_type=code&client_id=";
	/** 获取Access Token地址 */
	private static final String ACCESS_TOKEN_URL = "https://graph.qq.com/oauth2.0/token?grant_type=authorization_code&client_id=";
	/** 获取OpenID地址 */
	private static final String OPENID_URL = "https://graph.qq.com/oauth2.0/me?";

	/** 获取用户基本信息 */
	private static final String USER_INFO_URL = "https://graph.qq.com/user/get_user_info?";

	@SuppressWarnings("all")
	@RequestMapping(value = "userThirdLogin")
	public String jump(String code, String state, HttpServletRequest request) {
		if (null == code) {
			// 验证状态码(防止非法盗链,可以随机生成)
			state = UUIDUtil.generate();
			request.getSession().setMaxInactiveInterval(5 * 60);// 五分钟有效
			request.getSession().setAttribute("qq_state", state);
			return "redirect:" + CODE_URL + CLIENT_ID + "&redirect_uri=" + REDIRECT_URL + "&state=" + state;
		} else {
			if (!state.equals(request.getSession().getAttribute("qq_state"))) {
				return "redirect:http://ydgcj.tunnel.2bdata.com";// TODO 这里的跳转需要修改
			}

			// 根据code码获取token
			String tokenUrl = ACCESS_TOKEN_URL + CLIENT_ID + "&client_secret=" + CLIENT_SECRET + "&code=" + code + "&redirect_uri=" + REDIRECT_URL;
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
						String[] strs = StringUtils.split(content, "&");
						String accessToken = strs[0];

						// 获取openID
						String openIDUrl = OPENID_URL + accessToken;
						HttpGet openIDHttpGet = new HttpGet(openIDUrl);
						CloseableHttpResponse openIDResponse = httpclient.execute(openIDHttpGet);
						if (openIDResponse.getStatusLine().getStatusCode() == 200) {
							String openIDContent = EntityUtils.toString(openIDResponse.getEntity(), "UTF-8");
							System.out.println("内容: " + openIDContent);
							// callback( {"client_id":"101384210","openid":"3E824F7246A702B048398FC71E5B4BAC"} );
							int startIndex = openIDContent.indexOf("{");
							int endIndex = openIDContent.indexOf("}");
							String contentStr = openIDContent.substring(startIndex, endIndex + 1);
							Map<String, String> fromJson = GsonUtil.fromJson(contentStr, Map.class);
							// String clientId = fromJson.get("client_id");
							String openid = fromJson.get("openid");

							// 获取用户基本信息
							String userInfoUrl = USER_INFO_URL + accessToken + "&oauth_consumer_key=" + CLIENT_ID + "&openid=" + openid;
							HttpGet userInfoHttpGet = new HttpGet(userInfoUrl);
							CloseableHttpResponse userInfoResponse = httpclient.execute(userInfoHttpGet);
							if (userInfoResponse.getStatusLine().getStatusCode() == 200) {
								String userInfoContent = EntityUtils.toString(userInfoResponse.getEntity(), "UTF-8");
								System.out.println("内容: " + userInfoContent);
								Map<String, String> userInfo = GsonUtil.fromJson(userInfoContent, Map.class);
								String nickname = userInfo.get("nickname");
								String figureurl = userInfo.get("figureurl_qq_1");
								String gender = userInfo.get("gender");
								System.out.println("昵称: " + nickname + "\n性别: " + gender + "\n头像地址: " + figureurl);
							}
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
}
