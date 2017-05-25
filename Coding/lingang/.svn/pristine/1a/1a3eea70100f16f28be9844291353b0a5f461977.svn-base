'use strict';

define([
		'app',
		'route-config'
	],

	function (app, routeConfig) {
	    return app.config(['$routeProvider', function ($routeProvider) {
	    	
	    	$routeProvider.when("/register",routeConfig.config('views/register.html',
                    'controllers/RegisterController',{
                        directives: [],
                        services: ["services/webService"],
                        filters: [],
                        parameters:{title:'注册'}
                    }));
           $routeProvider.when("/index",routeConfig.config('views/index.html',
                    'controllers/IndexController',{
                        directives: ["directives/common/onFinishRenderFilters.js","directives/common/datePicker.js"],
                        services: ["services/webService"],
                        filters: [],
                        parameters:{title:'首页'}
                    }));
            $routeProvider.when("/book/:childModule/:value", routeConfig.config('views/book/mainBook.html',
            		'controllers/book/MainBookCtrl', {
		            	directives: [],
		                services: ["services/book/bookService"],
		                filters: [],
		                parameters:{title:'书舍'}
            }));
            $routeProvider.when("/bookPay/paySuccess", routeConfig.config('views/book/paySuccess.html',
            		'controllers/book/PaySuccessController', {
            	directives: [],
            	services: ["services/webService"],
            	filters: [],
            	parameters:{title:'借阅卡支付成功页面'}
            }));
            $routeProvider.when("/information/:childModule/:value", routeConfig.config('views/information/mainInformation.html',
            		'controllers/information/MainInformationCtrl', {
		            	directives: [],
		                services: ["directives/common/onFinishRenderFilters.js","services/information/informationService"],
		                filters: [],
		                parameters:{title:'视野'}
            }));
            /**********************************活动开始********************************************/
            $routeProvider.when("/act/index", routeConfig.config('views/act/actIndex.html',
            		'controllers/act/ActIndexController', {
            	directives: [],
            	services: ["services/webService"],
            	filters: [],
            	parameters:{title:'活动'}
            }));
            $routeProvider.when("/act/seriesIndex/:id", routeConfig.config('views/act/seriesIndex.html',
            		'controllers/act/SeriesIndexController', {
            	directives: ["directives/common/datePicker.js"],
            	services: ["services/webService"],
            	filters: ["filters/bmstatusFilter.js"],
            	parameters:{title:'活动'}
            }));
            $routeProvider.when("/act/actDetail/:id", routeConfig.config('views/act/actDetail.html',
            		'controllers/act/ActDetailController', {
            	directives: ["directives/common/datePicker.js"],
            	services: ["services/webService"],
            	filters: ["filters/bmstatusFilter.js"],
            	parameters:{title:'活动'}
            }));
            $routeProvider.when("/act/addActOrder/:id", routeConfig.config('views/act/addActOrder.html',
            		'controllers/act/AddActOrderController', {
            	directives: ["directives/common/datePicker.js"],
            	services: ["services/webService"],
            	filters: ["filters/bmstatusFilter.js","filters/cardnoFilter.js"],
            	parameters:{title:'活动'}
            }));
            $routeProvider.when("/act/paySuccess", routeConfig.config('views/act/paySuccess.html',
            		'controllers/act/PaySuccessController', {
            	directives: [],
            	services: ["services/webService"],
            	filters: [],
            	parameters:{title:'活动'}
            }));
            $routeProvider.when("/act/actList", routeConfig.config('views/act/actList.html',
            		'controllers/act/ActListController', {
            	directives: [],
            	services: ["services/webService"],
            	filters: ["filters/bmstatusFilter.js"],
            	parameters:{title:'活动'}
            }));
            /**********************************活动结束********************************************/
            $routeProvider.when("/feedBack/:childModule/:value", routeConfig.config('views/feedBack/mainFeedBack.html',
            		'controllers/feedBack/MainFeedBackCtrl', {
		            	directives: [],
		                services: [],
		                filters: [],
		                parameters:{title:'定制'}
            }));
             /********************************个人中心开始****************************************************/
            $routeProvider.when("/personal/personalMsg", routeConfig.config('views/personal/personalMsg.html',
            		'controllers/personal/PersonalMsgCtrl', {
		            	directives: [],
		                services: [],
		                filters: [],
		                parameters:{title:'个人信息'}
            }));
            $routeProvider.when("/personal/commentContacts", routeConfig.config('views/personal/commentContacts.html',
            		'controllers/personal/CommentContactsCtrl', {
		            	directives: [],
		                services: [],
		                filters: [],
		                parameters:{title:'常用资料--常用联系人'}
            }));
            $routeProvider.when("/personal/myMsgList", routeConfig.config('views/personal/myMsgList.html',
            		'controllers/personal/MyMsgListCtrl', {
            	directives: [],
            	services: [],
            	filters: [],
            	parameters:{title:'消息--我的消息列表'}
            }));
            $routeProvider.when("/personal/getMsgDetail/:id", routeConfig.config('views/personal/myMsgDetail.html',
            		'controllers/personal/MyMsgDetailCtrl', {
            	directives: [],
            	services: [],
            	filters: [],
            	parameters:{title:'消息--我的消息详情'}
            }));
            $routeProvider.when("/personal/myCollect", routeConfig.config('views/personal/myCollect.html',
            		'controllers/personal/MyCollectCtrl', {
            	directives: [],
            	services: [],
            	filters: ["filters/bmstatusFilter.js"],
            	parameters:{title:'消息--我的收藏'}
            }));
            $routeProvider.when("/personal/myBookList", routeConfig.config('views/personal/myBookList.html',
            		'controllers/personal/MyBookListCtrl', {
            	directives: [],
            	services: [],
            	filters: [],
            	parameters:{title:'我的书舍'}
            }));
           /* $routeProvider.when("/personal/backBookDetail/:id", routeConfig.config('views/personal/backBookDetail.html',
            		'controllers/personal/BackBookDetailCtrl', {
            	directives: [],
            	services: [],
            	filters: [],
            	parameters:{title:'我的书舍--快递还书'}
            }));*/
            $routeProvider.when("/personal/myOrders", routeConfig.config('views/personal/myOrders.html',
            		'controllers/personal/MyOrdersController', {
            	directives: [],
            	services: ["services/webService"],
            	filters: [],
            	parameters:{title:'我的订单'}
            }));
            $routeProvider.when("/personal/myCard", routeConfig.config('views/personal/myCard.html',
            		'controllers/personal/MyCardController', {
            	directives: [],
            	services: ["services/webService"],
            	filters: ['filters/ieTypeFilter.js'],
            	parameters:{title:'我的钱包'}
            }));
            $routeProvider.when("/personal/myGrow", routeConfig.config('views/personal/myGrow.html',
            		'controllers/personal/MyGrowController', {
            	directives: [],
            	services: ["services/webService"],
            	filters: [],
            	parameters:{title:'我的成长'}
            }));
            $routeProvider.when("/footer/getCodeDetail/:id", routeConfig.config('views/footer/footer.html',
            		'controllers/footer/FooterCtrl', {
            	directives: [],
            	services: [],
            	filters: [],
            	parameters:{title:'底部菜单'}
            }));
            $routeProvider.otherwise({redirectTo:function(route,path,search){
                return "/index";
            }});
		}]);
});
