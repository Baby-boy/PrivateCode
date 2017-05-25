//requireJS
define([
		'library/registers/lazy-directives',
		'library/registers/lazy-services',
		'library/registers/lazy-filters'
	],
	function (lazyDirectives, lazyServices, lazyFilters) {
		var $controllerProvider;
        function setControllerProvider(value) {
			$controllerProvider = value;
		}
        function regController(value) {
            if(value){
                if (!$controllerProvider) {
                    throw new Error("$controllerProvider is not set!");
                }
                $controllerProvider.register(value[0],value[1]);
            }
        }
		function setCompileProvider(value) {
			lazyDirectives.setCompileProvider(value);
		}
        function regCompileProvider(value) {
            lazyDirectives.register(value);
        }
		function setProvide(value) {
			lazyServices.setProvide(value);
		}
        function regProvide(value) {
            lazyServices.register(value);
        }
		function setFilterProvider(value) {
			lazyFilters.setFilterProvider(value);
		}
        function regFilterProvider(value) {
            lazyFilters.register(value);
        }
		function config(templatePath, controllerPath, lazyResources) {
			if (!$controllerProvider) {
				throw new Error("$controllerProvider is not set!");
			}
			var defer,html="",routeDefinition = {};
			routeDefinition.templateUrl	= templatePath.replace("../", "");
			routeDefinition.controller		= controllerPath.substring(controllerPath.lastIndexOf("/") + 1);
            routeDefinition.parameters		= lazyResources.parameters;
			routeDefinition.resolve		= {
			    deps: function ($q, $rootScope) {
			        defer = $q.defer();
			        if (!html) {
			            var dependencies = ["text!" + templatePath, controllerPath];
			            if (lazyResources) {
			                dependencies = dependencies.concat(lazyResources.directives);
			                dependencies = dependencies.concat(lazyResources.services);
			                dependencies = dependencies.concat(lazyResources.filters);
			            }
			            require(dependencies, function () {
			                var indicator = 0;
			                var template = arguments[indicator++];
			                if (angular.isDefined(controllerPath)) {
			                    $controllerProvider.register(controllerPath.substring(controllerPath.lastIndexOf("/") + 1), arguments[indicator]);
			                    indicator++;
			                }
			                if (angular.isDefined(lazyResources)) {
			                    if (angular.isDefined(lazyResources.directives)) {
			                        for (var i = 0; i < lazyResources.directives.length; i++) {
			                            lazyDirectives.register(arguments[indicator]);
			                            indicator++;
			                        }
			                    }
			                    //服务(value)
			                    if (angular.isDefined(lazyResources.services)) {
			                        for (var i = 0; i < lazyResources.services.length; i++) {
			                            lazyServices.register(arguments[indicator]);
			                            indicator++;
			                        }
			                    }
			                    //过滤器
			                    if (angular.isDefined(lazyResources.filters)) {
			                        for (var i = 0; i < lazyResources.filters.length; i++) {
			                            lazyFilters.register(arguments[indicator]);
			                            indicator++;
			                        }
			                    }
			                }
			                html = template;
			                defer.resolve();
			                $rootScope.$apply();
			            })
			        }
			        else {
			            defer.resolve();
			        }
			        return defer.promise;
			    }
			};
			return routeDefinition;
		}
		return {
			setControllerProvider: setControllerProvider,
            regController:regController,
			setCompileProvider: setCompileProvider,
            regCompileProvider:regCompileProvider,
			setProvide: setProvide,
            regProvide:regProvide,
			setFilterProvider: setFilterProvider,
            regFilterProvider:regFilterProvider,
			config: config
		};
	}
);

