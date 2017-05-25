package com.yd.dby.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.dby.app.entity.YdData;
import com.yd.dby.app.entity.vo.HomeCtcVo;

public interface YdDataMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(YdData record);

	int insertSelective(YdData record);

	YdData selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(YdData record);

	int updateByPrimaryKey(YdData record);

	List<HomeCtcVo> selectHomeCtcListByType(@Param("type") String type);
}