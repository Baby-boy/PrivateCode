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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glanway.hr.sso.entity.user.User;
import com.glanway.hr.sso.utils.Base64;
import com.glanway.hr.sso.utils.CookieUtil;

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

	/** 票据token */
	private static String TOKEN = "";

	/** cookie的名称 */
	private static final String COOKIE_NAME = "HR_TOKEN";

	/** SSO系统登录地址 */
	private final String LOGIN_URL = URL + "/login/";

	/** 检查token是否有效接口地址 */
	private final String REQUEST_URL = URL + "/api/user/";

	@Value("${httpclient.sso.request.url}")
	private void setURL(String url) {
		URL = url;
	}

	private static final ObjectMapper MAPPER = new ObjectMapper();

	/** 用户线程池(可以通过此线程池获取用户信息) */
	public static final ThreadLocal<User> THREAD_LOCAL = new ThreadLocal<User>();

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String url = request.getRequestURL().toString();
		TOKEN = request.getParameter("token");

		if (StringUtils.isNotEmpty(TOKEN)) {
			byte[] decode = Base64.decode(TOKEN.getBytes());// 对登录完成后的token进行解码
			TOKEN = new String(decode);
			CookieUtil.setCookie(response, COOKIE_NAME, TOKEN);

			response.sendRedirect(url);
			return false;
		} else {
			TOKEN = CookieUtil.getCookieValue(request, COOKIE_NAME);
			if (StringUtils.isEmpty(TOKEN)) {
				String encodeUrl = Base64.encodeToString(url.getBytes());// 对请求地址进行编码
				response.sendRedirect(LOGIN_URL + encodeUrl);
				return false;
			}
		}

		queryUserByToken(response, url, TOKEN);
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
		String requestUrl = REQUEST_URL + token;

		CloseableHttpClient httpClient = HttpClients.createDefault();// 创建Httpclient对象
		HttpGet httpGet = new HttpGet(requestUrl);// 创建Httpclient GET请求
		RequestConfig config = RequestConfig.custom().setConnectTimeout(3000).setConnectionRequestTimeout(500)
				.setSocketTimeout(10 * 1000).build();
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

				// 未登录
				String encodeUrl = Base64.encodeToString(url.getBytes());
				response.sendRedirect(LOGIN_URL + encodeUrl);
				return false;
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

}
