define([],function(){
    return ['orderStateFilter',function(){
        return function(input) {
        	var out = "";
        	switch(input){
        		case 1 :out = "已支付";break;
        		case 2 :out = "未支付";break;
        		case 3 :out = "申请退款";break;
        		case 4 :out = "退款成功";break;
        		case 5 :out = "拒绝退款";break;
        		case 6 :out = "取消订单";break;
        		default:out="未知状态";
        	}
        	return out;
        };
    }];
});