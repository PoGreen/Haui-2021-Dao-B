function generatedBuildings() {
    $.ajax({
        url: '/buildings?page=1&limit=10',
        type: 'GET',
        contentType: 'application/json', //dinh nghia kieu du lieu gui ve server
        dataType: 'JSON', //dinh nghi kieu du lieu server gui len

        success: function (result) {
            console.log(result);
            $('#example').DataTable( {
                "ajax": result.content,
            } );// result la ket qua server tra ve
           console.log(result);
        },
    });
}
$(document).ready(function() {
    var data ;
    $.ajax({
        url: '/buildings?page=1&limit=10',
        type: 'GET',
        contentType: 'application/json', //dinh nghia kieu du lieu gui ve server
        dataType: 'JSON', //dinh nghi kieu du lieu server gui len

        success: function (result) {
           data= result.content;
        },
    });
    $('#example').DataTable( {
        data: data,
        columns: [
            { title: "name" },
            { title: "title" },
            { title: "address" },
            { title: "price" },
            { title: "sale_rent" },
            { title: "edit" }
        ]
    } );
} );