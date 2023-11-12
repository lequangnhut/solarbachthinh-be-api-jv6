solar_app.controller('cart_controller', function ($scope, $http, $rootScope, $timeout, CartService) {

    $scope.formatPrice = function (price) {
        return new Intl.NumberFormat('vi-VN', {currency: 'VND'}).format(price);
    };

    // lấy ra object giỏ hàng sp, thương hiệu, img'
    CartService.findAllCart()
        .then(function successCallback(response) {
            $scope.object_cart = response.data;
            $scope.calculate_total();
        }, function errorCallback(response) {
            console.log(response.data);
        });

    // trừ số lượng
    $scope.decrease_quantity = function (cartItem) {
        if (cartItem.quantity > 1) {
            cartItem.quantity--;
            updateCartItemInDB(cartItem);
            $scope.calculate_total();
        } else {
            $scope.delete_cart(cartItem.id);
        }
    }

    // kiểm tra số lượng trong input
    $scope.check_quantity = function (cartItem) {
        console.log(cartItem)
        // if (cartItem.quantity > $scope.object_cart[1].quantity) {
        //     cartItem.quantity = $scope.object_cart[1].quantity;
        // }
    }

    // cộng số lượng
    $scope.increase_quantity = function (cartItem) {
        let product = $scope.object_cart[0];

        if (cartItem.quantity < product[1].quantity) {
            cartItem.quantity++;
            updateCartItemInDB(cartItem);
            $scope.calculate_total();
        }
    }

    // tính tổng tiền giỏ hàng
    $scope.calculate_total = function () {
        let subtotal = 0;

        for (let i = 0; i < $scope.object_cart.length; i++) {
            subtotal += $scope.object_cart[i][0].quantity * $scope.object_cart[i][1].price;
        }

        let discount = 0; // Giảm giá (nếu có)
        let shippingFee = 0; // Phí vận chuyển (nếu có)

        let total = subtotal - discount + shippingFee;

        $scope.subtotal = subtotal;
        $scope.discount = discount;
        $scope.shippingFee = shippingFee;
        $scope.total = total;
    }

    // cập nhật số giỏ hàng lượng trong db
    function updateCartItemInDB(cartItem) {
        CartService.updateQuantityCart(cartItem);
    }

    // tổng số lượng sp trong giỏ hàng
    $rootScope.sum_quantity_cart = function () {
        CartService.sumQuantityCart().then(function successCallback(response) {
            $rootScope.quantity_cart = response.data;
        }, function errorCallback(response) {
            console.log(response.data);
        });
    }

    // xoá sản phẩm trong giỏ hàng
    $scope.delete_cart = function (cartId) {
        Swal.fire({
            title: 'Cảnh báo ?',
            text: "Bạn có chắc chắn muốn xoá sản phẩm ra khỏi giỏ hàng không ?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Đồng ý !',
            cancelButtonText: 'Huỷ !'
        }).then((result) => {
            if (result.isConfirmed) {
                $http({
                    method: 'GET',
                    url: API_Cart + '/xoa-gio-hang/' + cartId
                }).then(function successCallback() {
                    for (let i = 0; i < $scope.object_cart.length; i++) {
                        if ($scope.object_cart[0].id !== cartId) {
                            $scope.object_cart.splice(i, 1);
                            break;
                        }
                    }

                    $rootScope.sum_quantity_cart();
                    $scope.calculate_total();
                    $scope.sum_quantity_cart();

                    $timeout(function () {
                        centerAlert('Thành công !', 'Xoá sản phẩm ra khỏi giỏ hàng thành công !', 'success');
                    });
                }, function errorCallback(response) {
                    console.log(response.data);
                });
            }
        })
    }
});