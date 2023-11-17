// Form đ ổi mật khẩu
const passwordField1 = document.getElementById('newPass');
const passwordField2 = document.getElementById('confirmPass');
const passwordField0 = document.getElementById('currentPass');
const togglePasswordIcon1 = document.getElementById('togglePassword1');
const togglePasswordIcon2 = document.getElementById('togglePassword2');
const togglePasswordIcon0 = document.getElementById('togglePassword0');

togglePasswordIcon1.addEventListener('click', function () {
    if (passwordField1.type === 'password') {
        passwordField1.type = 'text'; // Hiển thị mật khẩu
        togglePasswordIcon1.innerHTML = '<i class="fa-solid fa-eye"></i>'; // Đổi biểu tượng thành mở khóa
    } else {
        passwordField1.type = 'password'; // Ẩn mật khẩu
        togglePasswordIcon1.innerHTML = '<i class="fa-solid fa-eye-slash"></i>'; // Đổi biểu tượng thành khóa
    }
});

togglePasswordIcon2.addEventListener('click', function () {
    if (passwordField2.type === 'password') {
        passwordField2.type = 'text'; // Hiển thị mật khẩu
        togglePasswordIcon2.innerHTML = '<i class="fa-solid fa-eye"></i>'; // Đổi biểu tượng thành mở khóa
    } else {
        passwordField2.type = 'password'; // Ẩn mật khẩu
        togglePasswordIcon2.innerHTML = '<i class="fa-solid fa-eye-slash"></i>'; // Đổi biểu tượng thành khóa
    }
});

togglePasswordIcon0.addEventListener('click', function () {
    if (passwordField0.type === 'password') {
        passwordField0.type = 'text'; // Hiển thị mật khẩu
        togglePasswordIcon0.innerHTML = '<i class="fa-solid fa-eye"></i>'; // Đổi biểu tượng thành mở khóa
    } else {
        passwordField0.type = 'password'; // Ẩn mật khẩu
        togglePasswordIcon0.innerHTML = '<i class="fa-solid fa-eye-slash"></i>'; // Đổi biểu tượng thành khóa
    }
});

