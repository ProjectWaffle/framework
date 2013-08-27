var user = {
    username : null,
    password : null
};

apps.factory('LoginService', function($http) {
    return {
        authenticate : function(success, error, user) {
            $http.post('services/login/', user).success(function(data) {
                success(data);
            }).error(function(data) {
                error(data);
            });
        }
    };
});

function LoginCtrl($scope, $cookies, $location, LoginService) {
    $scope.user = user;

    $scope.login = function() {
        LoginService.authenticate(function(data) {
            $cookies.token = data.responseHeader.token;
            $location.path('/systemParameters');
        }, function(data) {
            handlerError(data, $scope, $location);
        }, angular.toJson($scope.user));
    };
}