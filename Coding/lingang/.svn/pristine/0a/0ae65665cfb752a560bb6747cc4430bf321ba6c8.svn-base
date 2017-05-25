define([], function () {

	var $compileProvider;
	var directives = [];//注册过的指令

	function setCompileProvider(value) {
		$compileProvider = value;
	}

	function register(directive) {
		if(directive){
			if (!$compileProvider) {
				throw new Error("$compileProvider is not set!");
			}
			var hasDirective = false;
			directives.forEach(function (o, i) {
				if (o == directive[0]) {
					hasDirective = true;
					return;
				}
			});
			if (!hasDirective) {
				directives.push(directive[0]);
				$compileProvider.directive.apply(null, directive);
			}
		}else{
			$compileProvider.directive.apply = null;
		}

	}

	return {
		setCompileProvider: setCompileProvider,
		register: register
	}
})
