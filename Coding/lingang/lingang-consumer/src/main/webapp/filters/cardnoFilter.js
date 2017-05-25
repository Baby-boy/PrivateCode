define([],function(){
    return ['cardnoFilter',function(){
    	return function(UUserCard,Uheight){
    		var storage = window.localStorage;
    		//活动信息
    		var actinfo = JSON.parse(storage.getItem("actinfo"));
    		var out = "";
    		var myDate = new Date();
            var month = myDate.getMonth() + 1;
            var day = myDate.getDate();
            var age = myDate.getFullYear() - UUserCard.substring(6, 10) - 1;
            if (UUserCard.substring(10, 12) < month || UUserCard.substring(10, 12) == month && UUserCard.substring(12, 14) <= day) {
                age++;
            }
            out =actinfo.actPrices[0].price;
    		/*if(actinfo.actPrices[0].type == 1){//统一价格
    			out = actinfo.actPrices[0];
    		}else */
    		if(actinfo.actPrices[0].type == 2){//价格不统一
    			if(actinfo.actPrices[1].heightthan != 0){
    				if(age <= actinfo.actPrices[1].youngerthan && Uheight <= actinfo.actPrices[1].heightthan){
        				out =actinfo.actPrices[1].price;
        			}
    			}else{
    				if(age <= actinfo.actPrices[1].youngerthan){
        				out =actinfo.actPrices[1].price;
        			}
    			}
    			if(age > actinfo.actPrices[2].olderthan){
    				out =actinfo.actPrices[2].price;
    			}
    		}else{//基础价格
				out =actinfo.actPrices[0].price;
			}
    		
    		return out;
    	};
    }];
});
