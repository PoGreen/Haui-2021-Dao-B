function submitNewsCategory() {
    var data = $('#news-category').serializeArray();
    $.ajax({
        url: '/news-categories?status=1',
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

function genNews(category) {
    console.log("category" + category)
    var url = "/news/categories";

    if (category !== 0) url = "/news/categories?id=" + category;

    $.ajax({
        url: url,
        type: 'GET',
        contentType: 'application/json', //dinh nghia kieu du lieu gui ve server
        dataType: 'JSON', //dinh nghi kieu du lieu server gui len

        success: function (result) { // result la ket qua server tra ve
            console.log(result);
            var data = result.data.content;
            console.log(data);
            var leng = data.length;
            var gererated = " ";
            $('#content').html(gererated);
            for (var i = 0; i < leng; i++) {
                var id = data[i].id;
                gererated += " <div class=\"single-blog-area mb-50\">\n" +
                    "                        <!-- Post Thumbnail -->\n" +
                    "                        <div class=\"blog-post-thumbnail\">\n" +
                    "                            <img style='width: 650px;height: 350px' src=\"" + data[i].image_rp.url + "\" alt=\"\">\n" +
                    "                        </div>\n" +
                    "                        <!-- Post Content -->\n" +
                    "                        <div class=\"post-content\">\n" +
                    "                            <!-- Date -->\n" +
                    "                            <div class=\"post-date\">\n" +
                    "                                <a href=\"#\">" + data[i].created_at + "</a>\n" +
                    "                            </div>\n" +
                    "                            <!-- Headline -->\n" +
                    "                            <a href=\"#\" class=\"headline\">" + data[i].name_news + "</a>\n" +
                    "                            <!-- Post Meta -->\n" +
                    "                            <div class=\"post-meta\">\n" +
                    "                                <p>By <a href=\"#\">Admin</a> | in <a href=\"#\">Uncategorized</a> | <a href=\"#\">2 Comments</a></p>\n" +
                    "                            </div>\n" +
                    "                            <p>" + data[i].title + "</p>\n" +
                    "                            <!-- Read More btn -->\n" +
                    "                            <input class='news-id' type='hidden' value=" + id + ">\n" +
                    "                            <a onclick='showNewsDetail(" + i + ")' class=\"btn south-btn\">Chi tiết</a>\n" +
                    "                        </div>\n" +
                    "                    </div>";
            }
            console.log(gererated);
            $('#content').append(gererated);
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
            var generated = "<table class=\"table table-bordered\" style=\" border: 2px solid black; display:inline-block\" >" +
                "        <thead class=\"thead-dark\">" +
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
                if (data[i].sale_rent == 1) status = "Hoạt động";
                if (data[i].sale_rent == 0) status = "Không hoạt động";
                var id = data[i].id;
                generated += "  <tr>\n" +
                    "      <th scope=\"row\">" + i + "</th>\n" +
                    "      <td>" + data[i].name_news + "</td>\n" +
                    "      <td>" + data[i].title + "</td>\n" +
                    "      <td>" + data[i].created_at + "</td>\n" +
                    "      <input class='del-news-id' type='hidden' value=" + id + ">\n" +
                    "      <td><a onclick='showNewsEdit( " + i + ")'><i class=\"fa fa-check\" aria-hidden=\"true\"></i>Sửa</a></td>\n" +
                    "      <td><a onclick='delNews( " + i + ")' ><i class=\"fa fa-check\" aria-hidden=\"true\"></i>Xóa</a></td>\n" +

                    "    </tr>";

            }
            generated +=
                "                        </tbody>" +
                "                    </table>";
            view.innerHTML = generated;
        },
    });
}


function genNewsCategory() {

    $.ajax({
        url: '/news-categories?status=2',
        type: 'GET',
        contentType: 'application/json', //dinh nghia kieu du lieu gui ve server
        dataType: 'JSON', //dinh nghi kieu du lieu server gui len

        success: function (result) { // result la ket qua server tra ve

            var data = result.data;
            var leng = data.length;

            var gererated = "<select style='padding: 10px' class=\"form-control\" id=\"news_category\" name=\"news_category\"> \n";
            for (var i = 0; i < leng; i++) {
                gererated += "<option value=" + data[i].id + ">" + data[i].name + "</option>\n";
            }
            gererated += "</select>";


            $('#news-category').append(gererated);

        },
    });
};


function showNewsDetail(i) {
    var id = document.getElementsByClassName("news-id")[i].value;
    console.log(id);
    localStorage.setItem("id-news", id);
    window.location.href = "/news-page-detail";
}

function showNewsEdit(i) {
    var id = document.getElementsByClassName("del-news-id")[i].value;
    console.log(id);
    localStorage.setItem("id-news", id);
    window.location.href = "/news-page-edit";
}

function delNews(i) {
    var id = document.getElementsByClassName("del-news-id")[i].value;
    console.log(id);
    $.ajax({
        url: '/news/status/' + id,
        type: 'PUT',
        contentType: 'application/json', //dinh nghia kieu du lieu gui ve server
        dataType: 'JSON', //dinh nghi kieu du lieu server gui len
        headers: {
            Authorization: 'Bearer ' + document.cookie
        },
        success: function (result) { // result la ket qua server tra ve
            $('#news-table').html("");
            genNewsTable();
        },
    });
}

function deleteContent(i) {
    console.log(i);
    console.log(document.getElementsByClassName("category-id")[i].value);
    genNews(document.getElementsByClassName("category-id")[i].value);
}

function genNewsCategory1() {
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
            gererated += "";
            for (var i = 0; i < length; i++) {
                gererated += "      <input class='category-id' type='hidden' value=" + data[i].id + ">\n";
                gererated += "<li><a style='text-decoration-line: underline' onclick='deleteContent( " + i + " )'>" + data[i].name + "</a></li>\n";
            }
            gererated += "</ul>";
            view.innerHTML = gererated;
        },
    });
};
