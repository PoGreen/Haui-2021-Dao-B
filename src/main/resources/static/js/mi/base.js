function base() {

    var token = localStorage.getItem("token");
    console.log(token);
    var generated = "";

    if (token === null) {
        generated = " <ul>\n" +
            "                            <li><a href=\"/home\">Trang chủ</a></li>\n" +
            "                            <li><a href=\"/buildings-buy-page\">Bất động sản cho thuê</a></li>\n" +
            "                            <li><a href=\"/buildings-rent-page\">Bất động sản đang bán</a></li>\n" +
            "                            <li><a href=\"/news-page\">Tin tức</a></li>\n" +
            "                            <li><a href=\"#\">Giới thiệu</a></li>\n" +
            "                            <li><a href=\"/login\">Bất động sản cá nhân</a></li>\n" +
            "                            <li><a href=\"/login\">Đăng nhập</a></li>\n" +
            "                        </ul>";
    }

    if (token !== null) {
        generated = " <ul>\n" +
            "                            <li><a href=\"/home\">Trang chủ</a></li>\n" +
            "                            <li><a href=\"/buildings-buy-page\">Bất động sản cho thuê</a></li>\n" +
            "                            <li><a href=\"/buildings-rent-page\">Bất động sản đang bán</a></li>\n" +
            "                            <li><a href=\"/news-page\">Tin tức</a></li>\n" +
            "                            <li><a href=\"#\">Giới thiệu</a></li>\n" +
            "                            <li><a href=\"/buildings-list-page\">Bất động sản cá nhân</a></li>\n" +
            "                            <li><a onclick='logout()' href=\"#\">Logout</a></li>\n" +
            "                        </ul>"
    }
    $('#menu').append(generated);
};


function logout() {

    $.ajax({
        url: '/users/logout',
        type: 'DELETE',
        contentType: 'application/json', //dinh nghia kieu du lieu gui ve server
        dataType: 'JSON', //dinh nghi kieu du lieu server gui len
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem("token")
        },
        success: function (result) { // result la ket qua server tra ve
            localStorage.removeItem("token");
            window.location = '/home';
        },
        error: function (result) {
            $('#alert').css('display', '')
        }
    });
}