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
import com.yd.gcj.entity.YdMangerArticleClass;
import com.yd.gcj.system.service.YdMangerServiceSystemArticle;
import com.yd.gcj.system.service.YdMangerServiceSystemArticleType;


/**
 * description(文章类型管理)
 * @author Administrator
 * @param <HttpServletRequest>
 */
@Controller
@RequestMapping("/system")
public class YdMangerControllerSystemArticleType<HttpServletRequest> {
	
	@Autowired
	private YdMangerServiceSystemArticleType ydMangerServiceSystemArticleType;
	
	@Autowired
	private YdMangerServiceSystemArticle ydMangerServiceSystemArticle;
	
	/**
	 * description(查询文章类型的所有信息)
	 * @param
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryAllArticleType")
	public String queryAllArticleType(Integer p,String ac_name,Model model){
		//当前页
		if (p==null) {
			p=1;
			PageHelper .startPage(p,8);
		}else {
			PageHelper .startPage(p,8);
		};
		List<YdMangerArticleClass> articleTypeList = ydMangerServiceSystemArticleType.queryAllArticleTypeInfo(ac_name);
		PageInfo<YdMangerArticleClass> pageInfo = new PageInfo<YdMangerArticleClass>(articleTypeList);
		//总页数
		Integer totalPage = null;
		Integer total = (int) pageInfo.getTotal();
		if (total % 8 != 0) {
			totalPage = total / 8 + 1;
		} else {
			totalPage = total / 8;
		}
				
		model.addAttribute("p",p);
		model.addAttribute("totalPage",totalPage);
		model.addAttribute("articleTypeList", articleTypeList);
		model.addAttribute("ac_name", ac_name);
		return "system/articlemanger/articletype";
	}
	
	/**
	 * description(查询文章的所有类型)
	 * @param
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryArticleType")
	public String queryArticleType(Model model){
		List<YdMangerArticleClass> articleClassList = ydMangerServiceSystemArticleType.queryAllArticleType();
		model.addAttribute("articleClassList", articleClassList);
		return "system/articlemanger/articleadd";
	}
	
	/**
	 * description(添加文章类型)
	 * @param
	 * @param ydMangerArticleClass
	 * @return
	 */
	@RequestMapping("/addArticleClass")
	@ResponseBody
	public Object addArticleClass(YdMangerArticleClass ydMangerArticleClass){
		Map<String,Object> map = new HashMap<String, Object>();
		Integer addNum = ydMangerServiceSystemArticleType.addArticleClass(ydMangerArticleClass);
		if (addNum>0) {
			map.put("msg",true);
		} else {
			map.put("msg",false);
		}
		return map;
	}
	
	/**
	 * description(根据指定的ac_id删除文章类型)
	 * @param
	 * @param ac_id
	 * @return
	 */
	@RequestMapping("/deleteArticleType")
	@ResponseBody
	public Object deleteArticleType(Integer ac_id){
		Map<String,Object> map = new HashMap<String, Object>();
		Integer arCount = ydMangerServiceSystemArticle.queryArticleByArticleType(ac_id);
		if (arCount>0) {
			map.put("msg",100);
		}else {
			Integer delNum = ydMangerServiceSystemArticleType.deleteArticleClassByAcId(ac_id);
			if (delNum>0) {
				map.put("msg",200);
			}else {
				map.put("msg",300);
			}
		}
		return map;
	}
	
	/**
	 * description(修改之前先根据ac_id查询到当前文章类型)
	 * @param
	 * @param ac_id
	 * @return
	 */
	@RequestMapping("/updateArticleTypeByAcId/{ac_id}")
	public String queryArticleTypeByAcId(@PathVariable Integer ac_id,Model model){
		YdMangerArticleClass ydMangerArticleClass = ydMangerServiceSystemArticleType.queryArticleTypeByAcId(ac_id);
		model.addAttribute("ydMangerArticleClass", ydMangerArticleClass);
		return "system/articlemanger/articletypeupdate";
	}
	
	/**
	 * description(根据指定的ac_id修改文章类型)
	 * @param
	 * @param ydMangerArticleClass
	 * @return
	 */
	@RequestMapping("/updateArticleClass")
	@ResponseBody
	public Object updateArticleClass(YdMangerArticleClass ydMangerArticleClass){
		Map<String,Object> map = new HashMap<String, Object>();
		Integer updateNum = ydMangerServiceSystemArticleType.updateArticleClassByAcId(ydMangerArticleClass);
		if (updateNum>0) {
			map.put("msg",true);
		} else {
			map.put("msg",false);
		}
		return map;
	}
	
}
