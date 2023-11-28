document.addEventListener('DOMContentLoaded', function () {
    const emailInput = document.getElementById("email-edit-account");
    const fullNameInput = document.getElementById("fullName-edit-account");
    const phoneInput = document.getElementById("phone-edit-account");
    const passwordInput = document.getElementById("password-edit-account");
    const birthInput = document.getElementById("birth-edit-account");
    const cityInput = document.getElementById('city');
    const districtInput = document.getElementById('province');
    const wardInput = document.getElementById('ward');
    const addressInput = document.getElementById('address-edit-account');

    const errorEmail = document.getElementById('invalid-email');
    const errorFull_name = document.getElementById('invalid-full-name');
    const errorPhone = document.getElementById('invalid-phone');
    const errorPassword = document.getElementById('invalid-password');
    const errorBirth = document.getElementById('invalid-birth-account');
    const errorCity = document.getElementById('invalid-city');
    const errorDistrict = document.getElementById('invalid-province');
    const errorWard = document.getElementById('invalid-ward');
    const errorAddress = document.getElementById('invalid-address');

    const submittedButton = document.getElementById('submitted-button');

    let accountSubmitted = false;

    var currentURL = window.location.href;

    if (currentURL.indexOf("tai-khoan/sua-tai-khoan/") !== -1) {

        function checkInputsAccountEdit() {
            const fields = [emailInput, fullNameInput, phoneInput, birthInput, cityInput, districtInput, wardInput, addressInput];
            for (let i = 0; i < fields.length; i++) {
                if (fields[i].value.trim() === '') {
                    submittedButton.disabled = true;
                    return;
                }
            }
            submittedButton.disabled = accountSubmitted;
        }

        checkInputsAccountEdit();

        emailInput.addEventListener('blur', function () {
            const emailValue = emailInput.value.trim();
            const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

            if (emailValue === '') {
                emailInput.classList.remove('is-valid');
                emailInput.classList.add('is-invalid');
                errorEmail.textContent = 'Vui lòng nhập địa chỉ email.';
            } else if (!emailRegex.test(emailValue)) {
                emailInput.classList.remove('is-valid');
                emailInput.classList.add('is-invalid');
                errorEmail.textContent = 'Địa chỉ email không hợp lệ.';
            } else {
                emailInput.classList.remove('is-invalid');
                emailInput.classList.add('is-valid');
                errorEmail.textContent = '';
            }
            checkInputsAccountEdit();
        });

        fullNameInput.addEventListener('blur', function () {
            const fullNameValue = fullNameInput.value.trim();
            const fullNameRegex = /^[\p{L}'][\p{L}'\s-]{0,}$/u;

            if (fullNameValue === '') {
                fullNameInput.classList.remove('is-valid');
                fullNameInput.classList.add('is-invalid');
                errorFull_name.textContent = 'Vui lòng nhập họ và tên.';
            } else if (!fullNameRegex.test(fullNameValue)) {
                fullNameInput.classList.remove('is-valid');
                fullNameInput.classList.add('is-invalid');
                errorFull_name.textContent = 'Họ và tên chỉ được nhập chữ cái.';
            } else {
                fullNameInput.classList.remove('is-invalid');
                fullNameInput.classList.add('is-valid');
                errorFull_name.textContent = '';
            }
            checkInputsAccountEdit();
        });

        phoneInput.addEventListener('blur', function () {
            const phoneValue = phoneInput.value.trim();
            const phoneRegex = /^[0-9]{10}$/;

            if (phoneValue === '') {
                phoneInput.classList.remove('is-valid');
                phoneInput.classList.add('is-invalid');
                errorPhone.textContent = 'Vui lòng nhập số điện thoại.';
            } else if (!phoneRegex.test(phoneValue)) {
                phoneInput.classList.remove('is-valid');
                phoneInput.classList.add('is-invalid');
                errorPhone.textContent = 'Số điện thoại không hợp lệ.';
            } else {
                phoneInput.classList.remove('is-invalid');
                phoneInput.classList.add('is-valid');
                errorPhone.textContent = '';
            }
            checkInputsAccountEdit();
        });

        birthInput.addEventListener('blur', function () {
            const value = birthInput.value.trim();

            if (value === '') {
                birthInput.classList.remove('is-valid');
                birthInput.classList.add('is-invalid');
                errorBirth.textContent = 'Vui lòng nhập ngày đăng ký !';
            } else {
                if (/^\d{4}-\d{2}-\d{2}$/.test(value)) {
                    // Kiểm tra xem ngày nhập vào có đúng định dạng 'YYYY-MM-DD' không

                    const birthDate = new Date(value);
                    const currentDate = new Date();

                    if (birthDate > currentDate) {
                        birthInput.classList.remove('is-valid');
                        birthInput.classList.add('is-invalid');
                        errorBirth.textContent = 'Ngày sinh phải trước ngày hiện tại.';
                    } else {
                        birthInput.classList.remove('is-invalid');
                        birthInput.classList.add('is-valid');
                        errorBirth.textContent = '';
                    }
                } else {
                    birthInput.classList.remove('is-valid');
                    birthInput.classList.add('is-invalid');
                    errorBirth.textContent = 'Ngày sinh không đúng định dạng.';
                }
            }
            checkInputsAccountEdit();
        });

        cityInput.addEventListener('blur', function () {
            const value = cityInput.value.trim();

            if (value === '') {
                cityInput.classList.remove('is-valid');
                cityInput.classList.add('is-invalid');
                errorCity.textContent = 'Vui lòng chọn tỉnh';
            } else {
                cityInput.classList.remove('is-invalid');
                cityInput.classList.add('is-valid');
                errorCity.textContent = '';
            }
            checkInputsAccountEdit();
        });

        districtInput.addEventListener('blur', function () {
            const value = districtInput.value.trim();

            if (value === '') {
                districtInput.classList.remove('is-valid');
                districtInput.classList.add('is-invalid');
                errorDistrict.textContent = 'Vui lòng chọn huyện';
            } else {
                districtInput.classList.remove('is-invalid');
                districtInput.classList.add('is-valid');
                errorDistrict.textContent = '';
            }
            checkInputsAccountEdit();
        });

        wardInput.addEventListener('blur', function () {
            const value = wardInput.value.trim();

            if (value === '') {
                wardInput.classList.remove('is-valid');
                wardInput.classList.add('is-invalid');
                errorWard.textContent = 'Vui lòng chọn ấp';
            } else {
                wardInput.classList.remove('is-invalid');
                wardInput.classList.add('is-valid');
                errorWard.textContent = '';
            }
            checkInputsAccountEdit();
        });

        addressInput.addEventListener('blur', function () {
            const value = addressInput.value.trim();

            if (value === '') {
                addressInput.classList.remove('is-valid');
                addressInput.classList.add('is-invalid');
                errorAddress.textContent = 'Vui lòng nhập địa chỉ';
            } else {
                addressInput.classList.remove('is-invalid');
                addressInput.classList.add('is-valid');
                errorAddress.textContent = '';
            }
            checkInputsAccountEdit();
        });

        // Xử lý sự kiện click nút gửi (kiểm tra đã gửi hay chưa)

        submittedButton.addEventListener('click', function (event) {
            if (accountSubmitted) {
                event.preventDefault(); // Ngăn form gửi đi nếu đã gửi rồi
            } else {
                const form = document.getElementById('form-product');
                form.submit();
                accountSubmitted = true;
                checkInputsAccountEdit();
            }
        });

        // Gán giá trị lưu trữ tương ứng từ Local Storage cho mỗi input (nếu tồn tại)

        const genderInput = document.getElementById('gender-edit-account');
        const activeInput = document.getElementById('edit-account-type');

        // emailInput.value = localStorage.getItem('email') || '';
        // fullNameInput.value = localStorage.getItem('fullName') || '';
        // phoneInput.value = localStorage.getItem('phone') || '';
        // birthInput.value = localStorage.getItem('birth') || '';
        // addressInput.value = localStorage.getItem('address') || '';
        //
        // // Kiểm tra xem Local Storage có giá trị đã lưu cho trường select không
        // const savedGenderValue = localStorage.getItem('gender');
        // const savedActiveInput = localStorage.getItem('active');

// Nếu có giá trị đã lưu, đặt giá trị cho trường select
//         if (savedGenderValue !== null) {
//             genderInput.value = savedGenderValue;
//         }
//
//         if (savedActiveInput !== null) {
//             activeInput.value = savedActiveInput;
//         }
//
// // Lắng nghe sự kiện onchange cho mỗi input
//         emailInput.addEventListener('change', function () {
//             // Lưu giá trị email vào Local Storage
//             localStorage.setItem('email', emailInput.value);
//         });
//
//         fullNameInput.addEventListener('change', function () {
//             // Lưu giá trị full name vào Local Storage
//             localStorage.setItem('fullName', fullNameInput.value);
//         });
//
//         phoneInput.addEventListener('change', function () {
//             // Lưu giá trị số điện thoại vào Local Storage
//             localStorage.setItem('phone', phoneInput.value);
//         });
//
//         birthInput.addEventListener('change', function () {
//             // Lưu giá trị ngày sinh vào Local Storage
//             localStorage.setItem('birth', birthInput.value);
//         });
//
//         cityInput.addEventListener('change', function () {
//             // Lưu giá trị tỉnh vào Local Storage
//             localStorage.setItem('city', cityInput.value);
//         });
//
//         districtInput.addEventListener('change', function () {
//             // Lưu giá trị huyện vào Local Storage
//             console.log('Local Storage')
//             console.log(districtInput.value)
//             localStorage.setItem('district', districtInput.value);
//         });
//
//         wardInput.addEventListener('change', function () {
//             // Lưu giá trị ấp vào Local Storage
//             localStorage.setItem('ward', wardInput.value);
//         });
//
//         addressInput.addEventListener('change', function () {
//             // Lưu giá trị địa chỉ vào Local Storage
//             localStorage.setItem('address', addressInput.value);
//         });
//
//         // Lắng nghe sự kiện onchange cho từng select
//         genderInput.addEventListener('change', function () {
//             localStorage.setItem('gender', genderInput.value);
//         });
//
//         activeInput.addEventListener('change', function () {
//             // Lưu giá trị active vào Local Storage
//             localStorage.setItem('active', activeInput.value);
//         });

    } else {
        function checkInputsAccountAdd() {
            const fields = [emailInput, fullNameInput, phoneInput, passwordInput, birthInput, cityInput, districtInput, wardInput, addressInput];
            for (let i = 0; i < fields.length; i++) {
                if (fields[i].value.trim() === '') {
                    submittedButton.disabled = true;
                    return;
                }
            }
            submittedButton.disabled = accountSubmitted;
        }

        checkInputsAccountAdd();

        emailInput.addEventListener('blur', function () {
            const emailValue = emailInput.value.trim();
            const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

            if (emailValue === '') {
                emailInput.classList.remove('is-valid');
                emailInput.classList.add('is-invalid');
                errorEmail.textContent = 'Vui lòng nhập địa chỉ email.';
            } else if (!emailRegex.test(emailValue)) {
                emailInput.classList.remove('is-valid');
                emailInput.classList.add('is-invalid');
                errorEmail.textContent = 'Địa chỉ email không hợp lệ.';
            } else {
                checkDuplicateEmail(emailValue);
            }
            checkInputsAccountAdd();
        });

        fullNameInput.addEventListener('blur', function () {
            const fullNameValue = fullNameInput.value.trim();
            const fullNameRegex = /^[\p{L}'][\p{L}'\s-]{0,}$/u;

            if (fullNameValue === '') {
                fullNameInput.classList.remove('is-valid');
                fullNameInput.classList.add('is-invalid');
                errorFull_name.textContent = 'Vui lòng nhập họ và tên.';
            } else if (!fullNameRegex.test(fullNameValue)) {
                fullNameInput.classList.remove('is-valid');
                fullNameInput.classList.add('is-invalid');
                errorFull_name.textContent = 'Họ và tên chỉ được nhập chữ cái.';
            } else {
                fullNameInput.classList.remove('is-invalid');
                fullNameInput.classList.add('is-valid');
                errorFull_name.textContent = '';
            }
            checkInputsAccountAdd();
        });

        phoneInput.addEventListener('blur', function () {
            const phoneValue = phoneInput.value.trim();
            const phoneRegex = /^[0-9]{10}$/;

            if (phoneValue === '') {
                phoneInput.classList.remove('is-valid');
                phoneInput.classList.add('is-invalid');
                errorPhone.textContent = 'Vui lòng nhập số điện thoại.';
            } else if (!phoneRegex.test(phoneValue)) {
                phoneInput.classList.remove('is-valid');
                phoneInput.classList.add('is-invalid');
                errorPhone.textContent = 'Số điện thoại không hợp lệ.';
            } else {
                checkDuplicatePhoneNumber(phoneValue);
            }
            checkInputsAccountAdd();
        });
        passwordInput.addEventListener('blur', function () {
            const passwordValue = passwordInput.value.trim();

            if (passwordValue === '' || passwordValue.length < 6) {
                passwordInput.classList.remove('is-valid');
                passwordInput.classList.add('is-invalid');
                errorPassword.textContent = 'Mật khẩu phải có ít nhất 6 kí tự.';
            } else {
                passwordInput.classList.remove('is-invalid');
                passwordInput.classList.add('is-valid');
                errorPassword.textContent = '';
            }
            checkInputsAccountAdd();
        });

        birthInput.addEventListener('blur', function () {
            const value = birthInput.value.trim();

            if (value === '') {
                birthInput.classList.remove('is-valid');
                birthInput.classList.add('is-invalid');
                errorBirth.textContent = 'Vui lòng nhập ngày đăng ký !';
            } else {
                if (/^\d{4}-\d{2}-\d{2}$/.test(value)) {
                    // Kiểm tra xem ngày nhập vào có đúng định dạng 'YYYY-MM-DD' không

                    const birthDate = new Date(value);
                    const currentDate = new Date();

                    if (birthDate > currentDate) {
                        birthInput.classList.remove('is-valid');
                        birthInput.classList.add('is-invalid');
                        errorBirth.textContent = 'Ngày sinh phải trước ngày hiện tại.';
                    } else {
                        birthInput.classList.remove('is-invalid');
                        birthInput.classList.add('is-valid');
                        errorBirth.textContent = '';
                    }
                } else {
                    birthInput.classList.remove('is-valid');
                    birthInput.classList.add('is-invalid');
                    errorBirth.textContent = 'Ngày sinh không đúng định dạng.';
                }
            }
            checkInputsAccountAdd();
        });

        cityInput.addEventListener('blur', function () {
            const value = cityInput.value.trim();

            if (value === '') {
                cityInput.classList.remove('is-valid');
                cityInput.classList.add('is-invalid');
                errorCity.textContent = 'Vui lòng chọn tỉnh';
            } else {
                cityInput.classList.remove('is-invalid');
                cityInput.classList.add('is-valid');
                errorCity.textContent = '';
            }
            checkInputsAccountAdd();
        });

        districtInput.addEventListener('blur', function () {
            const value = districtInput.value.trim();

            if (value === '') {
                districtInput.classList.remove('is-valid');
                districtInput.classList.add('is-invalid');
                errorDistrict.textContent = 'Vui lòng chọn huyện';
            } else {
                districtInput.classList.remove('is-invalid');
                districtInput.classList.add('is-valid');
                errorDistrict.textContent = '';
            }
            checkInputsAccountAdd();
        });

        wardInput.addEventListener('blur', function () {
            const value = wardInput.value.trim();

            if (value === '') {
                wardInput.classList.remove('is-valid');
                wardInput.classList.add('is-invalid');
                errorWard.textContent = 'Vui lòng chọn ấp';
            } else {
                wardInput.classList.remove('is-invalid');
                wardInput.classList.add('is-valid');
                errorWard.textContent = '';
            }
            checkInputsAccountAdd();
        });

        addressInput.addEventListener('blur', function () {
            const value = addressInput.value.trim();

            if (value === '') {
                addressInput.classList.remove('is-valid');
                addressInput.classList.add('is-invalid');
                errorAddress.textContent = 'Vui lòng nhập địa chỉ';
            } else {
                addressInput.classList.remove('is-invalid');
                addressInput.classList.add('is-valid');
                errorAddress.textContent = '';
            }
            checkInputsAccountAdd();
        });

        // Xử lý sự kiện click nút gửi (kiểm tra đã gửi hay chưa)

        submittedButton.addEventListener('click', function (event) {
            if (accountSubmitted) {
                event.preventDefault(); // Ngăn form gửi đi nếu đã gửi rồi
            } else {
                const form = document.getElementById('form-product');
                form.submit();
                accountSubmitted = true;
                checkInputsAccountAdd();
            }
        });

        // Hàm kiểm tra trùng lặp Email
        function checkDuplicateEmail(email) {

            const xhr = new XMLHttpRequest();
            const url = '/api/check-duplicate-email/' + email;

            xhr.open('GET', url, true);
            xhr.onload = function () {
                if (xhr.status === 200) {
                    const response = JSON.parse(xhr.responseText);
                    if (response.exists) {
                        // Email đã tồn tại
                        emailInput.classList.remove('is-valid');
                        emailInput.classList.add('is-invalid');
                        errorEmail.textContent = 'Email đã tồn tại !';
                    } else {
                        emailInput.classList.remove('is-invalid');
                        emailInput.classList.add('is-valid');
                        errorEmail.textContent = '';
                    }
                }
            };
            xhr.send();
        }

// Hàm kiểm tra trùng lặp Số điện thoại
        function checkDuplicatePhoneNumber(phoneNumber) {

            const xhr = new XMLHttpRequest();
            const url = '/api/check-duplicate-phone/' + phoneNumber;

            xhr.open('GET', url, true);
            xhr.onload = function () {
                if (xhr.status === 200) {
                    const response = JSON.parse(xhr.responseText);
                    if (response.exists) {
                        // Số điện thoại đã tồn tại
                        phoneInput.classList.remove('is-valid');
                        phoneInput.classList.add('is-invalid');
                        errorPhone.textContent = 'Số điện thoại đã tồn tại !';
                    } else {
                        phoneInput.classList.remove('is-invalid');
                        phoneInput.classList.add('is-valid');
                        errorPhone.textContent = '';
                    }
                }
            };
            xhr.send();
        }

        // submittedButton.addEventListener('click', function (event) {
        //     event.preventDefault();
        //
        //     if (accountSubmitted) {
        //         return;
        //     }
        //
        //     Swal.fire({
        //         title: 'Cảnh báo ?',
        //         text: "Bạn có chắc chắn muốn cập nhật không ?",
        //         icon: 'warning',
        //         showCancelButton: true,
        //         confirmButtonColor: '#3085d6',
        //         cancelButtonColor: '#d33',
        //         confirmButtonText: 'Đồng ý !'
        //     }).then((result) => {
        //         if (result.isConfirmed) {
        //             const form = document.getElementById('edit-form');
        //             form.submit();
        //             accountSubmitted = true;
        //             checkInputsAccountAdd();
        //         }
        //     });
        // });

        // Gán giá trị lưu trữ tương ứng từ Local Storage cho mỗi input (nếu tồn tại)

//         const genderInput = document.getElementById('gender-edit-account');
//         const activeInput = document.getElementById('edit-account-type');
//
//         emailInput.value = localStorage.getItem('email') || '';
//         fullNameInput.value = localStorage.getItem('fullName') || '';
//         phoneInput.value = localStorage.getItem('phone') || '';
//         passwordInput.value = localStorage.getItem('password') || '';
//         birthInput.value = localStorage.getItem('birth') || '';
//         addressInput.value = localStorage.getItem('address') || '';
//
//         // Kiểm tra xem Local Storage có giá trị đã lưu cho trường select không
//         const savedGenderValue = localStorage.getItem('gender');
//         const savedActiveInput = localStorage.getItem('active');
//
// // Nếu có giá trị đã lưu, đặt giá trị cho trường select
//         if (savedGenderValue !== null) {
//             genderInput.value = savedGenderValue;
//         }
//
//         if (savedActiveInput !== null) {
//             activeInput.value = savedActiveInput;
//         }
//
// // Lắng nghe sự kiện onchange cho mỗi input
//         emailInput.addEventListener('change', function () {
//             // Lưu giá trị email vào Local Storage
//             localStorage.setItem('email', emailInput.value);
//         });
//
//         fullNameInput.addEventListener('change', function () {
//             // Lưu giá trị full name vào Local Storage
//             localStorage.setItem('fullName', fullNameInput.value);
//         });
//
//         phoneInput.addEventListener('change', function () {
//             // Lưu giá trị số điện thoại vào Local Storage
//             localStorage.setItem('phone', phoneInput.value);
//         });
//         passwordInput.addEventListener('change', function () {
//             // Lưu giá trị mật khẩu vào Local Storage
//             localStorage.setItem('password', passwordInput.value);
//         });
//
//         birthInput.addEventListener('change', function () {
//             // Lưu giá trị ngày sinh vào Local Storage
//             localStorage.setItem('birth', birthInput.value);
//         });
//
//         cityInput.addEventListener('change', function () {
//             // Lưu giá trị tỉnh vào Local Storage
//             localStorage.setItem('city', cityInput.value);
//         });
//
//         districtInput.addEventListener('change', function () {
//             // Lưu giá trị huyện vào Local Storage
//             console.log('Local Storage')
//             console.log(districtInput.value)
//             localStorage.setItem('district', districtInput.value);
//         });
//
//         wardInput.addEventListener('change', function () {
//             // Lưu giá trị ấp vào Local Storage
//             localStorage.setItem('ward', wardInput.value);
//         });
//
//         addressInput.addEventListener('change', function () {
//             // Lưu giá trị địa chỉ vào Local Storage
//             localStorage.setItem('address', addressInput.value);
//         });
//
//         // Lắng nghe sự kiện onchange cho từng select
//         genderInput.addEventListener('change', function () {
//             localStorage.setItem('gender', genderInput.value);
//         });
//
//         activeInput.addEventListener('change', function () {
//             // Lưu giá trị active vào Local Storage
//             localStorage.setItem('active', activeInput.value);
//         });
//
//         const resetButton = document.getElementById('reset-button');
//
//         if (resetButton !== null) {
//             resetButton.addEventListener('click', function () {
//                 // Xóa giá trị của các trường input
//                 emailInput.value = '';
//                 fullNameInput.value = '';
//                 phoneInput.value = '';
//                 passwordInput.value = '';
//                 birthInput.value = '';
//                 addressInput.value = '';
//             });
//         }

        // function clearLocalStorage() {
        //     localStorage.removeItem('email');
        //     localStorage.removeItem('fullName');
        //     localStorage.removeItem('phone');
        //     localStorage.removeItem('password');
        //     localStorage.removeItem('birth');
        //     localStorage.removeItem('city');
        //     localStorage.removeItem('district');
        //     localStorage.removeItem('ward');
        //     localStorage.removeItem('address');
        //     localStorage.removeItem('gender');
        //     localStorage.removeItem('active');
        // }
        //
        // fetch('/success-message')
        //     .then(data => {
        //         if (data.exists === true) {
        //             // successMessage là true, thực hiện các hàSh động tương ứng ở đây
        //             console.log('successMessage is true');
        //             clearLocalStorage();
        //             emailInput.value = '';
        //             fullNameInput.value = '';
        //             phoneInput.value = '';
        //             passwordInput.value = '';
        //             birthInput.value = '';
        //             addressInput.value = '';
        //         } else {
        //             // successMessage là false hoặc không được xác định, thực hiện các hành động khác ở đây
        //             console.log('successMessage is false or undefined');
        //         }
        //     })
    }
});


