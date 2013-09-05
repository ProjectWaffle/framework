var apps = angular.module('waffle', [ 'ngCookies' ]);

apps.config(function($routeProvider) {
    $routeProvider.when('/', {
        templateUrl : 'pages/dashboard.html'
    }).when('/configuration', {
        templateUrl : 'pages/configuration/list.html',
        controller : 'ConfigurationCtrl'
    }).when('/configuration/edit/:code', {
        templateUrl : 'pages/configuration/detail.html',
        controller : 'ConfigurationCtrl'
    }).when('/configuration/saved/:code', {
        templateUrl : 'pages/configuration/success.html',
        controller : 'ConfigurationCtrl'
    }).when('/login', {
        templateUrl : 'pages/login.html'
    }).otherwise({
        templateUrl : 'pages/404.html'
    });
}).run(function($rootScope, $location, $http) {
    $rootScope.$on('$routeChangeStart', function(event, next, current) {
        $http.get('services/authentication/verify').success(function(data) {
            var isAuthenticated = data.result;
            if ($location.url() != '/login' && !isAuthenticated) {
                $location.path('/login');
            } else if ($location.url() == '/login' && isAuthenticated) {
                $location.path('/');
            }
        });
    });
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