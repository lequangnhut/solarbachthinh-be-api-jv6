solar_app.controller('product', function ($scope, $http, $timeout, CategoryService, ProductService) {

    $scope.formatPrice = function (price) {
        return new Intl.NumberFormat('vi-VN', {currency: 'VND'}).format(price);
    };

    // Hàm khởi tạo Swiper
    function initializeSwiper(selector, options) {
        // Tạo một instance Swiper mới và thêm vào mảng 'swipers' nằm dưới đái xã hội á
        let swiper = new Swiper(selector, options);
        swipers.push(swiper);
    }

    // Hàm lấy danh sách sản phẩm cho một danh mục và khởi tạo Swiper
    function fetchProductsAndInitializeSwiper(category) {
        return ProductService.showProductByCategory(category.id).then(
            function successCallback(response) {
                // gán dl vào products
                let products = response.data;

                // nạp BrandName cho từng sản phẩm
                let brandPromises = products.map(product => {
                    return ProductService.showBrandNameByProductBrandId(product.productBrandId)
                        .then(function successCallback(response) {
                            // Gắn BrandName cho từng sản phẩm
                            product.BrandName = response.data.brandName;
                        });
                });

                // Chờ tất cả brandPromises hoàn thành trước khi tiếp tục
                return Promise.all(brandPromises).then(function () {
                    // Gán dữ liệu sản phẩm vào scope
                    $scope.categories_productsType_products = products;

                    // Khởi tạo Swiper cho danh mục hiện tại
                    initializeSwiper("#product_light", {
                        slidesPerView: 3, spaceBetween: 30, autoplay: {
                            delay: 3000, disableOnInteraction: false,
                        }, navigation: {
                            nextEl: ".swiper-button-next", prevEl: ".swiper-button-prev",
                        }, breakpoints: {
                            0: {
                                slidesPerView: 1,
                            }, 600: {
                                slidesPerView: 1, spaceBetween: 10,
                            }, 1000: {
                                slidesPerView: 2, spaceBetween: 20,
                            }, 1300: {
                                slidesPerView: 3, spaceBetween: 30,
                            },
                        },
                    });
                });
            },
            function errorCallback(response) {
                console.log(response.data);
            }
        );
    }

    // Lấy danh sách tất cả các danh mục và khởi tạo Swiper cho mỗi danh mục
    CategoryService.findAllCategory().then(
        function successCallback(response) {
            // gán dl cho scope để hiện bên html nè
            $scope.categories = response.data;

            // Lấy và khởi tạo Swiper cho mỗi danh mục nè
            let categoryPromises = $scope.categories.map(category => fetchProductsAndInitializeSwiper(category));

            // Chờ tất cả categoryPromises hoàn thành trước khi tiếp tục, chờ đợi mới hạnh phúc
            Promise.all(categoryPromises).then(function () {
                // Tất cả Swipers đã được khởi tạo
            });
        },
        function errorCallback(response) {
            console.log(response.data);
        }
    );

    let swipers = []; // Mảng để lưu trữ các instance Swiper

});
