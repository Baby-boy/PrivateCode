package com.yd.gcj.service.impl.page;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.YdMangerTaskSpace;

@Service("pageTaskSpace")
public class YdMangerServiceImplPageTaskSpace{
	
	@Autowired
	private ServletContext context;
	
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
