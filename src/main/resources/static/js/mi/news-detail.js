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


function genNewsDetail() {
    var data = localStorage.getItem("id-news");
    console.log(data);
    $.ajax({
        url: '/news/' + data,
        type: 'GET',
        contentType: 'application/json', //dinh nghia kieu du lieu gui ve server
        dataType: 'JSON', //dinh nghi kieu du lieu server gui len
        success: function (result) { // result la ket qua server tra ve
            console.log(result);
            var data = result.data;
            var generated = "  <!-- Post Thumbnail -->\n" +
                "                    <div class=\"blog-post-thumbnail\">\n" +
                "                        <img src=\"" + data.image_rqs[0].url + "\" alt=\"\">\n" +
                "                    </div>\n" +
                "                    <!-- Post Content -->\n" +
                "                    <div class=\"post-content\">\n" +
                "                        <!-- Date -->\n" +
                "                        <div class=\"post-date\">\n" +
                "                            <a href=\"#\">" + data.created_at + "</a>\n" +
                "                        </div>\n" +
                "                        <!-- Headline -->\n" +
                "                        <a href=\"#\" class=\"headline\">" + data.name + "</a>\n" +
                "                        <!-- Post Meta -->\n" +
                "                        <div class=\"post-meta\">\n" +
                "                            <p>By <a href=\"#\">Admin</a> | in <a href=\"#\">Uncategorized</a> | <a href=\"#\">2 Comments</a></p>\n" +
                "                        </div>\n" +
                "                        <p>" + data.title + "</p>\n" +
                "                        <p>" + data.content + "</p>\n" +
                "                    </div>";
            $('#news-detail').append(generated);
        },
    });
}
