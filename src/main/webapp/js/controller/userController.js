var atplQuizzAppControllers = angular.module('atplQuizzAppControllers', []);

atplQuizzAppControllers.controller('userLoginController', ['$scope', 'UserService', function($scope, UserService) {
	  UserService.query(function(users){
		  $scope.users = users;
	  });
}]);

atplQuizzAppControllers.controller('registrationController', function ($scope) {
  
});