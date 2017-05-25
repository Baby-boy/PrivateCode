package com.crawler.crawler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {

	// public static final String URL_NAME = "qingchun";
	// public static final String URL_NAME = "xiaohua";
	// public static final String URL_NAME = "chemo";
	// public static final String URL_NAME = "qipao";
	// public static final String URL_NAME = "mingxing";
	public static final String URL_NAME = "xinggan";
	public static final String URL_STRING = "http://www.mm131.com/" + URL_NAME + "/{page}";
	public static final String LOCALURL = "F:/Love/upload/" + URL_NAME;
	/** 开始码 **/
	public static int begin = 0;
	/** 结束码 **/
	public static int end = 0;
	public static int kzNum = 0;

	public void start() throws Exception {
		/** 访问首页,查到该类型的总页数 **/
		// 请求 --
		String context = doGet("http://www.mm131.com/" + URL_NAME);

		Document parse = Jsoup.parse(context);
		Elements pageA = parse.select(".main dl[class='list-left public-box'] dd[class='page'] a");
		String pageStr = pageA.get(pageA.size() - 1).attr("href");
		String[] split = pageStr.split("\\D+");
		System.out.println(split[2]);
		int page = Integer.parseInt(split[2]);// 当前总页数
		for (int i = 1; i <= page; i++) {
			/** 拼接该类型的下一页地址 **/
			String pageUrl = StringUtils.replace(URL_STRING, "{page}", "list_1_" + i + ".html");
			if ("qingchun".equals(URL_NAME)) {
				pageUrl = StringUtils.replace(URL_STRING, "{page}", "list_1_" + i + ".html");
			}
			if ("xiaohua".equals(URL_NAME)) {
				pageUrl = StringUtils.replace(URL_STRING, "{page}", "list_2_" + i + ".html");
			}
			if ("chemo".equals(URL_NAME)) {
				pageUrl = StringUtils.replace(URL_STRING, "{page}", "list_3_" + i + ".html");
			}
			if ("qipao".equals(URL_NAME)) {
				pageUrl = StringUtils.replace(URL_STRING, "{page}", "list_4_" + i + ".html");
			}
			if ("mingxing".equals(URL_NAME)) {
				pageUrl = StringUtils.replace(URL_STRING, "{page}", "list_5_" + i + ".html");
			}
			if ("xinggan".equals(URL_NAME)) {
				pageUrl = StringUtils.replace(URL_STRING, "{page}", "list_6_" + i + ".html");
			}
			if (i == 1) {
				pageUrl = "http://www.mm131.com/" + URL_NAME;
			}
			// 请求 --
			String pageContent = doGet(pageUrl);
			// 控制 ++
			if (StringUtils.isBlank(pageContent)) {
				continue;
			}

			Document parse2 = Jsoup.parse(pageContent);
			Elements dds = parse2.select(".main dl[class='list-left public-box'] dd");
			for (int d = 0; d < dds.size() - 1; d++) {
				Element dd = dds.get(d);
				/** 获取该类型的每一类图片的总地址 **/
				String urlString = dd.child(0).attr("href");
				// System.out.println(urlString);
				int indexOf = urlString.lastIndexOf("/");
				String beginUrl = urlString.substring(0, indexOf);

				/** 这里放开可下载全部 -- 哈哈,你懂得 **/
				String number = urlString.substring(indexOf, urlString.length());
				String[] numStrs = number.split("\\D+");
				int num = Integer.parseInt(numStrs[1]);

				// 这里做一个控制,控制起始位置和结束位置
				if (end != 0 && kzNum == end) {
					return;
				}
				for (int index = begin; index <= end; index++) {
					kzNum = index;
					if (end == 0) {
						index = num;
					}
					urlString = beginUrl + "/" + index + ".html";
					System.out.println(urlString);
					/** 获取该类型的每一类图片的总页数 **/
					// 请求 --
					String imgeStr = doGet(urlString);
					// 控制 ++
					if (StringUtils.isBlank(imgeStr)) {
						continue;
					}
					Document parse3 = Jsoup.parse(imgeStr);
					String span = parse3.select(".content div[class='content-page'] span").text();
					String[] split2 = span.split("\\D+");
					for (int j = 1; j <= Integer.parseInt(split2[1]); j++) {
						/** 获取该类型的每一类图片的每一张访问地址 **/
						String imgUrl = beginUrl + "/" + index + "_" + j + ".html";

						if (j == 1) {
							imgUrl = beginUrl + "/" + index + ".html";
						}
						// 请求 --
						String imgContent = doGet(imgUrl);
						// 控制 ++
						if (StringUtils.isBlank(imgContent)) {
							continue;
						}
						/** 获取每一张图片的具体地址 **/
						Document parse4 = Jsoup.parse(imgContent);
						Elements imgElements = parse4.select(".content div[class='content-pic'] a img");
						String img = imgElements.attr("src");
						System.out.println(img);

						/** URL请求图片并读写图片 **/
						URL url = new URL(img);
						HttpURLConnection httpURL = (HttpURLConnection) url.openConnection();
						InputStream in = null;
						try {
							in = httpURL.getInputStream();
						} catch (Exception e) {
							continue;
						}

						File file = new File(LOCALURL);
						if (!file.exists()) {
							file.mkdirs();
						}
						file = new File(LOCALURL + "/" + index + "_" + j + ".jpg");
						OutputStream out = new FileOutputStream(file);
						int len = 0;
						byte[] buf = new byte[1024 * 1024];
						while ((len = in.read(buf)) != -1) {
							out.write(buf, 0, len);
						}
						out.close();
						in.close();
					}
				}
			}
		}
	}

	public String doPost(String url) throws Exception {

		// 创建Httpclient对象
		CloseableHttpClient httpclient = HttpClients.createDefault();

		// 创建http POST请求
		HttpPost httpPost = new HttpPost(url);

		CloseableHttpResponse response = null;
		try {
			// 执行请求
			response = httpclient.execute(httpPost);
			// 判断返回状态是否为200
			if (response.getStatusLine().getStatusCode() == 200) {
				String content = EntityUtils.toString(response.getEntity(), "UTF-8");
				return content;
			}
		} finally {
			if (response != null) {
				response.close();
			}
			httpclient.close();
		}

		return null;
	}

	public String doGet(String url) throws Exception {

		// 创建Httpclient对象
		CloseableHttpClient httpclient = HttpClients.createDefault();

		// 创建http GET请求
		HttpGet httpGet = new HttpGet(url);

		CloseableHttpResponse response = null;
		try {
			// 执行请求
			response = httpclient.execute(httpGet);
			// 判断返回状态是否为200
			if (response.getStatusLine().getStatusCode() == 200) {
				String content = EntityUtils.toString(response.getEntity(), "UTF-8");
				return content;
			}
		} finally {
			if (response != null) {
				response.close();
			}
			httpclient.close();
		}
		return null;
	}
}
