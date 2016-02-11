(function() {
    'use strict';
    angular.module("AtplQuizzApp").
    	controller('qcmController', function ($scope, qcm) {
    		
    		$scope.listIdQuestion = [];
    		
    		console.log(qcm);
    		angular.forEach(qcm, function(question){
    			$scope.listIdQuestion.push(question.id);
    		});
    		var param = {
    				idQuestionForAnswer : $scope.listIdQuestion
    		};
    		$scope.lancerQCM = function(){
    		
    			
    		};
    		
    		
    });
})();	