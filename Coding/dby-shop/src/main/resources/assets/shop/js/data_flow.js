$(function(){
	
	var html = "<dd class=\"reply-input\">";
		html += "	<textarea placeholder=\"请输入您的回复\" rows=\"3\"></textarea>";
		html += "	<p><span class=\"submit\">确认回复</span></p>";
		html +="</dd>";
		
	$("body").on("click","dl span.reply",function(){
		$(this).parents(".list").find("dl span.reply").show();
		$(this).parents(".list").find("dl dd.reply-input").remove();
		$(this).hide().before(html);
	});
	
	
	// ajax分页
	information.messagepage = {
		url : function(args){
			return "/api/shop/information/message";
		},
		tpl: function(args){
			return "/shop/information/ajaxtemp::message";
		},
		params : function(args){
			return args;
		},
		verification : function(args, params){
			return true;
		},
		callback : function(args, data){
			$(".flow-details .message .list").html(data.responseText);
		}
		
	};
	// ajax回复留言
	information.msgreply = {
		url : function(args){
			return "/api/shop/information/mesreply";
		},
		params : function(args){
			return args;
		},
		verification : function(args, params){
			return true;
		},
		callback : function(args, data){
			//$(".flow-details .message .list").html(data.responseText);
			console.log(data.data);
			if(data.data === 1){
				$.alert({
					type:4,
					text:"提交成功",
					callback:function(){
						var page = {"id":$(".online-message .submit input").attr("data-val"),"current":$("#sales span.current").html()};
				    	information_messagepage(page);
					}
				});
			}else{
				$.alert({
					type:3,
					text:"提交失败"
				});
			}
		}
		
	};
	// ajax留言
	information.reply = {
			url : function(args){
				return "/api/shop/information/reply";
			},
			params : function(args){
				return args;
			},
			verification : function(args, params){
				return true;
			},
			callback : function(args, data){
				console.log(data.data);
				if(data.data === 1){
					$.alert({
						type:4,
						text:"提交成功",
						callback:function(){
							var page = {"id":$(".online-message .submit input").attr("data-val"),"current":$("#sales span.current").html()};
					    	information_messagepage(page);
						}
					});
				}else{
					$.alert({
						type:3,
						text:"提交失败"
					});
				}
			}
			
		};
	
	
	
	$("body").on("click","dl dd.reply-input p .submit",function(){
		var json = {
			"re_im_id":$(this).parents("dl").attr("data-val"),
			"re_user_id":5,
			"re_user_name":"你是猪吗",
			"re_user_avatar":"/assets/shop/images/demoimg/information_portrait.jpg",
			"re_im_message":$(this).parents(".reply-input").find("textarea").val()
		};
		information_msgreply(json);
		$(this).parents(".list").find("dl span.reply").show();
		$(this).parents(".reply-input").remove();
		
	});
	
	
	$("body").on("click",".online-message .submit input",function(){
		var json = {
			"im_id":$(this).attr("data-val"),
			"user_id":5,
			"user_name":"你是猪吗",
			"user_avatar":"/assets/shop/images/demoimg/information_portrait.jpg",
			"im_message":$(this).parents(".online-message").find(".textarea textarea").val()
		};
		information_reply(json);
		$(this).parents(".online-message").find(".textarea textarea").val("");
	});
	
	
	
});