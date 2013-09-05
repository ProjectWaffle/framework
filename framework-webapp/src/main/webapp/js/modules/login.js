var user = {
    username : null,
    password : null
};

apps.factory('LoginService', function($http) {
    return {
        authenticate : function(onSuccess, json) {
            $http.post('services/login/', json).success(function(data) {
                onSuccess(data);
            });
        }
    };
});

function LoginCtrl($scope, $cookies, $location, LoginService) {
    $scope.user = user;

    $scope.login = function() {
        LoginService.authenticate(function(data) {
            $cookies.username = data.result.username;
            $cookies.sessionid= data.result.sessionid;
            $location.path('/configuration');
        }, angular.toJson($scope.user));
    };
}