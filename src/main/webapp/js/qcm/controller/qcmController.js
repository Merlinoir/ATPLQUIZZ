(function() {
    'use strict';
    angular.module("AtplQuizzApp").
    	controller('qcmController', function ($scope, qcm) {
    		$scope.qcm = qcm;
    		console.log(qcm);
    		var map = new Map();
    		$scope.checkBoxSelected = function(index, reponse){
    			map.set(index,reponse);
    			console.log(map);
    			
    		};
    		
    		$scope.validate= function() {
//    			var dir = document.getElementById('true');
//    			dir.style.backgroundColor = 'green';
//    			var dir2 = document.getElementById('false');
//    			dir2.style.backgroundColor = 'red';	
			angular.forEach(map, function(reponse ,index){
				var caseCheck = document.getElementById(reponse.id);
				var libelle = document.getElementById(reponse.id+'lib');
				if (reponse.veracite == true){
					caseCheck.style.backgroundColor = 'green';
					libelle.style.backgroundColor = 'green';
				}else{
					caseCheck.style.backgroundColor = 'red';
					libelle.style.backgroundColor = 'red';
					angular.forEach($scope.qcm[index].listReponse, function(findReponse){
						if(findReponse.veracite == true){
							var caseCheckFindReponse = document.getElementById(findReponse.id);
							var libelleFindReponse = document.getElementById(findReponse.id+'lib');
							caseCheckFindReponse.style.backgroundColor = 'green';
							libelleFindReponse.style.backgroundColor = 'green';
						}
					});
				}
				console.log(index);
				console.log(reponse);
			});
	};
    		
    		
    });
})();	