package com.yd.gcj.service.impl;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.YdMangerTaskSpace;
import com.yd.gcj.service.YdMangerServiceTaskSpace;
@Service("YdMangerServiceTaskSpace")
public class YdMangerServiceImplTaskSpace implements YdMangerServiceTaskSpace {
	
	@Autowired
	private ServletContext context;
	
	@Override
	public YdMangerTaskSpace $taskSpace() {
		
		YdMangerTaskSpace taskSpace = null;
		
		if(context.getAttribute("taskSpace")!=null){
			taskSpace = (YdMangerTaskSpace) context.getAttribute("taskSpace");
			
		}else{
			taskSpace = new YdMangerTaskSpace();
			
			taskSpace.setWorking_task(245);
			taskSpace.setWorking_user(1907);
			taskSpace.setEnd_task(86);
			taskSpace.setCount_price(10546271);
			
			context.setAttribute("taskSpace", taskSpace);
		}
		 
		return taskSpace;
	}

}
