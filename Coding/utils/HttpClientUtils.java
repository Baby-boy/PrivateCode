package com.glanway.util;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.NameValuePair;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

/**
 * HttpClient连接工具类.
 *
 * @author fuqihao
 * @version 1.0
 * @date 2018年1月1日
 */
public abstract class HttpClientUtils {

	/** http */
	private static final String HTTP = "http";
	/** https */
	private static final String HTTPS = "https";
	/** 默认编码 */
	public static final String DEFAULT_CODE = "UTF-8";
	/** cookie */
	public static final String COOKIE = "cookie";

	/** 默认超时毫秒数 */
	private static final int DEFAULT_CONNECT_TIMEOUT = 5000;
	/** 默认传输毫秒数 */
	private static final int DEFAULT_SOCKET_TIMEOUT = 10000;
	/** 默认请求超时毫秒数 */
	private static final int DEFAULT_REQUESTCONNECT_TIMEOUT = 3000;
	/** 默认最大连接数 */
	private static final int DEFAULT_CONNECT_TOTAL = 200;
	/** 默认每个路由的基础连接数 */
	private static final int DEFAULT_CONNECT_ROUTE = 20;
	/** 默认重用连接时间 */
	private static final int DEFAULT_VALIDATE_TIME = 30000;

	/** 默认通信失败内容 */
	private static final String DEFAULT_CONTENT = "通信失败";

	private static PoolingHttpClientConnectionManager manager = null;
	private static CloseableHttpClient client = null;

	/** 初始化配置 */
	static {
		ConnectionSocketFactory csf = PlainConnectionSocketFactory.getSocketFactory();
		LayeredConnectionSocketFactory lsf = createSSLConnSocketFactory();
		Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory> create()
				.register(HTTP, csf).register(HTTPS, lsf).build();
		manager = new PoolingHttpClientConnectionManager(registry);
		manager.setMaxTotal(DEFAULT_CONNECT_TOTAL);
		manager.setDefaultMaxPerRoute(DEFAULT_CONNECT_ROUTE);
		manager.setValidateAfterInactivity(DEFAULT_VALIDATE_TIME);
		SocketConfig config = SocketConfig.custom().setSoTimeout(DEFAULT_SOCKET_TIMEOUT).build();
		manager.setDefaultSocketConfig(config);
		RequestConfig requestConf = RequestConfig.custom().setConnectTimeout(DEFAULT_CONNECT_TIMEOUT)
				.setConnectionRequestTimeout(DEFAULT_REQUESTCONNECT_TIMEOUT).setSocketTimeout(DEFAULT_SOCKET_TIMEOUT)
				.build();

		client = HttpClients.custom().setConnectionManager(manager).setDefaultRequestConfig(requestConf)
				.setRetryHandler(new HttpRequestRetryHandler() {
					@Override
					public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
						if (executionCount >= 3) {
							return false;
						}
						if (exception instanceof NoHttpResponseException) {// 如果服务器断掉了连接那么重试
							return true;
						}
						if (exception instanceof SSLHandshakeException) {// 不重试握手异常
							return false;
						}
						if (exception instanceof InterruptedIOException) {// IO传输中断重试
							return true;
						}
						if (exception instanceof UnknownHostException) {// 未知服务器
							return false;
						}
						if (exception instanceof ConnectTimeoutException) {// 超时就重试
							return true;
						}
						if (exception instanceof SSLException) {
							return false;
						}

						HttpClientContext cliContext = HttpClientContext.adapt(context);
						HttpRequest request = cliContext.getRequest();
						if (!(request instanceof HttpEntityEnclosingRequest)) {
							return true;
						}
						return false;
					}
				}).build();
	}

	/** 协议工厂 */
	private static SSLConnectionSocketFactory createSSLConnSocketFactory() {
		SSLConnectionSocketFactory sslsf = null;
		SSLContext context;
		try {
			context = SSLContext.getInstance(SSLConnectionSocketFactory.TLS);
			context.init(null, null, null);
			sslsf = new SSLConnectionSocketFactory(context, NoopHostnameVerifier.INSTANCE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sslsf;
	}

	/** 公共执行方法 */
	private static String response(final HttpRequestBase method) {
		HttpClientContext context = HttpClientContext.create();
		CloseableHttpResponse response = null;
		String content = DEFAULT_CONTENT;
		try {
			response = client.execute(method, context);// 执行GET/POST请求
			if (200 == response.getStatusLine().getStatusCode()) {
				HttpEntity entity = response.getEntity();// 获取响应实体
				if (null != entity) {
					Charset charset = ContentType.getOrDefault(entity).getCharset();
					content = EntityUtils.toString(entity, charset);
					EntityUtils.consume(entity);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (method != null) {
				method.releaseConnection();
			}
		}
		return content;
	}

	/** 真实请求头信息 */
	private static HttpGet realHeader(final String url) {
		HttpGet httpGet = new HttpGet(url);
		httpGet.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;");
		httpGet.addHeader("Accept-Language", "zh-cn");
		httpGet.addHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.146 Safari/537.36");
		httpGet.addHeader("Keep-Alive", "300");
		httpGet.addHeader("Connection", "Keep-Alive");
		httpGet.addHeader("Cache-Control", "no-cache");
		return httpGet;
	}

	/**
	 * 执行GET请求.
	 *
	 * @param url(请求路径)
	 * @return Json字符串
	 * @throws Exception
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static String doGet(final String url) throws Exception {
		HttpGet httpGet = new HttpGet(url);
		return response(httpGet);
	}

	/**
	 * 执行GET请求.
	 *
	 * @param url(请求路径)
	 * @param cookie(cookie值)
	 * @return Json字符串
	 * @throws Exception
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static String doGet(final String url, final String cookie) throws Exception {
		HttpGet httpGet = new HttpGet(url);
		if (StringUtils.isNotBlank(cookie)) {
			httpGet.addHeader(COOKIE, cookie);
		}
		return response(httpGet);
	}

	/**
	 * 执行GET请求.
	 *
	 * @param url(请求路径)
	 * @return 字节数组
	 * @throws Exception
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static byte[] doGetAsByte(final String url) throws Exception {
		HttpGet httpGet = new HttpGet(url);
		return response(httpGet).getBytes();
	}

	/**
	 * 执行GET请求.
	 *
	 * @param url(请求路径)
	 * @param cookie(cookie值)
	 * @return 字节数组
	 * @throws Exception
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static byte[] doGetAsByte(final String url, final String cookie) throws Exception {
		HttpGet httpGet = new HttpGet(url);
		if (StringUtils.isNotBlank(cookie)) {
			httpGet.addHeader(COOKIE, cookie);
		}
		return response(httpGet).getBytes();
	}

	/**
	 * 根据名称获取请求头信息.
	 *
	 * @param url(请求路径)
	 * @param headerName(请求头key)
	 * @return 字符串
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public String getHeaders(final String url, final String headerName) {
		HttpGet get = new HttpGet(url);
		response(get);
		Header[] headers = get.getHeaders(headerName);
		return headers == null ? null : headers.toString();
	}

	/**
	 * 根据名称获取请求头信息.
	 *
	 * @param url(请求路径)
	 * @param cookie(cookie值)
	 * @param headerName(请求头key)
	 * @return 字符串
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public String getHeaders(final String url, final String cookie, final String headerName) {
		HttpGet get = new HttpGet(url);
		if (StringUtils.isNotBlank(cookie)) {
			get.addHeader(COOKIE, cookie);
		}
		response(get);
		Header[] headers = get.getHeaders(headerName);
		return headers == null ? null : headers.toString();
	}

	/**
	 * 模拟真实浏览器请求.
	 *
	 * @param url(请求路径)
	 * @return 字符串
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static String getReal(final String url) {
		HttpGet httpGet = realHeader(url);
		return response(httpGet);
	}

	/**
	 * 模拟真实浏览器请求.
	 *
	 * @param url(请求路径)
	 * @param cookie(cookie值)
	 * @return 字符串
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static String getReal(final String url, final String cookie) {
		HttpGet httpGet = realHeader(url);
		if (StringUtils.isNotBlank(cookie)) {
			httpGet.addHeader(COOKIE, cookie);
		}
		return response(httpGet);
	}

	/**
	 * 执行POST请求.
	 *
	 * @param url(请求路径)
	 * @param jsonData(json字符串)
	 * @return 字符串
	 * @throws Exception
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static String doPost(final String url, final String jsonData) throws Exception {
		final HttpPost httpPost = new HttpPost(url);
		if (StringUtils.isNotBlank(jsonData)) {
			httpPost.addHeader("Content-Type", "application/json");
		}
		httpPost.setEntity(new StringEntity(jsonData, ContentType.APPLICATION_JSON));
		return response(httpPost);
	}

	/**
	 * 执行POST请求.
	 *
	 * @param url(请求路径)
	 * @param cookie(cookie值)
	 * @param jsonData(json字符串)
	 * @return
	 * @throws Exception
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static String doPost(final String url, final String cookie, final String jsonData) throws Exception {
		final HttpPost httpPost = new HttpPost(url);
		if (StringUtils.isNotBlank(jsonData)) {
			httpPost.addHeader("Content-Type", "application/json");
		}
		if (StringUtils.isNotEmpty(cookie)) {
			httpPost.addHeader(COOKIE, cookie);
		}
		httpPost.setEntity(new StringEntity(jsonData, ContentType.APPLICATION_JSON));
		return response(httpPost);
	}

	/**
	 * 执行POST请求.
	 *
	 * @param url(请求路径)
	 * @param param(参数)
	 * @return 字符串
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static String doPost(final String url, final List<? extends NameValuePair> dataList) {
		HttpPost httpPost = new HttpPost(url);
		try {
			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(dataList, DEFAULT_CODE);
			httpPost.setEntity(formEntity);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return response(httpPost);
	}

	/**
	 * 执行POST请求.
	 *
	 * @param url(请求路径)
	 * @param cookie(cookie值)
	 * @param param(参数)
	 * @return 字符串
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static String doPost(final String url, final String cookie, final List<? extends NameValuePair> dataList) {
		HttpPost httpPost = new HttpPost(url);
		try {
			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(dataList, DEFAULT_CODE);
			httpPost.setEntity(formEntity);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		if (StringUtils.isNotEmpty(cookie)) {
			httpPost.addHeader(COOKIE, cookie);
		}
		return response(httpPost);
	}

}
