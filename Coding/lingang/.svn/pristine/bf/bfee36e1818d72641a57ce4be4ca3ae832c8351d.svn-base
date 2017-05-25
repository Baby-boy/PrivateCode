
define(['appConfig','mySession','slider','slider_options'], function (appConfig,mySession) {
    var _controller = function($scope, $http, webService,$location){
    	mySession.set("index",1);
    	$scope.img_url=appConfig.imgServerUrl;
    	$scope.o_check=0;
    	//轮播图
    	   webService.getIndexCarousel($http).then(function(data){
         	   if(data.ok){
         		   console.log(data);
         		  $scope.resources=data.data;
         	   }
       	   });
    	  
    	   //活动
    	   $scope.lingangToken =  mySession.getTicket();
    	   webService.getActInfos($http,8,1,null,null,2,1,null,null, $scope.lingangToken,2).then(function(data){
    		   if(data.ok){
    			   console.log(data);
    			   $scope.actInfos=data.data.list;
    			  /* setTimeout(function(){
    				   $().prepare_slider(); 
    			   		$('#slider_list > li').over();
    			   		//Download by http://www.codefans.net
    			   		//=======intro================
    			   		var slider_link = $('#right_but');
    			   		var slider_link_index = 1;
    			   		var slider_count = $('#slider_list > li').size();	

    			   		function slider_intro(){
    			   			if(slider_link_index <= slider_count){
    			   				slider_link.trigger('click');
    			   				slider_link_index++;
    			   				setTimeout(function(){slider_intro()}, 5000); //select change time
    			   			}
    			   		}
    			   		setTimeout(function(){slider_intro()}, 5000)
    			   	  //===============
    			   		
    			   		$('#left_but').hover(
    			   		   function () {
    			   			 $(this).addClass("over");
    			   		   },
    			   		   function () {
    			   			 $(this).removeClass("over");
    			   		   })
    			   		
    			   		$('#right_but').hover(
    			   		   function () {
    			   			 $(this).addClass("over");
    			   		   },
    			   		   function () {
    			   			 $(this).removeClass("over");
    			   		   })
    			   		
    			   		$('#slider_list li').hover(
    			   		   function () {
    			   			 $(this).find('.product-name').stop(true, true).slideDown("slow");
    			   		   },
    			   		   function () {
    			   			 $(this).find('.product-name').hide("slow");
    			   		   })
    			   }, 1000);*/
    		   }
    	   });
    	   //系列
//    	   webService.getActSeriess2($http).then(function(data){
//    		   if(data.ok){
//    			   console.log(data);
//    			   $scope.seriess=data.data;
//    		   }
//    	   });
//    	   webService.getActSeriess($http).then(function(data){
//    		   if(data.ok){
//    			   console.log(data);
//    			   $scope.seriess=data.data;
//    		   }
//    	   });
    	   //资讯
    	   var query={
    			   isTop:1,
    			   onePageCount:4,
    	   };
    	   webService.informationList($http,query).then(function(data){
    		   if(data.ok){
    			   console.log(data);
    			   $scope.informations=data.data.list;
    		   }
    	   });
    	   
    	   /*视野文章详情*/
    	   $scope.getInformationDetail = function(id){
    		    window.location = "information/informationDetail/"+id;  
    	   };
    	   //详情
    	   $scope.actDetail = function(id,actinfo){
//    		   session.setItem("actinfo", stringify(actinfo));
    		   mySession.set("actinfo",actinfo);
//    		   window.location = 'index.html#/act/actDetail/'+id;
    		   $location.path("/act/actDetail/"+id);
    	   };
    	   if(!$scope.age){
    		   $scope.age = 0;
    	   }
//    	   if(!$scope.subjectId){
//    		   $scope.subjectId = 0;
//    	   }
//    	   if(!$scope.serId){
//    		   $scope.serId = 0;
//    	   }
    	  
    	   $scope.changeAge = function(age){
    		   $scope.age = age;
    	   };
//    	   $scope.changeSub = function(sub){
//    		   $scope.subjectId = sub;
//    	   };
//    	   $scope.changeSer = function(sub){
//    		   $scope.serId = sub;
//    	   };
    	   //系列
//    	   webService.getActSubjectBySeries($http,$scope.seriesId,null,null).then(function(data){
//    		   if(data.ok){
//    			   console.log(data);
//    			   $scope.subject=data.data;
//    		   }
//    	   });
//    	   if($scope.seriesId == 1){//生存
//    		   $scope.objectType =8;
//    	   }else if($scope.seriesId == 2){//学识
//    		   $scope.objectType =9;
//    	   }else if($scope.seriesId == 3){//情殇
//    		   $scope.objectType =10;
//    	   }else if($scope.seriesId == 4){//梦想
//    		   $scope.objectType =11;
//    	   }
    	   //搜索活动
    	   $scope.searchActs = function(){
    		   window.location="#/act/actList?actname="+$scope.actName+"&actTime="+$scope.actTime+"&age="+$scope.age;
    	   };
    };
    return  _controller;
});