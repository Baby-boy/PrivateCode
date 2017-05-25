package com.yd.gcj.service;

import org.springframework.transaction.annotation.Transactional;

import com.yd.gcj.entity.YdMangerVerified;

public interface YdMangerServiceVerified {
	
	/**
	 * 根据用户id查询实名认证信息
	 * @param userId
	 * @return
	 */
	public YdMangerVerified queryByUserId(Integer userId);
	
	/***
	 * 根据用户id查询用户实名认证信息id
	 * @param userId
	 * @return
	 */
	public Integer queryIdByUserId(Integer userId);
	
	/***
	 * 上传身份证正面照片
	 * @param userId
	 * @param carAImgPath
	 * @return
	 */
	@Transactional
	public Object carAUpLoad(Integer userId,String carAImgPath);
	
	/***
	 * 上传身份证背面照片
	 * @param userId
	 * @param carBImgPath
	 * @return
	 */
	@Transactional
	public Object carBUpLoad(Integer userId,String carBImgPath);
	
	/***
	 * 上传用户实名信息
	 * @param vId
	 * @param userId
	 * @param name
	 * @return
	 */
	@Transactional
	public Object submitUserMsg(Integer vId,Integer userId,String name,String idNum,Integer yosId);
	
}
