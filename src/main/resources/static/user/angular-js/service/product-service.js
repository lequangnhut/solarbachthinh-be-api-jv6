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
            url: API_Product + '/' + 'find-by-id/' + productId
        })
    };

    this.findProductByCategoryId = function (categoryId) {
        return $http({
            method: 'GET',
            url: API_Product + '/' + categoryId
        })
    }
    // this.showProductByProductTypeByCategory = function (categoryId) {
    //     return $http({
    //         method: 'GET',
    //         url: API_Product
    //     })
    // }

    this.showProductByCategory = function (categoryId) {
        return $http({
            method: 'GET',
            url: API_Product + 's/' + categoryId
        })
    }

    this.showBrandNameByProductBrandId = function (productId) {
        return $http({
            method: 'GET',
            url: API_Product + '/brandName/' + productId
        })
    }


});
