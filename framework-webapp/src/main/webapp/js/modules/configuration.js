var configuration = {
    code : "CODE",
    description : "DESCRIPTION",
    value : "VALUE"
};

apps.factory('ConfigurationService', function($http) {
    return {
        load : function(onSuccess) {
            $http.get('services/configuration/').success(function(data) {
                onSuccess(data);
            });
        },
        loadByCode : function(onSuccess, code) {
            $http.get('services/configuration/' + code).success(function(data) {
                onSuccess(data);
            });
        },
        submit : function(onSuccess, code, config) {
            $http.get('services/configuration/' + code).success(function(data) {
                alert("AS");
            });
        }
    };
});

function ConfigurationListCtrl($scope, ConfigurationService) {
    ConfigurationService.load(function(data) {
        $scope.configurations = data.result;
    });
}

function ConfigurationEditCtrl($scope, $routeParams, ConfigurationService) {
    ConfigurationService.loadByCode(function(data) {
        $scope.configuration = data.result;
    }, $routeParams.code);
}
/*

$scope.loadByCode = SystemParameterService.loadByCode(function(data) {
    $scope.systemParameter = data.result;
}, $routeParams.code, config);

$scope.systemParameter.submit = SystemParameterService.submit(function(data) {
    $scope.systemParameter = data.result;
}, $routeParams.code, config);*/