(function() {
    'use strict';
    angular.module("AtplQuizzApp").
    	controller('qcmController', function ($scope, qcm) {
    		$scope.qcm = qcm;
    		console.log(qcm);
    		var map = new Map();
    		$scope.checkBoxSelected = function(index, reponse){
    			
    			map.set(index,reponse.veracite);
    			console.log(map);
    			
    		};
    		
    		$scope.validate= function() {
////			angular.forEach(map, function()){
////				
////			});
	};
    		
    		
    });
})();	