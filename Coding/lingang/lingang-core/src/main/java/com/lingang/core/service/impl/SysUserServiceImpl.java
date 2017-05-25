package com.lingang.core.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lingang.api.domain.basic.Page;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.basic.StateCodeConstant;
import com.lingang.api.domain.entity.SysCollect;
import com.lingang.api.domain.entity.SysHome;
import com.lingang.api.domain.entity.SysImages;
import com.lingang.api.domain.entity.SysMessage;
import com.lingang.api.domain.entity.SysUser;
import com.lingang.api.domain.para.SysUserPara;
import com.lingang.api.domain.vo.SysDownloadVo;
import com.lingang.api.domain.vo.SysFileParkVo;
import com.lingang.api.domain.vo.SysParkVo;
import com.lingang.api.domain.vo.SysPartnerVo;
import com.lingang.api.domain.vo.SysPublicVo;
import com.lingang.api.domain.vo.SysServiceVo;
import com.lingang.api.domain.vo.SysStationVo;
import com.lingang.api.domain.vo.SysUserVo;
import com.lingang.api.service.SysUserService;
import com.lingang.common.ad.HttpURLConnectionResult;
import com.lingang.core.persistence.reader.SysCollectReaderMapper;
import com.lingang.core.persistence.reader.SysDownloadReaderMapper;
import com.lingang.core.persistence.reader.SysFileReaderMapper;
import com.lingang.core.persistence.reader.SysImagesReaderMapper;
import com.lingang.core.persistence.reader.SysParkReaderMapper;
import com.lingang.core.persistence.reader.SysPartnerReaderMapper;
import com.lingang.core.persistence.reader.SysPublicReaderMapper;
import com.lingang.core.persistence.reader.SysServiceReaderMapper;
import com.lingang.core.persistence.reader.SysStationReaderMapper;
import com.lingang.core.persistence.reader.SysUserReaderMapper;
import com.lingang.core.persistence.writer.SysCollectWriterMapper;
import com.lingang.core.persistence.writer.SysHomeWriterMapper;
import com.lingang.core.persistence.writer.SysImagesWriterMapper;
import com.lingang.core.persistence.writer.SysMessageWriterMapper;
import com.lingang.core.persistence.writer.SysUserWriterMapper;

@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {

	@Resource
	private SysUserReaderMapper sysUserReaderMapper;

	@Resource
	private SysUserWriterMapper sysUserWriterMapper;

	@Resource
	private SysParkReaderMapper sysParkReaderMapper;

	@Resource
	private SysPartnerReaderMapper sysPartnerReaderMapper;

	@Resource
	private SysServiceReaderMapper sysServiceReaderMapper;

	@Resource
	private SysStationReaderMapper sysStationReaderMapper;

	@Resource
	private SysPublicReaderMapper sysPublicReaderMapper;

	@Resource
	private SysDownloadReaderMapper sysDownloadReaderMapper;

	@Resource
	private SysFileReaderMapper sysFileReaderMapper;

	@Resource
	private SysMessageWriterMapper sysMessageWriterMapper;

	@Resource
	private SysCollectWriterMapper sysCollectWriterMapper;

	@Resource
	private SysCollectReaderMapper sysCollectReaderMapper;

	@Resource
	private SysImagesReaderMapper sysImagesReaderMapper;

	@Resource
	private SysImagesWriterMapper sysImagesWriterMapper;

	@Resource
	private SysHomeWriterMapper sysHomeWriterMapper;

	@Override
	public ServiceResult<SysUserVo> selectSysUserLoginByUserAccount(Integer loginType, String userAccount, String userPassword, String gesturePwd) {
		ServiceResult<SysUserVo> result = new ServiceResult<SysUserVo>();
		//AD域登录
		String objStr=HttpURLConnectionResult.resultStrLogin(userAccount, userPassword);
		if(objStr.equals("")){
			result.setStateCode(StateCodeConstant.ERROR_CODE_ACCOUNT_PWD_AD);
			result.setMessage("登录失败");
			return result;
		}
		// 数据转换
		SysUser user = new SysUser();
		user = JSONObject.parseObject(objStr, SysUser.class);
		user.setUserName(user.getUserName().substring(3));
		// 本地信息查询(例如头像、首页链接)
		SysUserVo userVo = sysUserReaderMapper.selectSysUserVoLoginByUserAccount(userAccount);
		// 本地初始化头像、首页链接
		if (userVo == null) {
			user.setCreateTime(new Date());
			user.setUserState(1);
			user.setGestureState(2);
			sysUserWriterMapper.insertSelective(user);
			SysImages images = new SysImages();
			images.setImgType(1);
			images.setObjId(user.getUserId());
			images.setImgPath("/image/upload/img/user_default.jpg");
			images.setImgState(1);
			sysImagesWriterMapper.insertSelective(images);
			user.setImgId(images.getImgId());
			sysUserWriterMapper.updateByPrimaryKeySelective(user);
			SysHome home = new SysHome();
			home.setUserId(user.getUserId());
			home.setHomeName("1,2,3,4,5,6");
			sysHomeWriterMapper.insertSelective(home);
			//
			String userStr = JSONObject.toJSONString(user);
			userVo = JSONObject.parseObject(userStr, SysUserVo.class);
			userVo.setImgPath(images.getImgPath());
		}
		if (loginType == 2 && !userVo.getGesturePwd().equals(gesturePwd)) {
			result.setStateCode(StateCodeConstant.ERROR_CODE_ACCOUNT_PWD);
			result.setMessage("登录失败");
			return result;
		}
		userVo.setUserDepartment(user.getUserDepartment());
		userVo.setUserPost(user.getUserPost());
		userVo.setUserTel(user.getUserTel());
		userVo.setUserMobile(user.getUserMobile());
		userVo.setUserEmail(user.getUserEmail());
		userVo.setUserName(user.getUserName());
		userVo.setUserCompany(user.getUserCompany());
		userVo.setUserPassword(null);
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		result.setMessage("登录成功");
		result.setData(userVo);
		return result;
	}

	@Override
	public ServiceResult<SysUserVo> updateSysUser(SysUser sysUser) {
		ServiceResult<SysUserVo> result = new ServiceResult<SysUserVo>();
		int i = sysUserWriterMapper.updateByPrimaryKeySelective(sysUser);
		if (sysUser.getImgId() != null) {
			SysImages img = sysImagesReaderMapper.selectByPrimaryKey(sysUser.getImgId());
			if (img.getImgState() != 1) {
				img.setImgState(1);
				sysImagesWriterMapper.updateByPrimaryKeySelective(img);
			}
		}
		if (i != 1) {
			result.setStateCode(StateCodeConstant.ERROR_CODE);
			result.setMessage("修改失败");
			return result;
		}
		SysUserVo user = sysUserReaderMapper.selectSysUserVoByUserId(sysUser.getUserId());
		user.setUserPassword(null);
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		result.setMessage("修改成功");
		result.setData(user);
		return result;
	}

	@Override
	public ServiceResult<Object> selectUserCollect(Integer pageIndex, Integer pageSize, Integer collectType, Integer userId) {
		ServiceResult<Object> result = new ServiceResult<Object>();
		// 查询条件
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("collectType", collectType);
		map.put("userId", userId);
		if (collectType == 1) {
			int countRecord = sysParkReaderMapper.selectUserCollectCount(map);
			Page<SysParkVo> page = new Page<SysParkVo>(pageIndex, countRecord, pageSize);
			map.put("startIndex", page.getStartIndex());
			map.put("onePageCount", pageSize);
			List<SysParkVo> list = sysParkReaderMapper.selectUserCollectPageList(map);
			page.setList(list);
			result.setData(page);
		} else if (collectType == 2) {
			int countRecord = sysPartnerReaderMapper.selectUserCollectCount(map);
			Page<SysPartnerVo> page = new Page<SysPartnerVo>(pageIndex, countRecord, pageSize);
			map.put("startIndex", page.getStartIndex());
			map.put("onePageCount", pageSize);
			List<SysPartnerVo> list = sysPartnerReaderMapper.selectUserCollectPageList(map);
			page.setList(list);
			result.setData(page);
		} else if (collectType == 3) {
			int countRecord = sysServiceReaderMapper.selectUserCollectCount(map);
			Page<SysServiceVo> page = new Page<SysServiceVo>(pageIndex, countRecord, pageSize);
			map.put("startIndex", page.getStartIndex());
			map.put("onePageCount", pageSize);
			List<SysServiceVo> list = sysServiceReaderMapper.selectUserCollectPageList(map);
			page.setList(list);
			result.setData(page);
		} else if (collectType == 4) {
			int countRecord = sysStationReaderMapper.selectUserCollectCount(map);
			Page<SysStationVo> page = new Page<SysStationVo>(pageIndex, countRecord, pageSize);
			map.put("startIndex", page.getStartIndex());
			map.put("onePageCount", pageSize);
			List<SysStationVo> list = sysStationReaderMapper.selectUserCollectPageList(map);
			page.setList(list);
			result.setData(page);
		} else if (collectType == 5) {
			int countRecord = sysPublicReaderMapper.selectUserCollectCount(map);
			Page<SysPublicVo> page = new Page<SysPublicVo>(pageIndex, countRecord, pageSize);
			map.put("startIndex", page.getStartIndex());
			map.put("onePageCount", pageSize);
			List<SysPublicVo> list = sysPublicReaderMapper.selectUserCollectPageList(map);
			page.setList(list);
			result.setData(page);
		}
		// 返回信息
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		return result;
	}

	@Override
	public ServiceResult<Page<SysDownloadVo>> selectUserDownload(Integer pageIndex, Integer pageSize, Integer userId) {
		ServiceResult<Page<SysDownloadVo>> result = new ServiceResult<Page<SysDownloadVo>>();
		// 查询条件
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		int countRecord = sysDownloadReaderMapper.selectSysDownloadVoCount(map);
		Page<SysDownloadVo> page = new Page<SysDownloadVo>(pageIndex, countRecord, pageSize);
		if (countRecord > 0) {
			map.put("startIndex", page.getStartIndex());
			map.put("onePageCount", pageSize);
			List<SysDownloadVo> list = sysDownloadReaderMapper.selectSysDownloadVoPageList(map);
			page.setList(list);
		}
		// 返回信息
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		result.setData(page);
		return result;
	}

	@Override
	public ServiceResult<Page<SysFileParkVo>> selectAllFile(Integer pageIndex, Integer pageSize, Integer userId) {
		ServiceResult<Page<SysFileParkVo>> result = new ServiceResult<Page<SysFileParkVo>>();
		// 查询条件
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		int countRecord = sysFileReaderMapper.selectAllFileCount(map);
		Page<SysFileParkVo> page = new Page<SysFileParkVo>(pageIndex, countRecord, pageSize);
		if (countRecord > 0) {
			map.put("startIndex", page.getCurrentPage() - 1);
			map.put("onePageCount", pageSize);
			List<SysFileParkVo> list = sysFileReaderMapper.selectAllFilePageList(map);
			page.setList(list);
		}
		// 返回信息
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		result.setData(page);
		return result;
	}

	@Override
	public ServiceResult<Object> insertUserOpinion(SysMessage message) {
		ServiceResult<Object> result = new ServiceResult<Object>();
		int i = sysMessageWriterMapper.insertSelective(message);
		if (i != 1) {
			result.setStateCode(StateCodeConstant.ERROR_CODE);
			result.setMessage("操作失败");
			return result;
		}
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		result.setMessage("操作成功");
		return result;
	}

	@Override
	public ServiceResult<Page<SysUser>> selectSysUserPageList(SysUserPara para) {
		ServiceResult<Page<SysUser>> result = new ServiceResult<Page<SysUser>>();
		// 查询条件
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userAccount", para.getUserAccount());
		map.put("userName", para.getUserName());
		int countRecord = sysUserReaderMapper.selectSysUserCount(map);
		Page<SysUser> page = new Page<>(para.getCurrentPage(), countRecord, para.getOnePageCount());
		if (countRecord > 0) {
			map.put("startIndex", page.getStartIndex());
			map.put("onePageCount", para.getOnePageCount());
			List<SysUser> list = sysUserReaderMapper.selectSysUserPageList(map);
			page.setList(list);
		}
		// 返回信息
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		result.setData(page);
		return result;
	}

	@Override
	public ServiceResult<Object> insertSysCollect(SysCollect collect) {
		ServiceResult<Object> result = new ServiceResult<Object>();
		SysCollect c = sysCollectReaderMapper.selectByUserIdAndCollectTypeAndObjId(collect.getUserId(), collect.getCollectType(), collect.getObjId());
		if (c != null) {
			result.setStateCode(StateCodeConstant.ERROR_CODE_HAVE);
			result.setMessage("已经收藏");
			return result;
		}
		int i = sysCollectWriterMapper.insertSelective(collect);
		if (i != 1) {
			result.setStateCode(StateCodeConstant.ERROR_CODE);
			result.setMessage("收藏失败");
			return result;
		}
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		result.setMessage("收藏成功");
		result.setData(collect);
		return result;
	}

	@Override
	public ServiceResult<Object> delSysCollect(Integer collectId) {
		ServiceResult<Object> result = new ServiceResult<Object>();
		int i = sysCollectWriterMapper.deleteByPrimaryKey(collectId);
		if (i != 1) {
			result.setStateCode(StateCodeConstant.ERROR_CODE);
			result.setMessage("取消失败");
			return result;
		}
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		result.setMessage("取消成功");
		return result;
	}

}
