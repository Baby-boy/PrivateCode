package com.yd.gcj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.gcj.entity.YdMangerAttention;

public interface YdMangerMapperAttention {
	
	/***
	 * 根据用户id查询所关注的用户信息
	 * @param att_aid
	 * @return
	 */
	List<YdMangerAttention> $queryAll(@Param("att_aid") Integer att_aid);
	
	/***
	 * 查询指定用户关注数量
	 * @param att_aid
	 * @return
	 */
	Integer $queryACountNum(@Param("att_aid") Integer att_aid);
	
	/***
	 * 查询指定用户被关注数量
	 * @param att_bid
	 * @return
	 */
	Integer $queryBCountNum(@Param("att_bid") Integer att_bid);
	
	/***
	 * 添加关注
	 * @param att
	 * @return
	 */
	Integer $insert(YdMangerAttention att);
	
	/***
	 * 删除关注信息（取消关注）
	 * @param att_id
	 * @return
	 */
	Integer $delete(@Param("userAId") Integer userAId,@Param("userBId") Integer userBId); 
	
	/**
	 * 检查当前用户是否关注过对方用户
	 * @param att_aid
	 * @param att_bid
	 * @return
	 */
	Integer $isExist(@Param("att_aid") Integer att_aid,@Param("att_bid") Integer att_bid);
}
