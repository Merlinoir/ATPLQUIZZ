(function() {
    'use strict';

    angular.module('AtplQuizzApp').
    	factory('UserService', UserService);
    
    var injectParams = ["$resource"];
    
    UserService.$inject = injectParams;
    
    function UserService($resource) {
		return $resource('/users/:user_id', {user_id:'@user_id'}, {
          	'query': { method: 'GET', isArray: true },
             'save': {method:'POST', isArray: false },
             'update': {method:'PUT', isArray: false },
             'delete': {method:'DELETE', isArray: false}
		});
    } 
})();