let API = 'http://localhost:8080/api/product';

let index_product = angular.module('products', []);

index_product.controller('change_product', function ($scope, $http, $timeout) {

    $scope.formatPrice = function (price) {
        return new Intl.NumberFormat('vi-VN', {currency: 'VND'}).format(price);
    };

    $http({
        method: 'GET',
        url: 'http://localhost:8080/api/product/get-top4-category'
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
            url: API + '/' + 1
        }).then(function successCallback(response) {
            $scope.products = response.data;

            // Khi dữ liệu đã được load, hãy cập nhật Swiper
            $timeout(function () {
                mySwiper.update();
            });
        }, function errorCallback(response) {
            console.log(response.data);
        });

        $scope.changes = function (categoryId) {
            $http({
                method: 'GET',
                url: API + '/' + categoryId
            }).then(function successCallback(response) {
                $scope.products = response.data;

                // Khi dữ liệu đã được load, hãy cập nhật Swiper
                $timeout(function () {
                    mySwiper.update();
                });
            }, function errorCallback(response) {
                console.log(response.data);
            });
        }
    });
});