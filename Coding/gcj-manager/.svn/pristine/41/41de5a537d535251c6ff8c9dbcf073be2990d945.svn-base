package com.yd.gcj.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yd.gcj.entity.YdMangerTaskPM;
import com.yd.gcj.entity.vo.YdMangerUserVo;

public interface YdMangerServiceTaskPM {

	/***
	 * 查询任务付款跟进模块信息
	 * @param tpm_taskid
	 * @return
	 */
	List<YdMangerTaskPM> $queryByTid(Integer tpm_taskid);
	
	/***
	 * 查询指定付款跟进信息
	 * @param tpm_id
	 * @return
	 */
	YdMangerTaskPM $queryById(Integer tpm_id);
	
	/***
	 * 查询指定的付款跟状态
	 * @param tpmId
	 * @return
	 */
	Integer $queryStateById(Integer tpmId);
	
	/***
	 * 添加新付款跟进信息模块
	 * @param tpm
	 * @return
	 */
	@Transactional
	Object $insert(YdMangerTaskPM tpm);
	
	/***
	 * 修改跟进付款模块信息
	 * @param tpm
	 * @return
	 */
	@Transactional
	Integer $update(YdMangerTaskPM tpm);
	
	/***
	 * 修改付款信息模块状态
	 * @param tpm_id 任务跟进模块付款信息id
	 * @param tpm_state 状态
	 * @param tpm_update_time 当前时间
	 * @return
	 */
	@Transactional
	Integer $updateState(Integer taskId,Integer tpmId,Integer tpmState,Integer userId);
	
	
	/***
	 * 修改付款信息模块状态和原因（主要用在拒绝付款、托管接口）
	 * @param taskId
	 * @param tpmId
	 * @param tpmState
	 * @param reason
	 * @return
	 */
	@Transactional
	Integer $updateStateAndReason(Integer taskId,Integer tpmId,Integer tpmState,String reason);
	
	/***
	 * 删除指定任务跟进付款信息
	 * @param tpm_id
	 * @return
	 */
	@Transactional
	Object $delete(Integer tpm_id);
	
	/***
	 * 雇主托管项目资金
	 * @Description: 
	 * @param userVo
	 * @param taskId
	 * @param desc
	 * @param price
	 * @param phone
	 * @param payPwd
	 * @param priceNow
	 * @return    
	 * @date: 2017年3月8日 下午3:36:07
	 */
	@Transactional
	Object tg(final YdMangerUserVo userVo, Integer taskId,String desc, float price, String phone, String payPwd);
	
	/***
	 * 雇主托管已有的付款模块资金申请并修改状态
	 * @param userVo
	 * @param taskId
	 * @param desc
	 * @param price
	 * @param phone
	 * @param payPwd
	 * @return
	 */
	@Transactional
	Object updateTg(final YdMangerUserVo userVo, Integer taskId,Integer tpmId, String phone, String payPwd);
	
	/***
	 * 
	 * @param userVo
	 * @param taskId
	 * @param tpmId
	 * @param phone
	 * @param payPwd
	 * @return
	 */
	@Transactional
	Object surePay(final YdMangerUserVo userVo, Integer taskId,Integer tpmId, String phone,String payPwd);
}
