(function($) {
    $.fn.placeholder = function(options) {
        var opts = $.extend({}, $.fn.placeholder.defaults, options);
        var isIE = document.all ? true : false;
        return this.each(function() {
            var _this = this,
                placeholderValue = _this.getAttribute("placeholder"); //缓存默认的placeholder值
            if (isIE) {
                _this.setAttribute("value", placeholderValue);
                _this.onfocus = function() {
                    $.trim(_this.value) == placeholderValue ? _this.value = "" : '';
                };
                _this.onblur = function() {
                    $.trim(_this.value) == "" ? _this.value = placeholderValue : '';
                };
            }
        });
    };
})(jQuery);

//弹窗交互
function Popups(data,callback){
		var doms='<div class="yd-popup-tishi"><div class="guanbi"></div><p></p><div class="button" style="text-align:center"><button type="button">确认</button></div></div>'
			$(document.body).append(doms)
		if(data){
		    $(".yd-popup-tishi p").html(data);
		}
		$(".yd-popup-tishi .guanbi,.yd-popup-tishi button").on("click",function(){
		    $(".yd-popup-tishi").remove();
		    if(callback){
		    	callback();
		    }
		});
}
function Popupss(data){
		var doms='<div class="yd-popup-tishi"><div class="guanbi"></div><p>恭喜您登录成功！</p><div class="button" style="text-align:center"><button type="button">确认</button><button type="button">取消</button></div></div>'
			$(document.body).append(doms)
		if(data){
		    $(".yd-popup-tishi p").html(data);
		}
		$(".yd-popup-tishi .guanbi,.yd-popup-tishi button").on("click",function(){
			var index=$(this).index();
			if(index==0){
				$(".yd-popup-tishi").remove();
			    return true;
			}else{
				$(".yd-popup-tishi").remove();
			    return false;
			}
		});
}

$(function () {
    $("input[placeholder]").placeholder();
    $("textarea[placeholder]").placeholder();
});





