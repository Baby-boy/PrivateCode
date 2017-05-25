package com.lingang.platform.controller.home;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lingang.api.domain.basic.JsonResult;
import com.lingang.api.domain.basic.Page;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.basic.StateCodeConstant;
import com.lingang.api.domain.entity.SysManager;
import com.lingang.api.domain.entity.SysNews;
import com.lingang.api.domain.para.SysNewsPara;
import com.lingang.api.domain.vo.SysNewsVo;
import com.lingang.api.service.SysLogService;
import com.lingang.api.service.SysNewsService;
import com.lingang.common.util.IpAddressUtil;
import com.lingang.platform.controller.BaseController;

/**
 *@Description: 新闻
 *@Author: lgl(lgl1012dream@foxmail.com)
 *@date:2016年12月7日 上午10:50:03
 *@Version:1.0
 */
@Controller
@RequestMapping("/sysNews")
public class SysNewsController extends BaseController{

	@Resource
	private SysLogService sysLogService;
	
	@Resource
	private SysNewsService sysNewsService;
	
	/***
	 * @Description: 获取新闻列表信息
	 * @param map
	 * @return    
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 * @date: 2016年12月8日 下午3:07:00
	 */
	//{onePageCount=15, newAuthor=, currentPage=1, newTitle=}
	@ResponseBody
	@RequestMapping(value = "/newsList.do",method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult newsList(@RequestBody SysNewsPara para){
		JsonResult jsonResult=new JsonResult(); 
		ServiceResult<Page<SysNewsVo>> serviceResult = sysNewsService.queryAllByPage(para);
		//浏览数量获取
		serviceResult.getData().setList(getLookNum(serviceResult.getData().getList()));
		jsonResult.setData(serviceResult);
		jsonResult.setStateCode(serviceResult.getStateCode());
		
		return jsonResult;
	}
	
	/**
	* @Description: (读取浏览数量)
	* @param newList
	* @return    
	* @author lgl(lgl1012dream@foxmail.com)
	* @date: 2017年1月10日 下午2:52:00
	 */
	private List<SysNewsVo>  getLookNum(List<SysNewsVo> newList){
		for(SysNewsVo news:newList){
			Integer newsNum=1000;
			try {
				if(redisGet("news_"+news.getNewsId()) !=null){
					newsNum=Integer.parseInt(redisGet("news_"+news.getNewsId()).toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			news.setLookNumber(newsNum);
		}
		return newList;
	}
	
	/***
	 * @Description: 根据新闻id查询新闻详情 
	 * @param news_id
	 * @return    
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月8日 下午3:07:12
	 */
	@ResponseBody
	@RequestMapping(value = "/queryById.do",method = { RequestMethod.POST })
	public JsonResult queryById(@RequestBody HashMap<String, Object> map){
		return sysNewsService.queryById(map);
	}
	
	/***
	 * @Description: 添加新闻信息 
	 * @param news
	 * @return    
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月8日 下午3:07:26
	 */
	@ResponseBody
	@RequestMapping(value = "/insert.do",method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult insert(@RequestBody HashMap<String, Object> map,HttpServletRequest request){
		JsonResult jsonResult=new JsonResult();
		Date newDate = new Date();
		jsonResult=sysNewsService.insert(map,newDate);
		
		// 记录日志
		if (jsonResult.getStateCode().equals(StateCodeConstant.SUCCESS_CODE)) {
			HttpSession session = request.getSession();
			SysManager manager = (SysManager) session.getAttribute("manager");
			sysLogService.insertSysLog(manager.getManagerAccount(), IpAddressUtil.getIpAddress(request), newDate,
					"添加新闻信息");
		}
		return jsonResult;
	}
	
	/***
	 * @Description: 修改新闻信息 
	 * @param news
	 * @return    
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月8日 下午3:07:39
	 */
	@ResponseBody
	@RequestMapping(value = "/update.do",method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult update(@RequestBody SysNews news,HttpServletRequest request){
		JsonResult jsonResult=new JsonResult();
		Date newDate = new Date();
		news.setUpdateTime(newDate);
		jsonResult=sysNewsService.update(news);
		
		// 记录日志
		if (jsonResult.getStateCode().equals(StateCodeConstant.SUCCESS_CODE)) {
			HttpSession session = request.getSession();
			SysManager manager = (SysManager) session.getAttribute("manager");
			sysLogService.insertSysLog(manager.getManagerAccount(), IpAddressUtil.getIpAddress(request), newDate,
					"修改新闻信息");
		}
		return jsonResult;
	}
	
	/***
	 * @Description: 删除新闻信息 
	 * @param news_id
	 * @return    
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月8日 下午3:07:54
	 */
	@ResponseBody
	@RequestMapping(value = "/del.do",method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult del(@RequestBody HashMap<String, Object> map,HttpServletRequest request){
		JsonResult jsonResult=new JsonResult();
		Date newDate = new Date();
		jsonResult=sysNewsService.del(map,newDate);
		
		// 记录日志
		if (jsonResult.getStateCode().equals(StateCodeConstant.SUCCESS_CODE)) {
			HttpSession session = request.getSession();
			SysManager manager = (SysManager) session.getAttribute("manager");
			sysLogService.insertSysLog(manager.getManagerAccount(), IpAddressUtil.getIpAddress(request), newDate,
					"修改新闻信息");
		}
		return jsonResult;
	}
	
}
