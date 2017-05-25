package com.lingang.core.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lingang.api.domain.basic.Page;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.basic.StateCodeConstant;
import com.lingang.api.domain.entity.SysManager;
import com.lingang.api.domain.entity.SysPower;
import com.lingang.api.domain.para.SysManagerPara;
import com.lingang.api.domain.pfvo.SysManagerPfVo;
import com.lingang.api.service.SysManagerService;
import com.lingang.common.util.StringUtils;
import com.lingang.core.persistence.reader.SysManagerReaderMapper;
import com.lingang.core.persistence.reader.SysPowerReaderMapper;
import com.lingang.core.persistence.writer.SysManagerWriterMapper;
import com.lingang.core.persistence.writer.SysPowerWriterMapper;

@Service("sysManagerService")
public class SysManagerServiceImpl implements SysManagerService {

	@Resource
	private SysManagerReaderMapper sysManagerReaderMapper;

	@Resource
	private SysManagerWriterMapper sysManagerWriterMapper;
	
	@Resource
	private SysPowerReaderMapper sysPowerReaderMapper;
	
	@Resource
	private SysPowerWriterMapper sysPowerWriterMapper;

	@Override
	public ServiceResult<Page<SysManagerPfVo>> selectSysManagerPageList(SysManagerPara para) {
		ServiceResult<Page<SysManagerPfVo>> result = new ServiceResult<Page<SysManagerPfVo>>();
		// 查询条件
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("managerAccount", para.getManagerAccount());
		map.put("managerIdcard", para.getManagerIdcard());
		map.put("managerState", para.getManagerState());
		int countRecord = sysManagerReaderMapper.selectSysManagerCount(map);
		Page<SysManagerPfVo> page = new Page<SysManagerPfVo>(para.getCurrentPage(), countRecord, para.getOnePageCount());
		if (countRecord > 0) {
			map.put("startIndex", page.getStartIndex());
			map.put("onePageCount", para.getOnePageCount());
			List<SysManagerPfVo> list = sysManagerReaderMapper.selectSysManagerPageList(map);
			page.setList(list);
		}
		// 返回信息
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		result.setData(page);
		return result;
	}

	@Override
	public ServiceResult<SysManager> updateSysManager(SysManager manager) {
		ServiceResult<SysManager> result = new ServiceResult<SysManager>();
		// 加密
		if ("".equals(manager.getManagerPassword())) {
			manager.setManagerPassword(null);
		}
		if (manager.getManagerPassword() != null) {
			manager.setManagerPassword(StringUtils.MD5(manager.getManagerPassword() + manager.getManagerAccount()));
		}
		int i = sysManagerWriterMapper.updateByPrimaryKeySelective(manager);
		if (i == 1) {
			// 返回信息
			result.setStateCode(StateCodeConstant.SUCCESS_CODE);
			result.setMessage("修改成功");
			result.setData(manager);
		} else {
			result.setMessage(StateCodeConstant.ERROR_CODE);
			result.setMessage("修改失败");
		}
		return result;
	}

	@Override
	public ServiceResult<SysManager> addSysManager(SysManager manager) {
		ServiceResult<SysManager> result = new ServiceResult<>();
		// 加密
		manager.setManagerPassword(StringUtils.MD5(manager.getManagerPassword() + manager.getManagerAccount()));
//		manager.setCreateTime(new Date());
		manager.setUpdateTime(manager.getCreateTime());
		manager.setManagerId(null);
		int info = sysManagerWriterMapper.insertSelective(manager);
		if (info == 1) {
			// 添加成功
			result.setStateCode(StateCodeConstant.SUCCESS_CODE);
			result.setMessage("添加管理员成功!");
		} else {
			result.setStateCode(StateCodeConstant.ERROR_CODE);
			result.setMessage("添加管理员失败!");
		}
		return result;
	}

	@Override
	public ServiceResult<SysManager> selectSysManagerLogin(String userAccount, String userPwd) {
		ServiceResult<SysManager> result = new ServiceResult<SysManager>();

		SysManager manager = sysManagerReaderMapper.selectByManagerAccount(userAccount);
		if (manager == null) {
			result.setStateCode(StateCodeConstant.ERROR_CODE_ACCOUNT_NULL);
			result.setMessage("查询不到此用户");
			return result;
		}
		if (manager.getManagerState() != 1) {
			result.setStateCode(StateCodeConstant.ERROR_CODE_ACCOUNT_STATE);
			result.setMessage("账号信息异常");
			return result;
		}
		if (!manager.getManagerPassword().equals(userPwd)) {
			result.setStateCode(StateCodeConstant.ERROR_CODE_ACCOUNT_PWD);
			result.setMessage("账户或密码错误");
			return result;
		}
		//权限获取
		Map<String, Object> powerData=new HashMap<String, Object>();
		powerData.put("pId", "");
		powerData.put("p", "");
		SysPower power=sysPowerReaderMapper.selectByManagerId(manager.getManagerId());
		if(power !=null){
			powerData.put("pId", power.getPowerId());
			powerData.put("p", power.getPowerModularStr());
		}
		result.setDataMap(powerData);
		manager.setManagerPassword(null);
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		result.setMessage("登录成功");
		result.setData(manager);
		return result;
	}

	@Override
	public ServiceResult<SysPower> addPower(SysPower power) {
		ServiceResult<SysPower> result=new ServiceResult<SysPower>();
		int i=sysPowerWriterMapper.insertSelective(power);
		if (i == 1) {
			// 添加成功
			result.setStateCode(StateCodeConstant.SUCCESS_CODE);
			result.setMessage("添加成功!");
			result.setData(power);
		} else {
			result.setStateCode(StateCodeConstant.ERROR_CODE);
			result.setMessage("添加失败!");
		}
		return result;
	}

	@Override
	public ServiceResult<SysPower> updatePower(SysPower power) {
		ServiceResult<SysPower> result=new ServiceResult<SysPower>();
		int i =sysPowerWriterMapper.updateByPrimaryKeySelective(power);
		if (i == 1) {
			result.setStateCode(StateCodeConstant.SUCCESS_CODE);
			result.setMessage("修改成功!");
			result.setData(power);
		} else {
			result.setStateCode(StateCodeConstant.ERROR_CODE);
			result.setMessage("修改失败!");
		}
		return result;
	}

}
