angular.module('systemParameter.service', []).factory('SystemParameterResponse', [ '$http', function($http) {
    return {
        load : function(success, error, systemParameter) {
            $http.post('services/systemParameter/', systemParameter).success(function(data) {
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
} ]);

angular.module('systemParameter.controller', [ 'systemParameter.service' ]).config(function($routeProvider) {
    $routeProvider.when('/systemParameters', {
        templateUrl : 'pages/systemParameter/list.html',
        controller : 'ListCtrl'
    }).when('/systemParameter/:code', {
        templateUrl : 'pages/systemParameter/detail.html',
        controller : 'EditCtrl'
    });
});

function ListCtrl($scope, $cookies, $location, SystemParameterResponse) {
    $scope.systemParameter = {
        requestHeader : {
            username : $cookies.username,
            sessionid : $cookies.sessionid
        }
    };

    SystemParameterResponse.load(function(data) {
        $scope.serviceResponse = data;
    }, function(data) {
        handlerError(data, $scope, $location);
    }, angular.toJson($scope.systemParameter));
}

function EditCtrl($scope, $location, $routeParams, SystemParameterResponse) {
    SystemParameterResponse.loadByCode(function(data) {
        $scope.systemParameter = data.results[0];
    }, $routeParams.code);
}