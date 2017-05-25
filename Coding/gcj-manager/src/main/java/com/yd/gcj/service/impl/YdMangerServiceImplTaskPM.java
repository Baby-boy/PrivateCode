package com.yd.gcj.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.YdMangerBankTR;
import com.yd.gcj.entity.YdMangerTaskPM;
import com.yd.gcj.entity.vo.YdMangerTaskVo;
import com.yd.gcj.entity.vo.YdMangerUserVo;
import com.yd.gcj.mapper.YdMangerMapperBankTR;
import com.yd.gcj.mapper.YdMangerMapperTask;
import com.yd.gcj.mapper.YdMangerMapperTaskPM;
import com.yd.gcj.mapper.YdMangerMapperUser;
import com.yd.gcj.service.YdMangerServiceTaskPM;
import com.yd.gcj.tool.MD5Util;
import com.yd.gcj.tool.MapInitFactory;
import com.yd.gcj.tool.Values;

@Service("YdMangerServiceTaskPM")
public class YdMangerServiceImplTaskPM implements YdMangerServiceTaskPM {

	@Autowired
	private YdMangerMapperTaskPM mapperTaskPM;
	
	@Autowired
	private YdMangerMapperUser mapperUser;
	
	@Autowired
	private YdMangerMapperBankTR mapperBankTR;
	
	@Autowired
	private YdMangerMapperTask mapperTask;
	
	@Override
	public List<YdMangerTaskPM> $queryByTid(Integer taskId) {
		return mapperTaskPM.$queryByTid(taskId);
	}

	@Override
	public YdMangerTaskPM $queryById(Integer tpm_id) {
		return mapperTaskPM.$queryById(tpm_id);
	}

	@Override
	public Object $insert(YdMangerTaskPM tpm) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		Date date = new Date();
		tpm.setTpm_create_time(date);
		tpm.setTpm_update_time(date);
		tpm.setTpm_isbalance(0);
		tpm.setTpm_state(0);
		int success = mapperTaskPM.$insert(tpm);
		if (success > 0) {
			mapInitFactory.put("tpmId", tpm.getTpm_id());
			mapInitFactory.setMsg(Values.INITSUCCESSCODE, "添加成功！");
		} else {
			mapInitFactory.setMsg("501", "添加失败，请检查后再试！");
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Integer $update(YdMangerTaskPM tpm) {
		return mapperTaskPM.$update(tpm);
	}

	@Override
	public Integer $updateState(Integer taskId, Integer tpmId, Integer tpmState,Integer userId) {
		return mapperTaskPM.$updateStateAndSid(taskId,tpmId, tpmState, new Date(),userId);
	}
	
	@Override
	public Object $delete(Integer tpm_id) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			Integer success = mapperTaskPM.$delete(tpm_id);
			if (success > 0) {
				mapInitFactory.setMsg("200", "删除成功！");
			} else {
				mapInitFactory.setMsg("501", "删除失败！");
			}
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object tg(final YdMangerUserVo userVo, Integer taskId, String desc, float price, String phone,String payPwd) {
		MapInitFactory mf = new MapInitFactory();
		Integer userId = userVo.getUser_id();
		int istask = mapperTask.$isTask(taskId, userId, userVo.getUser_type());
		if(istask == 1){
			Date date = new Date();
			if(price > 0){
				//获取用户支付密码
				String dbPayPwd = mapperUser.$queryPayPwdByUserPhoneAndUserId(userVo.getUser_id(), phone);
				if(dbPayPwd != null && !dbPayPwd.isEmpty()){
					//对原输入支付密码加密
					String ppwd = MD5Util.textToMD5L32(payPwd + "@$#");
					//输入的支付密码与数据库支付密码对比
					if(ppwd.equals(dbPayPwd)){
						//查看用户余额
						float cprice = mapperUser.$queryPriceByUserId(userId);
						//对比用户余额
						if(cprice > price){
							//获取任务信息
							YdMangerTaskVo taskVo = mapperTask.$queryById(taskId);
							if(taskVo != null){
								//修改用户余额
								cprice = cprice - price;
								userVo.setUser_cprice(cprice);
								mapperUser.$updateUserPrice(userId, cprice);
								
								//添加付款跟进
								YdMangerTaskPM taskPM = new YdMangerTaskPM();
								taskPM.setTpm_desc(desc);
								taskPM.setTpm_money(price);
								taskPM.setTpm_taskid(taskId);
								taskPM.setTpm_state(2);
								taskPM.setTpm_isbalance(0);
								taskPM.setTpm_create_time(date);
								taskPM.setTpm_update_time(date);
								taskPM.setTpm_isbalance(1);
								mapperTaskPM.$insert(taskPM);
								
								//添加一条交易记录
								YdMangerBankTR banktr = new YdMangerBankTR();
								banktr.setBtr_osid(userVo.getUser_id());
								banktr.setBtr_create_time(date);
								banktr.setBtr_type(3);
								banktr.setBtr_rtime(date);
								banktr.setBtr_price(-price);
								banktr.setBtr_account("");
								banktr.setBtr_osid(0);
								banktr.setBtr_aos("");
								banktr.setBtr_uid(userId);
								banktr.setBtr_tdesc("项目“"+taskVo.getTask_pname()+"”节点“"+desc+"”资金托管");
								mapperBankTR.$insert(banktr);
								mapperTask.$updateHostPrice(taskId);
								
								mf.setMsg("200", "资金托管成功！");	
							
							}else{
								mf.setMsg("506", "托管失败！");
							}
						}else{
							mf.setMsg("505", "余额不足！");
						}
					}else{
						mf.setMsg("503", "支付密码不正确！");
					}
				}else{
					mf.setMsg("501", "您还没有设置支付密码，请设置支付密码后再操作！");
				}
			}else{
				mf.setMsg("504", "托管资金额度必须大于0！");
			}
		}
		return mf.getMap();
	}

	@Override
	public Integer $queryStateById(Integer tpmId) {
		return mapperTaskPM.$queryStateById(tpmId);
	}

	@Override
	public Integer $updateStateAndReason(Integer taskId, Integer tpmId, Integer tpmState, String reason) {
		return mapperTaskPM.$updateStateAndReason(taskId, tpmId, tpmState, reason, new Date());
	}

	@Override
	public Object updateTg(final YdMangerUserVo userVo, Integer taskId, Integer tpmId,String phone,
			String payPwd) {
		MapInitFactory mf = new MapInitFactory();
		Integer userId = userVo.getUser_id();
		int istask = mapperTask.$isTask(taskId, userId, userVo.getUser_type());
		if(istask == 1){
			Date date = new Date();
			YdMangerTaskPM taskPM = mapperTaskPM.$queryById(tpmId);
			float price = taskPM.getTpm_money();
			if(taskPM != null && price > 0){
				//获取用户支付密码
				String dbPayPwd = mapperUser.$queryPayPwdByUserPhoneAndUserId(userVo.getUser_id(), phone);
				if(dbPayPwd != null && !dbPayPwd.isEmpty()){
					
					//对原输入支付密码加密
					String ppwd = MD5Util.textToMD5L32(payPwd + "@$#");
					//输入的支付密码与数据库支付密码对比
					if(ppwd.equals(dbPayPwd)){
						//查看用户余额
						float cprice = mapperUser.$queryPriceByUserId(userId);
						//对比用户余额
						if(cprice > price){
							//获取任务信息
							YdMangerTaskVo taskVo = mapperTask.$queryById(taskId);
							if(taskVo != null){
								//修改用户余额
								cprice = cprice - price;
								userVo.setUser_cprice(cprice);
								mapperUser.$updateUserPrice(userId, cprice);
								//修改项目节点状态
								mapperTaskPM.$updateState(taskId, tpmId, 2, new Date());
								
								//添加一条交易记录
								YdMangerBankTR banktr = new YdMangerBankTR();
								banktr.setBtr_osid(userVo.getUser_id());
								banktr.setBtr_create_time(date);
								banktr.setBtr_type(3);
								banktr.setBtr_rtime(date);
								banktr.setBtr_price(-price);
								banktr.setBtr_account("");
								banktr.setBtr_osid(0);
								banktr.setBtr_aos("");
								banktr.setBtr_uid(userId);
								banktr.setBtr_tdesc("项目“"+taskVo.getTask_pname()+"”节点“"+taskPM.getTpm_desc()+"”资金托管");
								mapperBankTR.$insert(banktr);
								//更新任务（项目）中托管佣金金额
								mapperTask.$updateHostPrice(taskId);
								mf.setMsg("200", "资金托管成功！");	
							
							}else{
								mf.setMsg("506", "托管失败！");
							}
						}else{
							mf.setMsg("505", "余额不足！");
						}
					}else{
						mf.setMsg("503", "支付密码不正确！");
					}
				}else{
					mf.setMsg("501", "您还没有设置支付密码，请设置支付密码后再操作！");
				}
			}else{
				mf.setMsg("504", "托管资金额度必须大于0！");
			}
		}
		return mf.getMap();
	}

	@Override
	public Object surePay(final YdMangerUserVo userVo, Integer taskId, Integer tpmId, String phone, String payPwd) {
		MapInitFactory mf = new MapInitFactory();
		Integer userId = userVo.getUser_id();
		int istask = mapperTask.$isTask(taskId, userId, userVo.getUser_type());
		if(istask == 1){
			//获取支付密码
			String dbPayPwd = mapperUser.$queryPayPwdByUserPhoneAndUserId(userVo.getUser_id(), phone);
			
			if(dbPayPwd != null && !dbPayPwd.isEmpty()){
				//对原输入支付密码加密
				String ppwd = MD5Util.textToMD5L32(payPwd + "@$#");
				//输入的支付密码与数据库支付密码对比
				if(ppwd.equals(dbPayPwd)){
					//获取付款节点信息
					YdMangerTaskPM taskPM = mapperTaskPM.$queryById(tpmId);
					if(taskPM != null && (taskPM.getTpm_state() == 4 || taskPM.getTpm_state() == 6)){
						//获取服务商用户信息
						Integer sid = taskPM.getTpm_sid(); 
						if(sid != null && sid != 0){
							YdMangerTaskVo taskVo = mapperTask.$queryById(taskId);
							//查看用户余额
							float cprice = mapperUser.$queryPriceByUserId(sid);
							//修改服务商用户账户余额
							float price = taskPM.getTpm_money()+cprice;
							mapperUser.$updateUserPrice(sid, price);
							//修改节点信息状态
							mapperTaskPM.$updateState(taskId, tpmId, 5, new Date());
							// TODO 服务商信息推送
							//为服务商添加交易记录
							YdMangerBankTR banktr = new YdMangerBankTR();
							banktr.setBtr_osid(userVo.getUser_id());
							banktr.setBtr_create_time(new Date());
							banktr.setBtr_type(3);
							banktr.setBtr_rtime(new Date());
							banktr.setBtr_price(taskPM.getTpm_money());
							banktr.setBtr_account("");
							banktr.setBtr_osid(0);
							banktr.setBtr_aos("");
							banktr.setBtr_uid(sid);
							banktr.setBtr_tdesc("项目“"+taskVo.getTask_pname()+"”节点“"+taskPM.getTpm_desc()+"”支付确认");
							mapperBankTR.$insert(banktr);
							// TODO 雇主信息推送
							mf.setMsg("200", "节点佣金支付成功！");
						}else{
							mf.setMsg("505", "操作失败！");
						}			
					}else{
						mf.setMsg("504", "操作失败！");
					}
						
				}else{
					mf.setMsg("503", "支付密码不正确！");
				}
			}else{
				mf.setMsg("501", "您还没有设置支付密码，请设置支付密码后再操作！");
			}
		}
		return mf.getMap();
	}

}
