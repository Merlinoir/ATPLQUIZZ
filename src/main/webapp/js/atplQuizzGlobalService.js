(function() {
    'use strict';
    angular.module('AtplQuizz.globalService', []).
    factory('GlobalService', ['$resource', function ($resource) {
    	return $resource(
    	        '/users', {}, {
    			query: {method:'GET', isArray:true},
    			save: {method:'POST'}
    		});
    	}]);
})();

