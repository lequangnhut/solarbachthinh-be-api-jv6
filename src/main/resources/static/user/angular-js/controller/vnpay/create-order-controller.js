solar_app.controller('create_order_controller', function ($scope, $location, $routeParams, UserService, DiscountService, OrderService) {

    $scope.formatPriceVNPAY = function (price) {
        let formattedPrice = price / 100;
        return new Intl.NumberFormat('vi-VN', {style: 'currency', currency: 'VND'}).format(formattedPrice);
    };

    // thông tin của vnpay trả về
    $scope.orderInfo = $routeParams.orderInfo;
    $scope.paymentTime = $routeParams.paymentTime;
    $scope.totalPrice = $routeParams.totalPrice;
    $scope.bankCode = $routeParams.bankCode;
    $scope.transactionId = $routeParams.transactionId;

    // lấy ra các thông tin người đặt hàng, thông tin đơn hàng
    $scope.userPayment = function () {
        let productCartDto = {
            cartsList: [],
            productsList: []
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
            orderId: $scope.orderInfo,
            user_payment: $scope.user_payment,
            productCartDto: productCartDto,
            discountId: $scope.discountId,
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