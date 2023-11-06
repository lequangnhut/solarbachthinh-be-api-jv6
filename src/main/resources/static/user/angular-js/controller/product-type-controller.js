let API_Product = 'http://localhost:8080/api/product';
let API_ProductCategory = 'http://localhost:8080/api/product-category';
let API_ProductType = 'http://localhost:8080/api/product-type';

let product_type = angular.module('product_type', []);

product_type.controller('product_type', function ($scope, $http, $timeout) {

    let params = new URLSearchParams(window.location.search);
    $scope.categoryId = params.get('danh-muc');

    // lấy ra danh sách danh mục
    $http({
        method: 'GET',
        url: API_ProductCategory
    }).then(function successCallback(response) {
        $scope.categories = response.data;
    }, function errorCallback(response) {
        console.log(response.data);
    });

    // lấy ra danh sách sản phẩm
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

    }, function errorCallback(response) {
        console.log(response.data);
    });

    $scope.isLoading = false;

    $scope.changes = function (categoryId) {
        $scope.isLoading = true;
        $scope.categoryId = categoryId;

        $http({
            method: 'GET',
            url: API_ProductType + '/' + categoryId
        }).then(function successCallback(response) {
            $scope.product_type = response.data;
        }, function errorCallback(response) {
            console.log(response.data);
        });

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

            }, function errorCallback(response) {
                console.log(response.data);
            });
        }, 1500);
    }

    function getProductBrandByBrandId(brandId) {
        $http({
            method: 'GET',
            url: 'http://localhost:8080/api/product-brand/' + brandId
        }).then(function successCallback(response) {
            $scope.productBrand = response.data;
            $scope.isLoading = false;
        }, function errorCallback(response) {
            console.log(response.data);
            $scope.isLoading = false;
        });
    }
});