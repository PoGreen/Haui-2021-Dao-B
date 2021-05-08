function genUserBuildingsTable(status, saleRent) {

    setStatus(status, saleRent);
    var view = document.getElementById("buildings-table");
    var url = '/user/buildings/filters?status=' + status + '&sale_rent=' + saleRent;
    if (saleRent === 0 && status === 0) {
        url = '/user/buildings/filters';
    }
    $.ajax({
        url: url,
        type: 'GET',
        contentType: 'application/json', //dinh nghia kieu du lieu gui ve server
        dataType: 'json', //dinh nghi kieu du lieu server gui len
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem("token")
        },
        success: function (result) { // result la ket qua server tra ve
            console.log(result);
            var data = result.data;
            var leng = data.total_record;
            var generated = "<table class=\"table\" style=\" border: 2px solid black; display:inline-block\" >" +
                "        <thead>" +
                "        <tr>\<n></n>" +
                "            <th scope=\"col\">#</th>" +
                "            <th scope=\"col\">Tên</th>" +
                "            <th scope=\"col\">Địa chỉ</th>" +
                "            <th scope=\"col\">Số phòng ngủ</th>" +
                "            <th scope=\"col\">Số phòng chức năng</th>" +
                "            <th scope=\"col\">Giá</th>" +
                "            <th scope=\"col\">Diện tích sàn</th>" +
                "            <th scope=\"col\">Trạng thái</th>" +
                "            <th scope=\"col\">Loại</th>" +
                "            <th scope=\"col\">Xóa</th>" +
                "            <th scope=\"col\">Chi tiết</th>" +
                "        </tr>" +
                "        </thead>" +
                "        <tbody>";
            for (var i = 0; i < leng; i++) {
                var status;
                var loai;
                if (data.data[i].sale_rent === 1) loai = "Bán";
                if (data.data[i].sale_rent === 2) loai = "Cho thuê";
                if (data.data[i].status === 1) status = "Hoạt động";
                if (data.data[i].status === 2) status = "Chờ duyệt";
                generated += "  <tr>\n" +
                    "      <input class='building-id' type='hidden' value=" + data.data[i].id + ">\n" +
                    "      <th scope=\"row\">" + i + "</th>\n" +
                    "      <td>" + data.data[i].name + "</td>\n" +
                    "      <td>" + data.data[i].address + "</td>\n" +
                    "      <td>" + data.data[i].bedroom + "</td>\n" +
                    "      <td>" + data.data[i].function_room + "</td>\n" +
                    "      <td>" + data.data[i].price + "</td>\n" +
                    "      <td>" + data.data[i].floor_area + "</td>\n" +
                    "      <td>" + status + "</td>\n" +
                    "      <td>" + loai + "</td>\n" +
                    "      <td><a onclick='deleteBuildings(" + i + ")' href=\"#\" ><i class=\"fa fa-check\" aria-hidden=\"true\"></i>Xóa</a></td>\n" +
                    "      <td><a onclick='genEditBuilding(" + i + ")' href=\"#\" ><i class=\"fa fa-check\" aria-hidden=\"true\"></i>Chỉnh sửa</a></td>\n" +
                    "      <td><a onclick='showDetail(" + i + ")' href=\"#\" ><i class=\"fa fa-check\" aria-hidden=\"true\"></i>Chi tiết</a></td>\n" +
                    "    </tr>";
            }
            generated +=
                "                        </tbody>" +
                "                    </table>";
            view.innerHTML = generated;
        },
    });
}

function setStatus(status, saleRent) {
    sessionStorage.removeItem("user-status");
    sessionStorage.removeItem("user-saleRent");

    sessionStorage.setItem("user-status", status);
    sessionStorage.setItem("user-saleRent", saleRent);
};


function showDetail(i) {
    var id = document.getElementsByClassName("building-id")[i].value;
    console.log(id);
    sessionStorage.setItem("id-building", id);
    window.location.href = "/buildings-detail";
}
function genEditBuilding(i) {
    var id = document.getElementsByClassName("building-id")[i].value;
    console.log(id);
    sessionStorage.setItem("id-building", id);
    window.location.href = "/buildings-edit";
}