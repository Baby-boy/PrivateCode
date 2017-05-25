/**
 *延迟绑定
 */
define([], function() {
	return [ 'onFinishRenderFilters', function($timeout) {
		return {
			 restrict: 'A',
	        link: function (scope, element, attr) {
	            if (scope.$last === true) {
	                $timeout(function () {
	                    scope.$emit('ngRepeatFinished');
	                });
	            }
	        }
		}
	} ];
});