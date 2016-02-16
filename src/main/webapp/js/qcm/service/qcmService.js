(function() {
    'use strict';
    angular.module('AtplQuizz.globalService', []).
    factory('QuestionnaireByThemeService', ['$resource', function ($resource) {
    	return $resource(
    	        '/qcm/getQuestionnaire', {}, {
    			query: {method:'GET', isArray: true}
    		});
    	}]);
})();
