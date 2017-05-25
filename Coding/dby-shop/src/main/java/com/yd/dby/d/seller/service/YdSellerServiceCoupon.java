package com.yd.dby.d.seller.service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 作者 E-mail:
 * @version 创建时间：2017年2月14日 上午11:36:23
 * 
 */
public interface YdSellerServiceCoupon {

	/**
	 * @param request
	 * @return
	 * 方法作用(查询优惠券列表)
	 */
	Map<String, Object> index(HashMap<String, Object> request);

	/**
	 * @param request
	 * @return
	 * 方法作用(添加优惠券)
	 */
	Map<String, Object> store(HashMap<String, Object> request);
	
	/**
	 * @param coupon_id
	 * @return
	 * 方法作用(修改优惠券之前先查到当前优惠券的信息)
	 */
	
	Map<String,Object> edit(HashMap<String, Object> request);
	
	/**
	 * @param request
	 * @return
	 * 方法作用(确认修改优惠券)
	 */
	Map<String, Object> update(HashMap<String, Object> request);

	/**
	 * @param request
	 * @return
	 * 方法作用(删除优惠券)
	 */
	Map<String, Object> destroy(HashMap<String, Object> request);

}
