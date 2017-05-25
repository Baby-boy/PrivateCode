define(['mySession', 'route-config', 'serviceBase'], function (mySession, routeConfig, serviceBase) {
    var _controller = function($scope, $http, $routeParams, $location,trsService){
    	
//    	$scope.js_method=function (){
//    		$scope.postMethod('http://10.40.201.201:8124/wcm/app/login_dowith.jsp', {UserName :'admin',PassWord:'trsadmin'}); 
//		};
    	
    	$scope.jsMethod=function(){
    		trsService.trsPost($http,null).then(function(data) {
    			if(data.stateCode=="1000"){
    				$scope.postMethod(data.dataMap.url, {UserName :data.dataMap.userName,PassWord:data.dataMap.passWord}); 
    			}else{
    				alert("请求失败，请稍后再试");
    			}
    		});
    	};
		
		$scope.postMethod=function(URL, PARAMS) {      
		    var temp = document.createElement("form");        
		    temp.action = URL;        
		    temp.target="_blank"
		    temp.method = "post";        
		    temp.style.display = "none";        
		    for (var x in PARAMS) {        
		        var opt = document.createElement("textarea");        
		        opt.name = x;        
		        opt.value = PARAMS[x];        
		        // alert(opt.name)        
		        temp.appendChild(opt);        
		    }        
		    document.body.appendChild(temp);        
		    temp.submit();        
		    return temp;        
		}        
    	
    	
		$scope.jsMethod();
    };
    return ["wcmCtrl", _controller];
});