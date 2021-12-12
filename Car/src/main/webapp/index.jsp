<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>系统登录 - 超市订单管理系统</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css" />
    <script type="application/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"/>

    <script>
        $(function () {
            $("#actionForm").submit(function () {
                $.post("${pageContext.request.contextPath}/user/login",$(this).serialize(),function (data) {
                    if(data){
                        location.href="http://localhost/x4/jsp/frame.jsp";
                    }

                })
                return false;
            });

        })
    </script>
</head>
<body class="login_bg">
<section class="loginBox">
    <header class="loginHeader">
        <h1>超市订单管理系统</h1>
    </header>
    <section class="loginCont">
        <form class="loginForm" action="${pageContext.request.contextPath}/user/login"  name="actionForm"   method="post" >
            <div class="info">${error }</div>
            <div class="inputbox">
                <label for="username">用户名：</label>
                <input type="text" class="input-text" id="username" name="username"  placeholder="请输入用户名" required/>
            </div>
            <div class="inputbox">
                <label for="password">密码：</label>
                <input type="password" id="password" name="password" placeholder="请输入密码" required/>
            </div>
            <div class="subBtn">

                <input type="submit" value="登录"/>
                <input type="reset" value="重置"/>
            </div>
        </form>
    </section>
</section>
</body>
</html>
