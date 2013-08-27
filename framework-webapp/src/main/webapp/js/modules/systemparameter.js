apps.factory('SystemParameterService', function($http) {
    return {
        load : function(success, error, config) {
            $http.get('services/systemParameter/', config).success(function(data) {
                success(data);
            }).error(function(data) {
                error(data);
            });
        },
        loadByCode : function(callback, code) {
            $http.get('services/systemParameter/' + code).success(function(data) {
                callback(data);
            });
        }
    };
});

function ListCtrl($scope, $cookies, $location, SystemParameterService) {
    var config = {
        headers : {
            token : $cookies.token
        }
    };

    SystemParameterService.load(function(data) {
        $cookies.token = data.responseHeader.token;
        $scope.serviceResponse = data;
    }, function(data) {
        handlerError(data, $scope, $location);
    }, config);
}

function EditCtrl($scope, $location, $routeParams, SystemParameterService) {
    SystemParameterService.loadByCode(function(data) {
        $scope.systemParameter = data.results[0];
    }, $routeParams.code);
}