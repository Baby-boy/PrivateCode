package com.glanway.iclock.service.sign;

import java.util.List;

import com.glanway.iclock.entity.sign.Device;
import com.glanway.iclock.entity.vo.device.EmployeeDeviceFingerFaceVo;
import com.glanway.iclock.entity.vo.device.EmployeeDeviceInfoVO;
import com.glanway.iclock.service.BaseService;

public interface DeviceService extends BaseService<Device> {
	public static final String HT = "\t"; // 设备中制表符
	public static final String LF = "\n"; // 换行符

	/** 新增考勤设备 */
	void saveDevice(Device device);

	/***
	 * 说明 : 修改
	 * 
	 * @param record
	 * @return
	 * @author zhangshaung
	 * @dateTime 2017年4月24日 下午6:12:19
	 */
	int updateByPrimaryKeySelective(Device record);

	/**
	 * 说明 : 根据设备代码sn 查询设备信息
	 * 
	 * @param sn
	 * @return
	 * @author zhangshaung
	 * @dateTime 2017年4月20日 下午2:03:25
	 */
	Device selectByDeviceSn(String sn);

	/**
	 * 说明 : 根据设备代码sn 查询考勤点不为空的设备
	 * 
	 * @param sn
	 * @return
	 * @author zhangshaung
	 * @dateTime 2017年4月25日 下午10:05:19
	 */
	Device selectSignPointNotNullByDeviceSn(String sn);

	/**
	 * 说明 : 取数据库中根据已连通设备最后一次连接时间超过5分钟的设备
	 * 
	 * @return
	 * @author zhangshaung
	 * @dateTime 2017年4月20日 下午6:05:16
	 */
	List<Device> findLastConnectionTimeExcendFiveMinute();

	/**
	 * 说明 : 根据ID修改设备的状态
	 * 
	 * @param id
	 * @param state
	 * @author zhangshaung
	 * @dateTime 2017年4月20日 下午6:18:12
	 */
	void updateStateById(Long id, Integer state);

	/**
	 * 说明 : 根据设备代码SN修改设备的状态
	 * 
	 * @param sn
	 * @param state
	 * @author zhangshaung
	 * @dateTime 2017年4月20日 下午6:19:11
	 */
	void updateStateBySn(String sn, Integer state);

	/**
	 * 说明 : 根据设备sn 得到更新或新增员工基本信息的命令数据
	 * 
	 * @param sn
	 * @return
	 * @author zhangshaung
	 * @dateTime 2017年4月21日 下午4:14:57
	 */
	List<String> updateUserInfoDataBySn(String sn);

	/**
	 * 说明 : 根据设备sn 得到更新或新增员工头像的命令数据
	 * 
	 * @param sn
	 * @return
	 * @author zhangshaung
	 * @dateTime 2017年4月21日 下午4:14:57
	 */
	List<String> updateUserPhoneDataBySn(String sn);

	/**
	 * 说明 : 根据设备sn 查询 需要到当前设备上打卡的所有员工信息
	 * 
	 * @param sn
	 * @return
	 * @author zhangshaung
	 * @dateTime 2017年4月21日 下午4:11:17
	 */
	List<EmployeeDeviceInfoVO> findEmployeeBySn(String sn);

	/**
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
	List<EmployeeDeviceFingerFaceVo> findEmployeeFingerFaceBySn(String sn, Integer type);

	/**
	 * 说明 : 根据设备sn 得到更新或新增员工指纹模板的命令数据
	 * 
	 * @param sn
	 * @return
	 * @author zhangshaung
	 * @dateTime 2017年4月21日 下午7:30:45
	 */
	List<String> updateUserFingerTmpDataBySn(String sn);

	/**
	 * 说明 : 根据设备sn 得到更新或新增员工面部模板的命令数据
	 * 
	 * @param sn
	 * @return
	 * @author zhangshaung
	 * @dateTime 2017年4月21日 下午7:30:45
	 */
	List<String> updateUserFaceTmpDataBySn(String sn);

	/**
	 * TODO - 2017-04-26 glanwayuser11 complete JavaDoc. 根据设备sn 查询
	 * 需要到当前设备上打卡的所有员工的数量
	 * 
	 * @author zhangshuagn
	 * @param sn
	 * @return
	 * @since 1.0-20170426
	 */
	int countEmployeeBySn(String sn);

	/**
	 * 说明 : 根据员工code,查询员工的指纹模板和面部模板数
	 * 
	 * @param code
	 * @return
	 * @author zhangshaung
	 * @dateTime 2017年4月27日 下午6:00:10
	 */
	int countFingerAndFaceByEmployeeCode(String code);

	/**
	 * 说明 : 根据考勤机sn获取考勤机上已经录入的指纹数
	 * 
	 * @param sn
	 * @return
	 * @author fuqihao
	 * @dateTime 2017年6月5日 下午1:52:20
	 */
	Long syncPeopleCountEmployeeBySn(String sn);
}
