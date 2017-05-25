var mainApp = angular.module('mainApp',['ngRoute','routeConfig']);
mainApp.config(['$routeProvider', function($routeProvider,routeConfig) {
	$routeProvider.when('/', 
		routeConfig.config('views/index.html',
			'controllers/IndexController',
			{directives: [],
	        services: [],
	        filters: [],
	        parameters:{title:'首页'}
	})).when('/act/basis',
			routeConfig.config('views/act/basis.html',
			'controllers/BasisController',
			{directives: [],
	        services: [],
	        filters: [],
	        parameters:{title:'活动基础设置'}
	})).when('/act/manage',
			routeConfig.config('views/act/manage.html',
			'',
			{directives: [],
	        services: [],
	        filters: [],
	        parameters:{title:'活动管理'}
	})).when('/act/grow',
			routeConfig.config('views/act/grow.html',
			'',
			{directives: [],
	        services: [],
	        filters: [],
	        parameters:{title:'成长评价'} 
	})).otherwise({
		redirectTo : '/'
	});
}]);
mainApp.controller("MainController", function($scope,$http) {
	/*if(!sessionStorage.getItem('user_id')){
		alert('Please login the system');
		window.location.href = 'login.html';
		return;
	}*/
});
