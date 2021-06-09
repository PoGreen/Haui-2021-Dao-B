function genUserBuildingsTable(status, saleRent) {
    setStatus(status, saleRent);
    var url = '/user/buildings/v2?status=' + status + '&sale_rent=' + saleRent;
    if (saleRent === 0 && status === 0) {
        url = '/users/buildings';
    }
    console.log()
    $.ajax({
        url: url,
        type: 'GET',
        contentType: 'application/json', //dinh nghia kieu du lieu gui ve server
        dataType: 'json', //dinh nghi kieu du lieu server gui len
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem("token")

        },
        success: function (result) { // result la ket qua server tra ve
            var data = result.data.content;
            console.log(data);
            var leng = data.length;
            $('#user-buildings-table').html("");

            var generated = "<table class=\"table table-bordered\" style=\" border: 2px solid black; display:inline-block\" >" +
                "        <thead class=\"thead-dark\">" +
                "        <tr>\<n></n>" +
                "           <th scope=\"col\" style='width: 10%'>#</th>" +
                "            <th scope=\"col\" style='width: 30%'>Tên</th>" +
                "            <th scope=\"col\" style='width: 7%'>Địa chỉ</th>" +
                "            <th scope=\"col\" style='width: 9%'>Số phòng ngủ</th>" +
                "            <th scope=\"col\" style='width: 9%'>Số phòng chức năng</th>" +
                "            <th scope=\"col\" style='width: 7%'>Giá</th>" +
                "            <th scope=\"col\" style='width: 10%'>Diện tích sàn</th>" +
                "            <th scope=\"col\" style='width: 10%'>Trạng thái</th>" +
                "            <th scope=\"col\" style='width: 3%'>Loại</th>" +
                "            <th scope=\"col\" style='width: 5%'>Kích hoạt</th>" +
                "            <th scope=\"col\" style='width: 5%'>Xóa</th>" +
                "            <th scope=\"col\">Chi tiết</th>" +
                "        </tr>" +
                "        </thead>" +
                "        <tbody>";
            for (var i = 0; i < leng; i++) {
                var status;
                var loai;
                if (data[i].sale_rent === 1) loai = "Bán";
                if (data[i].sale_rent === 2) loai = "Cho thuê";
                if (data[i].status === 1) status = "Hoạt động";
                if (data[i].status === 2) status = "Chờ duyệt";
                if (data[i].status === 3) status = "Đã xoá";
                generated += "  <tr>\n" +
                    "      <input class='building-id' type='hidden' value=" + data[i].id + ">\n" +
                    "      <th scope=\"row\">" + i + "</th>\n" +
                    "      <td>" + data[i].name + "</td>\n" +
                    "      <td>" + data[i].address + "</td>\n" +
                    "      <td>" + data[i].bedroom + "</td>\n" +
                    "      <td>" + data[i].function_room + "</td>\n" +
                    "      <td>" + data[i].price + "</td>\n" +
                    "      <td>" + data[i].floor_area + "</td>\n" +
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

            $('#user-buildings-table').append(generated);
        },
        error:function (result){
            alert("Đã xảy ra lỗi! vui lòng thử lại")
        }
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
    window.location.href = "/users/buildings-edits-pages";
}



function deleteBuildings(i) {
    var data = {
        "id": document.getElementsByClassName("building-id")[i].value,
        "status": 3,
    };
    console.log(data);
    $.ajax({
        url: '/buildings/status',
        type: 'PUT',
        contentType: 'application/json', //dinh nghia kieu du lieu gui ve server
        dataType: 'JSON', //dinh nghi kieu du lieu server gui len
        data: JSON.stringify(data),
        headers: {
            Authorization: 'Bearer ' + document.cookie
        },
        success: function (result) { // result la ket qua server tra ve
            alert("Xoá bất động sản thành công");
            window.location = "/buildings-list-page";
        },
        error: function (result) {
            alert("Sảy ra lỗi! vui lòng thử lại");
        }
    });
}


function showDetail(i) {
    var id = document.getElementsByClassName("building-id")[i].value;
    console.log(id);
    sessionStorage.setItem("id-building", id);
    window.location.href = "/buildings-detail";
}