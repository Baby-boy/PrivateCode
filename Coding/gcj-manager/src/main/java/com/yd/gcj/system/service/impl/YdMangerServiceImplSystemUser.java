package com.yd.gcj.system.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.YdMangerUser;
import com.yd.gcj.entity.vo.YdMangerUserVo;
import com.yd.gcj.system.mapper.YdMangerMapperSystemUser;
import com.yd.gcj.system.service.YdMangerServiceSystemUser;
@Service("ydMangerServiceSystemUser")
public class YdMangerServiceImplSystemUser implements YdMangerServiceSystemUser {
	@Autowired
	private YdMangerMapperSystemUser ydMangerMapperSystemUser;
	
	//查询所有的用户信息
	@Override
	public List<YdMangerUser> queryAllUser(String user_phone) {
	
		List<YdMangerUser> userList = ydMangerMapperSystemUser.queryAllUser(user_phone);
		return userList;
	}

	//添加用户
	@Override
	public Integer addUser(YdMangerUser ydMangerUser) {
		Integer addNum =  ydMangerMapperSystemUser.addUser(ydMangerUser);
		return addNum;
	}

	//删除用户
	@Override
	public Integer deleteUser(Integer user_id) {
		Integer delNum = ydMangerMapperSystemUser.deleteUser(user_id);
		return delNum;
	}

	//修改用户信息
	@Override
	public Integer updateUserById(YdMangerUser ydMangerUser) {
		Integer updateNum =  ydMangerMapperSystemUser.updateUserById(ydMangerUser);
		return updateNum;
	}

	//查询所有的服务商信息
	@Override
	public List<YdMangerUserVo> queryAllServer(String user_name,Integer user_cstate,Integer user_skillstate,Integer user_verified) {
		List<YdMangerUserVo> serverList = ydMangerMapperSystemUser.queryAllServer(user_name,user_cstate,user_verified,user_skillstate);
		return serverList;
	}

	//查询服务商公司认证状态
	@Override
	public YdMangerUserVo queryCompanyByUserId(Integer user_id) {
		YdMangerUserVo ydMangerUser = ydMangerMapperSystemUser.queryCompanyByUserId(user_id);
		return ydMangerUser;
	}

	//认证公司状态
	@Override
	public Integer updateUCstateByUserId(YdMangerUser ydMangerUser) {
		ydMangerUser.setUser_update_time(new Date());
		if (ydMangerUser.getUser_cstate()==1) {
			ydMangerUser.setUser_type(2);
		}
		Integer updateNum = ydMangerMapperSystemUser.updateUCstateByUserId(ydMangerUser);
		return updateNum;
	}

	//查询当前用户
	@Override
	public YdMangerUserVo queryUserByUserId(Integer userl_uid) {
		YdMangerUserVo ydMangerUserVo = ydMangerMapperSystemUser.queryUserByUserId(userl_uid);
		return ydMangerUserVo;
	}

	//修改个人技能审核状态
	@Override
	public Integer updateUserSkillStateByUsreId(YdMangerUserVo ydMangerUserVo) {
		Integer updateNum = ydMangerMapperSystemUser.updateUserSkillStateByUsreId(ydMangerUserVo);
		return updateNum;
	}

	//查询当前服务商的所有信息
	@Override
	public YdMangerUserVo queryServerByUserId(Integer user_id) {
		YdMangerUserVo ydMangerUser = ydMangerMapperSystemUser.queryServerByUserId(user_id);
		return ydMangerUser;
	}

	//根据用户账号查询消息接收方用户的id
	@Override
	public YdMangerUser queryUserByUserAccount(String user_account) {
		YdMangerUser ydMangerUser = ydMangerMapperSystemUser.queryUserByUserAccount(user_account);
		return ydMangerUser;
	}

	//条件查询用户消息先查询当前发送方的id
	@Override
	public YdMangerUser queryUserByUserNickName(String user_nickname) {
		YdMangerUser ydMangerUser = ydMangerMapperSystemUser.queryUserByUserNickName(user_nickname);
		return ydMangerUser;
	}

	//添加系统消息发给个人时先查询用户是否存在
	@Override
	public YdMangerUser queryUserByNickName(String user_nickname) {
		YdMangerUser ydMangerUser = ydMangerMapperSystemUser.queryUserByNickName(user_nickname);
		return ydMangerUser;
	}

	//根据user_id冻结用户
	@Override
	public Integer updateUserByUserId(YdMangerUserVo ydMangerUserVo) {
		Integer updateNum = ydMangerMapperSystemUser.updateUserByUserId(ydMangerUserVo);
		return updateNum;
	}

	//恢复冻结的用户
	@Override
	public Integer renewUserByUserId(YdMangerUserVo ydMangerUserVo) {
		Integer updateNum = ydMangerMapperSystemUser.renewUserByUserId(ydMangerUserVo);
		return updateNum;
	}

	@Override
	public Integer updateUserVerifiedStateByUsreId(@Param("userId")Integer userId, @Param("userVerified")Integer userVerified) {
		Integer updateNum = ydMangerMapperSystemUser.updateUserVerifiedStateByUsreId(userId,userVerified);
		return updateNum;
	}

}
