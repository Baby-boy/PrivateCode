package com.yd.dby.d.seller.mapper;

import java.util.HashMap;
import java.util.List;

/**
 * @author 作者 E-mail:
 * @version 创建时间：2017年2月14日 上午11:14:45
 * 
 */
public interface YdSellerMapperCoupon {
	
	/**
	 * @param request
	 * @return
	 * 方法作用(确认添加优惠券)
	 */
	Integer store(HashMap<String, Object> request);

	// 修改优惠券
	Integer update(HashMap<String, Object> request);

	// 删除优惠券
	Integer destroy(HashMap<String, Object> request);

	// 查询优惠券列表
	List<HashMap<String,Object>> index(HashMap<String, Object> request);

	/**
	 * @param request
	 * @return
	 * 方法作用(查询当前店铺的优惠券总数)
	 */
	Integer totalCoupon(HashMap<String, Object> request);

	/**
	 * @param request
	 * @return
	 * 方法作用(修改优惠券之前先查到优惠券的信息)
	 */
	HashMap<String, Object> edit(HashMap<String, Object> request);

	

}
