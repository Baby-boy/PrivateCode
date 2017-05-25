/**
 * datePicker 输入格式化
 * ng-model 双向绑定
 */
define(["My97DatePicker"], function() {
	return [ 'datePicker', function($filter) {
		var dateFilter = $filter('date');
		return {
			restrict : 'A',
			require : 'ngModel',
			scope : {
				minDate : '@',
			},
			link : function(scope, element, attr, ngModel) {
				function onpicking(dp) {
					var date = dp.cal.getNewDateStr();
					scope.$apply(function() {
						ngModel.$setViewValue(date);
					});
				}
				function onpicked(dp) {
					/*$(dp.el).validationEngine('validate');*/
					$(dp.el).trigger("change");
				}
				function oncleared() {
					scope.$apply(function() {
						ngModel.$setViewValue("");
					});
				}
				function formatter(value) {  
	                return dateFilter(value, attr.datefmt || 'yyyy-MM-dd'); //format  
	            }
	            ngModel.$formatters.push(formatter);
	            element.val(ngModel.$viewValue);
				element.bind('click', function() {
					WdatePicker({
						onpicking : onpicking,
						oncleared : oncleared,
						onpicked : onpicked,
						dateFmt : (attr.datefmt || 'yyyy-MM-dd'),
						minDate : (attr.mindate || '%y-%M-%d')
					})
				});
			}
		}
	} ];
});