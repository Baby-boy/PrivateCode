package com.yd.dby.app.mapper;

import com.yd.dby.app.entity.YdOrderGoods;
import com.yd.dby.app.entity.vo.OrderDetailsVo;

public interface YdOrderGoodsMapper {
	int deleteByPrimaryKey(Integer ogId);

	int insert(YdOrderGoods record);

	int insertSelective(YdOrderGoods record);

	YdOrderGoods selectByPrimaryKey(Integer ogId);

	int updateByPrimaryKeySelective(YdOrderGoods record);

	int updateByPrimaryKey(YdOrderGoods record);

	OrderDetailsVo selectOrderDetails(Integer ogId);
}