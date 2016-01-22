//var atplQuizzAppModule = angular.module('atplQuizzApp', ['ngRoute', 'ngResource']);
(function() {
    'use strict';
    
	angular.module("atplQuizzApp",[]).
		config(['$stateProvider', '$urlRouterProvider','ui.router', function($stateProvider, $urlRouterProvider) {
			$stateProvider.
				state('login', {
					url:'/',
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
				state('themes', {
					url:'/themes',
					templateUrl : 'js/theme/view/themes.html',
					controller : 'themeController'
				});
				
			$urlRouteProvider.otherwise('/');
		}]).
		run(['$rootScope', '$state', '$stateParams'], function($rootScope, $state, $stateParams) {
			$rootScope.$state = $state;
			$rootScope.$stateParams = $stateParams;
		});
})();
