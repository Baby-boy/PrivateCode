package com.lingang.platform.controller.home;

import java.util.Date;
import java.util.HashMap;

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
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.basic.StateCodeConstant;
import com.lingang.api.domain.entity.SysManager;
import com.lingang.api.domain.entity.SysStation;
import com.lingang.api.domain.para.SysStationPara;
import com.lingang.api.domain.vo.SysStationVo;
import com.lingang.api.service.SysLogService;
import com.lingang.api.service.SysStationService;
import com.lingang.common.util.IpAddressUtil;

/**
 *@Description: 后台入驻信息管理
 *@Author: lgl(lgl1012dream@foxmail.com)
 *@date:2016年12月8日 下午1:58:37
 *@Version:1.0
 */
@Controller
@RequestMapping("/sysTopStation")
public class SysTopStationController {
	
	@Resource
	private SysLogService sysLogService;
	
	@Resource
	private SysStationService sysStationService;
	
	/**
	* @Description: (入驻列表 2016-12-15 gsh改)
	* @param map
	* @return    
	* @author gsh(15136390655@163.com)
	* @date: 2016年12月15日 上午9:56:21
	 */
	@ResponseBody
	@RequestMapping(value = "/queryAllByTop.do", method = {RequestMethod.GET,RequestMethod.POST})
	public JsonResult queryAllByTop(@RequestBody HashMap<String, Object> map){
		return sysStationService.selectAll(map);
	}
	
	/**
	* @Description: (入驻企业详情 2016-12-15 gsh改)
	* @param station_id
	* @return    
	* @author gsh(15136390655@163.com)
	* @date: 2016年12月15日 上午11:01:13
	 */
	@ResponseBody
	@RequestMapping(value = "/topStationDetails.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult sysPartnerDetails(@RequestParam(value = "stationId", required = true) Integer stationId) {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<SysStationVo> result = sysStationService.selectByPrimaryKey(stationId);
		jsonResult.setData(result.getData());
		jsonResult.setStateCode(result.getStateCode());
		return jsonResult;
	}
	

	/**
	* @Description: (修改入驻企业信息 2016-12-15 gsh改)
	* @param para
	* @return    
	* @author gsh(15136390655@163.com)
	* @date: 2016年12月15日 下午12:06:37
	 */
	@ResponseBody
	@RequestMapping(value = "/updateTopStationDetails.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult updateStationDetails(@RequestBody SysStationPara para,HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();
		SysStation station = new SysStation();
		station.setStationId(para.getStationId());
		station.setStationTitle(para.getStationTitle());
		station.setStationSimple(para.getStationSimple());
		station.setRegNumber(para.getRegNumber());
		station.setCreditCode(para.getCreditCode());
		station.setStationCompany(para.getStationCompany());
		station.setIsFictitious(para.getIsFictitious());
		station.setLossState(para.getLossState());
		station.setStationState(para.getStationState());
		station.setStationContent(para.getStationContent());
		station.setUpdateTime(newDate);
		station.setImgId(para.getImgId());
		station.setRegionId(para.getRegionId());

		ServiceResult<Object> result = sysStationService.updateStationDetails(station);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());
		
		// 记录日志
		if (result.getStateCode().equals(StateCodeConstant.SUCCESS_CODE)) {
			HttpSession session = request.getSession();
			SysManager manager = (SysManager) session.getAttribute("manager");
			sysLogService.insertSysLog(manager.getManagerAccount(), IpAddressUtil.getIpAddress(request), newDate,
					"修改入驻企业信息");
		}
		return jsonResult;
	}
	
	/**
	 * @Description: 删除指定的入驻信息
	 * @param station_id
	 * @return    
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月8日 下午1:56:59
	 */
	@RequestMapping(value = "/del.do", method = {RequestMethod.GET,RequestMethod.POST})
	public JsonResult del(Integer station_id,HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		int i=sysStationService.deleteByPrimaryKey(station_id);
		
		if(i>0){
			// 记录日志
				HttpSession session = request.getSession();
				SysManager manager = (SysManager) session.getAttribute("manager");
				sysLogService.insertSysLog(manager.getManagerAccount(), IpAddressUtil.getIpAddress(request), new Date(),
						"修改入驻企业信息");
		}
		return jsonResult;
	}
	
	/**
	* @Description: (取消置顶)
	* @param para
	* @return    
	* @author gsh(15136390655@163.com)
	* @date: 2016年12月19日 下午4:18:52
	 */
	@ResponseBody
	@RequestMapping(value = "/delStationTop.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult delStationTop(@RequestBody SysStationPara para,HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();
		SysStation sysStation = new SysStation();
		sysStation.setStationId(para.getStationId());
		sysStation.setTopState(0);
		sysStation.setUpdateTime(newDate);
		int result = sysStationService.updateByPrimaryKeySelective(sysStation);
		if(result<1){
			jsonResult.setStateCode(StateCodeConstant.ERROR_CODE);
			return jsonResult;
		}else{
			// 记录日志
			HttpSession session = request.getSession();
			SysManager manager = (SysManager) session.getAttribute("manager");
			sysLogService.insertSysLog(manager.getManagerAccount(), IpAddressUtil.getIpAddress(request), new Date(),
					"修改入驻企业信息");
		}
		return jsonResult;
	}
}
