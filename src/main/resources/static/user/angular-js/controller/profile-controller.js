solar_app.controller('profile-controller', function ($scope, $route, UserService) {

    $scope.genderOptions = [
        {label: 'Nam', value: true},
        {label: 'Nữ', value: false}
    ];


    UserService.findUserBySession()
        .then(function successCallback(response) {
            $scope.users = response.data;

            $scope.users.birth = new Date($scope.users.birth);
            console.log($scope.users);
        }, function errorCallback(response) {
            console.log(response.data);
        });

    //Thao tác lấy giá trị tỉnh/thành mới
    $scope.onProvinceSelect = function (selectedWard) {
        $scope.users.provinceName = "";
        var selectElement = document.getElementById("city");
        var selectedText = selectElement.options[selectElement.selectedIndex].text;
        $scope.users.provinceName = selectedText;
        console.log("Tỉnh mới chọn: " + $scope.users.provinceName);
    };

    //Thao tác lấy giá trị quận/huyện mới
    $scope.onDistrictSelect = function (selectedWard) {
        $scope.users.districtName = "";
        var selectElement = document.getElementById("province");
        var selectedText = selectElement.options[selectElement.selectedIndex].text;
        $scope.users.districtName = selectedText;
        console.log("Quận mới chọn: " + $scope.users.districtName);
    };

    //Thao tác lấy giá trị phường/xã mới
    $scope.onWardSelect = function (selectedWard) {
        $scope.users.wardName = "";
        var selectElement = document.getElementById("ward");
        var selectedText = selectElement.options[selectElement.selectedIndex].text;
        $scope.users.wardName = selectedText;
        console.log("Phường mới chọn: " + $scope.users.wardName);
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
                UserService.checkExistPhoneProfile($scope.users.phoneNumber).then(function successCallback(response) {
                    phone = response.data.exists;
                    if (phone) {
                        centerAlert('Cảnh báo !', 'Số điện thoại đã tồn tại ở một tài khoản khác !', 'warning');
                    } else {
                        UserService.updateUser(userId, $scope.users)
                            .then(function successCallback(response) {
                                console.log($scope.users)
                                console.log("Địa chỉ mới: " + $scope.users.address + ", "
                                    + $scope.users.wardName + ", " + $scope.users.districtName
                                    + ", " + $scope.users.provinceName)
                                console.log("Thông tin chỉnh sửa thành công")
                                $scope.users = response.data;
                                window.location.href = "/redirect";
                            }, function errorCallback(response) {
                                console.log('Lỗi khi cập nhật thông tin người dùng', response.data);
                            });
                    }
                });
            }
        });
    };

    $scope.submit_changePass = function (userId) {
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
                UserService.checkCorrectCurrentPass($scope.users.phoneNumber)
                    .then(function successCallback(response) {
                    phone = response.data.exists;
                    if (phone) {
                        centerAlert('Cảnh báo !', 'Số điện thoại đã tồn tại ở một tài khoản khác !', 'warning');
                    } else {
                        UserService.updateUser(userId, $scope.users)
                            .then(function successCallback(response) {
                                console.log($scope.users)
                                centerAlert('Thông báo', 'Cập nhật thành công!')
                                console.log("Thông tin chỉnh sửa thành công")
                                $scope.users = response.data;
                                window.location.href = "/redirect";
                            }, function errorCallback(response) {
                                console.log('Lỗi khi cập nhật thông tin người dùng', response.data);
                            });
                    }
                });
            }
        });
    };
})
