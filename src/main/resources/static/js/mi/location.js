function generatedDistrict(id) {
    var view = document.getElementById("districts");

    $.ajax({

        url: '/locations/provinces/'+id+'/districts',
        type: 'GET',
        contentType: 'application/json', //dinh nghia kieu du lieu gui ve server
        dataType: 'json', //dinh nghi kieu du lieu server gui len

        success: function (result) { // result la ket qua server tra ve
            var data = result.data.districts;
            var leng = data.length;

            var gererated = "<select style='padding: 10px' class=\"form-control\" id=\"district\" name=\"district\" onchange=\"generatedWardByDistrict(this.form);\"> \n";
            for (var i = 0; i < leng; i++) {
                gererated += "<option value=" + data[i].id + ">" + data[i].name + "</option>\n";
            }
            gererated += "</select>";
            view.innerHTML = gererated;

            generatedWards(data[0].id)
        },
    });
}

function generatedProvince() {
    var view = document.getElementById("provinces");

    $.ajax({
        url: '/locations/provinces',
        type: 'GET',
        contentType: 'application/json', //dinh nghia kieu du lieu gui ve server
        dataType: 'JSON', //dinh nghi kieu du lieu server gui len

        success: function (result) { // result la ket qua server tra ve
            var data = result.data.provinces;
            var leng = data.length;
            // ide = result[0].id;
            var gererated = "<select style='padding: 10px' class=\"form-control\" id=\"province\" name=\"province\" onchange=\"generatedDistrictByProvince(this.form);\"> \n";
            for (var i = 0; i < leng; i++) {
                gererated += "<option value=" + data[i].id + ">" + data[i].name + "</option>\n";
            }
            gererated += "</select>";
            view.innerHTML = gererated;
            generatedDistrict(data[0].id)
        },
    });
}

function generatedWards(id) {
    var view = document.getElementById("wards");
    var district = {
        "id": id
    }
    $.ajax({

        url: '/locations/districts/'+id+'/wards',
        type: 'GET',
        contentType: 'application/json', //dinh nghia kieu du lieu gui ve server
        data: district, // chuyen tu javascriptObject sang json
        dataType: 'json', //dinh nghi kieu du lieu server gui len

        success: function (result) { // result la ket qua server tra ve

            var data = result.data.wards;
            var leng = data.length;
            var gererated = "<select style='padding: 10px' class=\"form-control\" id=\"ward\" name=\"ward\"> \n";
            for (var i = 0; i < leng; i++) {
                gererated += "<option value=" + data[i].id + ">" + data[i].name + "</option>\n";
            }
            gererated += "</select>";
            view.innerHTML = gererated;
        },
    });
}
function generatedDistrictByProvince(fn){
    var province =fn.province.value;
    generatedDistrict(province);
}

function generatedWardByDistrict(fn){
    var district =fn.district.value;
    generatedWards(district)
}
