solar_app.controller('address_controller', function ($scope, $http, $timeout, $window, ShippingService, AddressService) {

    $scope.address = {
        provinceName: "", districtName: "", wardName: "",
    };

    // add modal
    $scope.addAddressModal = function () {
        $('#model-add-address').modal('show');
        $('#model-address').modal('hide');
    }

    // default modal
    $scope.showDefaultModal1 = function () {
        $('#model-address').modal('show');
        $('#model-info-address').modal('hide');
    }

    // default modal
    $scope.showDefaultModal2 = function () {
        $('#model-address').modal('show');
        $('#model-add-address').modal('hide');
    }

    // Lấy danh sách tỉnh thành
    ShippingService.getProvince().then(function (response) {
        $scope.ship_province = response.data.data;
    }, function (error) {
        console.log(error);
    });

    // Hàm này được gọi khi người dùng chọn một tỉnh thành cụ thể
    $scope.getDistrictsAddress = function (provinceName) {
        const province_id = provinceName.ProvinceID;

        ShippingService.getDistrictsByProvinceId(province_id).then(function (response) {
            $scope.ship_districts = response.data.data;
        }, function (error) {
            console.log(error);
        });
    };

    // Hàm này được gọi khi người dùng chọn một quận huyện cụ thể
    $scope.getWardAddress = function (districtName) {
        if (districtName) {
            const district_id = districtName.DistrictID;

            ShippingService.getWardByDistrictId(district_id).then(function (response) {
                $scope.ship_ward = response.data.data;
            }, function (error) {
                console.log(error);
            });
        }
    };

    // lấy ra tất cả địa chỉ đã lưu của user id đang đăng nhập
    AddressService.findAllAddress().then(function successCallback(response) {
        $scope.allAdress = response.data;
    });

    $scope.onProvinceChange = function () {
        $scope.address.provinceName = null;
        const selectElement = document.getElementById("city");
        $scope.address.provinceName = selectElement.options[selectElement.selectedIndex].text;
    }

    $scope.onDistrictChange = function () {
        $scope.address.districtName = null;
        const selectElement = document.getElementById("province");
        $scope.address.districtName = selectElement.options[selectElement.selectedIndex].text;
    }

    $scope.onWardsChange = function () {
        $scope.address.wardName = null;
        const selectElement = document.getElementById("ward");
        $scope.address.wardName = selectElement.options[selectElement.selectedIndex].text;
    }

    // submit thêm địa chỉ
    $scope.submitAddAddressForm = function () {
        let data = {
            toName: $scope.address.fullName,
            toPhone: $scope.address.phoneNumber,
            toProvince: $scope.address.provinceName.ProvinceName,
            toDistrict: $scope.address.districtName.DistrictName,
            toWard: $scope.address.wardName.WardName,
            toAddress: $scope.address.address,
            isActive: $scope.address.isActive
        }

        AddressService.submitAddress(data).then(function successCallback() {
            AddressService.findAllAddress().then(function successCallback(response) {
                $scope.allAdress = response.data;

                toastAlert('success', 'Thêm địa chỉ mới thành công !');
                $('#model-add-address').modal('hide');
                $('#model-address').modal('show');
                $scope.address = {};
            });
        });
    }

    // update modal
    $scope.updateAddressModal = function (addressId) {
        $('#model-address').modal('hide');
        $('#model-info-address').modal('show');

        AddressService.findByAddressId(addressId).then(function successCallback(response) {
            let dataAddress = response.data;

            $scope.address = {
                id: dataAddress.id,
                fullName: dataAddress.toName,
                phoneNumber: dataAddress.toPhone,
                provinceName: dataAddress.toProvince,
                districtName: dataAddress.toDistrict,
                wardName: dataAddress.toWard,
                toAddress: dataAddress.toAddress,
                isActive: dataAddress.isActive,
            };
        });
    };

    // cập nhật địa chỉ
    $scope.updateAddAddressForm = function () {
        $scope.address = {
            id: $scope.address.id,
            toName: $scope.address.fullName,
            toPhone: $scope.address.phoneNumber,
            toProvince: $scope.address.provinceName,
            toDistrict: $scope.address.districtName,
            toWard: $scope.address.wardName,
            toAddress: $scope.address.toAddress,
            isActive: $scope.address.isActive,
        };

        AddressService.updateAddress($scope.address).then(function successCallback() {
            AddressService.findAllAddress().then(function successCallback(response) {
                $scope.allAdress = response.data;

                toastAlert('success', 'Cập nhật địa chỉ thành công !');
                $('#model-info-address').modal('hide');
                $('#model-address').modal('show');
                $scope.address = {};
            });
        });
    }

    // thay đổi địa chỉ trên form
    $scope.updateSelectedAddress = function (addressId) {
        $scope.selectedAddressId = addressId;
    };

    $scope.confirmSelection = function () {
        let addressId = $scope.selectedAddressId;

        if (addressId) {
            AddressService.findByAddressId(addressId).then(function successCallback(response) {
                let selectedAddress = response.data;

                $scope.user.fullname = selectedAddress.toName;
                $scope.user.phoneNumber = selectedAddress.toPhone;
                $scope.user.provinceName = selectedAddress.toProvince;
                $scope.user.districtName = selectedAddress.toDistrict;
                $scope.user.wardName = selectedAddress.toWard;
                $scope.user.address = selectedAddress.toAddress;

                $('#model-address').modal('hide');
            });
        }
    };
});