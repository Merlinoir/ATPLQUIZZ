app.controller('UserListController', function($scope, $http) {
	$scope.users = [];

	var response = $http.get("/users");

	response.success(function(data, status, headers, config) {
		$scope.users = data;
	});

	response.error(function(data, status, headers, config) {
		console.log("Error: ");
		console.dir(data);
	});
})

.controller('UserController', function($scope, $http, $routeParams) {
	$scope.user = {};

	var response = $http.get("/users/" + parseInt ($routeParams.id, 10));

	response.success(function(data, status, headers, config) {
		$scope.user = data;
	});

	response.error(function(data, status, headers, config) {
		console.log("Error: ");
		console.dir(data);
	});
})

.controller('UserFormController', function($scope, $http, $location) {
	$scope.submit = function() {
		var response = $http.put ('/users/create', {
			'pseudo': $scope.pseudo,
			'password' : $scope.password,
			'isAdmin' : false
		});

		response.success(function(data, status, headers, config) {
			$location.path ('/');
		});

		response.error(function(data, status, headers, config) {
			console.log("Error: ");
			console.dir(data);
		});
	};
})

.controller('UserDeletionController', function ($scope, $http, $routeParams, $location) {
	var response = $http.delete("/users/" + parseInt ($routeParams.id, 10));

	response.success(function(data, status, headers, config) {
		$location.path ('/');
	});

	response.error(function(data, status, headers, config) {
		console.log("Error: ");
		console.dir(data);
	});
});
