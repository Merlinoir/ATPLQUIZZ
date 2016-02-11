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
				
		function defaultRoute($stateProvider, QcmByThemeService, ReponseForQuestion) {
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
						reponseResource: 'ReponseForQuestion',
						
						qcm : function(qcmResource, $stateParams){
							var params = {
									idTheme : $stateParams.idtheme
							}
							
							return qcmResource.query(params).$promise;
						},
				
						reponse : function(reponseResource, qcm){
								
							qcm.forEach(function(question){
				    			$scope.listIdQuestion.push(question.id);
				    		});
				    		var param = {
				    				idQuestionForAnswer : $scope.listIdQuestion
				    		};
							
							return reponseResource.query(param).$promise;
					}
				},
				controller : 'qcmController'
				});
				
//			$routeProvider.otherwise('/');
		}
})();
