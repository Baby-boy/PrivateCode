package com.glanway.hr.app.utils;

import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpClientUtil {

	public static void doGet(String url) throws Exception {
		// 创建Httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();

		// 创建Httpclient GET请求
		HttpGet httpGet = new HttpGet(url);

		RequestConfig config = RequestConfig.custom().setConnectTimeout(1000).setConnectionRequestTimeout(500)
				.setSocketTimeout(10 * 1000).build();

		httpGet.setConfig(config);
		CloseableHttpResponse response = null;
		try {
			// 执行请求
			response = httpClient.execute(httpGet);
			if (response.getStatusLine().getStatusCode() == 200) {
				// String content = EntityUtils.toString(response.getEntity(),
				// "UTF-8");
				// System.out.print(content);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != response) {
				response.close();
			}
		}
	}

	public static void doPost(List<? extends NameValuePair> list, String url) throws Exception {
		// 创建Httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();

		// 创建Httpclient GET请求
		HttpPost httpPost = new HttpPost(url);

		RequestConfig config = RequestConfig.custom().setConnectTimeout(1000).setConnectionRequestTimeout(500)
				.setSocketTimeout(10 * 1000).build();
		httpPost.setConfig(config);

		UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(list);
		httpPost.setEntity(formEntity);

		CloseableHttpResponse response = null;
		try {
			// 执行请求
			response = httpClient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == 200) {
				// String content = EntityUtils.toString(response.getEntity(),
				// "UTF-8");
				// System.out.print(content);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != response) {
				response.close();
			}
		}
	}
}
