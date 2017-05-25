package com.yd.gcj.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yd.gcj.entity.YdMangerUser;
import com.yd.gcj.entity.YdMangerVerified;
import com.yd.gcj.entity.vo.YdMangerUserLabelVo;
import com.yd.gcj.entity.vo.YdMangerUserVo;
import com.yd.gcj.mapper.YdMangerMapperVerified;
import com.yd.gcj.system.service.YdMangerServiceSystemUser;
import com.yd.gcj.tool.ObjectMapperFactory;

/**
 * description(服务商信息管理)
 * 
 * @author Administrator
 * @param <HttpServletRequest>
 */
@Controller
@RequestMapping("/system")
public class YdMangerControllerSystemServe<HttpServletRequest> {

	@Autowired
	private YdMangerServiceSystemUser ydMangerServiceSystemUser;
	@Autowired
	private YdMangerMapperVerified ydMangerMapperVerified;

	/**
	 * description(查询所有的服务商信息)
	 * 
	 * @param
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryAllServer")
	public String queryAllServer(Integer p, Integer user_cstate, Integer user_skillstate, Integer user_verified,
			String user_name, Model model) {
		// 当前页
		if (p == null) {
			p = 1;
			PageHelper.startPage(p, 8);
		} else {
			PageHelper.startPage(p, 8);
		}
		;

		List<YdMangerUserVo> serverList = ydMangerServiceSystemUser.queryAllServer(user_name, user_cstate,
				user_verified, user_skillstate);
		PageInfo<YdMangerUserVo> pageInfo = new PageInfo<YdMangerUserVo>(serverList);

		// 总页数
		Integer totalPage = null;
		Integer total = (int) pageInfo.getTotal();
		if (total % 8 != 0) {
			totalPage = total / 8 + 1;
		} else {
			totalPage = total / 8;
		}
		model.addAttribute("p", p);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("user_cstate", user_cstate);
		model.addAttribute("user_skillstate", user_skillstate);
		model.addAttribute("user_verified", user_verified);
		model.addAttribute("user_name", user_name);
		model.addAttribute("serverList", serverList);
		return "system/yhgl/fuwushang";
	}

	/**
	 * description(删除服务商用户)
	 * 
	 * @param
	 * @param user_id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/deleteServer", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object deleteUser(Integer user_id, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer addNum = ydMangerServiceSystemUser.deleteUser(user_id);
		if (addNum > 0) {
			map.put("msg", "删除用户成功");

		} else {
			map.put("msg", "删除用户失败");
		}

		return map;
	}

	/**
	 * description(修改公司认证状态之前先根据user_id查询服务商公司认证的状态)
	 * 
	 * @param
	 * @param user_id
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryCompany/{user_id}")
	public String queryCompany(@PathVariable Integer user_id, Model model) {
		YdMangerUserVo ydMangerUser = ydMangerServiceSystemUser.queryCompanyByUserId(user_id);
		model.addAttribute("ydMangerUser", ydMangerUser);
		return "system/yhgl/fuwushangcompanyupdate";
	}

	/**
	 * description(修改实名认证状态之前先根据user_id查询实名认证的信息)
	 * 
	 * @param
	 * @param user_id
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryVerified/{user_id}")
	public String queryVerified(@PathVariable Integer user_id, Model model) {
		YdMangerVerified ydMangerVerified = ydMangerMapperVerified.$queryByUserId(user_id);
		model.addAttribute("ydMangerVerified", ydMangerVerified);
		return "system/yhgl/fuwushangidentityverification";
	}

	/**
	 * description(修改公司状态)
	 * 
	 * @param
	 * @param ydMangerUserVo
	 * @return
	 */
	@RequestMapping("/updateUCstate")
	@ResponseBody
	public Object updateUCstate(YdMangerUser ydMangerUserVo) {
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapperFactory.doIt(ydMangerUserVo);
		Integer updateNum = ydMangerServiceSystemUser.updateUCstateByUserId(ydMangerUserVo);

		if (updateNum > 0) {
			map.put("msg", true);
		} else {
			map.put("msg", false);
		}
		return map;
	}

	/**
	 * description(很据user_id修改个人技能状态)
	 * 
	 * @param
	 * @param ydMangerUserLabelVo
	 * @return
	 */
	@RequestMapping("/updateUCSkillState")
	@ResponseBody
	public Object updateUSkillState(YdMangerUserLabelVo ydMangerUserLabelVo) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer userl_uid = ydMangerUserLabelVo.getUserl_uid();
		Integer user_skillstate = ydMangerUserLabelVo.getUser_skillstate();

		// System.out.println( ydMangerUserLabelVo.getUser_skillstate() );

		YdMangerUserVo ydMangerUserVo = ydMangerServiceSystemUser.queryUserByUserId(userl_uid);
		ydMangerUserVo.setUser_skillstate(user_skillstate);
		Integer updateNum = ydMangerServiceSystemUser.updateUserSkillStateByUsreId(ydMangerUserVo);
		if (updateNum > 0) {
			map.put("msg", true);
		} else {
			map.put("msg", false);
		}
		return map;
	}

	/**
	 * description(根据指定的user_id查询实名认证的所有信息)
	 * 
	 * @param
	 * @param user_id
	 * @param model
	 * @return
	 */
	@RequestMapping("/updateUCverifiedStart")
	@ResponseBody
	public Object updateUCverifiedStart(Integer user_id, Integer user_verified) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer updateNum = ydMangerServiceSystemUser.updateUserVerifiedStateByUsreId(user_id, user_verified);
		if (updateNum > 0) {
			map.put("msg", true);
		} else {
			map.put("msg", false);
		}
		return map;
	}

	/**
	 * description(根据指定的user_id查询服务商的所有信息)
	 * 
	 * @param
	 * @param user_id
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryServerByUserId/{user_id}")
	public String queryServerByUserId(@PathVariable Integer user_id, Model model) {
		YdMangerUserVo ydMangerUser = ydMangerServiceSystemUser.queryServerByUserId(user_id);
		model.addAttribute("ydMangerUser", ydMangerUser);
		return "system/yhgl/fuwushangdetails";
	}

	/**
	 * description(添加系统消息发给个人时先根据用户昵称查询用户是否存在)
	 * 
	 * @param
	 * @param user_nickname
	 * @return
	 */
	@RequestMapping("/queryUserByNickName")
	@ResponseBody
	public Object queryUserByNickName(String user_nickname) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (user_nickname.equals("全部")) {
			map.put("msg", 200);

			return map;
		}
		YdMangerUser ydMangerUser = ydMangerServiceSystemUser.queryUserByNickName(user_nickname);
		if (ydMangerUser == null) {
			map.put("msg", false);
		} else {
			map.put("msg", true);
		}
		return map;
	}

	/**
	 * description(根据user_id冻结用户)
	 * 
	 * @param
	 * @param user_id
	 * @return
	 */
	@RequestMapping("/queryServerByUserId/updateUserByUserId")
	@ResponseBody
	public Object updateUserByUserId(Integer user_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		YdMangerUserVo ydMangerUserVo = ydMangerServiceSystemUser.queryServerByUserId(user_id);
		if (ydMangerUserVo == null) {
			// 用户不存在
			map.put("msg", 100);
		} else {
			Integer updateNum = ydMangerServiceSystemUser.updateUserByUserId(ydMangerUserVo);
			if (updateNum > 0) {
				// 冻结成功
				map.put("msg", 200);
			} else {
				// 冻结失败
				map.put("msg", 300);
			}
		}
		return map;
	}

	/**
	 * description(根据user_id恢复冻结的用户)
	 * 
	 * @param
	 * @param user_id
	 * @return
	 */
	@RequestMapping("/queryServerByUserId/renewUserByUserId")
	@ResponseBody
	public Object renewUserByUserId(Integer user_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		YdMangerUserVo ydMangerUserVo = ydMangerServiceSystemUser.queryServerByUserId(user_id);
		if (ydMangerUserVo == null) {
			// 用户不存在
			map.put("msg", 100);
		} else {
			Integer updateNum = ydMangerServiceSystemUser.renewUserByUserId(ydMangerUserVo);
			if (updateNum > 0) {
				// 冻结成功
				map.put("msg", 200);
			} else {
				// 冻结失败
				map.put("msg", 300);
			}
		}
		return map;
	}

}
