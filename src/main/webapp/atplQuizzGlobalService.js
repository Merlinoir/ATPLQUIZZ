atplQuizzAppModule.factory('GlobalService', ['$resource', function ($resource) {
	return $resource(
	        '/users', {}, {
			query: {method:'GET', isArray:false},
			save: {method:'POST'}
		});
	}]);