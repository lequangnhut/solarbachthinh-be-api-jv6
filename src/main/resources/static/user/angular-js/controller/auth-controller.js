solar_app.controller('login_controller', function ($scope, $http, AuthService) {

    $scope.submit_login = function () {
        let email = $scope.email;
        let password = $scope.password;

        AuthService.loginAuth(email, password)
            .then(function successCallback() {
                window.location.href = "/redirect";
            });
    };

    $scope.submit_signup = function () {
        console.log($scope.user)
    };
});