package com.yd.dby.app.mapper;

import com.yd.dby.app.entity.YdAds;

public interface YdAdsMapper {
    int deleteByPrimaryKey(Integer adsId);

    int insert(YdAds record);

    int insertSelective(YdAds record);

    YdAds selectByPrimaryKey(Integer adsId);

    int updateByPrimaryKeySelective(YdAds record);

    int updateByPrimaryKey(YdAds record);
}