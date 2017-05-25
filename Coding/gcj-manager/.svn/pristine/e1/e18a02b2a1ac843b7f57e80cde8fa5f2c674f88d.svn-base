package com.yd.gcj.system.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.gcj.entity.YdMangerUser;
import com.yd.gcj.entity.vo.YdMangerUserVo;

public interface YdMangerServiceSystemUser {

	/**
	 * description(查询出所有的雇主用户)
	 * @param
	 * @return
	 */
	List<YdMangerUser> queryAllUser(String user_phone);

	/**
	 * description(添加雇主用户)
	 * @param
	 * @return
	 */
	Integer addUser(YdMangerUser ydMangerUser);

	/**
	 * description(根据指定的user_id删除雇主用户)
	 * @param
	 * @param user_id
	 * @return
	 */
	Integer deleteUser(Integer user_id);

	/**
	 * description(修改雇主用户信息)
	 * @param
	 * @param ydMangerUser
	 * @return
	 */
	Integer updateUserById(YdMangerUser ydMangerUser);

	/**
	 * description(查询所有的服务商信息)
	 * @param
	 * @return
	 */
	List<YdMangerUserVo> queryAllServer(String user_name,Integer user_cstate,Integer user_skillstate,Integer user_verified);

	/**
	 * description(根据user_id查询服务商公司认证的状态)
	 * @param
	 * @param user_id
	 * @return
	 */
	YdMangerUserVo queryCompanyByUserId(Integer user_id);

	/**
	 * description(认证公司状态)
	 * @param
	 * @param ydMangerUserVo
	 * @return
	 */
	Integer updateUCstateByUserId(YdMangerUser ydMangerUser);

	/**
	 * description(根据user_id查询当前用户)
	 * @param
	 * @param userl_uid
	 * @return
	 */
	YdMangerUserVo queryUserByUserId(Integer userl_uid);

	/**
	 * description(根据user_id修改个人技能审核状态)
	 * @param
	 * @param ydMangerUserVo
	 * @return
	 */
	Integer updateUserSkillStateByUsreId(YdMangerUserVo ydMangerUserVo);

	/**
	 * description(根据指定的user_id查询服务商的所有信息)
	 * @param
	 * @param user_id
	 * @return
	 */
	YdMangerUserVo queryServerByUserId(Integer user_id);

	/**
	 * description(添加系统消息时根据用户昵称查询消息接收方的用户id)
	 * @param
	 * @param msg_sbid
	 * @return
	 */
	YdMangerUser queryUserByUserAccount(String msg_sbid);

	/**
	 * description(用户消息条件查询时获取当前发送方的id)
	 * @param
	 * @param user_nickname
	 * @return
	 */
	YdMangerUser queryUserByUserNickName(String user_nickname);

	/**
	 * description(添加系统消息时吸纳根据用户昵称查询用户是否存在)
	 * @param
	 * @param user_nickname
	 * @return
	 */
	YdMangerUser queryUserByNickName(String user_nickname);

	/**
	 * description(根据user_id冻结用户)
	 * @param
	 * @param ydMangerUserVo
	 * @return
	 */
	Integer updateUserByUserId(YdMangerUserVo ydMangerUserVo);

	/**
	 * description(根据user_id恢复冻结的用户)
	 * @param
	 * @param ydMangerUserVo
	 * @return
	 */
	Integer renewUserByUserId(YdMangerUserVo ydMangerUserVo);

	/**
	 * description(根据user_id来改变实名认证状态)
	 * @param
	 * @param ydMangerUserVo
	 * @return
	 */
	Integer updateUserVerifiedStateByUsreId(@Param("userId")Integer userId,@Param("userVerified") Integer userVerified);


}
