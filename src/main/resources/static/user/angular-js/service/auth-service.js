solar_app.service('AuthService', function ($http) {
    this.loginAuth = function (email, password){
        return $http({
            method: 'POST',
            url: '/dang-nhap',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            data: 'username=' + email + '&password=' + password
        })
    };
});
