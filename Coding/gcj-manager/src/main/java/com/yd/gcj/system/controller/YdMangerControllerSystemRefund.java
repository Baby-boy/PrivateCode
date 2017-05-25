package com.yd.gcj.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yd.gcj.entity.YdMangerRefund;
import com.yd.gcj.entity.YdMangerTask;
import com.yd.gcj.entity.vo.YdMangerRefundVo;
import com.yd.gcj.system.service.YdMangerServiceSystemRefund;
import com.yd.gcj.system.service.YdMangerServiceSystemTask;

/**
 * description(退款信息管理)
 * 
 * @author Administrator
 * @param <HttpServletRequest>
 */
@Controller
@RequestMapping("/system")
public class YdMangerControllerSystemRefund<HttpServletRequest> {

	@Autowired
	private YdMangerServiceSystemRefund ydMangerServcieSystemRefund;

	@Autowired
	private YdMangerServiceSystemTask ydMangerServiceSystemTask; 
	
	/**
	 * description(查询所有的退款信息)
	 * 
	 * @param
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryAllRefund")
	public String queryAllRefund(Model model, Integer p,String refund_fphone,String refund_gphone,Integer refund_state) {
		// 当前页
		if (p == null) {
			p = 1;
			PageHelper.startPage(p, 8);
		} else {
			PageHelper.startPage(p, 8);
		}
		;
		List<YdMangerRefundVo> refundList = ydMangerServcieSystemRefund.queryAllRefund(refund_fphone,refund_gphone,refund_state);
		PageInfo<YdMangerRefundVo> pageInfo = new PageInfo<YdMangerRefundVo>(refundList);
		// 总页数
		Integer totalPage = null;
		Integer total = (int) pageInfo.getTotal();
		if (total % 8 != 0) {
			totalPage = total / 8 + 1;
		} else {
			totalPage = total / 8;
		}

		model.addAttribute("p", p);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("refund_fphone",refund_fphone);
		model.addAttribute("refund_gphone",refund_gphone);
		model.addAttribute("refund_state",refund_state);
		model.addAttribute("refundList", refundList);
		return "system/refund/refund";
	}
	
	/**
	 * description(根据refund_id查询当前退款的详细信息)
	 * @param
	 * @param refund_id
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryRefundByRefundId/{refund_id}")
	public String queryRefundByRefundId(@PathVariable Integer refund_id,Model model){
		
		YdMangerRefundVo ydMangerRefund = ydMangerServcieSystemRefund.queryRefundByRefundId(refund_id);
		Integer task_id = ydMangerRefund.getRefund_tid();
		//根据任务id查询
		YdMangerTask ydMangerTask = ydMangerServiceSystemTask.queryTaskByTaskId(task_id);
		model.addAttribute("ydMangerTask",ydMangerTask);
		model.addAttribute("ydMangerRefund", ydMangerRefund);
		return "system/refund/refunddetails";
	}
	
	/**
	 * description(审核退款信息之前先根据refund_id查询当前的退款信息)
	 * @param
	 * @param refund_id
	 * @param model
	 * @return
	 */
	@RequestMapping("/updateRefunStatedByRefundId/{refund_id}")
	public String updateRefunStatedByRefundId(@PathVariable Integer refund_id,Model model){
		YdMangerRefundVo ydMangerRefund = ydMangerServcieSystemRefund.queryRefundByRefundId(refund_id);
		Integer task_id = ydMangerRefund.getRefund_tid();
		//根据任务id查询
		YdMangerTask ydMangerTask = ydMangerServiceSystemTask.queryTaskByTaskId(task_id);
		model.addAttribute("ydMangerTask",ydMangerTask);
		model.addAttribute("ydMangerRefund",ydMangerRefund);
		return "system/refund/checkrefund";
	}
	
	/**
	 * description(审核退款信息)
	 * @param
	 * @param ydMangerRefund
	 * @return
	 */
	@RequestMapping("/updateRefundByRefundId")
	@ResponseBody
	public Object updateRefundStateByRefundId(YdMangerRefund ydMangerRefund,Integer task_id){
		Map<String,Object> map = new HashMap<String, Object>();
		Integer updateNum = ydMangerServcieSystemRefund.updateRefundStateByRefundId(ydMangerRefund);
		if (updateNum>0) {
			map.put("msg",true);
		}else {
			map.put("msg",false);
		}
		return map;
	}
}
