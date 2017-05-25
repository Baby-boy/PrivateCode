package com.yd.gcj.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import com.yd.gcj.entity.YdMangerTender;
import com.yd.gcj.entity.vo.YdMangerTenderNumVo;
import com.yd.gcj.entity.vo.YdMangerTenderVo;

public interface YdMangerMapperTender {
	
	/***
	 * 查询指定任务投标信息
	 * @param tender_tid
	 * @return
	 */
	List<YdMangerTenderVo> $queryByTid(@Param("taskId") Integer taskId,@Param("startPageNum") Integer startPageNum,@Param("queryPageNum") Integer queryPageNum,@Param("type") Integer type);
	
	/***
	 * 查询指定投标信息
	 * @param tenderId
	 * @return
	 */
	YdMangerTenderVo $queryById(@Param("tenderId") Integer tenderId);
	
	/**
	 * 根据多个任务id查询相应的投标数量
	 * @param tids
	 * @return
	 */
	List<YdMangerTenderNumVo> $queryCountByTids(@Param("tids") List<Integer> tids);
	
	/***
	 * 查询指定任务中按个人或公司类型查询
	 * @param tender_tid
	 * @param tender_utype 0个人  1公司
	 * @return
	 */
	List<YdMangerTender> $queryByType(@Param("tender_tid") Integer tender_tid,@Param("tender_type") Integer tender_type);
	
	/***
	 * 查询指定服务商所有投标信息
	 * @param tender_sid
	 * @param startPageNum
	 * @param queryPageNum
	 * @return
	 */
	List<YdMangerTender> $queryBySid(@Param("tender_uid") Integer tender_uid,@Param("startPageNum") Integer startPageNum,@Param("queryPageNum") Integer queryPageNum);
	
	/***
	 * 查询指定任务组长投标信息
	 * @param taskId
	 * @return
	 */
	YdMangerTenderVo $queryLearByTaskId(@Param("taskId") Integer taskId);
	
	/***
	 * 查询指定服务商投标信息数量
	 * @param tender_sid
	 * @return
	 */
	Integer $queryCountNumBySid(@Param("tender_uid") Integer tender_uid);
	
	/***
	 * 查询指定任务投标信息数量
	 * @param tender_tid
	 * @return
	 */
	Integer $queryCountNumByTid(@Param("tender_tid") Integer tender_tid);
	
	/**
	 * 查询该用户在指定任务上是否进行过投标
	 * @param tender_tid
	 * @param tender_uid
	 * @return
	 */
	Integer $isExist(@Param("tender_tid") Integer tender_tid,@Param("tender_uid") Integer tender_uid);
	
	/***
	 * 查询指定任务是否已选组长
	 * @param taskId
	 * @param type
	 * @return
	 */
	Integer $isExistLeader(@Param("taskId") Integer taskId,@Param("type") Integer type);
	
	/***
	 * 检查指定服务商是否是指定任务的组长
	 * @param taskId
	 * @param type
	 * @param userId
	 * @return
	 */
	
	Integer $isExistLeaderIsSelf(@Param("tenderId") Integer tenderId,@Param("userId") Integer userId);
	/**
	 * 查询指定的投标信息状态
	 * @param tender_id
	 * @return
	 */
	Integer $queryState(@Param("tender_id") Integer tender_id);
	
	/**
	 * 添加新投标信息
	 * @param tender
	 * @return
	 */
	Integer $insert(YdMangerTender tender);
	
	/***
	 * 更新指定投标信息
	 * @param tender
	 * @return
	 */
	Integer $update(YdMangerTender tender);
	
	/***
	 * 修改投标状态
	 * @param tender_id
	 * @param tender_state
	 * @param tender_update_time
	 * @return
	 */
	Integer $updateState(@Param("tender_id") Integer tender_id,@Param("tender_state") Integer tender_state,@Param("tender_update_time") Date tender_update_time);
	
	/***
	 * 设置投标用户为任务工作组角色
	 * @param tenderId 投标id
	 * @param type 角色（0啥都不是，1组长，2组员）
	 * @param state 投标状态（0未选或未中标，1中标）
	 * @param updateTime 
	 * @return
	 */
	Integer $selection(@Param("tenderId") Integer tenderId,@Param("type") Integer type,@Param("state") Integer state,@Param("updateTime") Date updateTime);
	
	/***
	 * 删除投标信息
	 * @param tender_id
	 * @return
	 */
	Integer $delete(@Param("taskId") Integer taskId,@Param("userId") Integer userId);
	
}
