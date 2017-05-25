package com.yd.dby.app.service;

import org.springframework.transaction.annotation.Transactional;

import com.yd.dby.app.basic.Page;
import com.yd.dby.app.common.ServiceResult;
import com.yd.dby.app.entity.YdFeedback;
import com.yd.dby.app.entity.YdPointsLog;
import com.yd.dby.app.entity.YdThridpartyLogin;
import com.yd.dby.app.entity.YdUser;
import com.yd.dby.app.entity.YdWish;
import com.yd.dby.app.entity.para.BasePara;
import com.yd.dby.app.entity.para.UserPara;
import com.yd.dby.app.entity.vo.UserCenterVo;

public interface YdUserService {

	/**
	 * 说明: 用户登录
	 * 
	 * @param account
	 * @param userPassword
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月7日 上午11:48:04
	 */
	public ServiceResult<YdUser> userLogin(String account, String userPassword);

	/**
	 * 说明: 第三方登录
	 * 
	 * @param tplOpenid
	 * @param tplType
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月28日 上午10:20:33
	 */
	ServiceResult<YdUser> userLoginThird(String tplOpenid, Integer tplType);

	/**
	 * 说明: 添加第三方绑定
	 * 
	 * @param thridpartyLogin
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月28日 上午10:45:42
	 */
	ServiceResult<Object> insertYdThridpartyLogin(YdThridpartyLogin thridpartyLogin);

	/**
	 * 说明: 用户注册
	 * 
	 * @param user
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月9日 下午1:43:39
	 */
	@Transactional
	public ServiceResult<YdUser> userRegister(YdUser user);

	/**
	 * 说明: 忘记密码
	 * 
	 * @param user
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月9日 下午2:46:16
	 */
	@Transactional
	public ServiceResult<YdUser> updateUserPwdForget(YdUser user);

	/**
	 * 说明: 修改密码
	 * 
	 * @param user
	 * @param newPassword
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月9日 下午3:02:50
	 */
	@Transactional
	public ServiceResult<YdUser> updateUserPwd(YdUser user, String oldPassword);

	/**
	 * 说明: 用户查询(分页)
	 * 
	 * @param para
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月7日 上午10:24:02
	 */
	public ServiceResult<Page<YdUser>> selectUserPageList(UserPara para);

	/**
	 * 说明: 修改用户信息
	 * 
	 * @param ydUser
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月12日 下午3:09:24
	 */
	@Transactional
	public ServiceResult<YdUser> updateUser(YdUser ydUser);

	/**
	 * 说明: 用户中心
	 * 
	 * @param user
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月13日 下午2:29:58
	 */
	public ServiceResult<UserCenterVo> selectUserCenter(YdUser user);

	/**
	 * 说明: 许愿池
	 * 
	 * @param ydWish
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月13日 下午8:42:30
	 */
	@Transactional
	public ServiceResult<Object> insertUserWish(YdWish ydWish);

	/**
	 * 说明: 许愿池列表
	 * 
	 * @param para
	 * @param user
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月13日 下午8:59:33
	 */
	public ServiceResult<Page<YdWish>> selectUserWishPageList(BasePara para, YdUser user);

	/**
	 * 说明: 积分中心
	 * 
	 * @param para
	 * @param user
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月14日 上午10:19:53
	 */
	public ServiceResult<Page<YdPointsLog>> selectUserPointsCenter(BasePara para, YdUser user);

	/**
	 * 说明: 添加用户意见反馈
	 * 
	 * @param ydFeedback
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月15日 上午10:18:06
	 */
	@Transactional
	public ServiceResult<Object> insertUserFeedback(YdFeedback ydFeedback);
	
	YdUser selectByMobile(String mobile);

}
