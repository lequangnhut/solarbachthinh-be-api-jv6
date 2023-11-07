let API_Cart = 'http://localhost:8080/api/carts';

solar_app.controller('cart_controller', function ($scope, $http, $timeout) {

    $scope.carts = [];
    $scope.quantity = 1;

    $http({
        method: 'GET',
        url: API_Cart
    }).then(function successCallback(response) {
        $scope.carts = response.data;
        $scope.quantity = $scope.carts.quantity;
    }, function errorCallback(response) {
        console.log(response.data);
    });

    // trừ số lượng
    $scope.decrease_quantity = function () {
        if ($scope.quantity > 1) {
            $scope.quantity--;
        }
    }

    // cộng số lượng
    $scope.increase_quantity = function () {
        $scope.quantity++;
    }

    $scope.deleteCart = function (cartId) {
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
                    url: API_Cart + '/delete/' + cartId
                }).then(function successCallback(response) {
                    window.location.href = "/gio-hang";
                }, function errorCallback(response) {
                    console.log(response.data);
                });
            }
        })
    }
});