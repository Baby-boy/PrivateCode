package com.yd.dby.a.sys.service.impl;

import org.springframework.stereotype.Service;

@Service("_url")
public class YdSysUrl {

	private static final String urlPublic = "https://woadby.oss-cn-beijing.aliyuncs.com/dby/";
	
	private static final String goods = urlPublic;
	
	private static final String other = urlPublic;
	
	private static final String images = urlPublic+"images/";
	
	public String goods() {
		return goods;
	}
	
	public String other() {
		return other;
	}
	
	public String images() {
		return images;
	}
}