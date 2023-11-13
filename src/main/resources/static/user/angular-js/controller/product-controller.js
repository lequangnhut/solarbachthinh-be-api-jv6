solar_app.controller('product', function ($scope, CategoryService, ProductService) {

    let mySwiper;

    function initializeSwiper() {
        mySwiper = new Swiper('#product_light', {
            slidesPerView: 3,
            spaceBetween: 30,
            autoplay: {
                delay: 3000,
                disableOnInteraction: false,
            },
            navigation: {
                nextEl: ".swiper-button-next",
                prevEl: ".swiper-button-prev",
            },
            breakpoints: {
                0: {slidesPerView: 1},
                600: {slidesPerView: 1, spaceBetween: 10},
                1000: {slidesPerView: 2, spaceBetween: 20},
                1300: {slidesPerView: 3, spaceBetween: 30},
            },
            observer: true,
            observeParents: true,
            on: {
                observerUpdate: function () {
                    this.update();
                }
            },
        });
    }

    function fetchProducts(category) {
        return ProductService.showProductByCategory(category.id)
            .then(function successCallback(response) {
                let products = response.data;

                let brandPromises = products.map(product => {
                    return ProductService.showBrandNameByProductBrandId(product.productBrandId)
                        .then(function successCallback(response) {
                            product.BrandName = response.data.brandName;
                        });
                });

                return Promise.all(brandPromises)
                    .then(function () {
                        category.productsTypeProducts = products;
                        return category;
                    });
            });
    }

    function updateCategoryAndSwiper(category) {
        return fetchProducts(category)
            .then(function (updatedCategory) {
                return updatedCategory; // Trả về category đã được cập nhật
            })
            .catch(function errorCallback(response) {
                console.log(response.data);
            });
    }

    function updateAllCategoriesAndSwiper(categories) {
        let updatePromises = categories.map(updateCategoryAndSwiper);
        return Promise.all(updatePromises)
            .then(function (updatedCategories) {
                initializeSwiper(); // Gọi initializeSwiper chỉ một lần sau khi tất cả danh mục đã được cập nhật
            });
    }

    function updateSwiper() {
        if (mySwiper) {
            mySwiper.update();
        }
    }

    function onProductChange(selectedProductTypeId, selectedCategory) {
        ProductService.findProductByProductType(selectedProductTypeId)
            .then(function successCallback(response) {
                let products = response.data;
                selectedCategory.productsTypeProducts = products;
                updateSwiper();
            }, function errorCallback(response) {
                console.log(response.data);
            });
    }

    function onSearchProduct(productName, category) {
        ProductService.findByProductName(category.id, productName)
            .then(function successCallback(response) {
                let products = response.data;
                category.productsTypeProducts = products;
                updateSwiper();
            }, function errorCallback(response) {
                console.log(response.data);
            });
    }

    CategoryService.findAllCategory()
        .then(function successCallback(response) {
            $scope.categories = response.data;
            return updateAllCategoriesAndSwiper($scope.categories);
        });

    $scope.formatPrice = function (price) {
        return new Intl.NumberFormat('vi-VN', {currency: 'VND'}).format(price);
    };

    $scope.onProductTypeChange = function (selectedProductTypeId) {
        let selectedCategory = $scope.categories.find(category =>
            category.productTypesById.some(productType => productType.id === parseInt(selectedProductTypeId))
        );

        if (selectedCategory) {
            onProductChange(selectedProductTypeId, selectedCategory);
        }
    };

    $scope.searchProduct = function (productName) {
        if (productName !== '' && productName !== undefined && productName !== null) {
            $scope.categories.forEach(function (category) {
                onSearchProduct(productName, category);
            });
        }
    };

});
