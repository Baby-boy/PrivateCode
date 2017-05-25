function recharge(userId,type){
	var price = $(".price").val();
	
	var url = "/page/account/zfbRecharge";
	var datas = {
		"userId":userId,
		"price":price,
		"type":type
	};
	var result = function(data){
		if(data.code == 200){
			zfbNotive(userId,price);
		}else{
			Popups(data.msg);
		}
	}
	
	sendPost(url,datas,result);
	
}

function zfbNotive(userId,price){
	
	var url = "/page/account/zfbPayNotive";
	var datas = {
		"userId":userId,
		"price":price
	};
	var result = function(data){
		Popups(data.msg,function(){
			if(data.code == 200){
				window.history.go(0);
			}
		});
	}
	sendPost(url,datas,result);
	
}