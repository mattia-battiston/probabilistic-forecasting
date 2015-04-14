angular.module('pft.controllers')
    .controller('helloCtrl', function ($scope, $http) {

        $scope.master = {};

        $scope.update = function (request) {
            $scope.master = angular.copy(request);
            $http.post("rest/leadtime", request).success(function (data) {
                console.log("suc");

                console.log(data);

            }).
                error(function (data) {
                    console.log("eror");

                    console.log(data);
                });
        };



    });