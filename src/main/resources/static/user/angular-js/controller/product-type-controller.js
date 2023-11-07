solar_app.controller('product_type', function ($scope, $http, $timeout, $location) {

    var params = $location.search();
    $scope.categoryId = params['danh-muc'];

    // lấy ra danh sách danh mục
    $http({
        method: 'GET',
        url: API_ProductCategory
    }).then(function successCallback(response) {
        $scope.categories = response.data;
    }, function errorCallback(response) {
        console.log(response.data);
    });

    // lấy ra danh sách sản phẩm bằng category id
    $http({
        method: 'GET',
        url: API_Product + '/' + $scope.categoryId
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
        if (brandId != null) {
            $http({
                method: 'GET',
                url: API_ProductBrand + '/' + brandId
            }).then(function successCallback(response) {
                $scope.productBrand = response.data;
                $scope.isLoading = false;
            }, function errorCallback(response) {
                console.log(response.data);
                $scope.isLoading = false;
            });
        } else {
            $scope.isLoading = false;
        }
    }
});