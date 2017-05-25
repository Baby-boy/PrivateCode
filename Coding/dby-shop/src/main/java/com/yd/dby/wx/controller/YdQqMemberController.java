package com.yd.dby.wx.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yd.dby.YdMain;
import com.yd.dby.a.sys.entity.YdSysUserFull;
import com.yd.dby.a.sys.service.YdSysServiceSecurity;
import com.yd.dby.wx.utils.YdWxParamUtils;

/**
 * @author 作者 E-mail:
 * @version 创建时间：2017年2月17日 下午1:44:58
 * 
 */
@SuppressWarnings("all")
@Controller
@RequestMapping(value = "/qq")
public class YdQqMemberController {
	
	@Autowired
	private YdSysServiceSecurity ydSysServiceSecurity;

	@Autowired
	private HttpSession session;
	
	// public static final String REDIRECT_URI = "http://dbyqq.tunnel.2bdata.com/qq";
	public static final String REDIRECT_URI = "https://www.wadby.com/qq";
	public static final String CLIENT_ID = "101384210";
	public static final String CLIENT_SECRET = "209458c5aec0bfa9fefa9c848ae270ed";
	// public static final String URL = "http://dbyqq.tunnel.2bdata.com/qq";
	public static final String QQ_ONE_URL = "https://graph.qq.com/oauth2.0/authorize?response_type=code&client_id=";
	public static final String QQ_TWO_URL = "https://graph.qq.com/oauth2.0/token?grant_type=authorization_code&client_id=";
	public static final String QQ_THREE_URL = "https://graph.qq.com/oauth2.0/me?access_token=";

	@RequestMapping(value = "")
	public Object jump(String code,Model model) throws ClientProtocolException, IOException {
		System.out.println("code的值为" + code);
		if (code == null) {
			return "redirect:" + QQ_ONE_URL + CLIENT_ID + "&redirect_uri=" + REDIRECT_URI + "&state123";
		} else {
			String twoURL = QQ_TWO_URL + CLIENT_ID + "&client_secret=" + CLIENT_SECRET + "=code=" + code + "&redirect_uri=" + REDIRECT_URI;
			// return "redirect:" + RELASEURL;
			String access_token = YdWxParamUtils.doGetAccessToken(twoURL);
			String threeUrl = QQ_THREE_URL + access_token;
			// JSONObject doGetJson = YdWxParamUtils.doGetJson(threeUrl);
			// String string = doGetJson.getString("openid");
			HttpClient client = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(threeUrl);
			HttpResponse response = client.execute(httpGet);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String result = EntityUtils.toString(entity, "UTF-8");
				String result2 = result.replace("callback", "")
				.replace("(", "")
				.replace(")", "")
				.replace(";", "")
				.trim();
				JSONObject jsonObject = new JSONObject();
				JSONObject open_id = JSONObject.fromObject(result2);
				String openid = (String) open_id.get("openid");
				httpGet.releaseConnection();
				//根据openid查询用户
				Map<String,Object> userResult = ydSysServiceSecurity.queryUserByOpenId(openid);
				if (userResult.get("msg")=="success") {
					//已经绑定了用户
					Integer tplUserId = (Integer) userResult.get("tpl_user_id");
					YdSysUserFull yu = ydSysServiceSecurity.queryUserByUserId(tplUserId);
					session.setAttribute("user_id",yu.getUser_id());
					session.setAttribute("user",yu);
					return "redirect:/";
				}else {
					//用户第一次使用第三方登陆还没绑定
					return "redirect:/thirdPartyLogin/bind?openid="+openid+"&tpl_type=2"; 
				}
			}
			
			return "member/pages/404";
		}
	}
	
}
