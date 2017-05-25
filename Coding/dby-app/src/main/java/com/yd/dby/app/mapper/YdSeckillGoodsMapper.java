package com.yd.dby.app.mapper;

import com.yd.dby.app.entity.YdSeckillGoods;

public interface YdSeckillGoodsMapper {
    int deleteByPrimaryKey(Integer sgId);

    int insert(YdSeckillGoods record);

    int insertSelective(YdSeckillGoods record);

    YdSeckillGoods selectByPrimaryKey(Integer sgId);

    int updateByPrimaryKeySelective(YdSeckillGoods record);

    int updateByPrimaryKey(YdSeckillGoods record);
}