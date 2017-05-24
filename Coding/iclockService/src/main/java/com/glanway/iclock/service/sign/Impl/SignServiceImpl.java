/**
 * @author zhangshuang
 * 2017年4月19日 下午9:02:40
 */
package com.glanway.iclock.service.sign.Impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glanway.iclock.dao.sign.SignDao;
import com.glanway.iclock.entity.sign.Sign;
import com.glanway.iclock.service.BaseServiceImpl;
import com.glanway.iclock.service.sign.SignService;

/**
 * 说明 : 
 * 
 * @author zhangshaung
 * @version 1.0.0
 * @dateTime 2017年4月19日 下午9:02:40
 */
@Transactional
@Service("signService")
public class SignServiceImpl extends BaseServiceImpl<Sign> implements SignService{
	
	@Autowired
	private SignDao signDao;
	
}
