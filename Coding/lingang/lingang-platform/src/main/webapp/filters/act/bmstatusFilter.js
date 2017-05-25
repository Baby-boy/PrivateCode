define([],function(){
    return ['bmstatusFilter',function(){
    	return function(enrollEtime,enrollStime,actStime,limitPeos,peopleNum){
    		var today = new Date();
    		var out = "报名中";
    		
    		if(today.getTime() < enrollStime){
    			out = "未开始";
    		}
    		
    		if(enrollEtime >= today.getTime() && today.getTime() >= enrollStime){
    			out = "报名中";
    		}
    		if(enrollEtime < today.getTime() ){
    			out = "已截止";
    		}
    		if(actStime < today.getTime() ){
    			out = "已发团";
    		}
    		if(limitPeos != null && limitPeos <= peopleNum ){
    			out = "已报满";
    		}
    		
    		return out;
    	};
    }];
});
