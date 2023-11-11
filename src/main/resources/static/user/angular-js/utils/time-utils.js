solar_app.filter('timestampToDateString', function () {
    return function (timestamp) {
        // Tạo một đối tượng Date từ timestamp (đơn vị: giây)
        const date = new Date(timestamp * 1000); // Nhân 1000 để chuyển từ mili-giây thành giây

        // Lấy ngày, tháng, năm
        let day = date.getDate();
        let month = date.getMonth() + 1; // Tháng bắt đầu từ 0, cần cộng thêm 1
        let year = date.getFullYear();

        // Đảm bảo rằng ngày và tháng có 2 chữ số
        day = day < 10 ? '0' + day : day;
        month = month < 10 ? '0' + month : month;

        // Trả về chuỗi ngày/tháng/năm
        return day + '/' + month + '/' + year;
    };
});

solar_app.filter('calculatePreviousDate', function () {
    return function (timestamp, days) {
        if (!timestamp) {
            return null;
        }

        // Tạo một đối tượng Date từ timestamp (đơn vị: giây)
        const date = new Date(timestamp * 1000); // Nhân 1000 để chuyển từ mili-giây thành giây

        // Trừ đi số ngày cần tính
        date.setDate(date.getDate() - days);

        // Lấy ngày, tháng, năm
        let day = date.getDate();
        let month = date.getMonth() + 1; // Tháng bắt đầu từ 0, cần cộng thêm 1
        let year = date.getFullYear();

        // Đảm bảo rằng ngày và tháng có 2 chữ số
        day = day < 10 ? '0' + day : day;
        month = month < 10 ? '0' + month : month;

        // Trả về chuỗi ngày/tháng/năm
        return day + '/' + month + '/' + year;
    };
});