package com.yd.gcj.util;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import com.yd.gcj.tool.MapInitFactory;

public class YdMangerSmsFactory {
	
	public MapInitFactory sendPhoneCode(String phone,String code,String description){
		String url="http://gw.api.taobao.com/router/rest";
		String appKey = "23607428";
		String appSecret = "480c08cc6e3cc798a9939d119c8b0e6c";
		
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			if(phone!=null){
				if(phone.length()==11&&phone.substring(0,1).equals("1")){
					TaobaoClient client = new DefaultTaobaoClient(url, appKey, appSecret);
					AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
					req.setExtend("123456");
					req.setSmsType("normal");
					req.setSmsFreeSignName("大鱼测试");
					req.setSmsParamString("{\"code\":\""+code+"\",\"product\":\""+description+"\"}");
					req.setRecNum(phone);
					req.setSmsTemplateCode("SMS_36000218");
					AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
					boolean success = rsp.getResult().getSuccess();
					if(success){
						mapInitFactory.setMsg("200", "发送成功，请注意接收！");
						mapInitFactory.put("success", "true");
					}else{
						mapInitFactory.setMsg("501", "发送失败，请检查后再试！");
					}
				}else{
					mapInitFactory.setMsg("503", "手机格式不正确！");
				}
			}else{
				mapInitFactory.setMsg("504", "手机号不能为空！");
			}
		} catch (Exception e) {
			mapInitFactory.setMsg("500", "短时间内每个号码只能发送不超过5次！");
		}
		return mapInitFactory;
	}
	
}
