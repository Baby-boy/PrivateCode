package com.glanway.iclock.service.sign.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glanway.gone.util.StringUtils;
import com.glanway.iclock.dao.employee.EmployeeDeviceInfoDao;
import com.glanway.iclock.dao.employee.FingerFaceTemplateDao;
import com.glanway.iclock.dao.sign.DeviceDao;
import com.glanway.iclock.entity.employee.EmployeeDeviceInfo;
import com.glanway.iclock.entity.employee.FingerFaceTemplate;
import com.glanway.iclock.entity.sign.Device;
import com.glanway.iclock.entity.vo.device.EmployeeDeviceFingerFaceVo;
import com.glanway.iclock.entity.vo.device.EmployeeDeviceInfoVO;
import com.glanway.iclock.service.BaseServiceImpl;
import com.glanway.iclock.service.sign.DeviceService;

@Service("deviceService")
@Transactional
public class DeviceServiceImpl extends BaseServiceImpl<Device> implements DeviceService {

	@Autowired
	private DeviceDao deviceDao;

	@Autowired
	private EmployeeDeviceInfoDao employeeDeviceInfoDao;

	@Autowired
	private FingerFaceTemplateDao faceTemplateDao;

	/**
	 * 新增考勤设备
	 */
	@Override
	public void saveDevice(Device device) {

		device.setId(null);
		device.setBatchDate(new Date());
		device.setCreatedBy(1l);
		device.setCreatedDate(new Date());
		device.setCreProId(1l);
		device.setModProId(1l);
		device.setLastModifiedBy(1l);
		device.setLastModifiedDate(new Date());

		deviceDao.insertSelective(device);
	}

	/**
	 * 
	 * 说明 : 根据设备代码sn 查询设备信息
	 * 
	 * @param sn
	 * @return
	 * @author zhangshaung
	 * @dateTime 2017年4月20日 下午2:03:25
	 */
	@Override
	public Device selectByDeviceSn(String sn) {
		return deviceDao.selectByDeviceSn(sn);
	}

	/**
	 * 
	 * 说明 : 取数据库中根据已连通设备最后一次连接时间超过5分钟的设备
	 * 
	 * @return
	 * @author zhangshaung
	 * @dateTime 2017年4月20日 下午6:05:16
	 */
	@Override
	public List<Device> findLastConnectionTimeExcendFiveMinute() {
		return deviceDao.findLastConnectionTimeExcendFiveMinute();
	}

	/**
	 * 
	 * 说明 : 根据ID修改设备的状态
	 * 
	 * @param id
	 * @param state
	 * @author zhangshaung
	 * @dateTime 2017年4月20日 下午6:18:12
	 */
	@Override
	public void updateStateById(Long id, Integer state) {
		Map<String, Object> param = new HashMap<String, Object>();

		param.put("id", id);

		param.put("state", state);

		deviceDao.updateStateById(param);

	}

	/**
	 * 
	 * 说明 : 根据设备代码SN修改设备的状态
	 * 
	 * @param sn
	 * @param state
	 * @author zhangshaung
	 * @dateTime 2017年4月20日 下午6:19:11
	 */
	@Override
	public void updateStateBySn(String sn, Integer state) {
		Map<String, Object> param = new HashMap<String, Object>();

		param.put("sn", sn);

		param.put("state", state);

		deviceDao.updateStateBySn(param);
	}

	/**
	 * 
	 * 说明 : 根据设备sn 得到更新或新增员工基本信息的命令数据
	 * 
	 * @param sn
	 * @return
	 * @author zhangshaung
	 * @dateTime 2017年4月21日 下午4:14:57
	 */
	@Override
	public List<String> updateUserInfoDataBySn(String sn) {
		// 先根据设备sn 查询 需要到当前设备上打卡的所有员工信息
		List<EmployeeDeviceInfoVO> employeeVos = this.findEmployeeBySn(sn);
		// 开始拼接命令
		// 参数集

		List<String> list = new ArrayList<String>();
		for (EmployeeDeviceInfoVO eInfoVO : employeeVos) {
			final StringBuilder param = new StringBuilder();
			final String code = eInfoVO.getCode();
			final String name = eInfoVO.getName();
			final String pwd = eInfoVO.getPwd(); // FIXME
			final String card = eInfoVO.getCard();
			final String pri = eInfoVO.getPri();

			param.append("PIN=").append(code);

			if (StringUtils.hasText(name)) {
				param.append(HT).append("Name=");

				param.append(name);
			}

			if (StringUtils.hasText(pwd)) {
				param.append(HT).append("Passwd=").append(pwd);
			}

			if (StringUtils.hasText(card)) {
				// param.append(HT).append("Card=").append(pwd); // FIXME
			}

			param.append(HT).append("Pri=").append(StringUtils.hasText(pri) ? pri : "0");

			for (int i = 0; i < name.length() - 1; i++) {
				param.append(HT);
			}

			/*
			 * //单个参数 param.append("PIN="+eInfoVO.getCode()+HT);
			 * //先判断Name是否为中文,如果为中文,编码为:GB2312;如果为其他,编码为UTF-8 String name =
			 * eInfoVO.getName(); if(null != name){ try {
			 * if(StringUtil.hasChinese(name)){ name=new
			 * String(name.getBytes(),"GB2312"); }else{ name=new
			 * String(name.getBytes(),"UTF-8"); } } catch
			 * (UnsupportedEncodingException e) { // TODO Auto-generated catch
			 * block //e.printStackTrace(); name=eInfoVO.getName(); } }
			 * param.append("Name="+eInfoVO.getName()+HT); if(null !=
			 * eInfoVO.getPwd()){ param.append("Passwd="+ eInfoVO.getPwd()+HT);
			 * } if(null != eInfoVO.getCard()){
			 * param.append("Card="+eInfoVO.getCard()+HT); }
			 * //param.append("Grp="+null+"\t");
			 * //param.append("TZ="+null+"\t"); param.append("Pri="+(null ==
			 * eInfoVO.getPri()? "0" : eInfoVO.getPri())+HT); //
			 * param.append(LF);
			 */

			/*
			 * 新需求 2017-04-27 如果员工没有指纹信息或脸部信息,就不需要同步到设备上
			 */
			// 先根据员工编号查询是否有指纹和脸纹 , 如果没有指纹或面部数据,就不同步当前员工
			final int count = this.countFingerAndFaceByEmployeeCode(code);
			if (count > 0) {
				list.add(param.toString());
			}
		}
		return list;
	}

	/**
	 * 
	 * 说明 : 根据设备sn 查询 需要到当前设备上打卡的所有员工信息
	 * 
	 * @param sn
	 * @return
	 * @author zhangshaung
	 * @dateTime 2017年4月21日 下午4:11:17
	 */
	@Override
	public List<EmployeeDeviceInfoVO> findEmployeeBySn(String sn) {
		if (null == sn && "".equals(sn)) {
			return null;
		}
		Map<String, Object> param = new HashMap<String, Object>();

		param.put("sn", sn);
		List<EmployeeDeviceInfoVO> deviceEmployeeVos = deviceDao.findEmployeeBySn(param);
		return deviceEmployeeVos;
	}

	/**
	 * 
	 * 说明 : 根据设备sn 得到更新或新增员工头像的命令数据
	 * 
	 * @param sn
	 * @return
	 * @author zhangshaung
	 * @dateTime 2017年4月21日 下午4:14:57
	 */
	@Override
	public List<String> updateUserPhoneDataBySn(String sn) {
		// 先根据设备sn 查询 需要到当前设备上打卡的所有员工信息
		List<EmployeeDeviceInfoVO> employeeVos = this.findEmployeeBySn(sn);
		// 开始拼接命令
		// 参数集
		List<String> list = new ArrayList<String>();

		for (EmployeeDeviceInfoVO eInfoVO : employeeVos) {
			final StringBuilder param = new StringBuilder();
			final String code = eInfoVO.getCode();
			String pic = "";
			param.append("PIN=").append(code);
			EmployeeDeviceInfo employeeDeviceInfo = employeeDeviceInfoDao.selectByEmployeeCode(eInfoVO.getCode());
			if (null != employeeDeviceInfo && null != employeeDeviceInfo.getPic()) {
				pic = employeeDeviceInfo.getPic();
				param.append(HT).append("Size=").append(pic.length());
				param.append(HT).append("Content=").append(pic);
				/*
				 * 新需求 2017-04-27 如果员工没有指纹信息或脸部信息,就不需要同步到设备上
				 */
				// 先根据员工编号查询是否有指纹和脸纹 , 如果没有指纹或面部数据,就不同步当前员工
				final int count = this.countFingerAndFaceByEmployeeCode(code);
				if (count > 0) {
					list.add(param.toString());
				}
			}

			// 单个参数
			// 根据code查询pic
			/*
			 * StringBuffer param = new StringBuffer(); EmployeeDeviceInfo
			 * employeeDeviceInfo=employeeDeviceInfoDao.selectByEmployeeCode(
			 * eInfoVO.getCode()); param.append("PIN="+eInfoVO.getCode()+HT);
			 * String pic=""; if(null !=employeeDeviceInfo && null !=
			 * employeeDeviceInfo.getPic()){ pic=employeeDeviceInfo.getPic();
			 * param.append("Size="+pic.length()+HT);
			 * param.append("Content="+pic+HT); //param.append(LF);
			 * list.add(param.toString()); }
			 */

		}
		return list;
	}

	/**
	 * 
	 * 说明 : 根据设备sn 查询 需要到当前设备上打卡的所有员工指纹模板或脸部模板
	 * 
	 * @param sn
	 *            设备代码
	 * @param type
	 *            类型 1.指纹模板 2.面部模板
	 * @return
	 * @author zhangshaung
	 * @dateTime 2017年4月21日 下午7:20:14
	 */
	@Override
	public List<EmployeeDeviceFingerFaceVo> findEmployeeFingerFaceBySn(String sn, Integer type) {
		if (null == sn || null == type) {
			return null;
		}
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("sn", sn);
		param.put("type", type);
		List<EmployeeDeviceFingerFaceVo> list = deviceDao.findEmployeeFingerFaceBySn(param);
		return list;
	}

	/**
	 * 
	 * 说明 : 根据设备sn 得到更新或新增员工指纹模板的命令数据
	 * 
	 * @param sn
	 * @return
	 * @author zhangshaung
	 * @dateTime 2017年4月21日 下午7:30:45
	 */
	@Override
	public List<String> updateUserFingerTmpDataBySn(String sn) {
		// 先根据设备sn 查询 需要到当前设备上打卡的所有员工指纹
		List<EmployeeDeviceFingerFaceVo> employeeVos = this.findEmployeeFingerFaceBySn(sn, 1);
		// 开始拼接命令
		// 参数集
		List<String> list = new ArrayList<String>();
		for (EmployeeDeviceFingerFaceVo eInfoVO : employeeVos) {
			final StringBuilder param = new StringBuilder();
			final String code = eInfoVO.getCode();
			final String fid = eInfoVO.getFid();
			final Integer size = eInfoVO.getSize();
			final String valid = eInfoVO.getValid();

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("type", 1);
			map.put("fid", eInfoVO.getFid());
			map.put("employeeCode", eInfoVO.getCode());
			FingerFaceTemplate fingerTemplate = faceTemplateDao.findInfoByEmployeeCodeAndTypeAndFid(map);
			if (null != fingerTemplate && null != fingerTemplate.getTmp()) {

				String tmp = fingerTemplate.getTmp();
				param.append("PIN=").append(code);
				param.append(HT).append("FID=").append(fid);
				param.append(HT).append("Size=").append(size);
				param.append(HT).append("Valid=").append(valid);
				param.append(HT).append("TMP=").append(tmp);

				list.add(param.toString());
			}

			/*
			 * StringBuffer param = new StringBuffer(); //单个参数 Map<String,
			 * Object> map = new HashMap<String,Object>(); map.put("type", 1);
			 * map.put("fid", eInfoVO.getFid()); map.put("employeeCode",
			 * eInfoVO.getCode()); FingerFaceTemplate
			 * fingerTemplate=faceTemplateDao.
			 * findInfoByEmployeeCodeAndTypeAndFid(map); String tmp=""; if(null
			 * !=fingerTemplate && null != fingerTemplate.getTmp()){
			 * tmp=fingerTemplate.getTmp();
			 * 
			 * param.append("PIN="+eInfoVO.getCode()+HT);
			 * param.append("FID="+eInfoVO.getFid()+HT);
			 * param.append("Size="+eInfoVO.getSize()+HT);
			 * param.append("Valid="+eInfoVO.getValid()+HT);
			 * param.append("TMP="+tmp); // param.append(LF);
			 * list.add(param.toString()); }
			 */

		}
		return list;
	}

	/**
	 * 
	 * 说明 : 根据设备sn 得到更新或新增员工面部模板的命令数据
	 * 
	 * @param sn
	 * @return
	 * @author zhangshaung
	 * @dateTime 2017年4月21日 下午7:30:45
	 */
	@Override
	public List<String> updateUserFaceTmpDataBySn(String sn) {
		// 先根据设备sn 查询 需要到当前设备上打卡的所有员工面部模板
		List<EmployeeDeviceFingerFaceVo> employeeVos = this.findEmployeeFingerFaceBySn(sn, 2);
		// 开始拼接命令
		// 参数集

		List<String> list = new ArrayList<String>();
		for (EmployeeDeviceFingerFaceVo eInfoVO : employeeVos) {

			final StringBuilder param = new StringBuilder();
			final String code = eInfoVO.getCode();
			final String fid = eInfoVO.getFid();
			final Integer size = eInfoVO.getSize();
			final String valid = eInfoVO.getValid();

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("type", 2);
			map.put("fid", eInfoVO.getFid());
			map.put("employeeCode", eInfoVO.getCode());
			FingerFaceTemplate faceTemplate = faceTemplateDao.findInfoByEmployeeCodeAndTypeAndFid(map);
			if (null != faceTemplate && null != faceTemplate.getTmp()) {
				String tmp = faceTemplate.getTmp();

				param.append("PIN=").append(code);

				param.append(HT).append("FID=").append(fid);
				param.append(HT).append("Size=").append(size);
				param.append(HT).append("Valid=").append(valid);
				param.append(HT).append("TMP=").append(tmp);

				list.add(param.toString());
			}

			/*
			 * StringBuffer param = new StringBuffer(); // 单个参数 Map<String,
			 * Object> map = new HashMap<String, Object>(); map.put("type", 1);
			 * map.put("fid", eInfoVO.getFid()); map.put("employeeCode",
			 * eInfoVO.getCode()); FingerFaceTemplate faceTemplate =
			 * faceTemplateDao.findInfoByEmployeeCodeAndTypeAndFid(map); String
			 * tmp = ""; if (null != faceTemplate && null !=
			 * faceTemplate.getTmp()) { tmp = faceTemplate.getTmp();
			 * param.append("PIN=" + eInfoVO.getCode() + HT);
			 * param.append("FID=" + eInfoVO.getFid() + HT);
			 * param.append("Size=" + eInfoVO.getSize() + HT);
			 * param.append("Valid=" + eInfoVO.getValid() + HT);
			 * param.append("TMP=" + tmp); // param.append(LF);
			 * 
			 * list.add(param.toString()); }
			 */
		}
		return list;
	}

	/***
	 * 
	 * 说明 : 修改
	 * 
	 * @param record
	 * @return
	 * @author zhangshaung
	 * @dateTime 2017年4月24日 下午6:12:19
	 */
	@Override
	public int updateByPrimaryKeySelective(Device record) {
		return deviceDao.updateByPrimaryKey(record);
	}

	/**
	 * 
	 * 说明 : 根据设备代码sn 查询考勤点不为空的设备
	 * 
	 * @param sn
	 * @return
	 * @author zhangshaung
	 * @dateTime 2017年4月25日 下午10:05:19
	 */
	@Override
	public Device selectSignPointNotNullByDeviceSn(String sn) {
		return deviceDao.selectSignPointNotNullByDeviceSn(sn);
	}

	/**
	 * 需要到当前设备上打卡的所有员工的数量
	 * 
	 * @author zhangshuagn
	 * @param param
	 * @return
	 * @since 1.0-20170426
	 */

	@Override
	public int countEmployeeBySn(String sn) {
		Map<String, Object> param = new HashMap<String, Object>();
		if (null == sn) {
			return 0;
		}

		param.put("sn", sn);
		return deviceDao.countEmployeeBySn(param);
	}

	/**
	 * 说明 : 根据员工code,查询员工的指纹模板和面部模板数
	 * 
	 * @param code
	 * @return
	 * @author zhangshaung
	 * @dateTime 2017年4月27日 下午6:00:10
	 */
	@Override
	public int countFingerAndFaceByEmployeeCode(String code) {
		if (null == code) {
			return 0;
		}
		return deviceDao.countFingerAndFaceByEmployeeCode(code);
	}

	@Override
	public Long syncPeopleCountEmployeeBySn(String sn) {
		Map<String, Object> param = new HashMap<String, Object>();
		if (org.apache.commons.lang3.StringUtils.isEmpty(sn)) {
			return 0L;
		}

		param.put("sn", sn);
		return deviceDao.syncPeopleCountEmployeeBySn(param);
	}
}
