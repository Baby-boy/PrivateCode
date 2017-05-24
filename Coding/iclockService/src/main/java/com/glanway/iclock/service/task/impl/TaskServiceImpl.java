/**
 * @author zhangshuang
 * 2017年4月18日 下午1:41:28
 */
package com.glanway.iclock.service.task.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ponly.webbase.domain.Filters;
import org.ponly.webbase.domain.Page;
import org.ponly.webbase.domain.Pageable;
import org.ponly.webbase.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glanway.iclock.dao.task.TaskDao;
import com.glanway.iclock.entity.task.Task;
import com.glanway.iclock.service.BaseServiceImpl;
import com.glanway.iclock.service.task.TaskService;
import com.google.common.base.Predicate;
import com.thoughtworks.xstream.mapper.Mapper.Null;

/**
 * 说明 : 
 *  任务实现类
 * @author zhangshaung
 * @version 1.0.0
 * @dateTime 2017年4月18日 下午1:41:28
 */
@Service("taskService")
@Transactional
public class TaskServiceImpl extends BaseServiceImpl<Task> implements TaskService {
	
	@Autowired
	private TaskDao taskDao;

	/**
	 * 
	 * 说明 : 根据设备代码SN,删除(逻辑删除)所有关于当前设备的记录
	 * 
	 * @param sn
	 * @return
	 * @author zhangshaung
	 * @dateTime 2017年4月18日 下午4:37:29
	 */
	@Override
	public int chearCommandsBySn(String sn) {
		// TODO Auto-generated method stub
		if(null == sn && "".equals(sn)){
			return 0;
		}
		return taskDao.chearCommandsBySn(sn);
	}

	/**
	 * 
	 * 说明 : 
	 * 根据设备代码SN,修改当前命令的状态
	 * @param sn 设备代码sn
	 * @param state 将要修改的状态
	 * @param oldSate 指定某一状态
	 * @author zhangshaung
	 * @dateTime 2017年4月18日 下午6:08:52
	 */
	@Override
	public void updateStateBySn(String sn, Integer state, Integer oldState) {
		// TODO Auto-generated method stub
		
		if(null != sn && !("".equals(sn)) && null != state && state > 0){
			Map<String, Object> params=new HashMap<String, Object>();
			params.put("sn", sn);
			params.put("state", state);
			
			if(null != oldState && oldState > 0){
				params.put("oldState", oldState);
			}
			taskDao.updateStateBySn(params);
		}
		
	}

	/**
	 * 
	 * 说明 : 
	 * 根据任务ID,修改当前命令的状态
	 * @param id 任务(命令)Id
	 * @param state 将要修改的状态
	 * @param oldSate 指定某一状态
	 * @author zhangshaung
	 * @dateTime 2017年4月18日 下午6:08:52
	 */
	@Override
	public void updateStateById(Long id, Integer state, Integer oldState) {
		// TODO Auto-generated method stub
		if(null != id && !("".equals(id)) && null != state && state > 0){
			Map<String, Object> params=new HashMap<String, Object>();
			params.put("id", id);
			params.put("state", state);
			
			if(null != oldState && oldState > 0){
				params.put("oldState", oldState);
			}
			taskDao.updateStateById(params);
		}
	}
	
	/**
	 * 
	 * 说明 : 
	 * 根据条件查询前20条数据
	 * @param params
	 * @return
	 * @author zhangshaung
	 * @dateTime 2017年4月20日 下午7:59:00
	 */
	@Override
	public List<Task> findManyBeforeSize(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return taskDao.findManyBeforeSize(params);
	}

}
