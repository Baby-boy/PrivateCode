package com.glanway.iclock.service.task.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glanway.iclock.dao.task.TaskDao;
import com.glanway.iclock.entity.task.Task;
import com.glanway.iclock.service.BaseServiceImpl;
import com.glanway.iclock.service.task.TaskService;

/**
 * 说明 : 任务实现类
 * 
 * @author zhangshaung
 * @version 1.0.0
 * @dateTime 2017年4月18日 下午1:41:28
 */
@Service("taskService")
@Transactional
public class TaskServiceImpl extends BaseServiceImpl<Task> implements TaskService {

	@Autowired
	private TaskDao taskDao;

	@Override
	public int chearCommandsBySn(String sn) {
		if (null == sn && "".equals(sn)) {
			return 0;
		}
		return taskDao.chearCommandsBySn(sn);
	}

	@Override
	public void updateStateBySn(String sn, Integer state, Integer oldState) {
		if (null != sn && !("".equals(sn)) && null != state && state > 0) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("sn", sn);
			params.put("state", state);

			if (null != oldState && oldState > 0) {
				params.put("oldState", oldState);
			}
			taskDao.updateStateBySn(params);
		}

	}

	@Override
	public void updateStateById(Long id, Integer state, Integer oldState) {
		if (null != id && !("".equals(id)) && null != state && state > 0) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id", id);
			params.put("state", state);

			if (null != oldState && oldState > 0) {
				params.put("oldState", oldState);
			}
			taskDao.updateStateById(params);
		}
	}

	@Override
	public List<Task> findManyBeforeSize(Map<String, Object> params) {
		return taskDao.findManyBeforeSize(params);
	}

	@Override
	public List<Task> findTaskByDeviceSn(String sn) {
		return taskDao.findTaskByDeviceSn(sn);
	}

}
