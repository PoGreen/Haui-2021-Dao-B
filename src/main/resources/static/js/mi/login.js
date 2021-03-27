function subitLogin(){

    var data = $('#login').serializeArray();

    $.ajax({
        url: '/users/login',
        type: 'POST',
        contentType: 'application/json', //dinh nghia kieu du lieu gui ve server
        dataType: 'JSON', //dinh nghi kieu du lieu server gui len
        data: mapToJson(data),
        success: function (result) { // result la ket qua server tra ve

        document.cookie = result.data.token;
        if(result.data.role === 'USER') window.location = '/home';
        if(result.data.role === 'ADMIN') window.location = '/admin/home';
        },
        error: function (result){
            $('#alert').css('display','')
        }
    });
}

function mapToJson(data){
    var o ={};
    $.each(data, function() {
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
