let API_Product = 'http://localhost:8080/API_Product/product';

solar_app.controller('index_controller', function ($scope, $http, $timeout) {

    $scope.formatPrice = function (price) {
        return new Intl.NumberFormat('vi-VN', {currency: 'VND'}).format(price);
    };

    // lấy ra danh sách sản phẩm
    $http({
        method: 'GET',
        url: API_Product + '/' + 1
    }).then(function successCallback(response) {
        $scope.products = response.data;
    }, function errorCallback(response) {
        console.log(response.data);
    });

    // lấy ra danh sách top 4 thể loại
    $http({
        method: 'GET',
        url: 'http://localhost:8080/API_Product/product/get-top4-category'
    }).then(function successCallback(response) {
        $scope.categories = response.data;
    }, function errorCallback(response) {
        console.log(response.data);
    });

    // Khởi tạo Swiper trong $timeout để đảm bảo nó được gọi sau khi dữ liệu đã được load
    $timeout(function () {
        var mySwiper = new Swiper('.mySwiper', {
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

        $http({
            method: 'GET',
            url: API_Product + '/' + 1
        }).then(function successCallback(response) {
            let brandId = null;
            const products = $scope.products = response.data;

            for (let i = 0; i < products.length; i++) {
                brandId = products[i].productBrandId;
            }

            getProductBrandByBrandId(brandId);

            // Khi dữ liệu đã được load, hãy cập nhật Swiper
            $timeout(function () {
                mySwiper.update();
            });
        }, function errorCallback(response) {
            console.log(response.data);
        });

        $scope.isLoading = false;

        $scope.changes = function (categoryId) {
            $scope.isLoading = true;

            $timeout(function () {
                $http({
                    method: 'GET',
                    url: API_Product + '/' + categoryId
                }).then(function successCallback(response) {
                    let brandId = null;
                    const products = $scope.products = response.data;

                    for (let i = 0; i < products.length; i++) {
                        brandId = products[i].productBrandId;
                    }

                    getProductBrandByBrandId(brandId);

                    // Khi dữ liệu đã được load, hãy cập nhật Swiper
                    $timeout(function () {
                        mySwiper.update();
                    });
                }, function errorCallback(response) {
                    console.log(response.data);
                });
            }, 1500);
        }

        function getProductBrandByBrandId(brandId) {
            $http({
                method: 'GET',
                url: 'http://localhost:8080/API_Product/product-brand/' + brandId
            }).then(function successCallback(response) {
                $scope.productBrand = response.data;
                $scope.isLoading = false;
            }, function errorCallback(response) {
                console.log(response.data);
                $scope.isLoading = false;
            });
        }
    });
});