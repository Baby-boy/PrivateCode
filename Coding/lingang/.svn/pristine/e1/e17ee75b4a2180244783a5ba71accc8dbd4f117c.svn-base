package com.lingang.consumer.controller.home;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lingang.api.domain.basic.JsonResult;
import com.lingang.api.domain.basic.Page;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.entity.SysNews;
import com.lingang.api.domain.vo.SysNewsVo;
import com.lingang.api.service.SysNewsService;
import com.lingang.consumer.controller.BaseController;

@Controller
@RequestMapping("/news")
public class SysNewsController extends BaseController{
	@Resource
	private SysNewsService sysNewsService;

	/**
	 * @Description: (查询新闻列表)
	 * @param pageIndex
	 * @param pageSize
	 * @param request
	 * @return
	 * @author gsh(15136390655@163.com)
	 * @date: 2016年12月3日 下午8:08:32
	 */
	@ResponseBody
	@RequestMapping(value = "/newsPageList", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult newsPageList(
			@RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
			HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageIndex", pageIndex);
		map.put("pageSize", pageSize);
		ServiceResult<Page<SysNewsVo>> result = sysNewsService.selectSysNewsAll(map);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());
		jsonResult.setData(result.getData());
		return jsonResult;
	};

	/**
	 * @Description: (查询新闻详情)
	 * @param 新闻ID
	 * @return
	 * @author gsh(15136390655@163.com)
	 * @date: 2016年12月3日 下午1:58:12
	 */
	@ResponseBody
	@RequestMapping(value = "/newsDetails", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult newsDetails(@RequestParam(value = "newsId", required = true) Integer newsId,
			HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<SysNewsVo> result = sysNewsService.selectByPrimaryKeys(newsId);
		if(result.getData() !=null){
			result.getData().setLookNumber(addLookNum(newsId));
		}
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());
		jsonResult.setData(result.getData());
		return jsonResult;
	};
	
	/**
	* @Description: (记录浏览次数)
	* @param newsId
	* @return    
	* @author lgl(lgl1012dream@foxmail.com)
	* @date: 2017年1月5日 下午6:32:53
	 */
	private Integer addLookNum(Integer newsId){
		Integer num=1000;
		try {
			if(redisGet("news_"+newsId) !=null){
				num=Integer.parseInt(redisGet("news_"+newsId).toString())+1;
			}
			redisSet("news_"+newsId+"",num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return num;
	}
}
