package com.yd.dby.app.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yd.dby.app.common.ServiceResult;
import com.yd.dby.app.entity.YdAddress;
import com.yd.dby.app.entity.YdUser;

public interface YdAddressService {

	/**
	 * 说明: 添加用户收货地址
	 * 
	 * @param ydAddress
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月12日 下午1:20:17
	 */
	@Transactional
	public ServiceResult<Object> insertUserAddress(YdAddress ydAddress);

	/**
	 * 说明: 用户收货地址列表
	 * 
	 * @param user
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月12日 下午1:50:46
	 */
	@Transactional
	public ServiceResult<List<YdAddress>> selectAddressList(YdUser user);

	/**
	 * 说明: 编辑用户收货地址
	 * 
	 * @param ydAddress
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月12日 下午2:33:12
	 */
	@Transactional
	public ServiceResult<Object> updateUserAddress(YdAddress ydAddress);

	/**
	 * 说明: 删除用户收货地址
	 * 
	 * @param adIds
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月12日 下午2:46:36
	 */
	@Transactional
	public ServiceResult<Object> deleteUserAddress(String adIds);

}
