angular.module('systemParameter.service', []).factory('SystemParameterResponse', [ '$http', function($http) {
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
    var config = {
        headers : {
            token : $cookies.token
        }
    };

    SystemParameterResponse.load(function(data) {
        $cookies.token = data.responseHeader.token;
        $scope.serviceResponse = data;
    }, function(data) {
        handlerError(data, $scope, $location);
    }, config);
}

function EditCtrl($scope, $location, $routeParams, SystemParameterResponse) {
    SystemParameterResponse.loadByCode(function(data) {
        $scope.systemParameter = data.results[0];
    }, $routeParams.code);
}