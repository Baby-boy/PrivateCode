package com.yd.gcj.controller.page;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yd.gcj.entity.YdMangerRefund;
import com.yd.gcj.entity.vo.YdMangerUserVo;
import com.yd.gcj.service.YdMangerServiceRefund;
import com.yd.gcj.tool.MapInitFactory;

@RestController
@RequestMapping(value = "/page/refund", produces = { "application/json;charset=UTF-8" })
public class YdMangerControllerPageReFund {

	@Autowired
	private YdMangerServiceRefund serviceRefund;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Object addReFund(Integer taskId, String reason, String content, HttpServletRequest request) {
		try {
			YdMangerUserVo userVo = (YdMangerUserVo) request.getSession().getAttribute("user");
			if (userVo != null) {
				YdMangerRefund refund = new YdMangerRefund();
				refund.setRefund_contents(content);
				refund.setRefund_reason(reason);
				refund.setRefund_tid(taskId);
				return serviceRefund.insert(refund, userVo);
			} else {
				return new MapInitFactory("600", "对不起，您没有操作权限！").getMap();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new MapInitFactory().setSystemError().getMap();
		}
	}

}
