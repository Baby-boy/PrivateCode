function pageNum(num,pageName){
	var url = "/page/pageNum/pageNum";
	var datas = {
		"pageNum":num,
		"pageNumName":pageName
	};
	var result = function(data){
		if(data.code == 200){
			window.history.go(0)
		}else{
			Popups(data.msg);
		}
	}
	sendPost(url,datas,result);
}

$(function(){
	
	var pageLable = $(".pageLabel");
	var pageName = $(pageLable).attr("data-pageName");
	var pageNum = $(pageLable).attr("data-pageNum");
	var pageIndex = $(pageLable).attr("data-pageIndex");
	var maxIndex = $(pageLable).attr("data-maxIndex");
	var pageCount = $(pageLable).attr("data-pageCount");
	
	var priviousPageOnclick = " onclick=\"pageNum("+(pageIndex-1)+",'"+pageName+"')\"";
	if(pageIndex == 1){
		priviousPageOnclick = "";
	}
	var previousPageTag = "<a href='###'class='disabled' "+priviousPageOnclick+">上一页</a>";
	
	var nextNum = parseInt(pageIndex)+1;
	var nextPageOnclick = " onclick=\"pageNum("+nextNum+",'"+pageName+"')\"";
	
	if(pageIndex == maxIndex){
		nextPageOnclick = "";
	}
	var nextPageTag = "<a href='###' class='nextPage' "+nextPageOnclick+">下一页</a>";
	
	var pageTags = "";
	
	pageTags += previousPageTag;
	
	for(var i = 1;i <= maxIndex;i++){
		
		if(pageIndex == i){
			var nowPageTag = "<span class='current'>"+i+"</span>";
			pageTags += nowPageTag;
		}else{
			var pageTagOnclick = "";
			var pageTag = "<a href=\"javascript:pageNum("+i+",'"+pageName+"');\" class='tcdNumber'>"+i+"</a>";
			pageTags += pageTag;
		}
	}
	pageTags += nextPageTag;
	
	$(pageLable).html(pageTags);
	
});