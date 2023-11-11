solar_app.service('AuthService', function ($http) {
    this.loginAuth = function (email, password) {
        return $http({
            method: 'POST',
            url: '/dang-nhap',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            data: 'username=' + email + '&password=' + password
        })
    };

    this.creatAuth = function (authData) {
        return $http({
            method: 'POST',
            url: API_User + '/create-auth',
            data: authData
        })
    };

    this.findUserByEmail = function (email) {
        return $http({
            method: 'GET',
            url: API_User + '/find-by-email/' + email
        })
    };

    this.checkExistEmail = function (email) {
        return $http({
            method: 'GET',
            url: API_ExistEmail + email,
            param: 'email' + email
        })
    }

    this.checkExistPhone = function (phone) {
        return $http({
            method: 'GET',
            url: API_ExistPhone + phone,
            param: 'phoneNumber' + phone
        })
    }
});
