function submitNewsCategory(){


    var data = $('#news-category').serializeArray();
    $.ajax({
        url: '/news-categories',
        type: 'POST',
        contentType: 'application/json', //dinh nghia kieu du lieu gui ve server
        dataType: 'JSON', //dinh nghi kieu du lieu server gui len
        data: mapToJson(data),
        headers: {
            Authorization: 'Bearer '+document.cookie
        },
        success: function (result) { // result la ket qua server tra ve
            window.location = '/admin/news-categories';
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
function genNewsCategory() {
    var view = document.getElementById("news-category");
    $.ajax({
        url: '/web/news-categories',
        type: 'GET',
        contentType: 'application/json', //dinh nghia kieu du lieu gui ve server
        dataType: 'JSON', //dinh nghi kieu du lieu server gui len

        success: function (result) { // result la ket qua server tra ve
            console.log(result);
            var data = result.data;
            console.log(data);
            var length = data.length;
            var gererated = " <ul class=\"catagories-menu\">";
            gererated += "<option>Thể loại</option>\n";
            for (var i = 0; i < length; i++) {
                gererated += "<li><a href=\"#\">"+data[i].name+"</a></li>\n";
            }
            gererated += "</ul>";
            view.innerHTML = gererated;
        },
    });
};

function genNewsCategoryTable() {
    var view = document.getElementById("news-category-table");
    var data = {
        "page": 1,
        "limit": 20
    }
    $.ajax({

        url: '/news-categories',
        type: 'GET',
        contentType: 'application/json', //dinh nghia kieu du lieu gui ve server
        dataType: 'json', //dinh nghi kieu du lieu server gui len
        data: data,
        success: function (result) { // result la ket qua server tra ve
            console.log(result);
            var data = result.data;
            var leng = data.length;
            console.log(data);
            var generated = "<table class=\"table\" style=\" border: 2px solid black; display:inline-block\" >" +
                "        <thead>" +
                "        <tr>\<n></n>" +
                "            <th scope=\"col\">#</th>" +
                "            <th scope=\"col\">Tên</th>" +
                "            <th scope=\"col\">Mô tả</th>" +
                "            <th scope=\"col\">Chỉnh sửa</th>" +
                "            <th scope=\"col\">Xóa</th>" +
                "        </tr>" +
                "        </thead>" +
                "        <tbody>";
            for (var i = 0; i < leng; i++) {
                var status;
                if (data[i].sale_rent == 1) status = "Bán";
                if (data[i].sale_rent == 0) status = "Cho thuê";
                generated += "  <tr>\n" +
                    "      <th scope=\"row\">" + i + "</th>\n" +
                    "      <td>" + data[i].name + "</td>\n" +
                    "      <td>" + data[i].description + "</td>\n" +
                    "      <td><a href=\"#\" ><i class=\"fa fa-check\" aria-hidden=\"true\"></i>Sửa</a></td>\n" +
                    "      <td><a href=\"#\" ><i class=\"fa fa-check\" aria-hidden=\"true\"></i>Xóa</a></td>\n" +

                    "    </tr>";

            }
            generated +=
                "                        </tbody>" +
                "                    </table>";
            view.innerHTML = generated;
        },
    });
}
