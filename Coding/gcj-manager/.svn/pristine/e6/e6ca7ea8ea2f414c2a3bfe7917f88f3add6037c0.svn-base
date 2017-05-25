package com.yd.gcj.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yd.gcj.entity.YdMangerUserLabel;

public interface YdMangerMapperUserLabel {
	
	
	/***
	 * 查询用户所有技能标签信息
	 * @param userl_uid 
	 * @return
	 */
	List<YdMangerUserLabel> $queryAll(@Param("userl_uid") Integer userl_uid);
	
	/***
	 * 查询用户指定技能标签信息
	 * @param userl_id
	 * @return
	 */
	YdMangerUserLabel $queryById(@Param("userl_id") Integer userl_id);
	
	/***
	 * 删除用户指定的技能标签
	 * @param userl_id
	 * @return
	 */
	Integer $delete(@Param("userId") Integer userId);
	
	/***
	 * 为用户绑定新的技能标签
	 * @param userLabel
	 * @return
	 */
	Integer $insert(Map<String, Object> map);
	
	/***
	 * 添加用户个人技能标签
	 * @param userId
	 * @param reLabel
	 * @return
	 */
	Integer $insertRe(@Param("userId") Integer userId,@Param("reLable") String reLabel);
	
	/***
	 * 更新用户技能信息
	 * @param userLabel
	 * @return
	 */
	Integer $update(YdMangerUserLabel userLabel);
	
	/***
	 * 查询指定用户技能标签数量
	 * @param userl_id
	 * @return
	 */
	Integer $queryCountNumById(@Param("userl_id") Integer userl_id);
	
	/***
	 * 根据用户id和技能id检查用户是否有该技能
	 * @param user_id
	 * @param skill_id
	 * @return
	 */
	YdMangerUserLabel $queryByUidAndSkilltype(@Param("userl_uid") Integer userl_uid,@Param("userl_skilltype") Integer userl_skilltype);
	
}
