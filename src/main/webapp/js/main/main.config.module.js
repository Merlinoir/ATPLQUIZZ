(function() {
    'use strict';

    angular.module('AtplQuizz.main')
        .config(configInterceptors);

    configInterceptors.$inject = ['$httpProvider'];
    function configInterceptors ($httpProvider) {
	    var interceptor = ['$rootScope', '$q', function($rootScope, $q) {
		      function success(response) {
		          if ( (angular.isFunction(response.data.indexOf)) && (response.data.indexOf('SITEMINDEROBJECT') > 0) ) {
		          	window.location = './index.html';
		          } else return response;
		      }

		      function error(response) {
		        if ( (response.status === 403) ) {
		        	window.location = './index.html#/403';
		        }

		        // otherwise, default behaviour
		        return $q.reject(response);
		      }

		      return function(promise) {
		        return promise.then(success, error);
		      };

		}];
		$httpProvider.interceptors.push(interceptor);
    }
})();