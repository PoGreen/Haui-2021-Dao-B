function genAccount() {
    $.ajax({
        url: '/users',
        type: 'GET',
        contentType: 'application/json', //dinh nghia kieu du lieu gui ve server
        dataType: 'JSON', //dinh nghi kieu du lieu server gui len
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem("token")
        },
        success: function (result) { // result la ket qua server tra ve
            console.log(result);
            console.log(result.data);
            result = result.data;
            $('#user_name').val(result.user_name);
            $('#full_name').val(result.full_name);
            $('#phone').val(result.phone);
            $('#email').val(result.email);
            $('#address').val(result.address);
            $('#id').val(result.id);

            $('#user_name').prop("disabled", true);
            $('#full_name').prop("disabled", true);
            $('#phone').prop("disabled", true);
            $('#email').prop("disabled", true);
            $('#address').prop("disabled", true);
        },
    });
}

function submitChangeInfo() {

    if (validate()) {
        var data = $('#create-admins').serializeArray();
        $.ajax({
            url: 'users',
            type: 'PUT',
            contentType: 'application/json', //dinh nghia kieu du lieu gui ve server
            dataType: 'JSON', //dinh nghi kieu du lieu server gui len
            data: mapToJson(data),
            headers: {
                Authorization: 'Bearer ' + document.cookie
            },
            success: function (result) { // result la ket qua server tra ve
                window.location = "/home";
            },
            error: function (result) {
                alert("Đã xảy ra lỗi! vui lòng thử lại");
            }
        });
    }
}

function mapToJson(data) {
    var o = {};
    $.each(data, function () {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });

    return JSON.stringify(o);
}


function validate() {
    var user_name = document.getElementById("user_name").value;
    var password = document.getElementById("password").value;
    var password2 = document.getElementById("password-2").value;
    var full_name = document.getElementById("full_name").value;
    var phone = document.getElementById("phone").value;
    var email = document.getElementById("email").value;
    var wards = document.getElementById("wards").value;
    var address = document.getElementById("address").value;


    if (user_name === "" || user_name.length > 50 || user_name < 0) {
        alert("Vui lòng nhập đăng nhập đúng định dạng (0-50 ký tự)");
        return false;
    }

    if (password === "" || password.length > 20 || password < 0) {
        alert("Vui lòng nhập mật khẩu đúng định dạng (0-20 ký tự)");
        return false;
    }

    if (password2 !== password) {
        alert("Vui lòng nhập đúng mật khẩu");
        return false;
    }

    if (full_name === "" || full_name.length > 50 || full_name < 0) {
        alert("Vui lòng nhập đăng tên đầy đủ định dạng dữ liệu (0-50 ký tự)");
        return false;
    }

    if (phone.match(/^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$/g) === null) {
        alert("Vui lòng nhập đúng định dạng số điện thoại");
        return false;
    }


    if (!email.match(/(\W|^)[\w.+\-]*@gmail\.com(\W|$)/g)) {
        alert("Vui lòng nhập đăng nhập đúng định gmail");
        return false;
    }
    if (address === "" || address.length > 250 || address < 0) {
        alert("Vui lòng nhập địa chỉ đúng định dạng (0-2501 ký tự)");
        return false;
    }
    return true;
}