var atplQuizzAppModule = angular.module('atplQuizzApp', ['ngRoute']);

atplQuizzAppModule.config(['$routeProvider', function($routeProvider) {
	$routeProvider.when('/', {
		templateUrl : 'view/login.html',
		controller : 'userLoginController'
	}).when('/register', {
		templateUrl : 'view/signin.html',
		controller : 'registrationController'
	}).otherwise({
        redirectTo: '/'
    });
}]);

atplQuizzAppModule.controller('userLoginController', function ($scope) {
	  
});