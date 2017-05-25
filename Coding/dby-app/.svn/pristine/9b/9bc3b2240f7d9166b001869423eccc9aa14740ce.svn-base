package com.yd.dby.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.dby.app.entity.YdAddress;

public interface YdAddressMapper {
	public int deleteByPrimaryKey(Integer adId);

	public int insert(YdAddress record);

	public int insertSelective(YdAddress record);

	public YdAddress selectByPrimaryKey(Integer adId);

	public int updateByPrimaryKeySelective(YdAddress record);

	public int updateByPrimaryKey(YdAddress record);

	/** 重置所有收货地址为非默认状态 */
	public int updateAllAddressState(@Param("userId") Integer userId);

	/** 查询用户收货地址总数 */
	public int selectAddressCount(@Param("userId") Integer userId);

	/** 查询用户收货地址列表 */
	public List<YdAddress> selectAddressList(@Param("userId") Integer userId, @Param("state") Integer state);

	/** 删除用户收货地址 */
	public int deleteUserAddress(@Param("adIdList") List<Integer> adIdList);

}