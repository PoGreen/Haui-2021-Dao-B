function genBuildings() {
    var view = document.getElementById("buildings");
    var data = {
        "page": 1,
        "limit": 8
    }
    $.ajax({

        url: 'buildings',
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
                    "                        <img src=\"img/bg-img/feature1.jpg\" alt=\"\">\n" +
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
    window.location.href ="/buildings-detail";
}
