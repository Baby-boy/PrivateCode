package com.yd.dby.app.mapper;

import com.yd.dby.app.entity.YdAuction;

public interface YdAuctionMapper {
    int deleteByPrimaryKey(Integer auctionId);

    int insert(YdAuction record);

    int insertSelective(YdAuction record);

    YdAuction selectByPrimaryKey(Integer auctionId);

    int updateByPrimaryKeySelective(YdAuction record);

    int updateByPrimaryKey(YdAuction record);
}