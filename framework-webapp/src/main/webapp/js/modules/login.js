function LoginCtrl($scope, $cookies, $location, $http) {
    $scope.user = {
        username : null,
        password : null
    };

    $scope.login = function() {
        $http.post('services/authentication/login/', $scope.user).success(function(data) {
            $cookies.username = data.result.username;
            $cookies.sessionid = data.result.sessionid;
            $location.path('/');
        });
    };
}