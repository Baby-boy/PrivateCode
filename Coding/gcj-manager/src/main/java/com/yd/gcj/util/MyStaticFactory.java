package com.yd.gcj.util;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;

public class MyStaticFactory {

	public static boolean queryGuzhuTask = false;

	// 头像文件夹
	public static String headimg = "/headimg/";
	// 身份证图片存放的地方
	public static String verifiedImg = "/verifiedImg/";

	public static String systemPath;

	// 用于检测是否自动登录的检测次数
	public static int num = 0;

	static {
		Properties properties = new Properties();

		try {
			ClassPathResource resource = new ClassPathResource("application.properties");
			properties.load(resource.getInputStream());
		} catch (IOException e) {
			throw new RuntimeException("Failed to load application.properties!");
		}

		if (properties.containsKey("myStaticFactory.systemPath")) {
			systemPath = properties.getProperty("myStaticFactory.systemPath");
		}
	}

}
