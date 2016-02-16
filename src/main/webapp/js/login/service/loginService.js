(function() {
	'use strict';

	angular.module('AtplQuizzApp').factory('LoginService', LoginService);

	var injectParams = [ "$resource" ];

	LoginService.$inject = injectParams;

	function LoginService($resource) {
		return $resource( '/users/loginUser', {}, {
			'query' : {
				method : 'GET',
				isArray : false
			}
		});
	}
})();
