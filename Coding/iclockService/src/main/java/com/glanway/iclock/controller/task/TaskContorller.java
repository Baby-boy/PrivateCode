/**
 * @author zhangshuang
 * 2017年4月22日 下午2:32:30
 */
package com.glanway.iclock.controller.task;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.glanway.iclock.common.CommandWrapper;
import com.glanway.iclock.common.HttpCode;
import com.glanway.iclock.common.JsonResult;
import com.glanway.iclock.entity.sign.Device;
import com.glanway.iclock.entity.task.Task;
import com.glanway.iclock.service.sign.DeviceService;
import com.glanway.iclock.service.task.TaskService;

/**
 * 说明 :
 * 
 * @author zhangshaung
 * @version 1.0.0
 * @dateTime 2017年4月22日 下午2:32:30
 */
@Controller
@RequestMapping("api/task")
public class TaskContorller {

	@Autowired
	private DeviceService deviceService;

	@Autowired
	private TaskService taskService;

	/**
	 * 说明 : 清除考勤机上的考勤记录
	 * 
	 * @param sns
	 * @return
	 * @author fuqihao
	 * @dateTime 2017年6月7日 下午1:04:56
	 */
	@ResponseBody
	@RequestMapping("clearLog")
	public JsonResult cleanLog(String sns) {
		JsonResult jsonResult = new JsonResult();

		if (org.apache.commons.lang3.StringUtils.isEmpty(sns)) {
			jsonResult.setCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("考勤机序列号(sn)不能为空!");
			return jsonResult;
		}

		String[] snArray = org.apache.commons.lang3.StringUtils.split(sns, ",");
		for (String sn : snArray) {
			/****** 清除所有员工信息 *****/
			clearLog(sn);
		}

		jsonResult.setLoginState(3);
		return jsonResult;
	}

	/**
	 * 说明 : 清除指定设备上的员工信息
	 * 
	 * @param sns
	 * @return
	 * @author fuqihao
	 * @dateTime 2017年6月7日 上午11:52:21
	 */
	@ResponseBody
	@RequestMapping("clearUserInfo")
	public JsonResult cleanUserInfo(String sns) {
		JsonResult jsonResult = new JsonResult();

		if (org.apache.commons.lang3.StringUtils.isEmpty(sns)) {
			jsonResult.setCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("考勤机序列号(sn)不能为空!");
			return jsonResult;
		}

		String[] snArray = org.apache.commons.lang3.StringUtils.split(sns, ",");
		for (String sn : snArray) {
			/****** 清除所有员工信息 *****/
			clearUserInfo(sn);
		}

		jsonResult.setLoginState(3);
		return jsonResult;
	}

	/**
	 * 说明 : HR系统调用 给设备下达上传考勤记录的命令的接口
	 * 
	 * @param sn(设备代码)
	 * @param startTime
	 *            格式:yyyy-MM-dd HH:mm:ss
	 * @param endTime
	 *            格式:yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 * @author zhangshaung
	 * @dateTime 2017年4月22日 下午2:40:49
	 */
	@RequestMapping("queryLog")
	@ResponseBody
	public JsonResult queryLog(String sns, String startTime, String endTime) {
		JsonResult jsonResult = new JsonResult();
		if (null == sns || null == startTime || null == endTime) {
			jsonResult.setCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("参数异常");
			return jsonResult;
		}
		String[] arraySn = sns.split(",");
		for (String sn : arraySn) {

			Task task = new Task();

			task.setSn(sn);
			task.setState(1);
			task.setCommand(CommandWrapper.CMD_DATA_QUERY_ATTLOG);
			StringBuilder param = new StringBuilder();
			if (StringUtils.hasText(startTime)) {
				param.append("StartTime").append(startTime).append("\t");
			}
			if (StringUtils.hasText(endTime)) {
				param.append("EndTime").append(endTime).append("\t");
			}
			task.setArgs(param.toString());
			task.setCreatedDate(new Date());
			task.setDeleted("0");
			taskService.save(task);
		}
		jsonResult.setLoginState(3);
		return jsonResult;
	}

	/**
	 * 说明 : HR系统调用 给设备下达修改/新增用户信息的接口
	 * 
	 * @param sns
	 * @return
	 * @author zhangshaung
	 * @dateTime 2017年4月22日 下午2:40:49
	 */
	@RequestMapping("test")
	@ResponseBody
	public JsonResult test(String sn) {
		JsonResult jsonResult = new JsonResult();
		int totalPeople = deviceService.countEmployeeBySn(sn);

		jsonResult.setMsg("totalPeople==" + totalPeople);
		jsonResult.setLoginState(3);
		return jsonResult;
	}

	/**
	 * 说明 : HR系统调用 给设备下达修改/新增用户信息的接口
	 * 
	 * @param sns
	 * @return
	 * @author zhangshaung
	 * @dateTime 2017年4月22日 下午2:40:49
	 */
	@RequestMapping("clearAll")
	@ResponseBody
	public JsonResult cleanAll(String sns) {
		JsonResult jsonResult = new JsonResult();

		if (org.apache.commons.lang3.StringUtils.isEmpty(sns)) {
			jsonResult.setCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("考勤机代码(sn)不能为空!");
			return jsonResult;
		}
		String[] snArray = org.apache.commons.lang3.StringUtils.split(sns, ",");
		for (String sn : snArray) {
			/****** 清除所有信息 *****/
			clearAll(sn);
		}

		// Map<String, Object> params = new HashMap<String, Object>();
		// params.put("state", 2);
		// List<Device> list = deviceService.findMany(params);

		jsonResult.setLoginState(3);
		return jsonResult;
	}

	/**
	 * 说明 : HR系统调用 给设备下达修改/新增用户信息的接口
	 * 
	 * @param sns
	 * @return
	 * @author zhangshaung
	 * @dateTime 2017年4月22日 下午2:40:49
	 */
	@RequestMapping("updateAll")
	@ResponseBody
	public JsonResult updateAllDevice() {
		JsonResult jsonResult = new JsonResult();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("state", 2);
		List<Device> list = deviceService.findMany(params);

		for (Device device : list) {

			/****** 清除所有员工信息 *****/
			clearUserInfo(device.getSn());
			/***** 同步员工基本信息 ******/
			syncUserInfo(device.getSn());

			/***** 同步员工头像信息 ******/
			syncUserPhoto(device.getSn());

			/***** 同步员工指纹模板信息 ******/
			syncUserFinger(device.getSn());
			/***** 同步员工面部模板信息 ******/
			syncUserFace(device.getSn());

			device.setSyncState(2);// 同步中
			deviceService.updateByPrimaryKeySelective(device);

		}
		jsonResult.setLoginState(3);
		return jsonResult;
	}

	/**
	 * 说明 : HR系统调用 给设备下达修改/新增用户信息的接口
	 * 
	 * @param sns
	 * @return
	 * @author zhangshaung
	 * @dateTime 2017年4月22日 下午2:40:49
	 */
	@RequestMapping("updateUserInfo")
	@ResponseBody
	public JsonResult updateUerInfo(@RequestParam(value = "sns") String sns) {
		JsonResult jsonResult = new JsonResult();
		if (null == sns || "".equals(sns)) {
			jsonResult.setCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("考勤机代码(sn)不能为空!");
			return jsonResult;
		}
		String[] snArray = sns.split(",");

		for (String sn : snArray) {

			/****** 清除所有员工信息 *****/
			clearUserInfo(sn);
			/***** 同步员工基本信息 ******/
			syncUserInfo(sn);

			/***** 同步员工头像信息 ******/
			syncUserPhoto(sn);

			/***** 同步员工指纹模板信息 ******/
			syncUserFinger(sn);
			/***** 同步员工面部模板信息 ******/
			syncUserFace(sn);

			Device device = deviceService.selectByDeviceSn(sn);
			device.setSyncState(2);// 同步中
			deviceService.updateByPrimaryKeySelective(device);

		}
		jsonResult.setLoginState(3);
		return jsonResult;
	}

	/**
	 * 说明 : 同步员工面部模板信息
	 * 
	 * @param sn
	 * @author zhangshaung
	 * @dateTime 2017年4月25日 下午5:54:26
	 */
	public void syncUserFace(String sn) {

		Task task = new Task();
		task.setId(null);
		task.setSn(sn);
		task.setState(1);
		/**
		 * 将args[] 转换成String类型,中间用""隔开
		 */
		List<String> paramList = deviceService.updateUserFaceTmpDataBySn(sn);
		int count = 0;
		if (null != paramList) {
			for (String param : paramList) {
				task.setArgs(param);
				task.setCommand(CommandWrapper.CMD_DATA_UPDATE_FACE);
				task.setCreatedDate(new Date());
				task.setDeleted("0");

				taskService.save(task);
				count++;
				// 下载重启命令

			}
			if (count > 0) {
				Task task1 = new Task();
				task1.setId(null);
				task1.setSn(sn);
				task1.setState(1);
				task1.setCommand("C:R-001:REBOOT");
				task1.setArgs("");
				task1.setCreatedDate(new Date());
				task1.setDeleted("0");
				taskService.save(task1);
			}

		}
	}

	/**
	 * 说明 : 同步员工指纹模板信息
	 * 
	 * @param sn
	 * @author zhangshaung
	 * @dateTime 2017年4月25日 下午5:54:26
	 */
	public void syncUserFinger(String sn) {

		Task task = new Task();
		task.setId(null);
		task.setSn(sn);
		task.setState(1);
		/**
		 * 将args[] 转换成String类型,中间用""隔开
		 */
		List<String> paramList = deviceService.updateUserFingerTmpDataBySn(sn);
		if (null != paramList) {
			for (String param : paramList) {
				task.setArgs(param);
				task.setCommand(CommandWrapper.CMD_DATA_UPDATE_FINGER);
				task.setCreatedDate(new Date());
				task.setDeleted("0");

				taskService.save(task);
			}
		}
	}

	/**
	 * 说明 : 同步员工头像信息
	 * 
	 * @param sn
	 * @author zhangshaung
	 * @dateTime 2017年4月25日 下午5:54:26
	 */
	public void syncUserPhoto(String sn) {
		clearUserPhoto(sn);

		Task task = new Task();
		task.setId(null);
		task.setSn(sn);
		task.setState(1);
		/**
		 * 将args[] 转换成String类型,中间用""隔开
		 */
		List<String> paramList = deviceService.updateUserPhoneDataBySn(sn);
		if (null != paramList) {
			for (String param : paramList) {
				task.setArgs(param);
				task.setCommand(CommandWrapper.CMD_DATA_UPDATE_PHOTO);
				task.setCreatedDate(new Date());
				task.setDeleted("0");

				taskService.save(task);
			}
		}
	}

	/**
	 * 说明 : 清除员工头像信息
	 * 
	 * @param sn
	 * @author zhangshaung
	 * @dateTime 2017年4月25日 下午5:54:06
	 */
	public void clearUserPhoto(String sn) {
		Task clearTask = new Task();
		clearTask.setId(null);
		clearTask.setSn(sn);
		clearTask.setState(1);
		/**
		 * 将args[] 转换成String类型,中间用""隔开
		 */
		List<String> clearParam = deviceService.updateUserPhoneDataBySn(sn);
		if (null != clearParam) {
			for (String param : clearParam) {
				clearTask.setArgs(param);
				clearTask.setCommand(CommandWrapper.CMD_CLEAR_PHOTO);
				clearTask.setCreatedDate(new Date());
				clearTask.setDeleted("0");

				taskService.save(clearTask);
			}

		}
	}

	/**
	 * 说明 : 同步员工基本信息
	 * 
	 * @param sn
	 * @author zhangshaung
	 * @dateTime 2017年4月25日 下午5:54:26
	 */
	public void syncUserInfo(String sn) {

		Task task = new Task();
		task.setId(null);
		task.setSn(sn);
		task.setState(1);
		/**
		 * 将args[] 转换成String类型,中间用""隔开
		 */
		List<String> paramList = deviceService.updateUserInfoDataBySn(sn);
		if (null != paramList) {
			for (String param : paramList) {
				task.setArgs(param);
				task.setCommand(CommandWrapper.CMD_DATA_UPDATE_USER);
				task.setCreatedDate(new Date());
				task.setDeleted("0");

				taskService.save(task);
			}
		}
	}

	/**
	 * 说明 : 清除所有员工基本信息
	 * 
	 * @param sn
	 * @author zhangshaung
	 * @dateTime 2017年4月25日 下午5:54:06
	 */
	public void clearUserInfo(String sn) {
		Task clearTask = new Task();
		clearTask.setId(null);
		clearTask.setSn(sn);
		clearTask.setState(1);
		/**
		 * 将args[] 转换成String类型,中间用""隔开
		 */
		List<String> clearParam = deviceService.updateUserInfoDataBySn(sn);
		if (null != clearParam) {
			for (String param : clearParam) {
				clearTask.setArgs(param);
				clearTask.setCommand(CommandWrapper.CMD_CLEAR_ALL_USERINFO);
				clearTask.setCreatedDate(new Date());
				clearTask.setDeleted("0");

				taskService.save(clearTask);
			}

		}
	}

	/**
	 * 说明 : 清除所有数据
	 * 
	 * @param sn
	 * @author zhangshaung
	 * @dateTime 2017年4月25日 下午5:54:06
	 */
	public void clearAll(String sn) {
		Task clearTask = new Task();
		clearTask.setId(null);
		clearTask.setSn(sn);
		clearTask.setState(1);
		/**
		 * 将args[] 转换成String类型,中间用""隔开
		 */
		List<String> clearParam = deviceService.updateUserInfoDataBySn(sn);
		if (null != clearParam) {
			for (String param : clearParam) {
				clearTask.setArgs(param);
				clearTask.setCommand(CommandWrapper.CMD_CLEAR_DATA);
				clearTask.setCreatedDate(new Date());
				clearTask.setDeleted("0");

				taskService.save(clearTask);
			}

		}
	}

	/**
	 * 说明 : 清除考勤记录
	 * 
	 * @param sn
	 * @author zhangshaung
	 * @dateTime 2017年4月25日 下午5:54:06
	 */
	public void clearLog(String sn) {
		Task clearTask = new Task();
		clearTask.setId(null);
		clearTask.setSn(sn);
		clearTask.setState(1);

		List<String> clearParam = deviceService.updateUserInfoDataBySn(sn);
		if (null != clearParam) {
			for (String param : clearParam) {
				clearTask.setArgs(param);
				clearTask.setCommand(CommandWrapper.CMD_CLEAR_LOG);
				clearTask.setCreatedDate(new Date());
				clearTask.setDeleted("0");

				taskService.save(clearTask);
			}

		}
	}

}
