/**
 * 2016年1月9日11:22:13
 */

define([], function() {
	return [ 'fileInput', function($parse) {
		return {
			restrict : "EA",
			template : "<input type='file' accept='image/*' name='myFile' >",
			replace : true,
			link : function(scope, element, attrs) {
				var modelGet = $parse(attrs.fileInput);
				var modelSet = modelGet.assign;
				var onChange = $parse(attrs.onChange);
				var updateModel = function(event) {
					scope.$apply(function() {
						modelSet(scope, event.target.files[0]);
						if(window.URL){
							var url = URL.createObjectURL(event.target.files[0]);
							window.imgUrl = url;
							onChange(scope);
						}else{
							alert("浏览器版本太低，无法预览图片，请更换高级版本浏览器");
						}
					});
				};
				element.bind('change', updateModel);
			}
		}
	} ];
})