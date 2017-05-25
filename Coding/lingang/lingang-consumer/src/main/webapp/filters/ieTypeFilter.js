define([],function(){
    return ['ieTypeFilter',function(){
    	return function(input){
    		var out = "借书";
    		if(input=='1'){
    			out = "借书";
    		}else if(input=='2'){
    			out = "还书";
    		}
    		return out;
    		
    	};
    }];
});
