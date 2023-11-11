solar_app.controller('history_controller', function ($scope, UserService, OrderService, OrderItermService) {

    UserService.findUserBySession().then(function successCallback(response) {
        $scope.users = response.data;

        OrderService.findByUserId($scope.users.id).then(function successCallback(response) {
            $scope.orders = response.data;
        });
    });

    $scope.getInfoOrder = function (orderId) {
        OrderService.findByOrderId(orderId).then(function successCallback(response) {
            $scope.order = response.data;

            OrderItermService.findByOrderId($scope.order.id).then(function successCallback(response) {
                $scope.order_items = response.data;
                console.log($scope.order_items)
            });
        });
    }
});