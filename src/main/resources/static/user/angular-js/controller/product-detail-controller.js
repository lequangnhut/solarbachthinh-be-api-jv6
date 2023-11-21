solar_app.controller('product_details', function ($scope, $http, $timeout, $location, $sce, $rootScope, UserService, ProductService, CategoryService, SaleOffService, CartService) {

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
    UserService.findUserBySession().then(function successCallback(response) {
        if (response.data) {
            $scope.session_user = response.data;
        }
    }, function errorCallback(response) {
        console.log(response.data);
    });

    // lấy ra danh sách danh mục
    CategoryService.findAllCategory().then(function successCallback(response) {
        $scope.categories = response.data;
        $scope.calculatorSaleOffProductPrice();
    }, function errorCallback(response) {
        console.log(response.data);
    });

    // lấy ra product bằng mã
    ProductService.findProductByProductId(productId).then(function successCallback(response) {
        $scope.product = response.data;
        $scope.trustedHtml = $sce.trustAsHtml($scope.product.templateDescription);
    }, function errorCallback(response) {
        console.log(response.data);
    });

    // lấy ra product nổi bật
    ProductService.findProductByIdOne().then(function successCallback(response) {
        $scope.products = response.data;
        $scope.max_quantity = $scope.product.quantity;
    }, function errorCallback(response) {
        console.log(response.data);
    });

    // Hàm kiểm tra xem giảm giá còn hiệu lực hay không
    $scope.isSaleOffValid = function (saleOff) {
        let currentDate = new Date();
        let saleStartDate = new Date(saleOff.startUse);
        let saleEndDate = new Date(saleOff.endUse);

        return currentDate >= saleStartDate && currentDate <= saleEndDate;
    };

    // lấy ra giá giảm sale chớp nhoáng
    $scope.calculatorSaleOffProductPrice = function () {
        SaleOffService.findByProductId(productId).then(function successCallback(response) {
            let saleOff = response.data;

            if (saleOff != null && saleOff.isActive && $scope.isSaleOffValid(saleOff)) {
                $scope.sale_off = saleOff;
            }
        });
    }

    // trừ số lượng
    $scope.decrease_quantity = function () {
        if ($scope.quantity > 1) {
            $scope.quantity--;
        }
    }

    // kiểm tra số lượng input
    $scope.check_quantity_input = function (quantity, product) {
        let quantityInDb = product.quantity;

        if (quantity <= quantityInDb) {

        } else {
            $scope.quantity = quantityInDb;
            centerAlert('Cảnh Báo !', 'Ôi hỏng rồi !!!, Bạn đã nhập quá số lượng giới hạn.', 'warning');
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
        CartService.addProductToCart(productId, quantity).then(function successCallback() {
            toastAlert('success', 'Thêm vào giỏ hàng thành công!');
            window.location.href = '#!/gio-hang';
            $rootScope.sum_quantity_cart();
        }, function errorCallback(response) {
            console.log(response.data)
        });
    }

    // lỗi tài nguyên tĩnh
    $timeout(function () {
        const thumbnailImages = document.querySelectorAll('.col-lg-3 img');

        thumbnailImages.forEach(function (thumbnail) {
            thumbnail.addEventListener('click', function () {
                const imagePath = this.src;

                const overlay = document.getElementById('overlay');
                const overlayImage = document.getElementById('overlay-image');

                overlayImage.src = imagePath;

                // Hiển thị overlay
                overlay.style.display = 'block';
            });
        });

        const overlay = document.getElementById('overlay');
        overlay.addEventListener('click', function () {
            this.style.display = 'none';
        });
    })
});