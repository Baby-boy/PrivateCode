//添加投标信息
function tender(taskId){
	var url="/page/tender/tender";
	var tenderPrice = $(".tenderPrice").val();
	if(tenderPrice > 9999999){
		Popups("对不起，投标佣金金额不能大于9999999");
		return;
	}
	var result = function(data){
		Popups(data.msg,function(){
			if(data.code==200 || data.code==503 || data.code==504){
				window.location.href="/task/task";
			}else if(data.code==501 || data.code == 502){
				window.location.href="/index";
			}
		});
	}
	sendAjax(url,"tender","","name",{"taskId":taskId},result);
}

//修改任务信息
function taskUpdate(taskId){
	var taskName = $(".taskName").val();
	var taskAddr = $(".taskAddr").val();
	var taskSize = $(".taskSize").val();
	var description = $(".description").val();
	var term = $(".term").val();
	var price = $(".price").val();
	var filesPath = "/files/test.rar";
	
	var taskTypes = $(".taskType.nowok");
	var taskLabel1s = $(".check1 .now");
	var taskLabel2s = $(".check1s .now");
	var typeIds = "";
	var labelIds = "";
	if(taskTypes.length>0){
		for(var i = 0;i<taskTypes.length;i++){
			var typeId = $(taskTypes[i]).attr("data");
			if(typeIds.length>0)
				typeIds += ",";
			typeIds += typeId;
		}
	}else{
		typeIds = "0";
	}
	
	for(var i = 0;i<taskLabel1s.length;i++){
		var labelId = $(taskLabel1s[i]).attr("data");
		if(labelIds.length>0)
			labelIds += ","
		labelIds += labelId;
	}
	for(var i = 0;i<taskLabel2s.length;i++){
		var labelId = $(taskLabel2s[i]).attr("data");
		if(labelId!=0){
			if(labelIds.length>0)
				labelIds += ",";
			labelIds += labelId;
		}
	}
	
	var url = "/page/task/update";
	var datas = {
		"taskId":taskId,
		"taskName":taskName,
		"taskAddr":taskAddr,
		"taskSize":taskSize,
		"description":description,
		"typeIds":typeIds,
		"labelIds":labelIds,
		"description":description,
		"term":term,
		"price":price,
		"filesPath":filesPath
	}
	var result = function(data){
		Popups(data.msg,function(){
			if(data.code==200){
				$(window).scrollTop(1);
		        $(".zhezhao").removeClass("dis-none");
		        $(".fb-tuijian").removeClass("dis-none");
			}
		});
	}
	sendPost(url,datas,result);
}

//添加关注
function addAtt(userBId,tag){
	var url = "/page/att/addAtten";
	var btndata = $(tag).attr("data");
	if(btndata == 1){
		url = "/page/att/delAtten";
	}
	var datas = {
		"userBId":userBId
	}
	var result = function(data){
		Popups(data.msg,function(){
			if(data.code==200){
				if(btndata==0){
					$(tag).html("取消关注");
					$(tag).attr("data",1)
				}else{
					$(tag).html("关注");
					$(tag).attr("data",0);
				}
			}else{
				window.location.href="/list-login/login";
			}
		});
		
	}
	sendPost(url,datas,result);
}

//服务商收藏和取消收藏任务
function collect(taskId,tag){
	var url = "/page/collection/insert";
	var state = $(tag).attr("data");
	if(state==1){
		url = "/page/collection/delete";
	}
	var datas = {
			"taskId":taskId
	}
	var result = function(data){
		Popups(data.msg,function(){
			if(data.code == 200){
				if(state == 0){
					$(tag).html("取消收藏");
					$(tag).attr("data",1);
				}else{
					$(tag).html("收藏任务");
					$(tag).attr("data",0);
				}
			}else if(data.code == 6){
				window.location.href="/list-login/login";
			}
		});
	}
	sendPost(url,datas,result);
}

//取消关注
function cancelAtt(userBId,tag){
	var url = "/page/att/delAtten";
	var datas = {
		"userBId":userBId
	}
	var result = function(data){
		Popups(data.msg,function(){
			if(data.code==200){
				$(tag).parent("li").remove();
				var attNum = $(".p2 .attNum").html();
				attNum = attNum-1;
				$(".p2 .attNum").html(attNum);
			}else{
				window.location.href="/list-login/login";
			}
		});
	}
	sendPost(url,datas,result);
}

//取消投标信息
function cancelTender(taskId,tag){
	if(window.confirm("你确定要撤销吗？")){
		var url = "/page/tender/delete";
		var state = $(tag).attr("data");
		var datas = {
				"taskId":taskId
		}
		var result = function(data){
			Popups(data.msg,function(){
				if(data.code == 200){
					$(tag).html("申请任务");
					var hrefstr = "/list-fuwushang/shenqingrenwu-bj/"+taskId;
					$(tag).attr({"data":0,"href":hrefstr,"onclick":""});
				}else if(data.code == 6){
					window.location.href="/list-login/login";
				}
			});
		}
		sendPost(url,datas,result);
	}
}

//添加任务评论信息
function active(taskId,userBId,groupNum,tag){
	var url = "/page/task_active/active";
	var contents = $(tag).val();
	if(contents.length > 0){
		var datas = {
				"taskId":taskId,
				"contents":contents,
				"userBId":userBId,
				"groupNum":groupNum
		}
		var result = function(data){
			Popups(data.msg,function(){
				if(data.code == 200){
					window.history.go(0);
				}else if(data.code == 501){
					window.location.href="/list-login/login";
				}
			});
		}
		sendPost(url,datas,result);
	}else{
		Popups("评论信息不能为空！");
	}
	
}


function condition(){
	var taskTypes = $(".taskType .active");
	var taskTypeIds = "";
	for(var i = 0;i < taskTypes.length;i++){
		if(taskTypeIds.length > 0){
			taskTypeIds += ",";
		}
		taskTypeIds += $(taskTypes[i]).attr("data");
	}
	var taskLabels = $(".taskLabel .now");
	var taskLabelIds = "";
	for(var i = 0;i < taskLabels.length;i++){
		if(taskLabelIds.length > 0){
			taskLabelIds += ",";
		}
		taskLabelIds += $(taskLabels[i]).attr("data");
	}
	
	if(taskLabelIds == ""){
		taskLabelIds = "-1";
	}
	var taskAddr = $(".taskAddr").val();
	var taskState = $(".taskState").val();
	var taskTerm = $(".taskTerm").val();
	
	var url = "/page/task/condition";
	var datas = {
		 "taskTypeIds":taskTypeIds,
		 "taskLabelIds":taskLabelIds,
		 "taskAddr":taskAddr,
		 "taskState":taskState,
		 "taskTerm":taskTerm
	};
	var result = function(data){
		if(data.code == 200){
			window.history.go(0);
		}
	}
	sendPost(url,datas,result);
}

function refund(taskId){
	var rason = $(".reason").val();
	var content = $(".content").val();
	
	var url = "/page/refund/add";
	var datas = {
		"taskId":taskId,
		"reason":rason,
		"content":content
	}
	var result = function(data){
		Popups(data.msg,function(){
			if(data.code == 200){
				
			}
		});
	}
	
	sendPost(url,datas,result);
}


var tpmId = 0;
//点击申请付款按钮
function isFK(id){
	tpmId = id;
	$(window).scrollTop(1);
    $(".zhezhao").removeClass("dis-none");
    $(".popup-shenqing").removeClass("dis-none");
}

//服务商申请雇主确认付款
function sqfk(userId,taskId){
	var url = "/page/taskPM/subPayAppli";
	var datas = {
		"userId":userId,
		"taskId":taskId,
		"tpmId":tpmId
	}
	var result = function(data){
		Popups(data.msg,function(){
			if(data.code == 200){		
			    $(".popup-shenqing,.zhezhao").addClass("dis-none");
			}
		});
	}
	sendPost(url,datas,result);
}


function newfile(){
	$('.newfile').removeClass('dis-none');
	$('.zhezhao').removeClass('dis-none');
	$('.newfile .guanbi').click(function(){
		$('.newfile').addClass('dis-none');
		$('.zhezhao').addClass('dis-none');
	})
}

function upfile(v){
	var file = $('#efile')
	if($.trim(file.val())==''){
		 return false;
	}
	filename('文件名');
}
function filename(n){
	$('.img-list').append('<div class="preview-list clear"><a target="_blank" href="javascript:;" ><i></i><p>'+n+'</p><a href="javascript:;" class="del">删除</a></a></div>');
}
function addFolder(taskId){
	var folderName = $('.filenName').val();
	console.log(folderName)
	//var folderName = prompt('请输入文件夹名称！', '');
	if(folderName == null){
		return;
	}
	if(folderName == ""){
		Popups("文件夹名称不能为空！");
		return;
	}
	var url = "/page/task/addFolder";
	var datas = {
		"taskId":taskId,
		"folderName":folderName
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
function filelist(){
	$('.filelist').removeClass('dis-none');
	$('.zhezhao').removeClass('dis-none');
	$('.filelist .guanbi').click(function(){
		$('.filelist').addClass('dis-none');
		$('.zhezhao').addClass('dis-none');
	})
}