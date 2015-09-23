angular
    .module('myApp', ['ngResource', 'ngRoute', 'swaggerUi'])
    .config(function ($routeProvider) {
        $routeProvider.when('/users', {
            templateUrl: 'partials/users.html',
            controller: 'UsersController',
        }).
            when('/apiDoc', {
                templateUrl: 'partials/apiDoc.html',
                controller: 'ApiDocController'
            }).otherwise({
                redirectTo: '/users',
            });
    })
    .service('UsersService', function ($log, $resource) {
        return {
            getAll: function () {
                var userResource = $resource('users', {}, {
                    query: {method: 'GET', params: {}, isArray: true}
                });
                return userResource.query();
            }
        }
    })
    .controller('UsersController', function ($scope, $log, UsersService) {
        $scope.users = UsersService.getAll();
    })
    .controller('ApiDocController', function ($scope) {
        // init form
        $scope.isLoading = false;
        $scope.url = $scope.swaggerUrl = 'v2/api-docs';
        // error management
        $scope.myErrorHandler = function (data, status) {
            alert('failed to load swagger: ' + status + '   ' + data);
        };

        $scope.infos = false;
    });
