<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <%--    <script type="application/javascript" src="../js/jquery-3.3.1.js"/>--%>
    <meta charset="UTF-8">
    <title>二手车交易系统</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/public.css"/>

    <script>
        function loadUser(pageNum, pageSize, userPhone, userAuditStatus) {
            $.post("${pageContext.request.contextPath}/user/findByPage", {
                pageNum: pageNum,
                pageSize: pageSize,
                userPhone: userPhone,
                userAuditStatus: userAuditStatus
            }, function (data) {
                console.log(data);
                var tableStr = '<tr class="firstTr">\n' +
                    '                    <th width="10%">姓名</th>\n' +
                    '                    <th width="15%">电话</th>\n' +
                    '                    <th width="15%">Email</th>\n' +
                    '                    <th width="15%">地址</th>\n' +
                    '                    <th width="15%">审核状态</th>\n' +
                    '                    <th width="10%">审核</th>\n' +
                    '                    <th width="10%">修改</th>\n' +
                    '                    <th width="10%">删除</th>\n' +
                    '                </tr>';
                for (var i = 0; i < data.list.length; i++) {
                    tableStr += '<tr>\n' +
                        '\t\t\t\t\t\t<td>\n' +
                        '\t\t\t\t\t\t<span>' + data.list[i].userName + '</span>\n' +
                        '\t\t\t\t\t\t</td>\n' +
                        '\t\t\t\t\t\t<td>\n' +
                        '\t\t\t\t\t\t<span>' + data.list[i].userPhone + '</span>\n' +
                        '\t\t\t\t\t\t</td>\n' +
                        '\t\t\t\t\t\t<td>\n' +
                        '\t\t\t\t\t\t<span>' + data.list[i].userEmail + '</span>\n' +
                        '\t\t\t\t\t\t</td>\n' +
                        '\t\t\t\t\t\t<td>\n' +
                        '\t\t\t\t\t\t<span>' + data.list[i].userAddress + '</span>\n' +
                        '\t\t\t\t\t\t</td>\n' +
                        ' \t\t\t\t\t\t  <td>\n' +
                        '  \t\t\t\t\t\t\t\t\t  <span>';
                    if (data.list[i].userAuditStatus == 0) {
                        tableStr += '\t\t\t\t\t\t通过\n';
                    } else if (data.list[i].userAuditStatus == 1) {
                        tableStr += '\t\t\t\t\t\t待审核\n';
                    } else {
                        tableStr += '\t\t\t\t\t\t不通过\n';
                    }
                    tableStr += '</span>\n' +
                        ' \t\t\t\t\t\t  </td>\n';
                    tableStr += '\t\t\t\t\t\t<td>\n' +
                        '\t\t\t\t\t\t<span><a class="modifyUser" href="#" onclick="checkUser(' + data.list[i].userId + ')"><img src="../images/read.png" alt="审核" title="审核"/></a></span>\n' +
                        '\t\t\t\t\t\t</td>\n';
                    tableStr += '\t\t\t\t\t\t<td>\n' +
                        '\t\t\t\t\t\t<span><a class="modifyUser" href="#" onclick="updateUser(' + data.list[i].userId + ')"><img src="../images/xiugai.png" alt="修改" title="修改"/></a></span>\n' +
                        '\t\t\t\t\t\t</td>\n';
                    tableStr += '\t\t\t\t\t\t<td>\n' +
                        '\t\t\t\t\t\t<span><a class="modifyUser" href="#" onclick="deleteUser(' + data.list[i].userId + ')"><img src="../images/schu.png" alt="删除" title="删除"/></a></span>\n' +
                        '\t\t\t\t\t\t</td>\n';

                    tableStr += '\t\t\t\t\t</tr>';

                }
                var bPage = data.pageNum - 1;
                if (bPage < 1) {
                    bPage = 1;
                }
                var aPage = data.pageNum + 1;
                if (aPage > data.pages) {
                    aPage = data.pages;
                }
                var divStr = '<button onclick="loadUser(1,' + data.pageSize + ',' + userPhone + ',' + userAuditStatus + ')">首页</button>\n' +
                    '\t\t\t\t\t<button onclick="loadUser(' + bPage + ',' + data.pageSize + ',' + userPhone + ',' + userAuditStatus + ')">上一页</button>\n' +
                    '\t\t\t\t\t' + data.pageNum + '/' + data.pages + '\n' +
                    '\t\t\t\t\t<button onclick="loadUser(' + aPage + ',' + data.pageSize + ',' + userPhone + ',' + userAuditStatus + ')">下一页</button>\n' +
                    '\t\t\t\t\t<button onclick="loadUser(' + data.pages + ',' + data.pageSize + ',' + userPhone + ',' + userAuditStatus + ')">末页</button>'
                $("#user_table").html(tableStr);
                $("#user_div").html(divStr);
            });
        }

        function loadCar(pageNum, pageSize, carBrand, carPrice, carCheck) {
            $.post("${pageContext.request.contextPath}/car/findByPage", {
                pageNum: pageNum,
                pageSize: pageSize,
                carBrand: carBrand,
                carPrice: carPrice,
                carCheck: carCheck
            }, function (data) {
                console.log(data);
                var tableStr = '<tr class="firstTr">\n' +
                    '                    <th width="10%">品牌</th>\n' +
                    '                    <th width="10%">系列</th>\n' +
                    '                    <th width="10%">年限</th>\n' +
                    '                    <th width="10%">价格</th>\n' +
                    '                    <th width="15%">车主电话</th>\n';
                if (${user.roleId==1}) {
                    tableStr +=
                        '                    <th width="15%">审核状态</th>\n' +
                        '                    <th width="10%">审核</th>\n' +
                        '                    <th width="10%">修改</th>\n' +
                        '                    <th width="10%">删除</th>\n' +
                        '                </tr>';
                } else {
                    tableStr += '<th width="10%">下订单</th>\n' +
                        '</tr>';
                }
                for (var i = 0; i < data.list.length; i++) {
                    tableStr += '<tr>\n' +
                        '\t\t\t\t\t\t<td>\n' +
                        '\t\t\t\t\t\t<span><a href="#" onclick="carIssueSee(' + data.list[i].carId + ')">' + data.list[i].carBrand + '</a></span>\n' +
                        '\t\t\t\t\t\t</td>\n' +
                        '\t\t\t\t\t\t<td>\n' +
                        '\t\t\t\t\t\t<span>' + data.list[i].carSeries + '</span>\n' +
                        '\t\t\t\t\t\t</td>\n' +
                        '\t\t\t\t\t\t<td>\n' +
                        '\t\t\t\t\t\t<span>' + data.list[i].carAgeLimit + '</span>\n' +
                        '\t\t\t\t\t\t</td>\n' +
                        '\t\t\t\t\t\t<td>\n' +
                        '\t\t\t\t\t\t<span>' + data.list[i].carPrice + ' 万</span>\n' +
                        '\t\t\t\t\t\t</td>\n' +
                        '\t\t\t\t\t\t<td>\n' +
                        '\t\t\t\t\t\t<span>' + data.list[i].userId + '</span>\n' +
                        '\t\t\t\t\t\t</td>\n';
                    if (${user.roleId==1}) {
                        tableStr += ' \t\t\t\t\t\t  <td>\n' +
                            '  \t\t\t\t\t\t\t\t\t  <span>';
                        if (data.list[i].carCheck == 0) {
                            tableStr += '\t\t\t\t\t\t通过\n';
                        } else if (data.list[i].carCheck == 1) {
                            tableStr += '\t\t\t\t\t\t待审核\n';
                        } else {
                            tableStr += '\t\t\t\t\t\t不通过\n';
                        }
                        tableStr += '</span>\n' +
                            ' \t\t\t\t\t\t  </td>\n';
                        tableStr += '\t\t\t\t\t\t<td>\n' +
                            '\t\t\t\t\t\t<span><a class="modifyUser" href="#" onclick="checkCar(' + data.list[i].carId + ')"><img src="../images/read.png" alt="审核" title="审核"/></a></span>\n' +
                            '\t\t\t\t\t\t</td>\n';
                        tableStr += '\t\t\t\t\t\t<td>\n' +
                            '\t\t\t\t\t\t<span><a class="modifyUser" href="#" onclick="updateCar(' + data.list[i].carId + ')"><img src="../images/xiugai.png" alt="修改" title="修改"/></a></span>\n' +
                            '\t\t\t\t\t\t</td>\n';
                        tableStr += '\t\t\t\t\t\t<td>\n' +
                            '\t\t\t\t\t\t<span><a class="modifyUser" href="#" onclick="deleteCar(' + data.list[i].carId + ')"><img src="../images/schu.png" alt="删除" title="删除"/></a></span>\n' +
                            '\t\t\t\t\t\t</td>\n';
                    } else {
                        tableStr += '\t\t\t\t\t\t<td>\n' +
                            '\t\t\t\t\t\t<span><a class="modifyUser" href="#" onclick="buyCar(' + data.list[i].carId + ')"><img src="../images/buy.png" alt="下订单" title="下订单"/></a></span>\n' +
                            '\t\t\t\t\t\t</td>\n';
                    }

                    tableStr += '\t\t\t\t\t</tr>';

                }
                var bPage = data.pageNum - 1;
                if (bPage < 1) {
                    bPage = 1;
                }
                var aPage = data.pageNum + 1;
                if (aPage > data.pages) {
                    aPage = data.pages;
                }
                var divStr = '<button onclick="loadCar(1,' + data.pageSize + ',' + carBrand + ',' + carPrice + ',' + carCheck + ')">首页</button>\n' +
                    '\t\t\t\t\t<button onclick="loadCar(' + bPage + ',' + data.pageSize + ',' + carBrand + ',' + carPrice + ',' + carCheck + ')">上一页</button>\n' +
                    '\t\t\t\t\t' + data.pageNum + '/' + data.pages + '\n' +
                    '\t\t\t\t\t<button onclick="loadCar(' + aPage + ',' + data.pageSize + ',' + carBrand + ',' + carPrice + ',' + carCheck + ')">下一页</button>\n' +
                    '\t\t\t\t\t<button onclick="loadCar(' + data.pages + ',' + data.pageSize + ',' + carBrand + ',' + carPrice + ',' + carCheck + ')">末页</button>'
                $("#car_table").html(tableStr);
                $("#car_div").html(divStr);
            });
        }

        function loadIndent(pageNum, pageSize, indentNo) {
            $.post("${pageContext.request.contextPath}/indent/findByPage", {
                    pageNum: pageNum,
                    pageSize: pageSize,
                    indentNo: indentNo
                }, function (data) {
                    console.log(data);
                    var tableStr = '<tr class="firstTr">\n' +
                        '                    <th width="15%">订单编码</th>\n' ;
                    if (${user.roleId==1}) {
                        tableStr +=
                            '                    <th width="10%">车辆序号</th>\n';
                    }
                    tableStr+=
                        '                    <th width="15%">买家用户</th>\n' +
                        '                    <th width="15%">卖家用户</th>\n' +
                        '                    <th width="10%">交易状态</th>\n' +
                        '                    <th width="10%">查看</th>\n' +
                        '                    <th width="10%">修改</th>\n' +
                        '                    <th width="10%">删除</th>\n' +
                        '                </tr>';
                    for (var i = 0; i < data.list.length; i++) {
                        tableStr += '<tr>\n' +
                            '\t\t\t\t\t\t<td>\n' +
                            '\t\t\t\t\t\t<span><a href="#" onclick="carIssueSee(' + data.list[i].carId + ')">' + data.list[i].indentNo + '</a></span>\n' +
                            '\t\t\t\t\t\t</td>\n' ;
                        if (${user.roleId==1}) {
                            tableStr +=
                                '\t\t\t\t\t\t<td>\n' +
                                '\t\t\t\t\t\t<span>' + data.list[i].carId + '</span>\n' +
                                '\t\t\t\t\t\t</td>\n';
                        }
                        tableStr+=
                            '\t\t\t\t\t\t<td>\n' +
                            '\t\t\t\t\t\t<span>' + data.list[i].buyerUserId + '</span>\n' +
                            '\t\t\t\t\t\t</td>\n' +
                            '\t\t\t\t\t\t<td>\n' +
                            '\t\t\t\t\t\t<span>' + data.list[i].sellerUserId + ' </span>\n' +
                            '\t\t\t\t\t\t</td>\n' +
                            ' \t\t\t\t\t\t  <td>\n' +
                            '  \t\t\t\t\t\t\t\t\t  <span>';
                        if (data.list[i].isDeal == 1) {
                            tableStr += '\t\t\t\t\t\t待交易\n';
                        } else if (data.list[i].isDeal == 0) {
                            tableStr += '\t\t\t\t\t\t交易成功\n';
                        } else {
                            tableStr += '\t\t\t\t\t\t交易失败\n';
                        }
                        tableStr += '</span>\n' +
                            ' \t\t\t\t\t\t  </td>\n';
                        tableStr += '\t\t\t\t\t\t<td>\n' +
                            '\t\t\t\t\t\t<span><a class="modifyUser" href="#" onclick="readCar(' + data.list[i].carId + ')"><img src="../images/read.png" alt="查看" title="查看"/></a></span>\n' +
                            '\t\t\t\t\t\t</td>\n';
                        if (data.list[i].isDeal == 1) {
                            tableStr += '\t\t\t\t\t\t<td>\n' +
                                '\t\t\t\t\t\t<span><a class="modifyUser" href="#" onclick="updateDeal(' + data.list[i].indentId + ')"><img src="../images/xiugai.png" alt="修改" title="修改"/></a></span>\n' +
                                '\t\t\t\t\t\t</td>\n';
                        } else {
                            tableStr += '\t\t\t\t\t\t<td>\n' +
                                '\t\t\t\t\t\t<span><a class="modifyUser" href="#"><img src="" alt="" title=""/></a></span>\n' +
                                '\t\t\t\t\t\t</td>\n';
                        }
                        tableStr += '\t\t\t\t\t\t<td>\n' +
                            '\t\t\t\t\t\t<span><a class="modifyUser" href="#" onclick="deleteIndent(' + data.list[i].indentId + ')"><img src="../images/schu.png" alt="删除" title="删除"/></a></span>\n' +
                            '\t\t\t\t\t\t</td>\n';

                        tableStr += '\t\t\t\t\t</tr>';

                    }
                    var bPage = data.pageNum - 1;
                    if (bPage < 1) {
                        bPage = 1;
                    }
                    var aPage = data.pageNum + 1;
                    if (aPage > data.pages) {
                        aPage = data.pages;
                    }
                    var divStr = '<button onclick="loadIndent(1,' + data.pageSize + ',' + indentNo + ')">首页</button>\n' +
                        '\t\t\t\t\t<button onclick="loadIndent(' + bPage + ',' + data.pageSize + ',' + indentNo + ')">上一页</button>\n' +
                        '\t\t\t\t\t' + data.pageNum + '/' + data.pages + '\n' +
                        '\t\t\t\t\t<button onclick="loadIndent(' + aPage + ',' + data.pageSize + ',' + indentNo + ')">下一页</button>\n' +
                        '\t\t\t\t\t<button onclick="loadIndent(' + data.pages + ',' + data.pageSize + ',' + indentNo + ')">末页</button>'
                    $("#indent_table").html(tableStr);
                    $("#indent_div").html(divStr);
                }
            )
            ;
        }

        function loadUserIssue(pageNum, pageSize, carBrand, issueCheck) {
            $.post("${pageContext.request.contextPath}/carIssue/findByPage", {
                pageNum: pageNum,
                pageSize: pageSize,
                carBrand: carBrand,
                issueCheck: issueCheck
            }, function (data) {
                console.log(data);
                var tableStr = '<tr class="firstTr">\n' +
                    '                    <th width="10%">品牌</th>\n' +
                    '                    <th width="10%">系列</th>\n' +
                    '                    <th width="15%">年限</th>\n' +
                    '                    <th width="10%">价格</th>\n' +
                    '                    <th width="15%">车主电话</th>\n' +
                    '                    <th width="10%">交易状态</th>\n' +
                    '                    <th width="10%">审核状态</th>\n' +
                    '                    <th width="10%">修改</th>\n' +
                    '                    <th width="10%">删除</th>\n' +
                    '                </tr>';
                for (var i = 0; i < data.list.length; i++) {
                    tableStr += '<tr>\n' +
                        '\t\t\t\t\t\t<td>\n' +
                        '\t\t\t\t\t\t<span><a href="#" onclick="carIssueSee(' + data.list[i].carId + ')">' + data.list[i].carBrand + '</a></span>\n' +
                        '\t\t\t\t\t\t</td>\n' +
                        '\t\t\t\t\t\t<td>\n' +
                        '\t\t\t\t\t\t<span>' + data.list[i].carSeries + '</span>\n' +
                        '\t\t\t\t\t\t</td>\n' +
                        '\t\t\t\t\t\t<td>\n' +
                        '\t\t\t\t\t\t<span>' + data.list[i].carAgeLimit + '</span>\n' +
                        '\t\t\t\t\t\t</td>\n' +
                        '\t\t\t\t\t\t<td>\n' +
                        '\t\t\t\t\t\t<span>' + data.list[i].carPrice + ' 万</span>\n' +
                        '\t\t\t\t\t\t</td>\n' +
                        '\t\t\t\t\t\t<td>\n' +
                        '\t\t\t\t\t\t<span>' + data.list[i].userId + ' </span>\n' +
                        '\t\t\t\t\t\t</td>\n' +
                        ' \t\t\t\t\t\t  <td>\n' +
                        '  \t\t\t\t\t\t\t\t\t  <span>';
                    if (data.list[i].carCheck == 1) {
                        tableStr += '\t\t\t\t\t\t待交易\n';
                    } else if (data.list[i].carCheck == 0) {
                        tableStr += '\t\t\t\t\t\t交易成功\n';
                    } else {
                        tableStr += '\t\t\t\t\t\t交易失败\n';
                    }
                    tableStr += '</span>\n' +
                        ' \t\t\t\t\t\t  </td>\n';
                    tableStr += ' \t\t\t\t\t\t  <td>\n' +
                        '  \t\t\t\t\t\t\t\t\t  <span>';
                    if (data.list[i].issueCheck == 1) {
                        tableStr += '\t\t\t\t\t\t待审核\n';
                    } else if (data.list[i].issueCheck == 0) {
                        tableStr += '\t\t\t\t\t\t审核通过\n';
                    } else {
                        tableStr += '\t\t\t\t\t\t审核未通过\n';
                    }
                    tableStr += '\t\t\t\t\t\t<td>\n' +
                        '\t\t\t\t\t\t<span><a class="modifyUser" href="#" onclick="updateIssue(' + data.list[i].carId + ')"><img src="../images/xiugai.png" alt="修改" title="修改"/></a></span>\n' +
                        '\t\t\t\t\t\t</td>\n';
                    tableStr += '\t\t\t\t\t\t<td>\n' +
                        '\t\t\t\t\t\t<span><a class="modifyUser" href="#" onclick="deleteIssue(' + data.list[i].carId + ')"><img src="../images/schu.png" alt="删除" title="删除"/></a></span>\n' +
                        '\t\t\t\t\t\t</td>\n';

                    tableStr += '\t\t\t\t\t</tr>';

                }
                var bPage = data.pageNum - 1;
                if (bPage < 1) {
                    bPage = 1;
                }
                var aPage = data.pageNum + 1;
                if (aPage > data.pages) {
                    aPage = data.pages;
                }
                var divStr = '<button onclick="loadUserIssue(1,' + data.pageSize + ',' + carBrand + ',' + issueCheck + ')">首页</button>\n' +
                    '\t\t\t\t\t<button onclick="loadUserIssue(' + bPage + ',' + data.pageSize + ',' + carBrand + ',' + issueCheck + ')">上一页</button>\n' +
                    '\t\t\t\t\t' + data.pageNum + '/' + data.pages + '\n' +
                    '\t\t\t\t\t<button onclick="loadUserIssue(' + aPage + ',' + data.pageSize + ',' + carBrand + ',' + issueCheck + ')">下一页</button>\n' +
                    '\t\t\t\t\t<button onclick="loadUserIssue(' + data.pages + ',' + data.pageSize + ',' + carBrand + ',' + issueCheck + ')">末页</button>'
                $("#user_issue").html(tableStr);
                $("#user_issue_div").html(divStr);
            });
        }


    </script>
</head>
<body>
<!--头部-->
<header class="publicHeader">
    <h1>二手车交易系统</h1>
    <div class="publicHeaderR">
        <p><span></span><span style="color: #fff21b"> </span> 用户：${user.userName}</p>
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
                <li><a href="${pageContext.request.contextPath }/jsp/frame.jsp">首页</a></li>
                <c:if test="${user.roleId==1}">
                    <li><a href="${pageContext.request.contextPath }/jsp/carList.jsp">车辆信息管理</a></li>
                    <li><a href="${pageContext.request.contextPath }/jsp/indentList.jsp">订单信息管理</a></li>
                    <li><a href="${pageContext.request.contextPath }/jsp/userList.jsp">用户信息管理</a></li>
                </c:if>
                <c:if test="${user.roleId==0}">
                    <li><a href="${pageContext.request.contextPath }/jsp/carList.jsp">车辆信息</a></li>
                    <li><a href="${pageContext.request.contextPath }/jsp/indentList.jsp">订单信息</a></li>
                    <li><a href="${pageContext.request.contextPath }/jsp/userIssue.jsp">发布车辆信息</a></li>
                </c:if>
                <li><a href="${pageContext.request.contextPath }/jsp/pwdmodify.jsp">密码修改</a></li>
                <%--                <li><a href="${pageContext.request.contextPath }/login.jsp">退出系统</a></li>--%>
            </ul>
        </nav>
    </div>

    <input type="hidden" id="path" name="path" value=""/>
    <input type="hidden" id="referer" name="referer" value="<%=request.getHeader("Referer")%>"/>
