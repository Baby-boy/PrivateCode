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
import com.yd.gcj.entity.vo.YdMangerUserVo;
import com.yd.gcj.system.service.YdMangerServiceSystemUser;

/**
 * description(雇主的信息管理)
 * @author Administrator
 * @param <HttpServletRequest>
 */
@Controller
@RequestMapping("/system")
public class YdMangerControllerSystemEmployer<HttpServletRequest> {
	
	@Autowired
	private YdMangerServiceSystemUser ydMangerServiceSystemUser;
	
	/**
	 * description(查询所有的雇主用户信息)
	 * @param
	 * @param model
	 * @return
	 */
	@RequestMapping( "/queryAllUser" )
	public String queryAllUser(Integer p, String user_phone,Model model){
		
		//当前页
		if (p==null) {
			p=1;
			PageHelper .startPage(p,8);
		}else {
			PageHelper .startPage(p,8);
		};
		List<YdMangerUser> userList = ydMangerServiceSystemUser.queryAllUser(user_phone);
		PageInfo<YdMangerUser> pageInfo = new PageInfo<YdMangerUser>(userList);
		//总页数
		//总页数
		Integer totalPage = null;
		Integer total = (int) pageInfo.getTotal();
		if (total%8 !=0) {
			totalPage = total/8+1;
		}else {
			totalPage = total/8;
		}
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("p",p);
		model.addAttribute("user_phone",user_phone);
		model.addAttribute("userList", userList);
		return "system/yhgl/employer";
	}

	/**
	 * description(添加雇主用户信息)
	 * @param
	 * @param ydMangerUser
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addUser", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String addUser(YdMangerUser ydMangerUser,Model model){
		Map<String, Object> map = new HashMap<String, Object>(); 
		Integer addNum =  ydMangerServiceSystemUser.addUser(ydMangerUser);
		if (addNum>0) {
			map.put("msg",true);
			
		}else {
			map.put("msg",false);
		}
		model.addAttribute("map", map);
		return "userInfo";
	}
	
	/**
	 * description(删除雇主用户)
	 * @param
	 * @param user_id
	 * @param model
	 * @return
	 */
	@RequestMapping("/deleteUser")
	@ResponseBody
	public Object deleteUser(Integer user_id,Model model){
		Map<String, Object> map = new HashMap<String, Object>(); 
		Integer addNum =  ydMangerServiceSystemUser.deleteUser(user_id);
		if (addNum>0) {
			map.put("msg",true);
			
		}else {
			map.put("msg",false);
		}
		model.addAttribute("map", map);
		return map;
	}
	
	/**
	 * description(修改雇主用户信息)
	 * @param
	 * @param ydMangerUser
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/updateUser", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String updateUserById(YdMangerUser ydMangerUser,Model model){
		Map<String, Object> map = new HashMap<String, Object>(); 
		Integer addNum =  ydMangerServiceSystemUser.updateUserById(ydMangerUser);
		if (addNum>0) {
			map.put("msg",true);
			
		}else {
			map.put("msg",false);
		}
		return "userInfo";
	}
	
	/**
	 * description(根据指定的user_id查询雇主的所有信息)
	 * @param
	 * @param user_id
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryEmployerByUserId/{user_id}")
	public String queryEmployerByUserId(@PathVariable Integer user_id,Model model){
		YdMangerUserVo ydMangerUser = ydMangerServiceSystemUser.queryUserByUserId(user_id);
		model.addAttribute("ydMangerUser", ydMangerUser);
		return "system/yhgl/employerdetails";
	}
	
	/**
	 * description(根据user_id冻结用户)
	 * @param
	 * @param user_id
	 * @return
	 */
	@RequestMapping("/queryEmployerByUserId/updateUserByUserId")
	@ResponseBody
	public Object updateUserByUserId(Integer user_id){
		Map<String,Object> map = new HashMap<String, Object>();
		YdMangerUserVo ydMangerUserVo = ydMangerServiceSystemUser.queryServerByUserId(user_id);
		if (ydMangerUserVo==null) {
			//用户不存在
			map.put("msg",100);
		}else {
			Integer updateNum = ydMangerServiceSystemUser.updateUserByUserId(ydMangerUserVo);
			if (updateNum>0) {
				//冻结成功
				map.put("msg",200);
			}else {
				//冻结失败
				map.put("msg", 300);
			}
		}
		return map;
	}
	
	/**
	 * description(根据user_id恢复冻结的用户)
	 * @param
	 * @param user_id
	 * @return
	 */
	@RequestMapping("/queryEmployerByUserId/renewUserByUserId")
	@ResponseBody
	public Object renewUserByUserId(Integer user_id){
		Map<String,Object> map = new HashMap<String, Object>();
		YdMangerUserVo ydMangerUserVo = ydMangerServiceSystemUser.queryServerByUserId(user_id);
		if (ydMangerUserVo==null) {
			//用户不存在
			map.put("msg",100);
		}else {
			Integer updateNum = ydMangerServiceSystemUser.renewUserByUserId(ydMangerUserVo);
			if (updateNum>0) {
				//冻结成功
				map.put("msg",200);
			}else {
				//冻结失败
				map.put("msg", 300);
			}
		}
		return map;
	}

}
