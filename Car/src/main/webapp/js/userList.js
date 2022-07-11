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



$("#queryUser").submit(function () {
    $.get("/Car/user/findByQuery", $(this).serialize(), function (data) {
        if (data) {
            loadUser(1, 8, data.userPhone, data.userAuditStatus);
        } else {
            loadUser(1, 8, null, null);
        }
        return false;
    })
    return false;
})

function checkUser(id) {
    $.get("/Car/user/userCheck", {id: id}, function (data) {
        if (data) {
            location.href = "http://localhost/Car/jsp/userCheck.jsp";
        }
    })

}

function deleteUser(id) {
    if (confirm("你确定要删除嘛？"))
    $.get("/Car/user/deleteUser", {id: id}, function (data) {
        if (data) {
            alert("删除成功");
            location.href = "http://localhost/Car/jsp/userList.jsp";
        }
        return false;
    })
    return false;

}

function updateUser(id) {
    $.post("/Car/user/userCheck", {id: id}, function (data) {
        if (data) {
            location.href = "http://localhost/Car/jsp/usermodify.jsp";
        }
    })
}


$(function () {

    loadUser(1, 8, null, null);

    backBtn = $("#back");

    backBtn.on("click",function(){
        //alert("modify: "+referer);
        if(referer != undefined
            && null != referer
            && "" != referer
            && "null" != referer
            && referer.length > 4){
            window.location.href = referer;
        }else{
            history.back(-1);
        }
    });
});

