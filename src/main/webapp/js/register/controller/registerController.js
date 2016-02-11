(function() {
	'use strict';
	angular.module("AtplQuizzApp").
	controller('registerController', RegisterController);

	var injectParams = ["$scope", "$state", "UserService", "$location", "usersListData"];

	/** Functions code */    
	RegisterController.$inject = injectParams;
	function RegisterController($scope, $state, UserService, $location, usersListData) {

		$scope.submit = onSubmit;
		$scope.users = usersListData;
		
		var matching = 0;

		function onSubmit(user) {
			console.log("onSubmit from registerController");
			user.isAdmin = false;
			checkUniqueness(user);	
			persistUser(user);
		};

		function checkUniqueness(data) {
			angular.forEach(usersListData, function(user){
				
				if(data.pseudo === user.pseudo){
					alert("User login already exists");
					matching++;
				}
			});
		}
		
		function persistUser(data){
			if(matching === 0){
				UserService.save(data);
				console.log("Registration done...");
				alert("Registration done...");
				$location.path("/login").replace();

			}else{
				console.log("Can't create "+data.pseudo+", the login already exists");
				$state.reload();

			}
		}
	}
})();