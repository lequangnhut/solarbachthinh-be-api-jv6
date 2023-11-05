function toastAlert(type, message) {
    const Toast = Swal.mixin({
        toast: true,
        position: 'top-end',
        showConfirmButton: false,
        timer: 3000,
        timerProgressBar: true,
        didOpen: (toast) => {
            toast.addEventListener('mouseenter', Swal.stopTimer)
            toast.addEventListener('mouseleave', Swal.resumeTimer)
        }
    })

    Toast.fire({
        icon: type, title: message
    })
}

function centerAlert(text, message, type) {
    Swal.fire(
        text,
        message,
        type
    )
}

function confirmAlert(text1, message1, type1, text2, message2, type2) {
    Swal.fire({
        title: text1,
        text: message1,
        icon: type1,
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Đồng ý !',
        cancelButtonText: 'Huỷ !'
    }).then((result) => {
        if (result.isConfirmed) {
            Swal.fire(
                text2,
                message2,
                type2
            )
        }
    })
}