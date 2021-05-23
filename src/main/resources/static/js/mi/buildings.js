function genBuildingCategory() {
    $.ajax({
        url: '/building-categories?status=1',
        type: 'GET',
        contentType: 'application/json', //dinh nghia kieu du lieu gui ve server
        dataType: 'JSON', //dinh nghi kieu du lieu server gui len

        success: function (result) { // result la ket qua server tra ve
            console.log(result);
            var data = result.data.building_category;
            var leng = data.length;
            var gererated = "<select style='padding: 10px' class=\"form-control\" id=\"building_category\" name=\"building_category\"> \n";
            for (var i = 0; i < leng; i++) {
                gererated += "<option value=" + data[i].id + ">" + data[i].name + "</option>\n";
            }
            gererated += "</select>";
            $('#building').append(gererated);

        },
    });
};

function showDetail(i) {
    var id = document.getElementsByClassName("building-id")[i].value;
    console.log(id);
    sessionStorage.setItem("id-building", id);
    window.location.href = "/buildings-detail";
}

function genBuildinDetail() {
    var element = document.getElementById("element");
    var data = sessionStorage.getItem("id-building");
    console.log(data);
    $.ajax({
        url: '/buildings/' + data,
        type: 'GET',
        contentType: 'application/json', //dinh nghia kieu du lieu gui ve server
        dataType: 'JSON', //dinh nghi kieu du lieu server gui len
        success: function (result) { // result la ket qua server tra ve

            console.log(result);
            var data = result.data;
            console.log(data);

            var gererated = "<ul class=\"listings-core-features d-flex align-items-center\" >";
            gererated += "<li id=\"category\"><i class=\"fa fa-check\" aria-hidden=\"true\"></i> Thể loại: </li>\n" +
                "                        <li id=\"car_park\"><i class=\"fa fa-check\" aria-hidden=\"true\"></i> Gara ôtô: " + data.car_park + " m2 </liid>\n" +
                "                        <li id=\"moto_park\"><i class=\"fa fa-check\" aria-hidden=\"true\"></i> Gara moto: " + data.moto_park + " m2 </li>\n" +
                "                        <li id=\"home_frontage\" ><i class=\"fa fa-check\" aria-hidden=\"true\"></i> Mặt tiền: " + data.home_frontage + " m2</li>\n" +
                "                        <li id=\"number_floor\"><i class=\"fa fa-check\" aria-hidden=\"true\"></i> Số tầng: " + data.number_floor + "</li>\n" +
                "                        <li id=\"frequence\"><i class=\"fa fa-check\" aria-hidden=\"true\"></i> Tầng số: " + data.frequence + "</li>\n" +
                "                        <li id=\"altar_room\"><i class=\"fa fa-check\" aria-hidden=\"true\"></i> Phòng thờ: " + data.altar_room + " m2 </li>\n" +
                "                        <li id=\"campus_area\"><i class=\"fa fa-check\" aria-hidden=\"true\"></i> Diện tích khuân viên: " + data.campus_area + " m2</li>\n" +
                "                        <li id=\"direction\"><i class=\"fa fa-check\" aria-hidden=\"true\"></i> Phương hướng: " + data.direction + " </li>\n" +
                "                        <li id=\"electricity_price\"><i class=\"fa fa-check\" aria-hidden=\"true\"></i> Giá điện: " + data.electricity_price + " VND</li>\n" +
                "                        <li id=\"water_price\"><i class=\"fa fa-check\" aria-hidden=\"true\"></i> Giá nước: " + data.water_price + " VND</li>\n" +
                "                        <li id=\"service_price\"><i class=\"fa fa-check\" aria-hidden=\"true\"></i> Giá dịch vụ: " + data.service_price + " VND</li>\n";
            if (data.sale_rent === 1) {
                gererated += "                        <li id=\"sale_rent\"><i class=\"fa fa-check\" aria-hidden=\"true\"></i> Trạng thái: Bán</li>";
            }
            if (data.sale_rent === 1) {
                gererated += "                        <li id=\"sale_rent\"><i class=\"fa fa-check\" aria-hidden=\"true\"></i> Trạng thái: Cho thuê</li>";
            }
            gererated += "  </ul>";
            element.innerHTML = gererated;

            $('#name').append(data.name);
            $('#address').append(data.address);
            $('#bedroom').append(data.bedroom);
            $('#description').append(data.description);

            $('#floor_area').append(data.floor_area);
            $('#function_room').append(data.function_room);
            $('#home_deposit').append(data.home_deposit);
            $('#price').append(data.price);
        },
    });
}

function genBuildingsByStatus(status) {
    $.ajax({
        url: '/buildings/filters?sale_rent=' + status,
        type: 'GET',
        contentType: 'application/json', //dinh nghia kieu du lieu gui ve server
        dataType: 'json', //dinh nghi kieu du lieu server gui len
        success: function (result) { // result la ket qua server tra ve
            console.log(result);
            var data = result.data;
            leng = data.total_record;
            console.log(data);
            console.log(leng);
            var generated = "";
            $('#buildings').append(generated);
            for (var i = 0; i < leng; i++) {
                var id = data.data[i].id;
                // console.log(id);
                generated += " <div class=\"col-12 col-md-6 col-xl-4\">\n" +
                    "                <div class=\"single-featured-property mb-50 wow fadeInUp\" data-wow-delay=\"100ms\">\n" +
                    "                    <!-- Property Thumbnail -->\n" +
                    "                    <div class=\"property-thumb\">\n" +
                    "                        <img style='width: 400px; height: 250px' src=\"" + data.data[i].image_rp + "\" alt=\"\">\n" +
                    "\n" +
                    "                        <div class=\"list-price\">\n" +
                    "                            <p> $ " + data.data[i].price + "</p>\n" +
                    "                        </div>\n" +
                    "                    </div>\n" +
                    "                    <!-- Property Content -->\n" +
                    "                    <div class=\"property-content\">\n" +
                    "                        <h5>" + data.data[i].name + "</h5>\n" +
                    "                        <p class=\"location\"><img src=\"img/icons/location.png\" alt=\"\">" + data.data[i].address + "</p>\n" +
                    "                        <p>" + data.data[i].title + "</p>\n" +
                    "                        <div class=\"property-meta-data d-flex align-items-end justify-content-between\">\n" +
                    "                            <div class=\"new-tag\">\n" +
                    "                            <input class='building-id' type='hidden' value=" + id + ">\n" +
                    "                            <a onclick='showDetail(" + i + ")'>Chi tiết</a>\n" +
                    "                            </div>\n" +
                    "                            <div class=\"bathroom\">\n" +
                    "                                <img src=\"img/icons/bathtub.png\" alt=\"\">\n" +
                    "                                <span>" + data.data[i].bedroom + "</span>\n" +
                    "                            </div>\n" +
                    "                            <div class=\"garage\">\n" +
                    "                                <img src=\"img/icons/garage.png\" alt=\"\">\n" +
                    "                                <span>" + data.data[i].function_room + "</span>\n" +
                    "                            </div>\n" +
                    "                            <div class=\"space\">\n" +
                    "                                <img src=\"img/icons/space.png\" alt=\"\">\n" +
                    "                                <span>" + data.data[i].floor_area + "</span>\n" +
                    "                            </div>\n" +
                    "                        </div>\n" +
                    "                    </div>\n" +
                    "                </div>\n" +
                    "            </div>";

            }
            $('#buildings').append(generated);
        },
    });
}


function genBuildingsTable(status, saleRent) {

    setStatus(status, saleRent);
    var view = document.getElementById("buildings-table");
    var url = '/buildings/filters?status=' + status + '&sale_rent=' + saleRent;
    if (saleRent === 0 && status === 0) {
        url = '/buildings/filters';
    }
    $.ajax({
        url: url,
        type: 'GET',
        contentType: 'application/json', //dinh nghia kieu du lieu gui ve server
        dataType: 'json', //dinh nghi kieu du lieu server gui len
        success: function (result) { // result la ket qua server tra ve
            console.log(result);
            var data = result.data;
            var leng = data.total_record;
            console.log(data);
            console.log(leng);
            var generated = "<table class=\"table table-bordered\" style=\" border: 2px solid black; display:inline-block\" >" +
                "        <thead class=\"thead-dark\">" +
                "        <tr>\<n></n>" +
                "            <th scope=\"col\" style='width: 10%'>#</th>" +
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
                if (data.data[i].sale_rent === 1) loai = "Bán";
                if (data.data[i].sale_rent === 2) loai = "Cho thuê";
                if (data.data[i].status === 1) status = "Hoạt động";
                if (data.data[i].status === 2) status = "Chờ duyệt";
                if (data.data[i].status === 3) status = "Không hoạt động";
                generated += "  <tr>\n" +
                    "      <input class='building-id' type='hidden' value=" + data.data[i].id + ">\n" +
                    "      <th scope=\"row\">" + i + "</th>\n" +
                    "      <td >" + data.data[i].name + "</td>\n" +
                    "      <td >" + data.data[i].address + "</td>\n" +
                    "      <td >" + data.data[i].bedroom + "</td>\n" +
                    "      <td >" + data.data[i].function_room + "</td>\n" +
                    "      <td >" + data.data[i].price + "</td>\n" +
                    "      <td >" + data.data[i].floor_area + "</td>\n" +
                    "      <td >" + status + "</td>\n" +
                    "      <td >" + loai + "</td>\n" +
                    "      <td ><a onclick='activeBuildings(" + i + ")' href=\"#\" ><i class=\"fa fa-check\" aria-hidden=\"true\"></i>Kích hoạt</a></td>\n" +
                    "      <td ><a onclick='deleteBuildings(" + i + ")' href=\"#\" ><i class=\"fa fa-check\" aria-hidden=\"true\"></i>Xóa</a></td>\n" +
                    "      <td ><a onclick='showDetail(" + i + ")' href=\"#\" ><i class=\"fa fa-check\" aria-hidden=\"true\"></i>Chi tiết</a></td>\n" +
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
    sessionStorage.removeItem("status");
    sessionStorage.removeItem("saleRent");

    sessionStorage.setItem("status", status);
    sessionStorage.setItem("saleRent", saleRent);
};

function genFileExcel() {
    var status = sessionStorage.getItem("status");
    var saleRent = sessionStorage.getItem("saleRent");
    $.ajax({
        url: '/buildings/excel?status=' + status + '&sale_rent=' + saleRent + '&fields=name,title,carPark,motoPark,floorArea,homeFrontage,numberFloor,bedroom,functionRoom,altarRoom,price,campusArea,direction,electricityPrice,frequence, waterPrice,servicePrice,homeDeposit,address,buildingCategory',
        type: 'GET',
        contentType: 'application/json', //dinh nghia kieu du lieu gui ve server
        dataType: 'JSON', //dinh nghi kieu du lieu server gui len

        success: function (result) { // result la ket qua server tra ve
            alert("Xuất dữ liệu thành công vui lòng kiểm tra thư mục    ");
        },
        error: function (result) {
            alert("Đã xảy ra lỗi!! vui lòng thử lại");
        }
    });
};

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
            window.location = "/admin/buildings";
        },
        error: function (result) {
            alert("Sảy ra lỗi! vui lòng thử lại");
        }
    });
}

function activeBuildings(i) {
    var data = {
        "id": document.getElementsByClassName("building-id")[i].value,
        "status": 1,
    };
    console.log(data);
    $.ajax({
        url: '/buildings/status',
        type: 'PUT',
        contentType: 'application/json', //dinh nghia kieu du lieu gui ve server
        dataType: 'JSON', //dinh nghi kieu du lieu server gui len
        data: JSON.stringify(data),
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem("token")
        },
        success: function (result) { // result la ket qua server tra ve
            window.location = "/admin/buildings";
        },
        error: function (result) {
            alert("Sảy ra lỗi! vui lòng thử lại");
        }
    });
}


function sendEmail() {
    var buildingId = sessionStorage.getItem("id-building");
    if(localStorage.getItem("token") === null){
        alert("Vui lòng đăng nhập vào hệ thống!!");
    }else{
        $.ajax({
            url: '/user/buildings/send-email?building-id=' + buildingId,
            type: 'POST',
            contentType: 'application/json', //dinh nghia kieu du lieu gui ve server
            dataType: 'JSON', //dinh nghi kieu du lieu server gui len
            headers: {
                Authorization: 'Bearer ' + localStorage.getItem("token")
            },
            success: function (result) { // result la ket qua server tra ve
                alert("Gửi thông báo thành công đến chủ sở hữu! vui lòng kiểm tra mail cá nhân");
            },
            error: function (result) {
                alert("Sảy ra lỗi! vui lòng thử lại");
            }
        });
    }

}


function validateBuilding() {

}


