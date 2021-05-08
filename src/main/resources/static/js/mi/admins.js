function gen(status) {
    var data = {
        "page": 1,
        "limit": 20
    }
    $.ajax({

        url: '/users/filter?status=' + status,
        type: 'GET',
        contentType: 'application/json', //dinh nghia kieu du lieu gui ve server
        dataType: 'json', //dinh nghi kieu du lieu server gui len
        data: data,
        success: function (result) { // result la ket qua server tra ve
            console.log(result);
            var data = result.data.content;
            var leng = data.length;
            console.log(data);
            $('#users-table').html("");
            var generated = "<table class=\"table\" style=\" border: 2px solid black; display:inline-block\" >" +
                "        <thead>" +
                "        <tr>\<n></n>" +
                "            <th scope=\"col\">#</th>" +
                "            <th scope=\"col\">Tên tài khoản</th>" +
                "            <th scope=\"col\">Tên đầy đủ</th>" +
                "            <th scope=\"col\">Số điện thoại</th>" +
                "            <th scope=\"col\">Email</th>" +
                "            <th scope=\"col\">Địa chỉ</th>" +
                "            <th scope=\"col\">Quyền hạn</th>" +
                "            <th scope=\"col\">Trạng thái</th>" +
                "            <th scope=\"col\">Ngày đăng ký</th>" +
                "            <th scope=\"col\">Xóa</th>" +
                "        </tr>" +
                "        </thead>" +
                "        <tbody>";
            for (var i = 0; i < leng; i++) {
                if (data[i].status == 1) status = "Active";
                if (data[i].status == 3) status = "NoActive";
                generated += "  <tr>\n" +
                    "  <input class='admin-id' type='hidden' value=" + data[i].id + ">\n" +
                    "      <th scope=\"row\">" + i + "</th>\n" +
                    "      <td>" + data[i].user_name + "</td>\n" +
                    "      <td>" + data[i].full_name + "</td>\n" +
                    "      <td>" + data[i].phone + "</td>\n" +
                    "      <td>" + data[i].email + "</td>\n" +
                    "      <td>" + data[i].address + "</td>\n" +
                    "      <td>" + data[i].role_name + "</td>\n" +
                    "      <td>" + status + "</td>\n" +
                    "      <th>" + data[i].created_at + "</th>" +
                    "      <td><a onclick='deleteUser(" + i + ")' href=\"#\" ><i class=\"fa fa-check\" aria-hidden=\"true\"></i>Xóa</a></td>\n" +

                    "    </tr>";

            }
            generated +=
                "                        </tbody>" +
                "                    </table>";

            $('#users-table').append(generated);

        },
    });
}

function deleteUser(i) {
    var data = {
        "id": document.getElementsByClassName("admin-id")[i].value,
        "status": 3,
    };
    console.log(data);
    $.ajax({
        url: '/users/status',
        type: 'PUT',
        contentType: 'application/json', //dinh nghia kieu du lieu gui ve server
        dataType: 'JSON', //dinh nghi kieu du lieu server gui len
        data: JSON.stringify(data),
        headers: {
            Authorization: 'Bearer ' + document.cookie
        },
        success: function (result) { // result la ket qua server tra ve
            window.location = "/admin/list-users-page";
        },
        error: function (result) {
            alert("Sảy ra lỗi! vui lòng thử lại");
        }
    });
}