package com.yd.gcj.mapper;

import org.apache.ibatis.annotations.Param;

import com.yd.gcj.entity.YdMangerUserTeam;
import com.yd.gcj.entity.vo.YdMangerUserTeamVo;

public interface YdMangerMapperUserTeam {
	
	/***
	 * 查询指定用户团队信息
	 * @param userId
	 * @return
	 */
	YdMangerUserTeamVo $queryByUserId(@Param("userId") Integer userId);
	
	/***
	 * 检查指定用户的团队信息是否存在
	 * @param userId
	 * @return
	 */
	Integer $isExsitByUserId(@Param("userId") Integer userId);
	
	/***
	 * 添加用户团队信息
	 * @param team
	 * @return
	 */
	Integer $insert(YdMangerUserTeam team);
	
	/***
	 * 更新服务商团队信息
	 * @param team
	 * @return
	 */
	Integer $update(YdMangerUserTeam team);
	
	/***
	 * 删除指定用户的团队信息
	 * @param userId
	 * @return
	 */
	Integer $deleteByUserId(@Param("userId") Integer userId);
}
