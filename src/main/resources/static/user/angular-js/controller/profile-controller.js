solar_app.controller('profile-controller', function ($scope, $route, UserService) {

    $scope.genderOptions = [
        {label: 'Nam', value: true},
        {label: 'Nữ', value: false}
    ];


    UserService.fillProfileUserBySession()
        .then(function successCallback(response) {
            $scope.users = response.data;
            $scope.users.birth = new Date($scope.users.birth);
            // console.log($scope.users);
        }, function errorCallback(response) {
            console.log(response.data);
        });

    //Thao tác lấy giá trị tỉnh/thành mới
    $scope.onProvinceSelect = function () {
        $scope.users.provinceName = "null";
        var selectElement = document.getElementById("city");
        var selectedText = selectElement.options[selectElement.selectedIndex].text;
        $scope.users.provinceName = selectedText;
    };

    //Thao tác lấy giá trị quận/huyện mới
    $scope.onDistrictSelect = function () {
        $scope.users.districtName = "null";
        var selectElement = document.getElementById("province");
        var selectedText = selectElement.options[selectElement.selectedIndex].text;
        $scope.users.districtName = selectedText;
    };

    //Thao tác lấy giá trị phường/xã mới
    $scope.onWardSelect = function () {
        $scope.users.wardName = "null";
        var selectElement = document.getElementById("ward");
        var selectedText = selectElement.options[selectElement.selectedIndex].text;
        $scope.users.wardName = selectedText;
    };

    $scope.submit_editProfile = function (userId) {
        Swal.fire({
            title: 'Cảnh báo ?',
            text: "Bạn có chắc chắn muốn cập nhật thông tin không ?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Đồng ý !',
            cancelButtonText: 'Huỷ !'
        }).then((result) => {
            if (result.isConfirmed) {
                UserService.checkExistPhoneProfile($scope.users.phoneNumber)
                    .then(function successCallback(response) {
                        phone = response.data.exists;
                        if (phone) {
                            centerAlert('Cảnh báo !', 'Số điện thoại đã tồn tại ở một tài khoản khác !', 'warning');
                        } else {
                            UserService.updateProfileUser(userId, $scope.users)
                                .then(function successCallback(response) {
                                    $scope.users = response.data;
                                    window.location.href = "/redirect";
                                    centerAlert('Thành công !', 'Thông tin người dùng được cập nhật!', 'success');
                                }, function errorCallback(response) {
                                    console.log('Lỗi khi cập nhật thông tin người dùng', response.data);
                                });
                        }
                    });
            }
        });
    };

    $scope.submit_changePass = function () {
        let currentPass = $scope.users.currentPass;
        let newPass = $scope.users.newPass;

        if (newPass === currentPass) {
            centerAlert('Cảnh báo !', 'Mật khẩu mới không được trùng mật khẩu cũ!', 'warning');
            return;
        }
        Swal.fire({
            title: 'Cảnh báo ?',
            text: "Bạn có chắc chắn muốn cập nhật mật khẩu không ?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Đồng ý !',
            cancelButtonText: 'Huỷ !'
        }).then((result) => {
            if (result.isConfirmed) {
                UserService.checkCorrectCurrentPass(currentPass)
                    .then(function successCallback(response) {
                        currentPass = response.data.exists;
                        if (currentPass) {
                            UserService.changePass($scope.users)
                                .then(function successCallback(response) {
                                    Swal.fire({
                                        title: 'Cảnh báo !',
                                        text: "Đổi mật khẩu thành công. Mời đăng nhập lại!",
                                        icon: 'success',
                                        showConfirmButton: false, // Tắt nút "Đồng ý"
                                        timer: 3000 // Tự động đóng thông báo sau 3 giây
                                    }).then((result) => {
                                        window.location.href = "/dang-xuat";
                                    });
                                    }, function errorCallback(response) {
                                    console.log('Lỗi khi đổi mật khẩu người dùng', response);
                                });
                        } else {
                            centerAlert('Cảnh báo !', 'Mật khẩu hiện tại không chính xác!', 'warning');
                        }
                    });
            }
        });
    };

})
