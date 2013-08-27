var systemParameter = {
    code : "CODE",
    description : "DESCRIPTION",
    value : "VALUE"
};

apps.factory('SystemParameterService', function($http) {
    return {
        load : function(success, error, config) {
            $http.get('services/systemParameter/', config).success(function(data) {
                success(data);
            }).error(function(data) {
                error(data);
            });
        },
        loadByCode : function(onSuccess, code, config) {
            $http.get('services/systemParameter/' + code, config).success(function(data) {
                onSuccess(data);
            });
        },
        submit : function(onSuccess, code, config) {
            $http.get('services/systemParameter/' + code, config).success(function(data) {
                alert("AS");
            });
        }
    };
});

function SystemParameterCtrl($scope, $cookies, $location, $routeParams, SystemParameterService) {
    var config = {
        headers : {
            token : $cookies.token
        }
    };

    $scope.systemParameter = systemParameter;

    $scope.loadAll = SystemParameterService.load(function(data) {
        $cookies.token = data.responseHeader.token;
        $scope.serviceResponse = data;
    }, function(data) {
        handlerError(data, $scope, $location);
    }, config);
    
    /*

    $scope.loadByCode = SystemParameterService.loadByCode(function(data) {
        $scope.systemParameter = data.result;
    }, $routeParams.code, config);

    $scope.systemParameter.submit = SystemParameterService.submit(function(data) {
        $scope.systemParameter = data.result;
    }, $routeParams.code, config);*/
}