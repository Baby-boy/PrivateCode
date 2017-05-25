'use strict';

define([
        'app','route-config'
        ],

	function (app, routeConfig) {
	    return app.config(['$routeProvider', function ($routeProvider) {
	    /***首页**/
	    	/***********************************************************************/
            $routeProvider.when("/index",routeConfig.config('views/index.html',
                'controllers/IndexController',{
                    directives: [],
                    services: [],
                    filters: [],
                    parameters:{title:'首页'}
                }));
            
	    /***基本设置**/
	    	/*************************基本设置_基础类型******************************/
	    	$routeProvider.when("/basics/sysBasics/:childModule/:value", routeConfig.config('views/basics/sysBasics/main.html',
            		'controllers/basics/sysBasics/mainSysBasicsCtrl', {
		            	directives: ["directives/common/ndtech.pagination.js"],
		                services: ["services/sysBasicsService"],
		                filters: [],
		                parameters:{title:'基本设置_基础类型'}
            }));
	    	
	    	/*************************基本设置_合作类型******************************/
	    	$routeProvider.when("/basics/sysPartnerType/:childModule/:value", routeConfig.config('views/basics/sysPartnerType/main.html',
	    			'controllers/basics/sysPartnerType/mainSysPartnerTypeCtrl', {
	    		directives: ["directives/common/ndtech.pagination.js"],
	    		services: ["services/sysPartnerTypeService"],
	    		filters: [],
	    		parameters:{title:'基本设置_合作类型'}
	    	}));
	    	
	    	/*************************基本设置_标签管理******************************/
            $routeProvider.when("/basics/sysLabel/:childModule/:value", routeConfig.config('views/basics/sysLabel/main.html',
            		'controllers/basics/sysLabel/mainSysLabelCtrl', {
		            	directives: ["directives/common/ndtech.pagination.js"],
		                services: ["services/sysLabelService"],
		                filters: [],
		                parameters:{title:'基本设置_标签管理'}
            }));
            
            /*************************基本设置_区域管理******************************/
            $routeProvider.when("/basics/sysRegion/:childModule/:value", routeConfig.config('views/basics/sysRegion/main.html',
            		'controllers/basics/sysRegion/mainSysRegionCtrl', {
		            	directives: ["directives/common/ndtech.pagination.js"],
		                services: ["services/sysRegionService"],
		                filters: [],
		                parameters:{title:'基本设置_区域管理'}
            }));
            
            /*************************基本设置_公司信息*****************************/
            $routeProvider.when("/basics/sysCompany/:childModule/:value", routeConfig.config('views/basics/sysCompany/main.html',
            		'controllers/basics/sysCompany/mainSysCompanyCtrl', {
		            	directives: ["directives/common/ndtech.pagination.js"],
		                services: ["services/sysCompanyService"],
		                filters: [],
		                parameters:{title:'基本设置_公司信息'}
            }));
            
        /***首页置顶**/
            /*************************首页置顶_新闻列表******************************/
            $routeProvider.when("/home/sysNews/:childModule/:value", routeConfig.config('views/home/sysNews/main.html',
            		'controllers/home/sysNews/mainSysNewsCtrl', {
            			directives: ["directives/common/ndtech.pagination.js", "directives/common/fileInput.js"],
		                services: ["services/sysNewsService"],
		                filters: [],
		                parameters:{title:'首页置顶_新闻列表'}
            }));
            
            /*************************首页置顶_最新合作******************************/
            $routeProvider.when("/home/topPartner/:childModule/:value", routeConfig.config('views/home/topPartner/main.html',
            		'controllers/home/topPartner/mainTopPartnerCtrl', {
            			directives: ["directives/common/ndtech.pagination.js","directives/common/fileInput.js"],
		                services: ["services/topPartnerService"],
		                filters: [],
		                parameters:{title:'首页置顶_最新合作'}
            }));
            
            /*************************首页置顶_最新入驻******************************/
            $routeProvider.when("/home/topStation/:childModule/:value", routeConfig.config('views/home/topStation/main.html',
            		'controllers/home/topStation/mainTopStationCtrl', {
            			directives: ["directives/common/ndtech.pagination.js","directives/common/fileInput.js"],
		                services: ["services/topStationService"],
		                filters: [],
		                parameters:{title:'首页置顶_最新入驻'}
            }));
            
            /*************************首页置顶_政策列表******************************/
            $routeProvider.when("/home/sysPolicy/:childModule/:value", routeConfig.config('views/home/sysPolicy/main.html',
            		'controllers/home/sysPolicy/mainSysPolicyCtrl', {
            			directives: ["directives/common/ndtech.pagination.js","directives/common/fileInput.js"],
		                services: ["services/sysPolicyService"],
		                filters: [],
		                parameters:{title:'首页置顶_政策列表'}
            }));
            
        /***查询设置**/
            /*************************查询设置_产业园区******************************/
            $routeProvider.when("/seek/sysPark/:childModule/:value", routeConfig.config('views/seek/sysPark/main.html',
            		'controllers/seek/sysPark/mainSysParkCtrl', {
		            	directives: ["directives/common/ndtech.pagination.js","directives/common/fileInput.js"],
		                services: ["services/sysParkService"],
		                filters: [],
		                parameters:{title:'查询设置_产业园区'}
            }));
            
            /*************************查询设置_合作伙伴******************************/
            $routeProvider.when("/seek/sysPartner/:childModule/:value", routeConfig.config('views/seek/sysPartner/main.html',
            		'controllers/seek/sysPartner/mainSysPartnerCtrl', {
            	directives: ["directives/common/ndtech.pagination.js", "directives/common/fileInput.js","directives/common/datePicker.js"],
            	services: ["services/sysPartnerService"],
            	filters: [],
            	parameters:{title:'查询设置_合作伙伴'}
            }));
            
            /*************************查询设置_产业集群******************************/
            $routeProvider.when("/seek/sysIndustry/:childModule/:value", routeConfig.config('views/seek/sysIndustry/main.html',
            		'controllers/seek/sysIndustry/mainSysIndustryCtrl', {
            	directives: ["directives/common/ndtech.pagination.js","directives/common/fileInput.js"],
            	services: ["services/sysIndustryService"],
            	filters: [],
            	parameters:{title:'查询设置_产业集群'}
            }));
            
            /*************************查询设置_服务机构******************************/
            $routeProvider.when("/seek/sysService/:childModule/:value", routeConfig.config('views/seek/sysService/main.html',
            		'controllers/seek/sysService/mainSysServiceCtrl', {
            	directives: ["directives/common/ndtech.pagination.js","directives/common/fileInput.js"],
            	services: ["services/sysServiceService"],
            	filters: [],
            	parameters:{title:'查询设置_服务机构'}
            }));
            
            /*************************查询设置_入驻企业******************************/
            $routeProvider.when("/seek/sysStation/:childModule/:value", routeConfig.config('views/seek/sysStation/main.html',
            		'controllers/seek/sysStation/mainSysStationCtrl', {
            	directives: ["directives/common/ndtech.pagination.js","directives/common/fileInput.js"],
            	services: ["services/sysStationService"],
            	filters: [],
            	parameters:{title:'查询设置_入驻企业'}
            }));
            
            /*************************查询设置_公共平台******************************/
            $routeProvider.when("/seek/sysPublic/:childModule/:value", routeConfig.config('views/seek/sysPublic/main.html',
            		'controllers/seek/sysPublic/mainSysPublicCtrl', {
            	directives: ["directives/common/ndtech.pagination.js","directives/common/fileInput.js"],
            	services: ["services/sysPublicService"],
            	filters: [],
            	parameters:{title:'查询设置_公共平台'}
            }));
            
        /***app设置**/
            /*************************app设置_APP账户******************************/
            $routeProvider.when("/app/appManager/:childModule/:value", routeConfig.config('views/app/appManager/main.html',
            		'controllers/app/appManager/mainSysUserCtrl', {
		            	directives: ["directives/common/ndtech.pagination.js"],
		                services: ["services/sysUserService"],
		                filters: [],
		                parameters:{title:'app设置_APP账户'}
            }));
	    	
        /***客服管理**/
            /*************************客服管理_信息纠错******************************/
            $routeProvider.when("/message/messageError/:childModule/:value", routeConfig.config('views/message/messageError/main.html',
            		'controllers/message/messageError/mainSysMessageCtrl', {
		            	directives: ["directives/common/ndtech.pagination.js"],
		                services: ["services/sysMessageService"],
		                filters: [],
		                parameters:{title:'客服管理_信息纠错'}
            }));
            
            /*************************客服管理_意见反馈******************************/
            $routeProvider.when("/message/feedBack/:childModule/:value", routeConfig.config('views/message/feedBack/main.html',
            		'controllers/message/feedBack/mainSysMessageOpinionCtrl', {
		            	directives: ["directives/common/ndtech.pagination.js"],
		                services: ["services/sysMessageService"],
		                filters: [],
		                parameters:{title:'客服管理_意见反馈'}
            }));
            
        /***系统设置**/
            /*************************系统设置_管理员账户/操作日志*****************************/
            $routeProvider.when("/system/sysManager/:childModule/:value", routeConfig.config('views/system/sysManager/main.html',
            		'controllers/system/sysManager/mainSysManagerCtrl', {
		            	directives: ["directives/common/ndtech.pagination.js","directives/common/datePicker.js"],
		                services: ["services/sysManagerService"],
		                filters: [],
		                parameters:{title:'系统设置_管理员账户'}
            }));
            
            /***站点发布**/
            /*************************站点发布系统*****************************/
            $routeProvider.when("/site/trs/:childModule/:value", routeConfig.config('views/site/trs/main.html',
            		'controllers/site/trs/mainTrsCtrl', {
		            	directives: [],
		                services: ["services/trsService"],
		                filters: [],
		                parameters:{title:'系统设置_管理员账户'}
            }));
            
            $routeProvider.otherwise({redirectTo:function(route,path,search){
                return "/index";
            }});
		}]);
});
