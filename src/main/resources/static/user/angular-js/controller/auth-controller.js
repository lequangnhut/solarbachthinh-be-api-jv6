solar_app.controller('auth_controller', function ($scope, $http, AuthService) {

    $scope.submit_login = function () {
        let email = $scope.email;
        let password = $scope.password;

        AuthService.loginAuth(email, password).then(function successCallback() {
            window.location.href = "/redirect";
        });
    };

    $scope.submit_signup = function () {
        let email;
        let phone

        AuthService.checkExistEmail($scope.user.email).then(function successCallback(response) {
            email = response.data.exists;

            AuthService.checkExistPhone($scope.user.phoneNumber).then(function successCallback(response) {
                phone = response.data.exists;

                if (email) {
                    centerAlert('Cảnh báo !', 'Email đã tồn tại ở một tài khoản khác !', 'warning');
                } else if (phone) {
                    centerAlert('Cảnh báo !', 'Số điện thoại đã tồn tại ở một tài khoản khác !', 'warning');
                } else {
                    AuthService.creatAuth($scope.user).then(function successCallback() {
                        window.location.href = "#!/dang-nhap";
                        centerAlert('Thành công !', 'Đăng ký tài khoản thành công. Vui lòng xác thực email để đăng nhập !', 'success');
                    });
                }
            });
        });
    };
});