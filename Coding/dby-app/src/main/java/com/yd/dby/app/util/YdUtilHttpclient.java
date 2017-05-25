package com.yd.dby.app.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

@SuppressWarnings("deprecation")
public class YdUtilHttpclient {

	public static String post(String Url, List<NameValuePair> parameters, String charset) throws Exception {
		String result = new String();
		HttpPost httppost = new HttpPost(Url);
		httppost.setEntity(new UrlEncodedFormEntity(parameters, charset));
		@SuppressWarnings("resource")
		HttpClient httpclient = new DefaultHttpClient();
		HttpEntity entity = httpclient.execute(httppost).getEntity();
		if (entity != null && entity.getContentLength() > 0) {
			result = EntityUtils.toString(entity);
		}
		httpclient.getConnectionManager().shutdown();
		return result;
	}

	@SuppressWarnings("finally")
	public static String get(String Url) throws Exception {
		BufferedReader in = null;

		String content = null;
		try {
			// 定义HttpClient
			@SuppressWarnings("resource")
			DefaultHttpClient client = new DefaultHttpClient();
			// 实例化HTTP方法
			HttpGet request = new HttpGet();
			request.setURI(new URI(Url));
			HttpResponse response = client.execute(request);

			in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer sb = new StringBuffer("");
			String line = "";
			String NL = System.getProperty("line.separator");
			while ((line = in.readLine()) != null) {
				sb.append(line + NL);
			}
			in.close();
			content = sb.toString();
		} catch (Exception e) {
		} finally {
			if (in != null) {
				try {
					in.close();// 最后要关闭BufferedReader
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return content;
		}
	}
}