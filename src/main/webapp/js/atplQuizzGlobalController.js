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
    controller('first', function ($rootScope) {
    	$rootScope.correctLogin = false;
    }).
		config(defaultRoute).
		run(function($rootScope, $state, $stateParams) {
			$rootScope.$state = $state;
			$rootScope.$stateParams = $stateParams;
		});
		
		defaultRoute.$inject = ['$stateProvider'];
				
		function defaultRoute($stateProvider, QcmByThemeService) {
			$stateProvider.
				state('login', {
					url:'/login',
					templateUrl : 'js/login/view/login.html',
					controller : 'loginController'
				}).
				state('register', {
					url:'/register',
					templateUrl : 'js/register/view/register.html',
					controller : 'registerController'
				}).
				state('notes', {
					url:'/notes',
					templateUrl : 'js/note/view/mesNotes.html',
					controller : 'noteController'
				}).
				state('user', {
					url:'/user',
					templateUrl : 'js/user/view/user.html',
					controller : 'userController'

				}).
				state('themes', {
					url:'/themes',
					templateUrl : 'js/theme/view/themes.html',
					controller : 'themeController'
				}).
				state('qcm', {
					url:'/qcm/:idtheme',
					templateUrl : 'js/qcm/view/qcm.html',
					resolve : {
						qcmResource : 'QcmByThemeService',
						
						qcm : function(qcmResource, $stateParams){
							var params = {
									idTheme : $stateParams.idtheme
							}
							
							return qcmResource.query(params).$promise;
						}
					},
				controller : 'qcmController'
				});
				
//			$routeProvider.otherwise('/');
		}
})();
