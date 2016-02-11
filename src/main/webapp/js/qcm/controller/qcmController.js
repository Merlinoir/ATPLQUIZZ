(function() {
    'use strict';
    angular.module("AtplQuizzApp").
    	controller('qcmController', function ($scope, qcm) {
    		$scope.qcm = qcm;
    		console.log(qcm);
    		$scope.lancerQCM = function(){
    			
    		};
    });
})();	