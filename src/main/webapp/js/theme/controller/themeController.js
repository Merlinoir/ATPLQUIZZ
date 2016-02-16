(function() {
    'use strict';
    angular.module("AtplQuizzApp").
    	controller('themeController', function ($scope, $location) {
  
    $scope.themeSelected = function(idThemeSelected){
    	$scope.idTheme = idThemeSelected;
    	$location.path("/qcm/"+$scope.idTheme);
    }; 
    });
})();	