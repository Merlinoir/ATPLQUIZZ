(function() {
    'use strict';
    angular.module("AtplQuizzApp").
    	controller('qcmController', ['$scope', 'qcm','ResponseForQuestion', function ($scope, qcm, ResponseForQuestion) {
    		
    		$scope.listIdQuestion = [];
    		
    		console.log(qcm);
    		angular.forEach(qcm, function(question){
    			$scope.listIdQuestion.push(question.id);
    		});
    		var param = {
    				idQuestionForAnswer : $scope.listIdQuestion
    		};
    		$scope.lancerQCM = function(){
    		
    			ResponseForQuestion.query(param, function(reponses){
        			$scope.allReponses = reponses;
        		});
    			
    		};
    		
    		
    }]);
})();	