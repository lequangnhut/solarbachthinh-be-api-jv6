solar_app.controller('product', function ($scope, CategoryService, ProductService) {

    let mySwiper;

    //sự kiện lướt của swiper
    function initializeSwiper() {
        mySwiper = new Swiper('#product_light', {
            slidesPerView: 3, spaceBetween: 30, autoplay: {
                delay: 3000, disableOnInteraction: false,
            }, navigation: {
                nextEl: ".swiper-button-next", prevEl: ".swiper-button-prev",
            }, breakpoints: {
                0: {slidesPerView: 1},
                600: {slidesPerView: 1, spaceBetween: 10},
                1000: {slidesPerView: 2, spaceBetween: 20},
                1300: {slidesPerView: 3, spaceBetween: 30},
            }, observer: true, observeParents: true, on: {
                observerUpdate: function () {
                    this.update();
                }
            },
        });
    }

    $scope.formatPrice = function (price) {
        return new Intl.NumberFormat('vi-VN', {currency: 'VND'}).format(price);
    };

    //start show category
    findAllCategory();

    function findAllCategory() {
        CategoryService.findAllCategory()
            .then(function successCallback(response) {
                $scope.categories = response.data;
                return updateAllCategoriesAndSwiper($scope.categories);
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

    function updateAllCategoriesAndSwiper(categories) {
        let updatePromises = categories.map(updateCategoryAndSwiper);
        return Promise.all(updatePromises)
            .then(function (updatedCategories) {
                initializeSwiper(); // Gọi initializeSwiper chỉ một lần sau khi tất cả danh mục đã được cập nhật
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

    //end show category

    //start selected
    $scope.onProductTypeChange = function (selectedProductTypeId, category) {
        if (selectedProductTypeId === '' || selectedProductTypeId === undefined || selectedProductTypeId === null) {
            ProductService.showProductByCategory(category.id)
                .then(response => {
                    let products = response.data;
                    let brandPromises = products.map(product => {
                        return ProductService.showBrandNameByProductBrandId(product.productBrandId)
                            .then(response => {
                                const brandName = response.data.brandName;
                                if (product.BrandName !== brandName) {
                                    product.BrandName = brandName;
                                    return product;
                                }
                            });
                    });

                    return Promise.all(brandPromises)
                        .then(updatedProducts => {
                            const productsChanged = updatedProducts.filter(product => product !== undefined);
                            if (productsChanged.length > 0) {
                                category.productsTypeProducts = productsChanged;
                                return category;
                            }
                        });
                })
                .catch(error => {
                    console.log(error.data);
                });
        } else {
            onProductChange(selectedProductTypeId, category);
        }
    };

    function onProductChange(selectedProductTypeId, category) {
        ProductService.findProductByProductType(selectedProductTypeId)
            .then(response => {
                let products = response.data;

                let brandPromises = products.map(product => {
                    return ProductService.showBrandNameByProductBrandId(product.productBrandId)
                        .then(response => {
                            const brandName = response.data.brandName;
                            if (product.BrandName !== brandName) {
                                product.BrandName = brandName;
                                return product;
                            }
                        });
                });

                return Promise.all(brandPromises)
                    .then(updatedProducts => {
                        const productsChanged = updatedProducts.filter(product => product !== undefined);
                        if (productsChanged.length > 0) {
                            category.productsTypeProducts = productsChanged;
                            return category;
                        }
                    });
            })
            .catch(error => {
                console.log(error.data);
            });
    }

    //end selected

    //start search
    $scope.searchProduct = function (productName) {
        if (productName !== '' && productName !== undefined && productName !== null) {
            $scope.categories.forEach(function (category) {
                onSearchProduct(productName, category);
            });
        } else {
            findAllCategory();
        }
    };

    function onSearchProduct(productName, category) {
        ProductService.findByProductName(category.id, productName)
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
            }, function errorCallback(response) {
                console.log(response.data);
            });
    }

    //end search

    ProductService.findAllProducts()
        .then(function successCallback(response) {
            // Lọc danh sách để chỉ giữ lại các giá trị duy nhất
            $scope.producsNameOptions = Array.from(new Set(response.data.map(product => product.productName)));
        });

});
