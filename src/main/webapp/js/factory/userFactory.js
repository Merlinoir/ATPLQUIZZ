atplQuizzAppModule.factory("User", ["$resource", function ($resource) {
	return $resource(
	        "/user', {}, {
			query: {method:'GET', isArray:true},
			save: {method:'POST'}
		});
	});