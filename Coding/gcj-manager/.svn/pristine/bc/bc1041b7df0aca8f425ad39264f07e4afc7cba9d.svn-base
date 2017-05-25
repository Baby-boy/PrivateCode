package com.yd.gcj.controller.page;

import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yd.gcj.entity.YdMangerPhoneCode;
import com.yd.gcj.entity.vo.YdMangerUserVo;
import com.yd.gcj.service.YdMangerServiceUser;
import com.yd.gcj.tool.MapInitFactory;
import com.yd.gcj.util.YdMangerSmsFactory;

@RestController
@RequestMapping(value = "/page/sendCode", produces = { "application/json;charset=UTF-8" })
public class YdMangerControllerPageSendPhoneCode {

	@Autowired
	private YdMangerServiceUser serviceUser;

	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public Object send(String phone, String sessionName, HttpServletRequest request) {
		HttpSession session = request.getSession();
		MapInitFactory mapInitFactory = new MapInitFactory();
		long stime = 60000;
		Date date = new Date();
		long nowtime = date.getTime();
		YdMangerUserVo userVo = (YdMangerUserVo) session.getAttribute("user");
		if (userVo != null) {
			try {
				Integer isExist = serviceUser.$userIsExist(phone);
				if (isExist > 0) {
					YdMangerPhoneCode pc = (YdMangerPhoneCode) session.getAttribute(sessionName);
					if (pc == null) {
						pc = new YdMangerPhoneCode();
					}
					if (pc.getTime() == 0 || (nowtime - pc.getTime()) > stime) {
						YdMangerSmsFactory ysf = new YdMangerSmsFactory();
						String phoneCode = code();
						mapInitFactory = ysf.sendPhoneCode(phone, phoneCode, "");
						String success = (String) mapInitFactory.getMap().get("success");
						if (success != null && success.equals("true")) {
							pc = new YdMangerPhoneCode();
							pc.setPhone(phone);
							pc.setCode(phoneCode);
							pc.setTime(date.getTime());
							session.setAttribute(sessionName, pc);
						}
					} else {
						mapInitFactory.setMsg("501", "每分钟内只能发送一条信息！");
					}
				} else {
					mapInitFactory.setMsg("505", "该手机号不存在！");
				}
			} catch (Exception e) {
				mapInitFactory.setSystemError();
			}
		} else {
			mapInitFactory.setMsg("600", "对不起，您没有权限操作此功能！");
		}
		return mapInitFactory.getMap();
	}

	@RequestMapping(value = "/registSend", method = RequestMethod.POST)
	public Object registSend(String phone, HttpServletRequest request) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		long stime = 60000;
		Date date = new Date();
		long nowtime = date.getTime();
		try {
			Integer isExist = serviceUser.$userIsExist(phone);
			if (isExist == 0) {
				YdMangerPhoneCode pc = (YdMangerPhoneCode) request.getSession().getAttribute("phoneCodeMsg");
				if (pc == null) {
					pc = new YdMangerPhoneCode();
				}
				if (pc.getTime() == 0 || (nowtime - pc.getTime()) > stime) {
					YdMangerSmsFactory ysf = new YdMangerSmsFactory();
					String phoneCode = code();
					mapInitFactory = ysf.sendPhoneCode(phone, phoneCode, "注册");
					String success = (String) mapInitFactory.getMap().get("success");
					System.out.println(success);
					if (success != null && success.equals("true")) {
						pc = new YdMangerPhoneCode();
						pc.setPhone(phone);
						pc.setCode(phoneCode);
						pc.setTime(date.getTime());
						request.getSession().setAttribute("phoneCodeMsg", pc);
						System.out.println("发送注册验证码！");
					}
				} else {
					mapInitFactory.setMsg("501", "每分钟内只能发送一条信息！");
				}
			} else {
				mapInitFactory.setMsg("505", "手机号已被注册！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

	@RequestMapping(value = "/sendforget", method = RequestMethod.POST)
	public Object sendforget(String phone, HttpServletRequest request) {
		HttpSession session = request.getSession();
		MapInitFactory mapInitFactory = new MapInitFactory();
		long stime = 60000;
		Date date = new Date();
		long nowtime = date.getTime();
		try {
			Integer isExist = serviceUser.$userIsExist(phone);
			if (isExist > 0) {
				YdMangerPhoneCode pc = (YdMangerPhoneCode) session.getAttribute("wjmm");
				if (pc == null) {
					pc = new YdMangerPhoneCode();
				}
				if (pc.getTime() == 0 || (nowtime - pc.getTime()) > stime) {
					YdMangerSmsFactory ysf = new YdMangerSmsFactory();
					String phoneCode = code();
					mapInitFactory = ysf.sendPhoneCode(phone, phoneCode, "");
					String success = (String) mapInitFactory.getMap().get("success");
					System.out.println(success);
					if (success != null && success.equals("true")) {
						pc = new YdMangerPhoneCode();
						pc.setPhone(phone);
						pc.setCode(phoneCode);
						pc.setTime(date.getTime());
						session.setAttribute("wjmm", pc);
					}
				} else {
					mapInitFactory.setMsg("501", "每分钟内只能发送一条信息！");
				}
			} else {
				mapInitFactory.setMsg("505", "该手机号不存在！");
			}
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

	private String code() {
		Random rand = new Random();
		int indexNum = 6;
		StringBuffer sb = new StringBuffer();
		sb.append(rand.nextInt());
		sb = new StringBuffer(sb.substring(sb.length() - indexNum, sb.length()));
		return sb.toString();
	}
}
