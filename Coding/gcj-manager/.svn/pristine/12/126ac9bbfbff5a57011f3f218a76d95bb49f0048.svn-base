package com.yd.gcj.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.YdMangerTaskLabel;
import com.yd.gcj.entity.vo.YdMangerTaskLabelVo;
import com.yd.gcj.mapper.YdMangerMapperTaskLabel;
import com.yd.gcj.service.YdMangerServiceTaskLabel;

@Service("YdMangerServiceTaskLabel")
public class YdMangerServiceImplTaskLabel implements YdMangerServiceTaskLabel{
	
	@Autowired
	private YdMangerMapperTaskLabel ydMangerMapperTaskLabel;
	
	@Autowired
	private ServletContext context;
	
	@Override
	public List<YdMangerTaskLabelVo> $queryAll() {
		
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
		
		return taskLabelVos;
		
	}

	@Override
	public Integer $queryCountNum() {
		return ydMangerMapperTaskLabel.$queryCountNum();
	}

	@Override
	public Integer $queryCountNumByType(Integer taskl_type) {
		return ydMangerMapperTaskLabel.$queryCountNum();
	}

	@Override
	public Integer $insert(YdMangerTaskLabel taskl) {
		return ydMangerMapperTaskLabel.$insert(taskl);
	}

	@Override
	public Integer $update(YdMangerTaskLabel taskl) {
		return ydMangerMapperTaskLabel.$update(taskl);
	}

	@Override
	public Integer $delete(Integer taskl_id) {
		return ydMangerMapperTaskLabel.$delete(taskl_id);
	}

}
