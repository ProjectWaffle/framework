function ConfigurationCtrl($scope, $http, $routeParams, $location) {
    $scope.list = function() {
        $http.get('services/configuration/').success(function(data) {
            $scope.configurations = data.result;
        });
    };
    $scope.edit = function() {
        $http.get('services/configuration/' + $routeParams.code).success(function(data) {
            $scope.message = data.responseHeader.statusMessage;
            $scope.configuration = data.result;
        });
    };
    $scope.submit = function() {
        $http.post('services/configuration/save', $scope.configuration).success(function(data) {
            $scope.configuration = data.result;
            $scope.message = data.responseHeader.statusMessage;
            $location.path('/configuration/saved/' + $routeParams.code);
        });
    };
}