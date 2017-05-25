package com.lingang.platform.controller;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

public class BaseController {

	@Resource
	protected RedisTemplate<String, Object> redisTemplate;

	/**
	 * @Description: (redis存储)
	 * @param key
	 *            存储的key
	 * @param val
	 *            存储的value
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2017年1月5日 下午5:11:19
	 */
	protected void redisSet(String key, Object val) throws Exception {
		ValueOperations<String, Object> valueops = redisTemplate.opsForValue();
		valueops.set(key, val);
	}

	/**
	 * @Description: (redis 根据key获取value)
	 * @param key
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2017年1月5日 下午5:11:55
	 */
	protected Object redisGet(String key) throws Exception {
		ValueOperations<String, Object> valueops = redisTemplate.opsForValue();
		return valueops.get(key);
	}

}
