var config={};
config.paths={
    'text': 'library/require/text-2.0.12',
    'jquery': 'library/jquery/jquery-2.1.1/jquery.min',
    'jCookie' : 'library/jquery/cookie/jquery.cookie',
    'angular': 'library/angular/angular-1.3.9/angular.min',
    'angularRoute': 'library/angular/angular-1.3.9/angular-route.min',
    'appConfig':'configuration/appSetting',
    'route-config':'js/route-config',
    'app':'app/index/app',
    'routes':'app/index/routes',
    'bootstrap':'library/bootstrap/bootstrap-3.3.5/js/bootstrap.min',
    'serviceBase':'services/serviceBase',
    'mySession':'common/my_session',
    'ngSanitize': 'library/angular/angular-1.3.9/angular-sanitize.min',
    'summernote':'js/plugins/summernote/summernote.min',
    'summernotezhcn':'js/plugins/summernote/summernote-zh-CN',
    'ajaxFileUpload':'library/bootstrap/bootstrap-fileinput-master/js/fileinput',
    'uEditor':'library/umeditor/umeditor',
	'uEditorConfig':'library/umeditor/umeditor.config',
	'uEditorCulture':'library/umeditor/lang/zh-cn/zh-cn',
	'metisMenu':'js/plugins/metisMenu/jquery.metisMenu',
	'slimscroll':'js/plugins/slimscroll/jquery.slimscroll.min',
	'pace':'js/plugins/pace/pace.min',
	'layer':'library/plugins/layer/layer.min',
	'ajaxUpload':'library/ajaxfileupload',
	'tips':'static/js/jquery.tips',
	'My97DatePicker':'js/My97DatePicker/WdatePicker'
};
config.shim={
    'angular':{
        deps:['jquery'],
        exports:'angular'
    },
    'app':{
        deps:['angular']
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
    'ajaxFileUpload':{
    	deps:['jquery']
    },
    'uEditor':{
		deps:['uEditorConfig']
	},	
	'uEditorCulture':{
		deps:['uEditor']
	},
	'mySession' : {
		deps:["jCookie"]
	},
	'metisMenu': {
		deps:['jquery']
	},
	'slimscroll':{
		deps:['jquery']
	},
	'ajaxUpload':{
		deps:['jquery']
	},
	'tips':{
		deps:['jquery']
	},
	'My97DatePicker':{
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
        'bootstrap', 'metisMenu', 'slimscroll', 'pace', 'tips','My97DatePicker'
    ],
    function (text, $, angular) {
        $(document).ready(function () {
            angular.bootstrap(document, ['BaseApp']);
        });
    }
);
