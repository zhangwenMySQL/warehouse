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
//
//
//
$("#queryUserIssue").submit(function () {
    $.get("/Car/carIssue/findByQuery", $(this).serialize(), function (data) {
        if (data) {
            loadUserIssue(1, 8, data.carBrand, data.carCheck);
        } else {
            loadUserIssue(1, 8, null, null);
        }
        return false;
    })
    return false;
})

function updateIssue(id) {
    $.post("/Car/car/carCheck", {id: id}, function (data) {
        if (data) {
            location.href = "http://localhost/Car/jsp/carmodify.jsp";
        } else {
            alert("该车辆已被审核，如需修改请联系管理员！")
        }
    })
}

function deleteIssue(id) {
    if (confirm("你确定要删除嘛？")) {
        $.post("/Car/carIssue/deleteIssue", {id: id}, function (data) {
            if (data) {
                alert("删除成功")
                location.href = "http://localhost/Car/jsp/userIssue.jsp";
            } else {
                alert("删除失败")
            }
        })
    }
}
function carIssueSee(id) {
    $.get("/Car/car/carIssueSee", {id: id}, function (data) {
        if (data) {
            location.href = "http://localhost/Car/jsp/photoList.jsp";
        }
    })

}
//
// function checkUser(id) {
//     $.get("/Car/user/userCheck", {id: id}, function (data) {
//         if (data) {
//             location.href = "http://localhost/Car/jsp/userCheck.jsp";
//         }
//     })
//
// }
//
// function deleteUser(id) {
//     if (confirm("你确定要删除嘛？"))
//     $.get("/Car/user/deleteUser", {id: id}, function (data) {
//         if (data) {
//             alert("删除成功");
//             location.href = "http://localhost/Car/jsp/userList.jsp";
//         }
//         return false;
//     })
//     return false;
//
// }
//
// function updateUser(id) {
//     $.post("/Car/user/userCheck", {id: id}, function (data) {
//         if (data) {
//             location.href = "http://localhost/Car/jsp/usermodify.jsp";
//         }
//     })
// }


$(function () {

    loadUserIssue(1, 8, null, null);

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

