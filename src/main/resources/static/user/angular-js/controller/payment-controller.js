solar_app.controller('payment_controller', function ($scope, $rootScope, OrderService, OrderCodeService, DiscountService) {

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
                $scope.processPayment();
            }
        })
    };

    // Trong AngularJS controller
    $scope.processPayment = function () {
        if ($scope.paymentMethod === 'COD') {
            $scope.userPayment();
            centerAlert('Thành công !', 'Đơn hàng của bạn đã được đặt thành công !', 'success');
            // Chuyển đến trang chủ
            window.location.href = '#!/trang-chu';

            // Xoá local mã giảm giá đi
            localStorage.removeItem('appliedDiscount');

            $rootScope.sum_quantity_cart();
        } else if ($scope.paymentMethod === 'TRANSFER') {
            // chuyển đến trang xác nhận thông tin để thanh toán
            $scope.shareData();
            window.location.href = '#!/gio-hang/xac-nhan-thong-tin-don-hang/thanh-toan?tong-tien=' + $scope.total;
        } else {
            console.log('Không có lựa chọn thanh toán nào được chọn');
        }
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
            orderId: OrderCodeService.generateOrderCode(),
            user_payment: $scope.user_payment,
            productCartDto: productCartDto,
            discountId: $scope.discountId,
            paymentStatus: 0,
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

    // lấy dữ liệu người dùng đặt hàng qua controller creat-order-controller để thanh toán vnpay
    $scope.shareData = function () {
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
            discountId: $scope.discountId,
            discountCost: $scope.discount,
            serviceName: $scope.serviceName.short_name,
            shippingFee: $scope.shippingFee,
            noted: $scope.noted,
            paymentMethod: $scope.paymentMethod
        };

        // Lưu dữ liệu vào Local Storage
        localStorage.setItem('sharedData', JSON.stringify(data));
    };
});