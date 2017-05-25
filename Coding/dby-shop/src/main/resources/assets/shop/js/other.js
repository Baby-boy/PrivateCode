var other = {};

$(function(){
	other.post_wish = {
		url : function(args){
			return "/simple/insert/wish";
		},
		params : function(args){
			return {
				"content": $(".wish-page textarea").val()
			};
		},
		verification : function(args, params){
			if(params.content.length == 0)
			{
				$.alert({
					type:3,
					text:"许愿内容不能为空"
				});
				return false;
			}
			return true;
		},
		callback : function(args, data){
			if(data.data === 1){
				$.alert({
					type:4,
					text:"提交成功",
					callback:function(){
						c2c.pics = [];
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
});
