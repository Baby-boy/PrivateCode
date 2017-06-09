package com.glanway.ctrlhr.controller.device;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.glanway.ctrlhr.common.HttpCode;
import com.glanway.ctrlhr.common.JsonResult;
import com.glanway.ctrlhr.common.Page;
import com.glanway.ctrlhr.entity.device.Device;
import com.glanway.ctrlhr.entity.para.DevicePara;
import com.glanway.ctrlhr.entity.para.KeywordPara;
import com.glanway.ctrlhr.entity.vo.DeviceVo;
import com.glanway.ctrlhr.entity.vo.SimpleDeviceVo;
import com.glanway.ctrlhr.service.device.DeviceService;
import com.glanway.ctrlhr.service.server.IClockServerService;

/**
 * 说明 : 设备管理相关
 *
 * @author 高伟南
 * @version 1.0.0
 * @dateTime 2017年4月20日 上午11:27:08
 */
@Controller
@RequestMapping("api/device")
public class DeviceController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DeviceController.class);

	@Autowired
	private DeviceService deviceService;

	@Autowired
	private IClockServerService iClockServerService;

	/**
	 * 说明 : 查询设备列表
	 *
	 * @param para
	 * @return
	 * @author 高伟南
	 * @dateTime 2017年4月20日 上午11:06:14
	 */
	@ResponseBody
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public JsonResult list(DevicePara para) {
		JsonResult jsonResult = new JsonResult();

		try {
			Page<DeviceVo> page = deviceService.findList(para);
			jsonResult.setData(page);
		} catch (Exception e) {
			LOGGER.info("查询设备列表时异常信息为: {}", e.getMessage());
			jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
			jsonResult.setMsg("操作失败!");
		}

		return jsonResult;
	}

	/**
	 * 说明 : 新增设备
	 * 
	 * @param device
	 * @return
	 * @author 高伟南
	 * @dateTime 2017年4月20日 下午2:17:32
	 */
	@ResponseBody
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public JsonResult addDevice(Device device) {
		JsonResult jsonResult = new JsonResult();

		if (StringUtils.isEmpty(device.getName())) {
			jsonResult.setCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("设备名称不能为空");
			return jsonResult;
		}

		if (StringUtils.isEmpty(device.getSn())) {
			jsonResult.setCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("设备序列号不能为空");
			return jsonResult;
		}

		try {
			deviceService.saveDevice(device);
		} catch (RuntimeException e) {
			LOGGER.info("添加设备时异常信息为: {}", e.getMessage());
			jsonResult.setMsg("设备已存在!");
			jsonResult.setCode(HttpCode.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setMsg("操作失败!");
			jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
		}

		return jsonResult;
	}

	/**
	 * 说明 :根据id获取考勤设备的信息
	 *
	 * @param id
	 * @return
	 * @author 高伟南
	 * @dateTime 2017年4月20日 下午2:46:38
	 */
	@ResponseBody
	@RequestMapping(value = "getInfo", method = RequestMethod.GET)
	public JsonResult getInfo(Long id) {
		JsonResult jsonResult = new JsonResult();

		try {
			DeviceVo deviceVo = deviceService.getInfo(id);
			jsonResult.setData(deviceVo);
		} catch (Exception e) {
			LOGGER.info("获取设备信息时异常信息为: {}", e.getMessage());
			jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
			jsonResult.setMsg("操作失败!");
		}

		return jsonResult;
	}

	/**
	 * 说明 : 更新考勤设备信息
	 *
	 * @param device
	 * @return
	 * @author 高伟南
	 * @dateTime 2017年4月20日 下午3:34:31
	 */
	@ResponseBody
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public JsonResult update(Device device) {
		JsonResult jsonResult = new JsonResult();

		if (StringUtils.isEmpty(device.getName())) {
			jsonResult.setCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("考勤设备名称不能为空!");
			return jsonResult;
		}
		if (StringUtils.isEmpty(device.getSn())) {
			jsonResult.setCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("考勤序列号不能为空!");
			return jsonResult;
		}

		try {
			deviceService.updateDevice(device);

			// 更新完设备信息后,调用IClock系统
			iClockServerService.syncUserInfoByDeviceSn(device.getSn());
		} catch (Exception e) {
			LOGGER.info("更新设备信息时异常信息为: {}", e.getMessage());
			jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
			jsonResult.setMsg("操作失败!");
		}

		return jsonResult;
	}

	/**
	 * 快速添加考勤点.
	 *
	 * @author fuqihao
	 * @param device
	 * @return
	 * @since 1.0-20170526
	 */
	@ResponseBody
	@RequestMapping(value = "quickUpdate", method = RequestMethod.POST)
	public JsonResult quickUpdate(Device device) {
		JsonResult jsonResult = new JsonResult();

		if (null == device.getSignPointId()) {
			jsonResult.setCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("未选中任何考勤点!");
			return jsonResult;
		}

		try {
			deviceService.updateDevice(device);

			// 快速更新考勤点后,调用IClock系统
			iClockServerService.syncUserInfoByDeviceId(device.getId().toString());
		} catch (Exception e) {
			LOGGER.info("快速更新设备信息时异常信息为: {}", e.getMessage());
			jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
			jsonResult.setMsg("操作失败!");
		}

		return jsonResult;
	}

	/**
	 * 说明 : 删除设备信息
	 *
	 * @param ids
	 * @return
	 * @author 高伟南
	 * @dateTime 2017年4月20日 上午11:27:22
	 */
	@ResponseBody
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public JsonResult delete(String ids) {
		JsonResult jsonResult = new JsonResult();

		if (StringUtils.isEmpty(ids)) {
			jsonResult.setCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("参数有误!");
			return jsonResult;
		}

		try {
			JsonResult result = deviceService.delete(ids);
			return result;
		} catch (Exception e) {
			LOGGER.info("删除设备时异常信息为: {}", e.getMessage());
			jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
			jsonResult.setMsg("操作失败!");
		}
		return jsonResult;
	}

	/**
	 * 说明 : 查询设备(精简)列表(备注: 该接口是提供个考勤点添加设备时使用,该设备是非异常的状态)
	 * 
	 * @return
	 * @author 付其浩
	 * @dateTime 2017年4月21日 上午9:17:46
	 */
	@ResponseBody
	@RequestMapping(value = "simpleList", method = RequestMethod.GET)
	public JsonResult simpleList(KeywordPara para) {
		JsonResult jsonResult = new JsonResult();

		try {
			List<SimpleDeviceVo> simpleList = deviceService.findsimpleList(para);
			jsonResult.setData(simpleList);
		} catch (Exception e) {
			LOGGER.info("查询设备精简列表时异常信息为: {}", e.getMessage());
			jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
			jsonResult.setMsg("操作失败!");
		}

		return jsonResult;
	}

	/**
	 * 说明 : 查询所有设备精简列表(正常连接的设备)
	 * 
	 * @param para
	 * @return
	 * @author fuqihao
	 * @dateTime 2017年6月7日 下午5:42:22
	 */
	@ResponseBody
	@RequestMapping(value = "dropDownList", method = RequestMethod.GET)
	public JsonResult dropDownList(KeywordPara para) {
		JsonResult jsonResult = new JsonResult();

		try {
			List<SimpleDeviceVo> dropDownList = deviceService.findDropDownList(para);
			jsonResult.setData(dropDownList);
		} catch (Exception e) {
			LOGGER.info("查询设备精简列表时异常信息为: {}", e.getMessage());
			jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
			jsonResult.setMsg("操作失败!");
		}

		return jsonResult;
	}

	/**
	 * 说明 : 根据设备ID同步数据
	 * 
	 * @param ids
	 * @return
	 * @author 付其浩
	 * @dateTime 2017年4月24日 下午6:24:16
	 */
	@ResponseBody
	@RequestMapping(value = "sync", method = RequestMethod.POST)
	public JsonResult sync(String ids) {
		JsonResult jsonResult = new JsonResult();

		try {
			iClockServerService.syncUserInfoByDeviceId(ids);
		} catch (Exception e) {
			LOGGER.info("同步信息时异常信息为: {}", e.getMessage());
			jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
			jsonResult.setMsg("操作失败!");
		}

		return jsonResult;
	}

}
