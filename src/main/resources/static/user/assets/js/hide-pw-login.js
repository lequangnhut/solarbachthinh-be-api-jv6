const togglePasswordLogin = document.getElementById('togglePasswordLogin');
const passwordLogin = document.getElementById('passwordLogin');

togglePasswordLogin.addEventListener('click', function () {
    if (passwordLogin.type === 'password') {
        passwordLogin.type = 'text'; // Hiển thị mật khẩu
        togglePasswordLogin.innerHTML = '<i class="fa-solid fa-eye"></i>'; // Đổi biểu tượng thành mở khóa
    } else {
        passwordLogin.type = 'password'; // Ẩn mật khẩu
        togglePasswordLogin.innerHTML = '<i class="fa-solid fa-eye-slash"></i>'; // Đổi biểu tượng thành khóa
    }
});