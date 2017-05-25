package com.yd.dby.b.shop.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.dby.b.shop.entity.YdShopCart;


public interface YdShopMapperCart {
	
	Object info(YdShopCart ydShopCart);
	
	Object infoCartId(Integer cart_id);
	
	List<Object> lists(HashMap<String, Object> request);
	
	
	/**
	 * 窗口
	 */
	List<YdShopCart> window(@Param("user_id") Integer user_id);
	
	
	/**
	 * 用户购物车总数
	 */
	Integer total(@Param("user_id") Integer user_id);
	
	
	/**
	 * 加入购物车
	 * @param ydShopCart
	 * @return
	 */
	Integer store(YdShopCart ydShopCart);
	
	
	/**
	 * 更新
	 */
	Integer update(YdShopCart ydShopCart);
	
	
	/**
	 * 购物车数量加
	 * @param cart_id
	 * @param num
	 * @return
	 */
	Integer setNum(@Param("cart_id") Integer cart_id, @Param("num") Integer num);
	
	
	/**
	 * 购物车数量减
	 * @param cart_id
	 * @param num
	 * @return
	 */
	Integer delNum(@Param("cart_id") Integer cart_id, @Param("num") Integer num);
	
	
	/**
	 * 删除
	 */
	Integer delete(@Param("cart_id") Integer cart_id, @Param("user_id") Integer user_id);

	/*
	List<HashMap<String, ?>> query(@Param("user_id") Integer user_id);

	@Select("select cart_id, user_id, depot_id, cart_num from yd_cart where user_id = #{user_id} and depot_id = #{depot_id}")
	HashMap<String, ?> queryById(@Param("user_id") Integer user_id, @Param("depot_id") Integer depot_id);

	@Delete("delete from yd_cart where user_id = #{user_id}")
	Integer delete(@Param("user_id") Integer user_id);

	@Delete("delete from yd_cart where user_id = #{user_id} and depot_id = #{depot_id}")
	Integer delete2(@Param("user_id") Integer user_id, @Param("depot_id") Integer depot_id);

	@Select("<script>delete from yd_cart where user_id = #{user_id} and depot_id in <foreach item='item' collection='depot_ids' open='(' separator=',' close=')'>#{item}</foreach></script>")
	Integer delete3(@Param("user_id") Integer user_id, @Param("depot_ids") List<Integer> depot_ids);

	@Update("update yd_cart set cart_num = #{cart_num} where user_id = #{user_id} and depot_id = #{depot_id}")
	Integer patch(@Param("user_id") Integer user_id, @Param("depot_id") Integer depot_id, @Param("cart_num") Integer cart_num);

	@Insert("insert yd_cart(user_id, depot_id, cart_num) value (#{user_id}, #{depot_id}, #{cart_num})")
	Integer post(@Param("user_id") Integer user_id, @Param("depot_id") Integer depot_id, @Param("cart_num") Integer cart_num);
	
	*/
}
