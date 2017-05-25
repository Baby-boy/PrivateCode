package com.yd.gcj.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.YdMangerSkill;
import com.yd.gcj.entity.YdMangerUser;
import com.yd.gcj.entity.YdMangerUserLabel;
import com.yd.gcj.mapper.YdMangerMapperSkill;
import com.yd.gcj.mapper.YdMangerMapperUser;
import com.yd.gcj.mapper.YdMangerMapperUserLabel;
import com.yd.gcj.service.YdMangerServiceUser;
import com.yd.gcj.tool.MD5Util;
import com.yd.gcj.tool.MapFactory;
import com.yd.gcj.tool.MapInitFactory;
import com.yd.gcj.tool.Values;
import com.yd.gcj.util.YdMangerRegistEntity;

@Service("YdMangerServiceUser")
public class YdMangerServiceImplUser implements YdMangerServiceUser {

	@Autowired
	private YdMangerMapperUser mapperUser;
	
	@Autowired
	private YdMangerMapperUserLabel mapperUserLabel;
	
	@Autowired
	private YdMangerMapperSkill mapperSkill;
	
	@Override
	public Object $queryAll(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		
		try {
			Integer startPageNum = (Integer) map.get("startPageNum");
			Integer queryPageNum = (Integer) map.get("queryPageNum");
			mapInitFactory.init().setData(mapperUser.$queryAll(startPageNum, queryPageNum));
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		
		return mapInitFactory.getMap();
	}
	
	@Override
	public Object $queryCountByRSP(HashMap<String, Object> map){
		Integer countNum = mapperUser.$queryCountByRSP();
		return countNum;
	}
	
	@Override
	public Object $queryRSP(){
		Integer countNum = mapperUser.$queryCountByRSP();
		List<YdMangerUser> users = new ArrayList<YdMangerUser>();
		if(countNum>0){
			users = mapperUser.$queryRSP(0,10);
		}
		return users;
	}
	
	@Override
	public Object $queryById(HashMap<String, Object> map) {
		Integer user_id = (Integer) map.get("user_id");
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			YdMangerUser user = mapperUser.$queryById(user_id);
			user.setUser_pwd(null);
			user.setUser_ppwd(null);
			mapInitFactory.init().setData(user);
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object $userIsExist(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			/*mapInitFactory.init().setData();*/
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object $regist(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			if(map.get("registEntity")!=null){
				YdMangerRegistEntity registEntity = (YdMangerRegistEntity) map.get("registEntity");
				Integer isExist = mapperUser.$userIsExist(registEntity.getUser_phone());
				if(isExist>0){
					mapInitFactory.setMsg("501", "手机号已存在");
				}else{
					/*if(registEntity.getUser_phone()==null||registEntity.getUser_phone().isEmpty()){
						mapInitFactory.setMsg("504", "请输入账号！");
						return mapInitFactory.getMap();
					}else if(registEntity.getUser_phone().length()!=11||!registEntity.getUser_phone().substring(0,1).equals("1")){
						mapInitFactory.setMsg("505", "手机号格式不正确！");
						return mapInitFactory.getMap();
					}else if(registEntity.getUser_pwd()==null){
						mapInitFactory.setMsg("506", "密码不能为空！");
						mapInitFactory.getMap();
					}else if(registEntity.getUser_pwd().length()<8){
						mapInitFactory.setMsg("507", "密码不能小于8位！");
						return mapInitFactory.getMap();
					}*/
					registEntity.setUser_pwd(MD5Util.textToMD5L32(registEntity.getUser_pwd()+"#@"));
					
					Date date = new Date();
					registEntity.setUser_create_time(date);
					registEntity.setUser_update_time(date);
					
					Integer success = mapperUser.$regist(registEntity);
					
					if(success>0){
						mapInitFactory.setMsg(Values.INITSUCCESSCODE, "注册成功");
						HttpSession session = (HttpSession) map.get("session");
						registEntity.setUser_pwd(null);
						registEntity.setPhoneCode(null);
						
						session.setAttribute("user",registEntity);
					}else{
						mapInitFactory.setMsg("502", "注册失败");
					}
				}
			}else{
				mapInitFactory.setMsg("503", "网络繁忙，请稍后再试！");
			}
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object $login(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			YdMangerUser u = (YdMangerUser) map.get("user");
			
			HttpSession session = (HttpSession) map.get("session");
			YdMangerUser user = mapperUser.$queryByPhoneAndType(u.getUser_phone(),u.getUser_type());
			if(user!=null){
				if(user.getUser_pwd().equals(MD5Util.textToMD5L32(u.getUser_pwd()+"#@"))){
					
					//清楚实体类不需要返回前台的数据
					user.setUser_pwd(null);
					user.setUser_ppwd(null);
					
					session.setAttribute("user", user);
					
					mapInitFactory.setMsg(Values.INITSUCCESSCODE, "登录成功").setData(user);
				}else{
					mapInitFactory.setMsg("502", "密码不正确");
				}
			}else{
				mapInitFactory.setMsg("501", "账号不存在");
			}
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object $queryCountNumBySql(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			/*mapInitFactory.init().setData();
			 * ydMangerMapperUser
			 * */
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object $updateMsg(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			
			YdMangerUser user = (YdMangerUser) MapFactory.toObject(map, YdMangerUser.class);
			user.setUser_update_time(new Date());
			
			Integer success = mapperUser.$update(user);
			if(success>0){
				mapInitFactory.setMsg(Values.INITSUCCESSCODE, "修改成功");
			}else{
				mapInitFactory.setMsg("501", "修改失败");
			}
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object $updatePwd(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		
		try {
			String pwd = map.get("user_pwd").toString();
			String opwd = map.get("user_opwd").toString();
			String phone = map.get("user_phone").toString();
			
			if(pwd!=null&&opwd!=null&&phone!=null){
				Integer isId = mapperUser.$queryIdByPhoneAndPwd(phone,opwd);
				if(isId>0){
					Integer success = mapperUser.$updatePwd(isId, pwd);
					if(success>0){
						mapInitFactory.setMsg(Values.INITSUCCESSCODE, "修改成功");
					}else{
						mapInitFactory.setMsg("503", "修改失败");
					}
				}else{
					mapInitFactory.setMsg("502", "此用户不存在");
				}
			}else{
				mapInitFactory.setMsg("501","参数有误");
			}
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		
		return mapInitFactory.getMap();
	}

	@Override
	public Object $updatePPwd(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		
		try {
			String ppwd = map.get("user_ppwd").toString();
			String phone = map.get("user_phone").toString();
			
			if(ppwd!=null&&phone!=null){
				Integer isId = mapperUser.$queryIdByPhone(phone);
				if(isId>0){
					Integer success = mapperUser.$updatePPwd(isId, ppwd);
					if(success>0){
						mapInitFactory.setMsg(Values.INITSUCCESSCODE, "修改成功");
					}else{
						mapInitFactory.setMsg("503", "修改失败");
					}
				}else{
					mapInitFactory.setMsg("502", "此用户不存在");
				}
			}else{
				mapInitFactory.setMsg("501","参数有误");
			}
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		
		return mapInitFactory.getMap();
	}

	@Override
	public Object $labelCFA(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			
			Integer userl_skillid = (Integer) map.get("skill_id");//获取技能id
			Integer userl_uid = (Integer) map.get("user_id");//获取当前用户id
			Integer userl_skilltype = (Integer) map.get("userl_skilltype");//获取技能类型（级别）
			
			//检查该用户是否已经存在相应类型（级别）的技能
			YdMangerUserLabel userLabel = mapperUserLabel.$queryByUidAndSkilltype(userl_uid, userl_skilltype);
			//获取用户需要添加的技能信息
			YdMangerSkill skill = mapperSkill.$queryById(userl_skillid);
			//用户是否已经有该类型（级别）的技能
			if(userLabel!=null){
				//判断数据库里面是否存在此技能信息
				if(skill!=null){
					if(userl_skillid!=skill.getSkill_id()){//判断用户的相应类型的技能是否不是同一个
						
						//如果用户已经有该类型的技能，并且已有的与需要添加的不是同一个，则将原来的技能信息替换成新的技能信息
						userLabel.setUserl_skillid(skill.getSkill_id());
						userLabel.setUserl_skilldesc(skill.getSkill_desc());
						userLabel.setUserl_uid(userl_uid);
						userLabel.setUserl_skilltype(skill.getSkill_type());
						
						//更新已有且与新技能不同的技能信息
						Integer success = mapperUserLabel.$update(userLabel);
						if(success>0){
							mapInitFactory.setMsg(Values.INITSUCCESSCODE, "添加成功");
						}else{
							mapInitFactory.setMsg("503", "添加失败");
						}
					}else{
						mapInitFactory.setMsg("502", "已经添加有此技能");
					}
				}else{
					mapInitFactory.setMsg("501", "没有查询到技能数据");
				}
			}else{
				//如果用户还没有跟需要新添加的技能相同类型的技能信息则新生成此技能信息并添加
				userLabel = new YdMangerUserLabel();
				userLabel.setUserl_skillid(skill.getSkill_id());
				userLabel.setUserl_skilldesc(skill.getSkill_desc());
				userLabel.setUserl_uid(userl_uid);
				userLabel.setUserl_skilltype(skill.getSkill_type());
				//插入新技能信息
				Integer success = mapperUserLabel.$insert(userLabel);
				if(success>0){
					mapInitFactory.setMsg(Values.INITSUCCESSCODE, "添加成功");
				}else{
					mapInitFactory.setMsg("503", "添加失败");
				}
			}
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object $enterCFA(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			
			Integer user_id = (Integer) map.get("user_id");//获取用户id
			String user_ename = map.get("user_ename").toString();//获取公司名称
			
			if(user_id!=null&&user_ename!=null){
				Integer success = mapperUser.$enterCFA(user_id, 1, user_ename);
				if(success>0){
					mapInitFactory.setMsg(Values.INITSUCCESSCODE, "提交成功，后台审核中");
				}else{
					mapInitFactory.setMsg("502", "提交失败");
				}
			}else{
				mapInitFactory.setMsg("501", "数据异常");
			}
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object $updateResume(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			Integer user_id = (Integer) map.get("user_id");
			String user_resume = map.get("user_resume").toString();//获取用户简历信息
			
			YdMangerUser user = new YdMangerUser();
			user.setUser_id(user_id);
			user.setUser_resume(user_resume);
			
			Integer success = mapperUser.$update(user);
			
			if(success > 0){
				mapInitFactory.setMsg(Values.INITSUCCESSCODE, "更新成功");
			}else{
				mapInitFactory.setMsg("501", "更新失败");
			}
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object $addBankMsg(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object $delBankMsg(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

	
}
