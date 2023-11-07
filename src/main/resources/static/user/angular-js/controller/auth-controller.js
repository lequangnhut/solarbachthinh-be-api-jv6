solar_app.controller('login_controller', function ($scope, $http) {

    $scope.submit_login = function () {
        let email = $scope.email;
        let password = $scope.password;

        $http({
            method: 'POST',
            url: '/dang-nhap',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            data: 'username=' + email + '&password=' + password
        }).then(function successCallback() {
            window.location.href = "/redirect";
        });
    };

    $scope.submit_signup = function () {
        console.log($scope.user)
    };
});