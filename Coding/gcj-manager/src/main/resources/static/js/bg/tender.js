
//选标
function selectionE(taskId,tenderId,userSId,type,tag){
	var tagData = $(tag).attr("data");
	type = tagData==1?0:type;
	var url = "/page/tender/selection";
	var datas = {
		"taskId":taskId,
		"tenderId":tenderId,
		"userSId":userSId,
		"type":type
	}
	var result = function(data){
		Popups(data.msg,function(){
			if(data.code == 200){
				window.location.href="/list-guzhu/guzhu-daiqianding";
			}
		});
	}
	sendPost(url,datas,result);
}
//选择、取消组员选标
function selectionS(taskId,tenderId,userSId,type,tag){
	var tagData = $(tag).attr("data");
	var ms = "";
	type = tagData==1?0:type;
	var url = "/page/tender/selection";
	var datas = {
		"taskId":taskId,
		"tenderId":tenderId,
		"userSId":userSId,
		"type":type
	}
	var result = function(data){
		Popups(data.msg,function(){
			if(data.code == 200){
				if(tagData == 1){
					$(tag).attr("data",0);
					$(tag).html("选定");
					$(".select"+tenderId).show();
				}else{
					$(tag).attr("data",1);
					$(".select"+tenderId).hide();
					$(tag).html("取消选定");
					$(tag).show();
				}
			}
		});
	}
	sendPost(url,datas,result);
}

//修改投标信息
function updateTender(tenderId,taskId){
	var url="/page/tender/update";
	var tenderPrice = $(".tenderPrice").val();
	if(tenderPrice > 9999999){
		Popups("对不起，投标佣金金额不能大于9999999");
		return;
	}
	var result = function(data){
		Popups(data.msg,function(){
			if(data.code==200){
				window.location.href="/list-fuwushang/fuwushang";
			}else if(data.code==503 || data.code==504){
				window.location.href="/task/task";
			}else if(data.code==501 || data.code == 502){
				window.location.href="/index";
			}
		});
		
	}
	sendAjax(url,"tender","","name",{"taskId":taskId,"tenderId":tenderId},result);
}