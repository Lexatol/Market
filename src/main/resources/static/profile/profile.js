angular.module('app').controller('profileController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market/api/v1';

    $scope.submitUpdateProfile = function () {
        $http.put(contextPath + '/profile', $scope.profile)
            .then(function (response) {
                $scope.loadProfile();
                alert('Профиль обновлен');
            });
    };

    $scope.loadProfile = function () {
        $http.get(contextPath + '/profile')
            .then(function (response) {
                $scope.profile = response.data;
            });
    }

    $scope.loadProfile();
});