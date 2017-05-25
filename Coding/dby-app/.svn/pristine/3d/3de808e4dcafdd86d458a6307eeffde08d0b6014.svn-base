package com.yd.dby.app.controller.user;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yd.dby.app.common.HttpCode;
import com.yd.dby.app.common.JsonResult;
import com.yd.dby.app.common.ServiceResult;
import com.yd.dby.app.controller.BaseController;
import com.yd.dby.app.entity.YdAddress;
import com.yd.dby.app.entity.YdUser;
import com.yd.dby.app.entity.para.BasePara;
import com.yd.dby.app.service.YdAddressService;

@RestController
@RequestMapping("address")
public class AddressController extends BaseController {

	@Autowired
	private YdAddressService ydAddressService;

	/**
	 * 说明: 添加收货地址
	 * 
	 * @param ydAddress(请求参数)
	 * @param request
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月12日 下午1:20:35
	 */
	@RequestMapping(value = "addAddress", method = RequestMethod.POST)
	public JsonResult addUserAddress(@RequestBody YdAddress ydAddress, HttpServletRequest request) throws Exception {
		JsonResult jsonResult = new JsonResult();

		if (StringUtils.isEmpty(ydAddress.getAdLinkman()) || StringUtils.isEmpty(ydAddress.getAdLinktel())) {
			jsonResult.setStatusCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("请求参数异常!");
			return jsonResult;
		}
		if (StringUtils.isEmpty(ydAddress.getAdPca()) || StringUtils.isEmpty(ydAddress.getAdStreet())) {
			jsonResult.setStatusCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("请求参数异常!");
			return jsonResult;
		}
		if (StringUtils.isEmpty(ydAddress.getAdMore()) || null == ydAddress.getAdIsDefault()) {
			jsonResult.setStatusCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("请求参数异常!");
			return jsonResult;
		}

		// 获取当前用户
		YdUser user = (YdUser) redisGet(request.getHeader("token") + "User", YdUser.class);

		ydAddress.setAdId(null);
		ydAddress.setUserId(user.getUserId());
		ydAddress.setAdCreatedTime(new Date());

		ServiceResult<Object> result = ydAddressService.insertUserAddress(ydAddress);
		jsonResult.setMsg(result.getMsg());
		jsonResult.setStatusCode(result.getStatusCode());
		return jsonResult;
	}

	/**
	 * 说明: 用户收货地址列表
	 * 
	 * @param request
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月12日 下午1:45:40
	 */
	@RequestMapping(value = "addressList", method = RequestMethod.POST)
	public JsonResult queryAddressList(@RequestBody BasePara para, HttpServletRequest request) throws Exception {
		JsonResult jsonResult = new JsonResult();

		// 获取当前用户
		YdUser user = (YdUser) redisGet(request.getHeader("token") + "User", YdUser.class);

		ServiceResult<List<YdAddress>> result = ydAddressService.selectAddressList(user);
		jsonResult.setMsg(result.getMsg());
		jsonResult.setData(result.getData());
		jsonResult.setStatusCode(result.getStatusCode());
		return jsonResult;
	}

	/**
	 * 说明: 编辑用户收货地址
	 * 
	 * @param ydAddress
	 * @param request
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月12日 下午2:32:54
	 */
	@RequestMapping(value = "updateAddress", method = RequestMethod.POST)
	public JsonResult updateUserAddress(@RequestBody YdAddress ydAddress, HttpServletRequest request) throws Exception {
		JsonResult jsonResult = new JsonResult();

		// 获取当前用户
		YdUser user = (YdUser) redisGet(request.getHeader("token") + "User", YdUser.class);

		ydAddress.setUserId(user.getUserId());

		ServiceResult<Object> result = ydAddressService.updateUserAddress(ydAddress);
		jsonResult.setMsg(result.getMsg());
		jsonResult.setStatusCode(result.getStatusCode());
		return jsonResult;
	}

	/**
	 * 说明: 删除用户收货地址
	 * 
	 * @param adIds(多个地址ID)
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月12日 下午2:48:55
	 */
	@RequestMapping(value = "deleteAddress", method = RequestMethod.GET)
	public JsonResult deleteUserAddress(@RequestParam("adIds") String adIds) {
		JsonResult jsonResult = new JsonResult();

		// 判断请求参数是否正确
		if (StringUtils.isEmpty(adIds)) {
			jsonResult.setStatusCode(HttpCode.NO_CONTENT);
			jsonResult.setMsg("未删除地址!");
			return jsonResult;
		}

		ServiceResult<Object> result = ydAddressService.deleteUserAddress(adIds);
		jsonResult.setMsg(result.getMsg());
		jsonResult.setStatusCode(result.getStatusCode());
		return jsonResult;
	}
}
