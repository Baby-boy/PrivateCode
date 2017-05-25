package com.yd.gcj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.gcj.entity.YdMangerUserTeamEmpr;

public interface YdMangerMapperUserTeamEmpr {
	/***
	 * 根据团队信息id查询指定用户的团队人员信息
	 * @param teamId
	 * @return
	 */
	List<YdMangerUserTeamEmpr> $queryAllByTeamId(@Param("teamId") Integer teamId);
	
	/***
	 * 根据服务商id查询该用户的团队人员信息
	 * @param userId
	 * @return
	 */
	List<YdMangerUserTeamEmpr> $queryAllByUserId(@Param("userId") Integer userId);
	
	/***
	 * 根据团队人员信息id查询详情
	 * @param emprId
	 * @return
	 */
	YdMangerUserTeamEmpr $queryByEmprId(@Param("emprId") Integer emprId);
	
	/***
	 * 根据团队id查询该团队人员数量
	 * @param teamId
	 * @return
	 */
	Integer $queryCountByTeamId(@Param("teamId") Integer teamId);
	
	/***
	 * 根据服务商id查询该服务商团队人员数量
	 * @param userId
	 * @return
	 */
	Integer $queryCountByUserId(@Param("userId") Integer userId);
	
	/***
	 * 
	 * @param emprs
	 * @return
	 */
	Integer insert(@Param("emprs") List<YdMangerUserTeamEmpr> emprs);
	
	/***
	 * 删除指定id团队人员单条信息
	 * @param emprId
	 * @return
	 */
	Integer deleteByEmprId(@Param("emprId") Integer emprId);
	
	/***
	 * 删除指定团队id所包含的所有人员信息
	 * @param teamId
	 * @return
	 */
	Integer deleteByTeamId(@Param("teamId") Integer teamId);
	
	/****
	 * 删除指定服务商团队所有人员信息
	 * @param userId
	 * @return
	 */
	Integer $deleteByUserId(@Param("userId") Integer userId);
}
