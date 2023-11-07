solar_app.controller('product_details', function ($scope, $http, $location) {

    var params = $location.search();
    let productId = $scope.productId = params['ma-san-pham'];

    $scope.formatPrice = function (price) {
        return new Intl.NumberFormat('vi-VN', {currency: 'VND'}).format(price);
    };

    $scope.quantity = 1;

    // thêm vào giỏ hàng
    $scope.addToCart = function (quantity) {
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

    // lấy ra session user đang đăng nhập
    $http({
        method: 'GET', url: API_UserSession
    }).then(function successCallback(response) {
        $scope.session_user = response.data;
    }, function errorCallback(response) {
        console.log(response.data);
    });

    // lấy ra danh sách danh mục
    $http({
        method: 'GET', url: API_ProductCategory
    }).then(function successCallback(response) {
        $scope.categories = response.data;
    }, function errorCallback(response) {
        console.log(response.data);
    });

    // lấy ra product bằng mã
    $http({
        method: 'GET', url: API_Product + '/' + 'find-by-id/' + productId
    }).then(function successCallback(response) {
        $scope.product = response.data;
    }, function errorCallback(response) {
        console.log(response.data);
    });

    // lấy ra product nổi bật
    $http({
        method: 'GET', url: API_Product + '/' + 1
    }).then(function successCallback(response) {
        $scope.products = response.data;
    }, function errorCallback(response) {
        console.log(response.data);
    });

    // thêm vào giỏ hàng
    function sendDataToController(productId, quantity) {
        $http({
            method: 'POST',
            url: API_Cart + '/them-vao-gio-hang/' + productId + '&' + quantity
        }).then(function successCallback(response) {
            window.location.href = '#!/gio-hang'
        }, function errorCallback(response) {
            console.log(response.data)
        });
    }
});