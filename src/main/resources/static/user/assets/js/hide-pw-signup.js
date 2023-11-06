const passwordSignup = document.getElementById('passwordSignup');
const passwordConfirm = document.getElementById('passwordConfirm');
const togglePasswordSignup = document.getElementById('togglePasswordSignup');
const togglePasswordConfirm = document.getElementById('togglePasswordConfirm');

togglePasswordSignup.addEventListener('click', function () {
    if (passwordSignup.type === 'password') {
        passwordSignup.type = 'text'; // Hiển thị mật khẩu
        togglePasswordSignup.innerHTML = '<i class="fa-solid fa-eye"></i>'; // Đổi biểu tượng thành mở khóa
    } else {
        passwordSignup.type = 'password'; // Ẩn mật khẩu
        togglePasswordSignup.innerHTML = '<i class="fa-solid fa-eye-slash"></i>'; // Đổi biểu tượng thành khóa
    }
});

togglePasswordConfirm.addEventListener('click', function () {
    if (passwordConfirm.type === 'password') {
        passwordConfirm.type = 'text'; // Hiển thị mật khẩu
        togglePasswordConfirm.innerHTML = '<i class="fa-solid fa-eye"></i>'; // Đổi biểu tượng thành mở khóa
    } else {
        passwordConfirm.type = 'password'; // Ẩn mật khẩu
        togglePasswordConfirm.innerHTML = '<i class="fa-solid fa-eye-slash"></i>'; // Đổi biểu tượng thành khóa
    }
});