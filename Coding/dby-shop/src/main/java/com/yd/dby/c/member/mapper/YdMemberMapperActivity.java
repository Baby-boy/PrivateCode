package com.yd.dby.c.member.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.dby.c.member.entity.YdActivity;
import com.yd.dby.d.seller.entity.YdSellerGoods;

public interface YdMemberMapperActivity {

	/**
	 * @param id
	 * @param to
	 * @return
	 * 方法作用(查询所有的活动商品并分页)
	 */
	List<YdSellerGoods> queryActivityByActivityId(@Param("id")Integer id, @Param("to")Integer to);

	/**
	 * @return
	 * 方法作用(查询活动中的所有商品)
	 */
	List<YdSellerGoods> queryTotal(@Param("id")Integer id);

	/**
	 * @param id
	 * @return
	 * 方法作用(查询活动详情)
	 */
	YdActivity queryActivityDetailsById(Integer id);

}
