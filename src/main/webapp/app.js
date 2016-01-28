var app = angular.module('atplQuizz', ['ngRoute'])

.config (function ($routeProvider) {
	$routeProvider
		.when ('/users/all', {
			templateUrl: 'js/user/view/userList.html'
		})
		.when ('/users/:id', {
			templateUrl: 'js/user/view/user.html'
		})
		.when ('/new', {
			templateUrl: 'js/user/view/user-form.html'
		})
		.when ('/delete/:id', {
			template: " ",
			controller: 'UserDeletionController'
		})
		.otherwise ({
			redirectTo: '/users'
		});
});
