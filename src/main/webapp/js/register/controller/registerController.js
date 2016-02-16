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
			user.isAdmin = false;
			matching = 0;
			checkUniqueness(user);	
			persistUser(user);
		};

		function replaceURL(){
			$location.path("/login").replace();
		};
		
		function resetForm(){
		    $scope.userForm = angular.copy($scope.originForm); 
			document.getElementById("warningMessage").style.display = "none";
			document.getElementById("errorMessage").style.display = "none";
			document.getElementById("successMessage").style.display = "none";
		};
		
		function checkUniqueness(data) {
			angular.forEach(usersListData, function(user){
				if(data.pseudo === user.pseudo){
					matching++;
				}
			});
		}
		
		function persistUser(data){
			if(matching == 0 && angular.isDefined(data.pseudo) && angular.isDefined(data.password) ){
				UserService.save(data);
				console.log("Registration done...");
				document.getElementById("successMessage").style.display = "block";
				$timeout(replaceURL, 3000);

			}else{
				if(matching != 0 && angular.isDefined(data.pseudo) && angular.isDefined(data.password) ){
					console.log("Can't create "+data.pseudo+", the login already exists");
					document.getElementById("warningMessage").style.display = "block";
					$timeout(resetForm,3000);
				}else{
					document.getElementById("errorMessage").style.display = "block";
					$timeout(resetForm,3000);
				}
			}
		}
	}
})();