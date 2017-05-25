package com.yd.gcj.service;

public interface Function<T, E> {

	/**
	 * 说明: 通用型回调函数
	 * 
	 * @param e(参数)
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年3月1日 上午11:47:36
	 */
	public T callback(E e);
}
