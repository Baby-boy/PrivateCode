package com.yd.dby.c.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.c.member.entity.YdActivity;
import com.yd.dby.c.member.mapper.YdMemberMapperActivity;
import com.yd.dby.c.member.service.YdMemberServiceActivity;
import com.yd.dby.d.seller.entity.YdSellerGoods;


@Service("_YdMemberServiceActivity")
public class YdMemberServiceImplActivity implements YdMemberServiceActivity {
	
	@Autowired
	private YdMemberMapperActivity ydMemberMapperActivity;
	
	
	/**
	 * 根据商品id查询所有的活动商品
	 */
	@Override
	public List<YdSellerGoods> queryActivityByActivityId(Integer id, Integer to) {
		List<YdSellerGoods> ydSellerGoodsList = ydMemberMapperActivity.queryActivityByActivityId(id,to);
		return ydSellerGoodsList;
	}


	/**
	 * 查询活动商品的总数
	 */
	@Override
	public List<YdSellerGoods> queryTotal(Integer id) {
		List<YdSellerGoods> ydSellerGoods = ydMemberMapperActivity.queryTotal(id);
		return ydSellerGoods;
	}


	/**
	 * 查询活动详情
	 */
	@Override
	public YdActivity queryActivityDetailsById(Integer id) {
		YdActivity ydActivity = ydMemberMapperActivity.queryActivityDetailsById(id);
		return ydActivity;
	}
	
}