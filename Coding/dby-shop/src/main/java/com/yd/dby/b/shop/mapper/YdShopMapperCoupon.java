package com.yd.dby.b.shop.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface YdShopMapperCoupon {
    List<HashMap<String, Object>> get_many(@Param("user_id") Integer user_id, @Param("isexpired") Integer isexpired, @Param("store_ids") List<Integer> store_ids);


    /**
     * 用户优惠券
     *
     * @param user_id
     * @param store_id
     * @return
     */
    List<Object> listConfirmOrder(@Param("user_id") Integer user_id, @Param("store_id") Integer store_id);


    /**
     * 用户全场优惠券
     *
     * @param user_id
     */
    List<Object> listConfirmOrderFull(@Param("user_id") Integer user_id);


    /**
     * 删除用户已经领取的优惠券
     * @param couponIds
     * @param user_id
     * @return
     */
    Integer deleteReceiveCoupon(@Param("couponIds") List<String> couponIds, @Param("userId") Integer userId);
}
