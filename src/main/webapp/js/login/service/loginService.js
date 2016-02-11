(function() {
    'use strict';
    angular.module('AtplQuizz', []).
    factory('LoginUserService', LoginUserService);
    	
    var injectParams = ["$resource"];
    
    LoginUserService.$inject = injectParams;
    
    function LoginUserService($resource){
    	return $resource(
    	        '/users/loginUser', {},
    	        {
    	        	'query':{
    	        		method: 'GET',
    	        		isArray: false
    	        	}
    	        });
};
})();
