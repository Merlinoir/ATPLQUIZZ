(function() {
    'use strict';
    angular.module('AtplQuizz.globalService', []).
    factory('LoginService', ['$resource', function ($resource) {
    	return $resource(
    	        '/users/loginUser', {}, {
    			query: {method:'GET', isArray: false}
    		});
    	}]);
})();
