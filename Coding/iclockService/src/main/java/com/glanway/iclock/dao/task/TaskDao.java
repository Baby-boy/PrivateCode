package com.glanway.iclock.dao.task;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.glanway.iclock.dao.BaseDao;
import com.glanway.iclock.entity.task.Task;

/**
 * 说明 : 任务 dao
 * 
 * @author zhangshaung
 * @version 1.0.0
 * @dateTime 2017年4月18日 上午10:12:36
 */
public interface TaskDao extends BaseDao<Task> {

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
	 * @param params
	 * @author zhangshaung
	 * @dateTime 2017年4月18日 下午6:08:52
	 */
	void updateStateBySn(Map<String, Object> params);

	/**
	 * 说明 : 根据任务ID,修改当前命令的状态
	 * 
	 * @param params
	 * @author zhangshaung
	 * @dateTime 2017年4月18日 下午6:08:52
	 */
	void updateStateById(Map<String, Object> params);

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
	 * @dateTime 2017年6月7日 下午1:49:46
	 */
	List<Task> findTaskByDeviceSn(@Param("sn") String sn);
}
