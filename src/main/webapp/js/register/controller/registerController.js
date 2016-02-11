(function() {

	'use strict';
	angular.module("AtplQuizzApp").
	controller('registerController', RegisterController);

	var injectParams = ["$scope", "$state", "UserService", "$location", "$timeout", "usersListData"];

	/** Functions code */    
	RegisterController.$inject = injectParams;
	function RegisterController($scope, $state, UserService, $location, $timeout, usersListData) {

		$scope.submit = onSubmit;
		$scope.users = usersListData;
		
		var matching;

		function onSubmit(user) {
			console.log("onSubmit from registerController");
			document.getElementById("warningMessage").style.display = "none";
			user.isAdmin = false;
			matching = 0;
			checkUniqueness(user);	
			persistUser(user);
		};

		function replaceURL(){
			$location.path("/login").replace();
		};
		
		function checkUniqueness(data) {
			angular.forEach(usersListData, function(user){
				if(data.pseudo === user.pseudo){
					matching++;
				}
			});
		}
		
		function persistUser(data){
			if(matching === 0){
				UserService.save(data);
				console.log("Registration done...");
				document.getElementById("successMessage").style.display = "block";
				$timeout(replaceURL, 3000);

			}else{
				console.log("Can't create "+data.pseudo+", the login already exists");
				document.getElementById("warningMessage").style.display = "block";
				data.pseudo = '';
				data.password = '';
			}
		}
	}
})();