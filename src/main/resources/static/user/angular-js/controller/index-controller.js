solar_app.controller('index_controller', function ($scope, $http, $timeout, CategoryService, ProductService) {

    $scope.formatPrice = function (price) {
        return new Intl.NumberFormat('vi-VN', {currency: 'VND'}).format(price);
    };

    // lấy ra danh sách sản phẩm
    ProductService.findProductByIdOne()
        .then(function successCallback(response) {
            $scope.products = response.data;
        }, function errorCallback(response) {
            console.log(response.data);
        });

    // lấy ra danh sách top 4 thể loại
    CategoryService.findCategoryTop4()
        .then(function successCallback(response) {
            $scope.categories = response.data;
        }, function errorCallback(response) {
            console.log(response.data);
        });

    // Khởi tạo Swiper trong $timeout để đảm bảo nó được gọi sau khi dữ liệu đã được load
    $timeout(function () {
        const mySwiper = new Swiper('.mySwiper', {
            slidesPerView: 2, grid: {
                rows: 2,
            }, spaceBetween: 30, pagination: {
                el: ".swiper-pagination", clickable: true,
            }, breakpoints: {
                0: {
                    slidesPerView: 1, grid: {
                        rows: 2,
                    },
                }, 600: {
                    slidesPerView: 1, grid: {
                        rows: 2,
                    },
                }, 1000: {
                    slidesPerView: 2, grid: {
                        rows: 2,
                    }, spaceBetween: 30,
                },
            },
        });

        loadSwiperProduct(1);

        $scope.isLoading = false;

        $scope.changes = function (categoryId) {
            $scope.isLoading = true;

            $timeout(function () {
                loadSwiperProduct(categoryId);
            }, 1500);
        }

        function loadSwiperProduct(categoryId) {
            ProductService.findProductByCategoryId(categoryId)
                .then(function successCallback(response) {
                    $scope.object_product = response.data;

                    // Khi dữ liệu đã được load, hãy cập nhật Swiper
                    $timeout(function () {
                        mySwiper.update();
                    });
                    $scope.isLoading = false;
                }, function errorCallback(response) {
                    console.log(response.data);
                });
        }
    });
});