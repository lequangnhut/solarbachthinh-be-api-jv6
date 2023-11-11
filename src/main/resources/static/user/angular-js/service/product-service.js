solar_app.service('ProductService', function ($http) {
    this.findProductByIdOne = function () {
        return $http({
            method: 'GET',
            url: API_Product + '/' + 1
        })
    };

    this.findProductByProductId = function (productId) {
        return $http({
            method: 'GET',
            url: API_Product + '/find-by-id/' + productId
        })
    };

    this.findProductByCategoryId = function (categoryId) {
        return $http({
            method: 'GET',
            url: API_Product + '/' + categoryId
        })
    }

    this.findAllByProductId = function (productId) {
        return $http({
            method: 'GET',
            url: API_Product + '/findByProductId/' + productId
        })
    };
});
