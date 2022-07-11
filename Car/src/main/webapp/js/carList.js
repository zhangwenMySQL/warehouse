var userObj;
var backBtn = null;
// $("#rollAction").submit(function () {
//     $.post("/ManagementSystem/user/roll", $(this).serialize(), function (data) {
//         if (data) {
//             load(data, 5, null, null);
//         }
//         return false;
//     })
//     return false;
// })


$("#queryCar").submit(function () {
    $.get("/Car/car/findByQuery", $(this).serialize(), function (data) {
        if (data) {
            loadCar(1, 8, data.carBrand, data.carPrice, data.carCheck);
        } else {
            loadCar(1, 8, null, null, null);
        }
        return false;
    })
    return false;
})

function checkCar(id) {
    $.get("/Car/car/carCheck", {id: id}, function (data) {
        if (data) {
            location.href = "http://localhost/Car/jsp/carCheck.jsp";
        }
    })

}
function carIssueSee(id) {
    $.get("/Car/car/carIssueSee", {id: id}, function (data) {
        if (data) {
            location.href = "http://localhost/Car/jsp/photoList.jsp";
        }
    })

}

function deleteCar(id) {
    if (confirm("你确定要删除嘛？"))
        $.get("/Car/car/deleteCar", {id: id}, function (data) {
            if (data) {
                alert("删除成功");
                location.href = "http://localhost/Car/jsp/carList.jsp";
            }
            return false;
        })
    return false;

}

function buyCar(id) {
    if (confirm("你确定要下订单吗？"))
        $.get("/Car/car/buyCar", {id: id}, function (data) {
            if (data) {
                alert("您已下过该车辆的订单，请进入订单界面查看")
                location.href ="http://localhost/Car/jsp/carList.jsp";
            }else {
                alert("下订单成功，等待卖家确认");
                location.href = "http://localhost/Car/jsp/carList.jsp";
            }

            return false;
        })
    return false;

}

function updateCar(id) {
    $.post("/Car/car/carCheck", {id: id}, function (data) {
        if (data) {
            location.href = "http://localhost/Car/jsp/carmodify.jsp";
        }
    })
}


$(function () {

    loadCar(1, 8, null, null, null);

    backBtn = $("#back");

    backBtn.on("click", function () {
        //alert("modify: "+referer);
        if (referer != undefined
            && null != referer
            && "" != referer
            && "null" != referer
            && referer.length > 4) {
            window.location.href = referer;
        } else {
            history.back(-1);
        }
    });
});

