solar_app.service('SecurityService', function () {
    // Biến lưu trữ trạng thái đăng nhập
    let isAuthenticated = false;

    // Hàm để kiểm tra trạng thái đăng nhập
    this.isAuthenticated = function () {
        return isAuthenticated;
    };

    // Hàm để đăng nhập (thực hiện sau khi xác thực từ server)
    this.login = function () {
        isAuthenticated = true;
    };

    // Hàm để đăng xuất
    this.logout = function () {
        isAuthenticated = false;
    };
});


solar_app.service('AuthMiddleware', function ($location, SecurityService) {
    this.requireLogin = function () {
        if (!SecurityService.isAuthenticated()) {
            $location.path('/login');
        }
    };
});