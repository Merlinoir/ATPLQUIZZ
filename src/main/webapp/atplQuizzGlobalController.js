var atplQuizzAppModule = angular.module('atplQuizzApp', ['ngRoute', 'ngResource']);

atplQuizzAppModule.config(['$routeProvider', function($routeProvider) {
	$routeProvider.when('/login', {
		templateUrl : 'view/login.html',
		controller : 'userLoginController'
	}).when('/register', {
		templateUrl : 'js/register/view/register.html',
		controller : 'registrationController'
	}).when('/notes', {
		templateUrl : 'js/note/view/mesNotes.html',
		controller : 'noteController'
	}).when('/themes', {
		templateUrl : 'js/theme/view/themes.html',
		controller : 'themeController'
	}).otherwise({
        redirectTo: '/'
    });
}]);

