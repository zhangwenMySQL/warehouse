$(function () {
    $.get("header.html",function (data) {
        $("#header").html(data);
    });
    $.get("footer.html",function (data) {
        $("#footer").html(data);
    });
});
$("#registerCar").submit(function () {
    $.post("/Car/user/register", $(this).serialize(), function (data) {
        //  console.log(data);
        if (data){
            alert("注册申请提交成功，等待管理员审核");
            location.href="http://localhost/Car/login.jsp";
        }else {
            alert("该电话号码已经注册过，无需重复操作");
            location.href="http://localhost/Car/login.jsp";
        }
        return false;
    })
    return false;
})