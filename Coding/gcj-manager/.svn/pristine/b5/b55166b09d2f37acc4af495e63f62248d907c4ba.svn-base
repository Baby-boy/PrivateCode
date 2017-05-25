//雇主确认合同或者拒绝合同
function submitContractStateMsg(taskId,state){
	var url = "/page/task/setContractState";
	var datas = {
		"taskId":taskId,
		"state":state
	}
	var result = function(data){
		Popups(data.msg,function(){
			if(data.code == 200){
				window.location.href="/list-guzhu/hetong/"+taskId;
			}
		});
	}
	sendPost(url,datas,result);
}


//模态框触发
var modal={
    result:[],
    recordId:0,
    init:function(){
        $('#add-module-trigger').click(function(){
            modal.show();
        });
        $('#close').click(function(){
            modal.close();
        });
        $('#confirm').click(function(){
        	
        	var taskId = $(".subPmBtn").attr("data");
        	var name = $(".taskPmName").val();
        	var price = $(".taskPmPrice").val();
        	var description = $(".taskPmDesc").val();
        	
        	var url = "/page/taskPM/insert";
        	var datas = {
        		"taskId":taskId,
        		"name":name,
        		"price":price,
        		"description":description
        	}
        	var result = function(data){
        		if(data.code == 200){
    				modal.getInput();
    	            modal.setInput(data.tpmId);
    	            modal.close();
    	            modal.result=[];
    	            modal.clearInput();
    			}else{
    				modal.close();
    				Popups(data.msg,null);
    			}
        		
        	}
        	sendPost(url,datas,result);
        	
        	
        });
        $('.contract-info>tbody').delegate('.btn-delete','click',function(e){
        	var tpmId = $(this).attr("data");
        	var url = "/page/taskPM/del";
        	var datas = {
        		"tpmId":tpmId
        	};
        	var result = function(data){
        		if(data.code == 200){
    				e.preventDefault();
    	            $(e.target).parent().parent().remove();
    			}else{
    				Popups(data.msg);
    			}
        	}
        	sendPost(url,datas,result);
        })
    },
    show:function(){
        $('#add-module').removeClass('hide');
    },
    close:function(){
        $('#add-module').addClass('hide');
    },
    getInput:function(){
        var taskName=$('#taskName').val().trim(),
            taskPrice=$('#taskPrice').val().trim(),
            taskDescription=$('#taskDescription').val().trim();
             this.recordId++;
            this.result.push({recordId:this.recordId,taskName:taskName,taskPrice:taskPrice,taskDescription:taskDescription});

    },
    setInput:function(taskPmId){
        var html='';
        var inputInfo=this.result[0];
        html+='<tr><td>'+inputInfo.taskName+'</td><td class="subprice">'+inputInfo.taskPrice+'</td><td>'+inputInfo.taskDescription+'</td><td><a class="btn-delete" href="#'+inputInfo.recordId+'" data="'+taskPmId+'">删除</a></td></tr>';
        $('.contract-info>tbody').append(html);
    },
    clearInput:function(){
        $('#taskName').val('');
        $('#taskPrice').val('');
        $('#taskDescription').val('');
    }

}
modal.init();
