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
import com.yd.gcj.entity.YdMangerArticle;
import com.yd.gcj.entity.YdMangerArticleClass;
import com.yd.gcj.entity.vo.YdMangerArticleVo;
import com.yd.gcj.system.service.YdMangerServiceSystemArticle;
import com.yd.gcj.system.service.YdMangerServiceSystemArticleType;

/**
 * description(文章信息管理)
 * @author Administrator
 * @param <HttpServletRequest>
 */
@Controller
@RequestMapping("/system")
public class YdMangerControllerSystemArticle<HttpServletRequest> {
	
	@Autowired
	private YdMangerServiceSystemArticle ydMangerServiceSystemArticle;
	
	@Autowired
	private YdMangerServiceSystemArticleType ydMangerServiceSystemArticleType;
	
	
	/**
	 * description(查询所有的文章信息)
	 * @param
	 * @param model
	 * @return
	 */
	@RequestMapping( "/queryAllArticle" )
	public String queryAllArticle(Integer p,String article_title,Integer article_type, Model model){
		//当前页
		if (p==null) {
			p=1;
			PageHelper .startPage(p,8);
		}else {
			PageHelper .startPage(p,8);
		};
		
		List<YdMangerArticleVo> articleList = ydMangerServiceSystemArticle.queryAllArticle(article_title,article_type);
		PageInfo<YdMangerArticleVo> pageInfo = new PageInfo<YdMangerArticleVo>(articleList);
		//总页数
		Integer totalPage = null;
		Integer total = (int) pageInfo.getTotal();
		if (total%8 !=0) {
			totalPage = total/8+1;
		}else {
			totalPage = total/8;
		}
		List<YdMangerArticleClass> articleClassList = ydMangerServiceSystemArticleType.queryAllArticleType();
		model.addAttribute("p",p);
		model.addAttribute("totalPage",totalPage);
		model.addAttribute("article_title", article_title);
		model.addAttribute("articleClassList", articleClassList);
		model.addAttribute("articleList",articleList);
		return "system/articlemanger/articleinfo";
	}
	
	/**
	 * description(根据article_id删除文章)
	 * @param
	 * @param article_id
	 * @param model
	 * @return
	 */
	@RequestMapping( "/deleteArticle" )
	@ResponseBody
	public Object deleteArticle(Integer article_id,Model model){
		Map<String, Object> map = new HashMap<String, Object>();
		Integer delNum = ydMangerServiceSystemArticle.deleteArticleByArticleId(article_id);
		if (delNum>0) {
			map.put("msg", true);
		}else {
			map.put("msg", false);
		}
		return map;
	}
	
	@RequestMapping("/addArticle")
	@ResponseBody
	public Object addArticle(YdMangerArticle ydMangerArticle){
		Map<String, Object> map = new HashMap<String, Object>();
		Integer adminNum =  ydMangerServiceSystemArticle.addArticle(ydMangerArticle);
		if (adminNum>0) {
			//添加成功
			map.put("msg",true); 
		}else {
			map.put("msg",false);
		}
		return map;
	}

	/**
	 * description(修改文章信息之前先根据article_id查询当前文章信息)
	 * @param
	 * @param article_id
	 * @param model
	 * @return
	 */
	@RequestMapping("/updateArticleByArticleId/{article_id}")
	public String queryArticleByArticleId(@PathVariable Integer article_id,Model model){
		YdMangerArticleVo ydMangerArticle = ydMangerServiceSystemArticle.queryArticleByArticleId(article_id);
		List<YdMangerArticleClass> articleClassList = ydMangerServiceSystemArticleType.queryAllArticleType();
		model.addAttribute("articleClassList", articleClassList);
		model.addAttribute("ydMangerArticle", ydMangerArticle);
		return "system/articlemanger/articleupdate";
	}
	
	/**
	 * description(根据指定的article_id修改文章信息)
	 * @param
	 * @param ydMangerArticle
	 * @return
	 */
	@RequestMapping("/updateArticle")
	@ResponseBody
	public Object updateArticleByArticleId(YdMangerArticle ydMangerArticle){
		Map<String,Object> map = new HashMap<String, Object>();
		Integer updateNum = ydMangerServiceSystemArticle.updateArticleByArticleId(ydMangerArticle);
		if (updateNum>0) {
			map.put("msg",true);
		} else {
			map.put("msg",false);
		}
		return map;
	}
	
	/**
	 * description(根据article_id查询文章内容)
	 * @param
	 * @param article_id
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryArticleContentByArticleId/{article_id}")
	public String queryArticleContentByArticleId(@PathVariable Integer article_id,Model model){
		YdMangerArticle ydMangerArticle = ydMangerServiceSystemArticle.queryArticleContentByArticleId(article_id);
		model.addAttribute("ydMangerArticle", ydMangerArticle);
		return "system/articlemanger/articlecontent";
	}
}
