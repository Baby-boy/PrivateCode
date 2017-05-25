package com.yd.dby.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.dby.app.entity.YdUser;
import com.yd.dby.app.entity.para.UserPara;

public interface YdUserMapper {
	int deleteByPrimaryKey(Integer userId);

	int insert(YdUser record);

	int insertSelective(YdUser record);

	YdUser selectByPrimaryKey(Integer userId);

	int updateByPrimaryKeySelective(YdUser record);

	int updateByPrimaryKey(YdUser record);

	int selectUserPageCount(@Param("para") UserPara para);

	List<YdUser> selectUserPageList(@Param("para") UserPara para);

	YdUser selectUserByNameOrMobile(@Param("account") String account);

	YdUser selectUserIntegrationByUserId(Integer userId);
	
	YdUser selectUserByMobile(@Param("mobile") String mobile);

}