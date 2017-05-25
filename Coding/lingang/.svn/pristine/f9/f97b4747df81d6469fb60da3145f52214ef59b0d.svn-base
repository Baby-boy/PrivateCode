package com.lingang.api.service;

import com.lingang.api.domain.basic.Page;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.entity.SysCollect;
import com.lingang.api.domain.entity.SysMessage;
import com.lingang.api.domain.entity.SysUser;
import com.lingang.api.domain.para.SysUserPara;
import com.lingang.api.domain.vo.SysDownloadVo;
import com.lingang.api.domain.vo.SysFileParkVo;
import com.lingang.api.domain.vo.SysUserVo;

public interface SysUserService {

	/**
	 * @Description: (APP用户登录)
	 * @param userAccount
	 * @param userPassword
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月1日 上午9:40:48
	 */
	ServiceResult<SysUserVo> selectSysUserLoginByUserAccount(Integer loginType, String userAccount, String userPassword,
			String gesturePwd);

	/**
	 * @Description: (修改用户信息)
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月3日 上午9:54:33
	 */
	ServiceResult<SysUserVo> updateSysUser(SysUser sysUser);

	/**
	 * @Description: (我的收藏)
	 * @param pageIndex
	 * @param pageSize
	 * @param collectType
	 * @param userId
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月6日 上午12:52:55
	 */
	ServiceResult<Object> selectUserCollect(Integer pageIndex, Integer pageSize, Integer collectType, Integer userId);

	/**
	 * @Description: (我的下载)
	 * @param pageIndex
	 * @param pageSize
	 * @param userId
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月6日 上午2:24:07
	 */
	ServiceResult<Page<SysDownloadVo>> selectUserDownload(Integer pageIndex, Integer pageSize, Integer userId);

	/**
	 * @Description: (全部下载)
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月6日 上午4:06:21
	 */
	ServiceResult<Page<SysFileParkVo>> selectAllFile(Integer pageIndex, Integer pageSize, Integer userId);

	/**
	 * @Description: (信息纠错/信息反馈)
	 * @param message
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月6日 上午5:02:18
	 */
	ServiceResult<Object> insertUserOpinion(SysMessage message);

	/**
	 * @Description: (APP用户列表)
	 * @param para
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月8日 下午5:21:54
	 */
	ServiceResult<Page<SysUser>> selectSysUserPageList(SysUserPara para);

	/**
	 * @Description: (添加收藏)
	 * @param
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月6日 上午5:02:18
	 */
	ServiceResult<Object> insertSysCollect(SysCollect collect);

	/**
	 * @Description: (取消收藏)
	 * @param
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月6日 上午5:02:18
	 */
	ServiceResult<Object> delSysCollect(Integer collectId);
}
