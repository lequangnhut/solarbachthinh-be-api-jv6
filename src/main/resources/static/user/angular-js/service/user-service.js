solar_app.service('UserService', function ($http) {
    this.findUserBySession = function () {
        return $http({
            method: 'GET',
            url: API_UserSession
        })
    };
});