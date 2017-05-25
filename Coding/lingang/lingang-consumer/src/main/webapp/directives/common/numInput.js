/**
 * 2016年1月9日11:22:13
 */

define([], function() {
	return [ 'numInput', function($parse) {
		return {
			restrict : "EA",
			link : function(scope, element, attrs) {
				 function format(){
					 if(isNaN(scope.model) || scope.model ==""){
					 scope.model ="0.00";
					}else{
					//最多保留两位小数
					 var f = parseFloat(scope.model);
					 var f = Math.round(scope.model*100)/100;
					 var s = f.toString();
					 var rs = s.indexOf('.');
					 if(rs < 0){
					 s = s +".00";
					}else{
					 while(s.length <= rs + 2){s += '0';}
					}
					 scope.model = s;
					}
					}
					format();//初始化
					$(elm).bind("blur",format);//jq方式绑定事件
			}
		}
	} ];
})