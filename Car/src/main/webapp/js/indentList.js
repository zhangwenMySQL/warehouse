var userObj;
var backBtn = null;
// $("#rollAction").submit(function () {
//     $.post("/ManagementSystem/user/roll", $(this).serialize(), function (data) {
//         if (data) {
//             load(data, 8, null, null);
//         }
//         return false;
//     })
//     return false;
// })


$("#queryIndent").submit(function () {
    $.get("/Car/indent/findByQuery", $(this).serialize(), function (data) {
        if (data) {
            loadIndent(1, 8, data.indentNo);
        } else {
            loadIndent(1, 8, null);
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

function deleteIndent(id) {
    if (confirm("你确定要删除嘛？"))
        $.get("/Car/indent/deleteIndent", {id: id}, function (data) {
            if (data) {
                alert("删除成功");
                location.href = "http://localhost/Car/jsp/indentList.jsp";
            }
            return false;
        })
    return false;

}

function updateDeal(id) {
    $.post("/Car/indent/updateDeal", {id: id}, function (data) {
        if (data) {
            location.href = "http://localhost/Car/jsp/indentmodify.jsp";
        } else {
            alert("请等待卖家确认！")
        }
    })
}

function readCar(id) {
    $.post("/Car/indent/readCar", {id: id}, function (data) {
        if (data) {
            location.href = "http://localhost/Car/jsp/indentRead.jsp";
        }else{
            alert("车辆信息已被删除")
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

$(function () {

    loadIndent(1, 8, null);

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

