solar_app.controller('check-details-controller', function ($scope, $http, CartService, UserService, DiscountService) {

    $scope.formatPrice = function (price) {
        return new Intl.NumberFormat('vi-VN', {currency: 'VND'}).format(price);
    };

    $scope.discount_code = "";

    // lấy ra object giỏ hàng sp, thương hiệu, img
    CartService.findAllCart()
        .then(function successCallback(response) {
            let object_cart = $scope.object_cart = response.data;
            calculate_total(object_cart);
        }, function errorCallback(response) {
            console.log(response.data);
        });

    // lấy ra session user đang đăng nhập
    UserService.findUserBySession()
        .then(function successCallback(response) {
            $scope.user = response.data;
        }, function errorCallback(response) {
            console.log(response.data);
        });

    // lấy ra đối tượng mã giảm giá
    DiscountService.findDiscount()
        .then(function successCallback(response) {
            $scope.discounts_modal = response.data;
        }, function errorCallback(response) {
            console.log(response.data);
        });

    $scope.load_discount = function () {
        let date_time = new Date().toLocaleString();

        let discounts_modal = $scope.discounts_modal;
        let discount_code_input = $scope.discount_code.trim();

        let discount_code_db = null;

        for (let i = 0; i < discounts_modal.length; i++) {
            discount_code_db = discounts_modal[i];
        }

        if (discount_code_input === null || discount_code_input.length === 0) {
            toastAlert('warning', 'Vui lòng nhập mã giảm giá !');

        } else if (!discount_code_input.endsWith(discount_code_db.id)) {
            toastAlert('warning', 'Mã giảm giá không tồn tại !');

        } else if (discount_code_db.endUse < date_time && !discount_code_db.isActive) {
            toastAlert('warning', 'Mã giảm giá đã hết hạn !');

        } else {
            $scope.apply_discount(discount_code_db);
        }
    };

    $scope.apply_discount = function (discount_code_db) {
        DiscountService.findDiscountByDiscountId(discount_code_db.id)
            .then(function successCallback(response) {
                let discounts = response.data;

                centerAlert(
                    'Thành công !',
                    'Sử dụng mã giảm giá thành công và được giảm giá '
                    + $scope.formatPrice(discounts.discountCost) + ' ₫ !',
                    'success'
                )
                // tính lại tiền
                calculate_total($scope.object_cart, discounts);
            }, function errorCallback(response) {
                console.log(response.data);
            });
    }

    // tính tổng giá tiền và tạm tính
    function calculate_total(object_cart, discounts) {
        let subtotal = 0;
        let discount = 0;
        let shippingFee = 0;
        let total = 0;

        for (let i = 0; i < object_cart.length; i++) {
            subtotal += object_cart[i][0].quantity * object_cart[i][1].price;
        }

        if (discounts != null) {
            discount = discounts.discountCost;
            shippingFee = 0;

            total = subtotal - discount + shippingFee;
        } else {
            discount = 0;
            shippingFee = 0;

            total = subtotal - discount + shippingFee;
        }

        $scope.subtotal = subtotal;
        $scope.discount = discount;
        $scope.shippingFee = shippingFee;
        $scope.total = total;
    }
});
