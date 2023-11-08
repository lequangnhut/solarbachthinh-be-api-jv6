solar_app.controller('product_details', function ($scope, $http, $rootScope, $location, UserService, ProductService, CategoryService, CartService) {

    $scope.formatPrice = function (price) {
        return new Intl.NumberFormat('vi-VN', {currency: 'VND'}).format(price);
    };

    $scope.quantity = 1;
    $scope.max_quantity = 1;

    let params = $location.search();
    let productId = $scope.productId = params['ma-san-pham'];

    // thêm vào giỏ hàng
    $scope.addToCart = function (quantity) {
        sendDataToController(productId, quantity);
    }

    // lấy ra session user đang đăng nhập
    UserService.findUserBySession()
        .then(function successCallback(response) {
            $scope.session_user = response.data;
        }, function errorCallback(response) {
            console.log(response.data);
        });

    // lấy ra danh sách danh mục
    CategoryService.findAllCategory()
        .then(function successCallback(response) {
            $scope.categories = response.data;
        }, function errorCallback(response) {
            console.log(response.data);
        });

    // lấy ra product bằng mã
    ProductService.findProductByProductId(productId)
        .then(function successCallback(response) {
            $scope.product = response.data;
        }, function errorCallback(response) {
            console.log(response.data);
        });

    // lấy ra product nổi bật
    ProductService.findProductByIdOne()
        .then(function successCallback(response) {
            $scope.products = response.data;
            $scope.max_quantity = $scope.product.quantity;
        }, function errorCallback(response) {
            console.log(response.data);
        });

    // trừ số lượng
    $scope.decrease_quantity = function () {
        if ($scope.quantity > 1) {
            $scope.quantity--;
        }
    }

    // kiểm tra số lượng input
    $scope.check_quantity_input = function (quantity) {
        if (quantity > $scope.max_quantity) {
            $scope.quantity = $scope.max_quantity;
        }
    };

    // cộng số lượng
    $scope.increase_quantity = function () {
        if ($scope.quantity < $scope.product.quantity) {
            $scope.quantity++;
        }
    }

    // thêm vào giỏ hàng
    function sendDataToController(productId, quantity) {
        CartService.addProductToCart(productId, quantity)
            .then(function successCallback() {
                toastAlert('success', 'Thêm vào giỏ hàng thành công!');
                $rootScope.sum_quantity_cart();
                window.location.href = '#!/gio-hang'
            }, function errorCallback(response) {
                console.log(response.data)
            });
    }
});