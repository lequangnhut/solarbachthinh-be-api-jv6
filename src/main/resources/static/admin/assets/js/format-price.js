//format giá tiền khi nhập vào
function formatPrice(input) {

    let rawValue = input.value.replace(/[^0-9]/g, '');

    let price = parseInt(rawValue);

    if (!isNaN(price)) {
        input.value = price.toLocaleString('en-US');
    }
}

// // Sử dụng sự kiện "input" để theo dõi thay đổi giá trị
// const priceInput = document.getElementById('price'); // Thay 'price' bằng ID của trường input của bạn
// priceInput.addEventListener('input', function () {
//     formatPrice(this); // "this" là trường input hiện tại
// });