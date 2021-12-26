var providerObj;
$("#rollAction").submit(function () {
    $.post("/x4/bill/roll",$(this).serialize(),function (data) {
        if (data){
            loadProvider(data,5,null,null);
        }
    })
    return false;
})
//供应商管理页面上点击删除按钮弹出删除框(providerlist.jsp)
function deleteProvider(obj) {
    $.ajax({
        type: "GET",
        url: path + "/jsp/provider.do",
        data: {method: "delprovider", proid: obj.attr("proid")},
        dataType: "json",
        success: function (data) {
            if (data.delResult == "true") {//删除成功：移除删除行
                cancleBtn();
                obj.parents("tr").remove();
            } else if (data.delResult == "false") {//删除失败
                //alert("对不起，删除供应商【"+obj.attr("proname")+"】失败");
                changeDLGContent("对不起，删除供应商【" + obj.attr("proname") + "】失败");
            } else if (data.delResult == "notexist") {
                //alert("对不起，供应商【"+obj.attr("proname")+"】不存在");
                changeDLGContent("对不起，供应商【" + obj.attr("proname") + "】不存在");
            } else {
                //alert("对不起，该供应商【"+obj.attr("proname")+"】下有【"+data.delResult+"】条订单，不能删除");
                changeDLGContent("对不起，该供应商【" + obj.attr("proname") + "】下有【" + data.delResult + "】条订单，不能删除");
            }
        },
        error: function (data) {
            //alert("对不起，删除失败");
            changeDLGContent("对不起，删除失败");
        }
    });
}

function openYesOrNoDLG() {
    $('.zhezhao').css('display', 'block');
    $('#removeProv').fadeIn();
}

function cancleBtn() {
    $('.zhezhao').css('display', 'none');
    $('#removeProv').fadeOut();
}

function changeDLGContent(contentStr) {
    var p = $(".removeMain").find("p");
    p.html(contentStr);
}
function del(id) {
    if (confirm("您确认要删除嘛？")) {
        location.href = "/x4/pro/del/" + id;
    }
}
function updataPro(id){
    $.post("/x4/pro/updataPro",{id:id},function (data) {
        if (data){
            location.href="http://localhost/x4/jsp/providermodify.jsp";
        }
    })
}
function queryPro(id){
    $.post("/x4/pro/updataPro",{id:id},function (data) {
        if (data){
            location.href="http://localhost/x4/jsp/providerview.jsp";
        }
    })
}
$(function () {
    loadProvider(1, 5, null, null);
    $("#proForm").submit(function () {
        $.post("/x4/pro/findByQuery", $(this).serialize(), function (data) {
            if (data.procode && data.proname) {
                loadProvider(1, 5, data.procode, data.proname);
            } else if (data.procode) {
                loadProvider(1, 5, data.procode, null);
            } else if (data.proname) {
                loadProvider(1, 5, null, data.proname);
            } else {
                loadProvider(1, 5, null, null);
            }
        });
        return false;
    })
    $(".viewProvider").on("click", function () {
        //将被绑定的元素（a）转换成jquery对象，可以使用jquery方法
        var obj = $(this);
        window.location.href = path + "/jsp/provider.do?method=view&proid=" + obj.attr("proid");
    });

    $(".modifyProvider").on("click", function () {
        var obj = $(this);
        window.location.href = path + "/jsp/provider.do?method=modify&proid=" + obj.attr("proid");
    });

    $('#no').click(function () {
        cancleBtn();
    });

    $('#yes').click(function () {
        deleteProvider(providerObj);
    });

    $(".deleteProvider").on("click", function () {
        providerObj = $(this);
        changeDLGContent("你确定要删除供应商【" + providerObj.attr("proname") + "】吗？");
        openYesOrNoDLG();
    });

    /*	$(".deleteProvider").on("click",function(){
            var obj = $(this);
            if(confirm("你确定要删除供应商【"+obj.attr("proname")+"】吗？")){
                $.ajax({
                    type:"GET",
                    url:path+"/jsp/provider.do",
                    data:{method:"delprovider",proid:obj.attr("proid")},
                    dataType:"json",
                    success:function(data){
                        if(data.delResult == "true"){//删除成功：移除删除行
                            alert("删除成功");
                            obj.parents("tr").remove();
                        }else if(data.delResult == "false"){//删除失败
                            alert("对不起，删除供应商【"+obj.attr("proname")+"】失败");
                        }else if(data.delResult == "notexist"){
                            alert("对不起，供应商【"+obj.attr("proname")+"】不存在");
                        }else{
                            alert("对不起，该供应商【"+obj.attr("proname")+"】下有【"+data.delResult+"】条订单，不能删除");
                        }
                    },
                    error:function(data){
                        alert("对不起，删除失败");
                    }
                });
            }
        });*/
});