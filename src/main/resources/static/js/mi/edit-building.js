function genBuildingDetail() {
    var data = sessionStorage.getItem("id-building");
    console.log(data);
    $.ajax({
        url: '/buildings/' + data,
        type: 'GET',
        contentType: 'application/json', //dinh nghia kieu du lieu gui ve server
        dataType: 'JSON', //dinh nghi kieu du lieu server gui len
        success: function (result) { // result la ket qua server tra ve
            var data = result.data;

            console.log(data);
            console.log(data.name);
            console.log(data.address);

            $('#id').val(data.id);
            $('#name').val(data.name);
            $('#address').val(data.address);
            $('#bedroom').val(data.bedroom);
            $('#description').val(data.description);
            $('#floor_area').val(data.floor_area);
            $('#function_room').val(data.function_room);
            $('#home_deposit').val(data.home_deposit);
            $('#price').val(data.price);
            $('#title').val(data.title);
            $('#car_park').val(data.car_park);
            $('#moto_park').val(data.moto_park);
            $('#home_frontage').val(data.home_frontage);
            $('#campus_area').val(data.campus_area);
            $('#altar_room').val(data.altar_room);
            $('#number_floor').val(data.number_floor);
            $('#frequence').val(data.frequence);
            $('#electricity_price').val(data.electricity_price);
            $('#water_price').val(data.water_price);
            $('#service_price').val(data.service_price);
            $('#direction').val(data.direction).change();
            CKEDITOR.instances.description.setData(data.description);
        },
    });
}