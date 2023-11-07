solar_app.controller('product', function ($scope, $http) {

    $scope.formatPrice = function (price) {
        return new Intl.NumberFormat('vi-VN', {currency: 'VND'}).format(price);
    };

    // lấy ra danh sách danh mục
    $http({
        method: 'GET',
        url: API_ProductCategory
    }).then(function successCallback(response) {
        $scope.categories = response.data;
    }, function errorCallback(response) {
        console.log(response.data);
    });
});