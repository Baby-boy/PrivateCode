var sendPost = function(urls,datas,mf){
	$.ajax({
		url:urls,
		type:"post",
		data:datas,
		dataType:"json",
		success:mf,
		error:function(){
			Popups("系统繁忙或参数错误，请稍后再试！");
		}
	});
}


var sendAjax = function(url,mark,tsmark,tagPropertysName,subDatas,method){
	var _datas = {};
	
	if(mark!=""){
		var tags = $("*["+mark+"='"+mark+"']");
		for(var i = 0;i<tags.length;i++){
			var propertyName = $(tags[i]).attr(tagPropertysName);
			var propertyValue = $(tags[i]).val();
			
			if($(tags[i]).attr("isNull")=="false" && propertyValue==""){
				Popups($(tags[i]).attr("keyWord")+"不能为空！");
				return;
			}
			
			_datas[ propertyName ] = propertyValue;
		}
	}
	
	if(tsmark!=""){
		var seltag = $("*["+tsmark+"='"+tsmark+"']:checked");
		$.each(seltag, function(k, v) {
			_datas[$(this).attr(tagPropertysName)] = $(this).val();
		});
	}
	
	if(subDatas.length!=0){
		$.each(subDatas, function(k, v) {
			_datas[k] = v;
		});
	}
	
	console.log(_datas);
	
	sendPost(url,_datas,method);
}