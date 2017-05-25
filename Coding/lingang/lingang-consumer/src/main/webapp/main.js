
var config={};
config.paths={
    'text': 'library/require/text-2.0.12',
    'jquery': 'library/jquery/jquery-2.1.1/jquery.min',
    'angular': 'library/angular/angular-1.3.9/angular.min',
    'angularRoute': 'library/angular/angular-1.3.9/angular-route.min',
    'appConfig':'configuration/appSetting',
    'route-config':'app/route-config',
    'mySession':'common/my_session',
    'app':'app/index/app',
    'routes':'app/index/routes',
    'bootstrap':'library/bootstrap/bootstrap-3.3.5/js/bootstrap.min',
    'serviceBase':'services/serviceBase',
    'ping++':'library/ping++/pingpp-pc',
    'ngSanitize': 'library/angular/angular-1.3.9/angular-sanitize.min',
    'custom' : 'library/jquery/datepicker/timecon_js/jquery-ui-1.10.4.custom.min',
    'datepicker-zh-CN':'library/jquery/datepicker/timecon_js/jquery.ui.datepicker-zh-CN',
    'timepicker-addon' : 'library/jquery/datepicker/timecon_js/jquery-ui-timepicker-addon',
    'timepicker-zh-CN':'library/jquery/datepicker/timecon_js/jquery-ui-timepicker-zh-CN',
    'ngAnimate' : 'library/angular/angular-1.3.9/angular-animate.min',
	'plugin' : 'pcResource/js/plugin',
	'slider' : 'pcResource/js/slider',
	'slider_options' : 'pcResource/js/slider_options',
	'My97DatePicker':'library/My97DatePicker/WdatePicker',
	'layer':'library/plugins/layer/layer.min',
	'kendo':'library/kendo/kendo.dataviz.min'
};
config.shim={
    'angular':{
        deps:['jquery'],
        exports:'angular'
    },
    'ping++':{
    	deps:['jquery'],
    },
    'app':{
        deps:['jquery','angular']
    },
    'routes':{
        deps:['angular']
    },
    'angularRoute': {
        deps: ['angular']
    },
    'ngSanitize':{
        deps:['angular']
    },
    'bootstrap':{
        deps:['angular']
    },
    'serviceBase':{
        deps:['angular']
    },
    'custom':{
    	deps:["jquery"]
    },
    'datepicker-zh-CN':{
        deps:['custom']
    },
    'timepicker-addon':{
        deps:['datepicker-zh-CN']
    },
    'timepicker-zh-CN':{
        deps:['timepicker-addon']
    },
    'ngAnimate':{
    	deps:['angular']
    },
    'plugin':{
    	deps:['jquery']
    },
    'slider':{
    	deps:['jquery']
    },
    'slider_options':{
    	deps:['jquery']
    },
    'My97DatePicker':{
		deps:['jquery']
	},
	'kendo':{
		deps:['jquery']
	}
};
config.priority=["angular"];
config.waitSeconds = 120;
requirejs.config(config);
requirejs( [
        'text',
        'jquery',
        'angular',
        'ngSanitize',
        'routes',
        'app',
        'ping++',
        'bootstrap',
        'ngAnimate',
        'plugin',
    ],
    function (text, $, angular) {
        $(document).ready(function () {
            angular.bootstrap(document, ['BaseApp']);
        });
    }
);
