package com.yd.dby.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.dby.app.entity.YdDict;

public interface YdDictMapper {
	public int deleteByPrimaryKey(Integer dictId);

	public int insert(YdDict record);

	public int insertSelective(YdDict record);

	public YdDict selectByPrimaryKey(Integer dictId);

	public int updateByPrimaryKeySelective(YdDict record);

	public int updateByPrimaryKeyWithBLOBs(YdDict record);

	public int updateByPrimaryKey(YdDict record);

	/** 查询懒鱼商品几层新 */
	public List<YdDict> selectQualityListByDictType(@Param("dictType") String dictType);
}