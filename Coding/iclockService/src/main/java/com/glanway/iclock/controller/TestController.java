package com.glanway.iclock.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.glanway.iclock.common.HttpCode;
import com.glanway.iclock.common.JsonResult;
import com.glanway.iclock.entity.vo.device.EmployeeDeviceFingerFaceVo;
import com.glanway.iclock.entity.vo.device.EmployeeDeviceInfoVO;
import com.glanway.iclock.service.sign.DeviceService;

@Controller
@RequestMapping("api/test")
public class TestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

	@Autowired
	private DeviceService deviceService;

	@ResponseBody
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public JsonResult getList(String sn) {
		JsonResult jsonResult = new JsonResult();
		try {
			List<EmployeeDeviceInfoVO> list = deviceService.findEmployeeBySn(sn);
			jsonResult.setData(list);
		} catch (Exception e) {
			jsonResult.setMsg("操作失败");
			jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
			LOGGER.info("查询异常信息为: {}", e.getMessage());
		}

		return jsonResult;
	}

	@ResponseBody
	@RequestMapping(value = "faceList", method = RequestMethod.GET)
	public JsonResult getList(String sn, Integer type) {
		JsonResult jsonResult = new JsonResult();
		try {
			List<EmployeeDeviceFingerFaceVo> list = deviceService.findEmployeeFingerFaceBySn(sn, type);

			jsonResult.setData(list);
		} catch (Exception e) {
			jsonResult.setMsg("操作失败");
			jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
			LOGGER.info("查询异常信息为: {}", e.getMessage());
		}

		return jsonResult;
	}

	@ResponseBody
	@RequestMapping(value = "count", method = RequestMethod.GET)
	public JsonResult getCount(String sn) {
		JsonResult jsonResult = new JsonResult();
		try {
			Long sync = deviceService.syncPeopleCountEmployeeBySn(sn);
			int count = deviceService.countEmployeeBySn(sn);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("count", count);
			map.put("sync", sync);
			jsonResult.setDataMap(map);
		} catch (Exception e) {
			jsonResult.setMsg("操作失败");
			jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
			LOGGER.info("查询异常信息为: {}", e.getMessage());
		}

		return jsonResult;
	}
}
