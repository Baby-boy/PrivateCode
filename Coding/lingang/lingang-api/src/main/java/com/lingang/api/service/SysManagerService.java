package com.lingang.api.service;

import com.lingang.api.domain.basic.Page;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.entity.SysManager;
import com.lingang.api.domain.entity.SysPower;
import com.lingang.api.domain.para.SysManagerPara;
import com.lingang.api.domain.pfvo.SysManagerPfVo;

public interface SysManagerService {

	/**
	 * @Description: (管理员列表)
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月6日 下午7:51:55
	 */
	public ServiceResult<Page<SysManagerPfVo>> selectSysManagerPageList(SysManagerPara para);

	/**
	 * @Description: (修改管理信息)
	 * @param manager
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月7日 下午7:58:12
	 */
	public ServiceResult<SysManager> updateSysManager(SysManager manager);

	/**
	 * @Description: (添加管理员)
	 * @param manager
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月20日 上午11:44:51
	 */
	public ServiceResult<SysManager> addSysManager(SysManager manager);
	
	/**
	* @Description: (管理员登录)
	* @param userAccount
	* @param userPwd
	* @return    
	* @author lgl(lgl1012dream@foxmail.com)
	* @date: 2016年12月20日 上午9:48:00
	 */
	ServiceResult<SysManager> selectSysManagerLogin(String userAccount,String userPwd);
	
	/**
	* @Description: (添加权限)
	* @param power
	* @return    
	* @author lgl(lgl1012dream@foxmail.com)
	* @date: 2017年1月17日 下午6:35:55
	 */
	ServiceResult<SysPower> addPower(SysPower power);
	
	/**
	* @Description: (修改权限)
	* @param power
	* @return    
	* @author lgl(lgl1012dream@foxmail.com)
	* @date: 2017年1月17日 下午6:36:12
	 */
	ServiceResult<SysPower> updatePower(SysPower power);
}
