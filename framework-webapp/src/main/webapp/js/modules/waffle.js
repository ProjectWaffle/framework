var apps = angular.module('waffle', [ 'ngCookies' ]);

apps.config(function($routeProvider) {
    $routeProvider.when('/', {
        templateUrl : 'pages/dashboard.html'
    }).when('/configuration', {
        templateUrl : 'pages/configuration/list.html',
        controller : 'ConfigurationCtrl'
    }).when('/configuration/:code', {
        templateUrl : 'pages/configuration/detail.html',
        controller : 'ConfigurationCtrl'
    }).when('/login', {
        templateUrl : 'pages/login.html',
    }).otherwise({
        templateUrl : 'pages/404.html'
    });
}).config(function($httpProvider) {
    $httpProvider.responseInterceptors.push('errorInterceptor');
});

apps.factory('errorInterceptor', function($q,  $location) {
    return function(promise) {

        return promise.then(function(response) {
            return promise;
        }, function(response) {
            if (response.status == 403) {
                $location.path('/login');
            }
            alert(response.data.responseHeader.statusMessage);
            return $q.reject(response);
        });
    };
});

apps.factory('MenuService', [ '$http', function($http) {
    return {
        populate : function(onSuccess) {
            $http.get('services/navigation/').success(function(data) {
                onSuccess(data);
            });
        }
    };
} ]);

apps.controller("MenuCtrl", function($scope, $cookies, MenuService) {
    MenuService.populate(function(data) {
        $scope.serviceResponse = data;
    });
});

function handlerError(data, $scope, $location) {
    alert(data);
    if (403 == data.responseHeader.statusCode) {
        $location.path('/login');
    } else {
        $scope.error = data.responseHeader.statusMessage;
    }
}
