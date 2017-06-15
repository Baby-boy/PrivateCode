package com.glanway.hr.sso.interceptors;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glanway.hr.sso.entity.user.User;
import com.glanway.hr.sso.utils.CookieUtil;

public class UserHandlerInterceptor implements HandlerInterceptor {

	private static final String COOKIE_NAME = "HR_TOKEN";

	private static final String LOGIN_URL = "http://localhost:8088/route/login";

	private static final String REQUEST_URL = "http://10.1.1.240:8088/api/user/";

	private static final ObjectMapper MAPPER = new ObjectMapper();

	private static final ThreadLocal<User> THREAD_LOCAL = new ThreadLocal<User>();

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 获取请求路径
		String requestURI = request.getRequestURI();

		String token = CookieUtil.getCookieValue(request, COOKIE_NAME);
		if (StringUtils.isEmpty(token)) {
			response.addHeader("requestURI", requestURI);
			// 未登录
			response.sendRedirect(LOGIN_URL);
			return false;
		}

		queryUserByToken(response, token);

		return true;
	}

	private Boolean queryUserByToken(HttpServletResponse response, String token) throws IOException {

		String url = REQUEST_URL + token;

		// 创建Httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();

		// 创建Httpclient GET请求
		HttpGet httpGet = new HttpGet(url);

		RequestConfig config = RequestConfig.custom().setConnectTimeout(1000).setConnectionRequestTimeout(500)
				.setSocketTimeout(10 * 1000).build();

		httpGet.setConfig(config);
		CloseableHttpResponse res = null;
		try {
			// 执行请求
			res = httpClient.execute(httpGet);
			if (res.getStatusLine().getStatusCode() == 200) {
				String content = EntityUtils.toString(res.getEntity(), "UTF-8");
				int begin = content.indexOf("data");
				int end = content.indexOf("dataMap");
				String substring = StringUtils.substring(content, begin + 6, end - 2);
				User readValue = MAPPER.readValue(substring, User.class);
				if (null == readValue) {
					// 登录超时
					response.sendRedirect(LOGIN_URL);
					return false;
				}
				// 处于登录状态
				THREAD_LOCAL.set(readValue);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != res) {
				res.close();
			}
		}

		return true;
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

}
