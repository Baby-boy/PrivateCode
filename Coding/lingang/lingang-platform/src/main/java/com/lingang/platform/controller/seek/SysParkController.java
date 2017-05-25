package com.lingang.platform.controller.seek;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lingang.api.domain.basic.JsonResult;
import com.lingang.api.domain.basic.Page;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.basic.StateCodeConstant;
import com.lingang.api.domain.entity.SysFile;
import com.lingang.api.domain.entity.SysImages;
import com.lingang.api.domain.entity.SysManager;
import com.lingang.api.domain.entity.SysPark;
import com.lingang.api.domain.entity.SysParkIndustry;
import com.lingang.api.domain.entity.SysParkUmanager;
import com.lingang.api.domain.entity.SysPublic;
import com.lingang.api.domain.entity.SysServicePark;
import com.lingang.api.domain.entity.SysStationPark;
import com.lingang.api.domain.entity.SysWay;
import com.lingang.api.domain.para.SysParkPara;
import com.lingang.api.domain.para.SysUmanagerPara;
import com.lingang.api.domain.pfvo.SysParkPfVo;
import com.lingang.api.domain.vo.SysParkVo;
import com.lingang.api.domain.vo.SysPublicVo;
import com.lingang.api.service.SysImagesService;
import com.lingang.api.service.SysIndustryService;
import com.lingang.api.service.SysLogService;
import com.lingang.api.service.SysParkService;
import com.lingang.api.service.SysPublicService;
import com.lingang.api.service.SysServiceService;
import com.lingang.api.service.SysStationService;
import com.lingang.common.util.IpAddressUtil;

@Controller
@RequestMapping("/sysPark")
public class SysParkController {

	@Resource
	private SysLogService sysLogService;

	@Resource
	private SysParkService sysParkService;

	@Resource
	private SysImagesService sysImagesService;

	@Resource
	private SysIndustryService sysIndustryService;

	@Resource
	private SysServiceService sysServiceService;

	@Resource
	private SysStationService sysStationService;

	@Resource
	private SysPublicService sysPublicService;

	/**
	 * @Description: (查询产业园区列表_后台 针对权限)
	 * @param para
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月8日 下午12:55:33
	 */
	@ResponseBody
	@RequestMapping(value = "/sysParkPageListP.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult sysParkPageListP(@RequestBody SysParkPara para) {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<Page<SysParkPfVo>> result = sysParkService.selectSysParkPfVoPageList(para);
		jsonResult.setData(result.getData());
		jsonResult.setStateCode(result.getStateCode());
		return jsonResult;
	}

	/**
	 * @Description: (查询产业园区列表_后台)
	 * @param para
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月8日 下午12:55:33
	 */
	@ResponseBody
	@RequestMapping(value = "/sysParkPageList.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult sysParkPageList(@RequestBody SysParkPara para) {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<Page<SysParkPfVo>> result = sysParkService.selectSysParkPfVoPageList(para);
		jsonResult.setData(result.getData());
		jsonResult.setStateCode(result.getStateCode());
		return jsonResult;
	}

	/**
	 * @Description: (查询产业园区详情)
	 * @param parkId
	 *            产业园区ID
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月5日 下午12:25:40
	 */
	@ResponseBody
	@RequestMapping(value = "/parkDetails.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult parkDetails(@RequestParam(value = "parkId", required = true) Integer parkId) {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<SysParkVo> result = sysParkService.selectSysParkDetailsByStationId(parkId);
		// 补充缩略图
		Map<String, Object> imgMap = new HashMap<String, Object>();
		imgMap.put("minImgPath", "");
		if (result.getData() != null && result.getData().getMinImgId() != null) {
			SysImages images = sysImagesService.selectSysImagesByImgId(result.getData().getMinImgId());
			imgMap.put("minImgPath", images.getImgPath());
		}
		jsonResult.setDataMap(imgMap);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		return jsonResult;
	}

	/**
	 * @Description: (修改产业园区)
	 * @param para
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月9日 上午2:10:20
	 */
	@ResponseBody
	@RequestMapping(value = "/updateParkDetails.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult updateParkDetails(@RequestBody SysParkPara para, HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();
		SysPark park = new SysPark();
		park.setParkId(para.getParkId());
		park.setParkName(para.getParkName());
		park.setParkAddress(para.getParkAddress());
		park.setParkFax(para.getParkFax());
		park.setParkLink(para.getParkLink());
		park.setParkState(para.getParkState());
		park.setParkContent(para.getParkContent());
		park.setUpdateTime(newDate);
		park.setMaxImgId(para.getMaxImgId());
		park.setMinImgId(para.getMinImgId());
		park.setRegionId(para.getRegionId());
		park.setDetailLink(para.getDetailLink());
		ServiceResult<Object> result = sysParkService.updateParkDetails(park);

		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());

		// 记录日志
		if (result.getStateCode().equals(StateCodeConstant.SUCCESS_CODE)) {
			HttpSession session = request.getSession();
			SysManager manager = (SysManager) session.getAttribute("manager");
			sysLogService.insertSysLog(manager.getManagerAccount(), IpAddressUtil.getIpAddress(request), newDate,
					"修改产业园区信息");
		}
		return jsonResult;
	}

	/**
	 * @Description: (添加产业园区)
	 * @param para
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月9日 上午2:10:20
	 */
	@ResponseBody
	@RequestMapping(value = "/addParkDetails.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult addParkDetails(@RequestBody SysParkPara para, HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();
		SysPark park = new SysPark();
		park.setParkId(null);
		park.setParkName(para.getParkName());
		park.setParkAddress(para.getParkAddress());
		park.setParkFax(para.getParkFax());
		park.setParkLink(para.getParkLink());
		park.setParkState(para.getParkState());
		park.setParkContent(para.getParkContent());
		park.setCreateTime(newDate);
		park.setUpdateTime(park.getCreateTime());
		park.setMaxImgId(para.getMaxImgId());
		park.setMinImgId(para.getMinImgId());
		park.setRegionId(para.getRegionId());
		park.setDetailLink(para.getDetailLink());
		ServiceResult<Object> result = sysParkService.addParkDetails(park);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());
		jsonResult.setData(result.getData());

		// 记录日志
		if (result.getStateCode().equals(StateCodeConstant.SUCCESS_CODE)) {
			HttpSession session = request.getSession();
			SysManager manager = (SysManager) session.getAttribute("manager");
			sysLogService.insertSysLog(manager.getManagerAccount(), IpAddressUtil.getIpAddress(request), newDate,
					"添加产业园区信息");
		}
		return jsonResult;
	}

	/**
	 * @Description: (删除标签)
	 * @param parkId
	 * @param labId
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月14日 下午3:32:27
	 */
	@RequestMapping(value = "/delLab.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult delLab(@RequestParam(value = "parkId", required = true) Integer parkId,
			@RequestParam(value = "labelId", required = true) Integer labelId, HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();

		ServiceResult<Object> result = sysParkService.delLab(parkId, labelId);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());

		// 记录日志
		if (result.getStateCode().equals(StateCodeConstant.SUCCESS_CODE)) {
			HttpSession session = request.getSession();
			SysManager manager = (SysManager) session.getAttribute("manager");
			sysLogService.insertSysLog(manager.getManagerAccount(), IpAddressUtil.getIpAddress(request), newDate,
					"删除级联标签");
		}
		return jsonResult;
	}

	/**
	 * @Description: (删除覆盖产业)
	 * @param parkId
	 * @param labelId
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月14日 下午4:04:56
	 */
	@RequestMapping(value = "/delIndustry.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult delIndustry(@RequestParam(value = "parkId", required = true) Integer parkId,
			@RequestParam(value = "industryId", required = true) Integer industryId, HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();
		ServiceResult<Object> result = sysParkService.delIndustry(parkId, industryId);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());

		// 记录日志
		if (result.getStateCode().equals(StateCodeConstant.SUCCESS_CODE)) {
			HttpSession session = request.getSession();
			SysManager manager = (SysManager) session.getAttribute("manager");
			sysLogService.insertSysLog(manager.getManagerAccount(), IpAddressUtil.getIpAddress(request), newDate,
					"删除覆盖产业");
		}
		return jsonResult;
	}

	/**
	 * @Description: (删除宣传册/宣传片)
	 * @param parkId
	 * @param labelId
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月14日 下午4:04:56
	 */
	@RequestMapping(value = "/delFile.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult delFile(@RequestParam(value = "fileId", required = true) Integer fileId) {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<Object> result = sysParkService.delFile(fileId);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());
		return jsonResult;
	}

	/**
	 * @Description: (添加级联园区)
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月14日 下午6:38:42
	 */
	@RequestMapping(value = "/addIndustryParkCascade.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult addIndustryCascade(@RequestBody SysParkPara para, HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();
		if (para.getParkType() == 1) {
			SysParkIndustry parkIndustry = new SysParkIndustry();
			parkIndustry.setIndustryId(para.getObjId());
			parkIndustry.setParkId(para.getParkId());
			ServiceResult<Object> result = sysIndustryService.addParkIndustryCascade(parkIndustry);
			jsonResult.setStateCode(result.getStateCode());
			jsonResult.setMessage(result.getMessage());
		} else if (para.getParkType() == 2) {
			SysServicePark servicePark = new SysServicePark();
			servicePark.setServiceId(para.getObjId());
			servicePark.setParkId(para.getParkId());
			ServiceResult<Object> result = sysServiceService.addServiceCascade(servicePark);
			jsonResult.setStateCode(result.getStateCode());
			jsonResult.setMessage(result.getMessage());
		} else if (para.getParkType() == 3) {
			SysStationPark stationPark = new SysStationPark();
			stationPark.setStationId(para.getObjId());
			stationPark.setParkId(para.getParkId());
			ServiceResult<Object> result = sysStationService.addStationCascade(stationPark);
			jsonResult.setStateCode(result.getStateCode());
			jsonResult.setMessage(result.getMessage());
		} else if (para.getParkType() == 4) {
			ServiceResult<SysPublicVo> result = sysPublicService.selectSysPublicDetails(para.getObjId());
			if (result.getData().getParkId() != null) {
				jsonResult.setStateCode(StateCodeConstant.ERROR_CODE_HAVE);
				jsonResult.setMessage("已有所属园区,请检查");
				return jsonResult;
			}
			SysPublic sysPublic = new SysPublic();
			sysPublic.setPublicId(result.getData().getPublicId());
			sysPublic.setParkId(para.getParkId());
			ServiceResult<Object> result2 = sysPublicService.updatePublicDetails(sysPublic);
			jsonResult.setStateCode(result2.getStateCode());
			jsonResult.setMessage(result2.getMessage());
		}

		// 记录日志
		if (jsonResult.getStateCode().equals(StateCodeConstant.SUCCESS_CODE)) {
			HttpSession session = request.getSession();
			SysManager manager = (SysManager) session.getAttribute("manager");
			sysLogService.insertSysLog(manager.getManagerAccount(), IpAddressUtil.getIpAddress(request), newDate,
					"添加级联园区");
		}
		return jsonResult;
	}

	/**
	 * @Description: (添加联系电话信息)
	 * @param wayTel
	 *            联系电话
	 * @param wayType
	 *            类型(1.产业园区 2.服务机构 3.公共平台)
	 * @param wayObjId
	 *            对应ID
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月22日 下午3:12:41
	 */
	@RequestMapping(value = "/addWay.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult addWay(@RequestParam(value = "wayTel", required = true) String wayTel,
			@RequestParam(value = "wayType", required = true) Integer wayType,
			@RequestParam(value = "wayObjId", required = true) Integer wayObjId, HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();
		SysWay sysWay = new SysWay();
		sysWay.setWayTel(wayTel);
		sysWay.setWayType(wayType);
		sysWay.setWayObjId(wayObjId);
		ServiceResult<SysWay> result = sysParkService.addWay(sysWay);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());
		jsonResult.setData(result.getData());

		// 记录日志
		if (result.getStateCode().equals(StateCodeConstant.SUCCESS_CODE)) {
			HttpSession session = request.getSession();
			SysManager manager = (SysManager) session.getAttribute("manager");
			sysLogService.insertSysLog(manager.getManagerAccount(), IpAddressUtil.getIpAddress(request), newDate,
					"添加级联电话");
		}
		return jsonResult;
	}

	/**
	 * @Description: (删除联系电话)
	 * @param wayId
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月22日 下午3:42:46
	 */
	@RequestMapping(value = "/delWay.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult delWay(@RequestParam(value = "wayId", required = true) Integer wayId,
			HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();
		ServiceResult<Object> result = sysParkService.delWay(wayId);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());
		jsonResult.setData(result.getData());

		// 记录日志
		if (result.getStateCode().equals(StateCodeConstant.SUCCESS_CODE)) {
			HttpSession session = request.getSession();
			SysManager manager = (SysManager) session.getAttribute("manager");
			sysLogService.insertSysLog(manager.getManagerAccount(), IpAddressUtil.getIpAddress(request), newDate,
					"删除级联电话");
		}
		return jsonResult;
	}

	/**
	 * @Description: (修改宣传片封面)
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2017年1月9日 下午7:01:54
	 */
	@RequestMapping(value = "/uploadFileParkVideoImage.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult uploadFileParkVideoImage(@RequestParam(value = "fileId", required = true) Integer fileId,
			@RequestParam(value = "imgId") Integer imgId, HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();
		SysFile file = new SysFile();
		file.setFileId(fileId);
		file.setImgId(imgId);
		ServiceResult<Object> result = sysParkService.uploadFileParkVideoImage(file);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());
		jsonResult.setData(result.getData());

		// 记录日志
		if (result.getStateCode().equals(StateCodeConstant.SUCCESS_CODE)) {
			HttpSession session = request.getSession();
			SysManager manager = (SysManager) session.getAttribute("manager");
			sysLogService.insertSysLog(manager.getManagerAccount(), IpAddressUtil.getIpAddress(request), newDate,
					"修改园区文件");
		}
		return jsonResult;
	}

	/**
	 * @Description: (删除客户经理)
	 * @param partnerUmanagerId
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2017年1月12日 下午2:08:07
	 */
	@RequestMapping(value = "/delUman.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult delUman(@RequestParam(value = "parkUmanagerId", required = true) Integer parkUmanagerId,
			HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();
		ServiceResult<Object> result = sysParkService.delUman(parkUmanagerId);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());
		
		// 记录日志
		if (result.getStateCode().equals(StateCodeConstant.SUCCESS_CODE)) {
			HttpSession session = request.getSession();
			SysManager manager = (SysManager) session.getAttribute("manager");
			sysLogService.insertSysLog(manager.getManagerAccount(), IpAddressUtil.getIpAddress(request), newDate,
					"删除客户经理");
		}
		return jsonResult;
	}

	/**
	 * @Description: (添加客户经理)
	 * @param para
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2017年1月12日 下午2:37:06
	 */
	@RequestMapping(value = "/addUManager.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult addUManager(@RequestBody SysUmanagerPara para,HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();
		SysParkUmanager parkUmanager = new SysParkUmanager();
		parkUmanager.setParkId(para.getObjId());
		parkUmanager.setUserAccount(para.getUserAccount());
		ServiceResult<Object> result = sysParkService.addUManager(parkUmanager);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());
		jsonResult.setData(result.getData());
		
		// 记录日志
		if (result.getStateCode().equals(StateCodeConstant.SUCCESS_CODE)) {
			HttpSession session = request.getSession();
			SysManager manager = (SysManager) session.getAttribute("manager");
			sysLogService.insertSysLog(manager.getManagerAccount(), IpAddressUtil.getIpAddress(request), newDate,
					"添加客户经理");
		}
		return jsonResult;
	}
}
