package com.yd.dby.d.seller.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.d.seller.mapper.YdSellerMapperCoupon;
import com.yd.dby.d.seller.service.YdSellerServiceCoupon;

/**
 * @author 作者 E-mail:
 * @version 创建时间：2017年2月14日 上午11:36:51
 * 
 */
@Service("_YdSellerServiceCoupon")
public class YdSellerServiceImplCoupon implements YdSellerServiceCoupon {

	@Autowired
	private YdSellerMapperCoupon ydSellerMapperCoupon;

	@Autowired
	private HttpSession session;

	// 查询优惠券列表
	@Override
	public Map<String, Object> index(HashMap<String, Object> request) {
		
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		request.put("to", (Integer.valueOf(request.get("p").toString()) - 1) * 10);
		request.put("perPage", 10);
		request.put("store_id", session.getAttribute("store_id") );
		Integer total = ydSellerMapperCoupon.totalCoupon(request);

		map.put("data", ydSellerMapperCoupon.index(request));
		
		map.put("p", request.get("p"));
		if (total % 10 == 0) {
			map.put("totalPage", Math.ceil(total / 10));
		} else {
			map.put("totalPage", Math.ceil(total / 10) + 1);
		}

		return map;
		
		
		/*Integer perPage = 40;

		HashMap<String, Object> map = new HashMap<String, Object>();

		request.put("to", (Integer.valueOf(request.get("p").toString()) - 1) * perPage);
		request.put("perPage", perPage);
		request.put("store_id", session.getAttribute("store_id"));
		map.put("data", ydSellerMapperCoupon.index(request));
		map.put("request", request);
		Integer total = ydSellerMapperCoupon.totalCoupon(request);

		request.put("p", request.get("p"));

		if (total % perPage == 0) {
			map.put("totalPage", Math.ceil(total / perPage));
		} else {
			map.put("totalPage", Math.ceil(total / perPage) + 1);
		}
		return map;*/
	}

	// 确认添加优惠券
	@Override
	public Map<String, Object> store(HashMap<String, Object> request) {

		if (request.get("coupon_full_money") == "" || request.get("coupon_full_money") == null) {
			request.put("coupon_full_money", 0);
		}
		request.put("store_id", session.getAttribute("store_id"));
		request.put("data", ydSellerMapperCoupon.store(request));
		request.put("p", 1);
		
		return index(request);
	}

	/**
	 * 先查询到优惠券的信息
	 */
	@Override
	public Map<String, Object> edit(HashMap<String, Object> request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		request.put("store_id", session.getAttribute("store_id") );
		map.put("data", ydSellerMapperCoupon.edit(request));
		return map;
	}

	// 修改优惠券
	@Override
	public Map<String, Object> update(HashMap<String, Object> request) {
		if (Integer.valueOf((String) request.get("coupon_store_type"))==2) {
			request.put("coupon_full_money",0);
		}
		request.put("store_id", session.getAttribute("store_id"));
		request.put("data", ydSellerMapperCoupon.update(request));
		request.put("p", 1);
		return index(request);
	}

	// 删除优惠券
	@Override
	public Map<String, Object> destroy(HashMap<String, Object> request) {
		request.put("store_id", session.getAttribute("store_id"));
		request.put("delete", ydSellerMapperCoupon.destroy(request));
		request.put("p", 1);
		return index(request);
	}

}
