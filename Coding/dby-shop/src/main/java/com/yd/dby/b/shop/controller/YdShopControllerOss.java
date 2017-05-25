package com.yd.dby.b.shop.controller;

import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Order(2)
@RequestMapping(value = "/oss")
public class YdShopControllerOss {

//	@RequestMapping(value = "/policy", method = { RequestMethod.GET, RequestMethod.POST })
//	public String createPolicy() {
//		return YdOssPolicy.createPolicy("test/").toJSONString();
//	}
//
//	@RequestMapping(value = "/callback", method = { RequestMethod.GET, RequestMethod.POST })
//	public HashMap<String, Object> ossCallback(HttpServletRequest request, HttpServletResponse response) {
//		HashMap<String, Object> out = new HashMap<String, Object>();
//		try {
//			Map<String, String[]> params = request.getParameterMap();
//			String queryString = new String();
//			for (String key : params.keySet()) {
//				String[] values = params.get(key);
//				for (int i = 0; i < values.length; i++) {
//					String value = values[i];
//					queryString += key + "=" + value + "&";
//				}
//			}
//			YdOssCallback.verifyOSSCallbackRequest(request, queryString.substring(0, queryString.length() - 1));
//			out.put("code", "1");
//		} catch (Exception e) {
//			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//			out.put("code", "2");
//			out.put("msg", e.getMessage());
//		}
//
//		return out;
//	}

}
