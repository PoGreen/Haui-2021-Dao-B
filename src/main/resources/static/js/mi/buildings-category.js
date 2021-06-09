function submitBuildingCategory() {

    var data = $('#buildings-category').serializeArray();
    $.ajax({
        url: '/building-categories',
        type: 'POST',
        contentType: 'application/json', //dinh nghia kieu du lieu gui ve server
        dataType: 'JSON', //dinh nghi kieu du lieu server gui len
        data: mapToJson(data),
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem("token")
        },
        success: function (result) { // result la ket qua server tra ve
            window.location = "/admin/buildings-categories";
        },
        error: function (result) {
            toastr.error('Đã xảy ra lỗi! vui lòng thử lai ');
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


function deleteBuildingCategory(i) {
    var data = {
        "id": document.getElementsByClassName("building-category-id")[i].value,
        "status": 3,
    };
    console.log(data);
    $.ajax({
        url: '/building-categories',
        type: 'PUT',
        contentType: 'application/json', //dinh nghia kieu du lieu gui ve server
        dataType: 'JSON', //dinh nghi kieu du lieu server gui len
        data: JSON.stringify(data),
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem("token")
        },
        success: function (result) { // result la ket qua server tra ve
            window.location = "/admin/buildings-categories";
        },
        error: function (result) {
            toastr.error('Đã xảy ra lỗi! vui lòng thử lai ');
        }
    });
}
function gen(status) {
    var data = {
        "page": 1,
        "limit": 20
    }
    $.ajax({

        url: '/building-categories?status=' + status,
        type: 'GET',
        contentType: 'application/json', //dinh nghia kieu du lieu gui ve server
        dataType: 'json', //dinh nghi kieu du lieu server gui len
        data: data,
        success: function (result) { // result la ket qua server tra ve
            console.log(result);
            var data = result.data.building_category;
            var leng = data.length;
            console.log(data);
            $('#building-category-table').html("");
            var generated = "<table class=\"table table-bordered\" style=\" border: 2px solid black; display:inline-block\" >" +
                "        <thead class=\"thead-dark\">" +
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
                if (data[i].status == 1) status = "Hoạt động";
                if (data[i].status == 3) status = "Không hoạt động";
                generated += "  <tr>\n" +
                    "  <input class='building-category-id' type='hidden' value=" + data[i].id + ">\n" +
                    "      <th scope=\"row\">" + i + "</th>\n" +
                    "      <td>" + data[i].name + "</td>\n" +
                    "      <td>" + data[i].description + "</td>\n" +
                    "      <td>" + status + "</td>\n" +
                    "      <th>" + data[i].created_at + "</th>" +
                    "      <td><a onclick='deleteBuildingCategory(" + i + ")' href=\"#\" ><i class=\"fa fa-check\" aria-hidden=\"true\"></i>Xóa</a></td>\n" +

                    "    </tr>";

            }
            generated +=
                "                        </tbody>" +
                "                    </table>";

            $('#building-category-table').append(generated);

        },
    });
}
