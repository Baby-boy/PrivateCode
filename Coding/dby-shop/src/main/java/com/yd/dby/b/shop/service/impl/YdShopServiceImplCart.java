package com.yd.dby.b.shop.service.impl;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.b.shop.entity.YdShopCart;
import com.yd.dby.b.shop.mapper.YdShopMapperCart;
import com.yd.dby.b.shop.mapper.YdShopMapperGoods;
import com.yd.dby.b.shop.service.YdShopServiceCart;
import com.yd.dby.utils.YdUtilResponse;

@Service("_Cart")
public class YdShopServiceImplCart implements YdShopServiceCart {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private YdShopMapperCart ydAppMapperCart;
	
	@Autowired
	private YdShopMapperGoods ydShopMapperGoods;
	
	
	/**
	 * 购物车 - 窗口
	 */
	public Object window() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if ( session.getAttribute("user_id") != null ) {
			Integer user_id = Integer.parseInt( session.getAttribute("user_id").toString() );
			map.put("data", ydAppMapperCart.window( user_id ));
			map.put("total", ydAppMapperCart.total( user_id ));
		} else {
			map.put("data", "");
			map.put("total", "");
		}
		return map;
	}
	
	/**
	 * 列表
	 */
	public Object lists(Integer p) {
		HashMap<String, Object> requestHashMap = new HashMap<String, Object>();
		requestHashMap.put("p", 1);
		requestHashMap.put("user_id", session.getAttribute("user_id"));
		return ydAppMapperCart.lists(requestHashMap);
	}
	
	
	/**
	 * 加入购物车
	 */
	@SuppressWarnings("unchecked")
	public Object store(HashMap<String, Object> request) {
		Integer returnInteger = 0;
		
		try {
			Integer user_id = (Integer) session.getAttribute("user_id");
			
			if (user_id == null) {
				return YdUtilResponse.fail("请先登录");
			}
		
			YdShopCart ydShopCart = new YdShopCart();
			
			HashMap<String, Object> goodsMap = new HashMap<String, Object>();
			goodsMap = (HashMap<String, Object>) ydShopMapperGoods.info( Integer.parseInt( request.get("depot_id").toString() ) );

			if ( Integer.parseInt( goodsMap.get("depot_stock").toString() ) <= 0 ) {
				return YdUtilResponse.fail("该商品已经卖完!");
			}
			ydShopCart.setUser_id( user_id );
			ydShopCart.setStore_id( Integer.parseInt( goodsMap.get("store_id").toString() ) );
			ydShopCart.setDepot_id( Integer.parseInt( goodsMap.get("depot_id").toString() ) );
			
			// 购物车商品是否存在
			HashMap<String, Object> cartData = (HashMap<String, Object>) ydAppMapperCart.info(ydShopCart);
			if ( cartData != null ) {
				if ( Integer.parseInt( goodsMap.get("depot_stock").toString() ) <= Integer.parseInt( cartData.get("cart_num").toString() ) ) {
					ydShopCart.setCart_id( Integer.parseInt( cartData.get("cart_id").toString() ) );
					ydShopCart.setCart_num( Integer.parseInt( goodsMap.get("depot_stock").toString() ) );
					ydAppMapperCart.update(ydShopCart);
					return YdUtilResponse.fail("最多只能购买"+goodsMap.get("depot_stock")+"件");
				}
				returnInteger = ydAppMapperCart.setNum( Integer.parseInt( cartData.get("cart_id").toString() ), 1);
				return YdUtilResponse.success(returnInteger, "添加购物车成功!");
			}
			
			ydShopCart.setGoods_price( Float.parseFloat( goodsMap.get("depot_price").toString() ) );
			ydShopCart.setGoods_name( goodsMap.get("goods_name").toString() );
			ydShopCart.setGoods_summary( goodsMap.get("goods_summary").toString() );
			ydShopCart.setStore_name( goodsMap.get("store_name").toString() );
			ydShopCart.setGoods_cover( goodsMap.get("goods_cover").toString() );

			returnInteger = ydAppMapperCart.store(ydShopCart);
			return YdUtilResponse.success(returnInteger, "添加购物车成功!");
			
		} catch (Exception e) {
			return YdUtilResponse.fail("添加购物车失败!");
		}
	}
	
	
	public Object total() {
		Integer total = 0;
		if ( session.getAttribute("user_id") != null ) {
			total = ydAppMapperCart.total(Integer.parseInt( session.getAttribute("user_id").toString() ));
		}
		return total;
	}
	
	
	/**
	 * 更新购物车数量
	 * @param request
	 * @return
	 */
	@SuppressWarnings("all")
	public Object update(HashMap<String, Object> request) {
		try {
			YdShopCart ydShopCart = new YdShopCart();
			
			Integer depot_id = Integer.parseInt( request.get("depot_id").toString() );
			Integer cart_id = Integer.parseInt( request.get("cart_id").toString() );
			Integer cart_num = Integer.parseInt( request.get("cart_num").toString() );;

			ydShopCart.setCart_id(cart_id);
			
			HashMap<String, Object> goodsMap = new HashMap<String, Object>();
			goodsMap = (HashMap<String, Object>) ydShopMapperGoods.info( depot_id );
			
			if ( Integer.parseInt( goodsMap.get("depot_stock").toString() ) <= 0 ) {
				return YdUtilResponse.fail("该商品已经卖完!");
			}
			
			if ( Integer.parseInt( goodsMap.get("depot_stock").toString() ) < cart_num ) {
				cart_num = Integer.parseInt( goodsMap.get("depot_stock").toString() );
				
				ydShopCart.setCart_num( cart_num );
				ydAppMapperCart.update(ydShopCart);

				return YdUtilResponse.fail("最多只能购买"+goodsMap.get("depot_stock")+"件");
			}
			
			ydShopCart.setCart_num( cart_num );
			ydAppMapperCart.update(ydShopCart);
			return YdUtilResponse.success(cart_num, "更新成功!");
		} catch (Exception e) {
			return YdUtilResponse.fail("更新失败!");
		}
	}
	
	
	/**
	 * 删除
	 */
	public Object delete(HashMap<String, Object> request) {
		try {
			Integer returnInteger = ydAppMapperCart.delete(Integer.parseInt( request.get("cart_id").toString() ), Integer.parseInt( session.getAttribute("user_id").toString() ));
			return YdUtilResponse.success(returnInteger, "删除成功!");
		} catch (Exception e) {
			return YdUtilResponse.fail("删除失败!");
		}
	}
}
