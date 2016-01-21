var atplQuizzAppServices = angular.module('atplQuizzAppServices', ['ngResource']);

atplQuizzAppServices.factory('UserService', ['$resource', function ($resource) {
	return $resource(
	        '/users', {}, {
			query: {method:'GET', isArray:false},
			save: {method:'POST'}
		});
	}]);