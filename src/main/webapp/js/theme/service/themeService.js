(function() {
	'use strict';

	angular.module('AtplQuizzApp').factory('ThemeService', ThemeService);

	var injectParams = [ "$resource" ];

	ThemeService.$inject = injectParams;

	function ThemeService($resource) {
		return $resource( '/themes/themeByID', {}, {
			'query' : {
				method : 'GET',
				isArray : true
			}
		});
	}
})();
