package com.yd.dby.app.mapper;

import com.yd.dby.app.entity.YdGroupbuyGoods;

public interface YdGroupbuyGoodsMapper {
    int deleteByPrimaryKey(Integer gbgId);

    int insert(YdGroupbuyGoods record);

    int insertSelective(YdGroupbuyGoods record);

    YdGroupbuyGoods selectByPrimaryKey(Integer gbgId);

    int updateByPrimaryKeySelective(YdGroupbuyGoods record);

    int updateByPrimaryKey(YdGroupbuyGoods record);
}