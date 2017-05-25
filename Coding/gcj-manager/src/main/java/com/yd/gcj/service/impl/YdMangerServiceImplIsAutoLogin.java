package com.yd.gcj.service.impl;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.YdMangerMsgSize;
import com.yd.gcj.entity.YdMangerUserLabel;
import com.yd.gcj.entity.vo.YdMangerUserVo;
import com.yd.gcj.mapper.YdMangerMapperAttention;
import com.yd.gcj.mapper.YdMangerMapperMessage;
import com.yd.gcj.mapper.YdMangerMapperUser;
import com.yd.gcj.mapper.YdMangerMapperUserLabel;
import com.yd.gcj.service.YdMangerServiceUserIsAutoLogin;
import com.yd.gcj.util.MyStaticFactory;

@Service("YdMangerServiceUserIsAutoLogin")
public class YdMangerServiceImplIsAutoLogin implements YdMangerServiceUserIsAutoLogin{


	@Autowired
	private YdMangerMapperUser mapperUser;

	@Autowired
	private YdMangerMapperUserLabel mapperUserLabel;

	@Autowired
	private YdMangerMapperAttention mapperAttention;

	@Autowired
	private YdMangerMapperMessage mapperMessage;
	
	@Override
	public boolean IsAutoLogin(HttpServletRequest request){
		boolean is = false;
		System.out.println("进来了");
			Cookie[] cookies = request.getCookies();
			for(Cookie cookie : cookies){
				if(cookie.getName().equals("userIsAutoLogin")){
					String phone = cookie.getValue();
					YdMangerUserVo userVo = mapperUser.$queryByPhone(phone);
					userVo.setUser_pwd(null);
					userVo.setUser_ppwd(null);
					List<YdMangerUserLabel> labels = mapperUserLabel.$queryAll(userVo.getUser_id());
					userVo.setUserLabels(labels);
					Integer attNum = mapperAttention.$queryACountNum(userVo.getUser_id());
					userVo.setAttentionNum(attNum);
					YdMangerMsgSize msgSize = (YdMangerMsgSize) mapperMessage.$queryAllTypeMsgSize(userVo.getUser_id());
					request.getSession().setAttribute("msgSize", msgSize);
					request.getSession().setAttribute("user", userVo);
					System.out.println("找到了");
					is = true;
				}
			}
		if(MyStaticFactory.num == 0){
			MyStaticFactory.num++;
		}
		System.out.println(is);
		return is;
	}
	

}
