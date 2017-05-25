define([],function(){

    return ['payStatusFilter',function(){
    	return function(input) {
    		var out = "支付宝";
    		switch(input){
    		case 1 :out="支付宝";break;
    		
    		default:out="支付宝";
    		}
    		return out;
    	}
    }];
});
