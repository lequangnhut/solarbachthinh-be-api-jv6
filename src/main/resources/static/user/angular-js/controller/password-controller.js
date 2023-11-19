solar_app.controller('password-controller', function ($scope, $route, PasswordService) {

    $scope.submitFormForgot = function () {
        PasswordService.checkExistEmailForgotPass($scope.users.email).then(function successCallback(response) {
            let emailCheck = response.data.exists;

            if (emailCheck) {
                PasswordService.emailForgot($scope.users.email, $scope.users)
                    .then(function successCallback(response) {
                        Swal.fire({
                            title: 'Email hợp lệ!',
                            text: "Mời bạn kiểm tra mã OTP được gửi trong mail!",
                            icon: 'success',
                            showConfirmButton: false,
                            timer: 3000
                        }).then((result) => {
                            window.location.href = "#!/xac-nhan-otp";
                        });
                    }, function errorCallback(response) {
                        console.log('Lỗi khi đổi mật khẩu người dùng', response);
                    });
            } else {
                centerAlert('Thông báo!', 'Email không tồn tại!', 'error');
            }
        }).catch(function errorCallback(response) {
            console.log('Lỗi khi kiểm tra tồn tại email', response.data);
        });
    };

    $scope.submitVerifyCode = function () {
        PasswordService.checkCodeOnTime().then(function successCallback(response) {
            let codeTime = response.data.exists;

            if (codeTime) {
                PasswordService.checkVerifyCode({verifyCode: $scope.verifyCode}).then(function successCallback(response) {
                    let codeGet = response.data.isValid;

                    if (codeGet) {
                        Swal.fire({
                            title: 'Xác nhận thành công!',
                            text: "Mời người dùng thiết lập mật khẩu mới!",
                            icon: 'success',
                            showConfirmButton: false,
                            timer: 3000
                        }).then((result) => {
                            // Chuyển hướng đến trang đặt lại mật khẩu mới
                            window.location.href = "#!/mat-khau-moi";
                        });
                    } else {
                        centerAlert('Thông báo!', 'Mã OTP không đúng!', 'error');
                    }
                }, function errorCallback(response) {
                    console.log('Lỗi khi đổi mật khẩu người dùng', response);
                });
            } else {
                centerAlert('Thông báo!', 'Mã OTP hết hiệu lực, mời nhập lại email!', 'error');
                window.location.href = "#!/quen-mat-khau";
            }
        }).catch(function errorCallback(response) {
            console.log('Lỗi khi kiểm tra thời gian', response.data);
        });
    };

    $scope.submitNewPassword = function () {
        PasswordService.checkNewPassword($scope.users).then(function successCallback(response) {
            Swal.fire({
                title: 'Tạo mật khẩu mới thành công!',
                text: "Mời người dùng đăng nhập lại!",
                icon: 'success',
                showConfirmButton: false,
                timer: 3000
            }).then((result) => {
                // Chuyển hướng đến trang đặt lại mật khẩu mới
                window.location.href = "#!/dang-nhap";
            });
        }).catch(function errorCallback(response) {
            console.log('Lỗi khi kiểm tra thời gian', response.data);
        });
    }
})