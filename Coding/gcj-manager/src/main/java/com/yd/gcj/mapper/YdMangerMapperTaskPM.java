package com.yd.gcj.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.gcj.entity.YdMangerTaskPM;

public interface YdMangerMapperTaskPM {
	
	/***
	 * 查询任务付款跟进模块信息
	 * @param tpm_taskid
	 * @return
	 */
	List<YdMangerTaskPM> $queryByTid(@Param("tpm_taskid") Integer tpm_taskid);
	
	/***
	 * 查询指定付款跟进信息
	 * @param tpm_id
	 * @return
	 */
	YdMangerTaskPM $queryById(@Param("tpm_id") Integer tpm_id);
	
	/***
	 * 查询指定付款跟进信息状态
	 * @param tpmId
	 * @return
	 */
	Integer $queryStateById(@Param("tpmId") Integer tpmId);
	
	/***
	 * 添加新付款跟进信息模块
	 * @param tpm
	 * @return
	 */
	Integer $insert(YdMangerTaskPM tpm);
	
	/***
	 * 修改跟进付款模块信息
	 * @param tpm
	 * @return
	 */
	Integer $update(YdMangerTaskPM tpm);
	
	/***
	 * 修改付款信息模块状态
	 * @param tpm_id 任务跟进模块付款信息id
	 * @param tpm_state 状态
	 * @param tpm_update_time 当前时间
	 * @return
	 */
	Integer $updateState(@Param("taskId") Integer taskId,@Param("tpmId") Integer tpmId,@Param("tpmState") Integer tpmState,@Param("updateTime") Date updateTime);
	
	/***
	 * 修改项目付款节点中服务商id
	 * @param tpmId
	 * @param userId
	 * @return
	 */
	Integer $updateStateAndSid(@Param("taskId") Integer taskId,@Param("tpmId") Integer tpmId,@Param("tpmState") Integer tpmState,@Param("updateTime") Date updateTime,@Param("userId") Integer userId);
	
	/***
	 * 修改付款信息模块状态和拒绝原因
	 * @param taskId
	 * @param tpmId
	 * @param tpmState
	 * @param reason
	 * @param updateTime
	 * @return
	 */
	Integer $updateStateAndReason(@Param("taskId") Integer taskId,@Param("tpmId") Integer tpmId,@Param("tpmState") Integer tpmState,@Param("reason") String reason,@Param("updateTime") Date updateTime);
	
	/***
	 * 删除指定任务跟进付款信息
	 * @param tpm_id
	 * @return
	 */
	Integer $delete(@Param("tpm_id") Integer tpm_id);
	
}
