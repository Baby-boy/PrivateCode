function uploadimg(tag){
	var f = function(path,name){
		var url = "/page/file/fileUpLoad";
		var datas = {
			"path":path,
			"name":name,
			"size":0
		}
		var result = function(data){
			if(data.code==200){
     			if(_fileIds.length>0){
     				_fileIds += ",";
     			}
     			_fileIds += data.fileId;
     			var fileId = data.fileId;
             	var fujian="<li><i></i><p>"+name+"</p><span data-fileid='"+fileId+"' data-taskid='-1'>删除</span></li>";
                $(".type-seven .chuanok").append(fujian);
     		}
		}
		sendPost(url,datas,result);
	}
	var j = function(num){
		runJDT(num);
	}
	uploadFile(tag,f,j);
}



/*$.ajaxFileUpload({
		url:'/page/file/upload/'+upLoadNum,//用于文件上传的服务器端请求地址  
    secureuri:false,//一般设置为false
    fileElementId:"files",//文件上传空间的id属性  <input type="file" id="file" name="file" />  
    dataType: 'json',
    type:"POST",
    success: function (data){
     	if(data.state!=1){
     		if(data.code==200){
     			if(_fileIds.length>0){
     				_fileIds += ",";
     			}
     			_fileIds += data.fileId;
     			var fileId = data.fileId;
             	var fileName = $(datas).val().slice($(datas).val().lastIndexOf("\\")+1);
             	var fujian="<li><i></i><p>"+fileName+"</p><span data-fileid='"+fileId+"' data-taskid='-1'>删除</span></li>";
                $(".type-seven .chuanok").append(fujian);
     		}
     		upLoadNum++;
     	}
    }
 });*/