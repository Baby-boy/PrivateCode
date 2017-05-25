package com.yd.gcj.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yd.gcj.entity.YdMangerTask;
import com.yd.gcj.entity.YdMangerTaskLabel;
import com.yd.gcj.entity.YdMangerTaskLabelTR;
import com.yd.gcj.entity.YdMangerTaskType;
import com.yd.gcj.entity.YdMangerTaskTypeTR;
import com.yd.gcj.entity.vo.YdMangerTaskLabelVo;
import com.yd.gcj.mapper.YdMangerMapperTask;
import com.yd.gcj.mapper.YdMangerMapperTaskLabel;
import com.yd.gcj.mapper.YdMangerMapperTaskLabelTR;
import com.yd.gcj.mapper.YdMangerMapperTaskType;
import com.yd.gcj.mapper.YdMangerMapperTaskTypeTR;
import com.yd.gcj.service.YdMangerServiceTask;
import com.yd.gcj.tool.MapFactory;
import com.yd.gcj.tool.MapInitFactory;
import com.yd.gcj.tool.Values;

@Service("YdMangerServiceTask")
public class YdMangerServiceImplTask implements YdMangerServiceTask{
	
	@Autowired
	private YdMangerMapperTask ydMangerMapperTask;
	
	@Autowired
	private YdMangerMapperTaskType ydMangerMapperTaskType;
	
	private YdMangerMapperTaskLabel ydMangerMapperTaskLabel;
	
	@Autowired
	private YdMangerMapperTaskTypeTR ydMangerMapperTaskTypeTR;
	
	@Autowired
	private YdMangerMapperTaskLabelTR ydMangerMapperTaskLabelTR;
	
	@Autowired
	private ServletContext context;
	
	@Override
	public Object $queryByPageNum(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			Integer startPageNum = (Integer) map.get("startPageNum");
			Integer queryPageNum = (Integer) map.get("queryPageNum");
			mapInitFactory.init().setData(ydMangerMapperTask.$queryByPageNum(startPageNum, queryPageNum));
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object $queryByEId(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			Integer task_uid = (Integer) map.get("task_uid");
			mapInitFactory.init().setData(ydMangerMapperTask.$queryByEId(task_uid));
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object $queryBySId(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			Integer task_uid = (Integer) map.get("task_uid");
			mapInitFactory.init().setData(ydMangerMapperTask.$queryBySId(task_uid));
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object $queryBySql(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			String sql = (String) map.get("sql");
			mapInitFactory.init().setData(ydMangerMapperTask.$queryBySql(sql));
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object $queryAll() {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			mapInitFactory.init().setData(ydMangerMapperTask.$queryAll());
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object $queryById(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			Integer task_id = (Integer) map.get("task_id");
			mapInitFactory.init().setData(ydMangerMapperTask.$queryById(task_id));
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		
		return mapInitFactory.getMap();
	}

	@Override
	public Object $queryCountNumByEId(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			Integer task_uid = (Integer) map.get("task_uid");
			mapInitFactory.init().setData(ydMangerMapperTask.$queryCountNumByEId(task_uid));
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object $queryCountNum() {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			mapInitFactory.init().setData(ydMangerMapperTask.$queryCountNum());
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

	@Override
	@Transactional
	public Object $insert(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			YdMangerTask task = (YdMangerTask) MapFactory.toObject(map, YdMangerTask.class);
			
			Integer success = ydMangerMapperTask.$insert(task);
			
			if(success>0){
				String typeIds = (String) map.get("typeIds");
				String labelIds = (String) map.get("labelIds");
				
				String[] tids = typeIds.split(",");
				String[] lids = labelIds.split(",");
				
				List<YdMangerTaskType> taskTypes = (List<YdMangerTaskType>) context.getAttribute("taskTypes");
				if(taskTypes==null){
					taskTypes = ydMangerMapperTaskType.$queryAll();
					context.setAttribute("taskTypes", taskTypes);
				}
				List<YdMangerTaskLabelVo> taskLabelVos = new ArrayList<YdMangerTaskLabelVo>();
				if(context.getAttribute("taskLabelVos")!=null){
					taskLabelVos = (List<YdMangerTaskLabelVo>) context.getAttribute("taskLabelVos");
				}else{
					List<YdMangerTaskLabel> taskLabels = ydMangerMapperTaskLabel.$queryAll();
					List<YdMangerTaskLabel> tls = new ArrayList<YdMangerTaskLabel>();
					for(YdMangerTaskLabel label : taskLabels){
						if(label.getTaskl_type()==1){
							YdMangerTaskLabelVo labelVo = new YdMangerTaskLabelVo();
							labelVo.setTaskl_id(label.getTaskl_id());
							labelVo.setTaskl_name(label.getTaskl_name());
							labelVo.setTaskl_ulid(label.getTaskl_ulid());
							labelVo.setTaskl_type(label.getTaskl_type());
							labelVo.setTaskl_desc(label.getTaskl_desc());
							taskLabelVos.add(labelVo);
						}else if(label.getTaskl_type()==2){
							tls.add(label);
						}
					}
					for(YdMangerTaskLabelVo labelvo : taskLabelVos){
						List<YdMangerTaskLabel> labels = new ArrayList<YdMangerTaskLabel>();
						for(YdMangerTaskLabel label : tls){
							if(labelvo.getTaskl_id()==label.getTaskl_ulid()){
								labels.add(label);
							}
						}
						labelvo.setLabels(labels);
					}
					context.setAttribute("taskLabelVos", taskLabelVos);
				}
				
				Integer task_id = task.getTask_id();
				
				List<YdMangerTaskTypeTR> types = new ArrayList<YdMangerTaskTypeTR>();
				List<YdMangerTaskLabelTR> taskLabelTRs = new ArrayList<YdMangerTaskLabelTR>();
				
				if(tids.length>0){
					for(String tid : tids){
						Integer id = Integer.parseInt(tid);
						for(YdMangerTaskType taskType:taskTypes){
							if(taskType.getTaskt_id()==id){
								YdMangerTaskTypeTR taskTypeTR = new YdMangerTaskTypeTR();
								taskTypeTR.setTaskttr_name(taskType.getTaskt_name());
								taskTypeTR.setTaskttr_tid(task_id);
								taskTypeTR.setTaskttr_ttid(taskType.getTaskt_id());
								types.add(taskTypeTR);
							}
						}
					}
				}
				
				if(lids.length>0){
					for(String lid:lids){
						Integer id = Integer.parseInt(lid);
						for(YdMangerTaskLabelVo taskLabelVo:taskLabelVos){
							if(taskLabelVo.getTaskl_id()==id){
								YdMangerTaskLabelTR label = new YdMangerTaskLabelTR();
								label.setTaskltr_desc(taskLabelVo.getTaskl_desc());
								label.setTaskltr_name(taskLabelVo.getTaskl_name());
								label.setTaskltr_tid(task_id);
								label.setTaskltr_tlid(taskLabelVo.getTaskl_id());
								label.setTaskltr_type(taskLabelVo.getTaskl_type());
								label.setTaskltr_pid(taskLabelVo.getTaskl_ulid());
								taskLabelTRs.add(label);
							}else{
								for(YdMangerTaskLabel taskLabel : taskLabelVo.getLabels()){
									if(taskLabel.getTaskl_id()==id){
										YdMangerTaskLabelTR label = new YdMangerTaskLabelTR();
										label.setTaskltr_desc(taskLabel.getTaskl_desc());
										label.setTaskltr_name(taskLabel.getTaskl_name());
										label.setTaskltr_tid(task_id);
										label.setTaskltr_tlid(taskLabel.getTaskl_id());
										label.setTaskltr_type(taskLabel.getTaskl_type());
										label.setTaskltr_pid(taskLabel.getTaskl_ulid());
										taskLabelTRs.add(label);
									}
								}
							}
						}
					}
				}
				for(YdMangerTaskTypeTR type:types){
					ydMangerMapperTaskTypeTR.$insert(type);
				}
				for(YdMangerTaskLabelTR taskLabelTR : taskLabelTRs){
					ydMangerMapperTaskLabelTR.$insert(taskLabelTR);
				}
				
				mapInitFactory.setMsg("200", "任务发布成功！");
			}else{
				mapInitFactory.setMsg("502", "参数有误，请检查后再试！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object $update(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			YdMangerTask task = (YdMangerTask) MapFactory.toObject(map, YdMangerTask.class);
			task.setTask_update_time(new Date());			
			Integer success = ydMangerMapperTask.$update(task);
			if(success>0){
				mapInitFactory.setMsg(Values.INITSUCCESSCODE, "添加成功");
			}else{
				mapInitFactory.setMsg("502", "添加失败");
			}
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object $delete(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {
			Integer task_id = (Integer) map.get("task_id");
			mapInitFactory.init().setData(ydMangerMapperTask.$delete(task_id));
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}
	
	
	
	
	
}
