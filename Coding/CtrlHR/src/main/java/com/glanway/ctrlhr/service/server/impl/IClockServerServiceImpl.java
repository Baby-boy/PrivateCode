package com.glanway.ctrlhr.service.server.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glanway.ctrlhr.dao.device.DeviceDao;
import com.glanway.ctrlhr.entity.device.Device;
import com.glanway.ctrlhr.service.server.IClockServerService;
import com.glanway.ctrlhr.util.HttpClientUtil;

@Transactional
@Service("iClockServerService")
public class IClockServerServiceImpl implements IClockServerService {

	private String URL;

	@Value("${httpclient.request.url}")
	private void setURL(String url) {
		URL = url;
	}

	@Autowired
	private DeviceDao deviceDao;

	@Override
	public void syncUserInfoByDeviceId(String ids) throws Exception {
		List<Device> deviceList = new ArrayList<>();
		if (StringUtils.isEmpty(ids)) {
			// 查询所有可用未同步的设备序列号
			deviceList = deviceDao.findAllSn();
		} else {
			String[] idArr = StringUtils.split(ids, ",");
			deviceList = deviceDao.findSnById(idArr);
		}

		if (null != deviceList && deviceList.size() > 0) {
			StringBuffer buffer = new StringBuffer();
			for (Device device : deviceList) {
				buffer.append(device.getSn()).append(",");
			}
			String sns = buffer.deleteCharAt(buffer.length() - 1).toString();

			// 调用IClock系统
			String syncUrl = URL + "/api/task/updateUserInfo?sns=" + sns;
			HttpClientUtil.doGet(syncUrl);
		}
	}

	@Override
	public void syncUserInfoByDeviceSn(String sns) throws Exception {
		if (StringUtils.isEmpty(sns)) {
			// 查询所有可用未同步的设备序列号
			List<Device> deviceList = deviceDao.findAllSn();

			if (null != deviceList && deviceList.size() > 0) {
				StringBuffer buffer = new StringBuffer();
				for (Device device : deviceList) {
					buffer.append(device.getSn()).append(",");
				}
				sns = buffer.deleteCharAt(buffer.length() - 1).toString();
			} else {
				return;// 如果没有sns也就不需要调用系统了
			}

		}

		// 调用IClock系统
		String syncUrl = URL + "/api/task/updateUserInfo?sns=" + sns;
		HttpClientUtil.doGet(syncUrl);
	}

	@Override
	public void syncUserInfoByAllDevice() throws Exception {
		// 调用IClock系统
		String syncUrl = URL + "/api/task/updateAll";
		HttpClientUtil.doGet(syncUrl);
	}

	@Override
	public void cleanAll(String sns) throws Exception {
		// TODO 做一些别的操作
		// 调用IClock系统
		String syncUrl = URL + "/api/task/clearAll?sns=" + sns;
		HttpClientUtil.doGet(syncUrl);
	}

	@Override
	public void cleanUserInfoBySn(String sns) throws Exception {
		// TODO 做一些别的操作
		// 调用IClock系统
		String syncUrl = URL + "/api/task/clearUserInfo?sns=" + sns;
		HttpClientUtil.doGet(syncUrl);
	}

	@Override
	public void cleanLog(String sns) throws Exception {
		// TODO 做一些别的操作
		// 调用IClock系统
		String syncUrl = URL + "/api/task/clearLog?sns=" + sns;
		HttpClientUtil.doGet(syncUrl);
	}

}
