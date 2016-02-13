(function() {
    'use strict';
    angular.module("AtplQuizzApp").
    	controller('qcmController', function ($scope, qcm, $location, ThemeService, $window, QuestionnaireByThemeService) {
    		
    		$scope.resultMode = false;
    		$scope.notation = 20;
    		$scope.goodAnswer = 0;
    		$scope.finalPercent = 0;
    		$scope.idTheme = $location.path();
    		var afterSplit = $scope.idTheme.split("/");
    		
    		console.log($scope.idTheme);
    		var param = {
					themeId : afterSplit[2]
			}
			$scope.theme = ThemeService.query(param);
			$scope.theme.$promise.then(
					function(theme) {
						$scope.themeFound = theme;
					}, function(error) {
						console.log("error");
					});
    		
    		$scope.qcm = qcm;
    		console.log(qcm);
    		var map = new Map();
    		$scope.checkBoxSelected = function(index, reponse){
    			map.set(index,reponse);
    			console.log(map);
    			
    		};
    		
    		$scope.validate= function() {
			angular.forEach(map, function(reponse ,index){
				var caseCheck = document.getElementById(reponse.id);
				var libelle = document.getElementById(reponse.id+'lib');
				if (reponse.veracite == true){
					caseCheck.style.backgroundColor = 'green';
					libelle.style.backgroundColor = 'green';
					$scope.goodAnswer++;
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
			
			$scope.resultMode = true;
			console.log($scope.goodAnswer);
			$scope.finalPercent = ($scope.goodAnswer/$scope.notation)*100;
			console.log($scope.finalPercent);
			$window.scrollTo(0, angular.element(document.getElementById('timal')).offsetTop);
	};
	
	$scope.returnToTheme = function(){
		$location.path('/themes');
		$scope.resultMode = false;
	};
    		
    		
    });
})();	