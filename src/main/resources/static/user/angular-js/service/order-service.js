solar_app.service('OrderService', function ($http) {
    this.createOrder = function (data) {
        return $http({
            method: 'POST',
            url: API_Order + '/create-order',
            headers: {
                contentType: "application/json;charset=UTF-8",
                dataType: "json",
            },
            data: data
        })
    }
});