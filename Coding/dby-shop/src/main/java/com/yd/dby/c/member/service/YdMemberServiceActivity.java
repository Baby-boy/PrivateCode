package com.yd.dby.c.member.service;

import java.util.List;

import com.yd.dby.c.member.entity.YdActivity;
import com.yd.dby.d.seller.entity.YdSellerGoods;

public interface YdMemberServiceActivity {

	/**
	 * @param id
	 * @param to
	 * @return
	 * 方法作用(查询所有的活动商品并分页)
	 */
	List<YdSellerGoods> queryActivityByActivityId(Integer id, Integer to);

	/**
	 * @return
	 * 方法作用(查询活动商品的总数)
	 */
	List<YdSellerGoods> queryTotal(Integer id);

	/**
	 * @param id
	 * @return
	 * 方法作用(根据id查询活动详情)
	 */
	YdActivity queryActivityDetailsById(Integer id);

}
