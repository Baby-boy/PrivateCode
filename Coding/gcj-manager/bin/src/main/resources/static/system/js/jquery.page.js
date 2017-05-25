
/**
2015-06-27
**/
(function($){
	var ms = {
		init:function(obj,args){
			return (function(){
				ms.fillHtml(obj,args);
				ms.bindEvent(obj,args);
			})();
		},
		//填充html
		fillHtml:function(obj,args){
			return (function(){
				var param = "?"+getUrlArgStr(["p"]);
				obj.empty();
				//上一页
				if(args.current > 1){
					obj.append('<li><a href="'+param+'&p='+(args.current-1)+'" class="prevPage"><span>上一页</span></a></li>');
				}else{
					obj.remove('.prevPage');
					obj.append('<li><span class="disabled">上一页</span></li>');
				}
				//中间页码
				if(args.current != 1 && args.current >= 4 && args.pageCount != 4){
					obj.append('<li><a href="'+param+'&p=1" class="tcdNumber"><span>'+1+'</span></a></li>');
				}
				if(args.current-2 > 2 && args.current <= args.pageCount && args.pageCount > 5){
					obj.append('<li><span>...</span></li>');
				}
				var start = args.current -2,end = args.current+2;
				if((start > 1 && args.current < 4)||args.current == 1){
					end++;
				}
				if(args.current > args.pageCount-4 && args.current >= args.pageCount){
					start--;
				}
				for (;start <= end; start++) {
					if(start <= args.pageCount && start >= 1){
						if(start != args.current){
							obj.append('<li><a href="'+param+'&p='+start+'" class="tcdNumber"><span>'+ start +'</span></a></li>');
						}else{
							obj.append('<li><span class="currentpage">'+ start +'</span></li>');
						}
					}
				}
				if(args.current + 2 < args.pageCount - 1 && args.current >= 1 && args.pageCount > 5){
					obj.append('<li><span>...</span></li>');
				}
				if(args.current != args.pageCount && args.current < args.pageCount -2  && args.pageCount != 4){
					obj.append('<li><a href="'+param+'&p='+args.pageCount+'" class="tcdNumber"><span>'+args.pageCount+'</span></a></li>');
				}
				//下一页
				if(args.current < args.pageCount){
					obj.append('<li><a href="'+param+'&p='+(args.current+1)+'" class="nextPage"><span>下一页</span></a></li>');
				}else{
					obj.remove('.nextPage');
					obj.append('<li><span class="disabled">下一页</span></li>');
				}
			})();
		},
		//绑定事件
		bindEvent:function(obj,args){
			return (function(){
				obj.on("click","a.tcdNumber",function(){
					var current = parseInt($(this).text());
					ms.fillHtml(obj,{"current":current,"pageCount":args.pageCount});
					if(typeof(args.backFn)=="function"){
						args.backFn(current);
					}
				});
				//上一页
				obj.on("click","a.prevPage",function(){
					var current = parseInt(obj.children("span.current").text());
					ms.fillHtml(obj,{"current":current-1,"pageCount":args.pageCount});
					if(typeof(args.backFn)=="function"){
						args.backFn(current-1);
					}
				});
				//下一页
				obj.on("click","a.nextPage",function(){
					var current = parseInt(obj.children("span.current").text());
					ms.fillHtml(obj,{"current":current+1,"pageCount":args.pageCount});
					if(typeof(args.backFn)=="function"){
						args.backFn(current+1);
					}
				});
			})();
		}
	}
	$.fn.createPage = function(options){
		var args = $.extend({
			pageCount : 10,
			current : 1,
			backFn : function(){}
		},options);
		ms.init(this,args);
	}
})(jQuery);

function getUrlArgStr(arr){
    var q=location.search.substr(1);  
    var qs=q.split('&');  
    var argStr='';  
    if(qs){  
        for(var i=0;i<qs.length;i++){
        	if ( arr.indexOf( qs[i].substring(0,qs[i].indexOf('=')) ) > -1 ) {
        		continue;
        	}
            argStr += qs[i].substring(0,qs[i].indexOf('='))+'='+qs[i].substring(qs[i].indexOf('=')+1)+'&';  
        }  
    }  
    return argStr;  
}
