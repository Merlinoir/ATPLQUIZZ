//var atplQuizzAppModule = angular.module('atplQuizzApp', ['ngRoute', 'ngResource']);
(function() {
    'use strict';
    
    angular.module('AtplQuizzApp', [
        'AtplQuizz.main',
        'AtplQuizz.globalController',
        'AtplQuizz.globalService',
        'ui.router'
    ]).controller('first', function ($rootScope) {
    	$rootScope.correctLogin = false;
    });
})();
    