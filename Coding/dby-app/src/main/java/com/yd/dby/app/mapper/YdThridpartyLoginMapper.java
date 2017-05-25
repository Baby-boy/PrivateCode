package com.yd.dby.app.mapper;

import org.apache.ibatis.annotations.Param;

import com.yd.dby.app.entity.YdThridpartyLogin;

public interface YdThridpartyLoginMapper {
	int insert(YdThridpartyLogin record);

	int insertSelective(YdThridpartyLogin record);

	YdThridpartyLogin selectByOpenidAndType(@Param("tplOpenid") String tplOpenid, @Param("tplType") Integer tplType);
}