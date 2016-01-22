var atplQuizzAppModule = angular.module('atplQuizzApp', ['ngRoute', 'ngResource']);

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

