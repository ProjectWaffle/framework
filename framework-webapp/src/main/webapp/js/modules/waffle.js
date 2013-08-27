var apps = angular.module('waffle', [ 'ngCookies' ]);

apps.config(function($routeProvider) {
    $routeProvider.when('/', {
        templateUrl : 'pages/dashboard.html'
    }).when('/systemParameters', {
        templateUrl : 'pages/systemParameter/list.html',
        controller : 'SystemParameterCtrl'
    }).when('/systemParameters/:code', {
        templateUrl : 'pages/systemParameter/detail.html',
        controller : 'SystemParameterCtrl'
    }).when('/login', {
        templateUrl : 'pages/login.html',
    }).otherwise({
        templateUrl : 'pages/404.html'
    });
});

apps.factory('MenuService', [ '$http', function($http) {
    return {
        populate : function(success, config) {
            $http.get('services/menu/', config).success(function(data) {
                success(data);
            });
        }
    };
} ]);

apps.controller("MenuCtrl", function($scope, $cookies, MenuService) {
    var config = {
        headers : {
            token : $cookies.token
        }
    };

    MenuService.populate(function(data) {
        $scope.serviceResponse = data;
    }, config);
});

function handlerError(data, $scope, $location) {
    if (403 == data.responseHeader.statusCode) {
        $location.path('/login');
    } else {
        $scope.error = data.responseHeader.statusMessage;
    }
}
