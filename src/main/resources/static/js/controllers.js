angular.module('pft.controllers')
    .controller('leadTimeCtrl', function ($scope, $http, $filter) {

        $scope.post = function (request) {
            $http.post("rest/leadtime", request).success(function (data) {
                console.log("suc");
                data = $filter('orderBy')(data, '-days', true)

                var sum = 0;

                for (i=0; i < data.length; i++) {
                    sum  = sum + data[i].probabilityPercentage;
                    data[i].cumulate = sum;
                }
                $scope.responses= data;




            }).
                error(function (data) {
                    console.log("eror");

                    console.log(data);
                });
        };


    });