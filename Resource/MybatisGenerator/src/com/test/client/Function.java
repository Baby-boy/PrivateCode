package com.test.client;

/**
 * 说明: 回调接口
 * 
 * @Author: fqh(fqh@zwzyd.com)
 * @date:2017年2月24日 下午2:15:53
 * @Version:1.0
 */
public interface Function<T, E> {

	public T callback(E e);
}

class Fun {

	public static String set(Function<String, Object> fun) {
		return fun.callback("1");
	}
}