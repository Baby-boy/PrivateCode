package com.glanway.iclock.service.task;

import java.util.List;
import java.util.Map;

import com.glanway.iclock.entity.task.Task;
import com.glanway.iclock.service.BaseService;

/**
 * 说明 : 任务(存储命令)service
 * 
 * @author zhangshaung
 * @version 1.0.0
 * @dateTime 2017年4月18日 上午11:05:57
 */
public interface TaskService extends BaseService<Task> {

	/**
	 * 说明 : 根据设备代码SN,删除(逻辑删除)所有关于当前设备的记录
	 * 
	 * @param sn
	 * @return
	 * @author zhangshaung
	 * @dateTime 2017年4月18日 下午4:37:29
	 */
	int chearCommandsBySn(String sn);

	/**
	 * 说明 : 根据设备代码SN,修改当前命令的状态
	 * 
	 * @param sn
	 *            设备代码sn
	 * @param state
	 *            将要修改的状态
	 * @param oldSate
	 *            指定某一状态
	 * @author zhangshaung
	 * @dateTime 2017年4月18日 下午6:08:52
	 */
	void updateStateBySn(String sn, Integer state, Integer oldState);

	/**
	 * 说明 : 根据任务ID,修改当前命令的状态
	 * 
	 * @param id
	 *            任务(命令)Id
	 * @param state
	 *            将要修改的状态
	 * @param oldSate
	 *            指定某一状态
	 * @author zhangshaung
	 * @dateTime 2017年4月18日 下午6:08:52
	 */
	void updateStateById(Long id, Integer state, Integer oldState);

	/**
	 * 说明 : 根据条件查询前20条数据
	 * 
	 * @param params
	 * @return
	 * @author zhangshaung
	 * @dateTime 2017年4月20日 下午7:59:00
	 */
	List<Task> findManyBeforeSize(Map<String, Object> params);

	/**
	 * 说明 : 根据设备序列号查询该设备是否还存在任务
	 * 
	 * @param sn
	 * @return
	 * @author fuqihao
	 * @dateTime 2017年6月7日 下午1:47:37
	 */
	List<Task> findTaskByDeviceSn(String sn);
}
