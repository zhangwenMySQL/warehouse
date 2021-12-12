<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <%--    <script type="application/javascript" src="../js/jquery-3.3.1.js"/>--%>
    <meta charset="UTF-8">
    <title>超市订单管理系统</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/public.css"/>

    <script>
        function load(pageNum, pageSize, queryname, queryUserRole) {
            $.post("${pageContext.request.contextPath}/user/findByPage", {
                pageNum: pageNum,
                pageSize: pageSize,
                queryname: queryname,
                queryUserRole: queryUserRole
            }, function (data) {
                console.log(data);
                var tableStr = '<tr class="firstTr">\n' +
                    '                    <th width="10%">用户编码</th>\n' +
                    '                    <th width="20%">用户名称</th>\n' +
                    '                    <th width="10%">性别</th>\n' +
                    '                    <th width="10%">出生年月</th>\n' +
                    '                    <th width="10%">电话</th>\n' +
                    '                    <th width="10%">用户角色</th>\n' +
                    '                    <th width="30%">操作</th>\n' +
                    '                </tr>';
                for (var i = 0; i < data.list.length; i++) {
                    tableStr += '<tr>\n' +
                        '\t\t\t\t\t\t<td>\n' +
                        '\t\t\t\t\t\t<span>' + data.list[i].usercode + '</span>\n' +
                        '\t\t\t\t\t\t</td>\n' +
                        '\t\t\t\t\t\t<td>\n' +
                        '\t\t\t\t\t\t<span>' + data.list[i].username + '</span>\n' +
                        '\t\t\t\t\t\t</td>\n' +
                        '\t\t\t\t\t\t<td>\n' +
                        '\t\t\t\t\t\t\t<span>\n';
                    if (data.list[i].gender == 1) {
                        tableStr += '\t\t\t\t\t\t\t\t男\n';
                    } else {
                        tableStr += '\t\t\t\t\t\t\t\t女\n';
                    }
                    tableStr += '\t\t\t\t\t\t\t</span>\n' +
                        '\t\t\t\t\t\t</td>\n' +
                        '\t\t\t\t\t\t<td>\n' +
                        '\t\t\t\t\t\t<span>' + data.list[i].birthday + '</span>\n' +
                        '\t\t\t\t\t\t</td>\n' +
                        '\t\t\t\t\t\t<td>\n' +
                        '\t\t\t\t\t\t<span>' + data.list[i].phone + '</span>\n' +
                        '\t\t\t\t\t\t</td>\n' +
                        '\t\t\t\t\t\t<td>\n' +
                        '\t\t\t\t\t\t\t<span>' + data.list[i].userrole + '</span>\n' +
                        '\t\t\t\t\t\t</td>\n' +
                        '\t\t\t\t\t\t<td>\n' +
                        '\t\t\t\t\t\t<span><a class="viewUser" href="javascript:;" ><img src="../images/read.png" alt="查看" title="查看"/></a></span>\n' +
                        '\t\t\t\t\t\t<span><a class="modifyUser" href="javascript:;"><img src="../images/xiugai.png" alt="修改" title="修改"/></a></span>\n' +
                        '\t\t\t\t\t\t<span><a class="deleteUser" href="#" onclick="del(' + data.list[i].id + ')" ><img src="../images/schu.png" alt="删除" title="删除"/></a></span>\n' +
                        '\t\t\t\t\t\t</td>\n' +
                        '\t\t\t\t\t</tr>';

                }
                var bPage = data.pageNum - 1;
                if (bPage < 1) {
                    bPage = 1;
                }
                var aPage = data.pageNum + 1;
                if (aPage > data.pages) {
                    aPage = data.pages;
                }
                var divStr = '<button onclick="load(1,' + data.pageSize + ',' + queryname + ',' + queryUserRole + ')">首页</button>\n' +
                    '\t\t\t\t\t<button onclick="load(' + bPage + ',' + data.pageSize + ',' + queryname + ',' + queryUserRole + ')">上一页</button>\n' +
                    '\t\t\t\t\t' + data.pageNum + '/' + data.pages + '\n' +
                    '\t\t\t\t\t<button onclick="load(' + aPage + ',' + data.pageSize + ',' + queryname + ',' + queryUserRole + ')">下一页</button>\n' +
                    '\t\t\t\t\t<button onclick="load(' + data.pages + ',' + data.pageSize + ',' + queryname + ',' + queryUserRole + ')">末页</button>'
                $("#user_table").html(tableStr);
                $("#div").html(divStr);
            });
        }

        function loadBill(pageNum, pageSize, productname, ispayment, providerid) {
            $.get("/x4/bill/findByAll", {
                pageNum: pageNum,
                pageSize: pageSize,
                productname: productname,
                ispayment: ispayment,
                providerid: providerid
            }, function (data) {
                var str = '';

                str += '<tr class="firstTr">\n' +
                    '       <th width="10%">订单编码</th>\n' +
                    '  <th width="15%">商品名称</th>\n' +
                    '  <th width="15%">供应商</th>\n' +
                    '  <th width="10%">订单金额</th>\n' +
                    '  <th width="10%">是否付款</th>\n' +
                    '  <th width="10%">创建时间</th>\n' +
                    '  <th width="30%">操作</th>\n' +
                    '  </tr>';
                for (var i = 0; i < data.list.length; i++) {
                    str += ' <tr>\n' +
                        '\t\t\t\t\t\t<td>\n' +
                        '\t\t\t\t\t\t<span>' + data.list[i].billcode + '</span>\n' +
                        '\t\t\t\t\t\t</td>\n' +
                        '\t\t\t\t\t\t   <td>\n' +
                        ' \t\t\t\t\t\t  <span>' + data.list[i].productname + '</span>\n' +
                        ' \t\t\t\t\t\t  </td>\n' +
                        ' \t\t\t\t\t\t  <td>\n' +
                        '  \t\t\t\t\t\t\t\t\t  <span>' + data.list[i].providerid + '</span>\n' +
                        ' \t\t\t\t\t\t  </td>\n' +
                        ' \t\t\t\t\t\t  <td>\n' +
                        ' \t\t\t\t\t\t  <span>' + data.list[i].totalprice + '</span>\n' +
                        ' \t\t\t\t\t\t  </td>\n' +
                        ' \t\t\t\t\t\t  <td>\n' +
                        ' \t\t\t\t\t\t  <span>\n';
                    if (data.list[i].ispayment == 1) {
                        str += '\t\t\t\t\t\t 未付款\n';
                    } else {
                        str += '\t\t\t\t\t\t 已付款\n';
                    }

                    str += '\t\t\t\t\t\t</span>\n' +
                        '\t\t\t\t\t\t</td>\n' +
                        '\t\t\t\t\t\t<td>\n' +
                        '\t\t\t\t\t\t<span>\n' +
                        '\t\t\t\t\t\t '+data.list[i].creationdate+'\n' +
                        '\t\t\t\t\t\t</span>\n' +
                        '\t\t\t\t\t\t</td>\n' +
                        '\t\t\t\t\t\t<td>\n' +
                        '\t\t\t\t\t\t<span><a class="viewUser" href="javascript:;" ><img src="../images/read.png" alt="查看" title="查看"/></a></span>\n' +
                        '\t\t\t\t\t\t<span><a class="modifyUser" href="javascript:;"><img src="../images/xiugai.png" alt="修改" title="修改"/></a></span>\n' +
                        '\t\t\t\t\t\t<span><a class="deleteUser" href="#" onclick="del(' + data.list[i].id + ')" ><img src="../images/schu.png" alt="删除" title="删除"/></a></span>\n' +
                        '\t\t\t\t\t\t</td>\n' +
                        '\t\t\t\t\t\t</tr>';
                }
                var bPage = data.pageNum - 1;
                if (bPage < 1) {
                    bPage = 1;
                }
                var aPage = data.pageNum + 1;
                if (aPage > data.pages) {
                    aPage = data.pages;
                }
                var divStr = '<button onclick="loadBill(1,' + data.pageSize + ',' + productname + ',' + ispayment + ',' + providerid + ')">首页</button>\n' +
                    '\t\t\t\t\t<button onclick="loadBill(' + bPage + ',' + data.pageSize + ',' + productname + ',' + ispayment + ',' + providerid + ')">上一页</button>\n' +
                    '\t\t\t\t\t' + data.pageNum + '/' + data.pages + '\n' +
                    '\t\t\t\t\t<button onclick="loadBill(' + aPage + ',' + data.pageSize + ',' + productname + ',' + ispayment + ',' + providerid + ')">下一页</button>\n' +
                    '\t\t\t\t\t<button onclick="loadBill(' + data.pages + ',' + data.pageSize + ',' + productname + ',' + ispayment + ',' + providerid + ')">末页</button>'
                // $("#user_table").html(tableStr);
                // $("#div").html(divStr);
                $("#billTable").html(str);
                $("#billDiv").html(divStr);
            });
            return false;
        };

        function loadProvider(pageNum, pageSize, queryProCode, queryProName) {
            $.post("${pageContext.request.contextPath}/pro/findByAll", {
                pageNum: pageNum,
                pageSize: pageSize,
                queryProCode: queryProCode,
                queryProName: queryProName
            }, function (data) {
                // console.log(data);
                 var tableStr = '<tr class="firstTr">\n' +
                     '                    <th width="10%">供应商编码</th>\n' +
                     '                    <th width="20%">供应商名称</th>\n' +
                     '                    <th width="10%">联系人</th>\n' +
                     '                    <th width="10%">联系电话</th>\n' +
                     '                    <th width="10%">传真</th>\n' +
                     '                    <th width="10%">创建时间</th>\n' +
                     '                    <th width="30%">操作</th>\n' +
                     '                </tr>';
                for (var i = 0; i < data.list.length; i++) {
                    tableStr += '<tr>\n' +
                        '\t\t\t\t\t\t<td>\n' +
                        '\t\t\t\t\t\t<span>' + data.list[i].procode + '</span>\n' +
                        '\t\t\t\t\t\t</td>\n' +
                        '\t\t\t\t\t\t<td>\n' +
                        '\t\t\t\t\t\t<span>' + data.list[i].proname + '</span>\n' +
                        '\t\t\t\t\t\t</td>\n' +
                        '\t\t\t\t\t\t<td>\n' +
                        '\t\t\t\t\t\t<span>' + data.list[i].procontact + '</span>\n' +
                        '\t\t\t\t\t\t</td>\n' +
                        '\t\t\t\t\t\t<td>\n' +
                        '\t\t\t\t\t\t<span>' + data.list[i].prophone + '</span>\n' +
                        '\t\t\t\t\t\t</td>\n' +
                        '\t\t\t\t\t\t<td>\n' +
                        '\t\t\t\t\t\t<span>' + data.list[i].profax + '</span>\n' +
                        '\t\t\t\t\t\t</td>\n' +
                        '\t\t\t\t\t\t<td>\n' +
                        '\t\t\t\t\t\t<span>' + data.list[i].creationdate + '</span>\n' +
                        '\t\t\t\t\t\t</td>\n' +
                        '\t\t\t\t\t\t<td>\n' +
                        '\t\t\t\t\t\t\t<span>\n' +
                        '\t\t\t\t\t\t<span><a class="viewUser" href="#" onclick="queryPro('+data.list[i].id+')"><img src="../images/read.png" alt="查看" title="查看"/></a></span>\n' +
                        '\t\t\t\t\t\t<span><a class="modifyUser" href="#" onclick="updataPro('+data.list[i].id+')"><img src="../images/xiugai.png" alt="修改" title="修改"/></a></span>\n' +
                        '\t\t\t\t\t\t<span><a class="deleteUser" href="#" onclick="del(' + data.list[i].id + ')" ><img src="../images/schu.png" alt="删除" title="删除"/></a></span>\n' +
                        '\t\t\t\t\t\t</td>\n' +
                        '\t\t\t\t\t</tr>';

                }
                var bPage = data.pageNum - 1;
                if (bPage < 1) {
                    bPage = 1;
                }
                var aPage = data.pageNum + 1;
                if (aPage > data.pages) {
                    aPage = data.pages;
                }
                var divStr = '<button onclick="loadProvider(1,' + data.pageSize + ',' + queryProCode + ',' + queryProName + ')">首页</button>\n' +
                    '\t\t\t\t\t<button onclick="loadProvider(' + bPage + ',' + data.pageSize + ',' + queryProCode + ',' + queryProName + ')">上一页</button>\n' +
                    '\t\t\t\t\t' + data.pageNum + '/' + data.pages + '\n' +
                    '\t\t\t\t\t<button onclick="loadProvider(' + aPage + ',' + data.pageSize + ',' + queryProCode + ',' + queryProName + ')">下一页</button>\n' +
                    '\t\t\t\t\t<button onclick="loadProvider(' + data.pages + ',' + data.pageSize + ',' + queryProCode + ',' + queryProName + ')">末页</button>'
                $("#proTable").html(tableStr);
                $("#proDiv").html(divStr);
            });
        }

    </script>
</head>
<body>
<!--头部-->
<header class="publicHeader">
    <h1>超市订单管理系统</h1>
    <div class="publicHeaderR">
        <p><span>下午好！</span><span style="color: #fff21b"> ${user.username}</span> , 欢迎你！</p>
        <a href="${pageContext.request.contextPath }/login.jsp">退出</a>
    </div>
</header>
<!--时间-->
<section class="publicTime">
    <span id="time">2015年1月1日 11:11  星期一</span>
    <a href="#">温馨提示：为了能正常浏览，请使用高版本浏览器！（IE10+）</a>
</section>
<!--主体内容-->
<section class="publicMian ">
    <div class="left">
        <h2 class="leftH2"><span class="span1"></span>功能列表 <span></span></h2>
        <nav>
            <ul class="list">
                <li><a href="${pageContext.request.contextPath }/jsp/billlist.jsp">订单管理</a></li>
                <li><a href="${pageContext.request.contextPath }/jsp/providerlist.jsp">供应商管理</a></li>
                <li><a href="${pageContext.request.contextPath }/jsp/userlist.jsp">用户管理</a></li>
                <li><a href="${pageContext.request.contextPath }/jsp/pwdmodify.jsp">密码修改</a></li>
                <li><a href="${pageContext.request.contextPath }/login.jsp">退出系统</a></li>
            </ul>
        </nav>
    </div>

    <input type="hidden" id="path" name="path" value="${pageContext.request.contextPath }"/>
    <input type="hidden" id="referer" name="referer" value="<%=request.getHeader("Referer")%>"/>
