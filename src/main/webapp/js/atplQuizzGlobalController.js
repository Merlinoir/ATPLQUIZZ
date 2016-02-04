//var atplQuizzAppModule = angular.module('atplQuizzApp', ['ngRoute', 'ngResource']);
(function() {
    'use strict';
    
    angular.module('AtplQuizz.globalController', []).
//    
//    'ui.router',
//    '$urlRouterProvider', 
//    '$stateProvider',  
//    'ngResource', 
//    '$rootScope', 
//    '$state', 
//    '$stateParams'
    
		config(defaultRoute).
		run(function($rootScope, $state, $stateParams) {
			$rootScope.$state = $state;
			$rootScope.$stateParams = $stateParams;
		});
		
		defaultRoute.$inject = ['$stateProvider'];
				
		function defaultRoute($stateProvider) {
			$stateProvider.
				state('login', {
					url:'/login',
					templateUrl : 'js/login/view/login.html',
					controller : 'userLoginController'
				}).
				state('register', {
					url:'/register',
					templateUrl : 'js/register/view/register.html',
					controller : 'registrationController'
				}).
				state('notes', {
					url:'/notes',
					templateUrl : 'js/note/view/mesNotes.html',
					controller : 'noteController'
				}).
				state('users', {
					url:'/users',
					templateUrl : 'js/user/view/user.html',
					controller : 'userController'
				}).
				state('themes', {
					url:'/themes',
					templateUrl : 'js/theme/view/themes.html',
					controller : 'themeController'
				});
				
//			$routeProvider.otherwise('/');
		}
})();
