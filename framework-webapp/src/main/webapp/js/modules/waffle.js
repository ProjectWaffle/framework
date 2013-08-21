angular.module('cookie', [ 'ngCookies' ]);

angular.module('waffle', [ 'cookie', 'waffle.controller', 'systemParameter.controller', 'login.controller' ]);

angular.module('waffle.controller', []).config(function($routeProvider) {
    $routeProvider.when('/', {
        templateUrl : 'pages/dashboard.html'
    }).otherwise({
        templateUrl : 'pages/404.html'
    });
});

function handlerError(data, $scope, $location) {
    if (403 == data.responseHeader.statusCode) {
        $location.path('/login');
    } else {
        $scope.error = data.responseHeader.statusMessage;
    }
}