function setCategory(index) {
    var url = "";
    switch (index) {
        case 1:
            url = "http://123nhadat.vn/rss/tin-tuc/14/kinh-nghiem-mua-nha.rss";
            break;
        case 2:
            url = "http://123nhadat.vn/rss/tin-tuc/19/kinh-nghiem-ban-nha.rss";
            break;
        case 3:
            url = "http://123nhadat.vn/rss/tin-tuc/20/kinh-nghiem-dau-tu.rss";
            break;
        case 6:
            url = "http://123nhadat.vn/rss/tin-tuc/15/phan-tich-nhan-dinh.rss";
            break;
        case 7:
            url = "http://123nhadat.vn/rss/tin-tuc/16/chinh-sach-quan-ly.rss";
            break;
        case 8:
            url = "http://123nhadat.vn/rss/tin-tuc/17/thong-tin-quy-hoach.rss";
            break;
        case 9:
            url = "http://123nhadat.vn/rss/tin-tuc/23/tai-chinh-chung-khoan.rss";
            break;
        case 10:
            url = "http://123nhadat.vn/rss/tin-tuc/50/tin-du-an.rss";
            break;
    }
    $.ajax({
        url: '/rss-news?url=' + url,
        type: 'GET',
        contentType: 'application/json', //dinh nghia kieu du lieu gui ve server
        dataType: 'JSON', //dinh nghi kieu du lieu server gui len

        success: function (result) { // result la ket qua server tra ve
            console.log(result);
            var data = result.data;
            console.log(data);
            var leng = data.length;
            var gererated = " ";
            $('#content').html(gererated);
            for (var i = 0; i < leng; i++) {
                var id = data[i].id;
                gererated += " <div class=\"single-blog-area mb-50\">\n" +
                    "                        <!-- Post Thumbnail -->\n" +
                    "                        <div class=\"blog-post-thumbnail\">\n" +
                    "                            <img style='width: 650px;height: 350px' src=\"" + data[i].image + "\" alt=\"\">\n" +
                    "                        </div>\n" +
                    "                        <!-- Post Content -->\n" +
                    "                        <div class=\"post-content\">\n" +
                    "                            <!-- Date -->\n" +
                    "                            <div class=\"post-date\">\n" +
                    "                                <a href=\"#\">" + data[i].date + "</a>\n" +
                    "                            </div>\n" +
                    "                            <!-- Headline -->\n" +
                    "                            <a href=\"#\" class=\"headline\">" + data[i].name + "</a>\n" +
                    "                            <!-- Post Meta -->\n" +
                    "                            <div class=\"post-meta\">\n" +
                    "                                <p>By <a href=\"#\">Admin</a> | in <a href=\"#\">Uncategorized</a> | <a href=\"#\">2 Comments</a></p>\n" +
                    "                            </div>\n" +
                    "                            <p>" + data[i].title + "</p>\n" +
                    "                            <!-- Read More btn -->\n" +
                    "                            <input class='news-id' type='hidden' value=" + id + ">\n" +
                    "                            <a href='" + data[i].url + "' class=\"btn south-btn\">Chi tiết</a>\n" +
                    "                        </div>\n" +
                    "                    </div>";
            }
            $('#content').append(gererated);
        },
    });
}
