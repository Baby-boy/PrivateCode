package com.yd.dby.app.mapper;

import org.apache.ibatis.annotations.Param;

import com.yd.dby.app.entity.YdMessageCtc;

public interface YdMessageCtcMapper {
	public int deleteByPrimaryKey(Integer messaeCtcId);

	public int insert(YdMessageCtc record);

	public int insertSelective(YdMessageCtc record);

	public YdMessageCtc selectByPrimaryKey(Integer messaeCtcId);

	public int updateByPrimaryKeySelective(YdMessageCtc record);

	public int updateByPrimaryKeyWithBLOBs(YdMessageCtc record);

	public int updateByPrimaryKey(YdMessageCtc record);

	/** 根据懒鱼商品ID查询消息中存储的懒鱼基本信息是否存在 */
	public YdMessageCtc selectMessageCtcByCtcId(@Param("ctcId") Integer ctcId);
}