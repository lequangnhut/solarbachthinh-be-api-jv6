solar_app.controller('payment_controller', function ($scope, OrderService, DiscountService) {

    $scope.paymentMethod = "COD";

    $scope.payment_calculator = function () {
        Swal.fire({
            title: 'Cảnh báo ?',
            text: "Bạn có chắc chắn muốn đặt hàng không ?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Đồng ý !',
            cancelButtonText: 'Huỷ !'
        }).then((result) => {
            if (result.isConfirmed) {
                $scope.userPayment();
                centerAlert('Thành công !', 'Đơn hàng của bạn đã được đặt thành công !', 'success')
                window.location.href = '#!/trang-chu'
            }
        })
    };

    // lấy ra các thông tin người đặt hàng, thông tin đơn hàng
    $scope.userPayment = function () {
        let productCartDto = {
            cartsList: [], productsList: []
        };

        for (let i = 0; i < $scope.object_cart.length; i++) {
            productCartDto.cartsList.push($scope.object_cart[i][0]);
            productCartDto.productsList.push($scope.object_cart[i][1]);
        }

        $scope.user_payment = {
            fullname: $scope.user.fullname,
            phoneNumber: $scope.user.phoneNumber,
            provinceName: $scope.user.provinceName.ProvinceName,
            districtName: $scope.user.districtName.DistrictName,
            wardName: $scope.user.wardName.WardName,
            address: $scope.user.address,
        };

        const data = {
            user_payment: $scope.user_payment,
            productCartDto: productCartDto,
            discountId: $scope.discount_code,
            serviceName: $scope.serviceName.short_name,
            shippingFee: $scope.shippingFee,
            total: $scope.total,
            noted: $scope.noted,
            paymentMethod: $scope.paymentMethod
        };

        $scope.createOrder(data);
    };

    // kiểm tra mã giảm giá
    $scope.checkDiscount = function () {
        let discountId = $scope.discount_code;
        let discountCost = $scope.discount;

        if (discountId.trim().length > 0 && discountCost !== 0) {
            DiscountService.decreaseQuantity(discountId);
        }
    }

    // tạo đơn lưu vào db
    $scope.createOrder = function (data) {
        $scope.checkDiscount();
        OrderService.createOrder(data);
    }
});