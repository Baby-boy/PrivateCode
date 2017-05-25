package com.yd.dby.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.app.basic.Page;
import com.yd.dby.app.common.HttpCode;
import com.yd.dby.app.common.ServiceResult;
import com.yd.dby.app.entity.YdFeedback;
import com.yd.dby.app.entity.YdPointsLog;
import com.yd.dby.app.entity.YdThridpartyLogin;
import com.yd.dby.app.entity.YdUser;
import com.yd.dby.app.entity.YdWish;
import com.yd.dby.app.entity.para.BasePara;
import com.yd.dby.app.entity.para.UserPara;
import com.yd.dby.app.entity.vo.UserCenterVo;
import com.yd.dby.app.mapper.YdAddressMapper;
import com.yd.dby.app.mapper.YdCommentMapper;
import com.yd.dby.app.mapper.YdFavoriteMapper;
import com.yd.dby.app.mapper.YdFeedbackMapper;
import com.yd.dby.app.mapper.YdOrderMapper;
import com.yd.dby.app.mapper.YdPointsLogMapper;
import com.yd.dby.app.mapper.YdThridpartyLoginMapper;
import com.yd.dby.app.mapper.YdUserMapper;
import com.yd.dby.app.mapper.YdWishMapper;
import com.yd.dby.app.service.YdUserService;

@Service("ydUserService")
public class YdUserServiceImpl implements YdUserService {

	@Autowired
	private YdUserMapper ydUserMapper;

	@Autowired
	private YdOrderMapper ydOrderMapper;// 订单

	@Autowired
	private YdCommentMapper ydCommentMapper;// 评价

	@Autowired
	private YdFavoriteMapper ydFavoriteMapper;// 收藏

	@Autowired
	private YdAddressMapper ydAddressMapper;// 地址管理

	@Autowired
	private YdWishMapper ydWishMapper;// 许愿池

	@Autowired
	private YdPointsLogMapper ydPointsLogMapper;// 积分日志

	@Autowired
	private YdFeedbackMapper ydFeedbackMapper;// 意见反馈

	@Autowired
	private YdThridpartyLoginMapper ydThridpartyLoginMapper;

	@Override
	public ServiceResult<YdUser> userLogin(String account, String userPassword) {
		ServiceResult<YdUser> result = new ServiceResult<YdUser>();

		YdUser user = ydUserMapper.selectUserByNameOrMobile(account);

		if (user == null) {
			result.setStatusCode(HttpCode.NO_CONTENT);
			result.setMsg("用户不存在");
			return result;
		}
		if (!userPassword.equals(user.getUserPassword())) {
			result.setStatusCode(HttpCode.NO_CONTENT);
			result.setMsg("账户或密码错误");
			return result;
		}

		YdUser ydUser = new YdUser();
		ydUser.setUserId(user.getUserId());
		ydUser.setUserUsername(user.getUserUsername());
		ydUser.setUserMobile(user.getUserMobile());
		result.setStatusCode(HttpCode.OK);
		result.setMsg("登录成功");
		result.setData(ydUser);

		return result;
	}

	@Override
	public ServiceResult<YdUser> userLoginThird(String tplOpenid, Integer tplType) {
		ServiceResult<YdUser> result = new ServiceResult<YdUser>();

		YdThridpartyLogin thridparty = ydThridpartyLoginMapper.selectByOpenidAndType(tplOpenid, tplType);
		if (thridparty == null) {
			result.setStatusCode(HttpCode.NON_AUTHORITATIVE_INFORMATION);
			result.setMsg("未绑定账号，请绑定账号后继续操作");
			return result;
		}

		YdUser user = ydUserMapper.selectByPrimaryKey(thridparty.getTplUserId());
		if (user == null) {
			result.setStatusCode(HttpCode.NO_CONTENT);
			result.setMsg("用户信息异常");
			return result;
		}

		YdUser ydUser = new YdUser();
		ydUser.setUserId(user.getUserId());
		ydUser.setUserUsername(user.getUserUsername());
		ydUser.setUserMobile(user.getUserMobile());
		result.setStatusCode(HttpCode.OK);
		result.setMsg("登录成功");
		result.setData(ydUser);

		return result;
	}

	@Override
	public ServiceResult<Object> insertYdThridpartyLogin(YdThridpartyLogin thridpartyLogin) {
		ServiceResult<Object> result = new ServiceResult<Object>();

		int i = ydThridpartyLoginMapper.insertSelective(thridpartyLogin);
		if (i == 1) {
			result.setStatusCode(HttpCode.OK);
			result.setMsg("绑定成功");
		} else {
			result.setStatusCode(HttpCode.INTERNAL_SERVER_ERROR);
			result.setMsg("系统繁忙，请稍后再试");
		}

		return result;
	}

	@Override
	public ServiceResult<YdUser> userRegister(YdUser user) {
		ServiceResult<YdUser> result = new ServiceResult<YdUser>();

		// 是否已注册
		YdUser u = ydUserMapper.selectUserByNameOrMobile(user.getUserMobile());
		if (u != null) {
			result.setStatusCode(HttpCode.ACCEPTED);
			result.setMsg("此账户已经存在");
			return result;
		}

		// 注册
		int i = ydUserMapper.insertSelective(user);
		if (i == 1) {
			result.setStatusCode(HttpCode.OK);
			result.setMsg("注册成功");
		} else {
			result.setStatusCode(HttpCode.INTERNAL_SERVER_ERROR);
			result.setMsg("系统繁忙，请稍后再试");
		}

		return result;
	}

	@Override
	public ServiceResult<YdUser> updateUserPwdForget(YdUser user) {
		ServiceResult<YdUser> result = new ServiceResult<YdUser>();

		// 是否存在
		YdUser u = ydUserMapper.selectUserByNameOrMobile(user.getUserMobile());
		if (u == null) {
			result.setStatusCode(HttpCode.NO_CONTENT);
			result.setMsg("此账户不存在");
			return result;
		}

		// 修改
		user.setUserId(u.getUserId());
		user.setUserMobile(null);
		int i = ydUserMapper.updateByPrimaryKeySelective(user);
		if (i == 1) {
			result.setStatusCode(HttpCode.OK);
			result.setMsg("修改成功");
		} else {
			result.setStatusCode(HttpCode.INTERNAL_SERVER_ERROR);
			result.setMsg("系统繁忙，请稍后再试");
		}

		return result;
	}

	@Override
	public ServiceResult<YdUser> updateUserPwd(YdUser user, String oldPassword) {
		ServiceResult<YdUser> result = new ServiceResult<YdUser>();

		// 是否存在
		YdUser u = ydUserMapper.selectByPrimaryKey(user.getUserId());
		if (u == null) {
			result.setStatusCode(HttpCode.NO_CONTENT);
			result.setMsg("此账户不存在");
			return result;
		}

		if (!u.getUserPassword().equals(oldPassword)) {
			result.setStatusCode(HttpCode.NO_CONTENT);
			result.setMsg("密码错误");
			return result;
		}

		// 修改
		user.setUserId(u.getUserId());
		int i = ydUserMapper.updateByPrimaryKeySelective(user);
		if (i == 1) {
			result.setStatusCode(HttpCode.OK);
			result.setMsg("修改成功");
		} else {
			result.setStatusCode(HttpCode.INTERNAL_SERVER_ERROR);
			result.setMsg("系统繁忙，请稍后再试");
		}

		return result;
	}

	@Override
	public ServiceResult<Page<YdUser>> selectUserPageList(UserPara para) {
		ServiceResult<Page<YdUser>> serviceResult = new ServiceResult<Page<YdUser>>();
		int countRecord = ydUserMapper.selectUserPageCount(para);
		Page<YdUser> page = new Page<YdUser>(para.getCurrentPage(), countRecord, para.getOnePageCount());
		if (para.getCurrentPage() <= page.getCountPage()) {
			page.setStartIndex(page.getStartIndex());
			List<YdUser> list = ydUserMapper.selectUserPageList(para);
			page.setList(list);
		}
		serviceResult.setData(page);
		serviceResult.setStatusCode(HttpCode.OK);
		serviceResult.setMsg("查询成功");
		return serviceResult;
	}

	@Override
	public ServiceResult<YdUser> updateUser(YdUser ydUser) {
		ServiceResult<YdUser> result = new ServiceResult<YdUser>();

		int i = ydUserMapper.updateByPrimaryKeySelective(ydUser);
		if (i == 1) {
			result.setStatusCode(HttpCode.OK);
			result.setMsg("修改成功");
			result.setData(ydUser);
		} else {
			result.setStatusCode(HttpCode.INTERNAL_SERVER_ERROR);
			result.setMsg("修改失败");
			result.setData(ydUser);
		}

		return result;
	}

	@Override
	public ServiceResult<UserCenterVo> selectUserCenter(YdUser user) {
		ServiceResult<UserCenterVo> result = new ServiceResult<>();

		// 查询用户基本信息
		YdUser ydUser = ydUserMapper.selectByPrimaryKey(user.getUserId());
		// 查询待付款总数
		int unPaidCount = ydOrderMapper.selectOrderCount(user.getUserId(), 1);// 1:待付款
		// 查询待收货总数
		int nonReceiptCount = ydOrderMapper.selectOrderCount(user.getUserId(), 3);// 3:已发货
		// 查询未评价总数
		int noCommentCount = ydCommentMapper.selectCommentCount(user.getUserId());
		// 查询收藏商品总数
		int favoriteGoodsCount = ydFavoriteMapper.selectFavoriteGoodsListCount(user.getUserId());
		// 查询收藏店铺总数
		int favoriteStoreCount = ydFavoriteMapper.selectFavoriteStoreListCount(user.getUserId());
		// 查询收货地址总数
		int addressCount = ydAddressMapper.selectAddressCount(user.getUserId());

		UserCenterVo userCenterVo = new UserCenterVo();// 创建一个对象用于存储信息
		userCenterVo.setUserUsername(ydUser.getUserUsername());
		userCenterVo.setUserNickname(ydUser.getUserNickname());
		userCenterVo.setUserSex(ydUser.getUserSex());
		userCenterVo.setUserBirthday(ydUser.getUserBirthday());
		userCenterVo.setUserProvinceValue(ydUser.getUserProvinceValue());
		userCenterVo.setUserIntegration(ydUser.getUserIntegration());
		userCenterVo.setUserAvatar(ydUser.getUserAvatar());
		userCenterVo.setUnPaidCount(unPaidCount);
		userCenterVo.setNonReceiptCount(nonReceiptCount);
		userCenterVo.setNoCommentCount(noCommentCount);
		userCenterVo.setFavoriteGoodsCount(favoriteGoodsCount);
		userCenterVo.setFavoriteStoreCount(favoriteStoreCount);
		userCenterVo.setAddressCount(addressCount);

		result.setMsg("查询成功!");
		result.setStatusCode(HttpCode.OK);
		result.setData(userCenterVo);
		return result;
	}

	@Override
	public ServiceResult<Object> insertUserWish(YdWish ydWish) {
		ServiceResult<Object> result = new ServiceResult<>();

		int info = ydWishMapper.insertSelective(ydWish);
		if (info == 1) {
			result.setMsg("许愿成功!");
			result.setStatusCode(HttpCode.OK);
		} else {
			result.setMsg("许愿失败!");
			result.setStatusCode(HttpCode.INTERNAL_SERVER_ERROR);
		}

		return result;
	}

	@Override
	public ServiceResult<Page<YdWish>> selectUserWishPageList(BasePara para, YdUser user) {
		ServiceResult<Page<YdWish>> result = new ServiceResult<>();

		int countRecord = ydWishMapper.selectUserWishPageListCount(user.getUserId());
		Page<YdWish> page = new Page<>(para.getCurrentPage(), countRecord, para.getOnePageCount());
		if (para.getCurrentPage() <= page.getCountPage()) {
			page.setStartIndex(page.getStartIndex());
			List<YdWish> list = ydWishMapper.selectUserWishPageList(para, user.getUserId());
			page.setList(list);
		}
		result.setData(page);
		result.setMsg("查询成功!");
		result.setStatusCode(HttpCode.OK);

		return result;
	}

	@Override
	public ServiceResult<Page<YdPointsLog>> selectUserPointsCenter(BasePara para, YdUser user) {
		ServiceResult<Page<YdPointsLog>> result = new ServiceResult<>();

		int countRecord = ydPointsLogMapper.selectUserPointsCenterPageListCount(user.getUserId());
		Page<YdPointsLog> page = new Page<>(para.getCurrentPage(), countRecord, para.getOnePageCount());
		if (para.getCurrentPage() <= page.getCountPage()) {
			page.setStartIndex(page.getStartIndex());
			List<YdPointsLog> list = ydPointsLogMapper.selectUserPointsCenterPageList(para, user.getUserId());
			page.setList(list);
		}
		result.setData(page);
		result.setMsg("查询成功!");
		result.setStatusCode(HttpCode.OK);

		return result;
	}

	@Override
	public ServiceResult<Object> insertUserFeedback(YdFeedback ydFeedback) {
		ServiceResult<Object> result = new ServiceResult<>();

		int info = ydFeedbackMapper.insertSelective(ydFeedback);
		if (info == 1) {
			result.setMsg("意见反馈成功!");
			result.setStatusCode(HttpCode.OK);
		} else {
			result.setMsg("意见反馈失败!");
			result.setStatusCode(HttpCode.INTERNAL_SERVER_ERROR);
		}

		return result;
	}

	@Override
	public YdUser selectByMobile(String mobile) {
		// TODO Auto-generated method stub
		return ydUserMapper.selectUserByMobile(mobile);
	}

}
