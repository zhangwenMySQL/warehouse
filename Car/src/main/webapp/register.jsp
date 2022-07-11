<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>注册</title>
        <link rel="stylesheet" type="text/css" href="css/common.css">
        <link rel="stylesheet" href="css/register.css">
		<!--导入jquery-->
		<script src="js/jquery-3.3.1.js"></script>

		<script>
			/*
				表单校验：
					1.用户名：单词字符，长度8到20位
					2.密码：   单词字符，长度8到20位
					3.email：邮件格式
					4.姓名：非空
					5.手机号：手机号格式
					6.出生日期：非空
					7.验证码：非空
			 */

			//校验用户名
			//单词字符，长度8到20位
			<%--function checkUsername() {--%>
            <%--    //1.获取用户名值--%>
			<%--	var username = $("#username").val();--%>
			<%--	//2.定义正则--%>
			<%--	var reg_username = /^\w{8,20}$/;--%>
			<%--	--%>
			<%--	//3.判断，给出提示信息--%>
			<%--    var flag = reg_username.test(username);--%>
			<%--    if(flag){--%>
			<%--        //用户名合法--%>
            <%--        $("#username").css("border","");--%>
			<%--	}else{--%>
			<%--        //用户名非法,加一个红色边框--%>
			<%--		$("#username").css("border","1px solid red");--%>
			<%--	}--%>
			<%--    --%>
            <%--    return flag;--%>
            <%--}--%>

            <%--//校验密码--%>
            <%--function checkPassword() {--%>
            <%--    //1.获取密码值--%>
            <%--    var password = $("#password").val();--%>
            <%--    //2.定义正则--%>
            <%--    var reg_password = /^\w{8,20}$/;--%>

            <%--    //3.判断，给出提示信息--%>
            <%--    var flag = reg_password.test(password);--%>
            <%--    if(flag){--%>
            <%--        //密码合法--%>
            <%--        $("#password").css("border","");--%>
            <%--    }else{--%>
            <%--        //密码非法,加一个红色边框--%>
            <%--        $("#password").css("border","1px solid red");--%>
            <%--    }--%>

            <%--    return flag;--%>
            <%--}--%>

            <%--//校验邮箱--%>
			<%--function checkEmail(){--%>
			<%--    //1.获取邮箱--%>
			<%--	var email = $("#email").val();--%>
			<%--	//2.定义正则		itcast@163.com--%>
			<%--	var reg_email = /^\w+@\w+\.\w+$/;--%>

			<%--	//3.判断--%>
			<%--	var flag = reg_email.test(email);--%>
			<%--	if(flag){--%>
            <%--        $("#email").css("border","");--%>
			<%--	}else{--%>
            <%--        $("#email").css("border","1px solid red");--%>
			<%--	}--%>

			<%--	return flag;--%>
			<%--}--%>

			<%--$(function () {--%>
            <%--    //当表单提交时，调用所有的校验方法--%>
			<%--	$("#registerForm").submit(function(){--%>
			<%--		//1.发送数据到服务器--%>
			<%--		if(checkUsername() && checkPassword() && checkEmail()){--%>
			<%--		    //校验通过,发送ajax请求，提交表单的数据   username=zhangsan&password=123--%>

			<%--			$.post("${pageContext.request.contextPath}/user/regist",$(this).serialize(),function(data){--%>
			<%--				//处理服务器响应的数据  data  {flag:true,errorMsg:"注册失败"}--%>
			<%--				if(data.flag){--%>
			<%--				    //注册成功，跳转成功页面--%>
			<%--					location.href="${pageContext.request.contextPath}/user/login";--%>
			<%--				}else{--%>
			<%--				    //注册失败,给errorMsg添加提示信息--%>
			<%--					$("#errorMsg").html(data.errorMsg);--%>

			<%--				}--%>
			<%--			});--%>

			<%--		}--%>
			<%--		//2.不让页面跳转--%>
            <%--        return false;--%>
            <%--        //如果这个方法没有返回值，或者返回为true，则表单提交，如果返回为false，则表单不提交--%>
			<%--	});--%>

            <%--    //当某一个组件失去焦点是，调用对应的校验方法--%>
			<%--	$("#username").blur(checkUsername);--%>
            <%--    $("#password").blur(checkPassword);--%>
            <%--    $("#email").blur(checkEmail);--%>


            <%--});--%>
			<%--$(function () {--%>
			<%--	&lt;%&ndash;$("#registerForm").submit(function () {&ndash;%&gt;--%>
			<%--	&lt;%&ndash;	$.get("${pageContext.request.contextPath}/user/register",$(this).serialize(),function (data) {&ndash;%&gt;--%>
			<%--	&lt;%&ndash;		if (data){&ndash;%&gt;--%>
			<%--	&lt;%&ndash;			alert("注册成功，请登录！")&ndash;%&gt;--%>
			<%--	&lt;%&ndash;			location.href="http://localhost/Car/login.jsp"&ndash;%&gt;--%>
			<%--	&lt;%&ndash;		}&ndash;%&gt;--%>
			<%--	&lt;%&ndash;	})&ndash;%&gt;--%>
			<%--	&lt;%&ndash;})&ndash;%&gt;--%>

			<%--})--%>


		</script>


    </head>
	<body>
	<!--引入头部-->
<%--	<div id="header"></div>--%>
        <!-- 头部 end -->
    	<div class="rg_layout">
    		<div class="rg_form clearfix">
    			<div class="rg_form_left">
    				<a href="#">新用户注册</a><br>
    				<a href="#">New User registration</a>
    			</div>
    			<div class="rg_form_center">
					<div id="errorMsg" style="color:#ff0000;text-align: center"></div>
					<!--注册表单-->
    				<form id="registerCar" >
						<!--提交处理请求的标识符-->
<%--						<input type="hidden" name="action" value="register">--%>
    					<table style="margin-top: 25px;" >
    						<tr>
    							<td class="td_left">
    								<label for="userName">用户名:</label>
    							</td>
    							<td class="td_right">
    								<input type="text" id="userName" name="userName" placeholder="请输入用户名">
    							</td>
    						</tr>
    						<tr>
    							<td class="td_left">
    								<label for="userPassword">密码:</label>
    							</td>
    							<td class="td_right">
    								<input type="text" id="userPassword" name="userPassword" placeholder="请输入密码">
    							</td>
    						</tr>
    						<tr>
    							<td class="td_left">
    								<label for="userPhone">电话号码:</label>
    							</td>
    							<td class="td_right">
    								<input type="text" id="userPhone" name="userPhone" placeholder="请输入电话号码">
    							</td>
    						</tr>

    						<tr>
    							<td class="td_left">
    								<label for="userEmail">Email:</label>
    							</td>
    							<td class="td_right">
    								<input type="text" id="userEmail" name="userEmail" placeholder="请输入邮箱">
    							</td>
    						</tr>
    						<tr>
    							<td class="td_left">
    								<label for="userAddress">地址:</label>
    							</td>
    							<td class="td_right">
    								<input type="text" id="userAddress" name="userAddress" placeholder="请输入地址">
    							</td>
    						</tr>
<%--    						<tr>--%>
<%--    							<td class="td_left">--%>
<%--    								<label for="check">验证码</label>--%>
<%--    							</td>--%>
<%--    							<td class="td_right check">--%>
<%--    								<input type="text" id="check" name="check" class="check">--%>
<%--    								<img src="checkCode" height="32px" alt="" onclick="changeCheckCode(this)">--%>
<%--									<script type="text/javascript">--%>
<%--										//图片点击事件--%>
<%--										function changeCheckCode(img) {--%>
<%--											img.src="checkCode?"+new Date().getTime();--%>
<%--                                        }--%>
<%--									</script>--%>
<%--    							</td>--%>
<%--    						</tr>--%>
    						<tr>
    							<td class="td_left"> 
    							</td>
    							<td class="td_right check"> 
    								<input type="submit" class="submit" value="注册">
									<span id="msg" style="color: red;"></span>
    							</td>
    						</tr>
    					</table>
    				</form>
    			</div>
    			<div class="rg_form_right">
    				<p>

    					<a href="${pageContext.request.contextPath}/login.jsp">已有账号？ 立即登录</a>
    				</p>
    			</div>
    		</div>
    	</div>
        <!--引入尾部-->
	<%@include file="/footer.jsp"%>
		<!--导入布局js，共享header和footer-->
		<script type="text/javascript" src="js/include.js"></script>
    	
    </body>
</html>