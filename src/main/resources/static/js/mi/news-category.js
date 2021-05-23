function submitNewsCategory() {


    var data = $('#news-category').serializeArray();
    $.ajax({
        url: '/news-categories',
        type: 'POST',
        contentType: 'application/json', //dinh nghia kieu du lieu gui ve server
        dataType: 'JSON', //dinh nghi kieu du lieu server gui len
        data: mapToJson(data),
        headers: {
            Authorization: 'Bearer ' + document.cookie
        },
        success: function (result) { // result la ket qua server tra ve
            window.location = '/admin/news-categories';
        },
        error: function (result) {
            alert("Sảy ra lỗi! vui lòng thử lại");
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

function genNewsCategory() {
    var view = document.getElementById("news-category");
    $.ajax({
        url: '/news-categories?status=2',
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
                gererated += "<li><a href=\"#\">" + data[i].name + "</a></li>\n";
            }
            gererated += "</ul>";
            view.innerHTML = gererated;
        },
    });
};

function gen(status) {

    var data = {
        "page": 1,
        "limit": 20
    }
    $.ajax({

        url: '/news-categories?status=' + status,
        type: 'GET',
        contentType: 'application/json', //dinh nghia kieu du lieu gui ve server
        dataType: 'json', //dinh nghi kieu du lieu server gui len
        data: data,
        success: function (result) { // result la ket qua server tra ve
            console.log(result);
            var data = result.data;
            var leng = data.length;
            console.log(data);
            $('#news-category-table').html("");
            var generated = "<table class=\"table table-bordered\" style=\" border: 2px solid black; display:inline-block\" class=\"table table-striped table-hover\" >" +
                "        <thead class=\"thead-dark\" style='width: 100%'>" +
                "        <tr>\<n></n>" +
                "            <th scope=\"col\" style='width: 5%'>#</th>" +
                "            <th scope=\"col\" style='width: 35%'>Tên</th>" +
                "            <th scope=\"col\" style='width: 35%'>Mô tả</th>" +
                "            <th scope=\"col\" style='width: 15%'>Trạng thái</th>" +
                "            <th scope=\"col\" style='width: 15%'>Ngày tạo</th>" +
                "            <th scope=\"col\" style='width: 5%'>Xóa</th>" +
                "        </tr>" +
                "        </thead>" +
                "        <tbody>";
            for (var i = 0; i < leng; i++) {

                if (data[i].status == 1) var status = "Active";
                if (data[i].status == 3) var status = "NoActive";
                generated += "  <tr>\n" +
                    "      <input class='news-category-id' type='hidden' value=" + data[i].id + ">\n" +
                    "      <th scope=\"row\">" + i + "</th>\n" +
                    "      <td>" + data[i].name + "</td>\n" +
                    "      <td>" + data[i].description + "</td>\n" +
                    "      <td >" + status + "</td>\n" +
                    "      <th>" + data[i].created_at + "</th>" +
                    "      <td><a onclick='deleteNewsCategory(" + i + ")' href=\"#\" ><i class=\"fa fa-check\" aria-hidden=\"true\"></i>Xóa</a></td>\n" +

                    "    </tr>";
            }
            generated +=
                "                        </tbody>" +
                "                    </table>";
            $('#news-category-table').append(generated);
        },
    });
}

function deleteNewsCategory(i) {
    var data = {
        "id": document.getElementsByClassName("news-category-id")[i].value,
        "status": 3,
    };
    console.log(data);
    $.ajax({
        url: '/news-categories',
        type: 'PUT',
        contentType: 'application/json', //dinh nghia kieu du lieu gui ve server
        dataType: 'JSON', //dinh nghi kieu du lieu server gui len
        data: JSON.stringify(data),
        headers: {
            Authorization: 'Bearer ' + document.cookie
        },
        success: function (result) { // result la ket qua server tra ve
            window.location = "/admin/news-categories";
        },
        error: function (result) {
            alert("Sảy ra lỗi! vui lòng thử lại");;
        }
    });
}