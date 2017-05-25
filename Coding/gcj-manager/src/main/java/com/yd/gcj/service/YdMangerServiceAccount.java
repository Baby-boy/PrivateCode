package com.yd.gcj.service;

public interface YdMangerServiceAccount {
	
	/***
	 * 支付宝签名
	 * @param userId
	 * @param price
	 * @return
	 */
	Object $zfbSign(Integer userId,float price);
	
	/***
	 * 支付宝通知
	 * @param userId
	 * @param price
	 * @param payType
	 * @return
	 */
	Object $zfbNotive(Integer userId,float price,Integer payType);
	
	/***
	 * 微信签名
	 * @param userId
	 * @param price
	 * @return
	 */
	Object $wxSign(Integer userId,float price);
	
	/***
	 * 微信通知
	 * @param userId
	 * @param price
	 * @param payType
	 * @return
	 */
	Object $wxNotive(Integer userId,float price,Integer payType);
	
}
