package com.yd.gcj.system.mapper;

import com.yd.gcj.entity.YdMangerContract;

public interface YdMangerMapperSystemContract {

	/**
	 * description(根据task_id查询项目任务所对应的合同信息)
	 * @param
	 * @param task_id
	 * @return
	 */
	YdMangerContract queryContractByTaskId(Integer task_id);

}
