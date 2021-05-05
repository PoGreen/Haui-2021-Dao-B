function genBuildings() {
    $.ajax({
        url: '/buildings/filters',
        type: 'GET',
        contentType: 'application/json', //dinh nghia kieu du lieu gui ve server
        dataType: 'json', //dinh nghi kieu du lieu server gui len
        success: function (result) { // result la ket qua server tra ve
            var data = result.data.data;
            var generated = "";
            $('#buildings').html("<div></div>");
            for (var i = 0; i < 9; i++) {
                var id = data[i].id;
                // console.log(id);
                generated += " <div class=\"col-12 col-md-6 col-xl-4\">\n" +
                    "                <div class=\"single-featured-property mb-50 wow fadeInUp\" data-wow-delay=\"100ms\">\n" +
                    "                    <!-- Property Thumbnail -->\n" +
                    "                    <div class=\"property-thumb\">\n" +
                    "                        <img style='width: 400px; height: 250px' src=\"" + data[i].image_rp + "\" alt=\"\">\n" +
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
            $('#buildings').append(generated);
        },
    });
}
function showDetail(i) {
    var id = document.getElementsByClassName("building-id")[i].value;
    sessionStorage.setItem("id-building", id);
    window.location.href = "/buildings-detail";
}


function searchBuilding() {
    var data = $('#advanceSearch').serializeArray();
    console.log(mapToJson(data));
    $.ajax({
        url: '/buildings/filters/body',
        type: 'POST',
        contentType: 'application/json', //dinh nghia kieu du lieu gui ve server
        dataType: 'JSON', //dinh nghi kieu du lieu server gui len
        data: mapToJson(data),
        success: function (result) { // result la ket qua server tra ve
            var data = result.data.data;
            var generated = "";
            $('#buildings').html("<div></div>");
            var length = data.length < 9 ? data.length : 9;
            for (var i = 0; i < length; i++) {
                var id = data[i].id;
                generated += " <div class=\"col-12 col-md-6 col-xl-4\">\n" +
                    "                <div class=\"single-featured-property mb-50 wow fadeInUp\" data-wow-delay=\"100ms\">\n" +
                    "                    <!-- Property Thumbnail -->\n" +
                    "                    <div class=\"property-thumb\">\n" +
                    "                        <img style='width: 400px; height: 250px' src=\"" + data[i].image_rp + "\" alt=\"\">\n" +
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
            $('#buildings').append(generated);
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

function genBuildingCategoryHome() {
    $.ajax({
        url: '/building-categories?status=1',
        type: 'GET',
        contentType: 'application/json', //dinh nghia kieu du lieu gui ve server
        dataType: 'JSON', //dinh nghi kieu du lieu server gui len

        success: function (result) { // result la ket qua server tra ve
            console.log(result);
            var data = result.data.building_category;
            var leng = data.length;
            var gererated = "<select style='padding: 10px' class=\"form-control\" id=\"buildingCategory\" name=\"buildingCategory\"> \n";
            for (var i = 0; i < leng; i++) {
                gererated += "<option value=" + data[i].id + ">" + data[i].name + "</option>\n";
            }
            gererated += "</select>";
            $('#building-category').append(gererated);

        },
    });
};