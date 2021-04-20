function genRole() {


    $.ajax({
        url: '/roles',
        type: 'GET',
        contentType: 'application/json', //dinh nghia kieu du lieu gui ve server
        dataType: 'json', //dinh nghi kieu du lieu server gui len

        success: function (result) { // result la ket qua server tra ve
            console.log(result);
            var data = result.data;
            var leng = data.length;
            console.log(leng);
            var gererated = "<select style='padding: 10px' class=\"form-control\" id=\"role\" name=\"role\"> \n";
            for (var i = 0; i < leng; i++) {
                gererated += "<option value=" + data[i].id + ">" + data[i].name + "</option>\n";
            }
            gererated += "</select>";

            $('#roles').append(gererated);

        },
    });
}
