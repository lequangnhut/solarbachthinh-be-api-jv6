solar_app.controller('auth_controller', function ($scope, $http, AuthService) {

    $scope.emailError = false;
    $scope.phoneError = false;

    $scope.submit_login = function () {
        let email = $scope.email;
        let password = $scope.password;

        AuthService.loginAuth(email, password).then(function successCallback() {
            window.location.href = "/redirect";
        });
    };

    $scope.checkDuplicateEmail = function () {
        AuthService.checkExistEmail($scope.user.email).then(function successCallback(response) {
            $scope.emailError = response.data.exists;
        });
    };

    $scope.checkDuplicatePhone = function () {
        AuthService.checkExistPhone($scope.user.phoneNumber).then(function successCallback(response) {
            $scope.phoneError = response.data.exists;
        });
    };

    $scope.submit_signup = function () {
        AuthService.creatAuth($scope.user).then(function successCallback() {
            window.location.href = "#!/dang-nhap";
            centerAlert('Thành công !', 'Đăng ký tài khoản thành công. Vui lòng xác thực email để đăng nhập !', 'success');
        });
    };
});