(function() {
	'use strict';
	angular.module("AtplQuizzApp").controller('userLoginController',
			function($scope, LoginService, GlobalService) {
				console.log("toto");
				$scope.test = function() {

					GlobalService.query(function(users) {
						$scope.users = users;
					});
					console.log($scope.users);

					//	    	var login = $scope.inputId;
					//	    	var pwd = $scope.inputPassword;
					var param = {
						login : $scope.form.inputPseudo,
						password : $scope.form.inputPassword
					}
					LoginService.query(param, function(user) {
						$scope.user = user;
						console.log("Test");
					});
				}
			});
})();
