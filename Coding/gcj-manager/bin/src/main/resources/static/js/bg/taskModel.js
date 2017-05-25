
//添加任务模块
function addModel(taskId){
	var name = $(".taskName").val();
	var ptime = $(".taskPtime").val();
	var etime = $(".taskEtime").val();
	var desc = $(".taskDesc").val();
	var price = $(".taskPrice").val();
	
	ptime = new Date(ptime);
	etime = new Date(etime);
	
	var url = "/page/task_model/add";
	var datas = {
		"taskId":taskId,
		"title":name,
		"ptime":ptime,
		"etime":etime,
		"desc":desc,
		"price":price
	};
	var result = function(data){
		Popups(data.msg,function(){
			if(data.code == 200){
				$(".popup-mokuaijia,.zhezhao").addClass("dis-none");
				window.history.go(0);
			}
		});
		
	}
	sendPost(url,datas,result);
}

//添加任务付款模块
function addTaskPM(taskId){
	var name = $(".taskPmName").val();
	var price = $(".taskPmPrice").val();
	var url = "/page/taskPM/insert";
	var datas = {
		"taskId":taskId,
		"name":name,
		"price":price
	}
	var result = function(data){
		Popups(data.msg,function(){
			if(data.code == 200){
				$(".popup-fukuanjia,.zhezhao").addClass("dis-none");
				window.history.go(0);
			}
		});
	}
	sendPost(url,datas,result);
}

//发布任务信息
function fabugonggao(taskId){
	var contents = $(".contents").val();
	var url = "/page/taskmsg/add";
	var datas = {
		"taskId":taskId,
		"contents":contents
	};
	var result = function(data){
		Popups(data.msg,function(){
			if(data.code == 200){
				$(".fbgg-popup,.zhezhao").addClass("dis-none");
				window.history.go(0);
			}
		});
	}
	sendPost(url,datas,result);
}

//添加任务评价信息
function epcs(taskId){
	
	var evaluation = $('.evaluation[name="ping"]:checked').val();
	var epcsReason = $(".epcsReason").val();
	var eContents = $(".eContents").val();
	var url = "/page/epcs/pcs";
	var datas = {
		"taskId":taskId,
		"evaluation":evaluation,
		"epcsReason":epcsReason,
		"eContent":eContents
	};
	
	var result = function(data){
		Popups(data.msg,function(){
			if(data.code == 200 || data.code == 502){
				$(".popup-evaluate,.zhezhao").addClass("dis-none");
			}else{
				window.location.href = "/index";
			}
		});
	}
	sendPost(url,datas,result);
}

//添加服务商评论信息
function spcs(taskId){
	var svaluation = $('.svaluation[name="ping"]:checked').val();
	var spcsReason = $(".spcsReason").val();
	var sContents = $(".sContents").val();
	
	var url = "/page/spcs/pcs";
	var datas = {
		"svaluation":svaluation,
		"spcsReason":spcsReason,
		"sContents":sContents,
		"taskId":taskId
	}
	var result = function(data){
		Popups(data.msg,function(){
			if(data.code == 200){
				
			}
		});
	}
	sendPost(url,datas,result);
}


