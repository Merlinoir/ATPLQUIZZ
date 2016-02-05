(function() {
    'use strict';
    angular.module("AtplQuizzApp").
    	controller('registerController', RegisterController);
    
    var injectParams = ["$scope", "UserService", "$location", "usersListData"];
    
    /** Functions code */    
	RegisterController.$inject = injectParams;
	function RegisterController($scope, UserService, $location, usersListData) {

		$scope.submit = onSubmit;
		$scope.users = usersListData;
		
		function onSubmit(user) {
			console.log("dans le onSubmit");
			user.isAdmin = false;
			userExist(user)
			//UserService.save(user);
			
	    	$location.path("/login").replace();;

		};
		
		function userExist(login) {
			
			console.log("users : " + $scope.users);
//			listPseudo.$promise.then(function (response){
//				angular.forEach(response, function(pseudo){
//					console.log("test: " + pseudo);					
//				});
//			});
		}
	}
})();