package com.yd.dby.a.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service("_redis")
public class YdSysRedis {
	
	@Autowired
	private StringRedisTemplate redis;
	
	public Object get(String key) {
		return redis.opsForValue().get(key);
	}
}