package com.glanway.ctrlhr.dao.device;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.glanway.ctrlhr.dao.BaseDao;
import com.glanway.ctrlhr.entity.device.Device;
import com.glanway.ctrlhr.entity.para.DevicePara;
import com.glanway.ctrlhr.entity.para.KeywordPara;
import com.glanway.ctrlhr.entity.vo.DeviceVo;
import com.glanway.ctrlhr.entity.vo.SimpleDeviceVo;

public interface DeviceDao extends BaseDao<Device> {

	public int deleteByPrimaryKey(Long id);

	public int insert(Device record);

	public int insertSelective(Device record);

	public Device selectByPrimaryKey(Long id);

	public int updateByPrimaryKeySelective(Device record);

	public int updateByPrimaryKey(Device record);

	/** 查询设备列表总数 */
	public int findListCount(@Param("para") DevicePara para);

	/** 查询设备列表 */
	public List<DeviceVo> findList(@Param("para") DevicePara para);

	/** 更新未绑定考勤点的考勤机的数据 */
	public void update(@Param("signPointId") Long id, @Param("deviceIdArr") String[] deviceIdArr);

	/** 根据考勤点ID查询设备 */
	public List<Device> findMany(@Param("signPointId") Long signPointId);

	/** 新增考勤设备 */
	public void saveDevice(Device device);

	/** 查询设备(精简)列表 */
	public List<SimpleDeviceVo> findsimpleList(@Param("para") KeywordPara para);

	/** 根据id查询考勤设备(删除时) */
	public List<Device> queryDevice(String id);

	/** 此处为逻辑删除 */
	public void delete(String id);

	/** 根据id获取考勤设备的信息 */
	public DeviceVo queryOneDevice(@Param("id") Long id);

	/** 根据设备序列号查询设备是否已经存在 */
	public Device findDeviceBySn(@Param("sn") String sn);

	/** 根据设备ID同步数据 */
	public List<Device> findSnById(@Param("idArr") String[] idArr);

	/** 查询所有可用未同步的设备序列号 */
	public List<Device> findAllSn();

}