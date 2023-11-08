solar_app.service('DiscountService', function ($http) {
    this.findDiscount = function () {
        return $http({
            method: 'GET',
            url: API_Discount
        })
    };

    this.findDiscountByDiscountId = function (discount_code) {
        return $http({
            method: 'GET',
            url: API_Discount + '/' + discount_code
        })
    };
});
