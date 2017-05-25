package com.yd.gcj.controller.page;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yd.gcj.entity.YdMangerAttention;
import com.yd.gcj.entity.vo.YdMangerUserVo;
import com.yd.gcj.service.YdMangerServiceAttention;
import com.yd.gcj.tool.MapInitFactory;

@RestController
@RequestMapping(value = "/page/att", produces = { "application/json;charset=UTF-8" })
public class YdMangerControllerPageAttention {
	@Autowired
	private YdMangerServiceAttention ydMangerServiceAttention;

	/***
	 * 关注用户
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/addAtten", method = RequestMethod.POST)
	public Object addAtten(Integer userBId, HttpServletRequest request) {
		YdMangerUserVo userVo = (YdMangerUserVo) request.getSession().getAttribute("user");
		try {
			if (userVo != null) {
				YdMangerAttention attention = new YdMangerAttention();
				attention.setAtt_aid(userVo.getUser_id());
				attention.setAtt_bid(userBId);
				attention.setAtt_create_time(new Date());
				return ydMangerServiceAttention.$addAtten(attention);
			} else {
				return new MapInitFactory().setMsg("503", "还没有登录或登录超时，请登录后再做操作！").getMap();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new MapInitFactory().setSystemError().getMap();
		}

	}

	/***
	 * 取消关注
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/delAtten", method = RequestMethod.POST)
	public Object delAtten(Integer userBId, HttpServletRequest request) {
		try {
			YdMangerUserVo userVo = (YdMangerUserVo) request.getSession().getAttribute("user");
			if (userVo != null) {
				return ydMangerServiceAttention.$delAtten(userVo.getUser_id(), userBId);
			} else {
				return new MapInitFactory().setMsg("501", "登录超时，请重新登录！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new MapInitFactory().setSystemError().getMap();
		}

	}

}
