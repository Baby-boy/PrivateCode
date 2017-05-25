define([],function(){
    return ['payTypeFilter',function(){
        return function(input) {
        	var out = "支付宝";
        	switch(input){
        		case 1 :out = "支付宝";break;
        		case 2 :out = "微信";break;
        		default:out="微信";
        	}
        	return out;
        };
    }];
});