package com.yd.gcj.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.YdMangerRefund;
import com.yd.gcj.entity.vo.YdMangerTaskVo;
import com.yd.gcj.entity.vo.YdMangerTenderVo;
import com.yd.gcj.entity.vo.YdMangerUserVo;
import com.yd.gcj.mapper.YdMangerMapperRefund;
import com.yd.gcj.mapper.YdMangerMapperTask;
import com.yd.gcj.mapper.YdMangerMapperTender;
import com.yd.gcj.mapper.YdMangerMapperUser;
import com.yd.gcj.service.YdMangerServiceRefund;
import com.yd.gcj.tool.MapInitFactory;
import com.yd.gcj.tool.ObjectMapperFactory;

@Service("YdMangerServiceRefund")
public class YdMangerServiceImplRefund implements YdMangerServiceRefund{
	
	@Autowired
	private YdMangerMapperRefund mapperRefund;
	
	@Autowired
	private YdMangerMapperTask mapperTask;
	
	@Autowired
	private YdMangerMapperUser mapperUser;
	
	@Autowired
	private YdMangerMapperTender mapperTender;
	
	@Override
	public Object insert(YdMangerRefund refund,YdMangerUserVo userVo) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		boolean isOk = true;
		YdMangerTaskVo taskVo = mapperTask.$queryById(refund.getRefund_tid());
		if(taskVo != null){
			Date date = new Date();
			if(userVo.getUser_type() == 0){
				refund.setRefund_gid(userVo.getUser_id());
				refund.setRefund_gname(userVo.getUser_name());
				refund.setRefund_gphone(userVo.getUser_phone());
				
				Integer istender = mapperTender.$isExistLeader(refund.getRefund_tid(), 1);
				if(istender == 1){
					YdMangerTenderVo tenderVo = mapperTender.$queryLearByTaskId(refund.getRefund_tid());
					if(tenderVo != null){
						refund.setRefund_fid(tenderVo.getTender_uid());
						refund.setRefund_fname(tenderVo.getTender_uname());
						refund.setRefund_fphone(tenderVo.getTender_uphone());
					}else{
						mapInitFactory.setMsg("504","系统没有查询到任务组长信息！");
						isOk = false;
					}
				}else{
					mapInitFactory.setMsg("502", "参数错误，系统检查到该任务没有选择组长！");
					isOk = false;
				}
			}else{
				Integer istender = mapperTender.$isExist(taskVo.getTask_id(), userVo.getUser_id());
				if(istender > 0){
					Integer isLear = mapperTender.$isExistLeader(taskVo.getTask_id(), 1);
					if(isLear > 0){
						refund.setRefund_fid(userVo.getUser_id());
						refund.setRefund_fname(userVo.getUser_name());
						refund.setRefund_fphone(userVo.getUser_phone());
						YdMangerUserVo euser = mapperUser.$queryById(taskVo.getTask_uid());
						refund.setRefund_gid(euser.getUser_id());
						refund.setRefund_gname(euser.getUser_name());
						refund.setRefund_gphone(euser.getUser_phone());
					}else{
						mapInitFactory.setMsg("504", "系统没有查询到任务组长信息！");
						isOk = false;
					}
				}else{
					mapInitFactory.setMsg("505", "对不起，系统检测到您没有对该任务进行过投标！");
					isOk = false;
				}
			}
			if(isOk){
				refund.setRefund_captial(taskVo.getTask_pay_price());
				refund.setRefund_state(0);
				refund.setRefund_uid(userVo.getUser_id());
				refund.setRefund_update_time(date);
				refund.setRefund_create_time(date);
				ObjectMapperFactory.doIt(refund);
				Integer success = mapperRefund.$insert(refund);
				if(success > 0){
					mapInitFactory.setMsg("200", "申请提交成功，审核结果通过手机短信及系统消息告知，请耐心等待！");
				}else{
					mapInitFactory.setMsg("503", "申请提交失败，请与客服联系并告知详情！");
				}
			}
		}else{
			mapInitFactory.setMsg("501", "任务不存在！");
		}
		return mapInitFactory.getMap();
	}
}
