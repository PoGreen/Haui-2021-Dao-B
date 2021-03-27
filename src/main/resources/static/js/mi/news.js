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
            window.location = '';
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

function genNews() {
    var view = document.getElementById("content");
    $.ajax({
        url: '/news',
        type: 'GET',
        contentType: 'application/json', //dinh nghia kieu du lieu gui ve server
        dataType: 'JSON', //dinh nghi kieu du lieu server gui len

        success: function (result) { // result la ket qua server tra ve
            console.log(result);
            var data = result.data.content;
            console.log(data);
            var leng = data.length;
            var gererated = " ";

            for (var i = 0; i < leng; i++) {
                console.log();
                gererated += " <div class=\"single-blog-area mb-50\">\n" +
                    "                        <!-- Post Thumbnail -->\n" +
                    "                        <div class=\"blog-post-thumbnail\">\n" +
                    "                            <img src=\""+data[i].image_rp.url+"\" alt=\"\">\n" +
                    "                        </div>\n" +
                    "                        <!-- Post Content -->\n" +
                    "                        <div class=\"post-content\">\n" +
                    "                            <!-- Date -->\n" +
                    "                            <div class=\"post-date\">\n" +
                    "                                <a href=\"#\">"+data[i].created_at+"</a>\n" +
                    "                            </div>\n" +
                    "                            <!-- Headline -->\n" +
                    "                            <a href=\"#\" class=\"headline\">"+data[i].name_news+"</a>\n" +
                    "                            <!-- Post Meta -->\n" +
                    "                            <div class=\"post-meta\">\n" +
                    "                                <p>By <a href=\"#\">Admin</a> | in <a href=\"#\">Uncategorized</a> | <a href=\"#\">2 Comments</a></p>\n" +
                    "                            </div>\n" +
                    "                            <p>"+data[i].title+"</p>\n" +
                    "                            <!-- Read More btn -->\n" +
                    "                            <a href=\"#\" class=\"btn south-btn\">Chi tiết</a>\n" +
                    "                        </div>\n" +
                    "                    </div>";
            }
            view.innerHTML = gererated;
        },
    });
};

function genNewsTable() {
    var view = document.getElementById("news-table");
    var data = {
        "page": 1,
        "limit": 20
    }
    $.ajax({

        url: '/news',
        type: 'GET',
        contentType: 'application/json', //dinh nghia kieu du lieu gui ve server
        dataType: 'json', //dinh nghi kieu du lieu server gui len
        data: data,
        success: function (result) { // result la ket qua server tra ve
            console.log(result);
            var data = result.data.content;
            var leng = data.length;
            console.log(data);
            var generated = "<table class=\"table\" style=\" border: 2px solid black; display:inline-block\" >" +
                "        <thead>" +
                "        <tr>\<n></n>" +
                "            <th scope=\"col\">#</th>" +
                "            <th scope=\"col\">Tên</th>" +
                "            <th scope=\"col\">Tiêu đề</th>" +
                "            <th scope=\"col\">Ngày tạo</th>" +
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
                    "      <td>" + data[i].name_news + "</td>\n" +
                    "      <td>" + data[i].title + "</td>\n" +
                    "      <td>" + data[i].created_at + "</td>\n" +
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
