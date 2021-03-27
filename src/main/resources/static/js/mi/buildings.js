function genBuildingCategory() {
    var view = document.getElementById("building");
    $.ajax({
        url: '/building-categories',
        type: 'GET',
        contentType: 'application/json', //dinh nghia kieu du lieu gui ve server
        dataType: 'JSON', //dinh nghi kieu du lieu server gui len

        success: function (result) { // result la ket qua server tra ve
            console.log(result);
            var data = result.data.building_category;
            var leng = data.length;
            var gererated = "<select style='padding: 10px' class=\"form-control\" id=\"building_category\" name=\"building_category\"> \n";
            gererated += "<option>Thể loại</option>\n";
            for (var i = 0; i < leng; i++) {
                gererated += "<option value=" + data[i].id + ">" + data[i].name + "</option>\n";
            }
            gererated += "</select>";
            view.innerHTML = gererated;
        },
    });
};

function genBuildinDetail(id) {
    var view = document.getElementById("images");
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

            var leng = data.image_rps.length;

            var gererated = "<ul class=\"listings-core-features d-flex align-items-center\" >";
            for (i = 0; i < leng; i++) {
                gererated += "<li id=\"category\"><i class=\"fa fa-check\" aria-hidden=\"true\"></i> Thể loại: </li>\n" +
                    "                        <li id=\"car_park\"><i class=\"fa fa-check\" aria-hidden=\"true\"></i> Gara ôtô: " + data.car_park + " m2 </liid>\n" +
                    "                        <li id=\"moto_park\"><i class=\"fa fa-check\" aria-hidden=\"true\"></i> Gara moto: " + data.moto_park + " m2 </li>\n" +
                    "                        <li id=\"home_frontage\" ><i class=\"fa fa-check\" aria-hidden=\"true\"></i> Mặt tiền: " + data.home_frontage + " m2</li>\n" +
                    "                        <li id=\"number_floor\"><i class=\"fa fa-check\" aria-hidden=\"true\"></i> Số tầng: " + data.number_floor + "</li>\n" +
                    "                        <li id=\"frequence\"><i class=\"fa fa-check\" aria-hidden=\"true\"></i> Tầng số: " + data.frequence + "</li>\n" +
                    "                        <li id=\"altar_room\"><i class=\"fa fa-check\" aria-hidden=\"true\"></i> Phòng thờ: " + data.altar_room + " m2 </li>\n" +
                    "                        <li id=\"campus_area\"><i class=\"fa fa-check\" aria-hidden=\"true\"></i> Diện tích khuân viên: " + data.campus_area + " m2</li>\n" +
                    "                        <li id=\"direction\"><i class=\"fa fa-check\" aria-hidden=\"true\"></i> Phương hướng: "+data.direction+" </li>\n" +
                    "                        <li id=\"electricity_price\"><i class=\"fa fa-check\" aria-hidden=\"true\"></i> Giá điện: "+data.electricity_price+" VND</li>\n" +
                    "                        <li id=\"water_price\"><i class=\"fa fa-check\" aria-hidden=\"true\"></i> Giá nước: "+data.water_price+" VND</li>\n" +
                    "                        <li id=\"service_price\"><i class=\"fa fa-check\" aria-hidden=\"true\"></i> Giá dịch vụ: "+data.service_price+" VND</li>\n";
                    if(data.sale_rent === 1){
                        gererated += "                        <li id=\"sale_rent\"><i class=\"fa fa-check\" aria-hidden=\"true\"></i> Trạng thái: Bán</li>";
                    }
                    if(data.sale_rent === 1){
                        gererated += "                        <li id=\"sale_rent\"><i class=\"fa fa-check\" aria-hidden=\"true\"></i> Trạng thái: Cho thuê</li>";
                    }

            }
            gererated += "  </ul>";
            console.log(gererated);
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

function genBuildings() {
    var view = document.getElementById("buildings");
    var data = {
        "page": 1,
        "limit": 9
    }
    $.ajax({

        url: '/buildings',
        type: 'GET',
        contentType: 'application/json', //dinh nghia kieu du lieu gui ve server
        dataType: 'json', //dinh nghi kieu du lieu server gui len
        data: data,
        success: function (result) { // result la ket qua server tra ve
            console.log(result);
            var data = result.data.content;
            console.log(data);
            var generated = "";
            for (var i = 0; i < 6; i++) {
                var id = data[i].id;
                // console.log(id);
                generated += " <div class=\"col-12 col-md-6 col-xl-4\">\n" +
                    "                <div class=\"single-featured-property mb-50 wow fadeInUp\" data-wow-delay=\"100ms\">\n" +
                    "                    <!-- Property Thumbnail -->\n" +
                    "                    <div class=\"property-thumb\">\n" +
                    "                        <img style='width: 400px; height: 250px' src=\"" + data[i].image_rp.url + "\" alt=\"\">\n" +
                    "\n" +
                    "                        <div class=\"tag\">\n" +
                    "                            <span>For Sale</span>\n" +
                    "                        </div>\n" +
                    "                        <div class=\"list-price\">\n" +
                    "                            <p> $ " + data[i].price + "</p>\n" +
                    "                        </div>\n" +
                    "                    </div>\n" +
                    "                    <!-- Property Content -->\n" +
                    "                    <div class=\"property-content\">\n" +
                    "                        <h5>" + data[i].name + "</h5>\n" +
                    "                        <p class=\"location\"><img src=\"img/icons/location.png\" alt=\"\">" + data[i].address + "</p>\n" +
                    "                        <p>" + data[i].title + "</p>\n" +
                    "                        <div class=\"property-meta-data d-flex align-items-end justify-content-between\">\n" +
                    "                            <div class=\"new-tag\">\n" +
                    "                            <input class='building-id' type='hidden' value=" + id + ">\n" +
                    "                            <a onclick='showDetail(" + i + ")'>Chi tiết</a>\n" +
                    "                            </div>\n" +
                    "                            <div class=\"bathroom\">\n" +
                    "                                <img src=\"img/icons/bathtub.png\" alt=\"\">\n" +
                    "                                <span>" + data[i].bedroom + "</span>\n" +
                    "                            </div>\n" +
                    "                            <div class=\"garage\">\n" +
                    "                                <img src=\"img/icons/garage.png\" alt=\"\">\n" +
                    "                                <span>" + data[i].function_room + "</span>\n" +
                    "                            </div>\n" +
                    "                            <div class=\"space\">\n" +
                    "                                <img src=\"img/icons/space.png\" alt=\"\">\n" +
                    "                                <span>" + data[i].floor_area + "</span>\n" +
                    "                            </div>\n" +
                    "                        </div>\n" +
                    "                    </div>\n" +
                    "                </div>\n" +
                    "            </div>";

            }
            view.innerHTML = generated;
        },
    });
}

function showDetail(i) {
    var id = document.getElementsByClassName("building-id")[i].value;
    console.log(id);
    sessionStorage.setItem("id-building", id);
    window.location.href = "/buildings-detail";
}

function submitBuilding() {

    var image = $('#images').val();
    var data = {"images": image};
    console.log(image.file[0]);
    console.log(data);

    $.ajax({
        url: '/images/load',
        type: 'POST',
        contentType: 'multipart/form-data', //dinh nghia kieu du lieu gui ve server
        dataType: 'JSON', //dinh nghi kieu du lieu server gui len
        data: new FormData().append("images", image),
        headers: {
            Authorization: 'Bearer ' + document.cookie
        },
        // data: image
        success: function (result) { // result la ket qua server tra ve

            console.log(result);
            window.location = "/admin/new-buildings";

            // var data = $('#buildings').serializeArray();
            // $.ajax({
            //     url: '/buildings',
            //     type: 'POST',
            //     contentType: 'application/json', //dinh nghia kieu du lieu gui ve server
            //     dataType: 'JSON', //dinh nghi kieu du lieu server gui len
            //     data: mapToJson(data),
            //     success: function (result) { // result la ket qua server tra ve
            //         window.location = "/admin/new-buildings";
            //     },
            //     error: function (result){
            //         $('#alert').css('display','')
            //     }
            // });

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

function genBuildingsTable() {
    var view = document.getElementById("buildings-table");
    var data = {
        "page": 1,
        "limit": 20
    }
    $.ajax({

        url: '/buildings',
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
                "            <th scope=\"col\">Địa chỉ</th>" +
                "            <th scope=\"col\">Số phòng ngủ</th>" +
                "            <th scope=\"col\">Số phòng chức năng</th>" +
                "            <th scope=\"col\">Giá</th>" +
                "            <th scope=\"col\">Diện tích sàn</th>" +
                "            <th scope=\"col\">Trạng thái</th>" +
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
                    "      <td>" + data[i].name + "</td>\n" +
                    "      <td>" + data[i].address + "</td>\n" +
                    "      <td>" + data[i].bedroom + "</td>\n" +
                    "      <td>" + data[i].function_room + "</td>\n" +
                    "      <td>" + data[i].price + "</td>\n" +
                    "      <td>" + data[i].floor_area + "</td>\n" +
                    "      <td>" + status + "</td>\n" +
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
