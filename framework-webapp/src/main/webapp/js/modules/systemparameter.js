var systemParameter = {
    code : "CODE",
    description : "DESCRIPTION",
    value : "VALUE"
};

apps.factory('SystemParameterService', function($http) {
    return {
        load : function(success, config) {
            $http.get('services/systemParameter/', config).success(function(data) {
                success(data);
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
    }, config);
    
    /*

    $scope.loadByCode = SystemParameterService.loadByCode(function(data) {
        $scope.systemParameter = data.result;
    }, $routeParams.code, config);

    $scope.systemParameter.submit = SystemParameterService.submit(function(data) {
        $scope.systemParameter = data.result;
    }, $routeParams.code, config);*/
}