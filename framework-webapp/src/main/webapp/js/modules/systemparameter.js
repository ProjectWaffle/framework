angular.module('systemParameter.service', []).factory('SystemParameterResponse', ['$http', function($http){
    return{
        load: function(callback){
            $http.post('services/systemParameter/','{"request":{"code":"ACODE"},"requestHeader":{"userid":"1111","sessionid":"AS"}}').success(function(data) {
                callback(data);
            });
        },
        loadByCode: function(callback, code){
            $http.get('services/systemParameter/'+ code ).success(function(data) {
                callback(data);
            });
        }
    };
}]);

angular.module('systemParameter.controller', [ 'systemParameter.service' ]).config(function($routeProvider) {
    $routeProvider.when('/systemParameters', {
        templateUrl: 'pages/systemParameter/list.html', 
        controller:  'ListCtrl'
    }).when('/systemParameter/:code', {
        templateUrl: 'pages/systemParameter/detail.html', 
        controller:  'EditCtrl'
    });
});

function ListCtrl($scope, SystemParameterResponse) {
    SystemParameterResponse.load(function(data){
        $scope.serviceResponse = data;
    });
}

function EditCtrl($scope, $location, $routeParams, SystemParameterResponse) {
    SystemParameterResponse.loadByCode(function(data){
        $scope.systemParameter = data.results[0];
    },$routeParams.code);
}