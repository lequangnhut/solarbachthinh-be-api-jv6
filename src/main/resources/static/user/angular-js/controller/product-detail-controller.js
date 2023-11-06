let APIAddToCart = '/san-pham/them-vao-gio-hang/';

let product_details = angular.module('product_details', []);

product_details.controller('product_details', function ($scope, $http, $timeout) {

    $scope.formatPrice = function (price) {
        return new Intl.NumberFormat('vi-VN', {currency: 'VND'}).format(price);
    };

    $scope.quantity = 1;

    // thêm vào giỏ hàng
    $scope.addToCart = function (quantity) {
        // Lấy tham số "ma-san-pham" từ URL
        let params = new URLSearchParams(window.location.search);
        let productId = params.get('ma-san-pham');

        sendDataToController(productId, quantity);
    }

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

    function sendDataToController(productId, quantity) {
        $http({
            method: 'POST',
            url: APIAddToCart + productId + '&' + quantity
        }).then(function successCallback(response) {
            window.location.href = 'http://localhost:8080/gio-hang';
        }, function errorCallback(response) {
            console.log(response.data)
        });
    }
});