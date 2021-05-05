function submitRegister() {
    var data = $('#create-admins').serializeArray();
    $.ajax({
        url: '/users/signup',
        type: 'POST',
        contentType: 'application/json', //dinh nghia kieu du lieu gui ve server
        dataType: 'JSON', //dinh nghi kieu du lieu server gui len
        data: mapToJson(data),
        headers: {
            Authorization: 'Bearer ' + document.cookie
        },
        success: function (result) { // result la ket qua server tra ve
            window.location = "/login";

        },
        error: function (result) {
            $('#alert').css('display', '')
        }
    });
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
