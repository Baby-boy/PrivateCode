package com.yd.gcj.util;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yd.gcj.mapper.YdMangerMapperUser;
import com.yd.gcj.tool.MapInitFactory;
import com.yd.gcj.tool.Values;

public class YdMangerSendCode {
	
	@Autowired
	private YdMangerMapperUser ydMangerMapperUser;
	
	public Object noUserNameSendCode(String phoneNum,HttpSession session) throws JsonProcessingException{
		MapInitFactory mapInitFactory = new MapInitFactory();
		if(phoneNum!=null){
			if(phoneNum.length()==11&&phoneNum.substring(0,1).equals("1")){
				Integer isExist = ydMangerMapperUser.$userIsExist(phoneNum);
				if(isExist==0){
			        mapInitFactory.setMsg(Values.INITSUCCESSCODE, "短信发送成功，请注意接收！");
			        session.setAttribute(session.getId(), phoneNum);
				}else{
					mapInitFactory.setMsg("503", "手机号已存在，不能重复注册！");
				}
			}else{
				mapInitFactory.setMsg("502", "手机号格式不正确");
			}
		}else{
			mapInitFactory.setMsg("501", "手机号不能为空！");
		}
		return mapInitFactory.getMap();
	}
	
	
	
	
}
