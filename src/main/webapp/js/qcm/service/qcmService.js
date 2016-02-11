(function() {
    'use strict';
    angular.module('AtplQuizz.globalService', []).
    factory('QcmByThemeService', ['$resource', function ($resource) {
    	return $resource(
    	        '/question/pickQuestionByThemeForQCM', {}, {
    			query: {method:'GET', isArray: true}
    		});
    	}]).
    	factory('ReponseForQuestion', ['$resource', function ($resource) {
        	return $resource(
        	        '/reponse/pickReponseForQCM', {}, {
        			query: {method:'GET', isArray: true}
        		});
        }]);
})();
