let API_Template = '/page/';

// api user
let API_UserSession = '/api/user/session-user';

// api category
let API_GetTop4Category = '/api/product/get-top4-category'
let API_ProductCategory = '/api/product-category';

// api product
let API_Product = '/api/product';
let API_ProductType = '/api/product-type';

// api discount
let API_Discount = '/api/discount'

// api giỏ hàng
let API_Cart = '/api/carts';

// khởi tạo ứng dụng
let solar_app = angular.module('solar_app', ['ngRoute'])
    .run(function ($rootScope, $http) {
        // lấy ra tổng số lượng giỏ hàng
        $http({
            method: 'GET',
            url: API_Cart + '/quantity-cart'
        }).then(function successCallback(response) {
            $rootScope.quantity_cart = response.data;
        }, function errorCallback(response) {
            console.log(response.data);
        });
    });

solar_app.config(['$qProvider', function ($qProvider) {
    $qProvider.errorOnUnhandledRejections(false);
}]);

// router link
solar_app.config(function ($routeProvider) {
    $routeProvider
        .when('/trang-chu', {
            templateUrl: API_Template + 'home/home.html',
            controller: 'index_controller'
        })
        .when('/gioi-thieu', {
            templateUrl: API_Template + 'contact/about.html',
            controller: ''
        })
        .when('/hoa-luoi-on-grid', {
            templateUrl: API_Template + 'contact/on-grid.html',
            controller: ''
        })
        .when('/hoa-luoi-hybrid', {
            templateUrl: API_Template + 'contact/hybrid.html',
            controller: ''
        })
        .when('/ban-tin', {
            templateUrl: API_Template + 'contact/news.html',
            controller: ''
        })
        .when('/chinh-sach', {
            templateUrl: API_Template + 'contact/policy.html',
            controller: ''
        })
        .when('/lien-he', {
            templateUrl: API_Template + 'contact/contact.html',
            controller: ''
        })

        // product
        .when('/san-pham', {
            templateUrl: API_Template + 'product/product.html',
            controller: 'product'
        })
        .when('/san-pham/loai-danh-muc', {
            templateUrl: API_Template + 'product/product-type.html',
            controller: 'product_type'
        })
        .when('/san-pham/loai-danh-muc/san-pham-chi-tiet', {
            templateUrl: API_Template + 'product/product_details.html',
            controller: 'product_details'
        })

        // cart
        .when('/gio-hang', {
            templateUrl: API_Template + 'cart/cart.html',
            controller: 'cart_controller'
        })
        .when('/gio-hang/xac-nhan-thong-tin-don-hang', {
            templateUrl: API_Template + 'cart/check-details.html',
            controller: 'check-details-controller'
        })
        .when('/lich-su-mua-hang', {
            templateUrl: API_Template + 'history/history-payment.html',
            controller: ''
        })

        // auth
        .when('/dang-nhap', {
            templateUrl: API_Template + 'auth/login.html',
            controller: 'login_controller'
        })
        .when('/dang-ky', {
            templateUrl: API_Template + 'auth/sign-up.html',
            controller: 'login_controller'
        })
        .when('/thong-tin', {
            templateUrl: API_Template + 'profile/user-profile.html',
            controller: ''
        })
        .when('/doi-mat-khau', {
            templateUrl: API_Template + 'password/change-password.html',
            controller: ''
        })
        .when('/quen-mat-khau', {
            templateUrl: API_Template + 'password/forgot-password.html',
            controller: ''
        })
        .otherwise({
            redirectTo: '/trang-chu'
        })
});