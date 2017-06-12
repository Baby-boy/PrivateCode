package com.glanway.ctrlhr.service.device.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glanway.ctrlhr.common.HttpCode;
import com.glanway.ctrlhr.common.JsonResult;
import com.glanway.ctrlhr.common.Page;
import com.glanway.ctrlhr.dao.device.DeviceDao;
import com.glanway.ctrlhr.entity.device.Device;
import com.glanway.ctrlhr.entity.para.DevicePara;
import com.glanway.ctrlhr.entity.para.KeywordPara;
import com.glanway.ctrlhr.entity.vo.DeviceVo;
import com.glanway.ctrlhr.entity.vo.SimpleDeviceVo;
import com.glanway.ctrlhr.service.BaseServiceImpl;
import com.glanway.ctrlhr.service.device.DeviceService;
import com.glanway.ctrlhr.service.server.IClockServerService;

@Transactional
@Service("deviceService")
public class DeviceServiceImpl extends BaseServiceImpl<Device> implements DeviceService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DeviceServiceImpl.class);

	@Autowired
	private DeviceDao deviceDao;

	@Autowired
	private IClockServerService iClockServerService;

	@Override
	public Page<DeviceVo> findList(DevicePara para) {

		int count = deviceDao.findListCount(para);
		Page<DeviceVo> page = new Page<>(para.getPage(), count, para.getPageSize());
		if (para.getPage() <= page.getCountPage()) {
			para.setStartIndex(page.getStartIndex());
			List<DeviceVo> list = deviceDao.findList(para);
			page.setList(list);
		}

		return page;
	}

	@Override
	public void saveDevice(Device device) {
		Device dev = deviceDao.findDeviceBySn(device.getSn());
		if (null != dev) {
			throw new RuntimeException();
		}

		device.setId(null);
		device.setBatchDate(new Date());
		device.setCreatedBy(1l);
		device.setCreatedDate(new Date());
		device.setCreProId(1l);
		device.setModProId(1l);
		device.setLastModifiedBy(1l);
		device.setLastModifiedDate(device.getCreatedDate());
		device.setSignPointId(device.getSignPointId());

		deviceDao.insertSelective(device);

	}

	@Override
	public DeviceVo getInfo(Long id) {
		return deviceDao.queryOneDevice(id);
	}

	@Override
	public void updateDevice(Device device) {
		// TODO 最后更新人ID写死,后期需要更改
		device.setLastModifiedBy(1L);
		device.setLastModifiedDate(new Date());
		// TODO 更新程序ID写死,后期需要更改
		device.setModProId(1L);

		device.setState(2);

		deviceDao.updateByPrimaryKeySelective(device);
	}

	@Override
	public JsonResult delete(String ids) {

		JsonResult result = new JsonResult();
		// 数据处理
		String[] idArr = StringUtils.split(ids, ",");
		// 删除要查询考群设备
		for (String id : idArr) {
			List<Device> deviceList = deviceDao.queryDevice(id);
			if (deviceList != null) {
				for (Device device : deviceList) {
					if (device.getState() != 1 && device.getSignPointId() != null) {
						result.setCode(HttpCode.FORBIDDEN);
						result.setMsg("考群设备在使用,不可删除!");
						return result;
					}
					// 此处为逻辑删除,更新删除字段为1
					deviceDao.delete(id);
				}
			} else {
				result.setCode(HttpCode.FORBIDDEN);
				result.setMsg("参数有误");
				return result;
			}
		}
		return result;
	}

	@Override
	public void update(Long id, String[] deviceIdArr) {
		Device device = new Device();

		for (String deviceIdStr : deviceIdArr) {
			device.setId(Long.valueOf(deviceIdStr));
			device.setSignPointId(id);
			device.setState(2);// 状态设置为已使用
			// TODO 最后更新人ID写死,后期需要更改
			device.setLastModifiedBy(1L);
			device.setLastModifiedDate(new Date());
			// TODO 更新程序ID写死,后期需要更改
			device.setModProId(1L);

			// 绑定考勤点后,同时需要更新状态为已使用
			deviceDao.updateByPrimaryKeySelective(device);
		}

	}

	@Override
	public List<Device> findDevice(Long signPointId) {
		return deviceDao.findMany(signPointId);
	}

	@Override
	public void updateDevice(Long id, String[] deviceIdArr) {
		// 重新绑定为绑定考勤点的的设备 需要 -- 先绑定该设备和考勤点 再更新原有设备的状态和 考勤点数据为空
		// TODO 以下代码可以通过一条sql进行优化(后期可以修改)
		// 通过考勤点的id查询已经绑定的设备ID
		List<Device> deviceList = deviceDao.findMany(id);
		if (null != deviceList && deviceList.size() > 0) {
			for (Device device : deviceList) {
				try {
					// 在清除考勤点之前,清除该台考勤机上的所有人员信息
					iClockServerService.cleanUserInfoBySn(device.getSn());
				} catch (Exception e) {
					LOGGER.info("调用IClock系统异常信息为: {}", e.getMessage());
				}
				device.setSignPointId(null);
				device.setState(1);// 状态设置为未使用
				device.setTotalPeople(0L);// 设备恢复未使用状态,对应的总人数应该剔除
				device.setUnsyncPeople(0L);// // 设备恢复未使用状态,对应的未同步人数应该剔除
				device.setSyncState(1);// 设置同步状态为未同步
				// TODO 最后更新人ID写死,后期需要更改
				device.setLastModifiedBy(1L);
				device.setLastModifiedDate(new Date());
				// TODO 更新程序ID写死,后期需要更改
				device.setModProId(1L);
				// 绑定考勤点后,同时需要更新状态为未使用
				deviceDao.updateByPrimaryKey(device);
			}
		}

		if (null != deviceIdArr) {
			// 绑定后更新数据
			update(id, deviceIdArr);
		}
	}

	@Override
	public List<SimpleDeviceVo> findsimpleList(KeywordPara para) {
		return deviceDao.findsimpleList(para);
	}

	@Override
	public List<SimpleDeviceVo> findDropDownList(KeywordPara para) {
		return deviceDao.findDropDownList(para);
	}

}
