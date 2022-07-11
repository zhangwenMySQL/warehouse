<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/head.jsp" %>
<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>用户管理页面 >> 用户修改页面</span>
    </div>
    <div class="providerAdd">
        <form id="userForm" name="userForm" method="post" action="${pageContext.request.contextPath}/user/updateModify">
            <input type="hidden" name="method" value="modifyexe">
            <input type="hidden" name="uid" value=""/>
            <div>
                <label for="userName">用户名称：</label>
                <input type="text" name="userName" id="userName" value="${userCheck.userName }">
                <font color="red"></font>
            </div>
            <div>
                <label for="userPassword">用户密码：</label>
                <input type="password" name="userPassword" id="userPassword" value="${userCheck.userPassword }">
                <font color="red"></font>
            </div>
            <div>
                <label for="userPhone">用户电话：</label>
                <input type="text" name="userPhone" id="userPhone" value="${userCheck.userPhone }">
                <font color="red"></font>
            </div>
            <div>
                <label for="userAddress">用户地址：</label>
                <input type="text" name="userAddress" id="userAddress" value="${userCheck.userAddress }">
            </div>
            <div>
                <label for="userEmail">用户邮箱：</label>
                <input type="text" name="userEmail" id="userEmail" value="${userCheck.userEmail }">
            </div>

            <div class="providerAddBtn">
                <input type="submit" name="save" id="save" value="保存"/>
                <input type="button" id="back" name="back" value="返回"/>
            </div>
        </form>
    </div>
</div>
</section>
<%@include file="/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/usermodify.js"></script>
