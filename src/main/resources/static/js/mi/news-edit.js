function genNewsEdit() {
    var data = localStorage.getItem("id-news");
    console.log(data);
    $.ajax({
        url: '/news/' + data,
        type: 'GET',
        contentType: 'application/json', //dinh nghia kieu du lieu gui ve server
        dataType: 'JSON', //dinh nghi kieu du lieu server gui len
        success: function (result) { // result la ket qua server tra ve
            $('#name').val(result.data.name);
            $('#id').val(result.data.id);
            $('#title').val(result.data.title);
            CKEDITOR.instances['content'].setData(result.data.content);
        },
    });
}
