<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>二手车交易系统-登录</title>
    <link rel="stylesheet" type="text/css" href="css/common.css">
    <link rel="stylesheet" type="text/css" href="css/login.css">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!--导入angularJS文件-->
    <!--<script src="js/angular.min.js"></script>-->
    <!--导入jquery-->
    <script src="js/jquery-3.3.1.js"></script>
<%--    <script type="text/javascript" src="js/login.js"></script>--%>
</head>

<body>
<!--引入头部-->
<%--<div id="header"></div>--%>
<!-- 头部 end -->
<section id="login_wrap">
    <div class="rg_layout">

    </div>
    <div class="login-box">
        <div class="title">
            <img src="images/login_logo.png" alt="">
            <div style="font-size: 18px">
                <span><font color="#ff7f50"> 二手车交易系统 — 登录</font></span>
            </div>
        </div>
        <div class="login_inner">

            <!--登录错误提示消息-->
            <div id="errorMsg" class="alert alert-danger">
                <p>${errorMsg}</p>
            </div>
            <form id="loginForm" action="${pageContext.request.contextPath}/user/login" method="get"
                  accept-charset="utf-8">
                <input name="userName" type="text" placeholder="请输入账号" autocomplete="off">
                <input name="password" type="password" placeholder="请输入密码" autocomplete="off">
                <div class="verify">
                    <input name="check" type="text" placeholder="请输入验证码" autocomplete="off">
                    <span><img src="checkCode" alt="" onclick="changeCheckCode(this)"></span>
                    <script type="text/javascript">
                        //图片点击事件
                        function changeCheckCode(img) {
                            img.src = "checkCode?" + new Date().getTime();
                        }
                    </script>
                </div>
                <div class="submit_btn">
                    <button type="submit" id="btn_sub">登录</button>
                    <div class="auto_login">
                        <%--                        <input type="checkbox" name="" class="checkbox">--%>
                        <span></span>
                    </div>
                </div>
            </form>
            <div class="reg"><a href="${pageContext.request.contextPath}/register.jsp">没有账户？ 立即注册</a></div>
        </div>
    </div>
</section>
<!--引入尾部-->
<%@include file="/footer.jsp"%>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery-1.11.0.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<%--<script type="text/javascript" src="js/login.js"></script>--%>

<!--导入布局js，共享header和footer-->
<%--<script type="text/javascript" src="js/include.js"></script>--%>
</body>

</html>