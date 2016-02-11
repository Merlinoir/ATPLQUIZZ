(function() {
    'use strict';
    angular.module("AtplQuizzApp").
<<<<<<< HEAD
	    controller('loginController', function ($rootScope, $scope, $location, LoginUserService) {
=======
	    controller('loginController', function ($rootScope, $scope, LoginService, $location) {
>>>>>>> 57d384b30102ec7793e05305b27482c186b5cb55
	    	
	    	$scope.login = function(){
	    	var param = {
	    		login : $scope.form.inputPseudo,
	    		password : $scope.form.inputPassword
	    	} 
	    	
	    	$scope.user = LoginUserService.query(param);
	    	$scope.user.$promise.then(function(user){
	    		if(user.id != null){
	    		$rootScope.user = user;
	    		console.log($rootScope.user);
	    		$rootScope.correctLogin = true;
	    		$location.path("/user");
	    		}
	    	}, function(error){
	    		console.log("error");
		    	});
	    	 };
	    });
})();
