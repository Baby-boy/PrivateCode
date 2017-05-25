$(function () {
    //2017-2-13新加
//    $("select").select();
    /*laydate({
        elem: '#yugu', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
        event: 'focus' //响应事件。如果没有传入event，则按照默认的click
    });*/


    $(".hall .type-three span").click(function () {
        var index=$(this).index(".hall .type-three span");
        if(index==0){
            $(this).addClass("nowok").siblings().removeClass("nowok");
        }else{
            $(".hall .type-three span").eq(0).removeClass("nowok");
            $(this).toggleClass("nowok");
        }
    });

    $(" .check1 .more").click(function () {
        if($(this).parent().height()==25){
            $(this).parent().css("height","auto");
        }else{
            $(this).parent().css("height","25px");
        }
    });


    // 发布大厅部分确认发布弹出框
    $(".hall .fabu .button-ok").click(function () {
    	var taskName = $(".taskName").val();
    	
    	var taskAddrP = $(".taskAddrP").val();
    	var taskAddrC = $(".taskAddrC").val();
    	var taskAddrA = $(".taskAddrA").val();
    	var taskAddr = taskAddrP+taskAddrC+taskAddrA;
    	var taskSize = $(".taskSize").val();
    	var description = $(".description").val();
    	
    	var taskPtime = $(".taskPtime").val();
    	var price = $(".price").val();
    	
    	if(price == ""){
    		price = "0";
    	}else if(price > 9999999){
    		Popups("对不起，佣金金额不能大于9999999元");
    		return;
    	}
    	if(taskPtime == ""){
    		alert("请输入项目投标截止时间！");
    		return;
    	}
    	taskPtime = new Date(taskPtime);
    	
    	var taskTypes = $(".taskType.nowok");
    	var taskLabel1s = $(".check1 .now");
    	var taskLabel2s = $(".check1s .now");
    	
    	
    	var term = $(".term").val();
    	if(taskName == ""){
    		Popups("请输入项目名称！");
    		return;
    	}
    	if((taskLabel1s.length + taskLabel2s.length) == 0){
    		Popups("请选择专业及任务类型！");
    		return;
    	}
    	if(!$(".isOk").is(':checked')){
    		Popups("必须同意协议后才能发布任务！");
    		return;
    	}
    	
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
    	
    	var url = "/page/task/insert";
    	var datas = {
			"taskName":taskName,
			"taskAddr":taskAddr,
			"taskSize":taskSize,
			"description":description,
			"typeIds":typeIds,
			"labelIds":labelIds,
			"description":description,
			"term":term,
			"price":price,
			"fileIds":_fileIds,
			"taskPtime":taskPtime
    	}
    	var result = function(data){
    		Popups(data.msg,function(){
    			if(data.code==200){
        	        window.location.href="/list-guzhu/guzhufang"
        		}
    		});
    	}
    	sendPost(url,datas,result);
        
    });
 // 发布大厅部分确认发布弹出框
    $(".fb-tuijian > button").on("click",function () {
        $(".zhezhao").addClass("dis-none");
        $(".fb-tuijian").addClass("dis-none");
    });
    
    
    // 发布大厅弹出框点击全选效果
    $(".fb-tuijian .title input").click(function () {
       if($(this)[0].checked==true){
           for(var i=0;i<$(".fb-tuijian .rencai input").length;i++){
               $(".fb-tuijian .rencai input").eq(i)[0].checked=true
           }
       }else{
           for(var i=0;i<$(".fb-tuijian .rencai input").length;i++){
               $(".fb-tuijian .rencai input").eq(i)[0].checked=false
           }
       }
       checkcg();
    });
    $(".fb-tuijian .rencai input").click(function(){
    	checkcg();
    })
    
    function checkcg(){
    	var inputcg=[];
    	$(".fb-tuijian .rencai input").each(function(i,obj){
        	if(obj.checked==true){
        		inputcg.push(obj)
        	}
        });
    	if(inputcg.length==0){
    		$(".fb-tuijian > a").show();
    		$(".fb-tuijian > button.ok").hide();
    		if($(".fb-tuijian .title input")[0]){
    			$(".fb-tuijian .title input")[0].checked=false;
    		}
    	}else{
    		$(".fb-tuijian > a").hide();
    		$(".fb-tuijian > button.ok").show();
    	}
    }
    checkcg();
 // 专业及任务类型选择
    // 循环数组将已选择的内容添加已选择标签
    function Domtype() {
        $(".yixuan .psadd").html("");
        var typenum,check1,check1s,check2s;
        $(".check1 li").each(function(i,obj){
            if($(obj).attr("class")=="now"){
                var nums=0;
                var dates=$(obj).attr("data-val");
                check1=$(obj).html();
                $(".check1s li:eq("+i+") span").each(function(j,objed){
                    if($(objed).attr("class")=="now"){
                        if(nums==0){
                           check1s=$(objed).html(); 
                           check2s=$(objed).html();
                        }else if(nums>=1){
                            check1s=check2s+"/..";
                            check2s="";
                            nums=0;
                            return false; 
                        }
                        nums++;
                    }
                });
                if($(".check1s li:eq("+i+") span[class='now']").length>0){
                    var checkoks="<p data-val="+dates+"><span>"+check1+"-"+check1s+"</span><i></i></p>";
                    $(".yixuan .psadd").append(checkoks);
                }else{
                    $(obj).removeClass("now")
                }
            }
        })
    }
    Domtype();

    function DomtypeTwo(){
        var typenum,check1,check1s,check2s;
        $(".check1 li").each(function(i,obj){
            $(".check1s li:eq("+i+") span").each(function(j,objed){
                if($(objed).attr("class")=="now"){
                    $(".check1 li").eq(i).addClass("now");
                }
            })
        })
    }
    // 一级栏目点击事件
    $(".check1 li").click(function () {
        if($(this).attr("class")=="more"){return false}
        var index=$(this).index(".check1 li");
        $(this).siblings().removeClass("now");
        $(this).addClass("now");
        $(".check1s li").addClass("dis-none").eq(index).removeClass("dis-none");
        $(this).parent().css("height","25px");
        DomtypeTwo();
    });
    // 二级栏目点击事件
    $(".check1s li").find("span").click(function () {
        var index=$(this).index();
        var indexT=$(this).parent().index();
        $(".check1 li").eq(indexT).addClass("now");
            if(index==0){
                $(this).toggleClass("now").siblings().removeClass("now");
            }else{
                $(this).parent().find("span").eq(0).removeClass("now");
                $(this).toggleClass("now");
            }
            Domtype();
            condition();
    });
    $(".yixuan").click(function (e) {
        var ev=e||window.event;
        var targets=ev.target;
        if(targets.nodeName=="I"){
            var index=parseInt($(targets).parent().attr("data-val"))-1;
            $(targets).parent().remove();
            var index2=$(".yixuan .psadd p").length>0?parseInt($(".yixuan .psadd p").eq(0).attr("data-val"))-1:0;
            $(".check1 li").eq(index).removeClass("now");
            $(".check1s li").addClass("dis-none").eq(index2).removeClass("dis-none");
            $(".check1s li").eq(index).each(function(i,obj){
                $(obj).find("span").removeClass("now");
            });
        }
        Domtype();
        condition();
    });
    // 任务描述字数限制
    $(".type-five textarea").keyup(function () {
        var len=$(this).val().length;
        var max=1000;
        if(len>=1000){
            len=1000;
        }
        $(this).val($(this).val().slice(0,1000));
        $(".type-five p i").html(len);
    });

    // 任务附件
    /*$(".type-seven input").change(function () {
    	
    });*/
    $(".type-seven .chuanok").click(function (event) {
        if(event.target.nodeName=="SPAN"){
        	var fileId = $(event.target).attr("data-fileId");
        	var taskId = $(event.target).attr("data-taskId");
        	var url = "/page/task/delTaskFile";
        	var datas = {
        		"fileId":fileId,
        		"taskId":taskId
        	}
        	var result = function(data){
        		if(data.code == 200){
        			$(event.target).parent().remove();
        		}
        		Popups(data.msg);
        	}
        	sendPost(url,datas,result);
        }
    });
});