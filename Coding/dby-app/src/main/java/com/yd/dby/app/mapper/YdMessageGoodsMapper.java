package com.yd.dby.app.mapper;

import com.yd.dby.app.entity.YdMessageGoods;

public interface YdMessageGoodsMapper {
    int deleteByPrimaryKey(Integer imMid);

    int insert(YdMessageGoods record);

    int insertSelective(YdMessageGoods record);

    YdMessageGoods selectByPrimaryKey(Integer imMid);

    int updateByPrimaryKeySelective(YdMessageGoods record);

    int updateByPrimaryKey(YdMessageGoods record);
}