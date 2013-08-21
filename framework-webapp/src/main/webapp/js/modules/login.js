angular.module('login.service', []).factory('LoginResponse', [ '$http', function($http) {
    return {
        authenticate : function(success, error, user) {
            $http.post('services/login/', user).success(function(data) {
                success(data);
            }).error(function(data) {
                error(data);
            });
        }
    };
} ]);

angular.module('login.controller', [ 'login.service' ]).config(function($routeProvider) {
    $routeProvider.when('/login', {
        templateUrl : 'pages/login.html',
    });
});

function LoginCtrl($scope, $cookies, $location, LoginResponse) {
    $scope.user = {
        request : {
            username : null,
            password : null
        }
    };

    $scope.login = function() {
        LoginResponse.authenticate(function(data) {
            $cookies.sessionid = data.result.sessionid;
            $cookies.username = data.result.username;
            $location.path('/');
        }, function(data) {
            handlerError(data, $scope, $location);
        }, angular.toJson($scope.user));
    };
}