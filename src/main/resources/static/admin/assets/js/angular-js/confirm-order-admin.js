let confirm_order = angular.module('confirm_order', []);

let API_findAllOrder = '/quan-tri/api/findAllOrder';
let API_findByOrderId = '/quan-tri/api/findByOrderId'
let API_findOrderItemByOrderId = '/quan-tri/api/findOrderItemByOrderId';

let API_CancelOrder = '/quan-tri/api/cancelOrder'
let API_ConfirmOrder = '/quan-tri/api/confirmOrder'
let API_DeliveredOrder = '/quan-tri/api/deliveredOrder'

confirm_order.controller('ConfirmOrderAdmin', function ($scope, $http, $window) {
    $scope.activeTab = 'confirm';

    $scope.setActiveTab = function (tab) {
        $scope.activeTab = tab;
    };

    $scope.formatPrice = function (price) {
        return new Intl.NumberFormat('vi-VN', {currency: 'VND'}).format(price);
    };

    $http({
        method: 'GET',
        url: API_findAllOrder
    }).then(function successCallback(response) {
        $scope.history_order = response.data;
    });

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
                DiscountService.findDiscountByDiscountId(discountId).then(function successCallback(response) {
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
                        product: listOrderItemAndProduct[i][1], orderItem: listOrderItemAndProduct[i][0]
                    };
                    combinedData.push(data);

                    // tạm tính
                    subTotal += (data.orderItem.quantity * data.product.price);
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

        // Watch for changes in discountId and update discountCost accordingly
        $scope.$watch('discountId', function (newDiscountId, oldDiscountId) {
            if (newDiscountId !== oldDiscountId) {
                if (newDiscountId !== null) {
                    DiscountService.findDiscountByDiscountId(newDiscountId).then(function successCallback(response) {
                        $scope.discountCost = response.data.discountCost;
                    });
                } else {
                    // If there's no discount, set discountCost to 0
                    $scope.discountCost = 0;
                }
            }
        });
    };

    $scope.confirmOrderAdmin = function (orderId) {
        Swal.fire({
            title: 'Cảnh báo ?',
            text: "Bạn có chắc chắn muốn xác nhận đơn hàng " + orderId + " không ?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Đồng ý !',
            cancelButtonText: 'Huỷ !'
        }).then((result) => {
            if (result.isConfirmed) {
                $http({
                    method: 'GET',
                    url: API_ConfirmOrder + '/' + orderId
                }).then(function successCallback() {
                    centerAlert('Thành công !', 'Đơn hàng ' + orderId + ' đã được xác nhận thành công !', 'success');
                    window.location.href = '/quan-tri/xac-nhan-don-hang'
                });
            }
        })
    }

    $scope.deliveredOrderAdmin = function (orderId) {
        Swal.fire({
            title: 'Cảnh báo ?',
            text: "Bạn có chắc chắn muốn xác nhận đơn hàng " + orderId + " giao thành công không ?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Đồng ý !',
            cancelButtonText: 'Huỷ !'
        }).then((result) => {
            if (result.isConfirmed) {
                $http({
                    method: 'GET',
                    url: API_DeliveredOrder + '/' + orderId
                }).then(function successCallback() {
                    centerAlert('Thành công !', 'Đơn hàng ' + orderId + ' đã được xác nhận thành công !', 'success');
                    window.location.href = '/quan-tri/xac-nhan-don-hang'
                });
            }
        })
    }

    $scope.cancelOrderAdmin = function (orderId) {
        Swal.fire({
            title: 'Cảnh báo ?',
            text: "Bạn có chắc chắn muốn huỷ đơn hàng " + orderId + " không ?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Đồng ý !',
            cancelButtonText: 'Huỷ !'
        }).then((result) => {
            if (result.isConfirmed) {
                $http({
                    method: 'GET',
                    url: API_CancelOrder + '/' + orderId
                }).then(function successCallback() {
                    centerAlert('Thành công !', 'Đơn hàng ' + orderId + ' đã được huỷ thành công !', 'success');
                    window.location.href = '/quan-tri/xac-nhan-don-hang'
                });
            }
        });
    };
});