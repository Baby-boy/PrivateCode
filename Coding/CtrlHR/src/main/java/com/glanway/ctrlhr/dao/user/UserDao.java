package com.glanway.ctrlhr.dao.user;

import org.apache.ibatis.annotations.Param;

import com.glanway.ctrlhr.dao.BaseDao;
import com.glanway.ctrlhr.entity.user.User;

public interface UserDao extends BaseDao<User> {
	public int deleteByPrimaryKey(Long id);

	public int insert(User record);

	public int insertSelective(User record);

	public User selectByPrimaryKey(Long id);

	public int updateByPrimaryKeySelective(User record);

	public int updateByPrimaryKey(User record);

	public User getUser(@Param("name") String name, @Param("pwd") String pwd);

}