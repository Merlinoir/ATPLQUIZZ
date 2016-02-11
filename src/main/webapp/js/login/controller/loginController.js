(function() {
    'use strict';
    angular.module("AtplQuizzApp").
	    controller('loginController', function ($rootScope, $scope, LoginService, $location) {
	    	
	    	$scope.login = function(){
	    	var param = {
	    		login : $scope.form.inputPseudo,
	    		password : $scope.form.inputPassword
	    	} 
	    	
	    	$scope.user = LoginService.query(param);
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
