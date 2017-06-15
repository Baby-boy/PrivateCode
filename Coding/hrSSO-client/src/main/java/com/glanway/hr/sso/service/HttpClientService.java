package com.glanway.hr.sso.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glanway.hr.sso.common.JsonResult;

@Service
public class HttpClientService implements BeanFactoryAware {

	@Autowired(required = false)
	private RequestConfig requestConfig;

	private BeanFactory beanFactory;

	private CloseableHttpClient getHttpclient() {
		return this.beanFactory.getBean(CloseableHttpClient.class);
	}

	/**
	 * 发起GET请求
	 * 
	 * @param url
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String doGet(String url) throws ClientProtocolException, IOException {
		// 创建http GET请求
		HttpGet httpGet = new HttpGet(url);
		httpGet.setConfig(this.requestConfig);
		CloseableHttpResponse response = null;
		try {
			// 执行请求
			response = this.getHttpclient().execute(httpGet);
			// 判断返回状态是否为200
			if (response.getStatusLine().getStatusCode() == 200) {
				return EntityUtils.toString(response.getEntity(), "UTF-8");
			}
		} finally {
			if (response != null) {
				response.close();
			}
		}
		return null;
	}

	/**
	 * 带有参数的GET请求
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public String doGet(String url, Map<String, String> params)
			throws ClientProtocolException, IOException, URISyntaxException {
		// 定义请求的参数
		URIBuilder builder = new URIBuilder(url);
		for (Map.Entry<String, String> entry : params.entrySet()) {
			builder.setParameter(entry.getKey(), entry.getValue());
		}
		return this.doGet(builder.build().toString());
	}

	/**
	 * 发起POST请求
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public JsonResult doPost(String url, Map<String, String> params) throws ParseException, IOException {
		JsonResult jsonResult = new JsonResult();

		// 创建http POST请求
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(this.requestConfig);

		if (null != params) {
			List<NameValuePair> parameters = new ArrayList<NameValuePair>(0);
			for (Map.Entry<String, String> entry : params.entrySet()) {
				parameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
			// 构造一个form表单式的实体
			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters);
			// 将请求实体设置到httpPost对象中
			httpPost.setEntity(formEntity);
		}

		CloseableHttpResponse response = null;
		try {
			// 执行请求
			response = this.getHttpclient().execute(httpPost);
			HttpEntity entity = response.getEntity();

			if (null != entity) {
				jsonResult.setData(EntityUtils.toString(entity, "UTF-8"));
			}
			return jsonResult;
		} finally {
			if (response != null) {
				response.close();
			}
		}
	}

	/**
	 * 发起POST请求,提交json数据
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public JsonResult doPostJosn(String url, String json) throws ParseException, IOException {
		JsonResult jsonResult = new JsonResult();

		// 创建http POST请求
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(this.requestConfig);

		if (null != json) {
			// 构造一个json的实体
			StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
			// 将请求实体设置到httpPost对象中
			httpPost.setEntity(stringEntity);
		}

		CloseableHttpResponse response = null;
		try {
			// 执行请求
			response = this.getHttpclient().execute(httpPost);
			HttpEntity entity = response.getEntity();

			if (null != entity) {
				jsonResult.setData(EntityUtils.toString(entity, "UTF-8"));
			}
			return jsonResult;
		} finally {
			if (response != null) {
				response.close();
			}
		}
	}

	/**
	 * 无参数的POST请求
	 * 
	 * @param url
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public JsonResult doPost(String url) throws ParseException, IOException {
		return this.doPost(url, null);
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}
}
