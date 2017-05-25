package com.yd.gcj.mapper;

import com.yd.gcj.entity.YdMangerTPMsg;

public interface YdMangerMapperTPMsg {
	public int deleteByPrimaryKey(Integer tpmsgId);

	public int insert(YdMangerTPMsg record);

	public int insertSelective(YdMangerTPMsg record);

	public YdMangerTPMsg selectByPrimaryKey(Integer tpmsgId);

	public int updateByPrimaryKeySelective(YdMangerTPMsg record);

	public int updateByPrimaryKey(YdMangerTPMsg record);
}