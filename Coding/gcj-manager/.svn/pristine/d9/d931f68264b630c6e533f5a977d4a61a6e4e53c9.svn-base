package com.yd.gcj.util;

import java.util.ArrayList;
import java.util.List;

import com.yd.gcj.entity.YdMangerLabel;
import com.yd.gcj.entity.vo.YdMangerLabelVo;

public class YdMangerLabelFactory {
	
	public static List<YdMangerLabelVo> getLabelVos(List<YdMangerLabel> labels){
		List<YdMangerLabelVo> taskLabelVos = new ArrayList<YdMangerLabelVo>();
		List<YdMangerLabel> tls = new ArrayList<YdMangerLabel>();
		
		for(YdMangerLabel label : labels){
			if(label.getLabel_type()==1){
				YdMangerLabelVo labelVo = new YdMangerLabelVo();
				labelVo.setLabel_id(label.getLabel_id());
				labelVo.setLabel_name(label.getLabel_name());
				labelVo.setLabel_pid(label.getLabel_pid());
				labelVo.setLabel_type(label.getLabel_type());
				labelVo.setIs_select(label.getIs_select());
				taskLabelVos.add(labelVo);
			}else if(label.getLabel_type()==2){
				tls.add(label);
			}
		}
		
		for(YdMangerLabelVo labelvo : taskLabelVos){
			List<YdMangerLabel> labels2 = new ArrayList<YdMangerLabel>();
			for(YdMangerLabel label : tls){
				if(labelvo.getLabel_id()==label.getLabel_pid()){
					labels2.add(label);
				}
			}
			labelvo.setLabels(labels2);
		}
		return taskLabelVos;
	}
	
}
