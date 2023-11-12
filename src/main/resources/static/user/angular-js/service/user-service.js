solar_app.service('UserService', function ($http) {
    this.findUserBySession = function () {
        return $http({
            method: 'GET',
            url: API_UserSession
        })
    };

    // Update thông tin người dùng
    this.updateUser = function (userId ,userData) {
        return $http({
            method: 'PUT',
            url: API_User + '/' + userId,
            data: userData
        });
    };

    this.checkExistPhoneProfile = function (phone) {
        return $http({
            method: 'GET',
            url: API_ExistPhoneProfile + phone,
            param: 'phoneNumber' + phone
        })
    }

    this.checkCorrectCurrentPassSV = function (currentPass){
        return $http({
            method: 'GET',
            url: API_CorrectCurrentPass,
            param: 'phoneNumber' + phone
        })
    }

    this.changePass = function (userId, currentPass, newPass) {
        return $http({
            method: 'PUT',
            url: API_ChangePassword + userId,
            params: {
                currentPass: currentPass,
                newPass: newPass
            }
        })
    };

});