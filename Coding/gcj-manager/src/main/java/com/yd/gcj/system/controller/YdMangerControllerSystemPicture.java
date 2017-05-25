package com.yd.gcj.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yd.gcj.entity.YdMangerBanner;
import com.yd.gcj.system.service.YdMangerServiceSystemPicture;
import com.yd.gcj.tool.ObjectMapperFactory;


/**
 * description(首页轮播及其他图片管理)
 * @author Administrator
 * @param <HttpServletRequest>
 */
@Controller
@RequestMapping("/system")
public class YdMangerControllerSystemPicture<HttpServletRequest> {
	
	@Autowired
	private YdMangerServiceSystemPicture ydMangerServcieSystemPicture; 
	/**
	 * description(查询所有图片信息)
	 * @param
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryAllPicture" )
	public String queryAllPicture(Integer p,String banner_title, Model model){
		//当前页
		if (p==null) {
			p=1;
			PageHelper .startPage(p,8);
		}else {
			PageHelper .startPage(p,8);
		};
		
		List<YdMangerBanner> bannerList = ydMangerServcieSystemPicture.queryAllPicture(banner_title);
		PageInfo<YdMangerBanner> pageInfo = new PageInfo<YdMangerBanner>(bannerList);
		//总页数
		Integer totalPage = null;
		Integer total = (int) pageInfo.getTotal();
		if (total%8 !=0) {
			totalPage = total/8+1;
		}else {
			totalPage = total/8;
		}
														
		model.addAttribute("p",p);
		model.addAttribute("totalPage",totalPage);
		model.addAttribute("bannerList", bannerList);
		model.addAttribute("banner_title", banner_title);
		return "system/tpgl/picture";
	}
	
	/**
	 * description(添加图片)
	 * @param
	 * @param ydMangerBanner
	 * @return
	 */
	@RequestMapping("/addPicture")
	@ResponseBody
	public Object addPicture(YdMangerBanner ydMangerBanner){
		Map<String ,Object> map = new HashMap<String, Object>();
		Integer addNum = ydMangerServcieSystemPicture.addPicture(ydMangerBanner);
		if (addNum>0) {
			map.put("msg", true);
		}else {
			map.put("msg",false);
		}
		return map;
	}
	
	/**
	 * description(根基指定的banner_id删除图片)
	 * @param
	 * @param banner_id
	 * @return
	 */
	@RequestMapping("/deletePicture")
	@ResponseBody
	public Object deletePicture(Integer banner_id){
		Map<String,Object> map = new HashMap<String, Object>();
		Integer delNum = ydMangerServcieSystemPicture.deletePictureByBannerId(banner_id);
		if (delNum>0) {
			map.put("msg",true);
		}else {
			map.put("msg",false);
		}
		return map;
	}
	
	/**
	 * description(修改图片信息之前先根据banner_id查到当前图片信息)
	 * @param
	 * @param banner_id
	 * @param model
	 * @return
	 */
	@RequestMapping("/updatePictureByBannerId/{banner_id}")
	public String queryBannerByBannerId(@PathVariable Integer banner_id,Model model){
		List<YdMangerBanner> bannerTypeList = ydMangerServcieSystemPicture.queryAllBannerType();
		YdMangerBanner ydMangerBanner = ydMangerServcieSystemPicture.queryBannerByBannerId(banner_id);
		model.addAttribute("bannerTypeList", bannerTypeList);
		ObjectMapperFactory.doIt(bannerTypeList);
		model.addAttribute("ydMangerBanner", ydMangerBanner);
		return "system/tpgl/pictureupdate";
	}
}
