package com.tadpole.util;

/**
 * 回调接口.
 *
 * @author FUQIHAO
 * @version 1.0.0
 * @dateTime 2017年5月25日 下午4:34:20
 */
public interface Function<T, E> {

	/**
	 * 回调方法.
	 *
	 * @param e(参数)
	 * @return T
	 * @author FUQIHAO
	 * @dateTime 2017年5月25日 下午4:34:33
	 */
	public T callback(E e);
}
