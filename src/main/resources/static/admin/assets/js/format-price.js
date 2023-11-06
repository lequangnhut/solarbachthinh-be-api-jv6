//format giá tiền khi nhập vào
function formatPrice(input) {
    let rawValue = input.value.replace(/[^0-9]/g, '');

    let price = parseInt(rawValue);

    if (!isNaN(price)) {
        let formattedPrice = price.toLocaleString('vi-VN');
        input.value = formattedPrice;
    }
}