package com.yd.gcj.system.service;

import com.yd.gcj.entity.YdMangerContract;

/**
 * description(项目任务所对应的合同信息管理)
 * @author Administrator
 */
public interface YdMangerServiceSystemContract {

	/**
	 * description(根据task_id查询所对应的合同信息)
	 * @param
	 * @param task_id
	 * @return
	 */
	YdMangerContract queryContractByTaskId(Integer task_id);

}
