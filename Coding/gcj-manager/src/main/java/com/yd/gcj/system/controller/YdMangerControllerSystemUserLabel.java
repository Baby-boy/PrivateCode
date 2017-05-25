package com.yd.gcj.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yd.gcj.entity.vo.YdMangerUserLabelVo;
import com.yd.gcj.entity.vo.YdMangerUserVo;
import com.yd.gcj.system.service.YdMangerServiceSystemUser;
import com.yd.gcj.system.service.YdMangerServiceSystemUserLabel;

/**
 * description(服务商技能信息管理)
 * @author Administrator
 * @param <HttpServletRequest>
 */
@Controller
@RequestMapping("/system")
public class YdMangerControllerSystemUserLabel<HttpServletRequest> {
	
	@Autowired
	private YdMangerServiceSystemUserLabel ydMangerServiceSystemUserLabel;
	
	@Autowired
	private YdMangerServiceSystemUser ydMangerServiceSystemUser;
	/**
	 * description(根据user_id查询技能信息)
	 * @param user_id
	 * @param model
	 * @return
	 */
	@RequestMapping( "/queryAllServerSkillStateByUserId/{user_id}" )
	public String queryAllServerSkillState(@PathVariable Integer user_id, Model model){
		List<YdMangerUserLabelVo> userSkillList = ydMangerServiceSystemUserLabel.queryAllServerSkillState(user_id);
		YdMangerUserVo ydMangerUserVo = ydMangerServiceSystemUser.queryUserByUserId(user_id);
		for (YdMangerUserLabelVo ydMangerUserLabelVo : userSkillList) {
			model.addAttribute("ydMangerUserLabelVo", ydMangerUserLabelVo);
		}
		model.addAttribute("userSkillList", userSkillList);
		model.addAttribute("ydMangerUserVo", ydMangerUserVo);
		return "system/yhgl/fuwushangupdate";
	}
	
}
