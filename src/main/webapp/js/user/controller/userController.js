(function() {

	'use strict';
	angular.module("AtplQuizzApp").
	controller('userController', userController);

	var injectParams = ["$rootScope", "$scope", "$timeout", "$location", "UserService"];

	/** Functions code */    
	userController.$inject = injectParams;
	function userController($rootScope, $scope, $timeout, $location, UserService) {

		$scope.correctLogin = $rootScope.correctLogin;
		$scope.user = $rootScope.user;
		$scope.deleteUser = function() {
			console.log("user deletion...");
			UserService.delete({"user_id": $scope.user.id});
			document.getElementById("successMessage").style.display = "block";
			$timeout(replaceURL, 3000);
		};
		
		function replaceURL(){
			$location.path("/login").replace();
		};
	}
})();

