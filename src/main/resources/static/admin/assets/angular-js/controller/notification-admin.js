solar_app_admin.controller('NotificationAdmin', function ($scope, $http) {

    $scope.formatPrice = function (price) {
        return new Intl.NumberFormat('vi-VN', {currency: 'VND'}).format(price);
    };

    $http({
        method: 'GET',
        url: API_Notification + '/findAll'
    }).then(function successCallback(response) {
        $scope.showNotification = false;
        $scope.allNotification = response.data;

        for (let i = 0; i < $scope.allNotification.length; i++) {
            if ($scope.allNotification[i].isSeen === true) {
                $scope.showNotification = true;
                break;
            }
        }
    });

    $scope.seenNotification = function (id, orderId) {
        $http({
            method: 'PUT',
            url: API_Notification + '/updateIsSeen/' + id + '/' + false
        }).then(function successCallback() {
            $scope.loadData();
            $('#modal-info-order-data').modal('show');
            $scope.getInfoOrder(orderId);
        });
    }

    $scope.deleteNotification = function (id) {
        $http({
            method: 'GET',
            url: API_Notification + '/deleteNoted/' + id
        }).then(function successCallback() {
            $scope.loadData();
        });
    }

    $scope.loadData = function () {
        $http({
            method: 'GET',
            url: API_Notification + '/findAll'
        }).then(function successCallback(response) {
            $scope.allNotification = response.data;
        });
    }

    $scope.getInfoOrder = function (orderId) {
        let combinedData = [];
        let totalAmount = 0;
        let shippingFee = 0;
        let subTotal = 0;
        let discountId;

        $http({
            method: 'GET',
            url: API_findByOrderId + '/' + orderId
        }).then(function successCallback(response) {
            discountId = response.data.discountId;
            $scope.order = response.data;

            $scope.getStatusText = function () {
                let paymentStatus = $scope.order.paymentStatus
                switch (paymentStatus) {
                    case 0:
                        return 'Chưa thanh toán';
                    case 1:
                        return 'Đã thanh toán';
                    case 2:
                        return 'Thanh toán thất bại';
                    default:
                        return 'Trạng thái không xác định';
                }
            };

            if (discountId !== null) {
                $http({
                    method: 'GET',
                    url: '/api/discount/' + discountId
                }).then(function successCallback(response) {
                    $scope.discountCost = response.data.discountCost;
                });
            } else {
                $scope.discountCost = 0;
            }

            $http({
                method: 'GET',
                url: API_findOrderItemByOrderId + '/' + $scope.order.id
            }).then(function successCallback(response) {
                let listOrderItemAndProduct = $scope.order_items = response.data;

                for (let i = 0; i < listOrderItemAndProduct.length; i++) {
                    let data = {
                        product: listOrderItemAndProduct[i][1],
                        orderItem: listOrderItemAndProduct[i][0]
                    };
                    combinedData.push(data);

                    // tạm tính
                    subTotal += (data.orderItem.quantity * data.orderItem.price);
                    shippingFee = $scope.order.orderShipCost;
                    // Tính tổng tiền dựa trên số lượng và giá của orderItem
                    totalAmount = subTotal - $scope.discountCost + shippingFee;
                }

                $scope.combinedData = combinedData;
                $scope.subTotal = subTotal;
                $scope.shippingFee = shippingFee;
                $scope.totalAmount = totalAmount;
            });
        });

        $scope.$watch('discountId', function (newDiscountId, oldDiscountId) {
            if (newDiscountId !== oldDiscountId) {
                if (newDiscountId !== null) {
                    $http({
                        method: 'GET',
                        url: '/api/discount/' + newDiscountId
                    }).then(function successCallback(response) {
                        $scope.discountCost = response.data.discountCost;
                    });
                } else {
                    $scope.discountCost = 0;
                }
            }
        });
    };
});