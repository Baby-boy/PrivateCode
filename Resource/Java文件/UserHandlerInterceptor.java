package com.glanway.hr.salary.interceptors;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glanway.hr.salary.entity.user.User;
import com.glanway.hr.salary.utils.Base64;
import com.glanway.hr.salary.utils.CookieUtil;

/**
 * Mapping拦截器.
 *
 * @author FUQIHAO
 * @version 1.0.0
 * @dateTime 2017年6月15日 上午09:00:05
 */
public class UserHandlerInterceptor implements HandlerInterceptor {

	/** SSO项目的路径 */
	private String URL;

	/** 登录地址拼接 */
	private String loginUrl = "/login?redirectUrl=";

	/** token参数 */
	private static final String TOKEN = "token";

	/** cookie的名称 */
	private static final String COOKIE_NAME = "HR_TOKEN";

	/** 用户信息cookie的名称 */
	private static final String USER_COOKIE_NAME = "HR_USER";

	@Value("${httpclient.sso.request.url}")
	private void setURL(String url) {
		URL = url;
	}

	private static final ObjectMapper MAPPER = new ObjectMapper();

	/** 用户线程池变量(可以通过此线程池获取用户信息) */
	public static final ThreadLocal<User> THREAD_LOCAL = new ThreadLocal<User>();

	@Override
	@SuppressWarnings("unchecked")
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String url = request.getRequestURL().toString();

		// 票据token
		String token = "";

		// 对请求参数进行处理,只针对GET请求
		if (request.getMethod().equalsIgnoreCase("GET")) {
			Enumeration<String> names = request.getParameterNames();
			StringBuffer buffer = new StringBuffer();
			buffer.append("?");
			while (names.hasMoreElements()) {
				String element = names.nextElement();
				if (!element.equals(TOKEN)) {
					String parameter = request.getParameter(element);
					if (StringUtils.isNotEmpty(parameter)) {
						buffer.append(element).append("=").append(parameter).append("&");
					}
				}
			}
			String params = buffer.deleteCharAt(buffer.length() - 1).toString();
			url = url + params;
		}

		token = request.getParameter(TOKEN);

		if (StringUtils.isNotEmpty(token)) {
			byte[] decode = Base64.decode(token.getBytes());// 对登录完成后的token进行解码
			String tokenDecode = new String(decode);
			token = tokenDecode.substring(tokenDecode.indexOf("_") + 1);
			String userEncode = tokenDecode.substring(0, tokenDecode.indexOf("_"));
			CookieUtil.setCookie(response, COOKIE_NAME, token, 60 * 60 * 24 * 30);
			CookieUtil.setCookie(response, USER_COOKIE_NAME, userEncode, 60 * 60 * 24 * 30);

			response.sendRedirect(url);
			return false;
		} else {
			token = CookieUtil.getCookieValue(request, COOKIE_NAME);
			if (StringUtils.isEmpty(token)) {
				String encodeUrl = Base64.encodeToString(url.getBytes());// 对请求地址进行编码
				response.sendRedirect(URL + loginUrl + encodeUrl);
				return false;
			}
		}

		return queryUserByToken(response, url, token);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		THREAD_LOCAL.set(null);
	}

	/**
	 * 每次向SSO系统检测token是否有效(刷新有效时间).
	 *
	 * @param response(
	 * @param url(请求地址)
	 * @param token(票据)
	 * @return
	 * @author FUQIHAO
	 * @dateTime 2017年6月15日 上午10:00:05
	 */
	private Boolean queryUserByToken(HttpServletResponse response, String url, String token) throws IOException {
		String requestUrl = URL + "/api/user?token=" + token;

		CloseableHttpClient httpClient = HttpClients.createDefault();// 创建Httpclient对象
		HttpGet httpGet = new HttpGet(requestUrl);// 创建Httpclient GET请求
		RequestConfig config = RequestConfig.custom().setConnectTimeout(5000).setConnectionRequestTimeout(500)
				.setSocketTimeout(20 * 1000).build();
		httpGet.setConfig(config);
		CloseableHttpResponse res = null;
		try {
			// 执行请求
			res = httpClient.execute(httpGet);
			if (res.getStatusLine().getStatusCode() == 200) {
				String content = EntityUtils.toString(res.getEntity(), "UTF-8");
				if (StringUtils.isNotEmpty(content)) {
					User user = MAPPER.readValue(content, User.class);
					if (null != user) {
						// 处于登录状态
						THREAD_LOCAL.set(user);
						return true;
					}
				}

				// 登录过期
				String encodeUrl = Base64.encodeToString(url.getBytes());
				response.sendRedirect(URL + loginUrl + encodeUrl);
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 异常重新登录
			String encodeUrl = Base64.encodeToString(url.getBytes());
			response.sendRedirect(URL + loginUrl + encodeUrl);
			return false;
		} finally {
			httpGet.releaseConnection();
			httpClient.close();
			if (null != res) {
				res.close();
			}
		}

		return true;
	}

}
