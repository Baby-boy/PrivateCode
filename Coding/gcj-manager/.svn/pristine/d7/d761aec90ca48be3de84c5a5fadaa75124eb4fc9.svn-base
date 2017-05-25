package com.yd.gcj.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yd.gcj.entity.YdMangerSystemAdmin;
import com.yd.gcj.system.service.YdMangerServiceSystemAdmin;

/**
 * description(管理员的信息管理)
 * 
 * @author Administrator
 * @param <HttpServletRequest>
 */
@Controller
@RequestMapping("/system")
public class YdMangerControllerSystemAdmin<HttpServletRequest> {

	@Autowired
	private YdMangerServiceSystemAdmin ydMangerServiceSystemAdmin;

	// 正则校验手机号码
	public static final String REGEX_MOBILE = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

	/**
	 * description(查询所有的管理员信息)
	 * 
	 * @param
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryAllAdmin")
	public String queryAllAdmin(Integer p, String admin_name, Integer admin_is_super, Model model,
			javax.servlet.http.HttpServletRequest request) {

		// 当前页
		if (p == null) {
			p = 1;
			PageHelper.startPage(p, 8);
		} else {
			PageHelper.startPage(p, 8);
		}
		;

		List<YdMangerSystemAdmin> adminList = ydMangerServiceSystemAdmin.queryAllAdmin(admin_name, admin_is_super);
		PageInfo<YdMangerSystemAdmin> pageInfo = new PageInfo<YdMangerSystemAdmin>(adminList);

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
		model.addAttribute("adminList", adminList);
		model.addAttribute("admin_name", admin_name);
		model.addAttribute("admin_is_super", admin_is_super);
		return "system/gly/admin";
	}

	/*
	 * public String queryAdminByCondition(String admin_name,Integer
	 * admin_is_super){
	 * 
	 * return ""; }
	 */

	/**
	 * description(修改之前先根据admin_id查到当前用户)
	 * 
	 * @param
	 * @param admin_id
	 * @param model
	 * @return
	 */
	@RequestMapping("/updateAdminByAdminId/{admin_id}")
	public String queryAdminByAdminId(@PathVariable Integer admin_id, Model model) {
		YdMangerSystemAdmin ydMangerSystemAdmin = ydMangerServiceSystemAdmin.queryAdminByAdminId(admin_id);
		model.addAttribute("ydMangerSystemAdmin", ydMangerSystemAdmin);
		return "system/gly/adminupdate";
	}

	@RequestMapping("/updateAdmin")
	@ResponseBody
	public Object updateAdminByAdminId(YdMangerSystemAdmin ydMangerSystemAdmin) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer updateNum = ydMangerServiceSystemAdmin.updateAdmin(ydMangerSystemAdmin);
		if (updateNum > 0) {
			map.put("msg", true);
		} else {
			map.put("msg", false);
		}
		return map;
	}

	/**
	 * description(添加管理员)
	 * 
	 * @param
	 * @param ydMangerAdmin
	 * @param model
	 * @return
	 */
	@RequestMapping("/addAdmin")
	@ResponseBody
	public Object addAdmin(YdMangerSystemAdmin ydMangerSystemAdmin, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (Pattern.matches(REGEX_MOBILE, ydMangerSystemAdmin.getAdmin_phone())) {
			Integer adminNum = ydMangerServiceSystemAdmin.addAdmin(ydMangerSystemAdmin);
			if (adminNum > 0) {
				// 添加成功
				map.put("msg", true);
			} else {
				map.put("msg", false);
			}
		} else {
			map.put("msg", 100);
		}
		return map;
	}

	/**
	 * description(根据指定的admin_id删除管理员)
	 * 
	 * @param
	 * @param admin_id
	 * @param model
	 * @param map
	 * @return
	 */
	@RequestMapping("/deleteAdmin")
	@ResponseBody
	public Object deleteAdminByAdminId(Integer admin_id, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer delNum = ydMangerServiceSystemAdmin.deleteAdmin(admin_id);
		if (delNum > 0) {
			map.put("msg", true);
		} else {
			map.put("msg", false);
		}

		return map;
	}

	/**
	 * description(批量删除)
	 * 
	 * @param
	 * @param map
	 * @param admin_ids
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/deleteBatch", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object deleteBatchAdminByAdminIds(List<Integer> admin_ids, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer delNum = ydMangerServiceSystemAdmin.deleteBatchAdminByAdminIds(admin_ids);
		if (delNum > 0) {

			map.put("msg", true);
		} else {
			map.put("msg", false);
		}

		return map;
	}

	/**
	 * description(ajax验证手机号码的唯一性)
	 * 
	 * @param
	 * @param admin_phone
	 * @return
	 */
	@RequestMapping("/validateTelNum")
	@ResponseBody
	public Object valitedateTelNum(String admin_phone) {
		Map<String, Object> map = new HashMap<String, Object>();
		YdMangerSystemAdmin ydMangerSystemAdmin = ydMangerServiceSystemAdmin.queryAdminByAdminPhon(admin_phone);
		if (ydMangerSystemAdmin == null) {
			map.put("msg", true);
		} else {
			map.put("msg", false);
		}
		return map;
	}

	/**
	 * description(ajax验证管理员名称的唯一性)
	 * 
	 * @param
	 * @param admin_name
	 * @return
	 */
	@RequestMapping("/validateAdminName")
	@ResponseBody
	public Object validateAdminName(String admin_name) {
		Map<String, Object> map = new HashMap<String, Object>();
		YdMangerSystemAdmin ydMangerSystemAdmin = ydMangerServiceSystemAdmin.queryAdminByAdminName(admin_name);
		if (ydMangerSystemAdmin == null) {
			map.put("msg", true);
		} else {
			map.put("msg", false);
		}
		return map;
	}

	/**
	 * description(ajax验证管理员账号的唯一性)
	 * 
	 * @param
	 * @param admin_account
	 * @return
	 */
	@RequestMapping("/validateAdminAccount")
	@ResponseBody
	public Object validateAdminAccount(String admin_account) {
		Map<String, Object> map = new HashMap<String, Object>();
		YdMangerSystemAdmin ydMangerSystemAdmin = ydMangerServiceSystemAdmin.queryAdminByAdminAccount(admin_account);
		if (ydMangerSystemAdmin == null) {
			map.put("msg", true);
		} else {
			map.put("msg", false);
		}
		return map;
	}

	@RequestMapping("/changeAdminPassword")
	@ResponseBody
	public Object changeAdminPassword(String admin_account, String admin_password, String newpassword,
			String renewpassword) {
		Map<String, Object> map = new HashMap<String, Object>();
		YdMangerSystemAdmin systemAdmin = ydMangerServiceSystemAdmin
				.queryAdminByUserAccountAndUserPassword(admin_account, admin_password);
		if (systemAdmin == null) {
			// 当前账号不存在
			map.put("msg", 100);
		} else {
			if (!newpassword.equals(renewpassword)) {
				// 两次密码输入 不一致
				map.put("msg", 300);
			} else {
				Integer admin_id = systemAdmin.getAdmin_id();
				Integer updateNum = ydMangerServiceSystemAdmin.updateAdminPassword(admin_id, newpassword);
				if (updateNum > 0) {
					// 修改成功
					map.put("msg", 200);
				} else {
					// 修改失败
					map.put("msg", 400);
				}
			}
		}
		return map;
	}
}
