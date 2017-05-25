define([
        'route-config',
        'angularRoute',
        'mySession'
    ],

    function (routeConfig, ngRoute, mySession) {
        var app = angular.module('BaseApp', ['ngRoute', 'ngSanitize'],
            function ($provide, $controllerProvider, $compileProvider, $filterProvider, $httpProvider) {
                routeConfig.setProvide($provide);
                routeConfig.setControllerProvider($controllerProvider);
                routeConfig.setCompileProvider($compileProvider);
                routeConfig.setFilterProvider($filterProvider);
            });
        app.run(function ($rootScope, $location) {
            $rootScope.$on("$routeChangeStart", function (evt, next, current) {
            	var redirectUrl = $location.url();
            	if(redirectUrl){
            		mySession.set("redirectUrl",redirectUrl);
            	}
            	if(!current || !current.scope || !current.scope.$parent){
            		mySession.set("index",1);
    			}
            	if(next.$$route && next.$$route.originalPath.substr(1,9) == "personal" && mySession.getTicket() == null){
            		alert("你未登陆请登录！");
            		mySession.set("index",1);
            		window.location = "login.html";
            	}
//            	console.log(current.scope.$parent);
            
            	if(next.$$route && next.$$route.originalPath.substr(1,5) == "index" ){
            		mySession.set("index",1);
            			if(current && current.scope && current.scope.$parent){
            				current.scope.$parent.index=1;
            			}else{
            				mySession.set("index",1);
            			}
                		
            	
            	}
            	if(next.$$route && next.$$route.originalPath.substr(1,3) == "act" ){
            		mySession.set("index",2);
            		if(current){
            			current.scope.$parent.index=2;
            		}
            		if(next.$$route.originalPath.substr(5,15) == "addActOrder" && mySession.getTicket() == null){
            			alert("你未登陆请登录！");
                		mySession.set("index",1);
                		window.location = "login.html";
            		}
            	}
            	if(next.$$route && next.$$route.originalPath.substr(1,11) == "information" ){
            		mySession.set("index",4);
            		if(current){
            			current.scope.$parent.index=4;
            		}
            	}
            });
            $rootScope.$on("$routeChangeSuccess", function (evt, next, previous) {
            });
        });
        app.controller('BaseController', function ($scope, $http) {
//        	$http.post('home/getComboAll.do', null).success(function(data){
//        		if(data.success){
//        			$scope.com_list=data.dataObj;
//        		}
//            });
//            .error(function(data,header,config,status){
//            	//处理响应失败
//            	alert("error");
//            });
        	$(window).unload(function(){
        		console.log("获取到了页面要关闭的事件了！");
        		mySession.set("index",1);
        		});

        	// 当前路径状态
            $scope.urlStatus = "";
            if(mySession.getTicket()){
            	$scope.user = mySession.getTicket();
            }else{
            	$scope.user = null;
            }
            if(mySession.get("index")!=null){
            	 $scope.index=mySession.get("index");
            }else{
            	 $scope.index=1;
            	 mySession.set("index",1);
            }
            
            $scope.jump=function(index,url){
            	mySession.set("index",index);
            	$scope.index=mySession.get("index");
            	if(url!=null&&url!=""){
            		window.location = url;
            	}
            
            };
            //退出登录
            $scope.logout = function(){
            	mySession.logout();
            	mySession.set("index",1);
            	window.location = "login.html";
            };
            $scope.regionId = 1;
            $scope.pageIndex = 1;
            //活动搜索
            $scope.getActInfos = function(){
            	window.location="#/act/actList?actname="+$scope.actname;
        	};
        	//活动系列
        	$scope.getActSeriess = function(){
        		$http({
            		url:'getActSeriess.do',
            		method:'GET'
            	}).success(function(data,header,config,status){
            		console.log(data);
            		if(data.ok){
            			$scope.actSeries = data.data;
            		}else{
            			console.log(data.message);
            		}
            	}).error(function(data,header,config,status){
            		console.log("系统繁忙，请稍后再试");
            	});
        	};
        	$scope.getActSeriess();
        	function showPreview(event) {
    			$("#featured-preview").show();
    		};
    		function hidePreview(event) {
    			$("#featured-preview").hide();
    		};
    		function updatePreview(index) {
    			$("#featured-preview img").hide().eq( index ).show();
    		};
    		function movePreview(event) {
    			var content_position = $("#featured-content").offset();
    			$("#featured-preview").css("left", event.pageX - content_position.left - 160);
    		};
    		$(document).ready(function() {
    			var content_els		= $("#featured-content a");
    			var overlay_wrap	= $("#featured-overlay");
    			var overlay_els		= $("div", overlay_wrap)
    			overlay_els
    				.css('opacity', 0)
    				.mousemove( movePreview )
    				.mouseenter(function() {
    					updatePreview( overlay_els.index( this ) );
    				})
    				.click(function() {
    					window.location.href = content_els.eq( overlay_els.index( this ) ).attr("href");
    				})
    				.show();
    			overlay_wrap
    				.mouseenter( showPreview )
    				.mouseleave( hidePreview );
    		});
        	
        });
        return app;
    }
);
