solar_app.controller('product_type', function ($scope, $http, $timeout, $location, ProductService, ProductTypeService, CategoryService) {

    const params = $location.search();
    $scope.categoryId = params['danh-muc'];

    // lấy ra danh sách danh mục
    CategoryService.findAllCategory()
        .then(function successCallback(response) {
            $scope.categories = response.data;
        }, function errorCallback(response) {
            console.log(response.data);
        });

    // lấy ra danh sách sản phẩm bằng category id
    ProductService.findProductByCategoryId($scope.categoryId)
        .then(function successCallback(response) {
            $scope.products = response.data;
        }, function errorCallback(response) {
            console.log(response.data);
        });

    // lấy ra product type đầu tiên khi vào product type
    ProductTypeService.findProductTypeByCategoryId(1)
        .then(function successCallback(response) {
            $scope.product_type = response.data;
        }, function errorCallback(response) {
            console.log(response.data);
        });

    $scope.isLoading = false;

    $scope.changes = function (categoryId) {
        $scope.isLoading = true;
        $scope.categoryId = categoryId;

        ProductTypeService.findProductTypeByCategoryId(categoryId)
            .then(function successCallback(response) {
                $scope.product_type = response.data;
            }, function errorCallback(response) {
                console.log(response.data);
            });

        $timeout(function () {
            ProductService.findProductByCategoryId(categoryId)
                .then(function successCallback(response) {
                    $scope.products = response.data;
                    $scope.isLoading = false;
                }, function errorCallback(response) {
                    console.log(response.data);
                });
        }, 1500);
    }
});