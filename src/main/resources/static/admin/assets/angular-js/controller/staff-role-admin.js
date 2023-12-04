solar_app_admin.controller('StaffRoleAdmin', function ($scope, $http, $location) {

    // Lưu trữ trạng thái ban đầu của các quyền
    $scope.originalRoles = {};

    $scope.selectedRoles = [];

    $http({
        method: 'GET',
        url: API_RoleStaff + '/findAllRoleStaff'
    }).then(function successCallback(response) {
        if (response.status === 200) {
            $scope.userRoleStaff = response.data;

            response.data.forEach(function (userStaff) {
                $scope.originalRoles[userStaff.id] = userStaff.roles.map(function (role) {
                    return role.nameRole;
                });
            });
        }
    });

    $scope.userHasRole = function (role, userStaff) {
        return userStaff.roles && userStaff.roles.some(function (r) {
            return r.nameRole === role;
        });
    };

    $scope.changeRole = function (role, userId) {
        if (!$scope.selectedRoles[userId]) {
            $scope.selectedRoles[userId] = $scope.originalRoles[userId].slice();
        }

        const roleIndex = $scope.selectedRoles[userId].indexOf(role);

        if (roleIndex === -1) {
            $scope.selectedRoles[userId].push(role);
        } else {
            $scope.selectedRoles[userId].splice(roleIndex, 1);
        }

        $scope.updateRoles(userId);
    };

    $scope.updateRoles = function (userId) {
        $http({
            method: 'PUT',
            url: API_RoleStaff + '/updateRole/' + userId,
            data: $scope.selectedRoles[userId],
            headers: {
                'Content-Type': 'application/json'
            }
        });
    };
});