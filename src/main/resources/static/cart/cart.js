angular.module('app').controller('cartController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market/api/v1';

    $scope.showCart = function () {
        $http({
            url: contextPath + '/cart',
            method: 'GET'
        }).then(function (response) {
            $scope.Cart = response.data;
        });
    };

    $scope.addToCart = function (productId) {
        $http.get(contextPath + '/cart/add/' + productId)
            .then(function (response) {
                $scope.showCart();
            });
    }

    $scope.clearCart = function () {
        $http.get(contextPath + '/cart/clear')
            .then(function (response) {
                $scope.showCart();
            });
    }

    $scope.decrementItem = function (productId) {
            $http.get(contextPath + '/cart/dec/' + productId)
                .then(function (response) {
                    $scope.showCart();
                });
        };

        $scope.removeItem = function (productId) {
            $http.get(contextPath + '/cart/remove/' + productId)
                .then(function (response) {
                    $scope.showCart();
                });
        };



    $scope.showCart();
});