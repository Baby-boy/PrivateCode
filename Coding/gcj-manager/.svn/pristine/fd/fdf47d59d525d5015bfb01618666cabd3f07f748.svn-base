package com.yd.gcj.mapper;

import org.apache.ibatis.annotations.Param;

import com.yd.gcj.entity.YdMangerContract;

public interface YdMangerMapperContract {
	
	/***
	 * 查询指定合同信息
	 * @param contract_id
	 * @return
	 */
	YdMangerContract $queryById(@Param("contract_id") Integer contract_id);
	
	/***
	 * 多条件使用sql动态查询
	 * @param sql
	 * @return
	 */
	YdMangerContract $queryBySql(@Param("sql") String sql);
	
	/***
	 * 跟进任务查询该任务对应的合同信息
	 * @param tid
	 * @return
	 */
	YdMangerContract $queryByTid(@Param("tid") Integer tid);
	
	/***
	 * 跟进任务id检查是否存在该任务对应的合同信息
	 * @param tid
	 * @return
	 */
	Integer $isExsit(@Param("tid") Integer tid);
	
	/***
	 * 雇主添加合同信息
	 * @param contract
	 * @return
	 */
	Integer $insert(YdMangerContract contract);
	
	/***
	 * 服务商补充合同信息
	 * @param contract
	 * @return
	 */
	Integer $update(YdMangerContract contract);
	
	/**
	 * 雇主对合同进行内容补充
	 * @param id
	 * @param tid
	 * @param supp
	 * @return
	 */
	Integer $updateBuChong(@Param("id") Integer id,@Param("tid") Integer tid,@Param("supp") String supp);
	
	/***
	 * 删除指定合同信息
	 * @param contract_id
	 * @return
	 */
	Integer $delete(@Param("contract_id") Integer contract_id);
	
	/***
	 * 雇主签订合同
	 * @param contract
	 * @return
	 */
	Integer $eSign(YdMangerContract contract);
	
	/***
	 * 服务商签订合同
	 * @param contract
	 * @return
	 */
	Integer $sSign(YdMangerContract contract);

	/***
	 * 补充合同
	 * @param contract
	 * @return
	 */
	Integer supplementaryContract(@Param("contractId")Integer contractId, @Param("contractSupp")String contractSupp);
	
	
}
