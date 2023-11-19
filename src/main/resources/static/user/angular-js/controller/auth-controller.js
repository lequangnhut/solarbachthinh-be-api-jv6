solar_app.controller('auth_controller', function ($scope, $http, AuthService) {

    $scope.emailError = false;
    $scope.phoneError = false;

    // form Đăng nhập

    $scope.togglePassword = function () {

        var passwordInput = document.getElementById('passwordLogin');
        var eyeIcon = document.getElementById('togglePasswordLogin');
        console.log("nhận");
        if (passwordInput.type === 'password') {
            passwordInput.type = 'text';
            eyeIcon.innerHTML = '<i class="fa-solid fa-eye"></i>';
        } else {
            passwordInput.type = 'password';
            eyeIcon.innerHTML = '<i class="fa-solid fa-eye-slash"></i>';
        }
    };
    // form đăng ký

    // nhập mật khẩu
    $scope.togglePassword1 = function () {
        var passwordInput = document.getElementById('passwordSignup');
        var eyeIcon = document.getElementById('togglePasswordSignup');


        if (passwordInput.type === 'password') {
            passwordInput.type = 'text';
            eyeIcon.innerHTML = '<i class="fa-solid fa-eye"></i>';
        } else {
            passwordInput.type = 'password';
            eyeIcon.innerHTML = '<i class="fa-solid fa-eye-slash"></i>';
        }
    };

    // xác nhận lại mật khẩu
    $scope.togglePassword2 = function () {
        var passwordInput = document.getElementById('passwordConfirm');
        var eyeIcon = document.getElementById('togglePasswordConfirm');


        if (passwordInput.type === 'password') {
            passwordInput.type = 'text';
            eyeIcon.innerHTML = '<i class="fa-solid fa-eye"></i>';
        } else {
            passwordInput.type = 'password';
            eyeIcon.innerHTML = '<i class="fa-solid fa-eye-slash"></i>';
        }
    };

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