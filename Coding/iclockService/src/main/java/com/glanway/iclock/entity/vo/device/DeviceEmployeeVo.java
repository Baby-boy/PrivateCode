/**
 * @author zhangshuang
 * 2017年4月21日 上午10:41:24
 */
package com.glanway.iclock.entity.vo.device;

import java.util.List;

import org.quartz.utils.StringKeyDirtyFlagMap;

/**
 * 说明 : 
 * 设备--员工
 * 属于设备 的员工
 * @author zhangshaung
 * @version 1.0.0
 * @dateTime 2017年4月21日 上午10:41:24
 */
public class DeviceEmployeeVo {
	
	/**
	 * 设备ID
	 */
	private Long id;
	
	/**
	 * 设备代码
	 */
	private String sn;
	
	/**
	 * 设备名称
	 */
	private String name;
	
	/**
	 * 
	 */
	private List<EmployeeDeviceInfoVO> employeeDeviceInfoList;
	
	
	
	
}
