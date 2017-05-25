package com.yd.gcj.router;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yd.gcj.entity.YdMangerArticle;
import com.yd.gcj.entity.vo.PageNum;
import com.yd.gcj.service.YdMangerServiceArticle;

@Controller
@RequestMapping("/consult")
public class YdMangerPageRouterConsult {
	
	private static final String pageFiles = "consult/";
	
	@Autowired
	private YdMangerServiceArticle serviceArticle;
	
	@RequestMapping("/help")
	public String help() {
		return pageFiles+"help";
	}
	
	@RequestMapping("/online-detail")
	public String onlineDetail() {
		return pageFiles+"online-detail";
	}
	
	@RequestMapping("/online")
	public String online(Model model,HttpServletRequest request) {
		Integer type = (Integer) request.getSession().getAttribute("articleType");
		if(type == null){
			type = 1;
		}
		// 获取数据总条数
		Integer num = serviceArticle.queryCountAll(type);
		// 分页函数
		PageNum pageNum = new PageNum(request.getSession(), num, "articlePageNum");
		List<YdMangerArticle> articles = serviceArticle.$queryAll(type, pageNum.getStartPageNum(), pageNum.getPageNum());
		pageNum.setData(articles);
		model.addAttribute("articlePageNum", pageNum);
		model.addAttribute("articleType", type);
		return pageFiles+"online";
	}
	
	
}
