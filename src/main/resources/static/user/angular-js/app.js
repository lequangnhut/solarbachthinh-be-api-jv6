let API_Template = 'http://localhost:8080/page/';

let solar_app = angular.module('solar_app', ['ngRoute']);

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
        .when('/san-pham', {
            templateUrl: API_Template + 'product/product.html',
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
        .when('/dang-nhap', {
            templateUrl: API_Template + 'auth/login.html',
            controller: ''
        })
        .when('/dang-ky', {
            templateUrl: API_Template + 'auth/sign-up.html',
            controller: ''
        })
        .when('/gio-hang', {
            templateUrl: API_Template + 'cart/cart.html',
            controller: ''
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
        .when('/lich-su-mua-hang', {
            templateUrl: API_Template + 'history/history-payment.html',
            controller: ''
        })
        .otherwise({
            redirectTo: '/trang-chu'
        })
});