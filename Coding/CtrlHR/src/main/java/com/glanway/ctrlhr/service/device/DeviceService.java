package com.glanway.ctrlhr.service.device;

import java.util.List;

import com.glanway.ctrlhr.common.JsonResult;
import com.glanway.ctrlhr.common.Page;
import com.glanway.ctrlhr.entity.device.Device;
import com.glanway.ctrlhr.entity.para.DevicePara;
import com.glanway.ctrlhr.entity.para.KeywordPara;
import com.glanway.ctrlhr.entity.vo.DeviceVo;
import com.glanway.ctrlhr.entity.vo.SimpleDeviceVo;
import com.glanway.ctrlhr.service.BaseService;

/**
 * 说明 : 
 *
 * @author 高伟南
 * @version 1.0.0
 * @dateTime 2017年4月20日 上午11:44:31
 */
/**
 * 说明 : 
 *
 * @author 高伟南
 * @version 1.0.0
 * @dateTime 2017年4月20日 上午11:44:34
 */
/**
 * 说明 : 
 *
 * @author 高伟南
 * @version 1.0.0
 * @dateTime 2017年4月20日 上午11:44:36
 */
/**
 * 说明 : 
 *
 * @author 高伟南
 * @version 1.0.0
 * @dateTime 2017年4月20日 上午11:44:36
 */
/**
 * 说明 :
 *
 * @author 高伟南
 * @version 1.0.0
 * @dateTime 2017年4月20日 上午11:44:37
 */
public interface DeviceService extends BaseService<Device> {

	/**
	 * 说明 : 设备列表查询
	 * 
	 * @param para
	 * @return
	 * @author 高伟南
	 * @dateTime 2017年4月20日 下午1:46:38
	 */
	public Page<DeviceVo> findList(DevicePara para);

	/**
	 * 说明 : 新增考勤设备
	 * 
	 * @param device
	 * @author 付其浩
	 * @dateTime 2017年4月20日 下午2:30:38
	 */
	public void saveDevice(Device device);

	/**
	 * 说明 :根据id获取考勤设备的信息
	 *
	 * @param id
	 * @return
	 * @author 高伟南
	 * @dateTime 2017年4月20日 下午2:46:38
	 */
	public DeviceVo getInfo(Long id);

	/**
	 * 说明 : 更新考勤设备信息
	 *
	 * @param device
	 * @return
	 * @author 高伟南
	 * @dateTime 2017年4月20日 下午3:34:31
	 */
	public void updateDevice(Device device);

	/**
	 * 说明 : 删除考勤设备
	 *
	 * @param ids
	 * @return
	 * @author 高伟南
	 * @dateTime 2017年4月20日 上午11:27:22
	 */
	public JsonResult delete(String ids);

	/**
	 * 说明 : 更新未绑定考勤点的考勤机的数据
	 * 
	 * @param id
	 * @param deviceIdArr
	 * @author 付其浩
	 * @dateTime 2017年4月19日 下午5:01:17
	 */
	public void update(Long id, String[] deviceIdArr);

	/**
	 * 说明 : 根据考勤点ID查询设备
	 * 
	 * @param signPointId
	 * @author 付其浩
	 * @dateTime 2017年4月19日 下午7:02:23
	 */
	public List<Device> findDevice(Long signPointId);

	/**
	 * 说明 : 根据考勤点ID更换设备的考勤点
	 * 
	 * @param id
	 * @param deviceIdArr
	 * @author 付其浩
	 * @dateTime 2017年4月19日 下午8:09:04
	 */
	public void updateDevice(Long id, String[] deviceIdArr);

	/**
	 * 说明 : 查询设备(精简)列表
	 * 
	 * @return
	 * @author 付其浩
	 * @dateTime 2017年4月21日 上午9:20:40
	 */
	public List<SimpleDeviceVo> findsimpleList(KeywordPara para);

	/**
	 * 说明 : 根据设备ID同步数据
	 * 
	 * @param ids
	 * @author 付其浩
	 * @dateTime 2017年4月24日 下午6:24:51
	 */
	public void findSnById(String ids) throws Exception;

}
